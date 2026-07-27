package com.ceditor;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.android.view.materialrefreshlayout.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.robinhood.ticker.*;
import com.theartofdev.edmodo.cropper.*;
import com.zolad.zoominimageview.*;
import io.github.rosemoe.sora.*;
import io.github.rosemoe.sora.langs.textmate.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import android.content.Context;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import androidx.core.content.FileProvider;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Stack;

import io.github.rosemoe.sora.lang.Language;
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage;
import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import org.eclipse.tm4e.core.internal.theme.reader.ThemeReader;
import org.eclipse.tm4e.core.theme.IRawTheme;
import io.github.rosemoe.sora.widget.SymbolInputView;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.text.Content;
import org.json.JSONObject;
import org.json.JSONArray;
import androidx.viewpager.widget.ViewPager;
import android.text.Editable;



public class EditorActivity extends AppCompatActivity {
	
	private boolean installed;
	private boolean htmlWindow = false;
	private boolean retainSession;
	private boolean isTextWatcherEnabled = true;
	private boolean isKeyboardVisible = false;
	
	private Handler handler = new Handler();
	private String lastText = "";
	private String originalContent = "";
	private int screenWidth, screenHeight, themeState, langState;
	private EditorColorScheme colorScheme;
	private ViewPager viewPager;
	
	private final Stack<String> undoStack = new Stack<>();
	private final Stack<String> redoStack = new Stack<>();
	
	private static final int PICK_TXT_FILE = 1001;
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private boolean landscape = false;
	private boolean hidden = false;
	private boolean menuPannelVisible = false;
	private double current_cursor = 0;
	private double end_cursor = 0;
	private boolean readOnly = false;
	private String responseTAG = "";
	
	private ArrayList<HashMap<String, Object>> binmap = new ArrayList<>();
	
	private LinearLayout toolbar1;
	private FrameLayout baseBackground;
	private LinearLayout titleToolbar;
	private ImageView run;
	private ImageView insert;
	private ImageView save;
	private ImageView undo;
	private ImageView redo;
	private ImageView more;
	private HorizontalScrollView horizontalScrollView;
	private TextView path;
	private LinearLayout LinearLayout4;
	private EditText filename;
	private LinearLayout l1;
	private LinearLayout web_preview;
	private LinearLayout l2;
	private CodeEditor editor;
	private LinearLayout webtoolbar;
	private FrameLayout framelayoutwebview;
	private LinearLayout resize_win;
	private TextView TextView50;
	private ImageView imageview1;
	private ImageView imageview17;
	private ImageView more_w;
	private WebView WebView1;
	private LinearLayout window_alpha_container;
	private SeekBar window_alpha;
	private TextView close_window_alpha;
	private ImageView l;
	private ImageView c;
	private ImageView r;
	private LinearLayout symb;
	private LinearLayout menu;
	private HorizontalScrollView HorizontalScrollView1;
	private SymbolInputView inputView;
	private LinearLayout LinearLayout17;
	private LinearLayout LinearLayout5;
	private LinearLayout b1;
	private LinearLayout LinearLayout6;
	private ImageView ImageView1;
	private TextView TextView2;
	private LinearLayout main;
	private LinearLayout second;
	private ScrollView ScrollView1;
	private LinearLayout LinearLayout7;
	private LinearLayout m1;
	private LinearLayout m2;
	private LinearLayout m3;
	private LinearLayout m4;
	private LinearLayout m5;
	private LinearLayout m6;
	private LinearLayout m7;
	private LinearLayout m8;
	private LinearLayout m9;
	private LinearLayout m10;
	private LinearLayout m11;
	private ImageView imageview2;
	private TextView textview38;
	private ImageView imageview7;
	private TextView textview39;
	private ImageView imageview8;
	private TextView textview40;
	private ImageView imageview9;
	private TextView textview41;
	private ImageView imageview10;
	private TextView textview42;
	private ImageView imageview11;
	private TextView textview43;
	private ImageView imageview12;
	private TextView textview44;
	private ImageView imageview13;
	private TextView textview45;
	private ImageView imageview14;
	private TextView textview46;
	private ImageView imageview15;
	private TextView textview47;
	private ImageView imageview16;
	private TextView textview48;
	private ScrollView ScrollView2;
	private LinearLayout LinearLayout16;
	private LinearLayout m1_2;
	private LinearLayout m2_2;
	private LinearLayout m2_3;
	private LinearLayout m2_4;
	private LinearLayout m2_5;
	private LinearLayout m2_6;
	private LinearLayout m2_7;
	private LinearLayout m2_8;
	private ImageView imageview18;
	private TextView textview49;
	private ImageView imageview19;
	private TextView textview50;
	private ImageView imageview47;
	private Switch wrapTextSwitch;
	private ImageView imageview49;
	private Switch numberDividerSwitch;
	private ImageView imageview53;
	private Switch ligatureSwitch;
	private ImageView imageview54;
	private Switch highlightBracketPairSwitch;
	private ImageView imageview55;
	private Switch fastScrollSwitch;
	private ImageView imageview56;
	private Switch pinNumberDividerSwitch;
	
