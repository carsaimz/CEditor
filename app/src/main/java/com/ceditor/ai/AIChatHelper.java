package com.ceditor.ai;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * AI Chat Helper - Enhanced wrapper around AIProviderManager with file system access.
 * Supports: reading files, creating files, listing directories, and code assistance.
 */
public class AIChatHelper {

    private static final String SYSTEM_PROMPT =
            "You are a powerful code assistant integrated into CEditor, a mobile code editor. " +
                    "You have access to the device's file system. When the user asks about their project, " +
                    "you can reference file operations. Provide concise, practical answers. " +
                    "You can help with: code explanations, debugging, refactoring, writing new code, " +
                    "explaining concepts, code reviews, project structure analysis, and file management. " +
                    "Format code blocks with ```language\\n code \\n```. " +
                    "When suggesting file changes, clearly specify the file path and what to change.";

    private Context context;
    private AIProviderManager providerManager;

    public AIChatHelper(Context context, AIProviderManager providerManager) {
        this.context = context;
        this.providerManager = providerManager;
    }

    public interface AIResponseListener {
        void onResponse(String response);
        void onError(String error);
    }

    /**
     * Send a message to AI with file system context.
     * Includes current editor content, project info, and file system capabilities.
     */
    public void sendMessage(String userMessage, String codeContext, AIResponseListener listener) {
        List<String> activeProviders = providerManager.getActiveProviders();
        if (activeProviders.isEmpty()) {
            listener.onError("No AI providers configured. Go to Settings > AI Providers.");
            return;
        }

        String provider = activeProviders.get(0);
        String limitedContext = limitContext(codeContext);

        // Build enhanced context with file system info
        String projectInfo = getProjectInfo();
        String fileCapabilities = getFileCapabilities();
        String fullContext = buildFullContext(limitedContext, projectInfo, fileCapabilities);

        CompletableFuture<String> future = providerManager.sendChatCompletion(
                provider, SYSTEM_PROMPT, userMessage, fullContext);

        future.thenAccept(response -> {
            new Handler(Looper.getMainLooper()).post(() -> listener.onResponse(response));
        }).exceptionally(e -> {
            new Handler(Looper.getMainLooper()).post(() -> listener.onError(e.getMessage()));
            return null;
        });
    }

    private String limitContext(String codeContext) {
        if (codeContext != null && codeContext.length() > 5000) {
            return codeContext.substring(0, 5000) + "\n...(truncated for length)";
        }
        return codeContext != null ? codeContext : "";
    }

    private String getProjectInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("CURRENT WORKING DIRECTORY: ");
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append("\n");

        // List common project directories
        File extDir = Environment.getExternalStorageDirectory();
        File[] files = extDir.listFiles();
        if (files != null && files.length > 0) {
            sb.append("TOP-LEVEL ITEMS: ");
            int count = 0;
            for (File f : files) {
                if (count >= 10) {
                    sb.append("... and ").append(files.length - 10).append(" more");
                    break;
                }
                if (f.isDirectory() && !f.getName().startsWith(".")) {
                    sb.append(f.getName()).append("/ ");
                }
                count++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private String getFileCapabilities() {
        return "FILE SYSTEM CAPABILITIES:\n" +
                "- Can read any file on the device\n" +
                "- Can create new files and directories\n" +
                "- Can modify existing files\n" +
                "- Can delete files\n" +
                "- Can list directory contents\n" +
                "- Can analyze project structure\n" +
                "\nWhen suggesting changes, provide:\n" +
                "1. Full file path (relative to working directory or absolute)\n" +
                "2. What to create/modify/delete\n" +
                "3. Complete content for new files or clear diffs for modifications\n";
    }

    private String buildFullContext(String codeContext, String projectInfo, String fileCapabilities) {
        StringBuilder sb = new StringBuilder();
        sb.append(projectInfo);
        sb.append("\n").append(fileCapabilities);
        sb.append("\n--- CURRENT EDITOR CONTENT ---\n");
        sb.append(codeContext);
        sb.append("\n--- END CURRENT EDITOR CONTENT ---\n");
        return sb.toString();
    }

    // --- File System Operations ---

    /**
     * Read a file's contents and return them.
     */
    public static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) return "File not found: " + filePath;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    /**
     * Write content to a file (create or overwrite).
     */
    public static boolean writeFile(String filePath, String content) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            if (!parent.mkdirs()) {
                return false;
            }
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(content.getBytes(StandardCharsets.UTF_8));
        fos.close();
        return true;
    }

    /**
     * Delete a file.
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }

    /**
     * Create a directory.
     */
    public static boolean createDirectory(String dirPath) {
        File dir = new File(dirPath);
        return dir.mkdirs();
    }

    /**
     * List files in a directory.
     */
    public static List<String> listFiles(String dirPath) {
        List<String> result = new ArrayList<>();
        File dir = new File(dirPath);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File f : files) {
                    result.add(f.isDirectory() ? f.getName() + "/" : f.getName());
                }
            }
        }
        return result;
    }

    /**
     * Get project structure as a tree string.
     */
    public static String getProjectStructure(String rootPath, int maxDepth) {
        return getProjectStructureRecursive(new File(rootPath), "", 0, maxDepth);
    }

    private static String getProjectStructureRecursive(File dir, String prefix, int depth, int maxDepth) {
        StringBuilder sb = new StringBuilder();
        if (depth > maxDepth) return sb.toString();

        File[] files = dir.listFiles();
        if (files == null) return sb.toString();

        // Sort: directories first, then files
        List<File> dirs = new ArrayList<>();
        List<File> regularFiles = new ArrayList<>();
        for (File f : files) {
            if (f.getName().startsWith(".") || f.getName().equals("node_modules") ||
                    f.getName().equals("build") || f.getName().equals(".gradle")) {
                continue;
            }
            if (f.isDirectory()) dirs.add(f);
            else regularFiles.add(f);
        }

        int total = dirs.size() + regularFiles.size();
        for (int i = 0; i < dirs.size(); i++) {
            boolean isLast = (i == dirs.size() - 1) && regularFiles.isEmpty();
            sb.append(prefix).append(isLast ? "└── " : "├── ");
            sb.append(dirs.get(i).getName()).append("/\n");
            if (!isLast) {
                sb.append(getProjectStructureRecursive(dirs.get(i), prefix + "│   ", depth + 1, maxDepth));
            } else {
                sb.append(getProjectStructureRecursive(dirs.get(i), prefix + "    ", depth + 1, maxDepth));
            }
        }
        for (int i = 0; i < regularFiles.size(); i++) {
            boolean isLast = (i == regularFiles.size() - 1);
            sb.append(prefix).append(isLast ? "└── " : "├── ");
            sb.append(regularFiles.get(i).getName()).append("\n");
        }
        return sb.toString();
    }
}
