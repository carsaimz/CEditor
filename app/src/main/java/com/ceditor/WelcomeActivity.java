package com.ceditor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

/**
 * WelcomeActivity - First launch screen inspired by Acode/VSCode.
 * Shows app introduction and quick actions. Only shown once.
 */
public class WelcomeActivity extends AppCompatActivity {

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

        setContentView(R.layout.welcome);

        // Check if welcome was already shown
        if (data.getBoolean("welcome_shown", false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setupViews();
    }

    private void setupViews() {
        SharedPreferences data = getSharedPreferences("ceditor_prefs", MODE_PRIVATE);
        String lang = data.getString("app_lang", "en");

        TextView introText = findViewById(R.id.welcome_intro);
        TextView tip1Desc = findViewById(R.id.tip1_desc);
        TextView tip2Desc = findViewById(R.id.tip2_desc);
        TextView tip3Desc = findViewById(R.id.tip3_desc);
        TextView tip4Desc = findViewById(R.id.tip4_desc);

        if (lang.equals("pt")) {
            introText.setText(R.string.welcome_intro_pt);
            tip1Desc.setText(R.string.welcome_tip1_desc_pt);
            tip2Desc.setText(R.string.welcome_tip2_desc_pt);
            tip3Desc.setText(R.string.welcome_tip3_desc_pt);
            tip4Desc.setText(R.string.welcome_tip4_desc_pt);
        } else {
            introText.setText(R.string.welcome_intro_en);
            tip1Desc.setText(R.string.welcome_tip1_desc_en);
            tip2Desc.setText(R.string.welcome_tip2_desc_en);
            tip3Desc.setText(R.string.welcome_tip3_desc_en);
            tip4Desc.setText(R.string.welcome_tip4_desc_en);
        }

        // Start Browsing button
        Button btnStart = findViewById(R.id.btn_start_browsing);
        btnStart.setOnClickListener(v -> {
            data.edit().putBoolean("welcome_shown", true).apply();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("navigate_to", Environment.getExternalStorageDirectory().getAbsolutePath());
            startActivity(intent);
            finish();
        });

        // Open Terminal button
        Button btnTerminal = findViewById(R.id.btn_terminal);
        btnTerminal.setOnClickListener(v -> {
            data.edit().putBoolean("welcome_shown", true).apply();
            startActivity(new Intent(this, TerminalActivity.class));
            finish();
        });

        // Settings button
        Button btnSettings = findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(v -> {
            data.edit().putBoolean("welcome_shown", true).apply();
            startActivity(new Intent(this, SettingsActivity.class));
            finish();
        });
    }
}
