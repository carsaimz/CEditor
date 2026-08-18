package com.ceditor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST = 1001;

    private TextView grantButton;
    private TextView statusText;
    private ImageView statusIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission);

        grantButton = findViewById(R.id.grand_permission_bttn);
        statusText = findViewById(R.id.textview3);
        statusIcon = findViewById(R.id.imageview3);

        // Check if permission already granted
        if (hasPermission()) {
            showGranted();
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        // Set up grant button
        grantButton.setOnClickListener(v -> requestPermission());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Re-check permission when returning (e.g., after user grants in settings)
        if (hasPermission()) {
            showGranted();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11+
            return Environment.isExternalStorageManager() ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        } else {
            // Android 10 and below
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11+ - request all files access
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            } catch (Exception e) {
                // Fallback
                try {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    startActivity(intent);
                } catch (Exception e2) {
                    Toast.makeText(this, "Please grant storage permission in Settings", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            // Android 10 and below - request runtime permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    PERMISSION_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showGranted();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                showDenied();
            }
        }
    }

    private void showGranted() {
        statusText.setText(R.string.permission_granted);
        statusText.setTextColor(0xFF4CAF50); // Green
        statusIcon.setImageResource(R.drawable.content_check);
        grantButton.setVisibility(View.GONE);
    }

    private void showDenied() {
        statusText.setText(R.string.permission_denied);
        statusText.setTextColor(0xFFF44336); // Red
        statusIcon.setImageResource(R.drawable.content_dismiss);
    }
}
