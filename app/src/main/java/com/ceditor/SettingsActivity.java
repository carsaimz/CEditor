package com.ceditor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.ceditor.ai.AIProviderManager;
import com.ceditor.ai.AIProvidersActivity;

import java.util.List;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner languageSpinner;
    private Switch darkModeSwitch;
    private LinearLayout aiProvidersSection;
    private TextView noProvidersText;
    private LinearLayout providersList;

    private SharedPreferences prefs;
    private AIProviderManager aiProviderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply current language
        applyLanguage();

        setContentView(R.layout.settings);

        initViews();
        setupToolbar();
        setupLanguage();
        setupTheme();
        setupAiProviders();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        languageSpinner = findViewById(R.id.language_spinner);
        darkModeSwitch = findViewById(R.id.dark_mode_switch);
        aiProvidersSection = findViewById(R.id.ai_providers_section);
        noProvidersText = findViewById(R.id.no_providers_text);
        providersList = findViewById(R.id.providers_list);

        prefs = getSharedPreferences("data", MODE_PRIVATE);
        aiProviderManager = new AIProviderManager(this);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(R.string.settings);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void setupLanguage() {
        String[] languages = {getString(R.string.english), getString(R.string.portuguese)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        // Set current language
        String currentLang = prefs.getString("app_lang", "en");
        if (currentLang.equals("pt")) {
            languageSpinner.setSelection(1);
        }

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String lang = position == 0 ? "en" : "pt";
                prefs.edit().putString("app_lang", lang).apply();
                applyLanguage();
                recreate(); // Restart activity to apply new language
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupTheme() {
        boolean isDark = prefs.getBoolean("dark_mode", true);
        darkModeSwitch.setChecked(isDark);

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("dark_mode", isChecked).apply();
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }

    private void setupAiProviders() {
        // Add AI providers button
        findViewById(R.id.btn_manage_providers).setOnClickListener(v -> {
            Intent intent = new Intent(this, AIProvidersActivity.class);
            startActivity(intent);
        });

        refreshProvidersList();
    }

    private void refreshProvidersList() {
        providersList.removeAllViews();

        List<AIProviderManager.ProviderInfo> activeProviders = aiProviderManager.getActiveProviderInfos();

        if (activeProviders.isEmpty()) {
            noProvidersText.setVisibility(View.VISIBLE);
        } else {
            noProvidersText.setVisibility(View.GONE);

            for (AIProviderManager.ProviderInfo info : activeProviders) {
                LinearLayout itemLayout = new LinearLayout(this);
                itemLayout.setOrientation(LinearLayout.HORIZONTAL);
                itemLayout.setPadding(16, 12, 16, 12);
                itemLayout.setGravity(android.view.Gravity.CENTER_VERTICAL);

                TextView providerName = new TextView(this);
                providerName.setText(info.name);
                providerName.setTextSize(14);
                providerName.setTextColor(0xFFFFFFFF);
                providerName.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                TextView statusText = new TextView(this);
                statusText.setText(R.string.active);
                statusText.setTextSize(12);
                statusText.setTextColor(0xFF4CAF50);

                itemLayout.addView(providerName);
                providersList.addView(itemLayout);
            }
        }
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

    @Override
    protected void onResume() {
        super.onResume();
        refreshProvidersList();
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
