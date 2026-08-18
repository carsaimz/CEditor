package com.ceditor;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * PreviewActivity - Renders HTML, CSS, JS, and Markdown files in a WebView.
 */
public class PreviewActivity extends AppCompatActivity {

    private WebView webView;
    private Toolbar toolbar;
    private ImageButton btnBack;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview);

        webView = findViewById(R.id.preview_webview);
        toolbar = findViewById(R.id.preview_toolbar);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> onBackPressed());
        handleIntent();
    }

    private void handleIntent() {
        filePath = getIntent().getStringExtra("file_path");
        if (filePath != null) {
            File file = new File(filePath);
            String fileName = file.getName();
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Preview: " + fileName);
            }
            loadPreview(file, fileName);
        } else {
            Toast.makeText(this, R.string.preview_error, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void loadPreview(File file, String fileName) {
        String lowerName = fileName.toLowerCase();
        if (lowerName.endsWith(".md") || lowerName.endsWith(".markdown")) {
            loadMarkdown(file);
        } else {
            loadHtml(file);
        }
    }

    private void loadHtml(File file) {
        try {
            String content = readFileContent(file);
            webView.setWebViewClient(new WebViewClient());
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setAllowFileAccess(true);
            settings.setDomStorageEnabled(true);

            String baseUrl = "file://" + file.getParent() + "/";
            webView.loadDataWithBaseURL(baseUrl, content, "text/html", "UTF-8", null);
        } catch (IOException e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadMarkdown(File file) {
        try {
            String mdContent = readFileContent(file);
            String htmlContent = convertMarkdownToHtml(mdContent);

            WebSettings settings = webView.getSettings();
            settings.setAllowFileAccess(true);
            settings.setDomStorageEnabled(true);

            webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);
        } catch (IOException e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String readFileContent(File file) throws IOException {
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

    private String convertMarkdownToHtml(String markdown) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n");
        html.append("<meta charset=\"UTF-8\">\n");
        html.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        html.append("<style>\n");
        html.append("body{font-family:-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,sans-serif;max-width:800px;margin:0 auto;padding:20px;color:#333;line-height:1.6;}\n");
        html.append("h1{font-size:2em;border-bottom:1px solid #eee;padding-bottom:8px;}\n");
        html.append("h2{font-size:1.5em;border-bottom:1px solid #eee;padding-bottom:6px;}\n");
        html.append("code{background:#f4f4f4;padding:2px 6px;border-radius:3px;font-family:monospace;}\n");
        html.append("pre{background:#f4f4f4;padding:12px;border-radius:6px;overflow-x:auto;}\n");
        html.append("pre code{background:none;padding:0;}\n");
        html.append("blockquote{border-left:4px solid #ddd;margin:0;padding-left:16px;color:#666;}\n");
        html.append("a{color:#0366d6;}\nul,ol{padding-left:24px;}\n");
        html.append("hr{border:none;border-top:1px solid #eee;margin:16px 0;}\nimg{max-width:100%;}\n");
        html.append("table{border-collapse:collapse;width:100%;}\nth,td{border:1px solid #ddd;padding:8px;text-align:left;}\nth{background:#f4f4f4;}\n");
        html.append("</style>\n</head>\n<body>\n");

        String[] lines = markdown.split("\n");
        boolean inCodeBlock = false;
        boolean inList = false;

        for (String line : lines) {
            if (line.trim().startsWith("```")) {
                if (inCodeBlock) {
                    html.append("</code></pre>\n");
                    inCodeBlock = false;
                } else {
                    if (inList) { html.append("</ul>\n"); inList = false; }
                    String lang = line.trim().substring(3).trim();
                    html.append("<pre><code class=\"language-").append(lang).append("\">");
                    inCodeBlock = true;
                }
                continue;
            }
            if (inCodeBlock) {
                html.append(escapeHtml(line)).append("\n");
                continue;
            }
            if (line.startsWith("# ")) {
                if (inList) { html.append("</ul>\n"); inList = false; }
                html.append("<h1>").append(escapeHtml(line.substring(2))).append("</h1>\n");
            } else if (line.startsWith("## ")) {
                if (inList) { html.append("</ul>\n"); inList = false; }
                html.append("<h2>").append(escapeHtml(line.substring(3))).append("</h2>\n");
            } else if (line.startsWith("### ")) {
                if (inList) { html.append("</ul>\n"); inList = false; }
                html.append("<h3>").append(escapeHtml(line.substring(4))).append("</h3>\n");
            } else if (line.trim().equals("---") || line.trim().equals("***")) {
                if (inList) { html.append("</ul>\n"); inList = false; }
                html.append("<hr>\n");
            } else if (line.startsWith("> ")) {
                if (inList) { html.append("</ul>\n"); inList = false; }
                html.append("<blockquote>").append(escapeHtml(line.substring(2))).append("</blockquote>\n");
            } else if (line.startsWith("- ") || line.startsWith("* ")) {
                if (!inList) { html.append("<ul>\n"); inList = true; }
                html.append("<li>").append(escapeHtml(line.substring(2))).append("</li>\n");
            } else if (line.matches("^\\d+\\. .*")) {
                if (!inList) { html.append("<ol>\n"); inList = true; }
                html.append("<li>").append(escapeHtml(line.replaceFirst("^\\d+\\. ", ""))).append("</li>\n");
            } else if (line.trim().isEmpty()) {
                if (inList) { html.append("</ul>\n"); inList = false; }
                html.append("<br>\n");
            } else {
                if (inList) { html.append("</ul>\n"); inList = false; }
                html.append("<p>").append(escapeHtml(line)).append("</p>\n");
            }
        }

        if (inCodeBlock) html.append("</code></pre>\n");
        if (inList) html.append("</ul>\n");

        html.append("</body>\n</html>");
        return html.toString();
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}
