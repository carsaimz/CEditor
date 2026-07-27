package com.ceditor;


import android.os.Handler;
import android.os.Looper;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ProgressMonitor;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;


public class GitCloner {
	private static final ExecutorService executor = Executors.newSingleThreadExecutor();
	private static final Handler mainHandler = new Handler(Looper.getMainLooper());
	private static final AtomicBoolean cancelFlag = new AtomicBoolean(false);
	

	public interface CloneCallback {
		void onProgress(int percent);
		void onSuccess(String folderPath);
		void onError(Exception e);
		void onCancelled();
	}

	
	public static void cancelCloning() {
		cancelFlag.set(true);
	}
	

	public static void cloneRepository(String repoUrl, String basePath, CloneCallback callback) {
		executor.execute(() -> {
			try {
				cancelFlag.set(false);
				String repoName = repoUrl.substring(repoUrl.lastIndexOf("/") + 1).replace(".git", "");
				File repoFolder = new File(basePath, repoName);
				if (!repoFolder.exists()) repoFolder.mkdirs();
				
				mainHandler.post(() -> callback.onProgress(0));
				
				Git.cloneRepository()
				.setURI(repoUrl)
				.setDirectory(repoFolder)
				.setProgressMonitor(new ProgressMonitor() {
					private int totalTasks = 1;
					private int completedTasks = 0;
				
                	
					@Override
					public void start(int totalTasks) {
						this.totalTasks = totalTasks > 0 ? totalTasks : 1;
					}
				
                	
					@Override
					public void beginTask(String title, int totalWork) {}
					
			
            		@Override
					public void update(int completed) {
						if (totalTasks > 0) {
							int percent = (int) (((double) completedTasks / totalTasks) * 100);
							completedTasks += completed;
							mainHandler.post(() -> callback.onProgress(percent));
						}
					}
					
				
                	@Override
					public void endTask() {}
					
					@Override
					public boolean isCancelled() {
						return cancelFlag.get();
					}
				})
				.call();
				
				if (cancelFlag.get()) {
					mainHandler.post(callback::onCancelled);
					return;
				}
				
				mainHandler.post(() -> callback.onSuccess(repoFolder.getAbsolutePath()));
				
			} catch (GitAPIException e) {
				mainHandler.post(() -> callback.onError(e));
			}
		});
	}
}