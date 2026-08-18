package com.ceditor.ai;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * AI Chat Helper - Provides a simple AI chat dialog for the editor.
 * Allows users to ask questions about their code or get AI assistance.
 */
public class AIChatHelper {

    private static final String SYSTEM_PROMPT = 
        "You are a helpful code assistant integrated into CEditor, a mobile code editor. " +
        "Provide concise, practical answers. When suggesting code changes, format them clearly. " +
        "You can help with: code explanations, debugging, refactoring, writing new code, " +
        "explaining concepts, and code reviews.";

    private Context context;
    private AIProviderManager providerManager;
    private String codeContext;

    public AIChatHelper(Context context, AIProviderManager providerManager) {
        this.context = context;
        this.providerManager = providerManager;
    }

    public void setCodeContext(String code) {
        this.codeContext = code;
    }

    /**
     * Show AI chat dialog
     */
    public void showChatDialog() {
        // Get active providers
        List<String> activeProviders = providerManager.getActiveProviders();
        
        if (activeProviders.isEmpty()) {
            // Show error - no providers configured
            new AlertDialog.Builder(context)
                    .setTitle("AI Assistant")
                    .setMessage("No AI providers are configured. Go to Settings > AI Providers to add API keys.")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        // Use first active provider for now
        String provider = activeProviders.get(0);
        String providerName = getProviderDisplayName(provider);

        // Build dialog
        LinearLayout dialogLayout = new LinearLayout(context);
        dialogLayout.setOrientation(LinearLayout.VERTICAL);
        dialogLayout.setPadding(32, 24, 32, 24);

        // Info text
        TextView infoText = new TextView(context);
        infoText.setText("Using: " + providerName);
        infoText.setTextSize(12);
        infoText.setTextColor(Color.GRAY);
        infoText.setGravity(Gravity.CENTER);
        dialogLayout.addView(infoText);

        // Input field
        EditText inputField = new EditText(context);
        inputField.setHint("Ask AI about your code...");
        inputField.setTextSize(14);
        inputField.setTextColor(Color.WHITE);
        inputField.setHintTextColor(Color.GRAY);
        inputField.setBackgroundColor(Color.parseColor("#333333"));
        inputField.setPadding(16, 12, 16, 12);
        LinearLayout.LayoutParams inputParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        inputParams.topMargin = 12;
        dialogLayout.addView(inputField, inputParams);

        // Response area
        TextView responseText = new TextView(context);
        responseText.setTextSize(13);
        responseText.setTextColor(Color.WHITE);
        responseText.setPadding(8, 8, 8, 8);
        LinearLayout.LayoutParams responseParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        responseParams.topMargin = 12;
        dialogLayout.addView(responseText, responseParams);

        // Loading indicator
        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setVisibility(View.GONE);
        dialogLayout.addView(progressBar);

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("AI Assistant")
                .setView(dialogLayout)
                .setPositiveButton("Send", null) // We'll override this
                .setNegativeButton("Cancel", null)
                .create();

        dialog.show();

        // Override positive button to handle async
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String userMessage = inputField.getText().toString().trim();
            if (userMessage.isEmpty()) return;

            inputField.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
            responseText.setText("Thinking...");

            // Limit code context to avoid huge payloads
            String limitedContext = codeContext != null && codeContext.length() > 5000 
                    ? codeContext.substring(0, 5000) + "\n...(truncated)" 
                    : codeContext;

            CompletableFuture<String> future = providerManager.sendChatCompletion(
                    provider, SYSTEM_PROMPT, userMessage, limitedContext != null ? limitedContext : "");

            future.thenAccept(response -> {
                new Handler(Looper.getMainLooper()).post(() -> {
                    progressBar.setVisibility(View.GONE);
                    inputField.setEnabled(true);
                    inputField.setText("");
                    
                    // Format response (simple markdown-like formatting)
                    SpannableStringBuilder formatted = new SpannableStringBuilder(response);
                    responseText.setText(formatted);
                });
            }).exceptionally(e -> {
                new Handler(Looper.getMainLooper()).post(() -> {
                    progressBar.setVisibility(View.GONE);
                    inputField.setEnabled(true);
                    responseText.setText("Error: " + e.getMessage());
                    responseText.setTextColor(Color.RED);
                });
                return null;
            });
        });
    }

    /**
     * Quick AI action: Ask AI to explain selected code
     */
    public void explainCode(String selectedCode) {
        setCodeContext(selectedCode);
        showChatDialog();
    }

    /**
     * Quick AI action: Ask AI to fix/complete code
     */
    public void fixCode(String currentCode) {
        setCodeContext(currentCode);
        showChatDialog();
    }

    private String getProviderDisplayName(String providerId) {
        for (AIProviderManager.ProviderInfo info : providerManager.getAvailableProviders()) {
            if (info.id.equals(providerId)) return info.name;
        }
        return providerId;
    }
}
