package com.ceditor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * MainActivity - File manager with RecyclerView + DrawerLayout.
 * Acode/VSCode-inspired design with light theme.
 */
public class MainActivity extends AppCompatActivity implements FileAdapter.OnFileClickListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TextView pathText;
    private RecyclerView recyclerView;
    private LinearLayout emptyState;
    private TextView emptyText;
    private View fab;
    private ProgressBar progressBar;
    private List<File> fileList = new ArrayList<>();
    private FileAdapter fileAdapter;
    private File currentDir;
    private File clipboardFile = null;
    private boolean clipboardCut = false;
    private int sortOrder = 0;
    private boolean showHidden = false;

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

        setContentView(R.layout.main);
        initViews();
        setupToolbar();
        setupDrawer();
        setupRecyclerView();
        setupFab();
        handleIntent();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("navigate_to")) {
            String targetPath = intent.getStringExtra("navigate_to");
            File targetDir = new File(targetPath);
            if (targetDir.isDirectory()) {
                navigateTo(targetDir);
            } else {
                navigateTo(Environment.getExternalStorageDirectory());
            }
        } else if (currentDir == null) {
            navigateTo(Environment.getExternalStorageDirectory());
        }
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        pathText = findViewById(R.id.path_text);
        recyclerView = findViewById(R.id.recycler_view);
        emptyState = findViewById(R.id.empty_state);
        emptyText = findViewById(R.id.empty_text);
        fab = findViewById(R.id.fab);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("CEditor");
        }
        toolbar.setNavigationOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void setupDrawer() {
        // Drawer items
        View navInternal = findViewById(R.id.nav_internal);
        View drawerHome = findViewById(R.id.nav_home);
        View drawerDocuments = findViewById(R.id.nav_documents);
        View drawerDownloads = findViewById(R.id.nav_downloads);
        View drawerPictures = findViewById(R.id.nav_pictures);
        View drawerMusic = findViewById(R.id.nav_music);
        View drawerMovies = findViewById(R.id.nav_movies);
        View drawerSettings = findViewById(R.id.nav_settings);

        if (navInternal != null) navInternal.setOnClickListener(v -> {
            navigateTo(Environment.getExternalStorageDirectory());
            drawerLayout.closeDrawer(GravityCompat.START);
        });
        if (drawerHome != null) drawerHome.setOnClickListener(v -> {
            navigateTo(Environment.getExternalStorageDirectory());
            drawerLayout.closeDrawer(GravityCompat.START);
        });
        if (drawerDocuments != null) drawerDocuments.setOnClickListener(v -> {
            navigateTo(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
            drawerLayout.closeDrawer(GravityCompat.START);
        });
        if (drawerDownloads != null) drawerDownloads.setOnClickListener(v -> {
            navigateTo(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
            drawerLayout.closeDrawer(GravityCompat.START);
        });
        if (drawerPictures != null) drawerPictures.setOnClickListener(v -> {
            navigateTo(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
            drawerLayout.closeDrawer(GravityCompat.START);
        });
        if (drawerMusic != null) drawerMusic.setOnClickListener(v -> {
            navigateTo(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
            drawerLayout.closeDrawer(GravityCompat.START);
        });
        if (drawerMovies != null) drawerMovies.setOnClickListener(v -> {
            navigateTo(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES));
            drawerLayout.closeDrawer(GravityCompat.START);
        });
        if (drawerSettings != null) drawerSettings.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
            drawerLayout.closeDrawer(GravityCompat.START);
        });
    }

    private void setupRecyclerView() {
        fileAdapter = new FileAdapter(this, fileList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(fileAdapter);
    }

    private void setupFab() {
        fab.setOnClickListener(v -> showNewFileOrFolderDialog());
    }

    public void navigateTo(File directory) {
        if (!directory.isDirectory()) return;
        currentDir = directory;
        updatePathBar();
        saveCurrentPath();
        refreshFiles();
    }

    private void updatePathBar() {
        if (currentDir != null) {
            pathText.setText(currentDir.getAbsolutePath());
        }
    }

    private void saveCurrentPath() {
        SharedPreferences data = getSharedPreferences("ceditor_prefs", MODE_PRIVATE);
        data.edit().putString("last_path", currentDir != null ? currentDir.getAbsolutePath() : "").apply();
    }

    private String getSavedPath() {
        SharedPreferences data = getSharedPreferences("ceditor_prefs", MODE_PRIVATE);
        return data.getString("last_path", null);
    }

    public void refreshFiles() {
        fileList.clear();
        if (currentDir != null && currentDir.isDirectory()) {
            File[] files = currentDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!showHidden && file.getName().startsWith(".")) continue;
                    fileList.add(file);
                }
            }
        }
        sortFiles();
        fileAdapter.notifyDataSetChanged();
        updateEmptyState();
    }

    private void sortFiles() {
        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File a, File b) {
                if (a.isDirectory() != b.isDirectory()) {
                    return a.isDirectory() ? -1 : 1;
                }
                return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
            }
        });
    }

    private void updateEmptyState() {
        if (fileList.isEmpty()) {
            emptyState.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyState.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void showNewFileOrFolderDialog() {
        new AlertDialog.Builder(this)
                .setTitle("New")
                .setItems(new String[]{"File", "Folder"}, (dialog, which) -> {
                    if (which == 0) showNewFileDialog();
                    else showNewFolderDialog();
                })
                .show();
    }

    private void showNewFileDialog() {
        EditText input = new EditText(this);
        input.setHint("filename.txt");
        input.setPadding(48, 24, 48, 24);
        new AlertDialog.Builder(this)
                .setTitle("New File")
                .setView(input)
                .setPositiveButton("Create", (dialog, which) -> {
                    String name = input.getText().toString().trim();
                    if (!name.isEmpty() && currentDir != null) {
                        File newFile = new File(currentDir, name);
                        try {
                            if (newFile.createNewFile()) {
                                refreshFiles();
                                openFile(newFile);
                            } else {
                                Toast.makeText(this, "File already exists", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showNewFolderDialog() {
        EditText input = new EditText(this);
        input.setHint("folder_name");
        input.setPadding(48, 24, 48, 24);
        new AlertDialog.Builder(this)
                .setTitle("New Folder")
                .setView(input)
                .setPositiveButton("Create", (dialog, which) -> {
                    String name = input.getText().toString().trim();
                    if (!name.isEmpty() && currentDir != null) {
                        File newDir = new File(currentDir, name);
                        if (newDir.mkdirs()) {
                            refreshFiles();
                        } else {
                            Toast.makeText(this, "Folder already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void openFile(File file) {
        Intent intent = new Intent(this, EditorActivity.class);
        intent.putExtra("file_path", file.getAbsolutePath());
        startActivity(intent);
    }

    private void copyFileToClipboard(File file) {
        clipboardFile = file;
        clipboardCut = false;
        Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
    }

    private void cutFileToClipboard(File file) {
        clipboardFile = file;
        clipboardCut = true;
        Toast.makeText(this, "Cut", Toast.LENGTH_SHORT).show();
    }

    public void pasteFiles() {
        if (clipboardFile == null || currentDir == null) return;
        File target = new File(currentDir, clipboardFile.getName());
        try {
            if (clipboardFile.isDirectory()) {
                copyDirectory(clipboardFile, target);
            } else {
                copyFileContents(clipboardFile, target);
            }
            if (clipboardCut) {
                deleteFileRecursively(clipboardFile);
                clipboardFile = null;
                clipboardCut = false;
            }
            refreshFiles();
        } catch (IOException e) {
            Toast.makeText(this, "Paste failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void copyFileContents(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);
        byte[] buf = new byte[8192];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

    private void copyDirectory(File src, File dst) throws IOException {
        dst.mkdirs();
        File[] files = src.listFiles();
        if (files != null) {
            for (File child : files) {
                File childDst = new File(dst, child.getName());
                if (child.isDirectory()) {
                    copyDirectory(child, childDst);
                } else {
                    copyFileContents(child, childDst);
                }
            }
        }
    }

    private void deleteSelectedFile(File file) {
        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Delete " + file.getName() + "?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    deleteFileRecursively(file);
                    refreshFiles();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void deleteFileRecursively(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File child : files) {
                    deleteFileRecursively(child);
                }
            }
        }
        file.delete();
    }

    private void shareFile(File file) {
        try {
            Uri uri = androidx.core.content.FileProvider.getUriForFile(
                    this, getPackageName() + ".provider", file);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("*/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(shareIntent, "Share"));
        } catch (Exception e) {
            Toast.makeText(this, "Share failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void renameSelectedFile(File file) {
        EditText input = new EditText(this);
        input.setText(file.getName());
        input.setPadding(48, 24, 48, 24);
        new AlertDialog.Builder(this)
                .setTitle("Rename")
                .setView(input)
                .setPositiveButton("Rename", (dialog, which) -> {
                    String newName = input.getText().toString().trim();
                    if (!newName.isEmpty()) {
                        File newFile = new File(file.getParentFile(), newName);
                        if (file.renameTo(newFile)) {
                            refreshFiles();
                        } else {
                            Toast.makeText(this, "Rename failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort) {
            sortOrder = (sortOrder + 1) % 3;
            sortFiles();
            fileAdapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.action_hidden) {
            showHidden = !showHidden;
            refreshFiles();
            return true;
        } else if (id == R.id.action_paste) {
            pasteFiles();
            return true;
        } else if (id == R.id.action_terminal) {
            startActivity(new Intent(this, TerminalActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (currentDir != null && !currentDir.equals(Environment.getExternalStorageDirectory())) {
            File parent = currentDir.getParentFile();
            if (parent != null) {
                navigateTo(parent);
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    // FileAdapter.OnFileClickListener implementation
    @Override
    public void onItemClick(int position) {
        File file = fileList.get(position);
        if (file.isDirectory()) {
            navigateTo(file);
        } else {
            openFile(file);
        }
    }

    @Override
    public void onItemLongClick(int position) {
        File file = fileList.get(position);
        showFileContextMenu(file);
    }

    private void showFileContextMenu(File file) {
        android.widget.PopupMenu popup = new android.widget.PopupMenu(this, recyclerView);
        popup.getMenu().add(android.view.Menu.NONE, 1, 0, "Open");
        popup.getMenu().add(android.view.Menu.NONE, 2, 0, "Rename");
        popup.getMenu().add(android.view.Menu.NONE, 3, 0, "Delete");
        popup.getMenu().add(android.view.Menu.NONE, 4, 0, "Copy");
        popup.getMenu().add(android.view.Menu.NONE, 5, 0, "Cut");
        popup.getMenu().add(android.view.Menu.NONE, 6, 0, "Share");
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case 1: openFile(file); return true;
                case 2: renameSelectedFile(file); return true;
                case 3: deleteSelectedFile(file); return true;
                case 4: copyFileToClipboard(file); return true;
                case 5: cutFileToClipboard(file); return true;
                case 6: shareFile(file); return true;
                default: return false;
            }
        });
        popup.show();
    }
}
