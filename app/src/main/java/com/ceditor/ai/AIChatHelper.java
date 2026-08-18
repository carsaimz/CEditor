package com.ceditor.ai;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * AI Chat Helper - Simple wrapper around AIProviderManager
 * for use in EditorActivity's inline AI panel.
 */
public class AIChatHelper {

    private static final String SYSTEM_PROMPT =
            "You are a helpful code assistant integrated into CEditor, a mobile code editor. " +
                    "Provide concise, practical answers. When suggesting code changes, format them clearly. " +
                    "You can help with: code explanations, debugging, refactoring, writing new code, " +
                    "explaining concepts, and code reviews.";

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

    public void sendMessage(String userMessage, String codeContext, AIResponseListener listener) {
        List<String> activeProviders = providerManager.getActiveProviders();

        if (activeProviders.isEmpty()) {
            listener.onError("No AI providers configured. Go to Settings > AI Providers.");
            return;
        }

        // Use first active provider
        String provider = activeProviders.get(0);

        // Limit code context
        String limitedContext = codeContext != null && codeContext.length() > 5000
                ? codeContext.substring(0, 5000) + "\n...(truncated)"
                : codeContext;

        if (limitedContext == null) limitedContext = "";

        CompletableFuture<String> future = providerManager.sendChatCompletion(
                provider, SYSTEM_PROMPT, userMessage, limitedContext);

        future.thenAccept(response -> {
            new Handler(Looper.getMainLooper()).post(() -> listener.onResponse(response));
        }).exceptionally(e -> {
            new Handler(Looper.getMainLooper()).post(() -> listener.onError(e.getMessage()));
            return null;
        });
    }
}
