package com.ceditor;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.ceditor.ai.AIProviderManager;

import java.util.List;
import java.util.Locale;

/**
 * SettingsActivity - App settings with language, theme, and AI provider management.
 * Fixed: no flickering, theme applied once at startup.
 */
public class SettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton btnBack;
    private Spinner languageSpinner;
    private Spinner themeSpinner;
    private Button btnAiProviders;
    private TextView tvAiStatus;
    private AIProviderManager aiProviderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply saved language preference BEFORE setContentView (prevents flicker)
        SharedPreferences prefs = getSharedPreferences("ceditor_prefs", MODE_PRIVATE);
        String langCode = prefs.getString("app_lang", "en");
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources res = getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());

        setContentView(R.layout.settings);

        initViews();
        setupToolbar();
        setupLanguageSpinner();
        setupThemeSpinner();
        setupAiSection();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        btnBack = findViewById(R.id.btn_back);
        languageSpinner = findViewById(R.id.language_spinner);
        themeSpinner = findViewById(R.id.theme_spinner);
        btnAiProviders = findViewById(R.id.btn_ai_providers);
        tvAiStatus = findViewById(R.id.tv_ai_status);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle(R.string.settings);
        }
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> onBackPressed());
        }
    }

    private void setupLanguageSpinner() {
        String[] languages = {getString(R.string.english), getString(R.string.portuguese)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        SharedPreferences prefs = getSharedPreferences("ceditor_prefs", MODE_PRIVATE);
        String currentLang = prefs.getString("app_lang", "en");
        languageSpinner.setSelection(currentLang.equals("pt") ? 1 : 0);

        languageSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view,
                                       int position, long id) {
                String newLang = position == 0 ? "en" : "pt";
                if (!currentLang.equals(newLang)) {
                    prefs.edit().putString("app_lang", newLang).apply();
                    recreate();
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });
    }

    private void setupThemeSpinner() {
        String[] themes = {
                getString(R.string.theme_light),
                getString(R.string.theme_dark)
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, themes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinner.setAdapter(adapter);

        SharedPreferences prefs = getSharedPreferences("ceditor_prefs", MODE_PRIVATE);
        boolean isDark = prefs.getBoolean("dark_mode", false);
        themeSpinner.setSelection(isDark ? 1 : 0);

        themeSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view,
                                       int position, long id) {
                boolean newDark = position == 1;
                if (isDark != newDark) {
                    prefs.edit().putBoolean("dark_mode", newDark).apply();
                    AppCompatDelegate.setDefaultNightMode(
                            newDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });
    }

    private void setupAiSection() {
        aiProviderManager = new AIProviderManager(this);
        updateAiStatus();

        if (btnAiProviders != null) {
            btnAiProviders.setOnClickListener(v -> {
                startActivity(new android.content.Intent(this,
                        com.ceditor.ai.AIProvidersActivity.class));
            });
        }
    }

    private void updateAiStatus() {
        if (tvAiStatus != null) {
            List<String> active = aiProviderManager.getActiveProviders();
            if (active.isEmpty()) {
                tvAiStatus.setText(R.string.ai_no_providers);
            } else {
                tvAiStatus.setText(getString(R.string.active) + ": " + active.size() +
                        " (" + String.join(", ", active) + ")");
            }
        }
    }
}
