package com.ceditor;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.robinhood.ticker.*;
import io.github.rosemoe.sora.*;
// import io.github.rosemoe.sora.langs.textmate.*; // Commented out due to missing language-textmate library
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import org.json.*;
import android.content.ContentResolver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.webkit.MimeTypeMap;
import com.bumptech.glide.Glide;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import java.io.FileInputStream;
import java.io.File;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.zip.CRC32;



public class FileInfoActivity extends AppCompatActivity {
	
	private String size;
	private String type;
	private String fname;
	private String fparent;
	private String mimeType;
	private boolean fileIsHidden;
	private String percentageOfStorage;
	private String checksumMD5 = "Folder";
	private String checksumSHA1 = "Folder";
	private String checksumSHA256 = "Folder";
	private String checksumSHA384 = "Folder";
	private String checksumSHA512 = "Folder";
	private String checksumCRC32 = "Folder";
	
	private File file;
	
	private FileInfoTask fileInfoTask;
	private AlertDialog changetime;
	private AlertDialog changepermission;
	private String path = "";
	private String cl = "";
	
	private ArrayList<String> files = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private TextView textview1;
	private LinearLayout linear3;
	private ScrollView vscroll1;
	private LinearLayout linear9;
	private ImageView imageview1;
	private LinearLayout linear6;
	private TextView filename;
	private TextView textview10;
	private LinearLayout linear4;
	private TextView filemetadata;
	private ShimmerTextView textview13;
	private TextView textview7;
	private ShimmerTextView textview12;
	private TextView filechecksum;
	private LinearLayout linear7;
	private LinearLayout linear5;
	private TextView textview11;
	private TextView TextView15;
	private LinearLayout miscellaneous_group;
	private TextView textview9;
	private TextView checksumresult;
	private EditText edittext1;
	private Button button1;
	private LinearLayout changetime_linear;
	private LinearLayout linear10;
	private ImageView imageview21;
	private TextView textview3;
	private CheckBox CheckBoxIsHidden;
	private TextView textview14;
	private LinearLayout close;
	private ImageView imageview9;
	private TextView textview18;
	
