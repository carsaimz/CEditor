package com.ceditor.ai;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.ceditor.R;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class AIProvidersActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout providersContainer;
    private AIProviderManager providerManager;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_ai);

        toolbar = findViewById(R.id.toolbar);
        providersContainer = findViewById(R.id.providers_container);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(R.string.ai_providers_title);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        prefs = getSharedPreferences("ai_providers", MODE_PRIVATE);
        providerManager = new AIProviderManager(this);

        setupProviders();
    }

    private void setupProviders() {
        List<AIProviderManager.ProviderInfo> providers = providerManager.getAvailableProviders();

        for (AIProviderManager.ProviderInfo info : providers) {
            addProviderView(info);
        }
    }

    private void addProviderView(AIProviderManager.ProviderInfo info) {
        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setOrientation(LinearLayout.VERTICAL);
        itemLayout.setPadding(16, 12, 16, 12);
        itemLayout.setBackground(new android.graphics.drawable.ColorDrawable(0xFF333333));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 8;
        itemLayout.setLayoutParams(params);

        // Header row
        LinearLayout headerRow = new LinearLayout(this);
        headerRow.setOrientation(LinearLayout.HORIZONTAL);
        headerRow.setGravity(android.view.Gravity.CENTER_VERTICAL);

        TextView nameText = new TextView(this);
        nameText.setText(info.name);
        nameText.setTextSize(14);
        nameText.setTextColor(0xFFFFFFFF);
        nameText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        Switch enableSwitch = new Switch(this);
        boolean isEnabled = providerManager.isEnabled(info.id);
        enableSwitch.setChecked(isEnabled);
        enableSwitch.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        headerRow.addView(nameText);
        headerRow.addView(enableSwitch);
        itemLayout.addView(headerRow);

        // Description
        TextView descText = new TextView(this);
        descText.setText(info.description);
        descText.setTextSize(12);
        descText.setTextColor(0xFF888888);
        itemLayout.addView(descText);

        // API Key button
        if (!info.id.equals(AIProviderManager.PROVIDER_OLLAMA_LOCAL)) {
            TextView keyButton = new TextView(this);
            keyButton.setText(R.string.ai_api_key_hint);
            keyButton.setTextSize(13);
            keyButton.setTextColor(0xFF64B5F6);
            keyButton.setPadding(0, 8, 0, 0);
            keyButton.setOnClickListener(v -> showApiKeyDialog(info.id));
            itemLayout.addView(keyButton);
        }

        // Model/URL button for some providers
        if (info.id.equals(AIProviderManager.PROVIDER_OLLAMA_LOCAL)) {
            TextView modelButton = new TextView(this);
            modelButton.setText(R.string.ai_model_hint);
            modelButton.setTextSize(13);
            modelButton.setTextColor(0xFF64B5F6);
            modelButton.setPadding(0, 8, 0, 0);
            modelButton.setOnClickListener(v -> showModelDialog(info.id));
            itemLayout.addView(modelButton);
        }

        // Enable/disable handler
        enableSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            providerManager.setEnabled(info.id, isChecked);
            Toast.makeText(this,
                    isChecked ? info.name + " enabled" : info.name + " disabled",
                    Toast.LENGTH_SHORT).show();
        });

        providersContainer.addView(itemLayout);
    }

    private void showApiKeyDialog(String providerId) {
        EditText editText = new EditText(this);
        editText.setHint("API Key");
        editText.setPadding(48, 24, 48, 24);
        editText.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);

        // Show existing key (masked)
        String existingKey = providerManager.getApiKey(providerId);
        if (!existingKey.isEmpty()) {
            editText.setText(existingKey);
        }

        new AlertDialog.Builder(this)
                .setTitle("API Key")
                .setView(editText)
                .setPositiveButton("Save", (dialog, which) -> {
                    String key = editText.getText().toString().trim();
                    providerManager.setApiKey(providerId, key);
                    Toast.makeText(this, "API key saved", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showModelDialog(String providerId) {
        EditText editText = new EditText(this);
        editText.setHint("Model name (e.g., llama2)");
        editText.setPadding(48, 24, 48, 24);

        String existingModel = providerManager.getModel(providerId);
        if (!existingModel.isEmpty()) {
            editText.setText(existingModel);
        }

        new AlertDialog.Builder(this)
                .setTitle("Model")
                .setView(editText)
                .setPositiveButton("Save", (dialog, which) -> {
                    String model = editText.getText().toString().trim();
                    providerManager.setModel(providerId, model);
                    Toast.makeText(this, "Model saved", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
