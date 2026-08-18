package com.ceditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.List;

/**
 * FileAdapter - RecyclerView adapter for file/folder items.
 */
public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private Context context;
    private List<File> files;
    private OnFileClickListener listener;

    public interface OnFileClickListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public FileAdapter(Context context, List<File> files) {
        this.context = context;
        this.files = files;
        if (context instanceof OnFileClickListener) {
            this.listener = (OnFileClickListener) context;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        File file = files.get(position);
        holder.fileName.setText(file.getName());
        
        if (file.isDirectory()) {
            holder.fileIcon.setImageResource(R.drawable.folder);
            holder.fileInfo.setText("");
        } else {
            holder.fileIcon.setImageResource(getFileIcon(file.getName()));
            String size = formatFileSize(file.length());
            holder.fileInfo.setText(size);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(holder.getAdapterPosition());
        });
        
        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) listener.onItemLongClick(holder.getAdapterPosition());
            return true;
        });
    }

    private int getFileIcon(String fileName) {
        String lower = fileName.toLowerCase();
        if (lower.endsWith(".java")) return R.drawable.text_x_java_icon;
        if (lower.endsWith(".kt")) return R.drawable.app_x_kotlin_icon;
        if (lower.endsWith(".xml")) return R.drawable.text_x_generic_icon;
        if (lower.endsWith(".json")) return R.drawable.text_x_generic_icon;
        if (lower.endsWith(".js")) return R.drawable.text_x_script_icon;
        if (lower.endsWith(".html") || lower.endsWith(".htm")) return R.drawable.content_web;
        if (lower.endsWith(".css")) return R.drawable.text_css_icon;
        if (lower.endsWith(".md")) return R.drawable.text_x_markdown_icon;
        if (lower.endsWith(".txt")) return R.drawable.text_x_generic_icon;
        if (lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg") || lower.endsWith(".webp")) return R.drawable.image_x_generic_icon;
        if (lower.endsWith(".pdf")) return R.drawable.app_pdf_icon;
        if (lower.endsWith(".py")) return R.drawable.text_x_python_icon;
        if (lower.endsWith(".php")) return R.drawable.text_x_script_icon;
        if (lower.endsWith(".c") || lower.endsWith(".cpp") || lower.endsWith(".h")) return R.drawable.text_x_csrc_icon;
        if (lower.endsWith(".gradle")) return R.drawable.text_x_generic_icon;
        if (lower.endsWith(".yml") || lower.endsWith(".yaml")) return R.drawable.text_x_generic_icon;
        if (lower.endsWith(".sh")) return R.drawable.text_x_script_icon;
        if (lower.endsWith(".properties")) return R.drawable.text_x_generic_icon;
        return R.drawable.file; // generic file icon
    }

    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.1f MB", bytes / (1024.0 * 1024));
        return String.format("%.1f GB", bytes / (1024.0 * 1024 * 1024));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fileName, fileInfo;
        ImageView fileIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.file_name);
            fileInfo = itemView.findViewById(R.id.file_info);
            fileIcon = itemView.findViewById(R.id.file_icon);
        }
    }
}
