package com.ceditor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ceditor.ai.AIChatHelper;
import com.ceditor.ai.AIProviderManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.EditorSearcher;

/**
 * EditorActivity - Code editor with Sora Editor, preview support, and AI integration.
 * Inspired by Acode/VSCode design.
 */
public class EditorActivity extends AppCompatActivity {

    private CodeEditor editor;
    private TextView editorFilename;
    private View btnBack;
    private ImageView btnUndo, btnRedo, btnSave, btnSearch, btnAi, btnPreview;
    private LinearLayout aiPanel;
    private TextView aiTitle, aiResponse;
    private EditText aiInput;
    private ImageButton aiSend;
    private ScrollView aiScroll;

    private String filePath;
    private String fileName;
    private boolean isModified = false;
    private AIChatHelper aiChatHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Apply language preference
        SharedPreferences data = getSharedPreferences("ceditor_prefs", MODE_PRIVATE);
        String langCode = data.getString("app_lang", "en");
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources res = getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());

        setContentView(R.layout.editor);
        initViews();
        setupToolbar();
        setupEditorButtons();
        setupAiPanel();
        handleIntent();
    }

    private void initViews() {
        editor = findViewById(R.id.editor);
        btnBack = findViewById(R.id.btn_back);
        editorFilename = findViewById(R.id.editor_filename);
        btnUndo = findViewById(R.id.btn_undo);
        btnRedo = findViewById(R.id.btn_redo);
        btnSave = findViewById(R.id.btn_save);
        btnSearch = findViewById(R.id.btn_search);
        btnAi = findViewById(R.id.btn_ai);
        btnPreview = findViewById(R.id.btn_preview);
        aiPanel = findViewById(R.id.ai_panel);
        aiTitle = findViewById(R.id.ai_title);
        aiResponse = findViewById(R.id.ai_response);
        aiInput = findViewById(R.id.ai_input);
        aiSend = findViewById(R.id.ai_send);
        aiScroll = findViewById(R.id.ai_scroll);
    }

    private void setupToolbar() {
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> onBackPressed());
        }
    }

    private void setupEditorButtons() {
        btnUndo.setOnClickListener(v -> editor.undo());
        btnRedo.setOnClickListener(v -> editor.redo());
        btnSave.setOnClickListener(v -> saveFile());
        btnSearch.setOnClickListener(v -> showSearchDialog());
        btnAi.setOnClickListener(v -> toggleAiPanel());
        btnPreview.setOnClickListener(v -> openPreview());
    }

    private void setupAiPanel() {
        aiChatHelper = new AIChatHelper(this, new AIProviderManager(this));
        aiSend.setOnClickListener(v -> {
            String message = aiInput.getText().toString().trim();
            if (!message.isEmpty()) {
                sendMessageToAI(message);
                aiInput.setText("");
            }
        });
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("file_path")) {
            filePath = intent.getStringExtra("file_path");
            File file = new File(filePath);
            fileName = file.getName();
            editor.setEditorLanguage(new JavaLanguage());
            loadFileContent(file);
            updateTitle();
        } else {
            fileName = "untitled.txt";
            editor.setEditorLanguage(new JavaLanguage());
            updateTitle();
        }
    }

    private void loadFileContent(File file) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            editor.setText(sb.toString());
            isModified = false;
            if (isPreviewableFile(fileName)) {
                btnPreview.setVisibility(View.VISIBLE);
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error loading: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isPreviewableFile(String name) {
        if (name == null) return false;
        String lower = name.toLowerCase();
        return lower.endsWith(".html") || lower.endsWith(".htm") ||
                lower.endsWith(".css") || lower.endsWith(".js") ||
                lower.endsWith(".md") || lower.endsWith(".markdown");
    }

    private void openPreview() {
        if (filePath != null && isPreviewableFile(fileName)) {
            Intent previewIntent = new Intent(this, PreviewActivity.class);
            previewIntent.putExtra("file_path", filePath);
            startActivity(previewIntent);
        } else {
            Toast.makeText(this, R.string.preview_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFile() {
        if (filePath != null) {
            File file = new File(filePath);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(editor.getText().toString().getBytes(StandardCharsets.UTF_8));
                fos.close();
                isModified = false;
                updateTitle();
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(this, "Error saving: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSearchDialog() {
        EditText searchInput = new EditText(this);
        searchInput.setHint("Search...");
        searchInput.setPadding(48, 24, 48, 24);
        new AlertDialog.Builder(this)
                .setTitle("Search")
                .setView(searchInput)
                .setPositiveButton("Search", (dialog, which) -> {
                    String query = searchInput.getText().toString();
                    if (!query.isEmpty()) {
                        EditorSearcher.SearchOptions options =
                                new EditorSearcher.SearchOptions(false, false);
                        editor.getSearcher().search(query, options);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void updateTitle() {
        String title = fileName != null ? fileName : "untitled";
        if (isModified) title += " *";
        if (editorFilename != null) {
            editorFilename.setText(title);
        }
    }

    private void toggleAiPanel() {
        if (aiPanel.getVisibility() == View.VISIBLE) {
            aiPanel.setVisibility(View.GONE);
        } else {
            aiPanel.setVisibility(View.VISIBLE);
            aiInput.requestFocus();
        }
    }

    private void sendMessageToAI(String message) {
        aiResponse.setText(R.string.ai_thinking);
        String codeContext = editor.getText().toString();
        aiChatHelper.sendMessage(message, codeContext, new AIChatHelper.AIResponseListener() {
            @Override
            public void onResponse(String response) {
                aiResponse.setText(response);
            }
            @Override
            public void onError(String error) {
                aiResponse.setText("Error: " + error);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editor_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_wrap) {
            editor.setWordwrap(!editor.isWordwrap());
            return true;
        } else if (id == R.id.action_indent) {
            editor.setTabWidth(Math.min(editor.getTabWidth() + 1, 8));
            return true;
        } else if (id == R.id.action_preview) {
            openPreview();
            return true;
        } else if (id == R.id.action_info) {
            showFileInfo();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFileInfo() {
        String text = editor.getText().toString();
        int lines = text.split("\n").length;
        int chars = text.length();
        String info = String.format("Lines: %d\nCharacters: %d\nFile: %s",
                lines, chars, fileName != null ? fileName : "New file");
        new AlertDialog.Builder(this)
                .setTitle("File Info")
                .setMessage(info)
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void onBackPressed() {
        if (isModified) {
            new AlertDialog.Builder(this)
                    .setTitle("Unsaved Changes")
                    .setMessage("Do you want to save before closing?")
                    .setPositiveButton("Save", (dialog, which) -> {
                        saveFile();
                        super.onBackPressed();
                    })
                    .setNegativeButton("Discard", (dialog, which) -> {
                        super.onBackPressed();
                    })
                    .setNeutralButton("Cancel", null)
                    .show();
        } else {
            super.onBackPressed();
        }
    }
}
