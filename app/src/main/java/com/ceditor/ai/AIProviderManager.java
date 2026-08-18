package com.ceditor.ai;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * AI Provider Manager - Manages multiple AI providers (Gemini, OpenAI, Anthropic, etc.)
 * Supports free-tier, paid, and premium providers.
 */
public class AIProviderManager {

    private static final String TAG = "AIProviderManager";
    private static final String PREFS_NAME = "ai_providers";

    // Provider constants
    public static final String PROVIDER_GEMINI_FREE = "gemini_free";
    public static final String PROVIDER_GEMINI_PRO = "gemini_pro";
    public static final String PROVIDER_OPENAI_FREE = "openai_free";
    public static final String PROVIDER_OPENAI_PREMIUM = "openai_premium";
    public static final String PROVIDER_ANTHROPIC = "anthropic";
    public static final String PROVIDER_MISTRAL_FREE = "mistral_free";
    public static final String PROVIDER_GROQ_FREE = "groq_free";
    public static final String PROVIDER_OLLAMA_LOCAL = "ollama_local";

    private Context context;
    private SharedPreferences prefs;
    private OkHttpClient httpClient;
    private ExecutorService executor;

    public AIProviderManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                .build();
        this.executor = Executors.newSingleThreadExecutor();
    }

    // Provider configuration methods
    public void setApiKey(String provider, String apiKey) {
        prefs.edit().putString("key_" + provider, apiKey).apply();
    }

    public String getApiKey(String provider) {
        return prefs.getString("key_" + provider, "");
    }

    public void setEnabled(String provider, boolean enabled) {
        prefs.edit().putBoolean("enabled_" + provider, enabled).apply();
    }

    public boolean isEnabled(String provider) {
        return prefs.getBoolean("enabled_" + provider, false);
    }

    public void setBaseUrl(String provider, String baseUrl) {
        prefs.edit().putString("baseurl_" + provider, baseUrl).apply();
    }

    public String getBaseUrl(String provider) {
        String custom = prefs.getString("baseurl_" + provider, "");
        if (!custom.isEmpty()) return custom;
        return getDefaultBaseUrl(provider);
    }

    public void setModel(String provider, String model) {
        prefs.edit().putString("model_" + provider, model).apply();
    }

    public String getModel(String provider) {
        String custom = prefs.getString("model_" + provider, "");
        if (!custom.isEmpty()) return custom;
        return getDefaultModel(provider);
    }

    private String getDefaultBaseUrl(String provider) {
        switch (provider) {
            case PROVIDER_GEMINI_FREE:
            case PROVIDER_GEMINI_PRO:
                return "https://generativelanguage.googleapis.com/v1beta";
            case PROVIDER_OPENAI_FREE:
            case PROVIDER_OPENAI_PREMIUM:
                return "https://api.openai.com/v1";
            case PROVIDER_ANTHROPIC:
                return "https://api.anthropic.com/v1";
            case PROVIDER_MISTRAL_FREE:
                return "https://api.mistral.ai/v1";
            case PROVIDER_GROQ_FREE:
                return "https://api.groq.com/openai/v1";
            case PROVIDER_OLLAMA_LOCAL:
                return "http://localhost:11434/api";
            default:
                return "";
        }
    }

    private String getDefaultModel(String provider) {
        switch (provider) {
            case PROVIDER_GEMINI_FREE:
                return "gemini-2.0-flash";
            case PROVIDER_GEMINI_PRO:
                return "gemini-2.0-pro";
            case PROVIDER_OPENAI_FREE:
                return "gpt-3.5-turbo";
            case PROVIDER_OPENAI_PREMIUM:
                return "gpt-4o";
            case PROVIDER_ANTHROPIC:
                return "claude-3-sonnet-20240229";
            case PROVIDER_MISTRAL_FREE:
                return "mistral-small-latest";
            case PROVIDER_GROQ_FREE:
                return "llama-3.3-70b-versatile";
            case PROVIDER_OLLAMA_LOCAL:
                return "llama3";
            default:
                return "";
        }
    }

    /**
     * Get list of available providers with their metadata
     */
    public List<ProviderInfo> getAvailableProviders() {
        List<ProviderInfo> providers = new ArrayList<>();
        providers.add(new ProviderInfo(PROVIDER_GEMINI_FREE, "Google Gemini (Free)", "Free tier - 15 RPM", true));
        providers.add(new ProviderInfo(PROVIDER_GEMINI_PRO, "Google Gemini Pro", "Paid - Premium", false));
        providers.add(new ProviderInfo(PROVIDER_OPENAI_FREE, "OpenAI GPT-3.5 (Free tier)", "Free trial credits", true));
        providers.add(new ProviderInfo(PROVIDER_OPENAI_PREMIUM, "OpenAI GPT-4o (Premium)", "Paid subscription", false));
        providers.add(new ProviderInfo(PROVIDER_ANTHROPIC, "Anthropic Claude", "Paid - Free tier available", true));
        providers.add(new ProviderInfo(PROVIDER_MISTRAL_FREE, "Mistral AI (Free)", "Free tier - 30 RPM", true));
        providers.add(new ProviderInfo(PROVIDER_GROQ_FREE, "Groq (Free)", "Free tier - Fast inference", true));
        providers.add(new ProviderInfo(PROVIDER_OLLAMA_LOCAL, "Ollama (Local)", "Self-hosted - No API key needed", true));
        return providers;
    }

    /**
     * Get active (enabled + has key) providers
     */
    public List<String> getActiveProviders() {
        List<String> active = new ArrayList<>();
        for (ProviderInfo info : getAvailableProviders()) {
            if (isEnabled(info.id)) {
                String key = getApiKey(info.id);
                // Ollama doesn't need a key
                if (!key.isEmpty() || info.id.equals(PROVIDER_OLLAMA_LOCAL)) {
                    active.add(info.id);
                }
            }
        }
        return active;
    }

    /**
     * Send a chat completion request to the specified provider
     */
    public CompletableFuture<String> sendChatCompletion(String provider, String systemPrompt, String userMessage, String codeContext) {
        CompletableFuture<String> future = new CompletableFuture<>();

        executor.submit(() -> {
            try {
                String response;
                switch (provider) {
                    case PROVIDER_GEMINI_FREE:
                    case PROVIDER_GEMINI_PRO:
                        response = sendGeminiRequest(provider, systemPrompt, userMessage, codeContext);
                        break;
                    case PROVIDER_OPENAI_FREE:
                    case PROVIDER_OPENAI_PREMIUM:
                    case PROVIDER_MISTRAL_FREE:
                    case PROVIDER_GROQ_FREE:
                        response = sendOpenAICompatibleRequest(provider, systemPrompt, userMessage, codeContext);
                        break;
                    case PROVIDER_ANTHROPIC:
                        response = sendAnthropicRequest(provider, systemPrompt, userMessage, codeContext);
                        break;
                    case PROVIDER_OLLAMA_LOCAL:
                        response = sendOllamaRequest(provider, userMessage, codeContext);
                        break;
                    default:
                        future.completeExceptionally(new IllegalArgumentException("Unknown provider: " + provider));
                        return;
                }
                future.complete(response);
            } catch (Exception e) {
                Log.e(TAG, "Error sending to " + provider + ": " + e.getMessage());
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    private String sendGeminiRequest(String provider, String systemPrompt, String userMessage, String codeContext) throws Exception {
        String apiKey = getApiKey(provider);
        String model = getModel(provider);
        String url = getBaseUrl(provider) + "/models/" + model + ":generateContent?key=" + apiKey;

        JSONObject content = new JSONObject();
        JSONArray parts = new JSONArray();

        // Build full prompt
        StringBuilder fullPrompt = new StringBuilder();
        if (!systemPrompt.isEmpty()) {
            fullPrompt.append(systemPrompt).append("\n\n");
        }
        if (!codeContext.isEmpty()) {
            fullPrompt.append("Context (current code):\n```").append(codeContext).append("```\n\n");
        }
        fullPrompt.append(userMessage);

        parts.put(new JSONObject().put("text", fullPrompt.toString()));
        content.put("parts", parts);

        JSONArray contents = new JSONArray();
        contents.put(new JSONObject().put("role", "user").put("parts", new JSONArray().put(content)));

        JSONObject requestBody = new JSONObject();
        requestBody.put("contents", contents);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Gemini API error: " + response.code() + " - " + response.body().string());
            }
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            JSONArray candidates = json.getJSONArray("candidates");
            if (candidates.length() > 0) {
                JSONObject candidate = candidates.getJSONObject(0);
                JSONObject output = candidate.getJSONObject("content");
                JSONArray outputParts = output.getJSONArray("parts");
                if (outputParts.length() > 0) {
                    return outputParts.getJSONObject(0).getString("text");
                }
            }
            return "No response generated.";
        }
    }

    private String sendOpenAICompatibleRequest(String provider, String systemPrompt, String userMessage, String codeContext) throws Exception {
        String apiKey = getApiKey(provider);
        String model = getModel(provider);
        String url = getBaseUrl(provider) + "/chat/completions";

        JSONArray messages = new JSONArray();

        if (!systemPrompt.isEmpty()) {
            messages.put(new JSONObject().put("role", "system").put("content", systemPrompt));
        }

        if (!codeContext.isEmpty()) {
            messages.put(new JSONObject().put("role", "system")
                    .put("content", "Current code context:\n```" + codeContext + "```"));
        }

        messages.put(new JSONObject().put("role", "user").put("content", userMessage));

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", model);
        requestBody.put("messages", messages);
        requestBody.put("max_tokens", 2048);
        requestBody.put("temperature", 0.7);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody.toString());
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json");

        Request request = requestBuilder.build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("API error: " + response.code() + " - " + response.body().string());
            }
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            JSONArray choices = json.getJSONArray("choices");
            if (choices.length() > 0) {
                JSONObject message = choices.getJSONObject(0).getJSONObject("message");
                return message.getString("content");
            }
            return "No response generated.";
        }
    }

    private String sendAnthropicRequest(String provider, String systemPrompt, String userMessage, String codeContext) throws Exception {
        String apiKey = getApiKey(provider);
        String model = getModel(provider);
        String url = getBaseUrl(provider) + "/messages";

        String fullMessage = userMessage;
        if (!codeContext.isEmpty()) {
            fullMessage = "Current code context:\n```\n" + codeContext + "\n```\n\n" + userMessage;
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", model);
        requestBody.put("max_tokens", 2048);

        if (!systemPrompt.isEmpty()) {
            requestBody.put("system", systemPrompt);
        }

        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "user").put("content", fullMessage));
        requestBody.put("messages", messages);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("x-api-key", apiKey)
                .addHeader("anthropic-version", "2023-06-01")
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Anthropic API error: " + response.code() + " - " + response.body().string());
            }
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            JSONArray content = json.getJSONArray("content");
            if (content.length() > 0) {
                JSONObject textBlock = content.getJSONObject(0);
                return textBlock.getString("text");
            }
            return "No response generated.";
        }
    }

    private String sendOllamaRequest(String provider, String userMessage, String codeContext) throws Exception {
        String model = getModel(provider);
        String url = getBaseUrl(provider) + "/generate";

        String fullPrompt = userMessage;
        if (!codeContext.isEmpty()) {
            fullPrompt = "Current code:\n```\n" + codeContext + "\n```\n\n" + userMessage;
        }

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", model);
        requestBody.put("prompt", fullPrompt);
        requestBody.put("stream", false);

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Ollama error: " + response.code() + " - " + response.body().string());
            }
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            return json.getString("response");
        }
    }

    /**
     * Check if a provider is properly configured and ready to use
     */
    public boolean isProviderReady(String provider) {
        if (!isEnabled(provider)) return false;
        String key = getApiKey(provider);
        return !key.isEmpty() || provider.equals(PROVIDER_OLLAMA_LOCAL);
    }

    /**
     * Get a formatted list of provider info for UI display
     */
    public static class ProviderInfo {
        public String id;
        public String name;
        public String description;
        public boolean isFree;

        public ProviderInfo(String id, String name, String description, boolean isFree) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.isFree = isFree;
        }
    }
}
