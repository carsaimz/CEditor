package com.ceditor;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * TerminalActivity - Termux-style terminal emulator.
 */
public class TerminalActivity extends AppCompatActivity {

    private ScrollView terminalScroll;
    private TextView terminalOutput;
    private EditText terminalInput;
    private Button btnRun;
    private Button btnClear;
    private ImageButton btnBack;

    private String currentDir;
    private static final String PS1 = "$ ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences data = getSharedPreferences("ceditor_prefs", MODE_PRIVATE);
        String langCode = data.getString("app_lang", "en");
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources res = getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());

        setContentView(R.layout.terminal);

        terminalScroll = findViewById(R.id.terminal_scroll);
        terminalOutput = findViewById(R.id.terminal_output);
        terminalInput = findViewById(R.id.terminal_input);
        btnRun = findViewById(R.id.btn_run);
        btnClear = findViewById(R.id.btn_clear);
        btnBack = findViewById(R.id.btn_back);

        btnRun.setOnClickListener(v -> executeCommand());
        btnClear.setOnClickListener(v -> clearTerminal());
        btnBack.setOnClickListener(v -> onBackPressed());

        currentDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        appendOutput("CEditor Terminal\n");
        appendOutput("Type 'help' for available commands\n");
        appendPrompt();
    }

    private void executeCommand() {
        String command = terminalInput.getText().toString().trim();
        if (command.isEmpty()) return;

        appendOutput(PS1 + command + "\n");
        terminalInput.setText("");

        if (command.equals("help")) {
            appendOutput("Available commands:\n");
            appendOutput("  help     - Show this help\n");
            appendOutput("  clear    - Clear terminal\n");
            appendOutput("  pwd      - Print working directory\n");
            appendOutput("  cd <dir> - Change directory\n");
            appendOutput("  ls       - List files\n");
            appendOutput("  exit     - Close terminal\n");
            appendOutput("  Any shell command via Runtime.exec\n\n");
            appendPrompt();
            return;
        }

        if (command.equals("clear") || command.equals("cls")) {
            clearTerminal();
            return;
        }

        if (command.equals("pwd")) {
            appendOutput(currentDir + "\n");
            appendPrompt();
            return;
        }

        if (command.startsWith("cd ")) {
            String target = command.substring(3).trim();
            if (target.equals("..")) {
                File parent = new File(currentDir).getParentFile();
                if (parent != null) currentDir = parent.getAbsolutePath();
            } else {
                File targetDir = new File(target);
                if (!targetDir.isAbsolute()) targetDir = new File(currentDir, target);
                if (targetDir.isDirectory()) {
                    currentDir = targetDir.getAbsolutePath();
                } else {
                    appendOutput("cd: no such directory: " + target + "\n");
                }
            }
            appendPrompt();
            return;
        }

        if (command.equals("ls")) {
            File dir = new File(currentDir);
            File[] files = dir.listFiles();
            if (files != null) {
                for (File f : files) {
                    appendOutput((f.isDirectory() ? f.getName() + "/" : f.getName()) + "\n");
                }
            }
            appendPrompt();
            return;
        }

        if (command.equals("exit") || command.equals("quit")) {
            finish();
            return;
        }

        // Execute as shell command
        new Thread(() -> {
            try {
                String[] env = {"PATH=/system/bin:/system/xbin:/sbin", "HOME=" + currentDir};
                Process process = Runtime.getRuntime().exec(
                        new String[]{"sh", "-c", command}, env, new File(currentDir));

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) output.append(line).append("\n");

                StringBuilder errors = new StringBuilder();
                while ((line = errorReader.readLine()) != null) errors.append(line).append("\n");

                process.waitFor();

                final String result = output.toString();
                final String error = errors.toString();

                runOnUiThread(() -> {
                    if (!result.isEmpty()) appendOutput(result);
                    if (!error.isEmpty()) appendOutput("[ERROR] " + error);
                    if (result.isEmpty() && error.isEmpty()) appendOutput("(no output)\n");
                    appendPrompt();
                });
            } catch (IOException | InterruptedException e) {
                runOnUiThread(() -> {
                    appendOutput("[ERROR] " + e.getMessage() + "\n");
                    appendPrompt();
                });
            }
        }).start();
    }

    private void appendOutput(String text) {
        runOnUiThread(() -> {
            terminalOutput.append(text);
            terminalScroll.post(() -> terminalScroll.fullScroll(ScrollView.FOCUS_DOWN));
        });
    }

    private void appendPrompt() {
        runOnUiThread(() -> {
            terminalOutput.append(PS1 + " ");
            terminalScroll.post(() -> terminalScroll.fullScroll(ScrollView.FOCUS_DOWN));
        });
    }

    private void clearTerminal() {
        runOnUiThread(() -> {
            terminalOutput.setText("");
            appendPrompt();
        });
    }
}