	private AlertDialog.Builder alert1;
	private Intent i = new Intent();
	private AlertDialog.Builder confirmation;
	private SharedPreferences tdata;
	private SharedPreferences tmtheme;
	private AlertDialog.Builder theme;
	private AlertDialog.Builder syntaxes;
	private SharedPreferences data;
	private Calendar timeFormat = Calendar.getInstance();
	private AlertDialog.Builder timeFormats;
	private RequestNetwork sourcecode;
	private RequestNetwork.RequestListener _sourcecode_request_listener;
	private AlertDialog.Builder listMapTextData;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.editor);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = findViewById(R.id._fab);
		
		toolbar1 = findViewById(R.id.toolbar1);
		baseBackground = findViewById(R.id.baseBackground);
		titleToolbar = findViewById(R.id.titleToolbar);
		run = findViewById(R.id.run);
		insert = findViewById(R.id.insert);
		save = findViewById(R.id.save);
		undo = findViewById(R.id.undo);
		redo = findViewById(R.id.redo);
		more = findViewById(R.id.more);
		horizontalScrollView = findViewById(R.id.horizontalScrollView);
		path = findViewById(R.id.path);
		LinearLayout4 = findViewById(R.id.LinearLayout4);
		filename = findViewById(R.id.filename);
		l1 = findViewById(R.id.l1);
		web_preview = findViewById(R.id.web_preview);
		l2 = findViewById(R.id.l2);
		editor = findViewById(R.id.editor);
		webtoolbar = findViewById(R.id.webtoolbar);
		framelayoutwebview = findViewById(R.id.framelayoutwebview);
		resize_win = findViewById(R.id.resize_win);
		TextView50 = findViewById(R.id.TextView50);
		imageview1 = findViewById(R.id.imageview1);
		imageview17 = findViewById(R.id.imageview17);
		more_w = findViewById(R.id.more_w);
		WebView1 = findViewById(R.id.WebView1);
		WebView1.getSettings().setJavaScriptEnabled(true);
		WebView1.getSettings().setSupportZoom(true);
		window_alpha_container = findViewById(R.id.window_alpha_container);
		window_alpha = findViewById(R.id.window_alpha);
		close_window_alpha = findViewById(R.id.close_window_alpha);
		l = findViewById(R.id.l);
		c = findViewById(R.id.c);
		r = findViewById(R.id.r);
		symb = findViewById(R.id.symb);
		menu = findViewById(R.id.menu);
		HorizontalScrollView1 = findViewById(R.id.HorizontalScrollView1);
		inputView = findViewById(R.id.inputView);
		LinearLayout17 = findViewById(R.id.LinearLayout17);
		LinearLayout5 = findViewById(R.id.LinearLayout5);
		b1 = findViewById(R.id.b1);
		LinearLayout6 = findViewById(R.id.LinearLayout6);
		ImageView1 = findViewById(R.id.ImageView1);
		TextView2 = findViewById(R.id.TextView2);
		main = findViewById(R.id.main);
		second = findViewById(R.id.second);
		ScrollView1 = findViewById(R.id.ScrollView1);
		LinearLayout7 = findViewById(R.id.LinearLayout7);
		m1 = findViewById(R.id.m1);
		m2 = findViewById(R.id.m2);
		m3 = findViewById(R.id.m3);
		m4 = findViewById(R.id.m4);
		m5 = findViewById(R.id.m5);
		m6 = findViewById(R.id.m6);
		m7 = findViewById(R.id.m7);
		m8 = findViewById(R.id.m8);
		m9 = findViewById(R.id.m9);
		m10 = findViewById(R.id.m10);
		m11 = findViewById(R.id.m11);
		imageview2 = findViewById(R.id.imageview2);
		textview38 = findViewById(R.id.textview38);
		imageview7 = findViewById(R.id.imageview7);
		textview39 = findViewById(R.id.textview39);
		imageview8 = findViewById(R.id.imageview8);
		textview40 = findViewById(R.id.textview40);
		imageview9 = findViewById(R.id.imageview9);
		textview41 = findViewById(R.id.textview41);
		imageview10 = findViewById(R.id.imageview10);
		textview42 = findViewById(R.id.textview42);
		imageview11 = findViewById(R.id.imageview11);
		textview43 = findViewById(R.id.textview43);
		imageview12 = findViewById(R.id.imageview12);
		textview44 = findViewById(R.id.textview44);
		imageview13 = findViewById(R.id.imageview13);
		textview45 = findViewById(R.id.textview45);
		imageview14 = findViewById(R.id.imageview14);
		textview46 = findViewById(R.id.textview46);
		imageview15 = findViewById(R.id.imageview15);
		textview47 = findViewById(R.id.textview47);
		imageview16 = findViewById(R.id.imageview16);
		textview48 = findViewById(R.id.textview48);
		ScrollView2 = findViewById(R.id.ScrollView2);
		LinearLayout16 = findViewById(R.id.LinearLayout16);
		m1_2 = findViewById(R.id.m1_2);
		m2_2 = findViewById(R.id.m2_2);
		m2_3 = findViewById(R.id.m2_3);
		m2_4 = findViewById(R.id.m2_4);
		m2_5 = findViewById(R.id.m2_5);
		m2_6 = findViewById(R.id.m2_6);
		m2_7 = findViewById(R.id.m2_7);
		m2_8 = findViewById(R.id.m2_8);
		imageview18 = findViewById(R.id.imageview18);
		textview49 = findViewById(R.id.textview49);
		imageview19 = findViewById(R.id.imageview19);
		textview50 = findViewById(R.id.textview50);
		imageview47 = findViewById(R.id.imageview47);
		wrapTextSwitch = findViewById(R.id.wrapTextSwitch);
		imageview49 = findViewById(R.id.imageview49);
		numberDividerSwitch = findViewById(R.id.numberDividerSwitch);
		imageview53 = findViewById(R.id.imageview53);
		ligatureSwitch = findViewById(R.id.ligatureSwitch);
		imageview54 = findViewById(R.id.imageview54);
		highlightBracketPairSwitch = findViewById(R.id.highlightBracketPairSwitch);
		imageview55 = findViewById(R.id.imageview55);
		fastScrollSwitch = findViewById(R.id.fastScrollSwitch);
		imageview56 = findViewById(R.id.imageview56);
		pinNumberDividerSwitch = findViewById(R.id.pinNumberDividerSwitch);
		alert1 = new AlertDialog.Builder(this);
		confirmation = new AlertDialog.Builder(this);
		tdata = getSharedPreferences("tdata", Activity.MODE_PRIVATE);
		tmtheme = getSharedPreferences("tmtheme", Activity.MODE_PRIVATE);
		theme = new AlertDialog.Builder(this);
		syntaxes = new AlertDialog.Builder(this);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		timeFormats = new AlertDialog.Builder(this);
		sourcecode = new RequestNetwork(this);
		listMapTextData = new AlertDialog.Builder(this);
		
		run.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (htmlWindow) {
					htmlWindow = false;
					hideV(web_preview);
					stopTextWatcher();
					run.setImageResource(R.drawable.content_play);
				}
				else {
					htmlWindow = true;
					showV(web_preview);
					startTextWatcher();
					run.setImageResource(R.drawable.content_stop);
				}
			}
		});
		
		insert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				View toolbarInsertpopupView = getLayoutInflater().inflate(R.layout.editorinsert, null);
				final PopupWindow toolbarinsertpopupview = new PopupWindow(toolbarInsertpopupView, 
				        ViewGroup.LayoutParams.WRAP_CONTENT, 
				        ViewGroup.LayoutParams.WRAP_CONTENT, 
				        true);
				LinearLayout background = toolbarInsertpopupView.findViewById(R.id.background);
				ScrollView scroll = toolbarInsertpopupView.findViewById(R.id.scroll);
				LinearLayout container = toolbarInsertpopupView.findViewById(R.id.container);
				LinearLayout o1 = toolbarInsertpopupView.findViewById(R.id.option1);
				ImageView o1ic = toolbarInsertpopupView.findViewById(R.id.option1_icon);
				TextView o1tx = toolbarInsertpopupView.findViewById(R.id.option1_text);
				LinearLayout o2 = toolbarInsertpopupView.findViewById(R.id.option2);
				ImageView o2ic = toolbarInsertpopupView.findViewById(R.id.option2_icon);
				TextView o2tx = toolbarInsertpopupView.findViewById(R.id.option2_text);
				LinearLayout o3 = toolbarInsertpopupView.findViewById(R.id.option3);
				ImageView o3ic = toolbarInsertpopupView.findViewById(R.id.option3_icon);
				TextView o3tx = toolbarInsertpopupView.findViewById(R.id.option3_text);
				LinearLayout o4 = toolbarInsertpopupView.findViewById(R.id.option4);
				ImageView o4ic = toolbarInsertpopupView.findViewById(R.id.option4_icon);
				TextView o4tx = toolbarInsertpopupView.findViewById(R.id.option4_text);
				LinearLayout o5 = toolbarInsertpopupView.findViewById(R.id.option5);
				ImageView o5ic = toolbarInsertpopupView.findViewById(R.id.option5_icon);
				TextView o5tx = toolbarInsertpopupView.findViewById(R.id.option5_text);
				LinearLayout o6 = toolbarInsertpopupView.findViewById(R.id.option6);
				ImageView o6ic = toolbarInsertpopupView.findViewById(R.id.option6_icon);
				TextView o6tx = toolbarInsertpopupView.findViewById(R.id.option6_text);
				LinearLayout s = toolbarInsertpopupView.findViewById(R.id.separator);
				LinearLayout o7 = toolbarInsertpopupView.findViewById(R.id.option7);
				ImageView o7ic = toolbarInsertpopupView.findViewById(R.id.option7_icon);
				TextView o7tx = toolbarInsertpopupView.findViewById(R.id.option7_text);
				TextView an = toolbarInsertpopupView.findViewById(R.id.appname);
				if (data.getString("clipboard", "").equals("false")) {
					o6.setVisibility(View.GONE);
				}
				_gradDrawable("#111111", "#474747", 1, 15, 5, false, background);
				_ripple(o1);
				_ripple(o2);
				_ripple(o3);
				_ripple(o4);
				_ripple(o5);
				_ripple(o6);
				_ripple(o7);
				
				o1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_showTimeFormatDialog();
					}
				});
				
				o2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_showColorPickerDialog();
					}
				});
				
				o3.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_showWebViewDialog();
					}
				});
				
				o4.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_showBase64ConvDialog();
					}
				});
				
				o5.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						_showBinConvDialog();
					}
				});
				
				o6.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						getClipboardText(EditorActivity.this);
					}
				});
				
				o7.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						pickTextFile();
					}
				});
				
				toolbarinsertpopupview.setAnimationStyle(android.R.style.Animation_Dialog);
				toolbarinsertpopupview.setBackgroundDrawable(new BitmapDrawable());
				toolbarinsertpopupview.setOutsideTouchable(true);
				toolbarinsertpopupview.showAsDropDown(insert, 0, 0);
			}
		});
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String path = getIntent().getStringExtra("path");
				String title = filename.getText().toString();
				if (path == null || title == null) {
					_AppDesignerToast("Invalid file parameters.");
					return;
				}
				if (!path.endsWith("/")) {
					path += "/";
				}
				String userFilePath = path + title;
				String userTextContent = editor.getText().toString();
				try{
					saveFile(userFilePath, userTextContent);
					originalContent = userTextContent;
					_AppDesignerToast("File saved: " + userFilePath);
				}catch(IOException e){
					_AppDesignerToast("Failed to save file!" + e.getMessage());
					e.printStackTrace();
				}catch(Exception e){
					_AppDesignerToast("Unexpected error: " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
		
		undo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				performUndo();
			}
		});
		
		redo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				performRedo();
			}
		});
		
		more.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				View toolbarMorepopupView = getLayoutInflater().inflate(R.layout.editormoreoptions, null);
				final PopupWindow toolbarmorepopupview = new PopupWindow(toolbarMorepopupView, 
				        ViewGroup.LayoutParams.WRAP_CONTENT, 
				        ViewGroup.LayoutParams.WRAP_CONTENT, 
				        true);
				LinearLayout background = toolbarMorepopupView.findViewById(R.id.background);
				ScrollView scroll = toolbarMorepopupView.findViewById(R.id.scroll);
				LinearLayout container = toolbarMorepopupView.findViewById(R.id.container);
				LinearLayout o1 = toolbarMorepopupView.findViewById(R.id.option1);
				ImageView o1ic = toolbarMorepopupView.findViewById(R.id.option1_icon);
				TextView o1tx = toolbarMorepopupView.findViewById(R.id.option1_text);
				LinearLayout o2 = toolbarMorepopupView.findViewById(R.id.option2);
				ImageView o2ic = toolbarMorepopupView.findViewById(R.id.option2_icon);
				TextView o2tx = toolbarMorepopupView.findViewById(R.id.option2_text);
				LinearLayout o3 = toolbarMorepopupView.findViewById(R.id.option3);
				ImageView o3ic = toolbarMorepopupView.findViewById(R.id.option3_icon);
				TextView o3tx = toolbarMorepopupView.findViewById(R.id.option3_text);
				CheckBox o3cb = toolbarMorepopupView.findViewById(R.id.option3_checkbox);
				LinearLayout o4 = toolbarMorepopupView.findViewById(R.id.option4);
				ImageView o4ic = toolbarMorepopupView.findViewById(R.id.option4_icon);
				TextView o4tx = toolbarMorepopupView.findViewById(R.id.option4_text);
				LinearLayout o5 = toolbarMorepopupView.findViewById(R.id.option5);
				ImageView o5ic = toolbarMorepopupView.findViewById(R.id.option5_icon);
				TextView o5tx = toolbarMorepopupView.findViewById(R.id.option5_text);
				CheckBox o5cb = toolbarMorepopupView.findViewById(R.id.option5_checkbox);
				LinearLayout o6 = toolbarMorepopupView.findViewById(R.id.option6);
				ImageView o6ic = toolbarMorepopupView.findViewById(R.id.option6_icon);
				TextView o6tx = toolbarMorepopupView.findViewById(R.id.option6_text);
				CheckBox o6cb = toolbarMorepopupView.findViewById(R.id.option6_checkbox);
				LinearLayout o7 = toolbarMorepopupView.findViewById(R.id.option7);
				ImageView o7ic = toolbarMorepopupView.findViewById(R.id.option7_icon);
				TextView o7tx = toolbarMorepopupView.findViewById(R.id.option7_text);
				CheckBox o7cb = toolbarMorepopupView.findViewById(R.id.option7_checkbox);
				LinearLayout o8 = toolbarMorepopupView.findViewById(R.id.option8);
				ImageView o8ic = toolbarMorepopupView.findViewById(R.id.option8_icon);
				TextView o8tx = toolbarMorepopupView.findViewById(R.id.option8_text);
				TextView an = toolbarMorepopupView.findViewById(R.id.appname);
				_gradDrawable("#111111", "#474747", 1, 15, 5, false, background);
				_ripple(o1);
				_ripple(o2);
				_ripple(o3);
				_ripple(o4);
				_ripple(o5);
				_ripple(o6);
				_ripple(o7);
				_ripple(o8);
				o3cb.setChecked(htmlWindow);
				o5cb.setChecked(tdata.getString("rse", "").equals("true"));
				o6cb.setChecked(readOnly);
				o7cb.setChecked(data.getString("symbol_tabs", "").equals("true"));
				
				o1.setOnClickListener(new OnClickListener() { 
					public void onClick(View view) {
						m1_2.performClick();
						toolbarmorepopupview.dismiss();
					}
				});
				
				o2.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						m2_2.performClick();
						toolbarmorepopupview.dismiss();
					}
				});
				
				o3.setOnClickListener(new OnClickListener() { 
					public void onClick(View view) {
						o3cb.performClick();
						toolbarmorepopupview.dismiss();
					}
				});
				
				o3cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
						final boolean _isChecked = _param2;
								
						if (_isChecked) {
							htmlWindow = true;
							showV(web_preview);
							startTextWatcher();
							run.setImageResource(R.drawable.content_stop);
						}
						else {
							htmlWindow = false;
							hideV(web_preview);
							stopTextWatcher();
							run.setImageResource(R.drawable.content_play);
						}
						toolbarmorepopupview.dismiss();
					}
				});
				
				o4.setOnClickListener(new OnClickListener() { 
					public void onClick(View view) {
						String code = editor.getText().toString();
						showTextStatistics(code, EditorActivity.this);
						toolbarmorepopupview.dismiss();
					}
				});
				
				o5.setOnClickListener(new OnClickListener() { 
					public void onClick(View view) {
						o5cb.performClick();
						toolbarmorepopupview.dismiss();
					}
				});
				
				o5cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
						final boolean _isChecked = _param2;
						if (_isChecked) {
							retainSession = true;
							tdata.edit().putString("rse", "true").commit();
							startBackup();
						}
						else {
							retainSession = false;
							tdata.edit().putString("rse", "false").commit();
							stopBackup();
						}
						toolbarmorepopupview.dismiss();
					}
				});
				
				o6.setOnClickListener(new OnClickListener() { 
					public void onClick(View view) {
						o6cb.performClick();
						toolbarmorepopupview.dismiss();
					}
				});
				
				o6cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
						final boolean _isChecked = _param2;
								
						if (_isChecked) {
							readOnly = true;
							editor.setEnabled(false);
							filename.setEnabled(false);
						}
						else {
							readOnly = false;
							editor.setEnabled(true);
							filename.setEnabled(true);
						}
						toolbarmorepopupview.dismiss();
					}
				});
				
				o7.setOnClickListener(new OnClickListener() { 
					public void onClick(View view) {
						o7cb.performClick();
						toolbarmorepopupview.dismiss();
					}
				});
				
				o7cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
						final boolean _isChecked = _param2;
						if (_isChecked) {
							data.edit().putString("symbol_tabs", "true").commit();
							symb.setVisibility(View.VISIBLE);
						}
						else {
							data.edit().putString("symbol_tabs", "false").commit();
							symb.setVisibility(View.GONE);
						}
						toolbarmorepopupview.dismiss();
					}
				});
				
				o8.setOnClickListener(new OnClickListener() {
					public void onClick(View view) {
						onBackPressed();
						toolbarmorepopupview.dismiss();
					}
				});
				
				toolbarmorepopupview.setAnimationStyle(android.R.style.Animation_Dialog);
				toolbarmorepopupview.setBackgroundDrawable(new BitmapDrawable());
				toolbarmorepopupview.setOutsideTouchable(true);
				toolbarmorepopupview.showAsDropDown(more, 0, 0);
			}
		});
		
		editor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (menu.getVisibility() == View.VISIBLE) {
					_showMenu(false);
				}
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (WebView1.canGoBack()) {
					WebView1.goBack();
				}
			}
		});
		
		imageview17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (WebView1.canGoForward()) {
					WebView1.goForward();
				}
			}
		});
		
		more_w.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				View webPreviewpopupView = getLayoutInflater().inflate(R.layout.webprevieweroptions, null);
				final PopupWindow webpreviewpopupview = new PopupWindow(webPreviewpopupView, 
				        ViewGroup.LayoutParams.WRAP_CONTENT, 
				        ViewGroup.LayoutParams.WRAP_CONTENT, 
				        true);
				LinearLayout background = webPreviewpopupView.findViewById(R.id.background);
				ScrollView scroll = webPreviewpopupView.findViewById(R.id.scroll);
				LinearLayout container = webPreviewpopupView.findViewById(R.id.container);
				LinearLayout o1 = webPreviewpopupView.findViewById(R.id.option1);
				ImageView o1ic = webPreviewpopupView.findViewById(R.id.option1_icon);
				TextView o1tx = webPreviewpopupView.findViewById(R.id.option1_text);
				LinearLayout o2 = webPreviewpopupView.findViewById(R.id.option2);
				ImageView o2ic = webPreviewpopupView.findViewById(R.id.option2_icon);
				TextView o2tx = webPreviewpopupView.findViewById(R.id.option2_text);
				TextView an = webPreviewpopupView.findViewById(R.id.appname);
				_gradDrawable("#111111", "#474747", 1, 15, 5, false, background);
				_ripple(o1);
				_ripple(o2);
				
				o1.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						window_alpha_container.setVisibility(View.VISIBLE);
						webpreviewpopupview.dismiss();
					} });
				
				o2.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						htmlWindow = false;
						run.setImageResource(R.drawable.content_play);
						hideV(web_preview);
						stopTextWatcher();
						webpreviewpopupview.dismiss();
					} });
				
				webpreviewpopupview.setAnimationStyle(android.R.style.Animation_Dialog);
				webpreviewpopupview.setBackgroundDrawable(new BitmapDrawable());
				webpreviewpopupview.setOutsideTouchable(true);
				webpreviewpopupview.showAsDropDown(more_w, 0, 0);
			}
		});
		
		WebView1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		window_alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				if (_progressValue < 25) {
					window_alpha.setProgress((int)25);
				}
				else {
					web_preview.setAlpha((float)(_progressValue / 100.0));
				}
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				
			}
		});
		
		close_window_alpha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				window_alpha_container.setVisibility(View.GONE);
			}
		});
		
		m1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				saveStateForUndo();
				String text = editor.getText().toString();
				text = reverseText(text, true, true);
				editor.setText(text);
			}
		});
		
		m2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (editor != null && editor.getText() != null) {
					String content = editor.getText().toString().trim();
					if (!content.isEmpty()) {
						saveStateForUndo();
						String jsonData = editor.getText().toString();
						final int indent_width = 4;
						final String newline = System.lineSeparator();
						final StringBuilder ret = new StringBuilder();
						
						new Thread(() -> {
								int indent = 0;
								boolean inQuotes = false;
								char[] chars = jsonData.toCharArray();
								
								for (int i = 0; i < chars.length; i++) {
										char c = chars[i];
										
										if (c == '\"') {
												ret.append(c);
												inQuotes = !inQuotes;
												continue;
										}
										
										if (!inQuotes) {
												switch (c) {
														case '{':
														case '[':
														ret.append(c).append(newline).append(" ".repeat(indent += indent_width));
														continue;
														case '}':
														case ']':
														ret.append(newline).append(" ".repeat(indent -= indent_width)).append(c);
														continue;
														case ':':
														ret.append(c).append(" ");
														continue;
														case ',':
														ret.append(c).append(newline).append(" ".repeat(indent));
														continue;
														default:
														if (Character.isWhitespace(c)) continue;
												}
										}
										
										ret.append(c);
										if (c == '\\' && i + 1 < chars.length) {
												ret.append(chars[++i]);
										}
								}
								
								
								runOnUiThread(() -> editor.setText(ret.toString()));
						}).start();
					}
				}
			}
		});
		
		m3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (editor != null && editor.getText() != null) {
					String content = editor.getText().toString().trim();
					if (!content.isEmpty()) {
						saveStateForUndo();
						String javaCode = editor.getText().toString();
						
						final char[] chars = javaCode.toCharArray();
						final String newline = "\n";
						final StringBuilder formattedCode = new StringBuilder(chars.length);
						
						int indent = 0;
						boolean inQuotes = false;
						boolean inSingleLineComment = false;
						boolean inMultiLineComment = false;
						boolean lastWasNewline = true;
						boolean lastWasSpace = false; 
						
						String[] indentCache = new String[100];
						for (int i = 0; i < indentCache.length; i++) {
							    indentCache[i] = generateIndent(i * 4);
						}
						
						for (int i = 0; i < chars.length; i++) {
							    char c = chars[i];
							
							    if (c == '\"' && !inSingleLineComment && !inMultiLineComment) {
								        if (i == 0 || chars[i - 1] != '\\') {
									            inQuotes = !inQuotes;
									        }
								    }
							
							    if (!inQuotes && !inMultiLineComment && c == '/' && i + 1 < chars.length && chars[i + 1] == '/') {
								        inSingleLineComment = true;
								    }
							    if (inSingleLineComment && c == '\n') {
								        inSingleLineComment = false;
								    }
							
							    if (!inQuotes && !inSingleLineComment && c == '/' && i + 1 < chars.length && chars[i + 1] == '*') {
								        inMultiLineComment = true;
								    }
							    if (inMultiLineComment && c == '*' && i + 1 < chars.length && chars[i + 1] == '/') {
								        inMultiLineComment = false;
								        i++;
								    }
							
							    if (!inQuotes && !inSingleLineComment && !inMultiLineComment) {
								        switch (c) {
									            case '{':
									                if (!lastWasNewline) {
										                    formattedCode.append(' ');
										                }
									                formattedCode.append('{').append(newline);
									                indent += 4;
									                if (indent < indentCache.length) {
										                    formattedCode.append(indentCache[indent / 4]);
										                } else {
										                    formattedCode.append(generateIndent(indent));
										                }
									                lastWasNewline = true;
									                lastWasSpace = false;
									                break;
									
									            case '}':
									                indent = Math.max(0, indent - 4);
									                if (!lastWasNewline) {
										                    formattedCode.append(newline);
										                }
									                if (indent < indentCache.length) {
										                    formattedCode.append(indentCache[indent / 4]);
										                } else {
										                    formattedCode.append(generateIndent(indent));
										                }
									                formattedCode.append('}');
									                lastWasNewline = false;
									                lastWasSpace = false;
									                break;
									
									            case ';':
									                formattedCode.append(';').append(newline);
									                if (indent < indentCache.length) {
										                    formattedCode.append(indentCache[indent / 4]);
										                } else {
										                    formattedCode.append(generateIndent(indent));
										                }
									                lastWasNewline = true;
									                lastWasSpace = false;
									                break;
									
									            case '\n':
									                if (!lastWasNewline) {
										                    formattedCode.append(newline);
										                    lastWasNewline = true;
										                    lastWasSpace = false;
										                }
									                break;
									
									            case ' ':
									                if (!lastWasSpace && !lastWasNewline) {
										                    formattedCode.append(c);
										                    lastWasSpace = true;
										                }
									                break;
									
									            default:
									                formattedCode.append(c);
									                lastWasNewline = false;
									                lastWasSpace = false;
									        }
								    } else {
								        formattedCode.append(c);
								        if (c == '\n') {
									            lastWasNewline = true;
									            lastWasSpace = false;
									        } else if (c == ' ') {
									            lastWasSpace = true;
									        } else {
									            lastWasNewline = false;
									            lastWasSpace = false;
									        }
								    }
						}
						
						if (!formattedCode.toString().endsWith("\n")) {
							    formattedCode.append("\n");
						}
						
						editor.setText(formattedCode.toString());
					}
				}
			}
		});
		
		m4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				saveStateForUndo();
				String text = editor.getText().toString();
				text = text.replaceAll("[ ]{2,}", " ").replaceAll("\n{2,}", "\n").trim();
				editor.setText(text);
			}
		});
		
		m5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				saveStateForUndo();
				String code = editor.getText().toString();
				code = removeCommentsPreservingStrings(code);
				editor.setText(code.trim());
			}
		});
		
		m6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				saveStateForUndo();
				int cursorLine = editor.getCursor().getLeftLine();
				Content text = editor.getText();
				String lineText = text.getLineString(cursorLine);
				
				if (cursorLine == text.getLineCount() - 1) {
					    text.insert(cursorLine, lineText.length(), "\n" + lineText);
					    editor.setSelection(cursorLine + 1, lineText.length());
				} else {
					    text.insert(cursorLine + 1, 0, lineText + "\n");
					    editor.setSelection(cursorLine + 1, lineText.length());
				}
			}
		});
		
		m7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				saveStateForUndo();
				String text = editor.getText().toString();
				text = text.replaceAll("[^\\x00-\\x7F]", "");
				editor.setText(text);
			}
		});
		
		m8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				saveStateForUndo();
				String text = editor.getText().toString()
				.replace("a", "4").replace("e", "3").replace("i", "1")
				.replace("o", "0").replace("s", "$").replace("t", "7");
				editor.setText(text);
			}
		});
		
		m9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				saveStateForUndo();
				String text = editor.getText().toString().replace(" ", "_");
				editor.setText(text);
			}
		});
		
		m10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				saveStateForUndo();
				List<String> imports = new ArrayList<>();
				StringBuilder others = new StringBuilder();
				
				for (String line : editor.getText().toString().split("\n")) {
						if (line.startsWith("import java.") || line.startsWith("import android.")) {
								imports.add(line);
						} else {
								others.append(line).append("\n");
						}
				}
				
				Collections.sort(imports);
				
				String sortedImports = imports.isEmpty() ? "" : TextUtils.join("\n", imports) + "\n\n";
				editor.setText(sortedImports + others.toString().trim());
			}
		});
		
		m11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_showReplaceTextDialog();
			}
		});
		
		m1_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				theme= new AlertDialog.Builder(EditorActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
				theme.setTitle("Change theme");
				theme.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() { 
					public void onClick(DialogInterface _dialog, int _which) { 
					} 
				});


				String[] items = {"Choco", "Cobalt", "Deluxe", 
					"Erebus", "Inkdeep", "Midnight", "Monokai dark", 
					"Radioactive olive", "Solarized dark", 
					"Space cadet", "Succulent", "Venom", "Zenburn"};
				
				int checkItem = themeState; 
				theme.setSingleChoiceItems(items, checkItem, new DialogInterface.OnClickListener() { 
					public void onClick(DialogInterface dialog, int which) { 
						switch (which) {
							case 0:
							_changeEditorTheme("choco");
							break;
							case 1:
							_changeEditorTheme("cobalt");
							break;
							case 2:
							_changeEditorTheme("deluxe");
							break;
							case 3: 
							_changeEditorTheme("erebus");
							break;
							case 4:
							_changeEditorTheme("inkdeep");
							break;
							case 5:
							_changeEditorTheme("midnight");
							break;
							case 6:
							_changeEditorTheme("monokai-dark");
							break;
							case 7:
							_changeEditorTheme("radioactive-olive");
							break;
							case 8: 
							_changeEditorTheme("solarized-dark");
							break;
							case 9:
							_changeEditorTheme("space-cadet");
							break;
							case 10:
							_changeEditorTheme("succulent");
							break;
							case 11: 
							_changeEditorTheme("venom");
							break;
							case 12:
							_changeEditorTheme("zenburn");
							 break;  } 
						 } 
					 });
				if (landscape) {
					_CXYZ_Simple_dialog_theme(theme);
				}
				else {
					_CXYZ_dialog_AdaptiveScale_theme(theme);
				}
			}
		});
		
		m2_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				syntaxes = new AlertDialog.Builder(EditorActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
				syntaxes.setTitle("Change language syntaxes");
				syntaxes.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() { 
					public void onClick(DialogInterface _dialog, int _which) { 
					} 
				});


				String[] items = {"C", "C++", "C#", 
					"CSS", "Groovy", "Haskell", "HTML", 
					"Java", "JavaScript", "JSON", "Kotlin", 
					"Lua", "PHP", "Python", "SQL", "TypeScript"};
				
				int checkItem = langState; 
				syntaxes.setSingleChoiceItems(items, checkItem, new DialogInterface.OnClickListener() { 
					public void onClick(DialogInterface dialog, int which) { 
						switch (which) {
							case 0:
							_changeLanguageSyntax("c");
							break;
							case 1:
							_changeLanguageSyntax("cpp");
							break;
							case 2:
							_changeLanguageSyntax("csharp");
							break;
							case 3: 
							_changeLanguageSyntax("css");
							break;
							case 4:
							_changeLanguageSyntax("groovy");
							break;
							case 5:
							_changeLanguageSyntax("haskell");
							break;
							case 6:
							_changeLanguageSyntax("html");
							break;
							case 7:
							_changeLanguageSyntax("java");
							break;
							case 8: 
							_changeLanguageSyntax("javascript");
							break;
							case 9:
							_changeLanguageSyntax("json");
							break;
							case 10:
							_changeLanguageSyntax("kotlin");
							break;
							case 11: 
							_changeLanguageSyntax("lua");
							break;
							case 12:
							_changeLanguageSyntax("php");
							break;
							case 13:
							_changeLanguageSyntax("python");
							break;
							case 14:
							_changeLanguageSyntax("sql");
							break;
							case 15: 
							_changeLanguageSyntax("typescript");
							 break;  } 
						 } 
					 });
				if (landscape) {
					_CXYZ_Simple_dialog_theme(syntaxes);
				}
				else {
					_CXYZ_dialog_AdaptiveScale_theme(syntaxes);
				}
			}
		});
		
		m2_3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				wrapTextSwitch.performClick();
			}
		});
		
		m2_4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				numberDividerSwitch.performClick();
			}
		});
		
		m2_5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ligatureSwitch.performClick();
			}
		});
		
		m2_6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				highlightBracketPairSwitch.performClick();
			}
		});
		
		m2_7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				fastScrollSwitch.performClick();
			}
		});
		
		m2_8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pinNumberDividerSwitch.performClick();
			}
		});
		
		wrapTextSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("wrap_text", "true").commit();
					editor.setWordwrap(true);
				}
				else {
					data.edit().putString("wrap_text", "false").commit();
					editor.setWordwrap(false);
				}
			}
		});
		
		numberDividerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("number_divider", "true").commit();
					editor.setLineNumberEnabled(true);
				}
				else {
					data.edit().putString("number_divider", "false").commit();
					editor.setLineNumberEnabled(false);
				}
			}
		});
		
		ligatureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("ligature", "true").commit();
					editor.setLigatureEnabled(true);
				}
				else {
					data.edit().putString("ligature", "false").commit();
					editor.setLigatureEnabled(false);
				}
			}
		});
		
		highlightBracketPairSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("highlight_bracket_pair", "true").commit();
					editor.setHighlightBracketPair(true);
				}
				else {
					data.edit().putString("highlight_bracket_pair", "false").commit();
					editor.setHighlightBracketPair(false);
				}
			}
		});
		
		fastScrollSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("fast_scroll", "true").commit();
					editor.setHorizontalScrollBarEnabled(true);
					editor.setVerticalScrollBarEnabled(true);
				}
				else {
					data.edit().putString("fast_scroll", "false").commit();
					editor.setHorizontalScrollBarEnabled(false);
					editor.setVerticalScrollBarEnabled(false);
				}
			}
		});
		
		pinNumberDividerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("pin_number_divider", "true").commit();
					editor.setPinLineNumber(true);
				}
				else {
					data.edit().putString("pin_number_divider", "false").commit();
					editor.setPinLineNumber(false);
				}
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_showMenu(true);
			}
		});
		
		_sourcecode_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		((ViewGroup)toolbar1.getParent()).removeView(toolbar1);
		_toolbar.addView(toolbar1);
		if (getIntent().getStringExtra("filescope").equals("editing")) {
			filename.setText(getIntent().getStringExtra("title"));
			path.setText(getIntent().getStringExtra("path"));
			editor.setText(FileUtil.readFile(getIntent().getStringExtra("contentPath")));
			originalContent = FileUtil.readFile(getIntent().getStringExtra("contentPath"));
		}
		else {
			if (getIntent().getStringExtra("hidden").equals("true")) {
				filename.setText(".".concat(getIntent().getStringExtra("title").concat(".".concat(getIntent().getStringExtra("type")))));
			}
			else {
				filename.setText(getIntent().getStringExtra("title").concat(".".concat(getIntent().getStringExtra("type"))));
			}
			path.setText(getIntent().getStringExtra("path"));
		}
		window_alpha_container.setBackgroundColor(0xD9111111);
		readOnly = false;
		WebSettings webSettings = WebView1.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setDisplayZoomControls(false);
		webSettings.setDomStorageEnabled(true);
		webSettings.setDatabaseEnabled(true);
		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			    webSettings.setAllowFileAccessFromFileURLs(false);
			    webSettings.setAllowUniversalAccessFromFileURLs(false);
		}
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			    webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
		}
		
		_setEllipsize(path);
		_setEllipsize(TextView50);
		_setEllipsize(close_window_alpha);
		_ripple(run);
		_ripple(insert);
		_ripple(save);
		_ripple(undo);
		_ripple(redo);
		_ripple(more);
		_ripple(imageview1);
		_ripple(imageview17);
		_ripple(more_w);
		_tablayout();
		editor.showSoftInput();
		editor.setTabWidth(4);
		editor.setScrollBarSize(1);
		editor.setCursorWidth(4);
		editor.setTextSize(20);
		editor.setNonPrintablePaintingFlags(
		CodeEditor.FLAG_DRAW_LINE_SEPARATOR |
		CodeEditor.FLAG_DRAW_WHITESPACE_LEADING |
		CodeEditor.FLAG_DRAW_WHITESPACE_IN_SELECTION
		);
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/sudoregular.ttf");
		editor.setTypefaceText(font);
		inputView.bindEditor(this.editor);
		inputView.addSymbols(
		    new String[]{
			        "->", "{", "}", "(", ")", "[", "]", "<", ">",
			        ",", ".", ";", ":", "\"", "'",
			        "+", "-", "*", "/", "=", "%",
			        "&", "|", "^", "~", "!", "?",
			        "&&", "||", "<<", ">>", ">>>",
			        "@", "#", "$", "\\", "_",
			        "==", "!=", "<=", ">=", "++", "--",
			        "/*", "*/", "//", "' '", "\"\"", "`"
			    },
		    new String[]{
			        "\t", "{}", "}", "()", ")", "[]", "]", "<>", ">", 
			        ",", ".", ";", ":", "\"", "'",  
			        "+", "-", "*", "/", "=", "%",  
			        "&", "|", "^", "~", "!", "?",  
			        "&&", "||", "<<", ">>", ">>>",  
			        "@", "#", "$", "\\", "_",  
			        "==", "!=", "<=", ">=", "++", "--",  
			        "/*\n\t\n*/", "*/", "// ", "' '", "\"\"", "`"
			    }
		);
		
		for (int i = 0; i < inputView.getChildCount(); i++) {
			    View child = inputView.getChildAt(i);
			    if (child instanceof Button) {
				        Button btn = (Button) child;
				        btn.setTextColor(Color.WHITE);
				        btn.setBackgroundColor(Color.parseColor("#00000000"));
				    }
		}
		View.OnTouchListener touchListener = new View.OnTouchListener() {
			    PointF DownPT = new PointF();
			    PointF StartPT = new PointF();
			    int screenWidth, screenHeight;
			    int margin = 20;
			    boolean isMoved = false;
			
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
				        screenWidth = getResources().getDisplayMetrics().widthPixels;
				        screenHeight = getResources().getDisplayMetrics().heightPixels;
				
				        int action = event.getAction();
				        switch (action) {
					            case MotionEvent.ACTION_DOWN:
					                DownPT.x = event.getRawX();
					                DownPT.y = event.getRawY();
					                StartPT = new PointF(v.getX(), v.getY());
					                isMoved = false;
					                break;
					
					            case MotionEvent.ACTION_MOVE:
					                PointF mv = new PointF(event.getRawX() - DownPT.x, event.getRawY() - DownPT.y);
					                if (Math.abs(mv.x) > 0 || Math.abs(mv.y) > 0) {
						                    isMoved = true;
						                    float newX = StartPT.x + mv.x;
						                    float newY = StartPT.y + mv.y;
						                    newX = Math.max(0, Math.min(newX, screenWidth - v.getWidth()));
						                    newY = Math.max(0, Math.min(newY, screenHeight - v.getHeight()));
						                    v.setX(newX);
						                    v.setY(newY);
						                }
					                break;
					
					            case MotionEvent.ACTION_UP:
					                if (isMoved) {
						                    v.bringToFront();
						                    v.invalidate();
						                }
					                
					                if (isWidgetOutOfBounds(v, screenWidth, screenHeight)) {
						                    adjustWidgetToScreenBounds(v, screenWidth, screenHeight);
						                }
					                break;
					
					            default:
					                break;
					        }
				        return true;
				    }
		};
		
		
		View.OnTouchListener resizeHandleListener = new View.OnTouchListener() {
			    PointF DownPT = new PointF();
			    float startWidth, startHeight, startX, startY;
			    int screenWidth, screenHeight;
			    int marginW = (int) dpToPx(20);
			    int marginH = (int) dpToPx(40);
			    float minWidth = dpToPx(150);
			    float minHeight = dpToPx(150);
			    float maxWidth, maxHeight;
			    boolean isLeftDrag = false;
			    boolean isCenterDrag = false;
			
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
				        screenWidth = getResources().getDisplayMetrics().widthPixels;
				        screenHeight = getResources().getDisplayMetrics().heightPixels;
				
				        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
					            maxWidth = screenWidth * 0.8f;
					            maxHeight = screenHeight * 0.8f;
					        } else {
					            maxWidth = screenWidth - marginW * 2;
					            maxHeight = screenHeight - marginH * 2;
					        }
				
				        int action = event.getAction();
				        switch (action) {
					            case MotionEvent.ACTION_DOWN:
					                DownPT.x = event.getRawX();
					                DownPT.y = event.getRawY();
					                startWidth = web_preview.getWidth();
					                startHeight = web_preview.getHeight();
					                startX = web_preview.getX();
					                startY = web_preview.getY();
					                float touchX = event.getRawX();
					                float widgetCenterX = startX + (web_preview.getWidth() / 2);
					                isLeftDrag = touchX < widgetCenterX;
					                isCenterDrag = Math.abs(touchX - widgetCenterX) < 50;
					                break;
					
					            case MotionEvent.ACTION_MOVE:
					                float deltaX = event.getRawX() - DownPT.x;
					                float deltaY = event.getRawY() - DownPT.y;
					                float newWidth = startWidth;
					                float newHeight = startHeight;
					                float newX = startX;
					                float newY = startY;
					
					                if (isCenterDrag) {
						                    newHeight = startHeight + deltaY;
						                    newHeight = Math.max(minHeight, Math.min(newHeight, maxHeight));
						                } else if (isLeftDrag) {
						                    newWidth = startWidth - deltaX;
						                    newX = startX + deltaX;
						                } else {
						                    newWidth = startWidth + deltaX;
						                }
					
					                newWidth = Math.max(minWidth, Math.min(newWidth, maxWidth));
					                if (isLeftDrag) {
						                    newX = Math.max(marginW, Math.min(newX, screenWidth - marginW - newWidth));
						                }
					
					                web_preview.getLayoutParams().width = (int) newWidth;
					                web_preview.getLayoutParams().height = (int) newHeight;
					                web_preview.setX(newX);
					                web_preview.requestLayout();
					                break;
					
					            case MotionEvent.ACTION_UP:
					                float finalX = web_preview.getX();
					                float finalY = web_preview.getY();
					                if (finalX < marginW) {
						                    finalX = 0;
						                } else if (finalX > screenWidth - web_preview.getWidth() - marginW) {
						                    finalX = screenWidth - web_preview.getWidth();
						                }
					                if (finalY < marginH) {
						                    finalY = 0;
						                } else if (finalY > screenHeight - web_preview.getHeight() - marginH) {
						                    finalY = screenHeight - web_preview.getHeight();
						                }
					                web_preview.setX(finalX);
					                web_preview.setY(finalY);
					                break;
					
					            default:
					                break;
					        }
				        return true;
				    }
		};
		
		resize_win.setOnTouchListener(resizeHandleListener);
		web_preview.setOnTouchListener(touchListener);
		String themeValue =  tmtheme.getString("theme", "");
		if (themeValue == null || themeValue.isEmpty()) {
			changeTheme("erebus.tmTheme");
			themeState = 3;
		}
		else {
			_reloadTheme();
		}
		String langValue =  tmtheme.getString("lang", "");
		if (langValue == null || langValue.isEmpty()) {
			configureEditor(editor, "java"); 
			langState = 7;
		}
		else {
			_reloadLanguage();
		}
		if (data.getString("symbol_tabs", "").equals("false")) {
			symb.setVisibility(View.GONE);
			_fab.setTranslationY((float)(-40));
		}
		inputView.setBackgroundColor(Color.TRANSPARENT);
		menu.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)1, 0xFF474747, 0xD9111111));
		web_preview.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)1, 0xFF474747, 0xD9111111));
		HorizontalScrollView1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)1, 0xFF474747, 0xD9111111));
	}
	
	
	private Runnable textUpdater = new Runnable() {
			private String lastText = "";
			
			@Override
			public void run() {
					if (!isTextWatcherEnabled) return;
					
					String currentText = "";
					
					if (editor != null && editor.getText() != null) {
							currentText = editor.getText().toString();
					} else {
							handler.postDelayed(this, 500);
							return;
					}
					
					if (!lastText.equals(currentText)) {
								WebView1.getSettings().setJavaScriptEnabled(true);
								WebView1.getSettings().setDefaultTextEncodingName("UTF-8");
								String htmlContent = "<html><head><meta charset='UTF-8'></head><body>" + currentText + "</body></html>";
								WebView1.loadData(htmlContent, "text/html; charset=UTF-8", null);
						    	lastText = currentText;
					}
					
					handler.postDelayed(this, 500);
			}
	};
	
	
	private void startTextWatcher() {
			handler.postDelayed(textUpdater, 500);
	}
	
	
	private void stopTextWatcher() {
			handler.removeCallbacks(textUpdater);
	}
	
	
	private void initializeViews() {
			WebView1 = findViewById(R.id.WebView1);
			editor = findViewById(R.id.editor);
	}
	
	
	private void showV(View widget) {
			widget.setVisibility(View.VISIBLE);
			widget.setScaleX(0f);
			widget.setScaleY(0f);
			widget.setAlpha(0f);
			
			AnimatorSet animatorSet = new AnimatorSet();
			animatorSet.playTogether(
			ObjectAnimator.ofFloat(widget, View.SCALE_X, 1f),
			ObjectAnimator.ofFloat(widget, View.SCALE_Y, 1f),
			ObjectAnimator.ofFloat(widget, View.ALPHA, 1f)
			);
			animatorSet.setDuration(300);
			animatorSet.start();
	}
	
	
	private void hideV(final View widget) {
			AnimatorSet animatorSet = new AnimatorSet();
			animatorSet.playTogether(
			ObjectAnimator.ofFloat(widget, View.SCALE_X, 0f),
			ObjectAnimator.ofFloat(widget, View.SCALE_Y, 0f),
			ObjectAnimator.ofFloat(widget, View.ALPHA, 0f)
			);
			animatorSet.setDuration(300);
			animatorSet.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
							widget.setVisibility(View.GONE);
					}
			});
			animatorSet.start();
	}
	
	
	private void saveStateForUndo() {
			if (editor != null && editor.getText() != null) {
					undoStack.push(editor.getText().toString());
					redoStack.clear();
			}
	}
	
	
	private void performUndo() {
		    if (!undoStack.isEmpty()) {
			        redoStack.push(editor.getText().toString());
			        editor.setText(undoStack.pop());
			    } else {
			        editor.undo();
			    }
	}
	
	
	private void performRedo() {
		    if (!redoStack.isEmpty()) {
			        undoStack.push(editor.getText().toString());
			        editor.setText(redoStack.pop());
			    } else {
			        editor.redo();
			    }
	}
	
	
	private void showTextStatistics(String code, Context context) {
		    int totalChars = code.length();
		    int totalWords = code.trim().isEmpty() ? 0 : code.trim().split("\\s+").length;
		    int totalLines = code.split("\n").length;
		    int blankLines = countPattern(code, "^\\s*$", Pattern.MULTILINE);
		    int codeLines = totalLines - blankLines;
		    int singleLineComments = countPattern(code, "//.*", Pattern.MULTILINE);
		    int multiLineComments = countPattern(code, "/\\*.*?\\*/", Pattern.DOTALL);
		    int blockComments = countPattern(code, "/\\*\\*.*?\\*/", Pattern.DOTALL);
		    int todoComments = countPattern(code, "//\\s*TODO:|/\\*\\*?\\s*TODO:", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		    int fixmeComments = countPattern(code, "//\\s*FIXME:|/\\*\\*?\\s*FIXME:", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		    int functions = countPattern(code, "\\b\\w+\\s+\\**\\w+\\s*\\([^)]*\\)\\s*\\{", Pattern.MULTILINE);
		    int classes = countPattern(code, "\\bclass\\s+\\w+", Pattern.MULTILINE);
		    int interfaces = countPattern(code, "\\binterface\\s+\\w+", Pattern.MULTILINE);
		    int methods = countPattern(code, "\\b\\w+\\s*\\([^)]*\\)\\s*\\{", Pattern.MULTILINE);
		    int loops = countPattern(code, "\\b(for|while|do)\\s*\\(", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		    int conditionals = countPattern(code, "\\b(if|switch)\\s*\\(", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		    int magicNumbers = countPattern(code, "\\b\\d+\\b(?!\\s*[;,)])", Pattern.MULTILINE);
		    int longLines = countLinesExceedingLength(code, 80);
		    int deepNesting = countPattern(code, "\\{\\s*\\{\\s*\\{\\s*\\{", Pattern.MULTILINE);
		    int imports = countPattern(code, "import\\s+", Pattern.MULTILINE);
		    int requires = countPattern(code, "require\\s*\\(", Pattern.MULTILINE);
		    int strings = countPattern(code, "\"[^\"]*\"", Pattern.MULTILINE);
		    int stringLengths = sumStringLengths(code);
		    int numbers = countPattern(code, "\\b\\d+\\b", Pattern.MULTILINE);
		
		    String htmlMessage = "<html><body style='background-color:#111111; color:#ffffff; font-family:sans-serif;'>"
		        + "<table border='0' cellpadding='5' cellspacing='0' width='100%'>"
		        + "<tr><td colspan='2' style='color:#ff0000;'><b>Basic Statistics</b></td></tr>"
		        + row("Total Characters", totalChars)
		        + row("Total Words", totalWords)
		        + row("Total Lines", totalLines)
		        + row("Blank Lines", blankLines + " (" + percent(blankLines, totalLines) + ")")
		        + row("Code Lines", codeLines + " (" + percent(codeLines, totalLines) + ")")
		        + "<tr><td colspan='2' style='color:#ff0000; padding-top:10px;'><b>Comment Analysis</b></td></tr>"
		        + row("Single-line Comments (//)", singleLineComments)
		        + row("Multi-line Comments (/* */)", multiLineComments)
		        + row("Documentation Comments (/** */)", blockComments)
		        + row("TODO Markers", todoComments, todoComments > 0 ? "color:#ff0000;" : "")
		        + row("FIXME Markers", fixmeComments, fixmeComments > 0 ? "color:#ff0000;" : "")
		        + "<tr><td colspan='2' style='color:#ff0000; padding-top:10px;'><b>Code Structure</b></td></tr>"
		        + row("Functions", functions)
		        + row("Classes", classes)
		        + row("Interfaces", interfaces)
		        + row("Methods", methods)
		        + row("Loops", loops)
		        + row("Conditionals", conditionals)
		        + "<tr><td colspan='2' style='color:#ff0000; padding-top:10px;'><b>Code Quality</b></td></tr>"
		        + row("Magic Numbers", magicNumbers, magicNumbers > 5 ? "color:#ff0000;" : "")
		        + row("Long Lines (>80 chars)", longLines, longLines > 10 ? "color:#ff0000;" : "")
		        + row("Deep Nesting (4+ levels)", deepNesting, deepNesting > 0 ? "color:#ff0000;" : "")
		        + "<tr><td colspan='2' style='color:#ff0000; padding-top:10px;'><b>Content Analysis</b></td></tr>"
		        + row("String Literals", strings)
		        + row("Total String Characters", stringLengths)
		        + row("Numeric Literals", numbers)
		        + "</table></body></html>";
		
		    showHtmlDialog(context, "Code Metrics", htmlMessage);
	}
	
	
	private int countPattern(String text, String regex, int flags) {
		    if (text == null || text.isEmpty()) return 0;
		    try {
			        Pattern pattern = Pattern.compile(regex, flags);
			        Matcher matcher = pattern.matcher(text);
			        int count = 0;
			        while (matcher.find()) count++;
			        return count;
			    } catch (PatternSyntaxException e) {
			        return 0;
			    }
	}
	
	
	private int countLinesExceedingLength(String code, int maxLength) {
		    if (code == null || code.isEmpty()) return 0;
		    String[] lines = code.split("\n");
		    int count = 0;
		    for (String line : lines) {
			        if (line.length() > maxLength) count++;
			    }
		    return count;
	}
	
	
	private int sumStringLengths(String code) {
		    if (code == null || code.isEmpty()) return 0;
		    Pattern pattern = Pattern.compile("\"([^\"]*)\"");
		    Matcher matcher = pattern.matcher(code);
		    int total = 0;
		    while (matcher.find()) {
			        total += matcher.group(1).length();
			    }
		    return total;
	}
	
	
	private String row(String label, Object value) {
		    return row(label, value, "");
	}
	
	
	private String row(String label, Object value, String style) {
		    return "<tr><td><b>" + label + ":</b></td><td" + (!style.isEmpty() ? " style='" + style + "'" : "") + ">" + value + "</td></tr>";
	}
	
	
	private String percent(int part, int whole) {
		    return whole > 0 ? String.format("%.1f%%", (part * 100.0 / whole)) : "0%";
	}
	
	
	private void showHtmlDialog(Context context, String title, String html) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(context);
		    builder.setTitle(title);
		    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			        WebView webView = new WebView(context);
			        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
			        builder.setView(webView);
			    } else {
			        builder.setMessage(Html.fromHtml(html));
			    }
		    builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
		    _CXYZ_dialog_AdaptiveScale_theme(builder);
	}
	
	
	public static abstract class BaseTransformer implements androidx.viewpager.widget.ViewPager.PageTransformer {
			protected abstract void onTransform(View view, float position);
			
			
			@Override
			public void transformPage(View view, float position) {
					onPreTransform(view, position);
					onTransform(view, position);
					onPostTransform(view, position);
			}
			
			
			protected boolean hideOffscreenPages() {
					return true;
			}
			
			
			protected boolean isPagingEnabled() {
					return false;
			}
			
			
			protected void onPreTransform(View view, float position) {
					final float width = view.getWidth();
					view.setRotationX(0);
					view.setRotationY(0);
					view.setRotation(0);
					view.setScaleX(1);
					view.setScaleY(1);
					view.setPivotX(0);
					view.setPivotY(0);
					view.setTranslationY(0);
					view.setTranslationX(isPagingEnabled() ? 0f : -width * position);
					
					if (hideOffscreenPages()) {
							view.setAlpha(position <= -1f || position >= 1f ? 0f : 1f);
					} else {
							view.setAlpha(1f);
					}
			}
			
			
			protected void onPostTransform(View view, float position) {
			}
	}
	
	
	public static class DefaultTransformer extends BaseTransformer {
			@Override 
			protected void onTransform(View view, float position) {
					
			}
			
			
			@Override 
			public boolean isPagingEnabled() {
					return true;
			}
	}
	
	
	public static class DepthPageTransformer extends BaseTransformer {
			private static final float MIN_SCALE = 0.75f;
			
			
			@Override
			protected void onTransform(View view, float position) {
					if (position <= 0f) {
							view.setTranslationX(0f);
							view.setScaleX(1f);
							view.setScaleY(1f);
					} else if (position <= 1f) {
							final float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
							view.setAlpha(1 - position);
							view.setPivotY(0.5f * view.getHeight());
							view.setTranslationX(view.getWidth() * -position);
							view.setScaleX(scaleFactor);
							view.setScaleY(scaleFactor);
					}
			}
			
			
			@Override
			protected boolean isPagingEnabled() {
					return true;
			}
	}
	
	
	public void changeTheme(String themeName) {
		    try {
			        IRawTheme iRawTheme = ThemeReader.readThemeSync(themeName, getAssets().open("textmate/" + themeName));
			        colorScheme = TextMateColorScheme.create(iRawTheme);
			        editor.setColorScheme(colorScheme);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
	}
	
	
	public void configureEditor(CodeEditor editor, String languageName) {
		    if (!(editor.getColorScheme() instanceof TextMateColorScheme)) {
			        Log.e("EditorSetup", "Color scheme is not a TextMateColorScheme!");
			        return;
			    }
		
		    try {
			        TextMateColorScheme tmColorScheme = (TextMateColorScheme) editor.getColorScheme();
			        String grammarPath = "textmate/" + languageName + "/syntaxes/" + languageName + ".tmLanguage.json";
			        String configPath = "textmate/" + languageName + "/language-configuration.json";
			
			        if (!fileExists(editor, grammarPath)) {
				            Log.e("EditorSetup", "Grammar file not found: " + grammarPath);
				            return;
				        }
			
			        InputStreamReader configReader = null;
			        if (fileExists(editor, configPath)) {
				            configReader = new InputStreamReader(editor.getContext().getAssets().open(configPath));
				        }
			
			        Language language = TextMateLanguage.create(
			            languageName + ".tmLanguage.json",
			            editor.getContext().getAssets().open(grammarPath),
			            configReader,
			            tmColorScheme.getRawTheme()
			        );
			
			        editor.setEditorLanguage(language);
			        editor.rerunAnalysis();
			        Log.i("EditorSetup", "Syntax highlighting applied for: " + languageName);
			
			    } catch (Exception e) {
			        Log.e("EditorSetup", "Failed to load language: " + languageName, e);
			    }
	}
	
	
	private boolean fileExists(CodeEditor editor, String path) {
		    try {
			        editor.getContext().getAssets().open(path).close();
			        return true;
			    } catch (IOException e) {
			        return false;
			    }
	}
	
	
	private int getToolbarHeight() {
			TypedValue tv = new TypedValue();
			if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
					return TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
			}
			return 0; 
	}
	
	
	private boolean isWidgetOutOfBounds(View widget, int screenWidth, int screenHeight) {
			float widgetX = widget.getX();
			float widgetY = widget.getY();
			float widgetWidth = widget.getWidth();
			float widgetHeight = widget.getHeight();
			
			return widgetX < 0 || widgetY < 0 ||
			widgetX + widgetWidth > screenWidth ||
			widgetY + widgetHeight > screenHeight;
	}
	
	
	private void adjustWidgetToScreenBounds(View widget, int screenWidth, int screenHeight) {
			int toolbarHeight = getToolbarHeight();
			int adjustedScreenHeight = screenHeight - toolbarHeight - 95;
			float widgetX = widget.getX();
			float widgetY = widget.getY();
			float widgetWidth = widget.getWidth();
			float widgetHeight = widget.getHeight();
			
			
			if (widgetX < 0) {
					widgetX = 0;
			} else if (widgetX + widgetWidth > screenWidth) {
					widgetX = screenWidth - widgetWidth;
			}
			
			
			if (widgetY < 0) {
					widgetY = 0;
			} else if (widgetY + widgetHeight > adjustedScreenHeight) {
					widgetY = adjustedScreenHeight - widgetHeight;
			}
			
			
			if (widgetWidth > screenWidth) {
					widgetWidth = screenWidth;
					widgetX = 0; 
			}
			
			
			if (widgetHeight > adjustedScreenHeight) {
					widgetHeight = adjustedScreenHeight;
					widgetY = 0; 
			}
			
			widget.setX(widgetX);
			widget.setY(widgetY);
			widget.getLayoutParams().width = (int) widgetWidth;
			widget.getLayoutParams().height = (int) widgetHeight;
			widget.requestLayout();
	}
	
	private float dpToPx(float dp) {
			return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, 
			Resources.getSystem().getDisplayMetrics());
	}
	
	
	public boolean isAppInstalled(final String packageName) {
			PackageManager pm = getPackageManager(); 
			installed = false; 
			try { 
					pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES); 
					installed = true; 
			} catch (PackageManager.NameNotFoundException e) { 
					installed = false; 
			} 
			return installed; 
	}
	
	
	private void saveSessionData(String text) {
			try {
					String path = getIntent().getStringExtra("path");
					if (path == null || path.isEmpty()) {
							Log.e("FileSave", "Invalid directory path");
							return;
					}
					
					File dir = new File(path);
					
					if (!dir.exists() && !dir.mkdirs()) {
							Log.e("FileSave", "Failed to create directory: " + dir.getAbsolutePath());
							return;
					}
					
					String fileNameText = filename != null ? filename.getText().toString().trim() : "default";
					if (fileNameText.isEmpty()) {
							Log.e("FileSave", "Filename is empty. Using default.");
							fileNameText = "default";
					}
					
					File file = new File(dir, ".DRAFT_" + fileNameText.replaceAll("[\\\\/:*?\"<>|]", "_") + "_.recov");
					
					try (FileWriter writer = new FileWriter(file)) {
							writer.write(text);
					}
					
					Log.d("FileSave", "File saved successfully: " + file.getAbsolutePath());
					
			} catch (IOException e) {
					Log.e("FileSave", "Error saving file", e);
			}
	}
	
	
	private String readTextFromFile() {
			File file = new File(getIntent().getStringExtra("path"));
			
			if (!file.exists()) {
					return "";
			}
			
			File dataFile = new File(file, ".DRAFT_" + filename.getText().toString() + "_.recov");
			
			if (!dataFile.exists()) {
					return "";
			}
			
			try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
					StringBuilder stringBuilder = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
							stringBuilder.append(line).append("\n");
					}
					return stringBuilder.toString().trim();
			} catch (IOException e) {
					e.printStackTrace();
					return "";
			}
	}
	
	
	private void deleteSessionData() {
			File dir = new File(getIntent().getStringExtra("path"));
			File file = new File(dir, ".DRAFT_" + filename.getText().toString() + "_.recov");
			
			if (file.exists()) {
					if (file.delete()) {
							Log.d("FileDeletion", "File deleted successfully: " + file.getAbsolutePath());
					} else {
							Log.e("FileDeletion", "Failed to delete file: " + file.getAbsolutePath());
					}
			}
	}
	
	
	private static void saveFile(String path, String content) throws IOException {
			Objects.requireNonNull(path, "Path cannot be null");
			Objects.requireNonNull(content, "Content cannot be null");
			
			File file = new File(path);
			File parent = file.getParentFile();
			
			if (parent != null && !parent.exists()) {
					parent.mkdirs();
			}
			
			File tempFile = new File(file.getParent(), file.getName() + ".tmp");
			try (BufferedWriter writer = new BufferedWriter(
			new OutputStreamWriter(new FileOutputStream(tempFile), StandardCharsets.UTF_8))) {
					writer.write(content);
			}
			
			if (file.exists()) {
					file.delete();
			}
			tempFile.renameTo(file);
	}
	
	private void insertTextInEditor(final CodeEditor editor, final String text) {
			if (editor == null || editor.getText() == null || text == null) {
					return;
			}
			
			try {
					final io.github.rosemoe.sora.text.Cursor cursor = editor.getCursor();
					final int line = cursor.getLeftLine();
					final int column = Math.max(0, 
					Math.min(cursor.getLeftColumn(), editor.getText().getColumnCount(line)));
					editor.getText().insert(line, column, text);
					
					final int newColumn = column + text.length();
					cursor.set(line, Math.min(newColumn, editor.getText().getColumnCount(line)));
					
					editor.post(() -> {
							editor.ensureSelectionVisible();
					});
			} catch (Exception e) {
					e.printStackTrace();
			}
	}
	
	
	private Runnable backupUpdater = new Runnable() {
			private String lastText = "";
			
			@Override
			public void run() {
					if (!retainSession) return;
					
					String currentText = "";
					
					if (editor != null && editor.getText() != null) {
							currentText = editor.getText().toString();
					} else {
							handler.postDelayed(this, 500);
							return;
					}
					
					if (!lastText.equals(currentText)) {
				            saveSessionData(currentText);
					    	lastText = currentText;
					}
					
					handler.postDelayed(this, 500);
			}
	};
	
	
	private void startBackup() {
			handler.postDelayed(backupUpdater, 500);
	}
	
	
	private void stopBackup() {
			handler.removeCallbacks(backupUpdater);
	}
	
	
	public static class CheckerboardDrawable extends android.graphics.drawable.Drawable {
		    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		    private int size;
		    private int colorOdd;
		    private int colorEven;
		    public static CheckerboardDrawable create() {
			        return new CheckerboardDrawable(new Builder());
			    }
		    
		    
		    private CheckerboardDrawable(Builder builder) {
			        this.size = builder.size;
			        this.colorOdd = builder.colorOdd;
			        this.colorEven = builder.colorEven;
			        configurePaint();
			    }
		    
		    
		    private void configurePaint() {
			        Bitmap bitmap = Bitmap.createBitmap(size * 2, size * 2, Bitmap.Config.ARGB_8888);
			        Paint bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			        bitmapPaint.setStyle(Paint.Style.FILL);
			        Canvas canvas = new Canvas(bitmap);
			        Rect rect = new Rect(0, 0, size, size);
			        bitmapPaint.setColor(colorOdd);
			        canvas.drawRect(rect, bitmapPaint);
			        rect.offset(size, size);
			        canvas.drawRect(rect, bitmapPaint);
			        bitmapPaint.setColor(colorEven);
			        rect.offset(-size, 0);
			        canvas.drawRect(rect, bitmapPaint);
			        rect.offset(size, -size);
			        canvas.drawRect(rect, bitmapPaint);
			        paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.REPEAT, BitmapShader.TileMode.REPEAT));
			    }
		    
		    
		    @Override
		    public void draw(Canvas canvas) {
			        canvas.drawPaint(paint);
			    }
		    
		    
		    @Override
		    public void setAlpha(int alpha) {
			        paint.setAlpha(alpha);
			    }
		    
		    
		    @Override
		    public void setColorFilter(ColorFilter colorFilter) {
			        paint.setColorFilter(colorFilter);
			    }
		    
		    
		    @Override
		    public int getOpacity() {
			        return PixelFormat.OPAQUE;
			    }
		    
		    
		    public static final class Builder {
			        private int size = 40;
			        private int colorOdd = 0xFFC2C2C2;
			        private int colorEven = 0xFFF3F3F3;
			        public Builder size(int size) {
				            this.size = size;
				            return this;
				        }
			        
			        
			        public Builder colorOdd(int color) {
				            colorOdd = color;
				            return this;
				        }
			        
			        
			        public Builder colorEven(int color) {
				            colorEven = color;
				            return this;
				        }
			        
			        
			        public CheckerboardDrawable build() {
				            return new CheckerboardDrawable(this);
				        }
			    }
	}
	
	
	public void getClipboardText(Context context) {
			ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		
			if (clipboard != null && clipboard.hasPrimaryClip()) {
					ClipData clipData = clipboard.getPrimaryClip();
			
					if (clipData != null && clipData.getItemCount() > 0) {
							CharSequence clipboardText = clipData.getItemAt(0).getText();
				
							if (clipboardText != null) {
									insertTextInEditor(editor, clipboardText.toString());
							} else {
									_AppDesignerToast("Clipboard contains non-text data!");
							}
					}
			} else {
					_AppDesignerToast("Clipboard is empty!");
			}
	}
	
	
	private void pickTextFile() {
		    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
		    intent.setType("text/*");
		    intent.addCategory(Intent.CATEGORY_OPENABLE);
		    startActivityForResult(intent, PICK_TXT_FILE);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		    super.onActivityResult(requestCode, resultCode, data);
		
		    if (requestCode == PICK_TXT_FILE && resultCode == RESULT_OK && data != null) {
			        Uri uri = data.getData();
			        if (uri != null) {
				            readTextFromUri(uri);
				        }
			    }
	}
	
	
	private void readTextFromUri(Uri uri) {
		    try (InputStream inputStream = getContentResolver().openInputStream(uri);
		         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			
			        StringBuilder text = new StringBuilder();
			        String line;
			        while ((line = reader.readLine()) != null) {
				            text.append(line).append("\n");
				        }
			
			        insertTextInEditor(editor, text.toString());
			
			    } catch (IOException e) {
			        Toast.makeText(this, "Failed to read file", Toast.LENGTH_SHORT).show();
			    }
	}
	
	
	private void checkKeyboardListener()  {
			baseBackground.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()  {
					@Override
					public void onGlobalLayout()  {
							Rect r = new Rect();
							baseBackground.getWindowVisibleDisplayFrame(r);
							int screenHeight = baseBackground.getRootView().getHeight();
							int keypadHeight = screenHeight - r.bottom;
							boolean isKeyboardNowVisible = keypadHeight > screenHeight * 0.15;
							
							if (isKeyboardNowVisible != isKeyboardVisible)  {
									isKeyboardVisible = isKeyboardNowVisible;
									if (isKeyboardVisible)  {
											_showMenu(false);
									} else  {
											if (menu.getVisibility() == View.VISIBLE) {
													_showMenu(true);
											} else {
							                        _showMenu(false);
							                    }
											
									}
							}
					}
			});
	}
	
	
	public void hideKeyboard() {
		    View view = this.getCurrentFocus();
		    if (view != null) {
			        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			        if (imm != null) {
				            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
				        }
			    }
	}
	
	
	private int getLineStartPosition(int cursorPosition, String text) {
			int lineStart = cursorPosition;
			while (lineStart > 0 && text.charAt(lineStart - 1) != '\n') {
					lineStart--;
			}
			return lineStart;
	}
	
	
	private int getLineEndPosition(int cursorPosition, String text) {
			int lineEnd = cursorPosition;
			while (lineEnd < text.length() && text.charAt(lineEnd) != '\n') {
					lineEnd++;
			}
			return lineEnd;
	}
	
	
	public String removeCommentsPreservingStrings(String code) {
			StringBuilder result = new StringBuilder();
			boolean inString = false;
			boolean inChar = false;
			boolean inSingleLineComment = false;
			boolean inMultiLineComment = false;
			
			for (int i = 0; i < code.length(); i++) {
					char current = code.charAt(i);
					char next = (i + 1 < code.length()) ? code.charAt(i + 1) : '\0';
					
					if (current == '"' && !inChar && !inSingleLineComment && !inMultiLineComment) {
							inString = !inString;
					}
					
					if (current == '\'' && !inString && !inSingleLineComment && !inMultiLineComment) {
							inChar = !inChar;
					}
					
					if (!inString && !inChar && !inMultiLineComment && current == '/' && next == '/') {
							inSingleLineComment = true;
					}
					
					if (!inString && !inChar && !inSingleLineComment && current == '/' && next == '*') {
							inMultiLineComment = true;
					}
					
					if (inSingleLineComment && current == '\n') {
							inSingleLineComment = false;
					}
					
					if (inMultiLineComment && current == '*' && next == '/') {
							inMultiLineComment = false;
							i++;
							continue;
					}
					
					if (!inSingleLineComment && !inMultiLineComment) {
							result.append(current);
					}
			}
			
			return result.toString();
	}
	
	
	public String reverseText(String text, boolean reverseWords, boolean reverseSentence) {
			String[] lines = text.split("\n");
			StringBuilder result = new StringBuilder();
			
			for (String line : lines) {
					String[] words = line.split("\\s+");
					StringBuilder lineResult = new StringBuilder();
					
					if (reverseWords) {
							for (String word : words) {
									lineResult.append(new StringBuilder(word).reverse().toString()).append(" ");
							}
					} else {
							for (String word : words) {
									lineResult.append(word).append(" ");
							}
					}
					
					if (reverseSentence) {
							String[] reversedWords = lineResult.toString().trim().split("\\s+");
							lineResult.setLength(0);
							for (int i = reversedWords.length - 1; i >= 0; i--) {
									lineResult.append(reversedWords[i]).append(" ");
							}
					}
					
					result.append(lineResult.toString().trim()).append("\n");
			}
			
			return result.toString().trim();
	}
	
	
	private String generateIndent(int indent) {
		    StringBuilder sb = new StringBuilder(indent);
		    for (int i = 0; i < indent; i++) {
			        sb.append(' ');
			    }
		    return sb.toString();
	}
	
	
	private boolean checkKeyword(char[] chars, int index, String keyword) {
			if (index + keyword.length() > chars.length) return false;
			for (int j = 0; j < keyword.length(); j++) {
					if (chars[index + j] != keyword.charAt(j)) return false;
			}
			return true;
	}
	
	
	@Override
	public void onBackPressed() {
		if (menu.getVisibility() == View.VISIBLE) {
			_showMenu(false);
		}
		else {
			if (getIntent().getStringExtra("filescope").equals("editing")) {
				if (editor.getText().toString().equals(originalContent)) {
					finish();
					overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
				}
				else {
					_exitConfirmationDialog();
				}
			}
			else {
				_exitConfirmationDialog();
			}
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		checkKeyboardListener();
		if (retainSession) {
			editor.setText(readTextFromFile());
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (retainSession) {
			saveSessionData(editor.getText().toString());
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		if (retainSession) {
			saveSessionData(editor.getText().toString());
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		wrapTextSwitch.setChecked(!data.getString("wrap_text", "").equals("") && data.getString("wrap_text", "").equals("true"));
		numberDividerSwitch.setChecked(!data.getString("number_divider", "").equals("") && data.getString("number_divider", "").equals("true"));
		ligatureSwitch.setChecked(!data.getString("ligature", "").equals("") && data.getString("ligature", "").equals("true"));
		highlightBracketPairSwitch.setChecked(!data.getString("highlight_bracket_pair", "").equals("") && data.getString("highlight_bracket_pair", "").equals("true"));
		fastScrollSwitch.setChecked(!data.getString("fast_scroll", "").equals("") && data.getString("fast_scroll", "").equals("true"));
		pinNumberDividerSwitch.setChecked(!data.getString("pin_number_divider", "").equals("") && data.getString("pin_number_divider", "").equals("true"));
		if (!data.getString("wrap_text", "").equals("") && data.getString("wrap_text", "").equals("true")) {
			editor.setWordwrap(true);
		}
		else {
			editor.setWordwrap(false);
		}
		if (!data.getString("number_divider", "").equals("") && data.getString("number_divider", "").equals("true")) {
			editor.setLineNumberEnabled(true);
		}
		else {
			editor.setLineNumberEnabled(false);
		}
		if (!data.getString("ligature", "").equals("") && data.getString("ligature", "").equals("true")) {
			editor.setLigatureEnabled(true);
		}
		else {
			editor.setLigatureEnabled(false);
		}
		if (!data.getString("highlight_bracket_pair", "").equals("") && data.getString("highlight_bracket_pair", "").equals("true")) {
			editor.setHighlightBracketPair(true);
		}
		else {
			editor.setHighlightBracketPair(false);
		}
		if (!data.getString("fast_scroll", "").equals("") && data.getString("fast_scroll", "").equals("true")) {
			editor.setHorizontalScrollBarEnabled(true);
			editor.setVerticalScrollBarEnabled(true);
		}
		else {
			editor.setHorizontalScrollBarEnabled(false);
			editor.setVerticalScrollBarEnabled(false);
		}
		if (!data.getString("pin_number_divider", "").equals("") && data.getString("pin_number_divider", "").equals("true")) {
			editor.setPinLineNumber(true);
		}
		else {
			editor.setPinLineNumber(false);
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
	}
	public void _tablayout() {
		viewPager = new ViewPager(this);
		viewPager.setLayoutParams(new ViewGroup.LayoutParams(
		ViewGroup.LayoutParams.MATCH_PARENT, 
		ViewGroup.LayoutParams.MATCH_PARENT
		));
		
		if (main == null || second == null) {
				throw new IllegalStateException("Content views must be initialized before creating adapter");
		}
		
		MyPagerAdapter pagadapter = new MyPagerAdapter();
		viewPager.setAdapter(pagadapter);
		viewPager.setCurrentItem(0);
		
		if (b1 != null) {
				b1.addView(viewPager);
		}
		
		viewPager.setPageTransformer(true, new DepthPageTransformer());
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
				@Override
				public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
			
				
				@Override
				public void onPageSelected(int position) {
						switch (position) {
								case 0:
					            TextView2.setText("Formatting");
								break;
								case 1:
					            TextView2.setText("Properties");
								break;
						}
				}
				
			
				@Override
				public void onPageScrollStateChanged(int state) { }
		});
	}
	
	
	class MyPagerAdapter extends androidx.viewpager.widget.PagerAdapter {
			private final int PAGE_COUNT = 2;
			
		
			@Override
			public int getCount() {
					return PAGE_COUNT;
			}
			
		
			@Override
			public Object instantiateItem(ViewGroup collection, int position) {
					LayoutInflater inflater = LayoutInflater.from(collection.getContext());
					View containerView = inflater.inflate(R.layout.test, collection, false);
					LinearLayout container = containerView.findViewById(R.id.linear1);
					
					View contentView = getContentViewForPosition(position);
					if (contentView != null) {
							ViewGroup parent = (ViewGroup) contentView.getParent();
							if (parent != null) {
									parent.removeView(contentView);
							}
							container.addView(contentView);
					}
					
					collection.addView(containerView);
					return containerView;
			}
		
			
			private View getContentViewForPosition(int position) {
					switch (position) {
							case 0: return main;
							case 1: return second;
							default: return null;
					}
			}
		
			
			@Override
			public void destroyItem(ViewGroup collection, int position, Object view) {
					collection.removeView((View) view);
			}
			
		
			@Override
			public boolean isViewFromObject(View view, Object object) {
					return view == object;
			}
		
			
			@Override
			public CharSequence getPageTitle(int position) {
					switch (position) {
							case 0: return "Main";
							case 1: return "Second";
							default: return null;
					}
			}
	}
	
	
	public void _showMenu(final boolean _b) {
		Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
		Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
		if (_b) {
			checkKeyboardListener();
			if (isKeyboardVisible) {
				hideKeyboard();
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						menu.startAnimation(slideUp);
						_fab.startAnimation(slideDown);
						menu.setVisibility(View.VISIBLE);
						_fab.setVisibility(View.GONE);
						if (!data.getString("symbol_tabs", "").equals("false")) {
							if (landscape) {
								symb.startAnimation(slideDown);
								symb.setVisibility(View.GONE);
							}
						}
					}
				}, 350);
			}
			else {
				menu.startAnimation(slideUp);
				_fab.startAnimation(slideDown);
				menu.setVisibility(View.VISIBLE);
				_fab.setVisibility(View.GONE);
				if (!data.getString("symbol_tabs", "").equals("false")) {
					if (landscape) {
						symb.startAnimation(slideDown);
						symb.setVisibility(View.GONE);
					}
				}
			}
		}
		else {
			menu.startAnimation(slideDown);
			_fab.startAnimation(slideUp);
			menu.setVisibility(View.GONE);
			_fab.setVisibility(View.VISIBLE);
			if (!data.getString("symbol_tabs", "").equals("false")) {
				if (symb.getVisibility() == View.GONE) {
					symb.startAnimation(slideUp);
					symb.setVisibility(View.VISIBLE);
				}
			}
		}
		if (data.getString("symbol_tabs", "").equals("false")) {
			_fab.setTranslationY((float)(-40));
		}
		else {
			_fab.setTranslationY((float)(0));
		}
	}
	
	
	public void _OrientationChange(final Configuration _configuration) {
		if (_configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			l2.setOrientation(LinearLayout.HORIZONTAL);
			l2.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
			int width = (int) (Resources.getSystem().getDisplayMetrics().widthPixels * 0.5);
			int height = LinearLayout.LayoutParams.MATCH_PARENT;
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
			
			int marginInDp = 16;
			int marginInPx = (int) TypedValue.applyDimension(
			    TypedValue.COMPLEX_UNIT_DIP, 
			    marginInDp, 
			    Resources.getSystem().getDisplayMetrics()
			);
			
			params.setMargins(marginInPx, marginInPx, marginInPx, marginInPx);
			menu.setLayoutParams(params);
			landscape = true;
		}
		else
		if (_configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
			l2.setOrientation(LinearLayout.VERTICAL);
			l2.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM);
			int width = LinearLayout.LayoutParams.MATCH_PARENT;
			int height = (int) TypedValue.applyDimension(
			    TypedValue.COMPLEX_UNIT_DIP, 
			    340, 
			    getResources().getDisplayMetrics()
			);
			
			int marginInDp = 16;
			int marginInPx = (int) TypedValue.applyDimension(
			    TypedValue.COMPLEX_UNIT_DIP, 
			    marginInDp, 
			    getResources().getDisplayMetrics()
			);
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
			params.setMargins(marginInPx, marginInPx, marginInPx, marginInPx);
			menu.setLayoutParams(params);
			landscape = false;
		}
	}
	
	@Override 
	public void onConfigurationChanged(Configuration _configuration) { 
		super.onConfigurationChanged(_configuration);
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		int screenHeight = getResources().getDisplayMetrics().heightPixels;
		
		if (isWidgetOutOfBounds(web_preview, screenWidth, screenHeight)) {
				adjustWidgetToScreenBounds(web_preview, screenWidth, screenHeight);
		}
		_OrientationChange(_configuration);
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
	
	
	public void _CXYZ_dialog_AdaptiveScale_theme(final AlertDialog.Builder _d) {
			final AlertDialog alert = _d.show();
			
			DisplayMetrics displayMetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			
			int margin = (int) (8 * displayMetrics.density);
			WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
			layoutParams.copyFrom(alert.getWindow().getAttributes());
			layoutParams.width = displayMetrics.widthPixels - (2 * margin);
			layoutParams.height = (int) (displayMetrics.heightPixels * 0.5);
			layoutParams.gravity = Gravity.BOTTOM;
			alert.getWindow().setAttributes(layoutParams);
			
			double dp = 15;
			double logicalDensity = displayMetrics.density;
			int px = (int) Math.ceil(dp * logicalDensity);
			
			alert.getWindow().getDecorView().setBackground(new GradientDrawable() {
					public GradientDrawable getIns(int cornerRadius, int bgColor, int strokeWidth, int strokeColor) {
							this.setCornerRadius(cornerRadius);
							this.setColor(bgColor);
							this.setStroke(strokeWidth, strokeColor);
							return this;
					}
			}.getIns(px, Color.parseColor("#111111"), (int) (1 * logicalDensity), Color.parseColor("#474747")));
			
			alert.getWindow().getDecorView().setPadding(8, 8, 8, 8);
			
			TextView textT = alert.findViewById(android.R.id.message);
			if (textT != null) {
					textT.setTextColor(Color.parseColor("#f9f9f9"));
					textT.setTextIsSelectable(true);
			}
			
			int titleId = getResources().getIdentifier("alertTitle", "id", "android");
			if (titleId > 0) {
					TextView dialogTitle = alert.findViewById(titleId);
					if (dialogTitle != null) {
							dialogTitle.setTextColor(Color.parseColor("#FFFFFF"));
					}
			}
			
			new Handler().postDelayed(() -> {
					if (alert.getButton(AlertDialog.BUTTON_POSITIVE) != null) {
							alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#FF0000"));
					}
					if (alert.getButton(AlertDialog.BUTTON_NEGATIVE) != null) {
							alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#FF0000"));
					}
					if (alert.getButton(AlertDialog.BUTTON_NEUTRAL) != null) {
							alert.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#FF0000"));
					}
			}, 100);
	}
	
	
	public void _CXYZ_Simple_dialog_theme(final AlertDialog.Builder _d) {
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
			
			int titleId = getResources().getIdentifier( "alertTitle", "id", "android" ); 
			
			if (titleId > 0) { 
					TextView dialogTitle = (TextView) alert.getWindow().getDecorView().findViewById(titleId); 
					
					if (dialogTitle != null) {
							dialogTitle.setTextColor(Color.parseColor("#FFFFFF"));
					} 
			}
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
	
	
	public void _setEllipsize(final TextView _textview) {
		_textview.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		_textview.setMarqueeRepeatLimit(-1);
		_textview.setHorizontallyScrolling(true);
		_textview.setSelected(true);
	}
	
	
	public void _exitConfirmationDialog() {
		confirmation.setTitle("Abandon this unsaved file?");
		confirmation.setMessage("This action cannot be canceled! Do you still continue?");
		confirmation.setPositiveButton("Abandon", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				tdata.edit().putString("rse", "false").commit();
				finish();
				overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			}
		});
		confirmation.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		_CXYZ_dialog_theme(confirmation);
	}
	
	
	public void _changeEditorTheme(final String _theme) {
		if (_theme.equals("cobalt")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("choco")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("deluxe")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("erebus")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("inkdeep")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("midnight")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("monokai-dark")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("radioactive-olive")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("solarized-dark")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("space-cadet")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("succulent")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("venom")) {
			tmtheme.edit().putString("theme", _theme).commit();
		} else if (_theme.equals("zenburn")) {
			tmtheme.edit().putString("theme", _theme).commit();
		}
		_reloadTheme();
	}
	
	
	public void _reloadTheme() {
		if (tmtheme.getString("theme", "").equals("cobalt")) {
			changeTheme("cobalt.tmTheme");
			themeState = 1;
		} else if (tmtheme.getString("theme", "").equals("choco")) {
			changeTheme("choco.tmTheme");
			themeState = 0;
		} else if (tmtheme.getString("theme", "").equals("deluxe")) {
			changeTheme("deluxe.tmTheme");
			themeState = 2;
		} else if (tmtheme.getString("theme", "").equals("erebus")) {
			changeTheme("erebus.tmTheme");
			themeState = 3;
		} else if (tmtheme.getString("theme", "").equals("inkdeep")) {
			changeTheme("inkdeep.tmTheme");
			themeState = 4;
		} else if (tmtheme.getString("theme", "").equals("midnight")) {
			changeTheme("midnight.tmTheme");
			themeState = 5;
		} else if (tmtheme.getString("theme", "").equals("monokai-dark")) {
			changeTheme("monokai-dark.tmTheme");
			themeState = 6;
		} else if (tmtheme.getString("theme", "").equals("radioactive-olive")) {
			changeTheme("radioactive-olive.tmTheme");
			themeState = 7;
		} else if (tmtheme.getString("theme", "").equals("solarized-dark")) {
			changeTheme("solarized-dark.tmTheme");
			themeState = 8;
		} else if (tmtheme.getString("theme", "").equals("space-cadet")) {
			changeTheme("space-cadet.tmTheme");
			themeState = 9;
		} else if (tmtheme.getString("theme", "").equals("succulent")) {
			changeTheme("succulent.tmTheme");
			themeState = 10;
		} else if (tmtheme.getString("theme", "").equals("venom")) {
			changeTheme("venom.tmTheme");
			themeState = 11;
		} else if (tmtheme.getString("theme", "").equals("zenburn")) {
			changeTheme("zenburn.tmTheme");
			themeState = 12;
		}
	}
	
	
	public void _changeLanguageSyntax(final String _language) {
		if (_language.equals("c")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("cpp")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("csharp")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("css")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("groovy")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("haskell")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("html")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("java")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("javascript")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("json")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("kotlin")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("lua")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("php")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("python")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("sql")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("typescript")) {
			tmtheme.edit().putString("lang", _language).commit();
		} else if (_language.equals("lua")) {
			tmtheme.edit().putString("lang", _language).commit();
		}
		_reloadLanguage();
	}
	
	
	public void _reloadLanguage() {
		if (tmtheme.getString("lang", "").equals("c")) {
			configureEditor(editor, "c"); 
			langState = 0;
		} else if (tmtheme.getString("lang", "").equals("cpp")) {
			configureEditor(editor, "cpp"); 
			langState = 1;
		} else if (tmtheme.getString("lang", "").equals("csharp")) {
			configureEditor(editor, "csharp"); 
			langState = 2;
		} else if (tmtheme.getString("lang", "").equals("css")) {
			configureEditor(editor, "css"); 
			langState = 3;
		} else if (tmtheme.getString("lang", "").equals("groovy")) {
			configureEditor(editor, "groovy"); 
			langState = 4;
		} else if (tmtheme.getString("lang", "").equals("haskell")) {
			configureEditor(editor, "haskell"); 
			langState = 5;
		} else if (tmtheme.getString("lang", "").equals("html")) {
			configureEditor(editor, "html"); 
			langState = 6;
		} else if (tmtheme.getString("lang", "").equals("java")) {
			configureEditor(editor, "java"); 
			langState = 7;
		} else if (tmtheme.getString("lang", "").equals("javascript")) {
			configureEditor(editor, "javascript"); 
			langState = 8;
		} else if (tmtheme.getString("lang", "").equals("json")) {
			configureEditor(editor, "json"); 
			langState = 9;
		} else if (tmtheme.getString("lang", "").equals("kotlin")) {
			configureEditor(editor, "kotlin"); 
			langState = 10;
		} else if (tmtheme.getString("lang", "").equals("lua")) {
			configureEditor(editor, "lua"); 
			langState = 11;
		} else if (tmtheme.getString("lang", "").equals("php")) {
			configureEditor(editor, "php"); 
			langState = 12;
		} else if (tmtheme.getString("lang", "").equals("python")) {
			configureEditor(editor, "python"); 
			langState = 13;
		} else if (tmtheme.getString("lang", "").equals("sql")) {
			configureEditor(editor, "sql"); 
			langState = 14;
		} else if (tmtheme.getString("lang", "").equals("typescript")) {
			configureEditor(editor, "typescript"); 
			langState = 15;
		}
	}
	
	
	public void _AppDesignerToast(final String _s) {
		// Code generated by App Designer
			DisplayMetrics screen = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(screen);
			double dp = 10;
			double logicalDensity = screen.density;
			int px = (int) Math.ceil(dp * logicalDensity);
			
			Toast customToast = Toast.makeText(EditorActivity.this, _s, Toast.LENGTH_SHORT);
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
	
	
	public void _showTimeFormatDialog() {
		timeFormat = Calendar.getInstance();
		AlertDialog.Builder timeFormats = new AlertDialog.Builder(EditorActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
		timeFormats.setTitle("Add time");
		
		String[] patterns = {
			    "yyyy-MM-dd HH:mm:ss",          // 2023-04-02 15:45:30 (Standard ISO-like format)
			    "yyyy.MM.dd G 'at' HH:mm:ss z", // 2023.04.02 AD at 15:45:30 PDT (Full date with era and timezone <verbose>. Depending on device language)
			    "EEE, MMM d, yy",               // Sun, Apr 2, 23 (Short readable weekday + date. Depending on device language)
			    "h:mm a",                       // 3:45 PM (12-hour clock with AM/PM)
			    "HH:mm:ss:SSS",                 // 15:45:30:123 (Time with milliseconds <for precision>)
			    "yyyyy.MMMMM.dd GGG hh:mm aaa", // 02023.April.02 AD 03:45 PM (Extreme verbose. Depending on device language)
			    "EEE, d MMM yyyy HH:mm:ss Z",   // Sun, 2 Apr 2023 15:45:30 -0700 (RFC 822 style <email headers>. Depending on device language)
			    "yyyy-MM-dd'T'HH:mm:ss*SSSZZZZ",// 2023-04-02T15:45:30-07:00 (ISO-8601 with timezone <technical>)
			    "yyyy MMM dd HH:mm:ss.SSS zzz", // 2023 Apr 02 15:45:30.123 PDT (Depending on device language)
			    "MMM dd HH:mm:ss ZZZZ yyyy",    // Apr 02 15:45:30 -0700 2023 (Depending on device language)
			    "HH:mm:ss",                     // 15:45:30
			    "dd/MM/yy HH:mm:ss ZZZZ",       // 02/04/23 15:45:30 -0700
			    "yyyy-MM-dd HH:mm:ss,SSSZZZZ",  // 2023-04-02 15:45:30,123-0700
			    "MMMM",                         // April (Depending on device language)
			    "EEEE",                         // Sunday (Depending on device language)
			    "MM/dd/yyyy hh:mm a",           // 04/02/2023 03:45 PM
			    "dd-MMM-yyyy",                  // 02-Apr-2023 (Depending on device language)
			    "hh 'o''clock' a, zzzz",        // 03 o'clock PM, Pacific Daylight Time
			    "K:mm a, z",                    // 3:45 PM, PDT
			    "yyyy年MM月dd日 HH:mm:ss",       // 2023年04月02日 15:45:30 (Japanese/Chinese Style)
			    "EEE, dd MMM yyyy HH:mm",       // Sun, 02 Apr 2023 15:45
			    "HH'h'mm'm'ss's'",              // 15h45m30s (Military/Technical)
			    "MMM dd, yyyy h:mm:ss a",       // Apr 02, 2023 3:45:30 PM
			    "yyyy-MM-dd HH:mm:ss.SSSZ",     // 2023-04-02 15:45:30.123-0700
			    "dd.MM.yy",                     // 02.04.23 (European short)
			    "'Today is' EEEE",              // Today is Sunday (Depending on device language)
			    "'The time is' h:mm a",         // The time is 3:45 PM
			    "hh:mm:ss a '('zzzz')'",        // 03:45:30 PM (Pacific Daylight Time)
			    "yyyy/MM/dd'T'HH:mm:ssXXX",     // 2023/04/02T15:45:30-07:00
			    "MMMM dd, yyyy (EEEE)",         // April 02, 2023 (Sunday)
		};
		
		final String[] formattedDates = new String[patterns.length];
		for (int i = 0; i < patterns.length; i++) {
			    formattedDates[i] = new SimpleDateFormat(patterns[i], Locale.getDefault()).format(timeFormat.getTime());
		}
		
		final int[] selectedPosition = {0};
		
		timeFormats.setSingleChoiceItems(formattedDates, 0, (dialog, which) -> {
			    selectedPosition[0] = which;
		});
		
		timeFormats.setPositiveButton("Insert", (dialog, which) -> {
			    CodeEditor codeEditor = (CodeEditor) editor;
			    String timeToInsert = formattedDates[selectedPosition[0]];
			    io.github.rosemoe.sora.text.Cursor cursor = codeEditor.getCursor();
			    int line = cursor.getLeftLine();
			    int column = cursor.getLeftColumn();
			    Content newContent = new Content(codeEditor.getText());
			    newContent.insert(line, column, timeToInsert);
			    codeEditor.setText(newContent);
			    int newColumn = column + timeToInsert.length();
			    codeEditor.setSelection(line, newColumn);
		});
		
		timeFormats.setNegativeButton("Cancel", null);
		
		_CXYZ_dialog_AdaptiveScale_theme(timeFormats);
	}
	
	
	public void _showColorPickerDialog() {
		final AlertDialog ColorPicker = new AlertDialog.Builder(EditorActivity.this).create();
		LayoutInflater inflater = getLayoutInflater();
		View colorPickerView = inflater.inflate(R.layout.colorpicker_dialog, null);
		ColorPicker.setView(colorPickerView);
		
		final SeekBar a = colorPickerView.findViewById(R.id.alpha);
		final SeekBar r = colorPickerView.findViewById(R.id.red);
		final SeekBar g = colorPickerView.findViewById(R.id.green);
		final SeekBar b = colorPickerView.findViewById(R.id.blue);
		final TextView aval = colorPickerView.findViewById(R.id.aval);
		final TextView rval = colorPickerView.findViewById(R.id.rval);
		final TextView gval = colorPickerView.findViewById(R.id.gval);
		final TextView bval = colorPickerView.findViewById(R.id.bval);
		
		final LinearLayout cancel = colorPickerView.findViewById(R.id.cancel_button);
		final LinearLayout insert = colorPickerView.findViewById(R.id.insert_button);
		
		final LinearLayout colorPreview = colorPickerView.findViewById(R.id.color_preview);
		final LinearLayout backgroundDrawable = colorPickerView.findViewById(R.id.background_drawable);
		final LinearLayout bg = colorPickerView.findViewById(R.id.background);
		final EditText hex = colorPickerView.findViewById(R.id.hexinput);
		
		_gradDrawable("#111111", "#474747", 1, 10, 10, false, bg);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, cancel);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, insert);
		
		GradientDrawable gradient = new GradientDrawable();
		gradient.setCornerRadius(25);
		gradient.setColor(Color.TRANSPARENT);
		Drawable checkerboard = CheckerboardDrawable.create();
		Drawable[] layers = new Drawable[2];
		layers[0] = checkerboard;
		layers[1] = gradient;
		LayerDrawable layeredBackground = new LayerDrawable(layers);
		backgroundDrawable.setBackground(layeredBackground);
		
		colorPreview.setClipToOutline(true);
		
		hex.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
		
		r.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
		g.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.DARKEN);
		b.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.DARKEN);
		
		String tempHex = "#FFFF0000";
		
		try {
				String hexColor = tempHex.replace("#", "");
				hex.setText(hexColor);
				
				int alpha = Integer.parseInt(hexColor.substring(0, 2), 16);
				int red = Integer.parseInt(hexColor.substring(2, 4), 16);
				int green = Integer.parseInt(hexColor.substring(4, 6), 16);
				int blue = Integer.parseInt(hexColor.substring(6, 8), 16);
				
				a.setProgress(alpha);
				r.setProgress(red);
				g.setProgress(green);
				b.setProgress(blue);
				
				aval.setText(String.valueOf(alpha));
				rval.setText(String.valueOf(red));
				gval.setText(String.valueOf(green));
				bval.setText(String.valueOf(blue));
				
				colorPreview.setBackgroundColor(Color.argb(alpha, red, green, blue));
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
						aval.setText(String.valueOf(a.getProgress()));
						rval.setText(String.valueOf(r.getProgress()));
						gval.setText(String.valueOf(g.getProgress()));
						bval.setText(String.valueOf(b.getProgress()));
						
						String newHex = String.format("%02X%02X%02X%02X", a.getProgress(), r.getProgress(), g.getProgress(), b.getProgress());
						hex.setText(newHex);
						colorPreview.setBackgroundColor(Color.argb(a.getProgress(), r.getProgress(), g.getProgress(), b.getProgress()));
				}
				
				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {}
				
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {}
		};
		
		a.setOnSeekBarChangeListener(seekBarChangeListener);
		r.setOnSeekBarChangeListener(seekBarChangeListener);
		g.setOnSeekBarChangeListener(seekBarChangeListener);
		b.setOnSeekBarChangeListener(seekBarChangeListener);
		
		hex.addTextChangedListener(new TextWatcher() {
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
						if (s.length() == 8) {
								try {
										int alpha = Integer.parseInt(s.subSequence(0, 2).toString(), 16);
										int red = Integer.parseInt(s.subSequence(2, 4).toString(), 16);
										int green = Integer.parseInt(s.subSequence(4, 6).toString(), 16);
										int blue = Integer.parseInt(s.subSequence(6, 8).toString(), 16);
										
										a.setProgress(alpha);
										r.setProgress(red);
										g.setProgress(green);
										b.setProgress(blue);
										
										colorPreview.setBackgroundColor(Color.argb(alpha, red, green, blue));
								} catch (NumberFormatException e) {
										e.printStackTrace();
								}
						}
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
				
				@Override
				public void afterTextChanged(Editable s) {}
		});
		
		insert.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
						CodeEditor codeEditor = (CodeEditor) editor;
						String finalHex = String.format("#%02X%02X%02X%02X", a.getProgress(), r.getProgress(), g.getProgress(), b.getProgress());
						io.github.rosemoe.sora.text.Cursor cursor = codeEditor.getCursor();
						int line = cursor.getLeftLine();
						int column = cursor.getLeftColumn();
						Content newContent = new Content(codeEditor.getText());
						newContent.insert(line, column, finalHex);
						codeEditor.setText(newContent);
						int newColumn = column + finalHex.length();
						codeEditor.setSelection(line, newColumn);
						ColorPicker.dismiss();
				}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
						ColorPicker.dismiss();
				}
		});
		
		if (ColorPicker.getWindow() != null) {
				ColorPicker.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		}
		ColorPicker.setCancelable(true);
		ColorPicker.show();
		
	}
	
	
	public void _showWebViewDialog() {
		final AlertDialog webscd = new AlertDialog.Builder(EditorActivity.this).create();
		LayoutInflater inflater = getLayoutInflater();
		View webscdView = inflater.inflate(R.layout.websc, null);
		webscd.setView(webscdView);
		
		final WebView w = webscdView.findViewById(R.id.webview);
		final EditText e = webscdView.findViewById(R.id.edittext);
		final LinearLayout stoolbar = webscdView.findViewById(R.id.search_tollbar);
		
		final LinearLayout cancel = webscdView.findViewById(R.id.cancel_button);
		final LinearLayout insert = webscdView.findViewById(R.id.insert_button);
		
		final ImageView search = webscdView.findViewById(R.id.search);
		final LinearLayout bg = webscdView.findViewById(R.id.background);
		final EditText url = webscdView.findViewById(R.id.url);
		
		_gradDrawable("#111111", "#474747", 1, 10, 10, false, bg);
		_gradDrawable("#111111", "#474747", 1, 10, 10, false, stoolbar);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, cancel);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, insert);
		
		WebSettings webSettings = w.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setDisplayZoomControls(false);
		webSettings.setDomStorageEnabled(true);
		webSettings.setDatabaseEnabled(true);
		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			    webSettings.setAllowFileAccessFromFileURLs(false);
			    webSettings.setAllowUniversalAccessFromFileURLs(false);
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			    webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
		}
		
		w.setWebViewClient(new WebViewClient() {
			    @Override
			    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
				        return false;
				    }
			
			    @Override
			    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
				        runOnUiThread(() -> {
					            w.loadUrl("about:blank");
					        });
				    }
			
			    @Override
			    public void onPageFinished(WebView view, String url) { }
		});
		
		url.addTextChangedListener(new TextWatcher() {
			    private Timer timer = new Timer();
			    private final long DELAY = 1000;
			
			    @Override
			    public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			    @Override
			    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			    @Override
			    public void afterTextChanged(Editable s) {
				        timer.cancel();
				        timer = new Timer();
				        timer.schedule(new TimerTask() {
					            @Override
					            public void run() {
						                runOnUiThread(() -> {
							                    String urlText = url.getText().toString().trim();
							                    if (!urlText.isEmpty()) {
								                        if (!urlText.startsWith("http://") && !urlText.startsWith("https://")) {
									                            urlText = "https://" + urlText;
									                            url.setText(urlText);
									                        }
								                        w.loadUrl(urlText);
								                    }
							                });
						            }
					        }, DELAY);
				    }
		});
		
		search.setOnClickListener(v -> {
			    String urlText = url.getText().toString().trim();
			    if (!urlText.isEmpty()) {
				        if (!urlText.startsWith("http://") && !urlText.startsWith("https://")) {
					            urlText = "https://" + urlText;
					            url.setText(urlText);
					        }
				        
				        w.loadUrl(urlText);
				        sourcecode.startRequestNetwork(
				            RequestNetworkController.GET, 
				            urlText, 
				            "Result", 
				            new RequestNetwork.RequestListener() {
					                @Override
					                public void onResponse(String tag, String response, HashMap<String, Object> headers) {
						                    runOnUiThread(() -> e.setText(response));
						                }
					
					                @Override
					                public void onErrorResponse(String tag, String message) {
						                    runOnUiThread(() -> {
							                         e.setText("Request failed: " + message);
							                    });
						                }
					            }
				        );
				    }
		});
		
		insert.setOnClickListener(v -> {
			    String text = e.getText().toString();
			    if (!text.isEmpty()) {
				        insertTextInEditor(editor, text);
				    }
			    webscd.dismiss();
		});
		
		cancel.setOnClickListener(v -> webscd.dismiss());
		
		if (webscd.getWindow() != null) {
			    webscd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		}
		webscd.setCancelable(false);
		webscd.show();
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
	
	
	public void _showReplaceTextDialog() {
		final AlertDialog TextReplacer = new AlertDialog.Builder(EditorActivity.this).create();
		LayoutInflater inflater = getLayoutInflater();
		View textReplacerView = inflater.inflate(R.layout.replace, null);
		TextReplacer.setView(textReplacerView);
		
		final LinearLayout cancel = textReplacerView.findViewById(R.id.cancel_button);
		final LinearLayout insert = textReplacerView.findViewById(R.id.insert_button);
		
		final LinearLayout bg = textReplacerView.findViewById(R.id.background);
		final EditText ref = textReplacerView.findViewById(R.id.ref);
		final EditText rep = textReplacerView.findViewById(R.id.rep);
		
		_gradDrawable("#111111", "#474747", 1, 10, 10, false, bg);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, cancel);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, insert);
		_gradDrawable("#282828", "#474747", 1, 15, 3, true, ref);
		_gradDrawable("#282828", "#474747", 1, 15, 3, true, rep);
		
		insert.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
						if (ref.getText().toString().equals("") || rep.getText().toString().equals("")) {
								((EditText)ref).setError(" Text can't be blank");
								((EditText)rep).setError(" Text can't be blank");
						}
						else {
								saveStateForUndo();
					            editor.setText(editor.getText().toString().replace(ref.getText().toString(), rep.getText().toString()));
						}
						TextReplacer.dismiss();
				}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
						TextReplacer.dismiss();
				}
		});
		
		if (TextReplacer.getWindow() != null) {
				TextReplacer.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		}
		TextReplacer.setCancelable(false);
		TextReplacer.show();
		
	}
	
	
	public void _showBase64ConvDialog() {
		final AlertDialog Base64Conv = new AlertDialog.Builder(EditorActivity.this).create();
		LayoutInflater inflater = getLayoutInflater();
		View base64ConvView = inflater.inflate(R.layout.base64, null);
		Base64Conv.setView(base64ConvView);
		
		final LinearLayout cancel = base64ConvView.findViewById(R.id.cancel_button);
		final LinearLayout insert = base64ConvView.findViewById(R.id.insert_button);
		
		final LinearLayout bg = base64ConvView.findViewById(R.id.background);
		final EditText edittext = base64ConvView.findViewById(R.id.edittext);
		final EditText output = base64ConvView.findViewById(R.id.output);
		
		_gradDrawable("#111111", "#474747", 1, 10, 10, false, bg);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, cancel);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, insert);
		_gradDrawable("#282828", "#474747", 1, 15, 3, true, edittext);
		_gradDrawable("#282828", "#474747", 1, 15, 3, true, output);
		
		final boolean[] isEditing = {false};
		
		edittext.addTextChangedListener(new TextWatcher() {
			    @Override
			    public void onTextChanged(CharSequence s, int start, int before, int count) {
				        if (isEditing[0]) return;
				        isEditing[0] = true;
				        try {
					            byte[] utf8Bytes = s.toString().getBytes(StandardCharsets.UTF_8);
					            String base64Encoded = Base64.encodeToString(utf8Bytes, Base64.NO_WRAP);
					            output.setText(base64Encoded);
					        } catch (Exception e) {
					            output.setText("Encoding Error");
					        }
				        isEditing[0] = false;
				    }
			
			    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			   
			    @Override public void afterTextChanged(Editable s) {}
		});
		
		
		output.addTextChangedListener(new TextWatcher() {
			    @Override
			    public void onTextChanged(CharSequence s, int start, int before, int count) {
				        if (isEditing[0]) return;
				        isEditing[0] = true;
				        try {
					            byte[] decodedBytes = Base64.decode(s.toString(), Base64.DEFAULT);
					            String decodedText = new String(decodedBytes, StandardCharsets.UTF_8);
					            edittext.setText(decodedText);
					        } catch (Exception e) {
					            edittext.setText("Invalid Base64");
					        }
				        isEditing[0] = false;
				    }
			 
			    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			 
			    @Override public void afterTextChanged(Editable s) {}
		});
		
		insert.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
				        insertTextInEditor(editor, output.getText().toString());
						Base64Conv.dismiss();
				}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
						Base64Conv.dismiss();
				}
		});
		
		if (Base64Conv.getWindow() != null) {
				Base64Conv.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		}
		Base64Conv.setCancelable(false);
		Base64Conv.show();
		
	}
	
	
	public void _showBinConvDialog() {
		final AlertDialog BinConv = new AlertDialog.Builder(EditorActivity.this).create();
		LayoutInflater inflater = getLayoutInflater();
		View binConvView = inflater.inflate(R.layout.base64, null);
		BinConv.setView(binConvView);
		
		final LinearLayout cancel = binConvView.findViewById(R.id.cancel_button);
		final LinearLayout insert = binConvView.findViewById(R.id.insert_button);
		final TextView title = binConvView.findViewById(R.id.title);
		final LinearLayout bg = binConvView.findViewById(R.id.background);
		final EditText edittext = binConvView.findViewById(R.id.edittext);
		final EditText output = binConvView.findViewById(R.id.output);
		
		_gradDrawable("#111111", "#474747", 1, 10, 10, false, bg);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, cancel);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, insert);
		_gradDrawable("#282828", "#474747", 1, 15, 3, true, edittext);
		_gradDrawable("#282828", "#474747", 1, 15, 3, true, output);
		
		title.setText("UTF-8 → Binary");
		output.setHint("Output (Binary)");
		
		final boolean[] isEditing = {false};
		
		edittext.addTextChangedListener(new TextWatcher() {
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
						if (isEditing[0]) return;
						isEditing[0] = true;
				
						try {
								StringBuilder binary = new StringBuilder();
								byte[] bytes = s.toString().getBytes(StandardCharsets.UTF_8);
								for (byte b : bytes) {
										String bin = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
										binary.append(bin).append(" ");
								}
								output.setText(binary.toString().trim());
						} catch (Exception e) {
								output.setText("Error encoding to binary.");
						}
				
						isEditing[0] = false;
				}
			
				@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
				@Override public void afterTextChanged(Editable s) {}
		});
		
		output.addTextChangedListener(new TextWatcher() {
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
						if (isEditing[0]) return;
						isEditing[0] = true;
				
						try {
								String[] binaryStrings = s.toString().trim().split(" ");
								ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
					
								for (String bin : binaryStrings) {
										if (bin.length() != 8) continue;
										int charCode = Integer.parseInt(bin, 2);
										byteStream.write(charCode);
								}
					
								String decoded = new String(byteStream.toByteArray(), StandardCharsets.UTF_8);
								edittext.setText(decoded);
						} catch (Exception e) {
								edittext.setText("Invalid binary input");
						}
				
						isEditing[0] = false;
				}
			
				@Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
				@Override public void afterTextChanged(Editable s) {}
		});
		
		insert.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
						String binaryText = output.getText().toString();
						if (!binaryText.isEmpty()) {
								insertTextInEditor(editor, binaryText);
						}
						BinConv.dismiss();
				}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
						BinConv.dismiss();
				}
		});
		
		if (BinConv.getWindow() != null) {
				BinConv.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		}
		BinConv.setCancelable(false);
		BinConv.show();
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
