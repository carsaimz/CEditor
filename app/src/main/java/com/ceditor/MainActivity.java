package com.ceditor;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST = 100;
    private static final String PREFS = "ceditor_prefs";

    // Views
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TextView pathText;
    private RecyclerView recyclerView;
    private LinearLayout emptyState;
    private TextView emptyText;
    private FloatingActionButton fab;

    // Data
    private FileAdapter fileAdapter;
    private List<FileItem> fileList = new ArrayList<>();
    private File currentDir;
    private File clipboardFile;
    private boolean clipboardCut = false;
    private int sortOrder = 0; // 0=name, 1=date, 2=size
    private boolean showHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply language preference
        SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
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
        setupNavigation();

        // Check permissions
        if (hasStoragePermission()) {
            String savedPath = getSavedPath();
            if (savedPath != null && new File(savedPath).isDirectory()) {
                navigateTo(new File(savedPath));
            } else {
                navigateTo(Environment.getExternalStorageDirectory());
            }
        } else {
            navigateTo(Environment.getExternalStorageDirectory());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh list when returning to this activity
        if (currentDir != null && currentDir.isDirectory()) {
            refreshFiles();
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
        findViewById(R.id.nav_internal).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            navigateTo(Environment.getExternalStorageDirectory());
        });

        findViewById(R.id.nav_home).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            navigateTo(Environment.getExternalStorageDirectory());
        });

        findViewById(R.id.nav_downloads).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            navigateTo(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        });

        findViewById(R.id.nav_documents).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            navigateTo(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
        });

        findViewById(R.id.nav_pictures).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            navigateTo(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
        });

        findViewById(R.id.nav_music).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            navigateTo(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC));
        });

        findViewById(R.id.nav_movies).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            navigateTo(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES));
        });

        findViewById(R.id.nav_settings).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, SettingsActivity.class));
        });
    }

    private void setupRecyclerView() {
        fileAdapter = new FileAdapter(this, fileList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(fileAdapter);
    }

    private void setupFab() {
        fab.setOnClickListener(v -> showCreateMenu());
    }

    private void setupNavigation() {
        pathText.setOnClickListener(v -> {
            if (currentDir != null && currentDir.getParentFile() != null) {
                navigateTo(currentDir.getParentFile());
            }
        });

        findViewById(R.id.btn_up).setOnClickListener(v -> {
            if (currentDir != null && currentDir.getParentFile() != null) {
                navigateTo(currentDir.getParentFile());
            }
        });
    }

    // Permission helpers
    private boolean hasStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager() ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        } else {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                navigateTo(Environment.getExternalStorageDirectory());
            } else {
                Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_LONG).show();
            }
        }
    }

    // Navigation
    private void navigateTo(File dir) {
        if (dir == null || !dir.isDirectory()) return;
        currentDir = dir;
        savePath(dir.getAbsolutePath());
        updatePathBar();
        refreshFiles();
    }

    private void updatePathBar() {
        if (currentDir != null) {
            pathText.setText(currentDir.getAbsolutePath());
        }
        if (getSupportActionBar() != null) {
            String name = currentDir.getName();
            if (name.isEmpty()) name = "/";
            getSupportActionBar().setTitle(name);
        }
    }

    private void refreshFiles() {
        fileList.clear();

        if (currentDir == null || !currentDir.isDirectory()) {
            showEmptyState();
            return;
        }

        File[] files = currentDir.listFiles();
        if (files == null) {
            showEmptyState();
            return;
        }

        List<File> dirs = new ArrayList<>();
        List<File> regularFiles = new ArrayList<>();

        for (File file : files) {
            if (!showHidden && file.isHidden()) continue;
            if (file.isDirectory()) {
                dirs.add(file);
            } else {
                regularFiles.add(file);
            }
        }

        Comparator<File> comparator = getComparator();
        Collections.sort(dirs, comparator);
        Collections.sort(regularFiles, comparator);

        for (File dir : dirs) {
            fileList.add(new FileItem(dir, true));
        }
        for (File file : regularFiles) {
            fileList.add(new FileItem(file, false));
        }

        if (fileList.isEmpty()) {
            showEmptyState();
        } else {
            showList();
        }

        fileAdapter.notifyDataSetChanged();
        if (!fileList.isEmpty()) {
            emptyText.setText(getString(R.string.items_count, fileList.size()));
        }
    }

    private Comparator<File> getComparator() {
        switch (sortOrder) {
            case 1: // date
                return (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified());
            case 2: // size
                return (f1, f2) -> Long.compare(f2.length(), f1.length());
            default: // name
                return (f1, f2) -> f1.getName().toLowerCase().compareTo(f2.getName().toLowerCase());
        }
    }

    private void showEmptyState() {
        emptyState.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        emptyText.setText(R.string.no_items);
    }

    private void showList() {
        emptyState.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    // Context menu on toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort) {
            showSortMenu();
            return true;
        } else if (id == R.id.action_hidden) {
            showHidden = !showHidden;
            refreshFiles();
            return true;
        } else if (id == R.id.action_paste) {
            pasteFiles();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSortMenu() {
        PopupMenu popup = new PopupMenu(this, toolbar);
        popup.getMenu().add(Menu.NONE, 0, 0, R.string.sort_by_name);
        popup.getMenu().add(Menu.NONE, 1, 1, R.string.sort_by_date);
        popup.getMenu().add(Menu.NONE, 2, 2, R.string.sort_by_size);
        popup.setOnMenuItemClickListener(item -> {
            sortOrder = item.getItemId();
            refreshFiles();
            return true;
        });
        popup.show();
    }

    private void showCreateMenu() {
        PopupMenu popup = new PopupMenu(this, fab);
        popup.getMenu().add(Menu.NONE, 0, 0, R.string.new_folder);
        popup.getMenu().add(Menu.NONE, 1, 1, R.string.new_file);
        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == 0) {
                showNewFolderDialog();
            } else {
                showNewFileDialog();
            }
            return true;
        });
        popup.show();
    }

    private void showNewFolderDialog() {
        EditText editText = new EditText(this);
        editText.setHint(R.string.folder_name);
        editText.setPadding(48, 24, 48, 24);

        new AlertDialog.Builder(this)
                .setTitle(R.string.new_folder)
                .setView(editText)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    String name = editText.getText().toString().trim();
                    if (!name.isEmpty()) {
                        File newDir = new File(currentDir, name);
                        if (newDir.mkdirs()) {
                            refreshFiles();
                            Toast.makeText(this, getString(R.string.created, name), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, getString(R.string.cannot_create, name), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel2, null)
                .show();
    }

    private void showNewFileDialog() {
        EditText editText = new EditText(this);
        editText.setHint(R.string.file_name_input);
        editText.setPadding(48, 24, 48, 24);

        new AlertDialog.Builder(this)
                .setTitle(R.string.new_file)
                .setView(editText)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    String name = editText.getText().toString().trim();
                    if (!name.isEmpty()) {
                        File newFile = new File(currentDir, name);
                        try {
                            if (newFile.createNewFile()) {
                                refreshFiles();
                                Toast.makeText(this, getString(R.string.created, name), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, getString(R.string.cannot_create, name), Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            Toast.makeText(this, getString(R.string.cannot_create, name), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel2, null)
                .show();
    }

    // File operations
    private void copyFile(File src, File destDir) {
        File dest = new File(destDir, src.getName());
        if (dest.exists()) {
            dest = getUniqueFile(destDir, src.getName());
        }
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
            byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyDirectory(File src, File destDir) {
        File dest = new File(destDir, src.getName());
        if (dest.exists()) {
            dest = getUniqueDir(destDir, src.getName());
        }
        dest.mkdirs();
        File[] files = src.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    copyDirectory(file, dest);
                } else {
                    copyFile(file, dest);
                }
            }
        }
    }

    private File getUniqueFile(File dir, String name) {
        File file = new File(dir, name);
        int counter = 1;
        String baseName = name;
        String extension = "";
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex > 0) {
            baseName = name.substring(0, dotIndex);
            extension = name.substring(dotIndex);
        }
        while (file.exists()) {
            file = new File(dir, baseName + "_" + counter + extension);
            counter++;
        }
        return file;
    }

    private File getUniqueDir(File dir, String name) {
        File file = new File(dir, name);
        int counter = 1;
        while (file.exists()) {
            file = new File(dir, name + "_" + counter);
            counter++;
        }
        return file;
    }

    private void pasteFiles() {
        if (clipboardFile == null) {
            Toast.makeText(this, R.string.no_items, Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle(R.string.paste_here)
                .setMessage(clipboardFile.getName())
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    if (clipboardCut) {
                        File destDir = getUniqueDir(currentDir, clipboardFile.getName());
                        if (clipboardFile.renameTo(destDir)) {
                            clipboardFile = null;
                            clipboardCut = false;
                            refreshFiles();
                            Toast.makeText(this, R.string.pasted, Toast.LENGTH_SHORT).show();
                        } else {
                            if (clipboardFile.isDirectory()) {
                                copyDirectory(clipboardFile, currentDir);
                            } else {
                                copyFile(clipboardFile, currentDir);
                            }
                            deleteRecursive(clipboardFile);
                            clipboardFile = null;
                            clipboardCut = false;
                            refreshFiles();
                            Toast.makeText(this, R.string.pasted, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (clipboardFile.isDirectory()) {
                            copyDirectory(clipboardFile, currentDir);
                        } else {
                            copyFile(clipboardFile, currentDir);
                        }
                        refreshFiles();
                        Toast.makeText(this, R.string.pasted, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.cancel2, null)
                .show();
    }

    private void deleteRecursive(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteRecursive(child);
                }
            }
        }
        file.delete();
    }

    // File item click handler
    public void onFileItemClick(FileItem item) {
        if (item.isDirectory) {
            navigateTo(item.file);
        } else {
            openFile(item.file);
        }
    }

    // File item long click handler
    public void onFileItemLongClick(FileItem item, View view) {
        showFileContextMenu(item, view);
    }

    private void showFileContextMenu(FileItem item, View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        Menu menu = popup.getMenu();

        menu.add(Menu.NONE, 0, 0, R.string.open_with);
        menu.add(Menu.NONE, 1, 1, R.string.edit_with_editor);
        menu.add(Menu.NONE, 2, 2, R.string.rename);
        menu.add(Menu.NONE, 3, 3, R.string.copy);
        menu.add(Menu.NONE, 4, 4, R.string.cut);
        menu.add(Menu.NONE, 5, 5, R.string.file_info);
        menu.add(Menu.NONE, 6, 6, R.string.share);
        menu.add(Menu.NONE, 7, 7, R.string.delete);

        popup.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case 0:
                    openFile(item.file);
                    break;
                case 1:
                    openInEditor(item.file);
                    break;
                case 2:
                    showRenameDialog(item.file);
                    break;
                case 3:
                    clipboardFile = item.file;
                    clipboardCut = false;
                    Toast.makeText(this, R.string.copied, Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    clipboardFile = item.file;
                    clipboardCut = true;
                    Toast.makeText(this, R.string.cut, Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    showFileInfo(item.file);
                    break;
                case 6:
                    shareFile(item.file);
                    break;
                case 7:
                    showDeleteDialog(item.file);
                    break;
            }
            return true;
        });
        popup.show();
    }

    private void openFile(File file) {
        if (isTextFile(file)) {
            openInEditor(file);
            return;
        }

        String mimeType = getMimeType(file);
        try {
            Uri uri = FileProvider.getUriForFile(this,
                    getPackageName() + ".provider", file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, mimeType);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.open_with, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, R.string.error_occurred, Toast.LENGTH_SHORT).show();
        }
    }

    private void openInEditor(File file) {
        Intent intent = new Intent(this, EditorActivity.class);
        intent.putExtra("file_path", file.getAbsolutePath());
        startActivity(intent);
    }

    private void showRenameDialog(File file) {
        EditText editText = new EditText(this);
        editText.setText(file.getName());
        editText.setPadding(48, 24, 48, 24);
        editText.setSelection(file.getName().length());

        new AlertDialog.Builder(this)
                .setTitle(R.string.rename)
                .setView(editText)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    String newName = editText.getText().toString().trim();
                    if (!newName.isEmpty() && !newName.equals(file.getName())) {
                        File newFile = new File(file.getParentFile(), newName);
                        if (file.renameTo(newFile)) {
                            refreshFiles();
                            Toast.makeText(this, R.string.deleted, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, R.string.error_occurred, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel2, null)
                .show();
    }

    private void showFileInfo(File file) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        String info = String.format("Name: %s\nSize: %s\nType: %s\nModified: %s",
                file.getName(),
                formatFileSize(file.length()),
                file.isDirectory() ? "Folder" : getMimeType(file),
                sdf.format(new Date(file.lastModified())));

        new AlertDialog.Builder(this)
                .setTitle(R.string.file_info)
                .setMessage(info)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    private void shareFile(File file) {
        try {
            Uri uri = FileProvider.getUriForFile(this,
                    getPackageName() + ".provider", file);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType(getMimeType(file));
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(intent, getString(R.string.share)));
        } catch (Exception e) {
            Toast.makeText(this, R.string.error_occurred, Toast.LENGTH_SHORT).show();
        }
    }

    private void showDeleteDialog(File file) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete)
                .setMessage(getString(R.string.delete_confirm, file.getName()))
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    deleteRecursive(file);
                    refreshFiles();
                    Toast.makeText(this, R.string.deleted, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    // Utility methods
    private String getMimeType(File file) {
        if (file.isDirectory()) return "inode/directory";
        String extension = "";
        String name = file.getName();
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = name.substring(dotIndex + 1).toLowerCase();
        }
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        return mimeType != null ? mimeType : "application/octet-stream";
    }

    private boolean isTextFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".java") || name.endsWith(".kt") || name.endsWith(".xml") ||
                name.endsWith(".json") || name.endsWith(".txt") || name.endsWith(".md") ||
                name.endsWith(".html") || name.endsWith(".css") || name.endsWith(".js") ||
                name.endsWith(".py") || name.endsWith(".c") || name.endsWith(".cpp") ||
                name.endsWith(".h") || name.endsWith(".gradle") || name.endsWith(".properties") ||
                name.endsWith(".yml") || name.endsWith(".yaml") || name.endsWith(".csv") ||
                name.endsWith(".log") || name.endsWith(".sh") || name.endsWith(".bat") ||
                name.endsWith(".sql") || name.endsWith(".rs") || name.endsWith(".go") ||
                name.endsWith(".swift") || name.endsWith(".ts") || name.endsWith(".jsx") ||
                name.endsWith(".tsx") || name.endsWith(".dart") || name.endsWith(".rb") ||
                name.endsWith(".php") || name.endsWith(".pl") || name.endsWith(".r") ||
                name.endsWith(".makefile") || name.endsWith(".cmake") || name.endsWith(".ini") ||
                name.endsWith(".cfg") || name.endsWith(".conf") || name.endsWith(".env") ||
                name.endsWith(".gitignore") || name.endsWith(".dockerfile") ||
                name.contains("makefile") || name.contains("dockerfile");
    }

    private String formatFileSize(long size) {
        if (size < 1024) return size + " B";
        if (size < 1024 * 1024) return String.format("%.1f KB", size / 1024.0);
        if (size < 1024 * 1024 * 1024) return String.format("%.1f MB", size / (1024.0 * 1024));
        return String.format("%.1f GB", size / (1024.0 * 1024 * 1024));
    }

    // SharedPreferences
    private void savePath(String path) {
        getSharedPreferences(PREFS, MODE_PRIVATE).edit()
                .putString("last_path", path).apply();
    }

    private String getSavedPath() {
        return getSharedPreferences(PREFS, MODE_PRIVATE).getString("last_path", null);
    }

    // FileItem model
    public static class FileItem {
        public File file;
        public boolean isDirectory;

        public FileItem(File file, boolean isDirectory) {
            this.file = file;
            this.isDirectory = isDirectory;
        }
    }

    // RecyclerView Adapter
    public static class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {
        private Context context;
        private List<FileItem> items;

        public FileAdapter(Context context, List<FileItem> items) {
            this.context = context;
            this.items = items;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            FileItem item = items.get(position);
            File file = item.file;

            holder.fileName.setText(file.getName());

            if (item.isDirectory) {
                holder.fileIcon.setImageResource(R.drawable.content_folder);
                holder.fileSize.setText("");

                File[] children = file.listFiles();
                int count = children != null ? children.length : 0;
                holder.fileInfo.setText(context.getString(R.string.items_count, count));
            } else {
                holder.fileIcon.setImageResource(getFileIcon(file));
                holder.fileSize.setText(formatFileSizeStatic(file.length()));
                holder.fileInfo.setText(formatDateStatic(file.lastModified()));
            }

            holder.itemView.setOnClickListener(v -> {
                if (context instanceof MainActivity) {
                    ((MainActivity) context).onFileItemClick(item);
                }
            });

            holder.itemView.setOnLongClickListener(v -> {
                if (context instanceof MainActivity) {
                    ((MainActivity) context).onFileItemLongClick(item, v);
                }
                return true;
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        private int getFileIcon(File file) {
            String name = file.getName().toLowerCase();

            if (name.endsWith(".java")) return R.drawable.text_x_java_icon;
            if (name.endsWith(".py")) return R.drawable.text_x_python_icon;
            if (name.endsWith(".js") || name.endsWith(".ts")) return R.drawable.app_x_javascript_icon;
            if (name.endsWith(".kt")) return R.drawable.text_x_java_icon;
            if (name.endsWith(".xml")) return R.drawable.app_xml_icon;
            if (name.endsWith(".json")) return R.drawable.app_json_icon;
            if (name.endsWith(".html") || name.endsWith(".htm")) return R.drawable.content_web;
            if (name.endsWith(".css")) return R.drawable.text_css_icon;
            if (name.endsWith(".md")) return R.drawable.text_x_markdown_icon;
            if (name.endsWith(".txt")) return R.drawable.content_text_box;
            if (name.endsWith(".pdf")) return R.drawable.app_pdf_icon;
            if (name.endsWith(".apk")) return R.drawable.content_apk_file;
            if (name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") ||
                    name.endsWith(".gif") || name.endsWith(".webp") || name.endsWith(".bmp"))
                return R.drawable.content_image;
            if (name.endsWith(".mp3") || name.endsWith(".wav") || name.endsWith(".ogg") ||
                    name.endsWith(".flac") || name.endsWith(".aac") || name.endsWith(".m4a"))
                return R.drawable.audio_x_generic_icon;
            if (name.endsWith(".mp4") || name.endsWith(".mkv") || name.endsWith(".avi") ||
                    name.endsWith(".mov") || name.endsWith(".webm"))
                return R.drawable.content_video;
            if (name.endsWith(".zip") || name.endsWith(".rar") || name.endsWith(".7z") ||
                    name.endsWith(".tar") || name.endsWith(".gz"))
                return R.drawable.app_x_compress_icon;
            if (name.endsWith(".gradle")) return R.drawable.folderyellowgradle_83823;
            if (name.endsWith(".properties")) return R.drawable.content_settings_cog;
            if (name.endsWith(".yml") || name.endsWith(".yaml")) return R.drawable.app_x_yaml_icon;
            if (name.endsWith(".csv")) return R.drawable.x_office_spreadsheet_icon;
            if (name.endsWith(".log")) return R.drawable.textxlog_92758;
            if (name.endsWith(".sh") || name.endsWith(".bat")) return R.drawable.content_terminal;
            if (name.endsWith(".c") || name.endsWith(".cpp") || name.endsWith(".h"))
                return R.drawable.text_x_generic_icon;
            if (name.endsWith(".rs")) return R.drawable.text_x_rust_icon;
            if (name.endsWith(".rb")) return R.drawable.text_x_ruby_icon;
            if (name.endsWith(".php")) return R.drawable.app_x_php_icon;
            if (name.endsWith(".r")) return R.drawable.text_r_icon;
            if (name.endsWith(".doc") || name.endsWith(".docx"))
                return R.drawable.x_office_document_icon;
            if (name.endsWith(".xls") || name.endsWith(".xlsx"))
                return R.drawable.x_office_spreadsheet_icon;
            if (name.endsWith(".ppt") || name.endsWith(".pptx"))
                return R.drawable.xofficepresentation_92765;

            return R.drawable.text_x_generic_icon;
        }

        static String formatFileSizeStatic(long size) {
            if (size < 1024) return size + " B";
            if (size < 1024 * 1024) return String.format("%.1f KB", size / 1024.0);
            if (size < 1024 * 1024 * 1024) return String.format("%.1f MB", size / (1024.0 * 1024));
            return String.format("%.1f GB", size / (1024.0 * 1024 * 1024));
        }

        static String formatDateStatic(long millis) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            return sdf.format(new Date(millis));
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView fileIcon;
            TextView fileName;
            TextView fileInfo;
            TextView fileSize;

            ViewHolder(View itemView) {
                super(itemView);
                fileIcon = itemView.findViewById(R.id.file_icon);
                fileName = itemView.findViewById(R.id.file_name);
                fileInfo = itemView.findViewById(R.id.file_info);
                fileSize = itemView.findViewById(R.id.file_size);
            }
        }
    }
}