	private Intent i = new Intent();
	private SharedPreferences data;
	private AlertDialog.Builder alert;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.file_info);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		textview1 = findViewById(R.id.textview1);
		linear3 = findViewById(R.id.linear3);
		vscroll1 = findViewById(R.id.vscroll1);
		linear9 = findViewById(R.id.linear9);
		imageview1 = findViewById(R.id.imageview1);
		linear6 = findViewById(R.id.linear6);
		filename = findViewById(R.id.filename);
		textview10 = findViewById(R.id.textview10);
		linear4 = findViewById(R.id.linear4);
		filemetadata = findViewById(R.id.filemetadata);
		textview13 = findViewById(R.id.textview13);
		textview7 = findViewById(R.id.textview7);
		textview12 = findViewById(R.id.textview12);
		filechecksum = findViewById(R.id.filechecksum);
		linear7 = findViewById(R.id.linear7);
		linear5 = findViewById(R.id.linear5);
		textview11 = findViewById(R.id.textview11);
		TextView15 = findViewById(R.id.TextView15);
		miscellaneous_group = findViewById(R.id.miscellaneous_group);
		textview9 = findViewById(R.id.textview9);
		checksumresult = findViewById(R.id.checksumresult);
		edittext1 = findViewById(R.id.edittext1);
		button1 = findViewById(R.id.button1);
		changetime_linear = findViewById(R.id.changetime_linear);
		linear10 = findViewById(R.id.linear10);
		imageview21 = findViewById(R.id.imageview21);
		textview3 = findViewById(R.id.textview3);
		CheckBoxIsHidden = findViewById(R.id.CheckBoxIsHidden);
		textview14 = findViewById(R.id.textview14);
		close = findViewById(R.id.close);
		imageview9 = findViewById(R.id.imageview9);
		textview18 = findViewById(R.id.textview18);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		alert = new AlertDialog.Builder(this);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				getClipboardText(edittext1, FileInfoActivity.this);
			}
		});
		
		changetime_linear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				changetime = new AlertDialog.Builder(FileInfoActivity.this).create();
				LayoutInflater changetimeLI = getLayoutInflater();
				View changetimeCV = (View) changetimeLI.inflate(R.layout.changetime, null);
				changetime.setView(changetimeCV);
				final View linear1 = (View)
				changetimeCV.findViewById(R.id.linear1);
				final View linear4 = (View)
				changetimeCV.findViewById(R.id.linear4);
				final TextView textview7 = (TextView)
				changetimeCV.findViewById(R.id.textview7);
				final TextView textview8 = (TextView)
				changetimeCV.findViewById(R.id.textview8);
				final View apply_button = (View)
				changetimeCV.findViewById(R.id.apply_button);
				final View cancel_button = (View)
				changetimeCV.findViewById(R.id.cancel_button);
				final EditText yearEditText = (EditText)
				changetimeCV.findViewById(R.id.yearEditText);
				final EditText monthEditText = (EditText)
				changetimeCV.findViewById(R.id.monthEditText);
				final EditText dayEditText = (EditText)
				changetimeCV.findViewById(R.id.dayEditText);
				final EditText hourEditText = (EditText)
				changetimeCV.findViewById(R.id.hourEditText);
				final EditText minuteEditText = (EditText)
				changetimeCV.findViewById(R.id.minuteEditText);
				final EditText secondEditText = (EditText)
				changetimeCV.findViewById(R.id.secondEditText);
				changetime.setCancelable(true);
				changetime.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				File file = new File(path);
				textview7.setText("Change time: ".concat(fname));
				_gradDrawable("#111111", "#474747", 1, 15, 5, false, linear1);
				_gradDrawable("#262626", "#474747", 1, 15, 5, false, linear4);
				_gradDrawable("#00000000", "#474747", 1, 15, 3, true, cancel_button);
				_gradDrawable("#00000000", "#474747", 1, 15, 3, true, apply_button);
				_setEllipsize(textview7);
				_setEllipsize(textview8);
				if (file.exists()) {
					long lastModified = file.lastModified();
					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(lastModified);
					yearEditText.setText(String.valueOf(calendar.get(Calendar.YEAR)));
					monthEditText.setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
					dayEditText.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
					hourEditText.setText(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
					minuteEditText.setText(String.valueOf(calendar.get(Calendar.MINUTE)));
					secondEditText.setText(String.valueOf(calendar.get(Calendar.SECOND)));
				}
				else {
					_AppDesignerToast("File does not exist. ");
				}
				cancel_button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						changetime.dismiss();
					}
				});
				apply_button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						try {
							int year = Integer.parseInt(yearEditText.getText().toString());
							int month = Integer.parseInt(monthEditText.getText().toString()) - 1;
							int day = Integer.parseInt(dayEditText.getText().toString());
							int hour = Integer.parseInt(hourEditText.getText().toString());
							int minute = Integer.parseInt(minuteEditText.getText().toString());
							int second = Integer.parseInt(secondEditText.getText().toString());
							
							Calendar calendar = Calendar.getInstance();
							calendar.set(year, month, day, hour, minute, second);
							if (calendar.getTimeInMillis() < 0) {
								_AppDesignerToast("Date cannot be before January 1, 1970.");
							}
							if (file.exists()) {
								boolean success = file.setLastModified(calendar.getTimeInMillis());
								if (success) {
									_AppDesignerToast("File last modification date changed.");
									new FileInfoTask().execute(file);
									_refresh(path);
								}
								else {
									_AppDesignerToast("Failed to change file date. ");
								}
								changetime.dismiss();
							}
							else {
								_AppDesignerToast("File does not exist. ");
							}
						} catch (Exception e) {
							_AppDesignerToast("Invalid input. Please try again.");
						}
					}
				});
				changetime.show();
				changetime.setOnDismissListener(new DialogInterface.OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialogInterface) {
					} 
				});
			}
		});
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				CheckBoxIsHidden.performClick();
			}
		});
		
		CheckBoxIsHidden.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				alert.setTitle("Confirmation");
				boolean isHidden = fname.startsWith(".");
				String itemType = type.equals("File") ? "file" : "folder";
				String action = isHidden ? "public" : "hidden";
				String opposite = isHidden ? "private" : "public";
				String message = String.format(
				    "This %s is about to be %s.\n" +
				    "Do you want to continue?",
				    itemType, action
				);
				alert.setMessage(message);
				alert.setPositiveButton(isHidden ? "Unhide" : "Hide", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						String newPath;
						boolean currentlyHidden = fname.startsWith(".");
						File cfile = new File(path);
						if (_isChecked) {
							newPath = currentlyHidden ? fparent + "/" + fname : fparent + "/." + fname;
						}
						else {
							newPath = currentlyHidden ? fparent + "/" + fname.replaceFirst("^\\.", "") : fparent + "/" + fname;
						}
						
						File newFile = new File(newPath);
						
						if (cfile.renameTo(newFile)) {
							cfile = newFile;
							_AppDesignerToast(_isChecked ? "File Hidden" : "File Unhidden");
							_refresh(newPath);
						}
						else {
							CheckBoxIsHidden.setChecked(!_isChecked);
							_AppDesignerToast("Failed to change visibility.");
						}
					}
				});
				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				_CXYZ_dialog_theme(alert);
			}
		});
		
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
	}
	
	private void initializeLogic() {
		
		Window window = getWindow();
		window.setStatusBarColor(Color.TRANSPARENT);
		window.getDecorView().setSystemUiVisibility(
		View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		
		
		linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)1, 0xFF474747, 0xFF111111));
		filename.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		filename.setMarqueeRepeatLimit(-1);
		filename.setHorizontallyScrolling(true);
		filename.setSelected(true);
		_dialogTheme();
		_ViewWidthHeight(linear1, SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) - SketchwareUtil.getDip(getApplicationContext(), (int)(16)), SketchwareUtil.getDisplayHeightPixels(getApplicationContext()) - SketchwareUtil.getDip(getApplicationContext(), (int)(16)));
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, close);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, button1);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, false, edittext1);
		path = getIntent().getStringExtra("tpath");
		File file = new File(path);
		new FileInfoTask().execute(file);
		fname = getFileName(path);
		fparent = file.getParent();
		type = file.isDirectory() ? "Folder" : "File";
		mimeType = getMIMEType(file);
		fileIsHidden = file.isHidden();
		if (fileIsHidden) {
			textview14.setText("Unhidden");
		}
		else {
			textview14.setText("Hidden");
		}
		_FileIconPlaceholder(imageview1, path);
		_ripple(linear9);
		_ripple(linear10);
		_ripple(changetime_linear);
		filename.setText(fname);
		textview10.setText(type);
		cl = "#212121";
		_CRadius(miscellaneous_group, cl);
		if (!data.getString("clipboard", "").equals("true")) {
			button1.setVisibility(View.GONE);
		}
	}
	
	
	public static String getFileName(final String _name){
			if (_name.startsWith("/")) {
					if (!_name.trim().equals("")) {
							final String nm = _name.substring((int)(_name.lastIndexOf("/") + 1), (int)(_name.length()));
							return (nm);
					}
					else {
							return("");
					}
			}
			else {
					if (_name.lastIndexOf("/") == _name.indexOf("/")) {
							if ((_name.lastIndexOf("/") + 1) == _name.length()) {
									return _name;
							}
							else {
									if (_name.indexOf(":") == -1) {
											return _name;
									}
									else {
											if ((_name.lastIndexOf(":") + 1) == _name.length()) {
													return _name;
											}
											else {
													if (_name.indexOf(":") == _name.lastIndexOf(":")) {
															if (_name.contains("/")) {
																	return _name.substring(_name.lastIndexOf("/") + 1, _name.length());
															}
															else {
																	return _name.substring(_name.lastIndexOf(":") + 1, _name.length());
															}
													}
													else {
															return _name.substring(_name.lastIndexOf("/") + 1, _name.length());
													}
											}
									}
							}
					}
					else {
							return _name.substring(_name.lastIndexOf("/") + 1, _name.length());
					}
			}
	}
	
	
	public int getRandomNumber(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	
	public String getMIMEType(File file) {
			String mimeType = null;
			Uri uri = Uri.fromFile(file);
			String extension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
			if (extension != null) {
					mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
			}
			if (mimeType == null) {
					mimeType = "*/*";
			}
			return mimeType;
	}
	
	
	public long getFolderSize(File folder) {
			long size = 0;
			if (folder.isDirectory()) {
					File[] files = folder.listFiles();
					if (files != null) {
							for (File file : files) {
									if (file.isFile()) {
											size += file.length();
									} else if (file.isDirectory()) {
											size += getFolderSize(file);
									}
							}
					}
			}
			return size;
	}
	
	
	public String getReadableFileSize(long size) {
			if (size <= 0) return "0 B";
			final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
			int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
			return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
	
	
	public String getFilePercentageOfStorage(long fileSize) {
			StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
			long totalBytes;
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
					totalBytes = statFs.getTotalBytes();
			} else {
					long blockSize = statFs.getBlockSize();
					long totalBlocks = statFs.getBlockCount();
					totalBytes = blockSize * totalBlocks;
			}
			double percentage = ((double) fileSize / totalBytes) * 100;
			return new DecimalFormat("#0.000000").format(percentage) + " %";
	}
	
	
	public Map<String, Integer> getFolderItemCount(File file) {
			int fileCount = 0;
			int folderCount = 0;
			
			if (file.isDirectory()) {
					File[] files = file.listFiles();
					if (files != null) {
							for (File f : files) {
									if (f.isFile()) {
											fileCount++;
									} else if (f.isDirectory()) {
											folderCount++;
									}
							}
					}
			}
			
			Map<String, Integer> result = new HashMap<>();
			result.put("Total", fileCount + folderCount);
			result.put("Files", fileCount);
			result.put("Folders", folderCount);
			return result;
	}
	
	
	public String getUnixPermissions(File file) {
			String ownerPerm = (file.canRead() ? "r" : "-") +
			(file.canWrite() ? "w" : "-") +
			(file.canExecute() ? "x" : "-");
			String permissions = "";
			try {
					java.lang.Process process = Runtime.getRuntime().exec("ls -ld " + file.getAbsolutePath());
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String result = reader.readLine();
					if (result != null && result.length() > 10) {
							permissions = result.substring(1, 10);
					}
			} catch (IOException e) {
					e.printStackTrace();
					permissions = ownerPerm + "???";
			}
			int octal = convertPermissionsToOctal(permissions);
			return permissions + " (" + octal + ")";
	}
	
	
	private int convertPermissionsToOctal(String permissions) {
			int[] bits = new int[3];
			
			for (int i = 0; i < permissions.length(); i++) {
					char perm = permissions.charAt(i);
					int group = i / 3;
					
					switch (perm) {
							case 'r':
							bits[group] += 4;
							break;
							case 'w':
							bits[group] += 2;
							break;
							case 'x':
							bits[group] += 1;
							break;
					}
			}
			return (bits[0] * 100) + (bits[1] * 10) + bits[2];
	}
	
	public String getOwnerAndGroupInfo(File file) {
			try {
					java.lang.Process process = Runtime.getRuntime().exec("stat -c %U:%G " + file.getAbsolutePath());
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String ownerGroupInfo = reader.readLine();
					reader.close();
					return ownerGroupInfo != null ? ownerGroupInfo : "Unknown";
			} catch (IOException e) {
					e.printStackTrace();
					return "Error retrieving owner/group info";
			}
	}
	
	
	public String getFileChecksum(String algorithm, File file) throws IOException, NoSuchAlgorithmException {
			if (algorithm.equalsIgnoreCase("CRC32")) {
					CRC32 crc = new CRC32();
					try (FileInputStream fis = new FileInputStream(file)) {
							byte[] byteArray = new byte[1024];
							int bytesCount;
							while ((bytesCount = fis.read(byteArray)) != -1) {
									crc.update(byteArray, 0, bytesCount);
							}
					}
					return Long.toHexString(crc.getValue());
			} else {
					MessageDigest digest = MessageDigest.getInstance(algorithm);
					try (FileInputStream fis = new FileInputStream(file)) {
							byte[] byteArray = new byte[1024];
							int bytesCount;
							while ((bytesCount = fis.read(byteArray)) != -1) {
									digest.update(byteArray, 0, bytesCount);
							}
					}
					byte[] bytes = digest.digest();
					BigInteger bigInt = new BigInteger(1, bytes);
					return bigInt.toString(16);
			}
	}
	
	
	public void getClipboardText(TextView textView, Context context) {
			ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
			
			if (clipboard != null && clipboard.hasPrimaryClip()) {
					ClipData clipData = clipboard.getPrimaryClip();
					
					if (clipData != null && clipData.getItemCount() > 0) {
							CharSequence clipboardText = clipData.getItemAt(0).getText();
							
							if (clipboardText != null) {
									textView.setText(clipboardText.toString());
							} else {
								_AppDesignerToast("Clipboard contains non-text data!");
							}
					}
			} else {
					_AppDesignerToast("Clipboard is empty!");
			}
	}
	
	public class FileMetadata {
		    public String name;
		    public String absolutePath;
		    public String parentPath;
		    public String type;
		    public boolean canExecute;
		    public boolean canRead;
		    public boolean canWrite;
		    public boolean isHidden;
		    public String permissionInfo;
		    public String ownerGroupInfo;
		    public String lastModified;
		    public long sizeInBytes;
		    public String mimeType;
		    public String checksumMD5async;
		    public String checksumSHA1async;
		    public String checksumSHA256async;
		    public String checksumSHA384async;
		    public String checksumSHA512async;
		    public String checksumCRC32async;
		    public int totalItems;
		    public int fileCount;
		    public int folderCount;
		    public String errorMessage;
	}
	
	
	private class FileInfoTask extends AsyncTask<File, Void, FileMetadata> {
			
		    
			@Override
			protected FileMetadata doInBackground(File... params) {
					File file = params[0];
					FileMetadata metadata = new FileMetadata();
					
			        
					try {
							if (!file.exists()) {
									metadata.errorMessage = "File/folder doesn't exist";
									return metadata;
							}
							
							metadata.name = file.getName();
							metadata.absolutePath = file.getAbsolutePath();
							metadata.parentPath = file.getParent();
							metadata.type = file.isDirectory() ? "Folder" : "File";
							metadata.canExecute = file.canExecute();
							metadata.canRead = file.canRead();
							metadata.canWrite = file.canWrite();
							metadata.isHidden = file.isHidden();
							metadata.permissionInfo = getUnixPermissions(file);
							metadata.ownerGroupInfo = getOwnerAndGroupInfo(file);
							metadata.lastModified = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(file.lastModified()));
						
				        	
							if (file.isDirectory()) {
									metadata.sizeInBytes = getFolderSize(file);
									Map<String, Integer> itemCount = getFolderItemCount(file);
									metadata.totalItems = itemCount.get("Total");
									metadata.fileCount = itemCount.get("Files");
									metadata.folderCount = itemCount.get("Folders");
							} else {
									metadata.sizeInBytes = file.length();
									metadata.mimeType = getMIMEType(file);
									metadata.checksumMD5async = getFileChecksum("MD5", file);
									metadata.checksumSHA1async = getFileChecksum("SHA-1", file);
									metadata.checksumSHA256async = getFileChecksum("SHA-256", file); 
									metadata.checksumSHA384async = getFileChecksum("SHA-384", file); 
									metadata.checksumSHA512async = getFileChecksum("SHA-512", file);
									metadata.checksumCRC32async = getFileChecksum("CRC32", file);  
									checksumMD5 = getFileChecksum("MD5", file);
									checksumSHA1 = getFileChecksum("SHA-1", file);
									checksumSHA256 = getFileChecksum("SHA-256", file);
									checksumSHA384 = getFileChecksum("SHA-384", file);
									checksumSHA512 = getFileChecksum("SHA-512", file);
									checksumCRC32 = getFileChecksum("CRC32", file);
							}
							
					} catch (Exception e) {
							e.printStackTrace();
							metadata.errorMessage = "Error while fetching file info";
					}
					return metadata;
			}
			
		    
			@Override
			protected void onPostExecute(FileMetadata result) {
					textview12.setVisibility(View.GONE);
					textview13.setVisibility(View.GONE);
			        
			        
					if (result.errorMessage != null) {
							filename.setText(result.errorMessage);
							return;
					}
			        
			        
					filename.setText(result.name);
					filemetadata.setText("Parent: " + result.parentPath +
					"\n\nAbsolute path: " + result.absolutePath +
					"\n\nIs hidden: " + result.isHidden +
					"\n\nMIME Type: " + (result.type.equals("File") ? result.mimeType : "Folder") +
					"\n\nCan Execute: " + result.canExecute +
					"\n\nCan Read: " + result.canRead +
					"\n\nCan Write: " + result.canWrite +
					"\n\nPermissions (Unix/octal): " + result.permissionInfo +
					"\n\nOwner/Group: " + result.ownerGroupInfo +
					"\n\nLast Modified: " + result.lastModified +
					"\n\n" + result.type + " Size: " + getReadableFileSize(result.sizeInBytes) +
					"\n\nOccupies: " + getFilePercentageOfStorage(result.sizeInBytes) + " of total storage");
				
			    
					if (result.type.equals("Folder")) {
							filemetadata.append("\n\nTotal Items: " + result.totalItems +
							"\n\nFiles: " + result.fileCount +
							"\n\nFolders: " + result.folderCount);
					} else if (result.type.equals("File")) {
							filechecksum.setText("MD5: " + result.checksumMD5async +
							"\n\nSHA-1: " + result.checksumSHA1async +
							"\n\nSHA-256: " + result.checksumSHA256async +
							"\n\nSHA-384: " + result.checksumSHA384async +
							"\n\nSHA-512: " + result.checksumSHA512async +
							"\n\nCRC32: " + result.checksumCRC32async);
							filechecksum.setTextIsSelectable(true);
					}
					
					filemetadata.setTextIsSelectable(true);
					
					edittext1.addTextChangedListener(new TextWatcher() {
							@Override
							public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
									final String _charSeq = _param1.toString();
									int characterCount = _charSeq.length();
									String input = _charSeq.toString();
									
					                
									if (input.isEmpty()) {
											checksumresult.setText("");
									} else if (input.equals(checksumMD5)) {
											checksumresult.setText("MD5 ✔");
											checksumresult.setTextColor(0xFF00C853);
									} else if (input.equals(checksumSHA1)) {
											checksumresult.setText("SHA-1 ✔");
											checksumresult.setTextColor(0xFF00C853); 
									} else if (input.equals(checksumSHA256)) {
											checksumresult.setText("SHA-256 ✔");
											checksumresult.setTextColor(0xFF00C853); 
									} else if (input.equals(checksumSHA384)) {
											checksumresult.setText("SHA-384 ✔");
											checksumresult.setTextColor(0xFF00C853); 
									} else if (input.equals(checksumSHA512)) {
											checksumresult.setText("SHA-512 ✔");
											checksumresult.setTextColor(0xFF00C853); 
									} else if (input.equals(checksumCRC32)) {
											checksumresult.setText("CRC32 ✔");
											checksumresult.setTextColor(0xFF00C853);
									} else {
											if (characterCount == 128) {
													checksumresult.setText("SHA-512 ✘");
													checksumresult.setTextColor(0xFFD50000);
											} else if (characterCount == 96) {
													checksumresult.setText("SHA-384 ✘");
													checksumresult.setTextColor(0xFFD50000);
											} else if (characterCount == 64) {
													checksumresult.setText("SHA-256 ✘");
													checksumresult.setTextColor(0xFFD50000);
											} else if (characterCount == 40) {
													checksumresult.setText("SHA-1 ✘");
													checksumresult.setTextColor(0xFFD50000);
											} else if (characterCount == 32) {
													checksumresult.setText("MD5 ✘");
													checksumresult.setTextColor(0xFFD50000);
											} else if (characterCount == 8) {
													checksumresult.setText("CRC32 ✘");
													checksumresult.setTextColor(0xFFD50000);
											} else {
													checksumresult.setText("?");
													checksumresult.setTextColor(0xFF607D8B);
											}
									}
									
							}
				            
							
							@Override
							public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
									
							}
				            
							
							@Override
							public void afterTextChanged(Editable _param1) {
									
							}
					});
			        
					
					if (type.equals("Folder")) {
							textview9.setVisibility(View.GONE);
							textview7.setVisibility(View.GONE);
							edittext1.setVisibility(View.GONE);
							button1.setVisibility(View.GONE);
							textview12.setVisibility(View.GONE);
					}
			}
			
		    
			@Override
			protected void onCancelled(FileMetadata result) {
					filemetadata.setText("TASK_CANCELLED_BY_USER");
					filechecksum.setText("TASK_CANCELLED_BY_USER");
			}
	}
	
	
	@Override
	public void onBackPressed() {
		if (fileInfoTask != null && fileInfoTask.getStatus() == AsyncTask.Status.RUNNING) {
				fileInfoTask.cancel(true);
		}
		finish();
		overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
	}
	public void _dialogTheme() {
	}
	
	
	 @Override 
	    public void setContentView( int layoutResID) {
		if(getIntent().getBooleanExtra("dialogTheme",true)){
			supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
			setTheme(R.style.CustomDialogTheme); // Use the custom theme
			setFinishOnTouchOutside(true);
			/* @Deprecated 
try {
java.lang.reflect.Method getActivityOptions = Activity.class.getDeclaredMethod("getActivityOptions"); 
getActivityOptions.setAccessible(true);
Object options = getActivityOptions.invoke(this); 
Class<?>[] classes = Activity.class.getDeclaredClasses(); 
Class<?> translucentConversionListenerClazz = null; 
for (Class clazz : classes) { if (clazz.getSimpleName().contains("TranslucentConversionListener")) { translucentConversionListenerClazz = clazz; } } 
java.lang.reflect.Method convertToTranslucent = Activity.class.getDeclaredMethod("convertToTranslucent", translucentConversionListenerClazz, ActivityOptions.class); convertToTranslucent.setAccessible(true); 
convertToTranslucent.invoke(this, null, options);
} catch (Throwable t) {
}
*/
		}
		super.setContentView(layoutResID);  
	}
	
	
	public void _ViewWidthHeight(final View _view, final double _w, final double _h) {
		_view.getLayoutParams().width = (int)_w;
		_view.getLayoutParams().height = (int)_h;
		_view.requestLayout();
	}
	
	
	public void _FileIconPlaceholder(final ImageView _imageview, final String _path) {
		/*String mimeType = getMIMEType(file);*/
		String lowerPath = _path.toLowerCase();
		Pattern pattern = Pattern.compile(
		"(read[-_ ]?me|read[-_ ]?me|important|readme)", 
		Pattern.CASE_INSENSITIVE
		);
		
		if (pattern.matcher(lowerPath).find()) {
				_imageview.setImageResource(R.drawable.textxreadme_92776);
		} else if (_path.endsWith(".swf") || _path.endsWith(".SWF")) {
				_imageview.setImageResource(R.drawable.applicationxshockwaveflash_92719);
		} else if (_path.endsWith(".css") || _path.endsWith(".CSS")) {
				_imageview.setImageResource(R.drawable.text_css_icon);
		} else if (_path.toLowerCase().contains("log") || _path.endsWith(".log")) {
				_imageview.setImageResource(R.drawable.textxlog_92758);
		} else if (_path.endsWith(".js") || _path.endsWith(".JS")) {
				_imageview.setImageResource(R.drawable.app_x_javascript_icon);
		} else if (_path.endsWith(".php") || _path.endsWith(".PHP")) {
				_imageview.setImageResource(R.drawable.app_x_php_icon);
		} else if (_path.endsWith(".apk") || _path.endsWith(".apks") || _path.endsWith(".aab") || _path.endsWith(".xapk") || _path.endsWith(".apkm") || _path.endsWith(".akp")) {
				_imageview.setImageResource(R.drawable.app_vnd_android_package_archive_icon);
		} else if (_path.endsWith(".iso") || _path.endsWith(".udf")) {
				_imageview.setImageResource(R.drawable.app_x_iso_icon);
		} else if (_path.endsWith(".tar") || _path.endsWith(".gzip")) {
				_imageview.setImageResource(R.drawable.applicationvnd_92738);
		} else if (_path.endsWith(".url") || _path.endsWith(".lnk")) {
				_imageview.setImageResource(R.drawable.applicationxmswinurl_92784);
		} else if (_path.endsWith(".c") || _path.endsWith(".cpp") || _path.endsWith(".h")) {
				_imageview.setImageResource(R.drawable.textxcsrc_92718);
		} else if (_path.endsWith(".cs") || _path.endsWith(".CS")) {
				_imageview.setImageResource(R.drawable.text_x_csharp_icon);
		} else if (_path.endsWith(".rpm")) {
				_imageview.setImageResource(R.drawable.rpm_icon);
		} else if (_path.endsWith(".srt")) {
				_imageview.setImageResource(R.drawable.app_x_srt_icon);
		} else if (_path.endsWith(".flac")) {
				_imageview.setImageResource(R.drawable.audio_x_flac_icon);
		} else if (_path.endsWith(".r") || _path.endsWith(".R")) {
				_imageview.setImageResource(R.drawable.text_r_icon);
		} else if (_path.endsWith(".xlf") || _path.endsWith(".xliff")) {
				_imageview.setImageResource(R.drawable.app_x_gettext_translation_icon);
		} else if (_path.endsWith(".java") || _path.endsWith(".jar") || _path.endsWith(".class")) {
				_imageview.setImageResource(R.drawable.text_x_java_icon);
		} else if (_path.endsWith(".rs") || _path.endsWith(".rlib")) {
				_imageview.setImageResource(R.drawable.text_x_rust_icon);
		} else if (_path.endsWith(".rb")) {
				_imageview.setImageResource(R.drawable.text_x_ruby_icon);
		} else if (_path.endsWith(".doc") || _path.endsWith(".docx") || _path.endsWith(".dot") || _path.endsWith(".wbk")) {
				_imageview.setImageResource(R.drawable.x_office_document_icon);
		} else if (_path.endsWith(".ppt") || _path.endsWith(".pptx")) {
				_imageview.setImageResource(R.drawable.xofficepresentation_92765);
		} else if (_path.endsWith(".torrent")) {
				_imageview.setImageResource(R.drawable.app_x_bittorrent_icon);
		} else if (_path.endsWith(".hex") || _path.endsWith(".rom")) {
				_imageview.setImageResource(R.drawable.text_x_hex_icon);
		} else if (_path.endsWith(".xls") || _path.endsWith(".xlsx") || _path.endsWith(".xlsm") || _path.endsWith(".xlsb")) {
				_imageview.setImageResource(R.drawable.x_office_spreadsheet_icon);
		} else if (_path.endsWith(".py") || _path.endsWith(".pyo") || _path.endsWith(".pyw") || _path.endsWith(".pyi") || _path.endsWith(".pyc") || _path.endsWith(".pyz") || _path.endsWith(".pyd")) {
				_imageview.setImageResource(R.drawable.text_x_python_icon);
		} else if (_path.endsWith(".unknown") || _path.endsWith(".unk") || _path.endsWith(".")){ 
				_imageview.setImageResource(R.drawable.unknown_icon);
		} else if (_path.endsWith(".zip") || _path.endsWith(".zipx") || _path.endsWith(".z01") || _path.endsWith(".zx01") || _path.endsWith(".ZIP") || _path.endsWith(".rar") || _path.endsWith(".rev") || _path.endsWith(".r00") || _path.endsWith(".r01") || _path.endsWith(".RAR") || _path.endsWith(".7z") || _path.endsWith(".7Z")) {
				_imageview.setImageResource(R.drawable.app_x_compress_icon);
		} else if (_path.endsWith(".pdf") || _path.endsWith(".PDF")) {
				_getPdfCover(_imageview, _path);
		} else if (_path.endsWith(".json") || _path.endsWith(".JSON")) {
				_imageview.setImageResource(R.drawable.app_json_icon);
		} else if (_path.endsWith(".ttf") || _path.endsWith(".otf") || _path.endsWith(".woff") || _path.endsWith(".TTF") || _path.endsWith(".OTF") || _path.endsWith(".WOFF")) {
				_imageview.setImageResource(R.drawable.app_x_font_ttf_icon);
		} else if (_path.endsWith(".xml") || _path.endsWith(".XML")) {
				_imageview.setImageResource(R.drawable.app_xml_icon);
		} else if (_path.endsWith(".txt") || _path.endsWith(".TXT")) {
				_imageview.setImageResource(R.drawable.text_x_generic_icon);
		} else if (FileUtil.isDirectory(_path)) {
				_DirIconPlaceholder(imageview1, _path);
		} else {
				if (mimeType != null && mimeType.contains("image")) {
						_setImageFromFile(_imageview, _path);
				} else if (mimeType != null && mimeType.contains("video")) {
						_getMP4Thumbnail(_path, _imageview);
				} else if (mimeType != null && mimeType.contains("audio")) {
						_getAlbumCover(_imageview, _path);
				} else {
						if (mimeType.contains("yaml")) {
								_imageview.setImageResource(R.drawable.app_x_yaml_icon);
						} else if (mimeType.contains("diff")) {
								_imageview.setImageResource(R.drawable.text_x_diff_icon);
						} else if (mimeType.contains("musescore")) {
								_imageview.setImageResource(R.drawable.app_x_musescore_icon);
						} else if (mimeType.contains("markdown")) {
								_imageview.setImageResource(R.drawable.text_x_markdown_icon);
						} else if (mimeType.contains("gettext")) {
								_imageview.setImageResource(R.drawable.text_x_po_icon);
						} else {
								_imageview.setImageResource(R.drawable.app_x_zerosize_icon);
						}
				}
		}
	}
	
	
	public void _setImageFromFile(final ImageView _img, final String _path) {
		java.io.File file = new java.io.File(_path);
		Uri imageUri = Uri.fromFile(file);
		Glide.with(getApplicationContext())
		.load(imageUri)
		.error(R.drawable.image_x_generic_icon)
		.into(_img);
		_img.clearColorFilter();
	}
	
	
	public void _getMP4Thumbnail(final String _path, final ImageView _img) {
		
		
		/*DEPRECATED
android.graphics.Bitmap thumb = android.media.ThumbnailUtils.createVideoThumbnail(_path, android.provider.MediaStore.Images.Thumbnails.MINI_KIND);
_img.setImageBitmap(thumb);
*/
		
		
		java.io.File file = new java.io.File(_path);
		Uri imageUri = Uri.fromFile(file);
		Glide.with(getApplicationContext())
		.load(imageUri)
		.error(R.drawable.playlist_icon)
		.into(_img);
		_img.clearColorFilter();
	}
	
	
	public void _getPdfCover(final ImageView _imageview, final String _path) {
		File file = new File(_path);
		if (!file.exists()) {
				return;
		}
		
		try (ParcelFileDescriptor fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)) {
				PdfRenderer pdfRenderer = new PdfRenderer(fileDescriptor);
				PdfRenderer.Page page = pdfRenderer.openPage(0);
				
				int width = getResources().getDisplayMetrics().densityDpi / 72 * page.getWidth();
				int height = getResources().getDisplayMetrics().densityDpi / 72 * page.getHeight();
				Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
				
				page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
				_imageview.setImageBitmap(bitmap);
				
				page.close();
				pdfRenderer.close();
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	
	public void _getAlbumCover(final ImageView _imageview, final String _path) {
		MediaMetadataRetriever mmr = new MediaMetadataRetriever();
		try {
				mmr.setDataSource(_path);
				byte[] artBytes = mmr.getEmbeddedPicture();
				if (artBytes != null) {
						Bitmap bitmap = BitmapFactory.decodeByteArray(artBytes, 0, artBytes.length);
						_imageview.setImageBitmap(bitmap);
				} else {
				}
		} catch (Exception e) {
				e.printStackTrace();
				
		} finally {
				try {
						mmr.release();
				} catch (Exception e) {
						e.printStackTrace();
				}
		}
	}
	
	
	public void _getApkIcon(final String _path, final ImageView _imageview) {
		android.content.pm.PackageManager packageManager = this.getPackageManager();
		android.content.pm.PackageInfo packageInfo = packageManager.getPackageArchiveInfo(_path, 0);
		packageInfo.applicationInfo.sourceDir = _path;
		packageInfo.applicationInfo.publicSourceDir = _path;
		_imageview.setImageDrawable(packageInfo.applicationInfo.loadIcon(packageManager));
		packageInfo = null;
		packageManager = null;
	}
	
	
	public void _DirIconPlaceholder(final ImageView _imageview, final String _path) {
		if (_path.equals("Download")) {
			_imageview.setImageResource(R.drawable.download_folder);
			_imageview.setAlpha((float)(1));
		}
		else {
			if (_path.equals("DCIM")) {
				_imageview.setImageResource(R.drawable.dcim_folder);
				_imageview.setAlpha((float)(1));
			}
			else {
				if (_path.equals("Pictures")) {
					_imageview.setImageResource(R.drawable.pictures_folder);
					_imageview.setAlpha((float)(1));
				}
				else {
					if (_path.equals(".sketchware")) {
						_imageview.setImageResource(R.drawable.sketchware_folder);
						_imageview.setAlpha((float)(0.45d));
					}
					else {
						if (_path.equals("sketchware")) {
							_imageview.setImageResource(R.drawable.sketchware_folder);
							_imageview.setAlpha((float)(1));
						}
						else {
							if (_path.equals("Movies")) {
								_imageview.setImageResource(R.drawable.movies_folder);
								_imageview.setAlpha((float)(1));
							}
							else {
								if (_path.equals("CEditor")) {
									_imageview.setImageResource(R.drawable.codexyz_folder);
									_imageview.setAlpha((float)(1));
								}
								else {
									if (_path.equals("Documents")) {
										_imageview.setImageResource(R.drawable.documents_folder);
										_imageview.setAlpha((float)(1));
									}
									else {
										if (_path.equals("Android")) {
											_imageview.setImageResource(R.drawable.android_folder);
											_imageview.setAlpha((float)(1));
										}
										else {
											if (_path.equals("Music") || _path.equals("Ringtones")) {
												_imageview.setImageResource(R.drawable.music_ringtones_folder);
												_imageview.setAlpha((float)(1));
											}
											else {
												if (_path.equals(".inflps")) {
													_imageview.setImageResource(R.drawable.hidden_inflps_folder);
													_imageview.setAlpha((float)(0.45d));
												}
												else {
													if (_path.startsWith(".")) {
														_imageview.setImageResource(R.drawable.folder);
														_imageview.setAlpha((float)(0.45d));
													}
													else {
														_imageview.setImageResource(R.drawable.folder);
														_imageview.setAlpha((float)(1));
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	public void _AppDesignerToast(final String _s) {
		// Code generated by App Designer
			DisplayMetrics screen = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(screen);
			double dp = 10;
			double logicalDensity = screen.density;
			int px = (int) Math.ceil(dp * logicalDensity);
			
			Toast customToast = Toast.makeText(FileInfoActivity.this, _s, Toast.LENGTH_SHORT);
			View customToastView = customToast.getView();
			customToastView.setBackground(new GradientDrawable() { 
					public GradientDrawable getIns(int a, int b) { 
							this.setCornerRadius(a); 
							this.setColor(b); 
							return this; 
					} 
			}.getIns(px, Color.parseColor("#181818")));
			
			TextView customToastText = customToastView.findViewById(android.R.id.message);
			customToastText.setTextColor(Color.parseColor("#ffffff"));
			customToastText.setShadowLayer(0, 0, 0, 0);
			customToast.show();
			
	}
	
	
	public void _setEllipsize(final TextView _textview) {
		_textview.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		_textview.setMarqueeRepeatLimit(-1);
		_textview.setHorizontallyScrolling(true);
		_textview.setSelected(true);
	}
	
	
	public void _gradDrawable(final String _bgColor, final String _strokeColor, final double _stroke, final double _rad, final double _elv, final boolean _rpl, final View _view) {
		gradDrawable(getApplicationContext(), _bgColor, _strokeColor, _stroke, _rad, _elv, _rpl, _view);
	}
	public static void gradDrawable(Context a, String _bgColor, String _strokeColor, double _stroke, double _rad, double _elv, boolean _rpl, View _view){
		try {
			android.graphics.drawable.GradientDrawable drawl = new android.graphics.drawable.GradientDrawable();
			int d = (int) a.getResources().getDisplayMetrics().density;
			if (!_bgColor.trim().equals("")) {
				
				drawl.setColor(Color.parseColor(_bgColor.startsWith("#")? _bgColor: "#".concat(_bgColor)));
			}
			drawl.setCornerRadius(d*((float)_rad));
			if (!_strokeColor.trim().equals("")) {
				drawl.setStroke(d * (int)_stroke,(Color.parseColor(_strokeColor.startsWith("#")? _strokeColor: "#".concat(_strokeColor))));
			}
			_view.setElevation(d*((float)_elv));
			if (_rpl) {
				android.graphics.drawable.RippleDrawable drawr = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), _bgColor.trim().equals("") ? null : drawl, null);
				_view.setBackground(drawr);
				_view.setClickable(true);
			}
			else {
				_view.setBackground(drawl);
			}
		} catch (Exception e){
			return;
		}
	}
	
	
	public void _CRadius(final View _view, final String _color) {
		android.graphics.drawable.GradientDrawable CRadius = new android.graphics.drawable.GradientDrawable();
		int dcr = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		CRadius.setColor(Color.parseColor(_color));
		CRadius.setCornerRadii(new float[]{dcr*30,dcr*30,dcr*30 ,dcr*30,dcr*30,dcr*30 ,dcr*30,dcr*30});
		_view.setBackground(CRadius);
	}
	
	
	public void _ripple(final View _view) {
		GradientDrawable roundedMask = new GradientDrawable();
		roundedMask.setShape(GradientDrawable.RECTANGLE);
		roundedMask.setColor(Color.WHITE);
		roundedMask.setCornerRadius(20f);
		ColorStateList rippleColor = new ColorStateList(
		    new int[][]{
			        new int[]{android.R.attr.state_enabled},
			        new int[]{-android.R.attr.state_enabled}
			    },
		    new int[]{Color.RED, Color.GRAY}
		);
		
		RippleDrawable rippleDrawable = new RippleDrawable(
		    rippleColor,
		    new ColorDrawable(Color.TRANSPARENT),
		    roundedMask
		);
		
		_view.setBackground(rippleDrawable);
	}
	
	
	public void _CXYZ_dialog_theme(final AlertDialog.Builder _d) {
		/*Code generated by App Designer
  Stroke by InfLps
  */
		final AlertDialog alert = _d.show();
		DisplayMetrics screen = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(screen);
		double dp = 15;
		double logicalDensity = screen.density;
		int px = (int) Math.ceil(dp * logicalDensity);
		alert.getWindow().getDecorView().setBackground(new GradientDrawable() {
				public GradientDrawable getIns(int cornerRadius, int bgColor, int strokeWidth, int strokeColor) {
						this.setCornerRadius(cornerRadius);
						this.setColor(bgColor);
						this.setStroke(strokeWidth, strokeColor);
						return this;
				}
		}.getIns(px, Color.parseColor("#111111"), (int) (1 * screen.density), Color.parseColor("#474747")));
		alert.getWindow().getDecorView().setPadding(8,8,8,8);
		alert.show();
		
		alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#FF0000"));
		alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FF0000"));
		alert.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#FF0000"));
		alert.getWindow().setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
		alert.getWindow().getDecorView().setTranslationY(-20);
		TextView textT = (TextView)alert.getWindow().getDecorView().findViewById(android.R.id.message);
		textT.setTextColor(Color.parseColor("#f9f9f9"));
		
		int titleId = getResources().getIdentifier( "alertTitle", "id", "android" ); 
		
		if (titleId > 0) { 
				TextView dialogTitle = (TextView) alert.getWindow().getDecorView().findViewById(titleId); 
				
				if (dialogTitle != null) {
						dialogTitle.setTextColor(Color.parseColor("#FFFFFF"));
				} 
		}
		textT.setTextIsSelectable(true);
	}
	
	
	public void _refresh(final String _path) {
		path = _path;
		File file = new File(path);
		new FileInfoTask().execute(file);
		fname = getFileName(path);
		fparent = file.getParent();
		type = file.isDirectory() ? "Folder" : "File";
		mimeType = getMIMEType(file);
		fileIsHidden = file.isHidden();
		filename.setText(fname);
		textview10.setText(type);
		_FileIconPlaceholder(imageview1, path);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
