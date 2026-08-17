package com.ceditor;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class PreviewArchiveFilesActivity extends AppCompatActivity {
	
	private String path = "";
	private HashMap<String, Object> file_info_map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> filelist = new ArrayList<>();
	private ArrayList<String> liststring = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout top_line;
	private LinearLayout listview_container;
	private LinearLayout linear4;
	private LinearLayout linear2;
	private TextView title;
	private TextView subtitle;
	private ListView listview1;
	private TextView textview8;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.preview_archive_files);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		top_line = findViewById(R.id.top_line);
		listview_container = findViewById(R.id.listview_container);
		linear4 = findViewById(R.id.linear4);
		linear2 = findViewById(R.id.linear2);
		title = findViewById(R.id.title);
		subtitle = findViewById(R.id.subtitle);
		listview1 = findViewById(R.id.listview1);
		textview8 = findViewById(R.id.textview8);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
			}
		});
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
				return true;
			}
		});
		
		textview8.setOnClickListener(new View.OnClickListener() {
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
		
		
		linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)1, 0xFFE0E0E0, 0xFF111111));
		_dialogTheme();
		path = getIntent().getStringExtra("path");
		listview1.setAdapter(new Listview1Adapter(filelist));
		_refresh(path, liststring, filelist);
		title.setText(getFileName(path));
		subtitle.setText(path);
		_setEllipsize(title);
		_setEllipsize(subtitle);
		_ripple(textview8);
	}
	
	@Override
	public void onBackPressed() {
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
			
		}
		super.setContentView(layoutResID);  
	}
	
	
	public void _refresh(final String _path, final ArrayList<String> _listString, final ArrayList<HashMap<String, Object>> _listMap) {
		_listMap.clear();
		ArrayList<String> files = new ArrayList<String>();
		try {
			java.util.zip.ZipFile zip = new java.util.zip.ZipFile(_path);
			java.util.Enumeration<? extends java.util.zip.ZipEntry> zipEntries = zip.entries();
			while(zipEntries.hasMoreElements()) {
				java.util.zip.ZipEntry entry = zipEntries.nextElement();
				String fileName = entry.getName();
				files.add(fileName);
			}
			for(int _repeat26 = 0; _repeat26 < (int)(files.size()); _repeat26++) {
				String fileName = files.get(_repeat26); 
				java.util.zip.ZipEntry entry = zip.getEntry(fileName);
				if (entry != null) {
					double num = entry.getSize();
					file_info_map = new HashMap<>();
					file_info_map.put("name", getFileName(fileName));
					if (entry.isDirectory()) {
						file_info_map.put("size", "Folder");
						file_info_map.put("dir", "true");
					}
					else {
						file_info_map.put("size", getReadableSize(num));
						file_info_map.put("dir", "false");
					}
					file_info_map.put("size_raw", (long) num);
					file_info_map.put("date", new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date(entry.getTime())));
					file_info_map.put("date_raw", entry.getTime());
					file_info_map.put("path", fileName);
					_listMap.add(file_info_map);
				}
			}
			zip.close();
		} catch (Exception e){
			SketchwareUtil.showMessage(getApplicationContext(), e.toString());
		}
	}
	public String getFileName(String path) {
			if (path == null || path.isEmpty()) return "";
			if (path.endsWith("/")) {
					path = path.substring(0, path.length() - 1);
			}
			int lastIndex = path.lastIndexOf('/');
			if (lastIndex != -1) {
					return path.substring(lastIndex + 1);
			} else {
					return path;
			}
	}
	
	public List<String> getDirectoryContents(String directoryPath, java.util.zip.ZipFile zip) {
			List<String> directoryContents = new ArrayList<>();
			try {
					java.util.Enumeration<? extends java.util.zip.ZipEntry> zipEntries = zip.entries();
					while (zipEntries.hasMoreElements()) {
							java.util.zip.ZipEntry entry = zipEntries.nextElement();
							String entryName = entry.getName();
							if (entryName.startsWith(directoryPath) && !entryName.equals(directoryPath)) {
									directoryContents.add(entryName);
							}
					}
			} catch (Exception e) {
					e.printStackTrace();
			}
			return directoryContents;
	}
	
	public String getReadableSize(double size){
		if (size == 0) return "0 B";
		if (size < Math.pow(2, 10)) {
			return String.valueOf((long)size).concat(" B");
		}
		if (size < Math.pow(2, 20)) {
			return new DecimalFormat("#.#").format(size / Math.pow(2, 10)).concat(" KB");


		}
		if (size < Math.pow(2, 30)) {
			return new DecimalFormat("#.#").format(size / Math.pow(2, 20)).concat(" MB");


		}
		if (size < Math.pow(2, 40)) {
			return new DecimalFormat("#.#").format(size / Math.pow(2, 30)).concat(" GB");


		}
		if (size < Math.pow(2, 50)) {
			return new DecimalFormat("#.#").format(size / Math.pow(2, 40)).concat(" TB");


		}
		return "Unknown";
	}
	
	
	public void _setEllipsize(final TextView _textview) {
		_textview.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		_textview.setMarqueeRepeatLimit(-1);
		_textview.setHorizontallyScrolling(true);
		_textview.setSelected(true);
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
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.archvitem, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final TextView badge = _view.findViewById(R.id.badge);
			final ImageView picture = _view.findViewById(R.id.picture);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout linear11 = _view.findViewById(R.id.linear11);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView length_and_type = _view.findViewById(R.id.length_and_type);
			
			textview1.setText(_data.get((int)_position).get("name").toString());
			_setEllipsize(badge);
			_setEllipsize(textview1);
			if (_data.get((int)_position).get("dir").toString().equals("true")) {
				picture.setImageResource(R.drawable.folderyellowlocked_92838);
				length_and_type.setText("<DIR>\n".concat(_data.get((int)_position).get("date").toString()));
				badge.setVisibility(View.GONE);
			}
			else {
				picture.setImageResource(R.drawable.app_pgp_encrypted_icon);
				length_and_type.setText(_data.get((int)_position).get("size").toString().concat("\n".concat(_data.get((int)_position).get("date").toString())));
				badge.setVisibility(View.VISIBLE);
				String fullPath = filelist.get((int)_position).get("path").toString();
				String lastPathSegment = Uri.parse(fullPath).getLastPathSegment();
				if (lastPathSegment != null && lastPathSegment.contains(".")) {
					String fileExtension = lastPathSegment.substring(lastPathSegment.lastIndexOf(".") + 1);
					badge.setText(fileExtension);
				} else {
					badge.setText("~");
				}
			}
			
			return _view;
		}
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
