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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ceditor.ai.AIChatHelper;
import com.ceditor.ai.AIProviderManager;
import com.google.android.material.appbar.AppBarLayout;

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
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import io.github.rosemoe.sora.widget.schemes.SchemeDarcula;

public class EditorActivity extends AppCompatActivity {

    private CodeEditor editor;
    private Toolbar toolbar;
    private ImageView btnUndo, btnRedo, btnSave, btnSearch, btnAi;
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
        applyLanguage();

        setContentView(R.layout.editor);

        initViews();
        setupToolbar();
        setupEditor();
        setupAiPanel();
        loadFile();
    }

    private void applyLanguage() {
        SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
        String langCode = data.getString("app_lang", "en");
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources res = getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        btnUndo = findViewById(R.id.btn_undo);
        btnRedo = findViewById(R.id.btn_redo);
        btnSave = findViewById(R.id.btn_save);
        btnSearch = findViewById(R.id.btn_search);
        btnAi = findViewById(R.id.btn_ai);
        aiPanel = findViewById(R.id.ai_panel);
        aiTitle = findViewById(R.id.ai_title);
        aiResponse = findViewById(R.id.ai_response);
        aiInput = findViewById(R.id.ai_input);
        aiSend = findViewById(R.id.ai_send);
        aiScroll = findViewById(R.id.ai_scroll);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        btnUndo.setOnClickListener(v -> editor.undo());
        btnRedo.setOnClickListener(v -> editor.redo());
        btnSave.setOnClickListener(v -> saveFile());
        btnSearch.setOnClickListener(v -> showSearchDialog());
        btnAi.setOnClickListener(v -> toggleAiPanel());
    }

    private void setupEditor() {
        editor = findViewById(R.id.editor);

        // Set color scheme
        EditorColorScheme scheme = new SchemeDarcula();
        editor.setColorScheme(scheme);

        // Enable features
        editor.setLineNumberEnabled(true);
        editor.setWordwrap(true);
        editor.setHighlightCurrentLine(true);
        editor.setPinLineNumber(true);
        editor.setHighlightBracketPair(true);

        // Set text change listener using Content's listener
        editor.getText().addContentListener(new io.github.rosemoe.sora.text.ContentListener() {
            @Override
            public void beforeReplace(io.github.rosemoe.sora.text.Content text) {
            }

            @Override
            public void afterInsert(io.github.rosemoe.sora.text.Content text, int startLine, int startColumn, int endLine, int endColumn, CharSequence inserted) {
                isModified = true;
                updateTitle();
            }

            @Override
            public void afterDelete(io.github.rosemoe.sora.text.Content text, int startLine, int startColumn, int endLine, int endColumn, CharSequence deleted) {
                isModified = true;
                updateTitle();
            }
        });
    }

    private void setupAiPanel() {
        aiSend.setOnClickListener(v -> sendAiMessage());
        aiInput.setOnEditorActionListener((v, actionId, event) -> {
            sendAiMessage();
            return true;
        });
    }

    private void toggleAiPanel() {
        if (aiPanel.getVisibility() == View.VISIBLE) {
            aiPanel.setVisibility(View.GONE);
        } else {
            aiPanel.setVisibility(View.VISIBLE);
            aiInput.requestFocus();
        }
    }

    private void sendAiMessage() {
        String message = aiInput.getText().toString().trim();
        if (message.isEmpty()) return;

        aiInput.setText("");
        aiResponse.append("You: " + message + "\n\n");
        aiResponse.append("AI: " + getString(R.string.ai_thinking) + "\n\n");
        aiScroll.post(() -> aiScroll.fullScroll(ScrollView.FOCUS_DOWN));

        // Get current code for context
        String code = editor.getText().toString();

        // Initialize AI helper if needed
        if (aiChatHelper == null) {
            AIProviderManager providerManager = new AIProviderManager(this);
            aiChatHelper = new AIChatHelper(this, providerManager);
        }

        final String thinkingMarker = getString(R.string.ai_thinking);

        // Send message asynchronously
        aiChatHelper.sendMessage(message, code, new AIChatHelper.AIResponseListener() {
            @Override
            public void onResponse(String response) {
                runOnUiThread(() -> {
                    String currentText = aiResponse.getText().toString();
                    int thinkingIndex = currentText.lastIndexOf(thinkingMarker);
                    if (thinkingIndex >= 0) {
                        String beforeThinking = currentText.substring(0, thinkingIndex);
                        aiResponse.setText(beforeThinking + "AI: " + response + "\n\n");
                    } else {
                        aiResponse.append("AI: " + response + "\n\n");
                    }
                    aiScroll.post(() -> aiScroll.fullScroll(ScrollView.FOCUS_DOWN));
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    String currentText = aiResponse.getText().toString();
                    int thinkingIndex = currentText.lastIndexOf(thinkingMarker);
                    if (thinkingIndex >= 0) {
                        String beforeThinking = currentText.substring(0, thinkingIndex);
                        aiResponse.setText(beforeThinking + "AI: Error: " + error + "\n\n");
                    } else {
                        aiResponse.append("AI: Error: " + error + "\n\n");
                    }
                    aiScroll.post(() -> aiScroll.fullScroll(ScrollView.FOCUS_DOWN));
                });
            }
        });
    }

    private void loadFile() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("file_path")) {
            filePath = intent.getStringExtra("file_path");
        }

        if (filePath != null) {
            File file = new File(filePath);
            fileName = file.getName();

            // Set language highlighting based on extension
            setLanguageHighlighting(fileName);

            // Load file content
            loadFileContent(file);

            updateTitle();
        } else {
            fileName = "untitled.txt";
            updateTitle();
        }
    }

    private void setLanguageHighlighting(String fileName) {
        String lowerName = fileName.toLowerCase();
        if (lowerName.endsWith(".java") || lowerName.endsWith(".kt")) {
            editor.setEditorLanguage(new JavaLanguage());
        } else {
            editor.setEditorLanguage(new JavaLanguage());
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
        } catch (IOException e) {
            Toast.makeText(this, "Error loading file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
        } else {
            Toast.makeText(this, "No file path set", Toast.LENGTH_SHORT).show();
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
                        io.github.rosemoe.sora.widget.EditorSearcher.SearchOptions options =
                                new io.github.rosemoe.sora.widget.EditorSearcher.SearchOptions(false, false);
                        editor.getSearcher().search(query, options);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void updateTitle() {
        if (getSupportActionBar() != null) {
            String title = fileName != null ? fileName : "Editor";
            if (isModified) {
                title += " *";
            }
            getSupportActionBar().setTitle(title);
        }
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
        String info = String.format("Lines: %d\nCharacters: %d\nFile: %s", lines, chars, fileName != null ? fileName : "New file");

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
