package com.ceditor;

import com.ceditor.PermissionActivity;
import android.Manifest;
import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.media.MediaPlayer;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.exifinterface.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.robinhood.ticker.*;
import io.github.rosemoe.sora.*;
import io.github.rosemoe.sora.langs.textmate.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import androidx.core.content.FileProvider;
import android.util.Log;
import android.content.ContentResolver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.core.widget.NestedScrollView;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.shapes.RectShape;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import org.eclipse.jgit.api.Git;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.ceditor.FallingEffect;



public class MainActivity extends AppCompatActivity {
	
	public final int REQ_CD_FILEPICK = 101;
	
	private Timer _timer = new Timer();
	
	private List<File> gitFileList = new ArrayList<>();
	private File repoDir;
	private String repoUrl, basePath;
	private boolean isCloning = false;
	private StringBuilder logBuilder = new StringBuilder();
	private String clonestatus = "";
	private int screenWidth, screenHeight;
	
	private static final Set<String> TEXT_EXTENSIONS = new HashSet<>(Arrays.asList(
	".txt", ".log", ".md", ".rtf", ".html", ".htm", ".xml", ".json", ".yaml", ".yml",
	".ini", ".cfg", ".toml", ".properties", ".conf", ".java", ".kt", ".dart", ".py",
	".c", ".cpp", ".h", ".cs", ".php", ".js", ".ts", ".rb", ".sh", ".bat", ".cmd",
	".ps1", ".swift", ".go", ".rs", ".lua", ".perl", ".pl", ".tcl", ".r", ".m"
	));
	private static final Set<String> CUSTOM_TEXT_EXTENSIONS = new HashSet<>();
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private DrawerLayout _drawer;
	private String currentdir = "";
	private boolean select = false;
	private double file_operation = 0;
	private HashMap<String, Object> file_info_map = new HashMap<>();
	private boolean dragging = false;
	private boolean usingUriToNavigate = false;
	private String lastdir = "";
	private boolean launch = false;
	private double amountOfBackPressed = 0;
	private HashMap<String, Object> app_temp_data = new HashMap<>();
	private HashMap<String, Object> selected_items = new HashMap<>();
	private String mainDirPathParent = "";
	private String primaryDirPathParent = "";
	private String lastAccesedDir = "";
	private double dirAccessState = 0;
	private boolean readingEULA = false;
	private boolean accesingZip = false;
	private boolean isFileWalking = false;
	private boolean fileContextMenuOnly = false;
	private String versionName = "";
	private boolean isGridMode = false;
	private double position = 0;
	
	private ArrayList<String> liststring = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> filelist = new ArrayList<>();
	private ArrayList<String> listviewLastPosition = new ArrayList<>();
	private ArrayList<String> list_of_selected_items = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> update_response = new ArrayList<>();
	
	private ProgressBar prog;
	private LinearLayout top_line;
	private LinearLayout root_layout;
	private LinearLayout bottom_line;
	private FrameLayout root_frame_layout;
	private LinearLayout event_layout;
	private LinearLayout base_layout;
// 	private FallingEffect imga;
	private LinearLayout info_layout;
	private LinearLayout divider;
	private FrameLayout main_layout;
	private ListView listView;
	private LinearLayout toolbar1;
	private LinearLayout layout_main;
	private FrameLayout linear4;
	private LinearLayout toolbar1_progressbar;
	private LinearLayout toolbar1_inner;
	private ProgressBar progbar;
	private TextView toolbar1_progressbar_text;
	private LinearLayout toolbar1_info;
	private ImageView paste;
	private ImageView cancel;
	private ImageView clear_selection;
	private ImageView update_available;
	private ImageView up;
	private ImageView menu;
	private TickerView title;
	private ProgressBar scrollprogressbar;
	private LinearLayout linear1;
	private TextView subtitle;
	private ImageView imageview10;
	// private View materialRefreshLayout;
	private LinearLayout layout_info;
	private RecyclerView recyclerview1;
	private LinearLayout layout_no_items;
	private ImageView no_file_folder;
	private TextView textview3;
	private LinearLayout allow;
	private TextView textview4;
	private LinearLayout linear5;
	private LinearLayout linear7;
	private LinearLayout LinearLayout10;
	private ImageView goTop;
	private LinearLayout linear6;
	private ImageView goBottom;
	private LinearLayout LinearLayout12;
	private LinearLayout LinearLayout13;
	private LinearLayout LinearLayout11;
	private ImageView imageview12;
	private ImageView imageview13;
	private ImageView imageview11;
	private LinearLayout linear10;
	private LinearLayout alertbox;
	private FrameLayout ContainerLoader;
	private LinearLayout loading;
	private LinearLayout LinearLayout2;
	private LinearLayout LinearLayout7;
	private LinearLayout LinearLayout8;
	private LinearLayout l1;
	private LinearLayout l4;
	private LinearLayout l2;
	private LinearLayout l3;
	private TextView loading_text;
	private LinearLayout LinearLayout14;
// 	private TextView alertbox_context;
// 	private WebView alertbox_webview;
// 	private LinearLayout linear9;
// 	private TextView alertbox_title;
// 	private ImageView imageview14;
// 	private TextView alertbox_button;
	private LinearLayout _drawer_backgroundcontainer;
	private LinearLayout _drawer_rootbg;
	private LinearLayout _drawer_vl1;
	private LinearLayout _drawer_l1;
	private LinearLayout _drawer_linear24;
	private LinearLayout _drawer_l2;
	private ScrollView _drawer_vscroll1;
	private LinearLayout _drawer_linear30;
	private LinearLayout _drawer_linear29;
	private TextView _drawer_textview26;
	private LinearLayout _drawer_l3;
	private ImageView _drawer_appic;
	private LinearLayout _drawer_linear26;
	private ImageView _drawer_settings_icon;
	private TextView _drawer_appname;
	private LinearLayout _drawer_scrollcontainer;
	private LinearLayout _drawer_og1;
	private TextView _drawer_tg2;
	private LinearLayout _drawer_og2;
	private TextView _drawer_TextView30;
	private LinearLayout _drawer_linear33;
	private TextView _drawer_tg3;
	private LinearLayout _drawer_og3;
	private LinearLayout _drawer_o1;
	private LinearLayout _drawer_o2;
	private ImageView _drawer_i4;
	private TextView _drawer_textview4;
	private ImageView _drawer_imageview2;
	private TextView _drawer_textview16;
	private LinearLayout _drawer_o3;
	private LinearLayout _drawer_o4;
	private LinearLayout _drawer_o5;
	private ImageView _drawer_imageview8;
	private Switch _drawer_dthumb;
	private ImageView _drawer_imageview12;
	private Switch _drawer_autoRefrsh;
// 	private ImageView _drawer_imageview14;
	private Switch _drawer_saveLastDirPath;
	private LinearLayout _drawer_linear34;
	private LinearLayout _drawer_linear35;
	private LinearLayout _drawer_linear36;
	private ImageView _drawer_imageview26;
	private TextView _drawer_textview36;
	private ImageView _drawer_imageview27;
	private TextView _drawer_textview37;
	private ImageView _drawer_imageview28;
	private TextView _drawer_textview38;
	private LinearLayout _drawer_o6;
	private LinearLayout _drawer_o7;
	private LinearLayout _drawer_o8;
	private LinearLayout _drawer_o9;
	private ImageView _drawer_imageview23;
	private TextView _drawer_textview33;
	private ImageView _drawer_imageview21;
	private TextView _drawer_textview31;
	private ImageView _drawer_imageview22;
	private TextView _drawer_textview32;
	private ImageView _drawer_imageview20;
	private TextView _drawer_textview29;
	private TextView _drawer_TextView39;
	
	private Intent filepick = new Intent(Intent.ACTION_GET_CONTENT);
	private Intent intent = new Intent();
	private TimerTask timer;
	private SharedPreferences data;
	private ObjectAnimator list_animation = new ObjectAnimator();
	private AlertDialog.Builder dialog;
	private Intent launch_file = new Intent();
	private MediaPlayer audio_player;
	private Calendar Unused_Calendar = Calendar.getInstance();
	private SharedPreferences values;
	private Intent gotoActivity = new Intent();
	private AlertDialog.Builder alert_dialog;
	private AlertDialog.Builder del;
	private Intent open_file = new Intent();
	private SharedPreferences openstring;
	private TimerTask t;
	private Intent newi = new Intent();
	private RequestNetwork updatefy;
	private RequestNetwork.RequestListener _updatefy_request_listener;
	private AlertDialog.Builder update;
	private Intent update_intent = new Intent();
	private SharedPreferences response;
	private AlertDialog.Builder dial;
	private Intent link = new Intent();
	private AlertDialog.Builder changelog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		
		SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
		String langCode = data.getString("app_lang", "en");
		Locale locale = new Locale(langCode);
		Locale.setDefault(locale);
		Resources res = getResources();
		Configuration config = new Configuration(res.getConfiguration());
		config.setLocale(locale);
		res.updateConfiguration(config, res.getDisplayMetrics());

		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(MainActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		prog = findViewById(R.id.prog);
		top_line = findViewById(R.id.top_line);
		root_layout = findViewById(R.id.root_layout);
		bottom_line = findViewById(R.id.bottom_line);
		root_frame_layout = findViewById(R.id.root_frame_layout);
		event_layout = findViewById(R.id.event_layout);
		base_layout = findViewById(R.id.base_layout);
// 		imga = findViewById(R.id.imga);
		info_layout = findViewById(R.id.info_layout);
		divider = findViewById(R.id.divider);
		main_layout = findViewById(R.id.main_layout);
		listView = findViewById(R.id.listView);
		toolbar1 = findViewById(R.id.toolbar1);
		layout_main = findViewById(R.id.layout_main);
		linear4 = findViewById(R.id.linear4);
		toolbar1_progressbar = findViewById(R.id.toolbar1_progressbar);
		toolbar1_inner = findViewById(R.id.toolbar1_inner);
		progbar = findViewById(R.id.progbar);
		toolbar1_progressbar_text = findViewById(R.id.toolbar1_progressbar_text);
		toolbar1_info = findViewById(R.id.toolbar1_info);
		paste = findViewById(R.id.paste);
		cancel = findViewById(R.id.cancel);
		clear_selection = findViewById(R.id.clear_selection);
		update_available = findViewById(R.id.update_available);
		up = findViewById(R.id.up);
		menu = findViewById(R.id.menu);
		title = findViewById(R.id.title);
		scrollprogressbar = findViewById(R.id.scrollprogressbar);
		linear1 = findViewById(R.id.linear1);
		subtitle = findViewById(R.id.subtitle);
		imageview10 = findViewById(R.id.imageview10);
		// materialRefreshLayout = findViewById(R.id.materialRefreshLayout);
		layout_info = findViewById(R.id.layout_info);
		recyclerview1 = findViewById(R.id.recyclerview1);
		layout_no_items = findViewById(R.id.layout_no_items);
		no_file_folder = findViewById(R.id.no_file_folder);
		textview3 = findViewById(R.id.textview3);
		allow = findViewById(R.id.allow);
		textview4 = findViewById(R.id.textview4);
		linear5 = findViewById(R.id.linear5);
		linear7 = findViewById(R.id.linear7);
		LinearLayout10 = findViewById(R.id.LinearLayout10);
		goTop = findViewById(R.id.goTop);
		linear6 = findViewById(R.id.linear6);
		goBottom = findViewById(R.id.goBottom);
		LinearLayout12 = findViewById(R.id.LinearLayout12);
		LinearLayout13 = findViewById(R.id.LinearLayout13);
		LinearLayout11 = findViewById(R.id.LinearLayout11);
		imageview12 = findViewById(R.id.imageview12);
		imageview13 = findViewById(R.id.imageview13);
		imageview11 = findViewById(R.id.imageview11);
		linear10 = findViewById(R.id.linear10);
		alertbox = findViewById(R.id.alertbox);
		ContainerLoader = findViewById(R.id.ContainerLoader);
		loading = findViewById(R.id.loading);
		LinearLayout2 = findViewById(R.id.LinearLayout2);
		LinearLayout7 = findViewById(R.id.LinearLayout7);
		LinearLayout8 = findViewById(R.id.LinearLayout8);
		l1 = findViewById(R.id.l1);
		l4 = findViewById(R.id.l4);
		l2 = findViewById(R.id.l2);
		l3 = findViewById(R.id.l3);
		loading_text = findViewById(R.id.loading_text);
		LinearLayout14 = findViewById(R.id.LinearLayout14);
/*
// 			alertbox_context = findViewById(R.id.alertbox_context);
// 			alertbox_webview = findViewById(R.id.alertbox_webview);
// 			alertbox_webview.getSettings().setJavaScriptEnabled(true);
// 			alertbox_webview.getSettings().setSupportZoom(true);
// 			linear9 = findViewById(R.id.linear9);
// 			alertbox_title = findViewById(R.id.alertbox_title);
// 			imageview14 = findViewById(R.id.imageview14);
// 			alertbox_button = findViewById(R.id.alertbox_button);
			*/
		_drawer_backgroundcontainer = _nav_view.findViewById(R.id.backgroundcontainer);
		_drawer_rootbg = _nav_view.findViewById(R.id.rootbg);
		_drawer_vl1 = _nav_view.findViewById(R.id.vl1);
		_drawer_l1 = _nav_view.findViewById(R.id.l1);
		_drawer_linear24 = _nav_view.findViewById(R.id.linear24);
		_drawer_l2 = _nav_view.findViewById(R.id.l2);
		_drawer_vscroll1 = _nav_view.findViewById(R.id.vscroll1);
		_drawer_linear30 = _nav_view.findViewById(R.id.linear30);
		_drawer_linear29 = _nav_view.findViewById(R.id.linear29);
		_drawer_textview26 = _nav_view.findViewById(R.id.textview26);
		_drawer_l3 = _nav_view.findViewById(R.id.l3);
		_drawer_appic = _nav_view.findViewById(R.id.appic);
		_drawer_linear26 = _nav_view.findViewById(R.id.linear26);
		_drawer_settings_icon = _nav_view.findViewById(R.id.settings_icon);
		_drawer_appname = _nav_view.findViewById(R.id.appname);
		_drawer_scrollcontainer = _nav_view.findViewById(R.id.scrollcontainer);
		_drawer_og1 = _nav_view.findViewById(R.id.og1);
		_drawer_tg2 = _nav_view.findViewById(R.id.tg2);
		_drawer_og2 = _nav_view.findViewById(R.id.og2);
		_drawer_TextView30 = _nav_view.findViewById(R.id.TextView30);
		_drawer_linear33 = _nav_view.findViewById(R.id.linear33);
		_drawer_tg3 = _nav_view.findViewById(R.id.tg3);
		_drawer_og3 = _nav_view.findViewById(R.id.og3);
		_drawer_o1 = _nav_view.findViewById(R.id.o1);
		_drawer_o2 = _nav_view.findViewById(R.id.o2);
		_drawer_i4 = _nav_view.findViewById(R.id.i4);
		_drawer_textview4 = _nav_view.findViewById(R.id.textview4);
		_drawer_imageview2 = _nav_view.findViewById(R.id.imageview2);
		_drawer_textview16 = _nav_view.findViewById(R.id.textview16);
		_drawer_o3 = _nav_view.findViewById(R.id.o3);
		_drawer_o4 = _nav_view.findViewById(R.id.o4);
		_drawer_o5 = _nav_view.findViewById(R.id.o5);
		_drawer_imageview8 = _nav_view.findViewById(R.id.imageview8);
		_drawer_dthumb = _nav_view.findViewById(R.id.dthumb);
		_drawer_imageview12 = _nav_view.findViewById(R.id.imageview12);
		_drawer_autoRefrsh = _nav_view.findViewById(R.id.autoRefrsh);
// 		_drawer_imageview14 = _nav_view.findViewById(R.id.imageview14);
		_drawer_saveLastDirPath = _nav_view.findViewById(R.id.saveLastDirPath);
		_drawer_linear34 = _nav_view.findViewById(R.id.linear34);
		_drawer_linear35 = _nav_view.findViewById(R.id.linear35);
		_drawer_linear36 = _nav_view.findViewById(R.id.linear36);
		_drawer_imageview26 = _nav_view.findViewById(R.id.imageview26);
		_drawer_textview36 = _nav_view.findViewById(R.id.textview36);
		_drawer_imageview27 = _nav_view.findViewById(R.id.imageview27);
		_drawer_textview37 = _nav_view.findViewById(R.id.textview37);
		_drawer_imageview28 = _nav_view.findViewById(R.id.imageview28);
		_drawer_textview38 = _nav_view.findViewById(R.id.textview38);
		_drawer_o6 = _nav_view.findViewById(R.id.o6);
		_drawer_o7 = _nav_view.findViewById(R.id.o7);
		_drawer_o8 = _nav_view.findViewById(R.id.o8);
		_drawer_o9 = _nav_view.findViewById(R.id.o9);
		_drawer_imageview23 = _nav_view.findViewById(R.id.imageview23);
		_drawer_textview33 = _nav_view.findViewById(R.id.textview33);
		_drawer_imageview21 = _nav_view.findViewById(R.id.imageview21);
		_drawer_textview31 = _nav_view.findViewById(R.id.textview31);
		_drawer_imageview22 = _nav_view.findViewById(R.id.imageview22);
		_drawer_textview32 = _nav_view.findViewById(R.id.textview32);
		_drawer_imageview20 = _nav_view.findViewById(R.id.imageview20);
		_drawer_textview29 = _nav_view.findViewById(R.id.textview29);
		_drawer_TextView39 = _nav_view.findViewById(R.id.TextView39);
		filepick.setType("*/*");
		filepick.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		dialog = new AlertDialog.Builder(this);
		values = getSharedPreferences("val", Activity.MODE_PRIVATE);
		alert_dialog = new AlertDialog.Builder(this);
		del = new AlertDialog.Builder(this);
		openstring = getSharedPreferences("openstring", Activity.MODE_PRIVATE);
		updatefy = new RequestNetwork(this);
		update = new AlertDialog.Builder(this);
		response = getSharedPreferences("response", Activity.MODE_PRIVATE);
		dial = new AlertDialog.Builder(this);
		changelog = new AlertDialog.Builder(this);
		
		toolbar1_progressbar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		toolbar1_info.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
			}});
		toolbar1_info.setOnLongClickListener(new View.OnLongClickListener(){
			public boolean onLongClick(View v){
				AlertDialog.Builder f = new AlertDialog.Builder(MainActivity.this/*, R.style.CustomDialogTheme*/);
				f.setTitle("Directory Info");
				f.setMessage("Current Path:\n".concat(currentdir.concat(" \n\nLast Modified:\n".concat(new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss").format(new Date(new java.io.File(usingUriToNavigate? "/storage/".concat(currentdir.replaceFirst("primary:", "emulated/0/").replaceFirst(":", "/")): currentdir).lastModified())).concat(" \n\nThis Folder MIME/Media Type:\n".concat(getMIMEType(usingUriToNavigate? "/storage/".concat(currentdir.replaceFirst("primary:", "emulated/0/").replaceFirst(":", "/")): currentdir)))))));
				f.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				_CXYZ_dialog_theme(f);
				return true;
			}
		});
		
		paste.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_modifyFile(file_operation);
				_openDir(currentdir);
				paste.setVisibility(View.GONE);
				cancel.setVisibility(View.GONE);
				_setSubtitle(currentdir);
				recyclerview1.getAdapter().notifyDataSetChanged();
				isFileWalking = false;
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_setSelectionMode(0);
				list_of_selected_items.clear();
				paste.setVisibility(View.GONE);
				cancel.setVisibility(View.GONE);
				isFileWalking = false;
			}
		});
		
		clear_selection.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_setSelectionMode(0);
			}
		});
		
		up.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (select) {
					if (amountOfBackPressed == 0) {
						_AppDesignerToast("Press again to deselect all.");
						amountOfBackPressed++;
						timer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										amountOfBackPressed = 0;
									}
								});
							}
						};
						_timer.schedule(timer, (int)(3000));
					}
					else {
						_setSelectionMode(0);
					}
				}
				else {
					if (currentdir.equals(FileUtil.getExternalStorageDir()) || currentdir.equals("/")) {
						
					}
					else {
						if (usingUriToNavigate) {
							if (!values.getString(lastdir.concat("_root_id"), "").equals("") && currentdir.equals(values.getString(lastdir.concat("_root_id"), ""))) {
								usingUriToNavigate = false;
								_openDir(lastdir.substring((int)(0), (int)(lastdir.lastIndexOf("/"))).trim());
							}
							else {
								_openDir(Uri.parse(values.getString(lastdir + "_uri", "")), currentdir.substring(0, (int) currentdir.lastIndexOf("/") == -1 ? currentdir.lastIndexOf(":") + 1 : currentdir.lastIndexOf("/")).trim());
							}
							return;
						}
						if (currentdir.equals("/storage/emulated")) {
							
						}
						else {
							_openDir(currentdir.substring((int)(0), (int)(currentdir.lastIndexOf("/"))).trim());
						}
					}
				}
			}
		});
		
		menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				View toolbarpopupView = getLayoutInflater().inflate(R.layout.toobarpopup, null);
				final PopupWindow toolbarpopup = new PopupWindow(toolbarpopupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
				LinearLayout background = toolbarpopupView.findViewById(R.id.background);
				ScrollView scroll = toolbarpopupView.findViewById(R.id.scroll);
				LinearLayout container = toolbarpopupView.findViewById(R.id.container);
				LinearLayout o1 = toolbarpopupView.findViewById(R.id.option1);
				ImageView o1ic = toolbarpopupView.findViewById(R.id.option1_icon);
				TextView o1tx = toolbarpopupView.findViewById(R.id.option1_text);
				LinearLayout o2 = toolbarpopupView.findViewById(R.id.option2);
				ImageView o2ic = toolbarpopupView.findViewById(R.id.option2_icon);
				TextView o2tx = toolbarpopupView.findViewById(R.id.option2_text);
				LinearLayout o3 = toolbarpopupView.findViewById(R.id.option3);
				ImageView o3ic = toolbarpopupView.findViewById(R.id.option3_icon);
				TextView o3tx = toolbarpopupView.findViewById(R.id.option3_text);
				LinearLayout o4 = toolbarpopupView.findViewById(R.id.option4);
				ImageView o4ic = toolbarpopupView.findViewById(R.id.option4_icon);
				TextView o4tx = toolbarpopupView.findViewById(R.id.option4_text);
				LinearLayout o5 = toolbarpopupView.findViewById(R.id.option5);
				ImageView o5ic = toolbarpopupView.findViewById(R.id.option5_icon);
				TextView o5tx = toolbarpopupView.findViewById(R.id.option5_text);
				LinearLayout o6 = toolbarpopupView.findViewById(R.id.option6);
				ImageView o6ic = toolbarpopupView.findViewById(R.id.option6_icon);
				TextView o6tx = toolbarpopupView.findViewById(R.id.option6_text);
				LinearLayout s = toolbarpopupView.findViewById(R.id.separator);
				LinearLayout o7 = toolbarpopupView.findViewById(R.id.option7);
				ImageView o7ic = toolbarpopupView.findViewById(R.id.option7_icon);
				TextView o7tx = toolbarpopupView.findViewById(R.id.option7_text);
				LinearLayout o8 = toolbarpopupView.findViewById(R.id.option8);
				ImageView o8ic = toolbarpopupView.findViewById(R.id.option8_icon);
				TextView o8tx = toolbarpopupView.findViewById(R.id.option8_text);
				LinearLayout o9 = toolbarpopupView.findViewById(R.id.option9);
				ImageView o9ic = toolbarpopupView.findViewById(R.id.option9_icon);
				TextView o9tx = toolbarpopupView.findViewById(R.id.option9_text);
				LinearLayout o10 = toolbarpopupView.findViewById(R.id.option10);
				ImageView o10ic = toolbarpopupView.findViewById(R.id.option10_icon);
				TextView o10tx = toolbarpopupView.findViewById(R.id.option10_text);
				TextView an = toolbarpopupView.findViewById(R.id.appname);
				_gradDrawable("#111111", "#474747", 1, 15, 5, false, background);
				_ripple(o1);
				_ripple(o2);
				_ripple(o3);
				_ripple(o4);
				_ripple(o5);
				_ripple(o6);
				_ripple(o7);
				_ripple(o8);
				_ripple(o9);
				if (isGridMode) {
					o3tx.setText("View as list");
					o3ic.setImageResource(R.drawable.content_listview);
				}
				else {
					o3tx.setText("View as grid");
					o3ic.setImageResource(R.drawable.content_grid);
				}
				if (!new java.io.File(currentdir).canRead() || !new java.io.File(currentdir).canWrite()) {
					if (currentdir.startsWith("/storage/") || currentdir.startsWith("/sdcard/Android")) {
						if (dirAccessState == 1) {
							o3.setVisibility(View.VISIBLE);
						}
						else {
							o3.setVisibility(View.GONE);
						}
					}
				}
				if (select) {
					o5.setVisibility(View.VISIBLE);
					o6.setVisibility(View.VISIBLE);
				}
				else {
					o5.setVisibility(View.VISIBLE);
					o6.setVisibility(View.GONE);
				}
				o1.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						_refreshItems("");
						toolbarpopup.dismiss();
					} });
				o2.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						toolbarpopup.dismiss();
						final Wrapper<String> selected = new Wrapper<String>("");
						final AlertDialog sort_dialog = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialogTheme).create();
						View ii = getLayoutInflater().inflate(R.layout.sort, null);
						sort_dialog.setView(ii);
						
						LinearLayout close = ii.findViewById(R.id.close);
						LinearLayout bk = ii.findViewById(R.id.linear1);
						LinearLayout action = ii.findViewById(R.id.action);
						RadioGroup rg1 = ii.findViewById(R.id.radiogroup1);
						RadioGroup rg2 = ii.findViewById(R.id.radiogroup2);
						RadioButton rb1 = ii.findViewById(R.id.radiobutton1);
						RadioButton rb2 = ii.findViewById(R.id.radiobutton2);
						RadioButton rb3 = ii.findViewById(R.id.radiobutton3);
						RadioButton rb4 = ii.findViewById(R.id.radiobutton4);
						RadioButton rb5 = ii.findViewById(R.id.radiobutton5);
						
						rb1.setId(1);
						rb2.setId(2);
						rb3.setId(3);
						rb4.setId(100);
						rb5.setId(101);
						_gradDrawable("#111111", "#474747", 1, 15, 5, false, bk);
						_gradDrawable("#00000000", "#474747", 1, 15, 3, true, close);
						_gradDrawable("#00000000", "#474747", 1, 15, 3, true, action);
						rb1.setChecked(app_temp_data.get("sort_by_0").toString().equals("name"));
						rb2.setChecked(app_temp_data.get("sort_by_0").toString().equals("date_raw"));
						rb3.setChecked(app_temp_data.get("sort_by_0").toString().equals("size_raw"));
						rb4.setChecked((Integer) app_temp_data.get("sort_by_1") == 100);
						rb5.setChecked((Integer) app_temp_data.get("sort_by_1") == 101);
						action.setOnClickListener(new View.OnClickListener(){
							public void onClick(View v){
								final int[] selection = new int[]{rg1.getCheckedRadioButtonId(), rg2.getCheckedRadioButtonId()};
								switch (selection[0]){
									case 2:
									selected.value("date_raw");
									break;
									
									case 3:
									selected.value("size_raw");
									break;
									
									default:
									selected.value("name");
									break;
								}
								app_temp_data = new HashMap<>();
								app_temp_data.put("sort_by_0", selected.value());
								app_temp_data.put("sort_by_1", selection[1]);
								_sortFileList(filelist);
								sort_dialog.dismiss();
							}
						});
						close.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
										sort_dialog.dismiss();
								}
						});
						sort_dialog.show();
						
					} });
				o3.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						if (isGridMode) {
							isGridMode = false;
							switchToList();
							data.edit().putString("grid_mode", "false").commit();
						}
						else {
							isGridMode = true;
							switchToGrid();
							data.edit().putString("grid_mode", "true").commit();
						}
						recyclerview1.setAdapter(new Recyclerview1Adapter(filelist));
						toolbarpopup.dismiss();
					} });
				o4.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						toolbarpopup.dismiss();
						subtitle.performLongClick();
					} });
				o5.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						if (!select) {
							_setSelectionMode(1);
						}
						for(int _repeat135 = 0; _repeat135 < (int)(filelist.size()); _repeat135++) {
							selected_items.put(String.valueOf((long)(_repeat135)), filelist.get((int)_repeat135).get("path").toString());
						}
						_setSelectionMode(2);
						toolbarpopup.dismiss();
					} });
				o6.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						_setSelectionMode(0);
						toolbarpopup.dismiss();
					} });
				o7.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						intent.setClass(getApplicationContext(), SettingsActivity.class);
						startActivity(intent);
						overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
						toolbarpopup.dismiss();
					} });
				o8.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						toolbar1.setVisibility(View.GONE);
						linear10.setVisibility(View.VISIBLE);
						_drawer_linear29.setVisibility(View.GONE);
						_drawer_linear30.setVisibility(View.GONE);
						_drawer_vscroll1.setVisibility(View.GONE);
						_drawer_textview26.setVisibility(View.VISIBLE);
						_drawer_textview26.setText("Under repair...");
						readingEULA = true;
						ValueAnimator animator1 = ValueAnimator.ofFloat(0.75f, 0.5f, 0.25f, 0f, 0.75f);
						animator1.setDuration(1000);
						animator1.setInterpolator(new LinearInterpolator());
						animator1.setRepeatCount(ValueAnimator.INFINITE);
						animator1.addUpdateListener(animation -> {
								float value = (float) animation.getAnimatedValue();
								l1.setAlpha(value);
						});
						animator1.start();
						
						ValueAnimator animator2 = ValueAnimator.ofFloat(0f, 0.75f, 0.5f, 0.5f, 0.25f, 0.25f, 0f);
						animator2.setDuration(1000);
						animator2.setInterpolator(new LinearInterpolator());
						animator2.setRepeatCount(ValueAnimator.INFINITE);
						animator2.addUpdateListener(animation -> {
								float value = (float) animation.getAnimatedValue();
								l2.setAlpha(value);
						});
						animator2.start();
						
						ValueAnimator animator3 = ValueAnimator.ofFloat(0.25f, 0f, 0.75f, 0.5f, 0.5f, 0.25f);
						animator3.setDuration(1000);
						animator3.setInterpolator(new LinearInterpolator());
						animator3.setRepeatCount(ValueAnimator.INFINITE);
						animator3.addUpdateListener(animation -> {
								float value = (float) animation.getAnimatedValue();
								l3.setAlpha(value);
						});
						animator3.start();
						
						ValueAnimator animator4 = ValueAnimator.ofFloat(0.5f, 0.25f, 0f, 0.75f, 0.5f);
						animator4.setDuration(1000);
						animator4.setInterpolator(new LinearInterpolator());
						animator4.setRepeatCount(ValueAnimator.INFINITE);
						animator4.addUpdateListener(animation -> {
								float value = (float) animation.getAnimatedValue();
								l4.setAlpha(value);
						});
						animator4.start();
						t = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/temp_path.txt"));
										finishAffinity();
										overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
										gotoActivity.setClass(getApplicationContext(), PermissionActivity.class);
										startActivity(gotoActivity);
									}
								});
							}
						};
						_timer.schedule(t, (int)(3500));
						toolbarpopup.dismiss();
					} });
				o9.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						finishAffinity();
						overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
					} });
				o10.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						gotoActivity.setClass(getApplicationContext(), TerminalActivity.class);
						gotoActivity.putExtra("path", currentdir);
						startActivity(gotoActivity);
						overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
					} });
				toolbarpopup.setAnimationStyle(android.R.style.Animation_Dialog);
				toolbarpopup.showAsDropDown(menu, 0,0);
				toolbarpopup.setBackgroundDrawable(new BitmapDrawable());
			}
		});
		
		linear1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				View DirShortcutPopupView = getLayoutInflater().inflate(R.layout.dirshortcutspopup, null);
				
				WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
				Display display = windowManager.getDefaultDisplay();
				DisplayMetrics outMetrics = new DisplayMetrics();
				display.getMetrics(outMetrics);
				int screenWidth = outMetrics.widthPixels;
				
				final PopupWindow DirShortcutPopup = new PopupWindow(DirShortcutPopupView, screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT, true);
				
				LinearLayout rootbg = DirShortcutPopupView.findViewById(R.id.rootbg);
				LinearLayout op1 = DirShortcutPopupView.findViewById(R.id.op1);
				LinearLayout op2 = DirShortcutPopupView.findViewById(R.id.op2);
				ImageView ic1 = DirShortcutPopupView.findViewById(R.id.ic1);
				ImageView ic2 = DirShortcutPopupView.findViewById(R.id.ic2);
				LinearLayout line1 = DirShortcutPopupView.findViewById(R.id.line1);
				LinearLayout main = DirShortcutPopupView.findViewById(R.id.main_fore);
				LinearLayout b1 = DirShortcutPopupView.findViewById(R.id.b1);
				LinearLayout b2 = DirShortcutPopupView.findViewById(R.id.b2);
				ListView listView = DirShortcutPopupView.findViewById(R.id.listview1);
				ListView listViewGit = DirShortcutPopupView.findViewById(R.id.listview2);
				LinearLayout sec = DirShortcutPopupView.findViewById(R.id.sec);
				LinearLayout clone = DirShortcutPopupView.findViewById(R.id.clone);
				LinearLayout nofile = DirShortcutPopupView.findViewById(R.id.layout_no_items);
				LinearLayout gotopath = DirShortcutPopupView.findViewById(R.id.gotopath);
				
				final LinearLayout[] currentFocusedView = {DirShortcutPopupView.findViewById(R.id.currentFocusedView)};
				
				_gradDrawable("#141414", "#373737", 1, 15, 5, false, rootbg);
				_gradDrawable("#00000000", "#474747", 1, 15, 3, true, clone);
				_gradDrawable("#00000000", "#474747", 1, 15, 3, true, gotopath);
				_tintAnimation(ic1, false);
				
				listView.setVisibility(View.VISIBLE);
				listView.setClipToOutline(true);
				sec.setVisibility(View.GONE);
				currentFocusedView[0] = op1; 
				
				List<DriveItem> driveItems = getDriveItems();
				DriveListAdapter adapter = new DriveListAdapter(MainActivity.this, driveItems);
				listView.setAdapter(adapter);
				
				listView.setOnItemClickListener((parent, view, position, id) -> {
						DriveItem selectedDrive = driveItems.get(position);
						TextView drivePath = view.findViewById(R.id.storagePath);
						drivePath.setText(selectedDrive.getPath());
						_openDir(selectedDrive.getPath());
						DirShortcutPopup.dismiss();
				});
				
				listView.setOnItemLongClickListener((parent, view, position, id) -> {
						DriveItem selectedDrive = driveItems.get(position);
						ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
						ClipData clip = ClipData.newPlainText("Drive Path", selectedDrive.getPath());
						clipboard.setPrimaryClip(clip);
						
						// Code generated by App Designer
						DisplayMetrics screen = new DisplayMetrics();
						getWindowManager().getDefaultDisplay().getMetrics(screen);
						double dp = 10;
						double logicalDensity = screen.density;
						int px = (int) Math.ceil(dp * logicalDensity);
						
						Toast customToast = Toast.makeText(MainActivity.this, "Path copied to clipboard: " + selectedDrive.getPath(), Toast.LENGTH_SHORT);
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
						
						return true;
				});
				
				
				gitFileList = new ArrayList<>();
				File gitcDir = new File("/storage/emulated/0/.inflps/C-XYZ/ClonedGit");
				
				if (gitcDir.exists()) {
						File[] files = gitcDir.listFiles();
						
						if (files != null) {
								for (File file : files) {
										if (file.isDirectory()) {
												gitFileList.add(file);
										}
								}
								
								Collections.sort(gitFileList, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));
								
								
								if (gitFileList.size() > 10) {
										gitFileList = gitFileList.subList(0, 10);
								}
						}
				}
				
				if (!gitFileList.isEmpty()) {
						DownloadListAdapter adapterGit = new DownloadListAdapter(MainActivity.this, gitFileList);
						listViewGit.setAdapter(adapterGit);
						nofile.setVisibility(View.GONE);
				} else {
						nofile.setVisibility(View.VISIBLE);
						listViewGit.setVisibility(View.GONE);
				}
				
				listViewGit.setOnItemClickListener((parent, view, position, id) -> {
						File selectedFile = gitFileList.get(position);
						TextView drivePath = view.findViewById(R.id.storagePath);
						drivePath.setText(selectedFile.getPath());
						_openDir(selectedFile.getPath());
						DirShortcutPopup.dismiss();
				});
				
				
				op1.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
								if (currentFocusedView[0] != op1) {
										animateSelection(op1, op2, line1, op1, op2);
										currentFocusedView[0] = op1;
							            
							            _tintAnimation(ic1, false);
							            _tintAnimation(ic2, true);
								}
								
								listView.setVisibility(View.VISIBLE);
								sec.setVisibility(View.GONE);
						}
				});
				
				
				op2.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
								if (currentFocusedView[0] != op2) {
										animateSelection(op2, op1, line1, op1, op2);
										currentFocusedView[0] = op2;
							            
							            _tintAnimation(ic1, true);
							            _tintAnimation(ic2, false);
								}
								
								listView.setVisibility(View.GONE);
								sec.setVisibility(View.VISIBLE);
						}
				});
				
				clone.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
								final AlertDialog dialc = new AlertDialog.Builder(MainActivity.this).create();
								
								DisplayMetrics screen = new DisplayMetrics();
								getWindowManager().getDefaultDisplay().getMetrics(screen);
								
								int px = (int) Math.ceil(15 * screen.density);
								int strokeWidth = (int) Math.ceil(1 * screen.density);
								
								dialc.getWindow().setBackgroundDrawable(new GradientDrawable() {
										public GradientDrawable getIns(int cornerRadius, int bgColor, int strokeWidth, int strokeColor) {
												this.setCornerRadius(cornerRadius);
												this.setColor(bgColor);
												this.setStroke(strokeWidth, strokeColor);
												return this;
										}
								}.getIns(px, Color.parseColor("#111111"), strokeWidth, Color.parseColor("#474747")));
								
								View base = getLayoutInflater().inflate(R.layout.view_newfile,null);
								dialc.setView(base);
								
								final TextView textview1 = base.findViewById(R.id.textview1);
								final TextView textview2 = base.findViewById(R.id.textview2);
								LinearLayout close = base.findViewById(R.id.close);
								ImageView imageview1 = base.findViewById(R.id.imageview1);
								LinearLayout create = base.findViewById(R.id.create);
								LinearLayout bk = base.findViewById(R.id.linear1);
								LinearLayout linear3 = base.findViewById(R.id.linear3);
								final EditText name = base.findViewById(R.id.name);
								final CheckBox c1 = base.findViewById(R.id.c1);
								final CheckBox c2 = base.findViewById(R.id.c2);
								/*final*/ TextView subtitle = base.findViewById(R.id.subtitle);
								/*final*/ TextView ann = base.findViewById(R.id.annotation);
								
								_gradDrawable("#00000000", "#474747", 1, 15, 3, true, create);
						        _gradDrawable("#00000000", "#474747", 1, 15, 3, true, close);
								_gradDrawable("262626", "000000", 0, 15, 3, false, linear3);
								_ViewWidthHeight(bk, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
								imageview1.setImageResource(R.drawable.content_right_arrow);
								textview1.setText("Clone git");
								textview2.setText("Clone");
						        subtitle.setVisibility(View.VISIBLE);
						        subtitle.setText("Warning: when cloning large repositories, the device may lag and/or freeze.");
								name.setHint("Repo URL");
								c1.setVisibility(View.GONE);
								c2.setVisibility(View.GONE);
								close.setOnClickListener(new View.OnClickListener(){
										public void onClick(View v){
												dialc.dismiss();
										}
								});
						        
								create.setOnClickListener(new View.OnClickListener(){
										public void onClick(View v){
												repoUrl = name.getText().toString().trim();
												String repoName = repoUrl.substring(repoUrl.lastIndexOf("/") + 1).replace(".git", "");
												File repoFolder = new File(basePath, repoName);
												
												ann.setVisibility(View.VISIBLE);
												
												if (repoUrl.isEmpty()) {
														name.setError("Error: Link is blank!");
														return;
												}
												
												if (!MainActivity.this.isValidGitUrl(repoUrl)) {
														name.setError("Error: Invalid link!");
														return;
												}
												
												if (repoFolder.exists()) {
														dial.setTitle("Warning: Folder exists");
														dial.setMessage("A folder with the same repository name already exists. Overwrite?");
														dial.setPositiveButton("OK", new DialogInterface.OnClickListener() {
																@Override
																public void onClick(DialogInterface _dialog, int _which) {
																		deleteFolder(repoFolder);
																		startCloning(repoUrl, basePath);
																}
														});
														dial.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
																@Override
																public void onClick(DialogInterface _dialog, int _which) {
																		
																}
														});
														_CXYZ_dialog_theme(dial);
												}
												else {
														startCloning(repoUrl, basePath);
												}
												
												final Handler handler = new Handler();
												handler.postDelayed(new Runnable() {
														@Override
														public void run() {
																ann.setText(clonestatus);
																handler.postDelayed(this, 1000); 
														}
												}, 1000);
										}
								});
								
								dialc.show();
						}
				});
				
				
				gotopath.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
								_openDir(gitcDir.getPath());
								DirShortcutPopup.dismiss();
						}
				});
				
				DirShortcutPopup.setAnimationStyle(R.style.PopupAnimation);
				DirShortcutPopup.showAsDropDown(findViewById(R.id.imageview10), 0, 0);
				DirShortcutPopup.setBackgroundDrawable(new BitmapDrawable());
			}
		});
		
		subtitle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linear1.performClick();
			}});
		subtitle.setOnLongClickListener(new View.OnLongClickListener(){
			public boolean onLongClick(View s){
				final AlertDialog dial = new AlertDialog.Builder(MainActivity.this).create();
				
				DisplayMetrics screen = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(screen);
				
				int px = (int) Math.ceil(15 * screen.density);
				int strokeWidth = (int) Math.ceil(1 * screen.density);
				
				dial.getWindow().setBackgroundDrawable(new GradientDrawable() {
					    public GradientDrawable getIns(int cornerRadius, int bgColor, int strokeWidth, int strokeColor) {
						        this.setCornerRadius(cornerRadius);
						        this.setColor(bgColor);
						        this.setStroke(strokeWidth, strokeColor);
						        return this;
						    }
				}.getIns(px, Color.parseColor("#111111"), strokeWidth, Color.parseColor("#474747")));
				
				View base = getLayoutInflater().inflate(R.layout.view_newfile,null);
				dial.setView(base);
				
				final TextView textview1 = base.findViewById(R.id.textview1);
				final TextView textview2 = base.findViewById(R.id.textview2);
				LinearLayout close = base.findViewById(R.id.close);
				ImageView imageview1 = base.findViewById(R.id.imageview1);
				LinearLayout create = base.findViewById(R.id.create);
				LinearLayout bk = base.findViewById(R.id.linear1);
				LinearLayout linear3 = base.findViewById(R.id.linear3);
				final EditText name = base.findViewById(R.id.name);
				final CheckBox c1 = base.findViewById(R.id.c1);
				final CheckBox c2 = base.findViewById(R.id.c2);
				
				_gradDrawable("#00000000", "#474747", 1, 15, 3, true, create);
				_gradDrawable("#00000000", "#474747", 1, 15, 3, true, close);
				_gradDrawable("262626", "000000", 0, 15, 3, false, linear3);
				_ViewWidthHeight(bk, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				 imageview1.setImageResource(R.drawable.content_right_arrow);
				textview1.setText("Go to");
				textview2.setText("Go");
				name.setText(currentdir);
				name.setHint("Path");
				c1.setVisibility(View.GONE);
				c2.setVisibility(View.GONE);
				close.setOnClickListener(new View.OnClickListener(){
					public void onClick(View v){
						dial.dismiss();
					}});
				create.setOnClickListener(new View.OnClickListener(){
					public void onClick(View v){
						if (!name.getText().toString().trim().equals("")) {
							String path = name.getText().toString().trim();
							if (!path.startsWith("/")) {
								path = "/storage/".concat(path.replaceFirst("primary:", "emulated/0/").replaceFirst(":", "/"));
							}
							if (FileUtil.isExistFile(path)) {
								if (usingUriToNavigate) {
									try {
										_openDir(Uri.parse(values.getString(lastdir.concat("_uri"), "")), name.getText().toString());
									} catch (Exception e) {}
								}
								else {
									_openDir(name.getText().toString().trim());
								}
								dial.dismiss();
							}
							else {
								name.setError("The specified directory does not exist");
							}
						}
					}});
				
				dial.show();
				return true;
			}
		});
		
		recyclerview1.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int _scrollState) {
				super.onScrollStateChanged(recyclerView, _scrollState);
				LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerview1.getLayoutManager();
				position = layoutManager.findFirstVisibleItemPosition();
			}
			
			@Override
			public void onScrolled(RecyclerView recyclerView, int _offsetX, int _offsetY) {
				super.onScrolled(recyclerView, _offsetX, _offsetY);
				if (data.getString("collapsing_toolbar", "").equals("true")) {
					if (_offsetY < 0) {
							goBottom.animate().translationY(-132f).setDuration(200).start();
					} else {
							goBottom.animate().translationY(0f).setDuration(200).start();
					}
				}
			}
		});
		
		allow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (currentdir.startsWith("/storage") || currentdir.startsWith("/sdcard")) {
					Intent local = new Intent();
					local.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
					local.setAction(Intent.ACTION_OPEN_DOCUMENT_TREE);
					if (currentdir.startsWith(FileUtil.getExternalStorageDir().concat("/Android/data")) || currentdir.startsWith("/sdcard/Android/data")) {
						local.putExtra(android.provider.DocumentsContract.EXTRA_INITIAL_URI, Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid/document/primary%3AAndroid%2Fdata"));
					}
					else {
						if (currentdir.startsWith(FileUtil.getExternalStorageDir().concat("/Android/obb")) || currentdir.startsWith("/sdcard/Android/obb")) {
							local.putExtra(android.provider.DocumentsContract.EXTRA_INITIAL_URI, Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid/document/primary%3AAndroid%2Fobb"));
						}
						else {
							try {
								if (usingUriToNavigate) {
									local.putExtra(android.provider.DocumentsContract.EXTRA_INITIAL_URI, Uri.parse(currentdir));
								}
								else {
									String ta = currentdir.substring((int)(currentdir.indexOf("/storage/") + 9), (int)(currentdir.length()));
									ta = ta.replaceFirst("emulated/0/", "primary").replaceFirst("emulated/0", "primary");
									if (!ta.contains("/")) {
										ta = ta.concat("%3A");
									}
									ta = "content://com.android.externalstorage.documents/tree/".concat(ta.replaceFirst("/", "%3A").replace("/", "%2F").concat("/document/".concat(ta.replaceFirst("/", "%3A").replace("/", "%2F"))));
									local.putExtra(android.provider.DocumentsContract.EXTRA_INITIAL_URI, Uri.parse(ta));
									SketchwareUtil.showMessage(getApplicationContext(), ta);
								}
							}catch(Exception e){
								SketchwareUtil.showMessage(getApplicationContext(), e.toString());
							}
						}
					}
					lastdir = currentdir;
					startActivityForResult(local, 43);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "You cannot do this.");
				}
			}
		});
		
		goTop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				recyclerview1.smoothScrollToPosition(0);
			}
		});
		
		goBottom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				recyclerview1.smoothScrollToPosition(recyclerview1.getAdapter().getItemCount() - 1);
			}
		});
		
		imageview12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.openDrawer(GravityCompat.START);
			}
		});
		
		imageview13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				up.performClick();
			}
		});
		
		imageview11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				menu.performClick();
			}
		});
		
		loading.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
// 		alertbox_webview.setWebViewClient(new WebViewClient() {
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
		
// 		alertbox_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				openstring.edit().putString("eula", "t").commit();
				_EULA();
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (select) {
					
				}
				else {
					newi.putExtra("path", currentdir);
					newi.setClass(getApplicationContext(), NewFileActivity.class);
					startActivity(newi);
				}
			}
		});
		
		_updatefy_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				String latestVersion = _response.replaceAll("[^0-9.]", "").trim();
				if (latestVersion == null || latestVersion.trim().isEmpty()) {
					return;
				}
				try {
					PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
					String currentVersion = packageInfo.versionName;
					if (compareVersions(currentVersion, latestVersion) < 0) {
						update_available.setVisibility(View.VISIBLE);
						Animator animatortint = AnimatorInflater.loadAnimator(MainActivity.this, R.anim.tint_animation);
						animatortint.setTarget(update_available);
						animatortint.start();
						update_available.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								update.setTitle("Update Available");
								update.setMessage("A new version of the app is available. \nDo you want to update?");
								update.setPositiveButton("Update", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										update_intent.setAction(Intent.ACTION_VIEW);
										update_intent.setData(Uri.parse("https://web.sketchub.in/p/2523"));
										startActivity(update_intent);
									}
								});
								update.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										
									}
								});
								_CXYZ_dialog_theme(update);
							}
						});
					}
				} catch (PackageManager.NameNotFoundException e) {
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_drawer_settings_icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				gotoActivity.setClass(getApplicationContext(), SettingsActivity.class);
				startActivity(gotoActivity);
			}
		});
	}
	
	private void initializeLogic() {
		
		StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
		StrictMode.setVmPolicy(builder.build());
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
		final TickerView tickerView = findViewById(R.id.title); title.setCharacterLists(TickerUtils.provideNumberList());
		final float[] initialX_fab = new float[1];
		final float[] initialY_fab = new float[1];
		final float[] dX_fab = new float[1];
		final float[] dY_fab = new float[1];
		final Animation shake_fab = new TranslateAnimation(0, 10, 0, 0);
		final long[] lastTouchDown_fab = new long[1];
		final int TOUCH_MAX_DURATION_fab = 200;
		
		final DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		final int screenWidth = displayMetrics.widthPixels;
		final int screenHeight = displayMetrics.heightPixels;
		shake_fab.setDuration(1500);
		shake_fab.setInterpolator(new CycleInterpolator(7));
		shake_fab.setRepeatCount(Animation.INFINITE);
		
		_fab.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View view, MotionEvent event) {
						switch (event.getAction()) {
								case MotionEvent.ACTION_DOWN:
								initialX_fab[0] = view.getX();
								initialY_fab[0] = view.getY();
								dX_fab[0] = view.getX() - event.getRawX();
								dY_fab[0] = view.getY() - event.getRawY();
								_fab.startAnimation(shake_fab);
								lastTouchDown_fab[0] = System.currentTimeMillis();
								return true;
								case MotionEvent.ACTION_MOVE:
								float newX = event.getRawX() + dX_fab[0];
								float newY = event.getRawY() + dY_fab[0];
								if (newX < 0) newX = 0;
								if (newX + view.getWidth() > screenWidth) newX = screenWidth - view.getWidth();
								if (newY < 0) newY = 0;
								if (newY + view.getHeight() > screenHeight) newY = screenHeight - view.getHeight();
								view.animate()
								.x(newX)
								.y(newY)
								.setDuration(0)
								.start();
								return true;
								case MotionEvent.ACTION_UP:
								_fab.clearAnimation();
								if (System.currentTimeMillis() - lastTouchDown_fab[0] < TOUCH_MAX_DURATION_fab) {
										view.performClick();
								}
								view.animate()
								.x(initialX_fab[0])
								.y(initialY_fab[0])
								.setInterpolator(new OvershootInterpolator())
								.setDuration(500)
								.start();
								return false;
								default:
								return false;
						}
				}
		});
		/*
 * InfLps
 */
		
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
					                startWidth = alertbox.getWidth();
					                startHeight = alertbox.getHeight();
					                startX = alertbox.getX();
					                startY = alertbox.getY();
					                float touchX = event.getRawX();
					                float widgetCenterX = startX + (alertbox.getWidth() / 2);
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
					
					                alertbox.getLayoutParams().width = (int) newWidth;
					                alertbox.getLayoutParams().height = (int) newHeight;
					                alertbox.setX(newX);
					                alertbox.requestLayout();
					                break;
					
					            case MotionEvent.ACTION_UP:
					                float finalX = alertbox.getX();
					                float finalY = alertbox.getY();
					                if (finalX < marginW) {
						                    finalX = 0;
						                } else if (finalX > screenWidth - alertbox.getWidth() - marginW) {
						                    finalX = screenWidth - alertbox.getWidth();
						                }
					                if (finalY < marginH) {
						                    finalY = 0;
						                } else if (finalY > screenHeight - alertbox.getHeight() - marginH) {
						                    finalY = screenHeight - alertbox.getHeight();
						                }
					                alertbox.setX(finalX);
					                alertbox.setY(finalY);
					                break;
					
					            default:
					                break;
					        }
				        return true;
				    }
		};
		
		
		
// 		linear9.setOnTouchListener(resizeHandleListener);
		alertbox.setOnTouchListener(touchListener);
		String appMainPath = "/storage/emulated/0/CEditor/Projects";
		basePath = "/storage/emulated/0/.inflps/C-XYZ/ClonedGit";
		File baseDir = new File(appMainPath);
		File gitDir = new File(basePath);
		if (!baseDir.exists()) {
			baseDir.mkdirs();
		}
		if (!gitDir.exists()) {
			gitDir.mkdirs();
		}
/*
			if (data.getString("falling_effect", "").equals("true")) {
// 				// FallingEffect fallingView = findViewById(R.id.imga);
				// ...
			}
			*/
			// Falling effect blocks disabled due to missing library
			/*
			if (data.getString("falling_effect_bday", "").equals("true")) {
// 				// FallingEffect fallingView = findViewById(R.id.imga);
				// ...
			}
			*/
			
			Calendar calendar = Calendar.getInstance();
			int currentMonth = calendar.get(Calendar.MONTH) + 1;
			int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
			int currentYear = calendar.get(Calendar.YEAR);
			
			String birthMessage = "Happy Birthday!";
			if (birthDay != -1 && birthMonth != -1 && birthYear != -1) {
					boolean isBirthday = (birthMonth == currentMonth && birthDay == currentDay);
					
					if (isBirthday) {
							int age = currentYear - birthYear;
							if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
									age--;
							}
							
							birthMessage = "Happy " + age + (getOrdinalSuffix(age)) + " Birthday!";
							List<Integer> birthdayColors = Arrays.asList(
							Color.RED, 
							Color.BLUE, 
							Color.GREEN, 
							Color.YELLOW, 
							Color.CYAN, 
							Color.MAGENTA, 
							Color.parseColor("#FF6347"),
							Color.parseColor("#FFD700"),
							Color.parseColor("#32CD32"),
							Color.parseColor("#FF1493"),
							Color.parseColor("#FF4500"),
							Color.parseColor("#8A2BE2"),
							Color.parseColor("#20B2AA"),
							Color.parseColor("#FF69B4"),
							Color.parseColor("#00FA9A"),
							Color.parseColor("#D2691E"),
							Color.parseColor("#A52A2A"),
							Color.parseColor("#800080"),
							Color.parseColor("#98FB98"),
							Color.parseColor("#FFC0CB"),
							Color.parseColor("#4682B4"),
							Color.parseColor("#ADFF2F"),
							Color.parseColor("#F0E68C"),
							Color.parseColor("#FF00FF")
							);
							
							fallingView.setEffectType(FallingEffect.EffectType.BIRTHDAY);
							fallingView.setThemeColors(birthdayColors);
							fallingView.setBirthdayMessage(birthMessage);
							fallingView.post(fallingView::startEffect);
					}
			}
			
		}
		_blank();
		_drawer_main_ui();
		toolbar1_progressbar.setVisibility(View.GONE);
		clear_selection.setVisibility(View.GONE);
		divider.setVisibility(View.GONE);
		info_layout.setVisibility(View.GONE);
		update_available.setVisibility(View.GONE);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, allow);
// 		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, alertbox_button);
		_EULA();
		if (openstring.getString("eula", "").equals("")) {
			openstring.edit().putString("eula", "f").commit();
		}
		dirAccessState = 0;
		currentdir = !data.getString("save_path","").equals("") && data.getString("save_path","").equals("true") ? data.getString("save_dir", Environment.getExternalStorageDirectory().toString()) : Environment.getExternalStorageDirectory().toString();
		lastdir = data.getString("lastdir_save_dir", "");
		usingUriToNavigate = !currentdir.startsWith("/");
		app_temp_data = new HashMap<>();
		app_temp_data.put("sort_by_0", "name");
		app_temp_data.put("sort_by_1", 100);
		launch = true;
		accesingZip = false;
		isFileWalking = false;
		fileContextMenuOnly = false;
		list_animation.setTarget(layout_main);
		list_animation.setPropertyName("alpha");
		list_animation.setFloatValues((float)(0), (float)(1));
		list_animation.setInterpolator(new DecelerateInterpolator());
		list_animation.setDuration((int)(450));
		list_animation.start();
		if (data.getString("collapsing_toolbar", "").equals("true")) {
			_collapsingToolbar(listView);
			_collapsingToolbar(recyclerview1);
		}
		_ripple(paste);
		_ripple(cancel);
		_ripple(up);
		_ripple(menu);
		_ripple(clear_selection);
// 		_ripple(linear9);
		_ripple(update_available);
		subtitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		subtitle.setMarqueeRepeatLimit(-1);
		subtitle.setHorizontallyScrolling(true);
		subtitle.setSelected(true);
		goTop.setVisibility(View.GONE);
		goBottom.setVisibility(View.GONE);
		imageview11.setVisibility(View.GONE);
		imageview12.setVisibility(View.GONE);
		imageview13.setVisibility(View.GONE);
		linear10.setVisibility(View.GONE);
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
		recyclerview1.setAdapter(new Recyclerview1Adapter(filelist));
		if (data.getString("fab_size_auto", "").equals("true")) {
			_fab.setSize(FloatingActionButton.SIZE_AUTO);
		}
		_refreshItems("");
		recyclerview1.getAdapter().notifyDataSetChanged();
		recyclerview1.setOnTouchListener(new View.OnTouchListener() {
			    float startX, startY;
			    boolean isHorizontal;
			
			    @Override
			    public boolean onTouch(View v, MotionEvent event) {
				        switch (event.getAction()) {
					            case MotionEvent.ACTION_DOWN:
					                startX = event.getX();
					                startY = event.getY();
					                isHorizontal = false;
					                break;
					            case MotionEvent.ACTION_MOVE:
					                float dx = Math.abs(event.getX() - startX);
					                float dy = Math.abs(event.getY() - startY);
					                isHorizontal = dx > dy;
					
					                if (isHorizontal) {
						                    // materialRefreshLayout.setEnabled(false);
						                } else {
						                    // materialRefreshLayout.setEnabled(true);
						                }
					                break;
					            case MotionEvent.ACTION_UP:
					            case MotionEvent.ACTION_CANCEL:
					                // materialRefreshLayout.setEnabled(true);
					                break;
					        }
				        return false;
				    }
		});
		
		
/*
			materialRefreshLayout.setColorSchemeColors(0xFF4D0000);
			if (data.getString("swipe2refresh_large", "").equals("true")) {
				// materialRefreshLayout.setSize(MaterialRefreshLayout.LARGE);
			}
			else {
				// materialRefreshLayout.setSize(MaterialRefreshLayout.DEFAULT);
			}
			if (data.getString("swipe_bottom", "").equals("true")) {
				// materialRefreshLayout.setDirection(MaterialRefreshLayout.Direction.BOTH);
			}
			else {
				// materialRefreshLayout.setDirection(MaterialRefreshLayout.Direction.TOP);
			}
			materialRefreshLayout.setOnRefreshListener(new Object() {
				    // @Override
				    public void onRefresh(Object direction) {
					        
					// materialRefreshLayout.setRefreshing(false);
					_refreshItems("");
					    }
			});
			*/
		((ViewGroup)toolbar1.getParent()).removeView(toolbar1);
		_toolbar.addView(toolbar1);
		int progressColor = 0xFF474747;
		scrollprogressbar.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(progressColor, PorterDuff.Mode.SRC_IN));
		scrollprogressbar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		_buttonUI();
		updatefy.startRequestNetwork(RequestNetworkController.GET, "https://pastefy.app/MLnEUVxg/raw", "u", _updatefy_request_listener);
		Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
		Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
		Animation slideUpOpposite = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_opposite);
		Animation slideDownOpposite = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_opposite);
		
		recyclerview1.addOnScrollListener(new RecyclerView.OnScrollListener() {
				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
						if (recyclerView.getVisibility() == View.GONE || recyclerView.getAdapter() == null) {
								hideButtons();
								return;
						}
						
						LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
						int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
						int visibleItemCount = layoutManager.getChildCount();
						int totalItemCount = layoutManager.getItemCount();
						boolean isScrollable = (totalItemCount > visibleItemCount);
						scrollprogressbar.setVisibility(isScrollable ? View.VISIBLE : View.INVISIBLE);
						
						if (!isScrollable) {
								hideButtons();
								return;
						}
						
						if (firstVisibleItem == 0) {
								View firstVisibleItemView = recyclerView.getChildAt(0);
								
								if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
										if (goTop.getVisibility() == View.VISIBLE) {
												goTop.startAnimation(slideDownOpposite);
												goTop.setVisibility(View.GONE);
										}
								} else {
										if (goTop.getVisibility() != View.VISIBLE) {
												goTop.startAnimation(slideUpOpposite);
												goTop.setVisibility(View.VISIBLE);
										}
								}
						} else {
								if (goTop.getVisibility() != View.VISIBLE) {
										goTop.startAnimation(slideUpOpposite);
										goTop.setVisibility(View.VISIBLE);
								}
						}
						
						int lastVisiblePosition = firstVisibleItem + visibleItemCount;
						
						if (lastVisiblePosition >= totalItemCount) {
								View lastVisibleItemView = recyclerView.getChildAt(visibleItemCount - 1);
								if (lastVisibleItemView != null && lastVisibleItemView.getBottom() <= recyclerView.getHeight()) {
										if (goBottom.getVisibility() == View.VISIBLE) {
												goBottom.startAnimation(slideDown);
												goBottom.setVisibility(View.GONE);
										}
								} else {
										if (goBottom.getVisibility() != View.VISIBLE) {
												goBottom.startAnimation(slideUp);
												goBottom.setVisibility(View.VISIBLE);
										}
								}
						} else {
								if (goBottom.getVisibility() != View.VISIBLE) {
										goBottom.startAnimation(slideUp);
										goBottom.setVisibility(View.VISIBLE);
								}
						}
						
						if (totalItemCount > 0) {
								View firstVisibleView = layoutManager.findViewByPosition(firstVisibleItem);
								if (firstVisibleView != null) {
										int itemTop = firstVisibleView.getTop();
										int itemHeight = firstVisibleView.getHeight();
										float scrollFraction = firstVisibleItem + (-itemTop / (float) itemHeight);
										int lastVisibleItem = firstVisibleItem + visibleItemCount;
										if (lastVisibleItem >= totalItemCount - 1) {
												scrollprogressbar.setProgress(100);
										} else {
												float progress = (scrollFraction / totalItemCount) * 100;
												scrollprogressbar.setProgress((int) progress);
										}
								}
						}
				}
				
				
				private void hideButtons() {
						if (goTop.getVisibility() == View.VISIBLE) {
								goTop.startAnimation(slideDownOpposite);
								goTop.setVisibility(View.GONE);
						}
						if (goBottom.getVisibility() == View.VISIBLE) {
								goBottom.startAnimation(slideDown);
								goBottom.setVisibility(View.GONE);
						}
				}
		});
		
		
	}
	
	
	public class Wrapper<T> {
			private T obj;
			public Wrapper(T object){
					this.obj = object;
			}
			public boolean value(T object){
					this.obj = object;
					if (object != null || this.obj != null) return true;
					return false;
			}
			public T value(){
					return this.obj;
			}
	}
	
	
	private int compareVersions(String v1, String v2) {
			String[] v1Parts = v1.split("\\.");
			String[] v2Parts = v2.split("\\.");
			
			int length = Math.max(v1Parts.length, v2Parts.length);
			for (int i = 0; i < length; i++) {
					int num1 = (i < v1Parts.length) ? Integer.parseInt(v1Parts[i]) : 0;
					int num2 = (i < v2Parts.length) ? Integer.parseInt(v2Parts[i]) : 0;
					if (num1 != num2) {
							return Integer.compare(num1, num2);
					}
			}
			return 0;
	}
	
	
	public static String getFileExtension(String fileName) {
		    if (fileName == null || fileName.lastIndexOf(".") == -1) {
			        return "";
			    }
		    return fileName.substring(fileName.lastIndexOf(".") + 1);
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
	
	
	public String getOrdinalSuffix(int number) {
		    if (number >= 11 && number <= 13) {
			        return "th";
			    }
		    switch (number % 10) {
			        case 1:  return "st";
			        case 2:  return "nd";
			        case 3:  return "rd";
			        default: return "th";
			    }
	}
	
	
	public void addCustomTextExtension(String extension) {
			if (extension != null && !extension.isEmpty()) {
					CUSTOM_TEXT_EXTENSIONS.add(extension.toLowerCase());
			}
	}
	
	private boolean isTextFile(String path) {
			if (path == null) return false;
			String lowerPath = path.toLowerCase();
			if (TEXT_EXTENSIONS.stream().anyMatch(lowerPath::endsWith)) {
					return true;
			}
			if (CUSTOM_TEXT_EXTENSIONS.stream().anyMatch(lowerPath::endsWith)) {
					return true;
			}
			return isTextBased(path);
	}
	
	private boolean isTextBased(String path) {
			File file = new File(path);
			if (file.length() > 3 * 1024 * 1024) {
					return false;
			}
			try {
					byte[] content = Files.readAllBytes(Paths.get(path));
					int textCharCount = 0;
					int totalBytes = content.length;
					for (byte b : content) {
							if ((b >= 0x20 && b <= 0x7E) || b == 0x09 || b == 0x0A || b == 0x0D) {
									textCharCount++;
							}
					}
					return (double) textCharCount / totalBytes > 0.9;
			} catch (IOException e) {
					e.printStackTrace();
					return false;
			}
	}
	
	private boolean isWritable(String path) {
			if (path == null) return false;
			File file = new File(path);
			return file.canWrite();
	}
	
	private boolean isTextMimeType(String path) {
			try {
					String mimeType = Files.probeContentType(Paths.get(path));
					return mimeType != null && mimeType.startsWith("text/");
			} catch (IOException e) {
					e.printStackTrace();
					return false;
			}
	}
	
	
	private void switchToGrid() {
		    recyclerview1.setLayoutManager(new GridLayoutManager(this, 3));
	}
	
	
	private void switchToList() {
		    recyclerview1.setLayoutManager(new LinearLayoutManager(this));
	}
	
	
	public static String removeLastElement(String path) {
			if (path == null || path.isEmpty()) {
					return path;
			}
			
			int lastSlashIndex = path.lastIndexOf("/");
			if (lastSlashIndex != -1) {
					return path.substring(0, lastSlashIndex);
			}
			return path;
	}
	
	
	private void startCloning(String repoUrl, String basePath) {
			isCloning = true;
			new CloneRepoTask(new CloneStatusCallback() {
					@Override
					public void onStatusUpdate(String status) {
							clonestatus = status; 
					}
			}).execute(repoUrl, basePath);
	}
	
	
	interface CloneStatusCallback {
			void onStatusUpdate(String status);
	}
	
	
	private class CloneRepoTask extends AsyncTask<String, String, String> {
			private final CloneStatusCallback callback;
			private String errorMessage = "";
			
			public CloneRepoTask(CloneStatusCallback callback) {
					this.callback = callback;
			}
			
			@Override
			protected String doInBackground(String... params) {
					String repoUrl = params[0];
					String basePath = params[1];
					
					try {
							GitCloner.cloneRepository(repoUrl, basePath, new GitCloner.CloneCallback() {
									@Override
									public void onProgress(int percent) {
											publishProgress("Progress: " + percent);
									}
									
									@Override
									public void onSuccess(String folderPath) {
											publishProgress("Clone Completed!");
									}
									
									@Override
									public void onError(Exception e) {
											errorMessage = e.getMessage();
									}
									
									@Override
									public void onCancelled() {
											publishProgress("Clone Cancelled!");
									}
							});
							
							return errorMessage.isEmpty() ? "Cloning in progress..." : "Clone Failed: " + errorMessage;
					} catch (Exception e) {
							return "Clone Failed: " + e.getMessage();
					}
			}
			
			@Override
			protected void onProgressUpdate(String... values) {
					if (callback != null) {
							callback.onStatusUpdate(values[0]);
					}
			}
			
			@Override
			protected void onPostExecute(String result) {
					isCloning = false;
					if (callback != null) {
							callback.onStatusUpdate(result);
					}
			}
	}
	
	private void cancelCloning() {
			if (isCloning) {
					GitCloner.cancelCloning();
					clonestatus = "Cancelling...";
			}
	}
	
	
	private boolean isValidGitUrl(String url) {
			return url.matches("^(https://|git@)([\\w\\.-]+)[:/]([\\w\\-]+/)+[\\w\\-]+(\\.git)?$");
	}
	
	
	private void deleteFolder(File folder) {
			if (folder.isDirectory()) {
					File[] children = folder.listFiles();
					if (children != null) {
							for (File child : children) {
									deleteFolder(child);
							}
					}
			}
			folder.delete();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FILEPICK:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
			}
			break;
			default:
			break;
		}
		if (_resultCode == Activity.RESULT_OK) {
			if (_data != null) {
				Uri locals = _data.getData();
				
				getContentResolver().takePersistableUriPermission(locals, _data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION));
				values.edit().putString(lastdir.concat("_uri"), locals.toString()).commit();
				values.edit().putString(lastdir.concat("_root_id"), android.provider.DocumentsContract.getTreeDocumentId(locals)).commit();
				androidx.documentfile.provider.DocumentFile df = androidx.documentfile.provider.DocumentFile.fromTreeUri(this, locals);
				androidx.documentfile.provider.DocumentFile dfi = df.createFile("text/*", "end.q");
				Uri test = dfi.getUri();
				values.edit().putString(lastdir.concat("_absolute_uri_path"), test.toString().substring((int)(0), (int)(test.toString().lastIndexOf("end.q")))).commit();
				try {
					android.provider.DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), test);
				}catch(Exception w){}
				usingUriToNavigate = true;
				_openDir(locals, android.provider.DocumentsContract.getTreeDocumentId(locals));
			}
			else {
				SketchwareUtil.showMessage(getApplicationContext(), "Couldn't retrieve directory.");
			}
		}
		else {
			SketchwareUtil.showMessage(getApplicationContext(), "The permission was not granted.");
		}
		switch (1){
			case 2:
			if(false){
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (readingEULA) {
// 			if (alertbox_webview.canGoBack()) {
// 				alertbox_webview.goBack();
			}
		}
		else {
			if (select) {
				if (amountOfBackPressed == 0) {
					_AppDesignerToast("Press back again to deselect all.");
					amountOfBackPressed++;
					timer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									amountOfBackPressed = 0;
								}
							});
						}
					};
					_timer.schedule(timer, (int)(3000));
				}
				else {
					_setSelectionMode(0);
				}
			}
			else {
				if (currentdir.equals("/storage/emulated")) {
					if (amountOfBackPressed == 0) {
						_AppDesignerToast("Press back again to exit app.");
						amountOfBackPressed++;
						timer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										amountOfBackPressed = 0;
									}
								});
							}
						};
						_timer.schedule(timer, (int)(750));
					}
					else {
						if (!data.getString("flush_cache", "").equals("") && data.getString("flush_cache", "").equals("true")) {
							FileUtil.deleteFile(getApplicationContext().getFilesDir().toString().concat("/cache/image_manager_disk_cache/"));
						}
						_saveCurrentDir(currentdir);
						finish();
					}
				}
				else {
					if (usingUriToNavigate) {
						if (!values.getString(lastdir.concat("_root_id"), "").equals("") && currentdir.equals(values.getString(lastdir.concat("_root_id"), ""))) {
							usingUriToNavigate = false;
							_openDir(lastdir.substring((int)(0), (int)(lastdir.lastIndexOf("/"))).trim());
						}
						else {
							_openDir(Uri.parse(values.getString(lastdir + "_uri", "")), currentdir.substring(0, (int) currentdir.lastIndexOf("/") == -1 ? currentdir.lastIndexOf(":") + 1 : currentdir.lastIndexOf("/")).trim());
						}
						return;
					}
					_openDir(currentdir.substring((int)(0), (int)(currentdir.lastIndexOf("/"))).trim());
				}
			}
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		if (!response.contains("iso")) {
			response.edit().putString("iso", "true").commit();
		}
		_refreshItems("");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		_saveCurrentDir(currentdir);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		_saveCurrentDir(currentdir);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		_saveCurrentDir(currentdir);
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		_refreshItems("");
		_drawer_dthumb.setChecked(!data.getString("disable_thumbnail", "").equals("") && data.getString("disable_thumbnail", "").equals("true"));
		_drawer_autoRefrsh.setChecked(!data.getString("auto_refresh", "").equals("") && data.getString("auto_refresh", "").equals("true"));
		_drawer_saveLastDirPath.setChecked(!data.getString("save_path", "").equals("") && data.getString("save_path", "").equals("true"));
/*
			if (data.getString("swipe_bottom", "").equals("true")) {
				materialRefreshLayout.setDirection(MaterialRefreshLayout.Direction.BOTH);
			}
			else {
				materialRefreshLayout.setDirection(MaterialRefreshLayout.Direction.TOP);
			}
			if (data.getString("swipe2refresh_large", "").equals("true")) {
				materialRefreshLayout.setSize(MaterialRefreshLayout.LARGE);
			}
			else {
				materialRefreshLayout.setSize(MaterialRefreshLayout.DEFAULT);
			}
			*/
		if (data.getString("collapsing_toolbar", "").equals("true")) {
			_collapsingToolbar(recyclerview1);
		}
		if (data.getString("fab_size_auto", "").equals("true")) {
			_fab.setSize(FloatingActionButton.SIZE_AUTO);
			
		}
		else {
			_fab.setSize(FloatingActionButton.SIZE_NORMAL);
			
		}
		_blank();
	}
	public void _setSubtitle(final String _name) {
		subtitle.setText(_name);
		title.setText(getFileName(_name));
	}
	
	
	public String getMIMEType(final String path1){
		String mime1 = null;
		Uri uri1 = Uri.parse(path1);
		    if (ContentResolver.SCHEME_CONTENT.equals(uri1.getScheme())) {
			        ContentResolver cr1 = getApplicationContext().getContentResolver();
			        mime1 = cr1.getType(uri1);
			    } else {
			        String fileExtension1 = MimeTypeMap.getFileExtensionFromUrl(uri1
			                .toString());
			        mime1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
			                fileExtension1.toLowerCase());
			    }
		return(mime1 instanceof String ? mime1 : "*/*");
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
	
	
	public void _openFile(final String _path, final String _ext) {
		if (FileUtil.isDirectory(_path)) {
			_openDir(_path.trim());
			return;
		}
		String path1 = "";
		final String mimeType = getMIMEType(_path);
		path1 = _ext.equals("") ? mimeType : _ext;
		if (path1.startsWith("image")) {
			intent.setClass(getApplicationContext(), PreviewImageFileActivity.class);
			if (usingUriToNavigate) {
				intent.putExtra("path", values.getString(lastdir.concat("_uri"), "").concat("/document/".concat(_path.replaceFirst(":", "%3A").replace("/", "%2F"))));
			}
			else {
				intent.putExtra("path", _path);
			}
			startActivity(intent);
			overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			return;
		}
		if (path1.startsWith("audio")) {
			intent.setClass(getApplicationContext(), AudioplayerActivity.class);
			intent.putExtra("path", _path);
			intent.putExtra("title", getFileName(_path));
			startActivity(intent);
			overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			return;
		}
		if (path1.startsWith("video")) {
			intent.setClass(getApplicationContext(), ContentViewerActivity.class);
			intent.putExtra("type", "video");
			intent.putExtra("path", _path);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			return;
		}
		if (path1.startsWith("font")) {
			final AlertDialog dial = new AlertDialog.Builder(MainActivity.this).create();
			View base = getLayoutInflater().inflate(R.layout.debug,null);
			dial.setView(base);

DisplayMetrics screen = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(screen);
			
			int px = (int) Math.ceil(15 * screen.density);
			int strokeWidth = (int) Math.ceil(1 * screen.density);
			
			dial.getWindow().setBackgroundDrawable(new GradientDrawable() {
					public GradientDrawable getIns(int cornerRadius, int bgColor, int strokeWidth, int strokeColor) {
							this.setCornerRadius(cornerRadius);
							this.setColor(bgColor);
							this.setStroke(strokeWidth, strokeColor);
							return this;
					}
			}.getIns(px, Color.parseColor("#111111"), strokeWidth, Color.parseColor("#474747")));
			
			
			final LinearLayout linear1 = base.findViewById(R.id.linear1);
			final LinearLayout linear5 = base.findViewById(R.id.linear5);
			final ScrollView vscroll2 = base.findViewById(R.id.vscroll2);
			final LinearLayout edit = base.findViewById(R.id.edit);
			final ImageView close = base.findViewById(R.id.close);
			final TextView textview1 = base.findViewById(R.id.textview1);
			final TextView subtitle = base.findViewById(R.id.subtitle);
			final EditText text = base.findViewById(R.id.text);
			
			String fontPath = usingUriToNavigate
			? "/storage/".concat(_path.replaceFirst("primary:", "emulated/0/").replaceFirst(":", "/"))
			: _path;
			
			Typeface typeface = Typeface.createFromFile(fontPath);
			
			linear1.setBackgroundColor(Color.TRANSPARENT);
			
			DisplayMetrics screenf = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(screenf);
			
			int pxs = (int) Math.ceil(15 * screenf.density);
			text.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)pxs, 0xFF282828));
			linear5.setVisibility(View.GONE);
			textview1.setText(getFileName(_path));
			text.setText("The quick brown fox jumps over the lazy dog.\n\nABCDEFGHIJKLMNOPQRSTUVWXYZ\n\nabcdefghijklmnopqrstuvwxyz\n\n1234567890\n\n@#$_&-+()/*\"':;!?");
			subtitle.setText("Font viewer");
			edit.setVisibility(View.GONE);
			text.setEnabled(false);
			text.setTypeface(typeface);
			text.setTextSize(20);
			close.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					dial.dismiss();
				}});
			dial.show();
			return;
		}
		if (path1.endsWith("zip")) {
			intent.setClass(getApplicationContext(), PreviewArchiveFilesActivity.class);
			intent.putExtra("path", _path);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			return;
		}
		if (path1.endsWith("vnd.android.package-archive")) {
			try {
				File apkFile = new File(_path);
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				Uri apkUri = FileProvider.getUriForFile(
				    getApplicationContext(),
				    getApplicationContext().getPackageName() + ".provider", // Authority
				    apkFile
				);
				intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
				intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
				startActivity(intent);
			} catch (Exception e){
				Log.e("APK_INSTALL", "Error installing APK", e);
			}
			return;
		}
		if (isTextFile(_path) || isTextMimeType(path1)) {
			if (isTextBased(_path)) {
				intent.setClass(getApplicationContext(), EditorActivity.class);
				intent.putExtra("path", removeLastElement(_path));
				intent.putExtra("contentPath", _path);
				intent.putExtra("title", getFileName(_path));
				intent.putExtra("type", getFileExtension(getFileName(_path)));
				intent.putExtra("filescope", "editing");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			}
			else {
				_AppDesignerToast("The written file is larger than 3MB.");
			}
			return;
		}
		try {
			launch_file.setAction(Intent.ACTION_VIEW);
			launch_file.setDataAndType(Uri.parse(usingUriToNavigate? values.getString(lastdir.concat("_uri"), "").concat("/document/").concat(_path.replace(":", "%3A").replace("/", "%2F")): "file://".concat(_path)), mimeType);
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
			startActivity(launch_file);
		} catch (Exception f){
			_AppDesignerToast("Error: \n".concat(f.toString()));
		}
		return;
	}
	
	
	public void _openDir(final String _path) {
		Animation sUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
		Animation sDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
		Animation sUpOpposite = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_opposite);
		Animation sDownOpposite = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_opposite);
		
		if (!_path.equals("") && FileUtil.isExistFile(_path)) {
			if (FileUtil.isDirectory(_path)) {
				_setSubtitle(_path.trim());
				up.setAlpha((float)(1));
				allow.setVisibility(View.GONE);
				dirAccessState = 0;
				if (!launch) {
					filelist.clear();
					recyclerview1.getAdapter().notifyDataSetChanged();
				}
				String previous_path = currentdir;
				currentdir = _path.trim();
				recyclerview1.getAdapter().notifyDataSetChanged();
				_refreshFileList(currentdir, liststring, filelist);
				if (launch) {
					launch = false;
					recyclerview1.setAdapter(new Recyclerview1Adapter(filelist));
				}
				paste.setVisibility(!select && list_of_selected_items.size() > 0 && new java.io.File(_path).canWrite() ? View.VISIBLE : View.GONE);
				cancel.setVisibility(!select && list_of_selected_items.size() > 0 ? View.VISIBLE : View.GONE);
				// materialRefreshLayout.setVisibility(filelist.size() == 0 ? View.GONE : View.VISIBLE);
				recyclerview1.setVisibility(filelist.size() == 0 ? View.GONE : View.VISIBLE);
				layout_info.setVisibility(filelist.size() == 0 ? View.VISIBLE : View.GONE);
				
				if (goBottom.getVisibility() == View.VISIBLE) {
					    goBottom.startAnimation(sDown);
				}
				if (goTop.getVisibility() == View.VISIBLE) {
					    goTop.startAnimation(sDownOpposite);
				}
				
				goTop.setVisibility(filelist.size() == 0 ? View.GONE : View.GONE);
				goBottom.setVisibility(filelist.size() == 0 ? View.GONE : View.GONE);
				if (currentdir.equals("/storage/emulated") || currentdir.equals("/")) {
					up.setAlpha((float)(0.7d));
				}
				if (new java.io.File(_path).canWrite()) {
					_fab.show();
				}
				else {
					_fab.hide();
				}
				if (!previous_path.equals(currentdir) && FileUtil.isDirectory(_path)) {
					list_animation.start();
				}
				recyclerview1.getAdapter().notifyDataSetChanged();
				if (new java.io.File(_path).canRead()) {
					textview3.setText("No items.");
					dirAccessState = 0;
				}
				else {
					if (_path.trim().startsWith("/storage")) {
						textview3.setText("To access this folder,\nyou need to grant access\nto this folder.");
						allow.setVisibility(View.VISIBLE);
						dirAccessState = 1;
					}
					else {
						textview3.setText("Inaccessible directory.");
						dirAccessState = 0;
					}
				}
			}
			else {
				_openFile(_path.trim(), getMIMEType(_path.trim()));
			}
		}
	}
	
	public void _openDir(Uri uri_path, String _path){
		Animation sUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
		Animation sDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
		Animation sUpOpposite = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_opposite);
		Animation sDownOpposite = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_opposite);
		
		if (usingUriToNavigate) {
			_setSubtitle(_path.trim());
			_fab.show();
			up.setAlpha((float)(1));
			filelist.clear();
			recyclerview1.getAdapter().notifyDataSetChanged();
			String previous_path = currentdir;
			currentdir = _path.trim();
			_refreshFileList(uri_path, _path, liststring, filelist);
			textview3.setText("No file.");
			dirAccessState = 0;
			paste.setVisibility(!select && list_of_selected_items.size() > 0 ? View.VISIBLE : View.GONE);
			cancel.setVisibility(!select && list_of_selected_items.size() > 0 ? View.VISIBLE : View.GONE);
			// materialRefreshLayout.setVisibility(filelist.size() == 0 ? View.GONE : View.VISIBLE);
			recyclerview1.setVisibility(filelist.size() == 0 ? View.GONE : View.VISIBLE);
			layout_info.setVisibility(filelist.size() == 0 ? View.VISIBLE : View.GONE);
			
			if (goBottom.getVisibility() == View.VISIBLE) {
				    goBottom.startAnimation(sDown);
			}
			if (goTop.getVisibility() == View.VISIBLE) {
				    goTop.startAnimation(sDownOpposite);
			}
			    
			goTop.setVisibility(filelist.size() == 0 ? View.GONE : View.GONE);
			goBottom.setVisibility(filelist.size() == 0 ? View.GONE : View.GONE);
			if (!previous_path.equals(currentdir)) {
				list_animation.start();
			}
			recyclerview1.getAdapter().notifyDataSetChanged();
			if (!androidx.documentfile.provider.DocumentFile.fromTreeUri(this, uri_path).canRead() || !androidx.documentfile.provider.DocumentFile.fromTreeUri(this, uri_path).canWrite()) {
				textview3.setText("To access this folder,\nyou need to grant access\nto this folder.");
				allow.setVisibility(View.VISIBLE);
				dirAccessState = 1;
			}
		}
	}
	
	
	public void _modifyFile(final double _n) {
		new AsyncTask<Void, Integer, String>(){
			final int lt = list_of_selected_items.size();
			double num = 0;
			int lp = 0;
			int failed_items = 0;
			boolean done = false;
			String temp_src = "";
			String temp_dest = "";
			String temp_filename = "";
			String messageresult = "";
			final String workingdir = currentdir;
			ArrayList<String> keys = new ArrayList<String>();
			protected void onPreExecute(){
				_toggleProgressBar();
				toolbar1_progressbar_text.setText("0/".concat(String.valueOf((long)(lt))));
			} protected String doInBackground(Void... v){
				if (!(lt == 0)) {
					SketchwareUtil.getAllKeysFromMap(selected_items, keys);
					for(int _repeat14 = 0; _repeat14 < (int)(lt); _repeat14++) {
						lp = _repeat14;
						temp_src = list_of_selected_items.get((int)(lp));
						temp_filename = getFileName(list_of_selected_items.get(lp));
						final boolean is_directory = new java.io.File(list_of_selected_items.get(lp)).isDirectory();
						if (FileUtil.isExistFile(workingdir.concat("/".concat(getFileName(list_of_selected_items.get(lp)))))) {
							file_operation = _n == 1 && is_directory ? _n + 5 : _n + 1;
						}
						else {
							file_operation = _n == 1 && is_directory  ? _n + 4 : _n;
						}
						if ((file_operation == 2) || (file_operation == 4)) {
							while(true) {
								if ((temp_filename.lastIndexOf(".") == -1) || (file_operation == 6)) {
									if (!FileUtil.isExistFile(workingdir.concat("/".concat(temp_filename.concat(" (".concat(String.valueOf((long)(num + 1)).concat(")"))))))) {
										temp_dest = workingdir.concat("/".concat(temp_filename.concat(" (".concat(String.valueOf((long)(num + 1)).concat(")")))));
										break;
									}
								}
								else {
									if (!FileUtil.isExistFile(workingdir.concat("/".concat(temp_filename.substring((int)(0), (int)(temp_filename.lastIndexOf("."))).concat(" (".concat(String.valueOf((long)(num + 1)).concat(")".concat(temp_filename.substring((int)(temp_filename.lastIndexOf(".")), (int)(temp_filename.length())))))))))) {
										temp_dest = workingdir.concat("/".concat(temp_filename.substring((int)(0), (int)(temp_filename.lastIndexOf("."))).concat(" (".concat(String.valueOf((long)(num + 1)).concat(")".concat(temp_filename.substring((int)(temp_filename.lastIndexOf(".")), (int)(temp_filename.length()))))))));
										break;
									}
								}
								num++;
							}
						}
						else {
							temp_dest = workingdir.concat("/".concat(temp_filename));
						}
						if (FileUtil.isExistFile(temp_src) && !usingUriToNavigate) {
							if (!temp_src.trim().equals(temp_dest.trim())) {
								if ((file_operation == 1) || (file_operation == 2)) {
									FileUtil.copyFile(temp_src, temp_dest);
								}
								if ((file_operation == 3) || (file_operation == 4)) {
									if (!(SettingsActivity.moveFile(temp_src, temp_dest))) {
										failed_items++;
									}
								}
								if ((file_operation == 5) || (file_operation == 6)) {
									_dirOperations(0, temp_src.concat("/"), temp_dest.concat("/"));
								}
								if ((file_operation == 7) || (file_operation == 8)) {
									_dirOperations(1, temp_src.concat("/"), temp_dest.concat("/"));
									if (!app_temp_data.isEmpty() && app_temp_data.containsKey("fh")) {
										failed_items = failed_items + Integer.parseInt(app_temp_data.get("fh").toString());
										app_temp_data.remove("fh");
									}
								}
							}
						}
						publishProgress(lp);
						if ((lp + 1) == lt) {
							done = true;
						}
					}
					if (done) {
						if (lt == 1) {
							if (failed_items == 0) {
								messageresult = list_of_selected_items.get((int)(0)).concat(" was ".concat((_n == 1 || _n == 2 || _n == 5 || _n == 6) ? "copied to " : "moved to ").concat(workingdir.concat(".")));
							}
							else {
								messageresult = list_of_selected_items.get((int)(0)).concat("failed to be moved.");
							}
						}
						else {
							if (failed_items == 0) {
								messageresult = String.valueOf((long)(list_of_selected_items.size())).concat(" items was ".concat((_n == 1 || _n == 2 || _n == 5 || _n == 6) ? "copied to " : "moved to ").concat(workingdir.concat(".")));
							}
							else {
								messageresult = String.valueOf((long)(list_of_selected_items.size())).concat(" items was ".concat((_n == 1 || _n == 2 || _n == 5 || _n == 6) ? "copied to " : "moved to ").concat(workingdir.concat(", ".concat(String.valueOf((long)(failed_items)).concat(" failed to be ".concat((_n == 1 || _n == 2 || _n == 5 || _n == 6) ? "copied." : "moved."))))));
							}
						}
						list_of_selected_items.clear();
						return messageresult;
					}
				}
				else {
					return "No files selected.";
				}
				return null;
			}
			protected void onPostExecute(String messageResult){
				SketchwareUtil.showMessage(getApplicationContext(), messageResult);
				TimerTask
				qw = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_toggleProgressBar();
								toolbar1_progressbar_text.setText("Done");
							}
						});
					}
				};
				_timer.schedule(qw, (int)(165));
				_refreshItems("");
			}
			protected void onProgressUpdate(Integer... num){
				toolbar1_progressbar_text.setText(String.valueOf((long)(num[0])).concat("/".concat(String.valueOf((long)(lt)))));
			}}.execute();
	}
	
	
	public void _saveCurrentDir(final String _path) {
		if (!data.getString("save_path", "").equals("") && data.getString("save_path", "").equals("true")) {
			data.edit().putString("save_dir", _path).commit();
		}
		if (usingUriToNavigate) {
			data.edit().putString("lastdir_save_dir", lastdir).commit();
		}
	}
	
	
	public void _refreshFileList(final String _path, final ArrayList<String> _listString, final ArrayList<HashMap<String, Object>> _listMap) {
		_listMap.clear();
		ArrayList<String> files = new ArrayList<String>();
		FileUtil.listDir(_path, files);
		if (currentdir.equals("/storage/emulated") && FileUtil.isExistFile(FileUtil.getExternalStorageDir())) {
			files.add(FileUtil.getExternalStorageDir());
		}
		if (files != null) {
			try {
				for(int _repeat52 = 0; _repeat52 < (int)(files.size()); _repeat52++) {
					final double num = FileUtil.getFileLength(files.get((int)_repeat52));
					File currentFile = new File(files.get(_repeat52));
					file_info_map = new HashMap<>();
					file_info_map.put("name", getFileName(files.get((int)_repeat52)));
					if (FileUtil.isDirectory(files.get((int)(_repeat52)))) {
						File[] itemsInDir = currentFile.listFiles();
						file_info_map.put("size", "Folder");
						file_info_map.put("absolute_size", 0L);
						if (itemsInDir != null) {
							file_info_map.put("file_count", itemsInDir.length);
						}
						else {
							file_info_map.put("file_count", 0);
						}
					}
					else {
						file_info_map.put("size", getReadableSize(num));
						file_info_map.put("absolute_size", (long) num);
						file_info_map.put("file_count", 0L);
					}
					file_info_map.put("size_raw", (long) num);
					file_info_map.put("date", new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date(new java.io.File(files.get((int)_repeat52)).lastModified())));
					file_info_map.put("date_raw", new java.io.File(files.get((int)_repeat52)).lastModified());
					file_info_map.put("path", files.get((int)(_repeat52)));
					file_info_map.put("mime_type", getMIMEType(files.get(_repeat52)));
					_listMap.add(file_info_map);
				}
			} catch (Exception e){
				SketchwareUtil.showMessage(getApplicationContext(), e.toString());
			}
		}
		_sortFileList(filelist);
	}
	public void _refreshFileList(final Uri uri_path, final String _path, final ArrayList<String> _listString, final ArrayList<HashMap<String, Object>> _listMap){
		if (usingUriToNavigate) {
			_listMap.clear();
			recyclerview1.getAdapter().notifyDataSetChanged();
			up.setVisibility(View.VISIBLE);
			String previous_path = currentdir;
			currentdir = _path.trim();
			_listMap.clear();
			String type = app_temp_data.get("sort_by_0").toString();
			ContentResolver cr = getApplicationContext().getContentResolver();
			Uri uri_lists = android.provider.DocumentsContract.buildChildDocumentsUriUsingTree(uri_path, _path);
			
			android.database.Cursor cur = cr.query(uri_lists, new String[]{
				android.provider.DocumentsContract.Document.COLUMN_DISPLAY_NAME,
				android.provider.DocumentsContract.Document.COLUMN_SIZE,
				android.provider.DocumentsContract.Document.COLUMN_LAST_MODIFIED,
				android.provider.DocumentsContract.Document.COLUMN_DOCUMENT_ID,
				android.provider.DocumentsContract.Document.COLUMN_MIME_TYPE
			}, null, null, null);
			try {
				while (cur.moveToNext()){
					file_info_map = new HashMap<>();
					file_info_map.put("name", cur.getString(0));
					file_info_map.put("size", getReadableSize(cur.getString(1) == null || cur.getString(1) == "" ? 0: Double.parseDouble(cur.getString(1))));
					file_info_map.put("absolute_size", cur.getString(1) == null || cur.getString(1).trim() == "" ? 0L: Long.valueOf(cur.getString(1)));
					file_info_map.put("size_raw", cur.getString(1) == null || cur.getString(1).trim() == "" ? 0L: Long.valueOf(cur.getString(1)));
					file_info_map.put("date", new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date(cur.getString(2) == null || cur.getString(2).trim() == "" ? 0L: Long.valueOf(cur.getString(2)))));
					file_info_map.put("date_raw", cur.getString(2) == null || cur.getString(2).trim() == "" ? 0L: Long.valueOf(cur.getString(2)));
					file_info_map.put("path", cur.getString(3));
					file_info_map.put("mime_type", cur.getString(4));
					_listMap.add(file_info_map);
				}} finally {
				if (cur != null){
					 try {
						  cur.close();
						 } catch (Exception r){
						SketchwareUtil.showMessage(getApplicationContext(), "Error: ".concat(r.toString()));
					}}}
			_sortFileList(filelist);
		}
	}
	
	
	public void _refreshItems(final String _string) {
		if (usingUriToNavigate) {
			_openDir(Uri.parse(values.getString(lastdir.concat("_uri"), "")), currentdir);
		}
		else {
			if (values.getString(currentdir.concat("_uri"), "").trim().equals("")) {
				usingUriToNavigate = false;
				lastdir = "";
				_openDir(currentdir);
			}
			else {
				usingUriToNavigate = true;
				lastdir = currentdir;
				Uri uri = Uri.parse(values.getString(lastdir.concat("_uri"), ""));
				_openDir(uri, android.provider.DocumentsContract.getTreeDocumentId(uri));
			}
		}
	}
	
	
	public void _sortFileList(final ArrayList<HashMap<String, Object>> _listMap) {
		if (!(_listMap.isEmpty() || _listMap == null)) {
			Collections.sort(_listMap, new Comparator<HashMap<String, Object>>(){
				public int compare(HashMap<String, Object> a, HashMap<String, Object> b){




					
					boolean is_a_directory = usingUriToNavigate ? a.get("mime_type").toString().equals(android.provider.DocumentsContract.Document.MIME_TYPE_DIR) : new java.io.File(a.get("path").toString()).isDirectory();
					
					boolean is_b_directory = usingUriToNavigate ? b.get("mime_type").toString().equals(android.provider.DocumentsContract.Document.MIME_TYPE_DIR) : new java.io.File(b.get("path").toString()).isDirectory();
					if (is_a_directory && !is_b_directory) {
						return -1;
					}
					else {
						if (!is_a_directory && is_b_directory) {
							return 1;
						}
						else {
							if ((Integer) app_temp_data.get("sort_by_1") == 101) {
								return (b.get(app_temp_data.get("sort_by_0").toString().toLowerCase()).toString().toLowerCase().compareTo(a.get(app_temp_data.get("sort_by_0").toString().toLowerCase()).toString().toLowerCase()));




							}
							else {
								return (a.get(app_temp_data.get("sort_by_0").toString().toLowerCase()).toString().toLowerCase().compareTo(b.get(app_temp_data.get("sort_by_0").toString().toLowerCase()).toString().toLowerCase()));




							}
						}
					}
				}});
			
			
			Collections.sort(_listMap, new Comparator<HashMap<String, Object>>(){
				public int compare(HashMap<String, Object> a, HashMap<String, Object> b){
					boolean is_a_directory = usingUriToNavigate ? a.get("mime_type").toString().equals(android.provider.DocumentsContract.Document.MIME_TYPE_DIR) : new java.io.File(a.get("path").toString()).isDirectory();
					boolean is_b_directory = usingUriToNavigate ? b.get("mime_type").toString().equals(android.provider.DocumentsContract.Document.MIME_TYPE_DIR) : new java.io.File(b.get("path").toString()).isDirectory();
					if (is_a_directory && !is_b_directory) {
						return -1;
					}
					else {
						if (!is_a_directory && is_b_directory) {
							return 1;
						}
						else {
							if (app_temp_data.get("sort_by_0").toString().equals("date_raw") || app_temp_data.get("sort_by_0").toString().equals("size_raw")) {
								final Long first_number = Long.valueOf(a.get(app_temp_data.get("sort_by_0")).toString());
								final Long second_number = Long.valueOf(b.get(app_temp_data.get("sort_by_0")).toString());
								if ((Integer) app_temp_data.get("sort_by_1") == 101) {
									return first_number.compareTo(second_number);
								}
								else {
									return second_number.compareTo(first_number);
								}
							}
							else {
								if ((Integer) app_temp_data.get("sort_by_1") == 101) {
									return (b.get(app_temp_data.get("sort_by_0").toString().toLowerCase()).toString().toLowerCase().compareTo(a.get(app_temp_data.get("sort_by_0").toString().toLowerCase()).toString().toLowerCase()));




								}
								else {
									return (a.get(app_temp_data.get("sort_by_0").toString().toLowerCase()).toString().toLowerCase().compareTo(b.get(app_temp_data.get("sort_by_0").toString().toLowerCase()).toString().toLowerCase()));




								}
							}
						}
					}
				}});
			
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							recyclerview1.getAdapter().notifyDataSetChanged();
						}
					});
				}
			};
			_timer.schedule(timer, (int)(02));
		}
	}
	
	
	public void _fileContextMenu(final double _position) {
		final
		com.google.android.material.bottomsheet.BottomSheetDialog bottomsheet = new com.google.android.material.bottomsheet.BottomSheetDialog(this);
		
		View layout = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
		bottomsheet.setContentView(layout);
		bottomsheet.show();
		
		// bottomsheet.getWindow().getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundColor(android.R.color.transparent);
		final AlertDialog dial = new AlertDialog.Builder(this, R.style.CustomDialogTheme).create();
		View base = getLayoutInflater().inflate(R.layout.view_newfile,null);
		dial.setView(base);
		
		boolean can_write;
		boolean is_file;
		boolean is_folder;
		boolean is_image;
		boolean is_archive;
		boolean contains_directory = false;
		ArrayList<String> keys = new ArrayList<String>();
		SketchwareUtil.getAllKeysFromMap(selected_items, keys);
		String pathl = filelist.get((int)_position).get("path").toString();
		String mime_type = getMIMEType(pathl);
		
		final TextView textview1 = base.findViewById(R.id.textview1);
		final TextView textview2 = base.findViewById(R.id.textview2);
		final LinearLayout close1 = base.findViewById(R.id.close);
		final ImageView imageview1 = base.findViewById(R.id.imageview1);
		final LinearLayout create = base.findViewById(R.id.create);
		final LinearLayout linear1 = base.findViewById(R.id.linear1);
		final EditText name1 = base.findViewById(R.id.name);
		final CheckBox c1 = base.findViewById(R.id.c1);
		final CheckBox c2 = base.findViewById(R.id.c2);
		
		LinearLayout base1 = layout.findViewById(R.id.base1);
		LinearLayout edit_image = layout.findViewById(R.id.edit_image);
		LinearLayout preview_archive = layout.findViewById(R.id.preview_archive);
		LinearLayout copy = layout.findViewById(R.id.copy);
		LinearLayout cut = layout.findViewById(R.id.cut);
		LinearLayout share = layout.findViewById(R.id.share);
		LinearLayout delete = layout.findViewById(R.id.delete);
		LinearLayout rename = layout.findViewById(R.id.rename);
		LinearLayout open_as_html = layout.findViewById(R.id.open_as_html);
		LinearLayout open_as = layout.findViewById(R.id.open_as);
		LinearLayout open_external = layout.findViewById(R.id.open_external);
		LinearLayout details = layout.findViewById(R.id.details);
		LinearLayout copy_id = layout.findViewById(R.id.copy_id);
		final TextView name = layout.findViewById(R.id.name);
		ImageView icon =
		layout.findViewById(R.id.icon);
		ImageView close = layout.findViewById(R.id.close);
		ImageView imageview7 = layout.findViewById(R.id.imageview7);
		
		final String mimeType = _position == -1 ? "*/*" : getMIMEType(filelist.get((int)_position).get("path").toString());
		
		_gradDrawable("#111111", "#474747", 1, 15, 5, false, linear1);
		
		close1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				dial.dismiss();
			}});
		
		close.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				bottomsheet.dismiss();
			}});
		_ViewWidthHeight(linear1, SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) - 60, ViewGroup.LayoutParams.WRAP_CONTENT);
		_gradDrawable("111111", "474747", 1, 10, 10, false, base1);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, create);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, close1);
		_setEllipsize(name);
		if (selected_items.size() > 1) {
			name.setText(String.valueOf(selected_items.size()).concat(" selected"));
			if (contains_directory) {
				cut.setVisibility(View.GONE);
			}
			rename.setVisibility(View.GONE);
			edit_image.setVisibility(View.GONE);
			preview_archive.setVisibility(View.GONE);
			share.setVisibility(View.GONE);
			open_as.setVisibility(View.GONE);
			open_external.setVisibility(View.GONE);
			open_as_html.setVisibility(View.GONE);
			details.setVisibility(View.GONE);
			icon.setImageResource(R.drawable.app_x_zerosize_icon);
		}
		else {
			name.setText(filelist.get((int)_position).get("name").toString());
		}
		if (usingUriToNavigate) {
			can_write = false;
			is_folder = !(_position == -1) && filelist.get((int)_position).get("mime_type").toString().equals(android.provider.DocumentsContract.Document.MIME_TYPE_DIR);
			is_file = !is_folder;
			is_image = false;
			is_archive = false;
			copy.setVisibility(View.GONE);
			cut.setVisibility(View.GONE);
			rename.setVisibility(View.GONE);
			edit_image.setVisibility(View.GONE);
			preview_archive.setVisibility(View.GONE);
		}
		else {
			can_write = !(new java.io.File(currentdir).canWrite());
			is_folder = !(_position == -1) && FileUtil.isDirectory(filelist.get((int)_position).get("path").toString());
			is_file = !(_position == -1) && FileUtil.isFile(filelist.get((int)_position).get("path").toString());
			is_image = mimeType != null && mimeType.contains("image");
			is_archive = filelist.get((int)_position).get("path").toString().endsWith(".zip") || (filelist.get((int)_position).get("path").toString().endsWith(".ZIP"));;
		}
		for (int i = 0; i < selected_items.size(); i++) {
			    String path = selected_items.get(keys.get(i)).toString();
			    if (FileUtil.isDirectory(path)) {
				        contains_directory = true;
				        break;
				    }
		}
		if (is_archive) {
			if (selected_items.size() > 1) {
				preview_archive.setVisibility(View.GONE);
			}
			else {
				preview_archive.setVisibility(View.VISIBLE);
			}
		}
		else {
			preview_archive.setVisibility(View.GONE);
		}
		if (is_image) {
			if (selected_items.size() > 1) {
				edit_image.setVisibility(View.GONE);
			}
			else {
				edit_image.setVisibility(View.VISIBLE);
			}
		}
		else {
			edit_image.setVisibility(View.GONE);
		}
		if (can_write) {
			cut.setVisibility(View.GONE);
		}
		if (contains_directory) {
			if (data.getString("allow_walk_dir", "").equals("true")) {
				copy.setVisibility(View.VISIBLE);
			}
			else {
				copy.setVisibility(View.GONE);
			}
		}
		if (_position == -1) {
			share.setVisibility(View.GONE);
			details.setVisibility(View.GONE);
			rename.setVisibility(View.GONE);
			open_as.setVisibility(View.GONE);
			open_external.setVisibility(View.GONE);
			open_as_html.setVisibility(View.GONE);
			icon.setImageResource(R.drawable.app_x_zerosize_icon);
		}
		else {
			rename.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					bottomsheet.dismiss();
					imageview1.setImageResource(R.drawable.content_text_box);
					textview1.setText("Rename");
					textview2.setText("Rename");
					name1.setText(getFileName(filelist.get((int)_position).get("path").toString()));
					name1.setHint("New file name");
					c1.setVisibility(View.GONE);
					c2.setVisibility(View.GONE);
					create.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v){
							if (!name.getText().toString().trim().equals("")) {
								dial.dismiss();
								String oldPath;
								final java.io.File dir = new
								java.io.File(currentdir);
								
								final java.io.File oldFile = new
								java.io.File(filelist.get((int)_position).get("path").toString());
								
								final java.io.File newFile = new
								java.io.File(dir, name1.getText().toString().concat("/"));
								
								boolean successful = oldFile.renameTo(newFile);
								_AppDesignerToast(successful ? "Renamed." : "Failed to rename.");
								_refreshItems("");
							}
						}
					});
					dial.show();
				}
			});
			details.setOnClickListener(new View.OnClickListener(){
				public void onClick(View q){
					bottomsheet.dismiss();
					
					final AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);
					final Calendar cal = Calendar.getInstance();
					open_file.putExtra("tpath", filelist.get((int)_position).get("path").toString());
					open_file.setClass(getApplicationContext(), FileInfoActivity.class);
					startActivity(open_file);
				}
			});
		}
		if (is_folder) {
			cut.setVisibility(View.GONE);
			share.setVisibility(View.GONE);
			open_as.setVisibility(View.GONE);
			open_external.setVisibility(View.GONE);
			open_as_html.setVisibility(View.GONE);
			edit_image.setVisibility(View.GONE);
			preview_archive.setVisibility(View.GONE);
			icon.setImageResource(R.drawable.folder);
		}
		if (is_file) {
			if (mimeType instanceof String && mimeType.contains("image")) {
				if (usingUriToNavigate) {
					Glide.with(getApplicationContext()).load(Uri.parse(values.getString(lastdir.concat("_uri"), "").concat("/document/".concat(liststring.get((int)_position).replaceFirst(":", "%3A").replace("/", "%2F"))))).into(icon);
				}
				else {
					icon.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(filelist.get((int)_position).get("path").toString(), 1024, 1024));
				}
			}
			else {
				if (mimeType instanceof String && mimeType.contains("video")) {
					_getMP4Thumbnail(filelist.get((int)_position).get("path").toString(), icon);
				}
				else {
					if (mimeType instanceof String && mimeType.contains("pdf")) {
						_getPdfCover(icon, filelist.get((int)_position).get("path").toString());
					}
				}
			}
			share.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					int position = (int) _position;
					String filePath = filelist.get(position).get("path").toString();
					File file = new File(filePath);
					if (file.exists()) {
						bottomsheet.dismiss();
						Uri uri = FileProvider.getUriForFile(
						                MainActivity.this,
						                "com.ceditor.provider",
						                file
						            );
						Intent send = new Intent(Intent.ACTION_SEND);
						send.putExtra(Intent.EXTRA_STREAM, uri);
						send.setType(mimeType);
						send.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
						startActivity(Intent.createChooser(send, "Sharing " + getFileName(filePath) + "..."));
					}
				}
			});
			open_as.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					bottomsheet.dismiss();
					imageview1.setImageResource(R.drawable.content_launch);
					textview1.setText("Enter file MIME type");
					textview2.setText("Open");
					name1.setHint("Full MIME type (eg. text/plain, image/png, or else)");
					c1.setText("Open in external\napp (Not always\nworking)");
					c2.setVisibility(View.GONE);
					create.setOnClickListener(new View.OnClickListener(){
						public void onClick(View v){
							if (!name1.getText().toString().trim().equals("")) {
								if (c1.isChecked()) {
									StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
									StrictMode.setVmPolicy(builder.build());
									
									try {
										launch_file.setAction(Intent.ACTION_VIEW);
										launch_file.setDataAndType(Uri.parse("file://".concat(filelist.get((int)_position).get("path").toString())), name.getText().toString());
										intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
										startActivity(launch_file);
									}
									catch (Exception f){
										_AppDesignerToast("An error was happened, caused by ".concat(f.toString()));
									}
								}
								else {
									_openFile(filelist.get((int)_position).get("path").toString(), name1.getText().toString());
									dial.dismiss();
									_refreshItems("");
									recyclerview1.getAdapter().notifyDataSetChanged();
								}
							}
						}
					});
					dial.show();
				}
			});
			open_external.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					bottomsheet.dismiss();
					StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
					StrictMode.setVmPolicy(builder.build());
					
					try {
						launch_file.setAction(Intent.ACTION_VIEW);
						launch_file.setDataAndType(Uri.parse("file://".concat(filelist.get((int)_position).get("path").toString())), mimeType);
						intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
						startActivity(launch_file);
					}
					catch (ActivityNotFoundException g){
						SketchwareUtil.showMessage(getApplicationContext(), "An error was happened, caused by ".concat(g.toString()));
					}
				}
			});
			open_as_html.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					bottomsheet.dismiss();
					open_file.putExtra("path", filelist.get((int)_position).get("path").toString());
					open_file.putExtra("name", filelist.get((int)_position).get("name").toString());
					open_file.setClass(getApplicationContext(), ViewHtmlActivity.class);
					startActivity(open_file);
				}
			});
			edit_image.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					bottomsheet.dismiss();
					open_file.putExtra("path", filelist.get((int)_position).get("path").toString());
					open_file.putExtra("name", filelist.get((int)_position).get("name").toString());
					open_file.setClass(getApplicationContext(), EditimageActivity.class);
					startActivity(open_file);
				}
			});
			preview_archive.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					bottomsheet.dismiss();
					open_file.putExtra("path", filelist.get((int)_position).get("path").toString());
					open_file.setClass(getApplicationContext(), PreviewArchiveFilesActivity.class);
					startActivity(open_file);
				}
			});
		}
		copy.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				bottomsheet.dismiss();
				ArrayList<String> keys = new ArrayList<String>();
				file_operation = 1;
				SketchwareUtil.getAllKeysFromMap(selected_items, keys);
				for(int _repeat364 = 0; _repeat364 < (int)(selected_items.size()); _repeat364++) {
					list_of_selected_items.add(selected_items.get(keys.get((int)(_repeat364))).toString());
				}
				paste.setVisibility(usingUriToNavigate || new java.io.File(currentdir).canWrite() ? View.VISIBLE : View.GONE);
				cancel.setVisibility(View.VISIBLE);
				_setSelectionMode(0);
			}
		});
		cut.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				bottomsheet.dismiss();
				ArrayList<String> keys = new ArrayList<String>();
				file_operation = 3;
				SketchwareUtil.getAllKeysFromMap(selected_items, keys);
				for(int _repeat375 = 0; _repeat375 < (int)(selected_items.size()); _repeat375++) {
					list_of_selected_items.add(selected_items.get(keys.get((int)(_repeat375))).toString());
				}
				paste.setVisibility(usingUriToNavigate || new java.io.File(currentdir).canWrite() ? View.VISIBLE : View.GONE);
				cancel.setVisibility(View.VISIBLE);
				_setSelectionMode(0);
			}
		});
		copy_id.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				bottomsheet.dismiss();
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", filelist.get((int)_position).get("name").toString()));
				_AppDesignerToast("Copied to clipboard: ".concat(filelist.get((int)_position).get("name").toString()));
			}
		});
		delete.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				bottomsheet.dismiss();
				dialog.setTitle("Delete?");
				dialog.setMessage("Are you sure to delete ".concat(_position == -1 ? "these ".concat(String.valueOf(selected_items.size())).concat(" files?") : getFileName(filelist.get((int)_position).get("path").toString()).concat("?")));
				dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						String n = "";
						ArrayList<String> keys = new ArrayList<String>();
						if (!(_position == -1)) {
							n = filelist.get((int)_position).get("name").toString();
						}
						SketchwareUtil.getAllKeysFromMap(selected_items, keys);
						if (selected_items.size() > 1) {
							for(int _repeat302 = 0; _repeat302 < (int)(selected_items.size()); _repeat302++) {
								if (usingUriToNavigate) {
									try {
										android.provider.DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), Uri.parse(values.getString(lastdir.concat("_uri"), "") + "/document/" + selected_items.get(keys.get(_repeat302)).toString().replace(":", "%3A").replace("/", "%2F")));
									}catch(Exception e){}
								}
								else {
									FileUtil.deleteFile(selected_items.get(keys.get((int)(_repeat302))).toString());
								}
							}
						}
						else {
							FileUtil.deleteFile(filelist.get((int)_position).get("path").toString());
						}
						if (usingUriToNavigate) {
							_openDir(Uri.parse(values.getString(lastdir + "_uri", "")), currentdir);
						}
						else {
							_openDir(currentdir);
						}
						if (_position == -1) {
							_AppDesignerToast(String.valueOf((long)(selected_items.size())).concat(" items has been deleted."));
						}
						else {
							_AppDesignerToast(n.concat(" has been deleted."));
						}
						paste.setVisibility(View.GONE);
						cancel.setVisibility(View.GONE);
						_setSelectionMode(0);
					}
				});
				dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				_CXYZ_dialog_theme(dialog);
			}
		});
		bottomsheet.setOnDismissListener(new DialogInterface.OnDismissListener() {
			 @Override
			public void onDismiss(DialogInterface dialog) {
				if (selected_items.size() > 1) {
					
				}
				else {
					_setSelectionMode(0);
				}
			}
		});
	}
	
	
	public void _dirOperations(final double _type, final String _source, final String _dest) {
		if (_type < 2) {
			if (!usingUriToNavigate) {
				java.io.File source_file = new java.io.File(_source);
				java.io.File[] files = source_file.listFiles();
				java.io.File dest_file = new java.io.File(_dest);
				if (!dest_file.exists()) {
					dest_file.mkdirs();
				}
				if (files != null) {
					int failed_items = 0;
					for (java.io.File each_file: files){
						if (each_file.isFile()) {
							if (_type == 0) {
								FileUtil.copyFile(each_file.getPath(), _dest.concat("/".concat(each_file.getName())));
							}
							if (_type == 1) {
								if (!SettingsActivity.moveFile(each_file.getPath(), _dest.concat("/".concat(each_file.getName())))) {
									failed_items++;
								}
							}
						}
						else {
							if (each_file.isDirectory()) {
								_dirOperations(_type, each_file.getPath().concat("/"), _dest.concat("/".concat(each_file.getName().concat("/"))));
							}
						}
					}
					app_temp_data.put("fh", String.valueOf((long)(failed_items)));
				}
			}
			return;
		}
	}
	
	
	public void _setSelectionMode(final double _state) {
		if (_state == 0) {
			selected_items.clear();
			select = false;
			if (!(usingUriToNavigate || new java.io.File(currentdir).canWrite())) {
				_fab.hide();
			}
			recyclerview1.getAdapter().notifyDataSetChanged();
			_setSubtitle(currentdir);
			clear_selection.setVisibility(View.GONE);
		}
		if (_state == 1) {
			select = true;
			_setSelectionMode(2);
			if (!fileContextMenuOnly) {
				clear_selection.setVisibility(View.VISIBLE);
			}
		}
		if (_state == 2) {
			title.setText(String.valueOf(selected_items.size()).concat(" selected"));
			recyclerview1.getAdapter().notifyDataSetChanged();
		}
	}
	
	
	public void _toggleProgressBar() {
		final ObjectAnimator test = new ObjectAnimator();
		test.setTarget(toolbar1_progressbar);
		test.setPropertyName("alpha");
		test.setFloatValues((float)(toolbar1_progressbar.getVisibility() == View.VISIBLE ? 1 : 0), (float)(toolbar1_progressbar.getVisibility() == View.VISIBLE ? 0 : 1));
		test.setDuration((int)(100));
		test.start();
		test.addListener(new Animator.AnimatorListener(){
			public void onAnimationCancel(Animator a){}
			public void onAnimationRepeat(Animator a){}
			
			public void onAnimationStart(Animator a){
				final android.transition.ChangeBounds anim = new android.transition.ChangeBounds();
				anim.setDuration(250L);
				android.transition.TransitionManager.beginDelayedTransition(toolbar1, anim);
				toolbar1_progressbar.setVisibility(View.VISIBLE);
			} public void onAnimationEnd(Animator a){
				final android.transition.ChangeBounds anim = new android.transition.ChangeBounds();
				anim.setDuration(250L);
				android.transition.TransitionManager.beginDelayedTransition(toolbar1, anim);
				toolbar1_progressbar.setVisibility(toolbar1_progressbar.getAlpha() == 1 ? View.VISIBLE : View.GONE);
			}});
	}
	
	
	public void _getDeviceAvailableInternalStorage() {
	}
	public static class DeviceMemory {
		    
		    public static long getInternalStorageSpace() {
			        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
			        long total = statFs.getBlockCountLong() * statFs.getBlockSizeLong();
			        return total; // Return in bytes
			    }
		
		    public static long getInternalFreeSpace() {
			        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
			        long free = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
			        return free; // Return in bytes
			    }
		
		    public static long getInternalUsedSpace() {
			        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
			        long total = statFs.getBlockCountLong() * statFs.getBlockSizeLong();
			        long free = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
			        long used = total - free;
			        return used; // Return in bytes
			    }
	}
	{
	}
	
	
	public void _buttonUI() {
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, allow);
		/*android.graphics.drawable.GradientDrawable ButtonUi1 = new android.graphics.drawable.GradientDrawable();
int dm = (int) getApplicationContext().getResources().getDisplayMetrics().density;
int colors [] = {0xFF474747,0xFF282828,0xFF353535};
ButtonUi1= new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colors);
ButtonUi1.setCornerRadius(dm*12);
ButtonUi1.setStroke(dm*1,0xFF212121);
allow.setElevation(dm*5);
android.graphics.drawable.RippleDrawable ButtonUi1_RD = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFF212121}), ButtonUi1, null);
allow.setBackground(ButtonUi1_RD);
allow.setClickable(true);*/
		
		android.graphics.drawable.GradientDrawable buttonUI2 = new android.graphics.drawable.GradientDrawable();
		int dn = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		buttonUI2.setColor(0xFFFF0000);
		buttonUI2.setCornerRadius(dn * 360);
		
		goTop.setElevation(dn * 5);
		
		android.content.res.ColorStateList rippleColor = new android.content.res.ColorStateList(
		    new int[][] { new int[] {} },
		    new int[] { 0xFF080808 }
		);
		
		goTop.setBackground(new android.graphics.drawable.RippleDrawable(
		    rippleColor,
		    buttonUI2.getConstantState().newDrawable().mutate(),
		    null
		));
		goTop.setClickable(true);
		
		goBottom.setBackground(new android.graphics.drawable.RippleDrawable(
		    rippleColor,
		    buttonUI2.getConstantState().newDrawable().mutate(),
		    null
		));
		goBottom.setClickable(true);
		
		imageview11.setBackground(new android.graphics.drawable.RippleDrawable(
		    rippleColor,
		    buttonUI2.getConstantState().newDrawable().mutate(),
		    null
		));
		imageview11.setClickable(true);
		
		imageview12.setBackground(new android.graphics.drawable.RippleDrawable(
		    rippleColor,
		    buttonUI2.getConstantState().newDrawable().mutate(),
		    null
		));
		imageview12.setClickable(true);
		
		imageview13.setBackground(new android.graphics.drawable.RippleDrawable(
		    rippleColor,
		    buttonUI2.getConstantState().newDrawable().mutate(),
		    null
		));
		imageview13.setClickable(true);
	}
	
	
	public void _popupListAdapterLib() {
		//Blank
	}
	
	
	private List<DriveItem> getDriveItems() {
			List<DriveItem> drives = new ArrayList<>();
			
			File internalStorage = Environment.getExternalStorageDirectory();
			drives.add(new DriveItem(R.drawable.folder, "Internal Storage", internalStorage.getAbsolutePath()));
			
			drives.add(new DriveItem(R.drawable.download_folder, "Downloads", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()));
			drives.add(new DriveItem(R.drawable.documents_folder, "Documents", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()));
			drives.add(new DriveItem(R.drawable.music_ringtones_folder, "Music", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath()));
			drives.add(new DriveItem(R.drawable.dcim_folder, "DCIM", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()));
			drives.add(new DriveItem(R.drawable.codexyz_folder, "C-XYZ", new File(internalStorage, "CEditor").getAbsolutePath()));
			
			android.os.storage.StorageManager storageManager = (android.os.storage.StorageManager) getSystemService(Context.STORAGE_SERVICE);
			try {
					String[] volumes = (String[]) storageManager.getClass().getMethod("getVolumePaths", new Class[0]).invoke(storageManager, new Object[0]);
					List<android.os.storage.StorageVolume> storageVolumeList = storageManager.getStorageVolumes();
					
					for (int i = 0; i < volumes.length; i++) {
							String v = volumes[i];
							android.os.storage.StorageVolume svl = storageVolumeList.get(i);
							if (svl.isRemovable()) {
									if ("mounted".equals(svl.getState())) {
											if (new java.io.File(v).exists()) {
													boolean isAlreadyAdded = false;
													for (DriveItem drive : drives) {
															if (v.equals(drive.getPath())) {
																	isAlreadyAdded = true;
																	break;
															}
													}
													if (!isAlreadyAdded) {
															drives.add(new DriveItem(R.drawable.content_micro_sd, svl.getDescription(this), v));
													}
											}
									}
							}
					}
			} catch (Exception e) {
					e.printStackTrace();
			}
			
			return drives;
	}
	
	
	private static class DriveItem {
			private final int iconResId;
			private final String title;
			private final String path;
			
			public DriveItem(int iconResId, String title, String path) {
					this.iconResId = iconResId;
					this.title = title;
					this.path = path;
			}
			
			public int getIconResId() {
					return iconResId;
			}
			
			public String getTitle() {
					return title;
			}
			
			public String getPath() {
					return path;
			}
	}
	
	private class DriveListAdapter extends ArrayAdapter<DriveItem> {
			
			public DriveListAdapter(Context context, List<DriveItem> drives) {
					super(context, 0, drives);
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
					if (convertView == null) {
							convertView = LayoutInflater.from(getContext()).inflate(R.layout.drivelist, parent, false);
					}
					
					DriveItem driveItem = getItem(position);
					
					ImageView itemIcon = convertView.findViewById(R.id.itemIcon);
					TextView storageTitle = convertView.findViewById(R.id.storageTitle);
					TextView storagePath = convertView.findViewById(R.id.storagePath);
					
					itemIcon.setImageResource(driveItem.getIconResId());
					storageTitle.setText(driveItem.getTitle());
					storagePath.setText(driveItem.getPath());
					
					return convertView;
			}
			
	}
	
	
	private class DownloadListAdapter extends BaseAdapter {
			private Context context;
			private List<File> fileList;
			
			public DownloadListAdapter(Context context, List<File> fileList) {
					this.context = context;
					this.fileList = fileList;
			}
			
			@Override
			public int getCount() {
					return fileList.size();
			}
			
			@Override
			public Object getItem(int position) {
					return fileList.get(position);
			}
			
			@Override
			public long getItemId(int position) {
					return position;
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
					if (convertView == null) {
							LayoutInflater inflater = LayoutInflater.from(context);
							convertView = inflater.inflate(R.layout.drivelist, parent, false);
					}
					
					TextView storageTitle = convertView.findViewById(R.id.storageTitle);
					TextView storagePath = convertView.findViewById(R.id.storagePath);
					ImageView itemIcon = convertView.findViewById(R.id.itemIcon);
					
					File file = fileList.get(position);
					storageTitle.setText(file.getName());
					storagePath.setText(file.getAbsolutePath());
					itemIcon.setImageResource(R.drawable.folder);
					
					return convertView;
			}
	}
	private void animateSelection(final View selected, final View unselected, final View line1, final LinearLayout op1, final LinearLayout op2) {
		    final int expandedWidth = dpToPx(60);
		    final int collapsedWidth = dpToPx(35);
		    animateWidth(selected, expandedWidth);
		    animateWidth(unselected, collapsedWidth);
		    float translationX = (selected == op1) ? 0 : collapsedWidth;
		    ObjectAnimator animator = ObjectAnimator.ofFloat(line1, "translationX", translationX);
		    animator.setDuration(300);
		    animator.start();
	}
	
	private void animateWidth(final View view, int targetWidth) {
		    int startWidth = view.getWidth();
		    ValueAnimator valueAnimator = ValueAnimator.ofInt(startWidth, targetWidth);
		    valueAnimator.setDuration(300);
		
		    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			        @Override
			        public void onAnimationUpdate(ValueAnimator animation) {
				            int newWidth = (int) animation.getAnimatedValue();
				            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
				            layoutParams.width = newWidth;
				            view.setLayoutParams(layoutParams);
				        }
			    });
		
		    valueAnimator.start();
	}
	
	private int dpToPx(int dp) {
		    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
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
	
	
	public void _getApkIcon(final String _path, final ImageView _imageview) {
		android.content.pm.PackageManager packageManager = this.getPackageManager();
		android.content.pm.PackageInfo packageInfo = packageManager.getPackageArchiveInfo(_path, 0);
		packageInfo.applicationInfo.sourceDir = _path;
		packageInfo.applicationInfo.publicSourceDir = _path;
		_imageview.setImageDrawable(packageInfo.applicationInfo.loadIcon(packageManager));
		packageInfo = null;
		packageManager = null;
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
				        _imageview.setImageResource(R.drawable.audio_x_generic_icon);
				    }
		} catch (Exception e) {
			    e.printStackTrace();
			    _imageview.setImageResource(R.drawable.audio_x_generic_icon);
		} finally {
			    try {
				        mmr.release();
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
		}
	}
	
	
	public void _getTextFontPreview(final ImageView _imageview, final String _path) {
		File file = new File(_path);
		if (!file.exists()) {
				return;
		}
		
		try {
				Typeface typeface = Typeface.createFromFile(file);
				Bitmap bitmap = Bitmap.createBitmap(300, 100, Bitmap.Config.ARGB_8888);
				Canvas canvas = new Canvas(bitmap);
				Paint paint = new Paint();
				paint.setTypeface(typeface);
				paint.setTextSize(12);
				paint.setColor(ContextCompat.getColor(this, android.R.color.black));
				canvas.drawText("Abc", 10, 50, paint);
				_imageview.setImageBitmap(bitmap);
		} catch (Exception e) {
				e.printStackTrace();
		}
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
	
	
	public void _FileIconPlaceholder(final ImageView _imageview, final String _path) {
		String mimeType = getMIMEType(_path);
		String lowerPath = _path.toLowerCase();
		Pattern pattern = Pattern.compile(
		"(read[-_ ]?me|read[-_ ]?me|important|readme)", 
		Pattern.CASE_INSENSITIVE
		);
		
		try{
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
					if (data.getString("disable_thumbnail_apk", "").equals("") || data.getString("disable_thumbnail_apk", "").equals("false")) {
							_getApkIcon(_path, _imageview);
					} else {
						    _imageview.setImageResource(R.drawable.app_vnd_android_package_archive_icon);
					}
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
			} else if (_path.endsWith("recov")) {
					_imageview.setImageResource(R.drawable.applicationrecovfile_94682);
			} else if (_path.endsWith(".swb")) {
					_imageview.setImageResource(R.drawable.applicationxsketchwareproject_94693);
			} else if (_path.endsWith(".unknown") || _path.endsWith(".unk") || _path.endsWith(".")){ 
					_imageview.setImageResource(R.drawable.unknown_icon);
			} else if (_path.endsWith(".zip") || _path.endsWith(".zipx") || _path.endsWith(".z01") || _path.endsWith(".zx01") || _path.endsWith(".ZIP") || _path.endsWith(".rar") || _path.endsWith(".rev") || _path.endsWith(".r00") || _path.endsWith(".r01") || _path.endsWith(".RAR") || _path.endsWith(".7z") || _path.endsWith(".7Z")) {
					_imageview.setImageResource(R.drawable.app_x_compress_icon);
			} else if (_path.endsWith(".pdf") || _path.endsWith(".PDF")) {
					if (data.getString("disable_thumbnail_pdf", "").equals("") || data.getString("disable_thumbnail_pdf", "").equals("false")) {
							_getPdfCover(_imageview, _path);
					} else {
							_imageview.setImageResource(R.drawable.app_pdf_icon);
					}
			} else if (_path.endsWith(".json") || _path.endsWith(".JSON")) {
					_imageview.setImageResource(R.drawable.app_json_icon);
			} else if (_path.endsWith(".ttf") || _path.endsWith(".otf") || _path.endsWith(".woff") || _path.endsWith(".TTF") || _path.endsWith(".OTF") || _path.endsWith(".WOFF")) {
					_imageview.setImageResource(R.drawable.app_x_font_ttf_icon);
			} else if (_path.endsWith(".xml") || _path.endsWith(".XML")) {
					_imageview.setImageResource(R.drawable.app_xml_icon);
			} else if (_path.endsWith(".txt") || _path.endsWith(".TXT")) {
					_imageview.setImageResource(R.drawable.text_x_generic_icon);
			} else {
					if (mimeType != null && mimeType.contains("image")) {
							if (data.getString("disable_thumbnail_image", "").equals("") || data.getString("disable_thumbnail_image", "").equals("false")) {
									_setImageFromFile(_imageview, _path);
							} else {
									_imageview.setImageResource(R.drawable.image_x_generic_icon);
							}
					} else if (mimeType != null && mimeType.contains("video")) {
							if (data.getString("disable_thumbnail_video", "").equals("") || data.getString("disable_thumbnail_video", "").equals("false")) {
									_getMP4Thumbnail(_path, _imageview);
							} else {
									_imageview.setImageResource(R.drawable.playlist_icon);
							}
					} else if (mimeType != null && mimeType.contains("audio")) {
							if (data.getString("disable_thumbnail_audio", "").equals("") || data.getString("disable_thumbnail_audio", "").equals("false")) {
									_getAlbumCover(_imageview, _path);
							} else {
									_imageview.setImageResource(R.drawable.audio_x_generic_icon);
							}
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
			
		} catch(Exception e){
			_imageview.setImageResource(R.drawable.applicationxzerosizerrorinit_68611);
		}
	}
	
	
	public void _DirIconPlaceholder(final ImageView _imageview, final String _path) {
		try {
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
		} catch(Exception e){
			_imageview.setImageResource(R.drawable.folderyellowerrorinit_68305);
		}
	}
	
	
	public void _DefaultDirIconPlaceholder(final ImageView _imageview, final String _path) {
		if (_path.equals("src")) {
			_imageview.setImageResource(R.drawable.folderyellowvisiting_93095);
			_imageview.setAlpha((float)(1));
		}
		else {
			if (_path.equals("java")) {
				_imageview.setImageResource(R.drawable.folderorangejava_92947);
				_imageview.setAlpha((float)(1));
			}
			else {
				if (_path.equals("res") || _path.equals("resources")) {
					_imageview.setImageResource(R.drawable.folderyellowtemplates_92904);
					_imageview.setAlpha((float)(1));
				}
				else {
					if (_path.equals("values")) {
						_imageview.setImageResource(R.drawable.folderyellowmail_93013);
						_imageview.setAlpha((float)(1));
					}
					else {
						if (_path.equals("dist")) {
							_imageview.setImageResource(R.drawable.folderbrownvisiting_92914);
							_imageview.setAlpha((float)(1));
						}
						else {
							if (_path.equals("lib")) {
								_imageview.setImageResource(R.drawable.folderyellowremote_93390);
								_imageview.setAlpha((float)(1));
							}
							else {
								if (_path.equals("classes")) {
									_imageview.setImageResource(R.drawable.folderviolettemplates_93204);
									_imageview.setAlpha((float)(1));
								}
								else {
									if (_path.equals("bin")) {
										_imageview.setImageResource(R.drawable.folderyellowscript_92851);
										_imageview.setAlpha((float)(1));
									}
									else {
										if (_path.equals("conf")) {
											_imageview.setImageResource(R.drawable.folderyellowdevelopment_93209);
											_imageview.setAlpha((float)(1));
										}
										else {
											if (_path.equals("main") || _path.equals("emulated")) {
												_imageview.setImageResource(R.drawable.usercyanhome_93466);
												_imageview.setAlpha((float)(1));
											}
											else {
												if (_path.equals("dimens")) {
													_imageview.setImageResource(R.drawable.folderyellowdimens_68423);
													_imageview.setAlpha((float)(1));
																
												}
												else {
													if (_path.startsWith(".")) {
														_imageview.setImageResource(R.drawable.folder);
														_imageview.setAlpha((float)(0.45d));
													}
													else {
														if (_path.contains("gradle")) {
															_imageview.setImageResource(R.drawable.folderyellowgradle_83823);
															_imageview.setAlpha((float)(1));
														}
														else {
															if (_path.equals("anim") || _path.equals("animator")) {
																_imageview.setImageResource(R.drawable.folderyellowanim_26493);
																_imageview.setAlpha((float)(1));
															}
															else {
																if (_path.equals("layout")) {
																	_imageview.setImageResource(R.drawable.folderyellowlayout_48252);
																	_imageview.setAlpha((float)(1));
																}
																else {
																	if (_path.equals("build")) {
																		_imageview.setImageResource(R.drawable.folderyellowbuild_53813);
																		_imageview.setAlpha((float)(1));
																	}
																	else {
																		if (_path.equals("font")) {
																			_imageview.setImageResource(R.drawable.folderyellowfont_66425);
																			_imageview.setAlpha((float)(1));
																		}
																		else {
																			if (_path.equals("color")) {
																				_imageview.setImageResource(R.drawable.folderyellowcolor_57259);
																				_imageview.setAlpha((float)(1));
																			}
																			else {
																				if (_path.equals("rs")) {
																					_imageview.setImageResource(R.drawable.folderyellowscript_63990);
																					_imageview.setAlpha((float)(1));
																				}
																				else {
																					if (_path.equals("assets")) {
																						_imageview.setImageResource(R.drawable.folderyellowdocuments_93435);
																						_imageview.setAlpha((float)(1));
																					}
																					else {
																						if (_path.equals("assets")) {
																							_imageview.setImageResource(R.drawable.folderyellowdocuments_93435);
																							_imageview.setAlpha((float)(1));
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
			
			Toast customToast = Toast.makeText(MainActivity.this, _s, Toast.LENGTH_SHORT);
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
	
	
	public void _EULA() {
		if (openstring.getString("eula", "").equals("") || openstring.getString("eula", "").equals("f")) {
			toolbar1.setVisibility(View.GONE);
			layout_main.setVisibility(View.GONE);
			linear5.setVisibility(View.GONE);
			_drawer_linear29.setVisibility(View.GONE);
			_drawer_linear30.setVisibility(View.GONE);
			_drawer_vscroll1.setVisibility(View.GONE);
			_drawer_textview26.setVisibility(View.VISIBLE);
			_fab.setEnabled(false);
			_fab.setAlpha((float)(0));
			readingEULA = true;
			alertbox.setVisibility(View.VISIBLE);
			android.graphics.drawable.GradientDrawable masterBox = new android.graphics.drawable.GradientDrawable();
			int dst = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			masterBox.setColor(0xFF141414);
			masterBox.setCornerRadius(dst*20);
			masterBox.setStroke(dst*1,0xFFFF0000);
			alertbox.setElevation(dst*30);
			alertbox.setBackground(masterBox);
			try {
// 					java.io.InputStream alertbox_webviewIn = this.getAssets().open("eula.html");
// 					int alertbox_webviewSi = alertbox_webviewIn.available();
// 					byte[] alertbox_webviewBu = new byte[alertbox_webviewSi];
// 					alertbox_webviewIn.read(alertbox_webviewBu);
// 					alertbox_webviewIn.close();
// 					final String alertbox_webviewStr = new String(alertbox_webviewBu, "UTF-8");
// 					WebSettings alertbox_webviewSs = alertbox_webview.getSettings(); 
// 					alertbox_webviewSs.setJavaScriptEnabled(true); 
// 					alertbox_webviewSs.setJavaScriptCanOpenWindowsAutomatically(true);
					if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
// 							alertbox_webviewSs.setAllowFileAccessFromFileURLs(true); 
// 							alertbox_webviewSs.setAllowUniversalAccessFromFileURLs(true);
					}
// 					alertbox_webview.loadDataWithBaseURL(null, alertbox_webviewStr, "text/html", "UTF-8", null);
			} catch(Exception e) {
					e.printStackTrace();
			}
		}
		else {
			toolbar1.setVisibility(View.VISIBLE);
			layout_main.setVisibility(View.VISIBLE);
			linear5.setVisibility(View.VISIBLE);
			_drawer_linear29.setVisibility(View.VISIBLE);
			_drawer_linear30.setVisibility(View.VISIBLE);
			_drawer_vscroll1.setVisibility(View.VISIBLE);
			_drawer_textview26.setVisibility(View.GONE);
			_fab.setEnabled(true);
			_fab.setAlpha((float)(1));
			readingEULA = false;
			_DynamicShortcuts();
			alertbox.setVisibility(View.GONE);
		}
	}
	
	
	public void _blank() {
		if (data.getString("grid_mode", "").equals("true")) {
			isGridMode = true;
			switchToGrid();
			 ((LinearLayoutManager) recyclerview1.getLayoutManager()).scrollToPositionWithOffset((int)position, (int)1);
			_refreshItems("");
		}
		else {
			isGridMode = false;
			switchToList();
			 ((LinearLayoutManager) recyclerview1.getLayoutManager()).scrollToPositionWithOffset((int)position, (int)1);
			_refreshItems("");
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
	
	
	public void _collapsingToolbar(final View _scroll) {
		Animation slideUpOpposite = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_opposite);
		Animation slideDownOpposite = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_opposite);
		
		try {
				com.google.android.material.appbar.AppBarLayout.LayoutParams params = (com.google.android.material.appbar.AppBarLayout.LayoutParams)_toolbar.getLayoutParams();
				params.setScrollFlags(com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS | com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
				androidx.core.widget.NestedScrollView nestedScrollView = new androidx.core.widget.NestedScrollView(this);
				
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
				nestedScrollView.setLayoutParams(layoutParams);
				androidx.core.view.ViewCompat.setNestedScrollingEnabled(_scroll, true);
				
				AppBarLayout appBarLayout = findViewById(R.id._app_bar);
				View b1 = findViewById(R.id.imageview11);
				View b2 = findViewById(R.id.imageview12);
				View b3 = findViewById(R.id.imageview13);
				
				appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
						@Override
						public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
								if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
										if (b1.getVisibility() != View.VISIBLE) {
												b1.startAnimation(slideUpOpposite);
												b2.startAnimation(slideUpOpposite);
												b3.startAnimation(slideUpOpposite);
												b1.setVisibility(View.VISIBLE);
												b2.setVisibility(View.VISIBLE);
												b3.setVisibility(View.VISIBLE);
										}
								} else if (verticalOffset == 0) {
										if (b1.getVisibility() != View.GONE) {
												b1.startAnimation(slideDownOpposite);
												b2.startAnimation(slideDownOpposite);
												b3.startAnimation(slideDownOpposite);
												b1.setVisibility(View.GONE);
												b2.setVisibility(View.GONE);
												b3.setVisibility(View.GONE);
										}
								}
						}
				});
		} catch(Exception e) {
				
		}
		
	}
	
	
	public void _ViewWidthHeight(final View _view, final double _w, final double _h) {
		_view.getLayoutParams().width = (int)_w;
		_view.getLayoutParams().height = (int)_h;
		_view.requestLayout();
	}
	
	
	public void _OrientationChange(final Configuration _configuration) {
		if (_configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			base_layout.setOrientation(LinearLayout.HORIZONTAL);
			int width=1;
			int height= LinearLayout.LayoutParams.MATCH_PARENT;
			LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
			divider.setLayoutParams(parms);
			if (readingEULA) {
				listView.setEnabled(false);
				listView.setAlpha((float)(0.4d));
			}
			else {
				listView.setEnabled(true);
				listView.setAlpha((float)(1));
			}
			divider.setVisibility(View.VISIBLE);
			info_layout.setVisibility(View.VISIBLE);
			List<DriveItem> driveItems = getDriveItems();
			DriveListAdapter adapter = new DriveListAdapter(MainActivity.this, driveItems);
			listView.setAdapter(adapter);
			
			listView.setOnItemClickListener((parent, view, position, id) -> {
					DriveItem selectedDrive = driveItems.get(position);
					TextView drivePath = view.findViewById(R.id.storagePath);
					drivePath.setText(selectedDrive.getPath());
					_openDir(selectedDrive.getPath());
			});
			
			listView.setOnItemLongClickListener((parent, view, position, id) -> {
					DriveItem selectedDrive = driveItems.get(position);
					ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
					ClipData clip = ClipData.newPlainText("Drive Path", selectedDrive.getPath());
					clipboard.setPrimaryClip(clip);
					
					// Code generated by App Designer
					DisplayMetrics screen = new DisplayMetrics();
					getWindowManager().getDefaultDisplay().getMetrics(screen);
					double dp = 10;
					double logicalDensity = screen.density;
					int px = (int) Math.ceil(dp * logicalDensity);
					
					Toast customToast = Toast.makeText(MainActivity.this, "Path copied to clipboard: " + selectedDrive.getPath(), Toast.LENGTH_SHORT);
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
					
					return true;
			});
		}
		else
		if (_configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
			base_layout.setOrientation(LinearLayout.VERTICAL);
			divider.setVisibility(View.GONE);
			info_layout.setVisibility(View.GONE);
		}
	}
	
	
	@Override public void onConfigurationChanged(Configuration _configuration) { 
		super.onConfigurationChanged(_configuration);
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
	
	
	public void _DynamicShortcuts() {
		android.content.pm.ShortcutManager shortcutManager = null;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
			shortcutManager = getSystemService(android.content.pm.ShortcutManager.class);
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			if (shortcutManager != null) {
				android.content.pm.ShortcutInfo shortcut_1 = new android.content.pm.ShortcutInfo.Builder(MainActivity.this, "file")
									.setShortLabel("New file")
									.setLongLabel("New file")
									.setRank(0)
									.setIntent(new android.content.Intent(android.content.Intent.ACTION_VIEW, null, MainActivity.this, NewFileActivity.class))
									.setIcon(android.graphics.drawable.Icon.createWithResource(MainActivity.this, R.drawable.shortcut_1))
									.build();
				android.content.pm.ShortcutInfo shortcut_2 = new android.content.pm.ShortcutInfo.Builder(MainActivity.this, "terminal")
									.setShortLabel("Terminal emulator")
									.setLongLabel("Terminal")
									.setRank(0)
									.setIntent(new android.content.Intent(android.content.Intent.ACTION_VIEW, null, MainActivity.this, TerminalActivity.class))
									.setIcon(android.graphics.drawable.Icon.createWithResource(MainActivity.this, R.drawable.shortcut_3))
									.build();
				shortcutManager.setDynamicShortcuts(java.util.Arrays.asList(shortcut_1, shortcut_2));
			}
		}
		else {
			
		}
	}
	
	
	public void _tintAnimation(final ImageView _img, final boolean _reverse) {
		if (_reverse) {
			ObjectAnimator animator1 = ObjectAnimator.ofObject(
			_img,
			"colorFilter",
			new ArgbEvaluator(),
			Color.parseColor("#FF0000"),
			Color.parseColor("#EAEAEA")
			);
			animator1.setDuration(500);
			animator1.start();
		}
		else {
			ObjectAnimator animator1 = ObjectAnimator.ofObject(
			_img,
			"colorFilter",
			new ArgbEvaluator(),
			Color.parseColor("#EAEAEA"),
			Color.parseColor("#FF0000")
			);
			animator1.setDuration(500);
			animator1.start();
		}
	}
	
	
	public void _setEllipsize(final TextView _textview) {
		_textview.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		_textview.setMarqueeRepeatLimit(-1);
		_textview.setHorizontallyScrolling(true);
		_textview.setSelected(true);
	}
	
	
	public void _drawer_main_ui() {
		_drawer.setScrimColor(Color.TRANSPARENT); ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(this, _drawer, R.string.app_name,
		R.string.app_name) {
				@Override
				public void onDrawerSlide (View drawerView, float slideOffset) {
						
						super.onDrawerSlide(drawerView, slideOffset);
						float slideX = drawerView.getWidth()
						* slideOffset; _coordinator.setTranslationX(slideX);
						;
				}
		};
		
		_drawer.addDrawerListener(_toggle);
		
		
		_drawer_dthumb.setChecked(!data.getString("disable_thumbnail", "").equals("") && data.getString("disable_thumbnail", "").equals("true"));
		_drawer_autoRefrsh.setChecked(!data.getString("auto_refresh", "").equals("") && data.getString("auto_refresh", "").equals("true"));
		_drawer_saveLastDirPath.setChecked(!data.getString("save_path", "").equals("") && data.getString("save_path", "").equals("true"));
		
		_drawer_dthumb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
						final boolean _isChecked = _param2;
						if (_isChecked) {
								data.edit().putString("disable_thumbnail", "true").commit();
								data.edit().putString("disable_thumbnail_image", "true").commit();
								data.edit().putString("disable_thumbnail_video", "true").commit();
								data.edit().putString("disable_thumbnail_audio", "true").commit();
								data.edit().putString("disable_thumbnail_pdf", "true").commit();
								data.edit().putString("disable_thumbnail_apk", "true").commit();
						}
						else {
								data.edit().putString("disable_thumbnail", "false").commit();
								data.edit().putString("disable_thumbnail_image", "false").commit();
								data.edit().putString("disable_thumbnail_video", "false").commit();
								data.edit().putString("disable_thumbnail_audio", "false").commit();
								data.edit().putString("disable_thumbnail_pdf", "false").commit();
								data.edit().putString("disable_thumbnail_apk", "false").commit();
						}
						_refreshItems("");
				}
		});
		
		
		_drawer_autoRefrsh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
						final boolean _isChecked = _param2;
						if (_isChecked) {
								data.edit().putString("auto_refresh", "true").commit();
						}
						else {
								data.edit().putString("auto_refresh", "false").commit();
						}
				}
		});
		
		
		_drawer_saveLastDirPath.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
						final boolean _isChecked = _param2;
						if (_isChecked) {
								data.edit().putString("save_path", "true").commit();
						}
						else {
								data.edit().putString("save_path", "false").commit();
						}
				}
		});
		
		_drawer_o1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SpannableStringBuilder spannable = new SpannableStringBuilder();
				String[] sections = {
					 "New Features:\n", 
					 "Improvements:\n", 
					 "Deprecations:\n", 
					 "Known Issues:\n"
				};
				String[] contents = {
					 
					 "• New file management system\n" +
					 "• Dark mode interface\n" + 
					 "• Updated icon set\n" +
					 "• GitHub repository cloning support\n" + 
					 "• Swipe-to-action on file items\n" + 
					 "• Quick scroll buttons\n" +
					 "• Particle background animation (static if animation is 0)\n" +
					 "• SD card access support\n" +
					 "• Multiple file selection (folders can only be moved)\n" +
					 "• File copy/move operations\n" + 
					 "• Quick ZIP file preview\n" +
					 "• New FAB with BottomSheet menu\n" + 
					 "• CRC32 hash verification\n" +
					 "• sora-editor 0.14.0 with TextMate support\n" +
					 "• Mini HTML preview\n" +
					 "• Image editor\n" +
					 "• Enhanced music player\n\n",
					 
					 "• Improved scroll thumb design\n" +
					 "• Replaced ListView with RecyclerView\n" +
					 "• Better JSON and Java code formatting\n" +
					 "• Terminal enhancements\n" +
					 "• New grid view for files\n" +
					 "• Advanced swipe-to-refresh implementation\n" +
					 "• Improved file loading performance\n\n", 
					 
					 "• Removed 'Java Codes' activity\n" +
					 "• Removed app icon extraction\n" +
					 "• Replaced reflection-based translucency with XML styles\n" +
					 "• Integrated 'More Tools' into navigation drawer\n" +
					 "• Moved extract-code and binary conversion to Editor\n" + 
					 "• Changed default folder to /storage/emulated/0/CEditor\n\n",
					 
					 "• Performance lag with large file lists (device-dependent)\n" +
					 "• Crash when tapping with multiple fingers\n" +
					 "• Folder corruption during move operations\n" +
					 "• Some features may be unstable and there are some glitches in landscape orientation\n"
				};
				
				for (int i = 0; i < sections.length; i++) {
					 int start = spannable.length();
					 spannable.append(sections[i]);
					 int end = spannable.length();
					 spannable.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					 spannable.append(contents[i]);
				}
				changelog.setMessage(spannable);
				changelog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				_CXYZ_dialog_theme(changelog);
			}
		});
		
		_drawer_o2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), AcknowledgementActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			}
		});
		
		_drawer_linear34.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), RetrieverActivity.class);
				intent.putExtra("retriever", "deviceinfo");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			}
		});
		
		_drawer_linear35.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), RetrieverActivity.class);
				intent.putExtra("retriever", "battery");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			}
		});
		
		_drawer_linear36.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), RetrieverActivity.class);
				intent.putExtra("retriever", "network");
				startActivity(intent);
				overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			}
		});
		
		_drawer_o6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				link.setAction(Intent.ACTION_VIEW);
				link.setData(Uri.parse("https://github.com/InfiniteLoops87/C-XYZ/issues"));
				startActivity(link);
			}
		});
		
		_drawer_o7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				link.setAction(Intent.ACTION_VIEW);
				link.setData(Uri.parse("https://t.me/inflpschat"));
				startActivity(link);
			}
		});
		
		_drawer_o8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				link.setAction(Intent.ACTION_VIEW);
				link.setData(Uri.parse("https://t.me/inflps_channel"));
				startActivity(link);
			}
		});
		
		_drawer_o9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				link.setAction(Intent.ACTION_VIEW);
				link.setData(Uri.parse("https://youtube.com/@inflps?si=VNaSrOxnNUw24S0K"));
				startActivity(link);
			}
		});
		
	}
	
	
	public void _CRadius(final View _view, final String _color) {
		android.graphics.drawable.GradientDrawable CRadius = new android.graphics.drawable.GradientDrawable();
		int dcr = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		CRadius.setColor(Color.parseColor(_color));
		CRadius.setCornerRadii(new float[]{dcr * 30, dcr * 30, dcr * 30, dcr * 30, dcr * 30, dcr * 30, dcr * 30, dcr * 30});
		CRadius.setStroke((int) (1 * dcr), Color.parseColor("#474747"));
		_view.setBackground(CRadius);
	}
	
	
	public void _Concave(final View _view, final String _color) {
		android.graphics.drawable.GradientDrawable Concave = new android.graphics.drawable.GradientDrawable();
		int d_ccv = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		Concave.setColor(Color.parseColor(_color));
		Concave.setCornerRadii(new float[]{d_ccv*30,d_ccv*30,d_ccv*30 ,d_ccv*30,d_ccv*0,d_ccv*0 ,d_ccv*0,d_ccv*0});
		Concave.setStroke((int) (1 * d_ccv), Color.parseColor("#474747"));
		_view.setBackground(Concave);
	}
	
	public class ListViewAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public ListViewAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.dirshortcutspopup, null);
			}
			
			final LinearLayout rootbg = _view.findViewById(R.id.rootbg);
			final LinearLayout currentFocusedView = _view.findViewById(R.id.currentFocusedView);
			final LinearLayout main_fore = _view.findViewById(R.id.main_fore);
			final LinearLayout op1 = _view.findViewById(R.id.op1);
			final LinearLayout LinearLayout4 = _view.findViewById(R.id.LinearLayout4);
			final LinearLayout vline = _view.findViewById(R.id.vline);
			final LinearLayout op2 = _view.findViewById(R.id.op2);
			final ImageView ic1 = _view.findViewById(R.id.ic1);
			final ImageView ic2 = _view.findViewById(R.id.ic2);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout LinearLayout3 = _view.findViewById(R.id.LinearLayout3);
			final LinearLayout line1 = _view.findViewById(R.id.line1);
			final ListView listview1 = _view.findViewById(R.id.listview1);
			final LinearLayout sec = _view.findViewById(R.id.sec);
			final LinearLayout LinearLayout6 = _view.findViewById(R.id.LinearLayout6);
			final ListView listview2 = _view.findViewById(R.id.listview2);
			final LinearLayout layout_no_items = _view.findViewById(R.id.layout_no_items);
			final LinearLayout clone = _view.findViewById(R.id.clone);
			final LinearLayout gotopath = _view.findViewById(R.id.gotopath);
			final LinearLayout LinearLayout5 = _view.findViewById(R.id.LinearLayout5);
			final ImageView up = _view.findViewById(R.id.up);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final ImageView no_file_folder = _view.findViewById(R.id.no_file_folder);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			
			return _view;
		}
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.filedesc, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout background1 = _view.findViewById(R.id.background1);
			final com.ceditor.SwipeRevealLayout swipe_reveal1 = _view.findViewById(R.id.swipe_reveal1);
			final LinearLayout archive = _view.findViewById(R.id.archive);
			final FrameLayout framelayout1 = _view.findViewById(R.id.framelayout1);
			final LinearLayout LinearLayout12 = _view.findViewById(R.id.LinearLayout12);
			final LinearLayout more = _view.findViewById(R.id.more);
			final LinearLayout l15 = _view.findViewById(R.id.l15);
			final ImageView imageview10 = _view.findViewById(R.id.imageview10);
			final LinearLayout moreMode = _view.findViewById(R.id.moreMode);
			final LinearLayout copyMode = _view.findViewById(R.id.copyMode);
			final LinearLayout cutMode = _view.findViewById(R.id.cutMode);
			final LinearLayout detailsMode = _view.findViewById(R.id.detailsMode);
			final LinearLayout deleteMode = _view.findViewById(R.id.deleteMode);
			final LinearLayout selectMode = _view.findViewById(R.id.selectMode);
			final ImageView imageview5 = _view.findViewById(R.id.imageview5);
			final TextView textview9 = _view.findViewById(R.id.textview9);
			final ImageView imageview6 = _view.findViewById(R.id.imageview6);
			final TextView textview10 = _view.findViewById(R.id.textview10);
			final ImageView imageview7 = _view.findViewById(R.id.imageview7);
			final TextView textview11 = _view.findViewById(R.id.textview11);
			final ImageView imageview9 = _view.findViewById(R.id.imageview9);
			final TextView textview13 = _view.findViewById(R.id.textview13);
			final ImageView imageview4 = _view.findViewById(R.id.imageview4);
			final TextView textview8 = _view.findViewById(R.id.textview8);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview6 = _view.findViewById(R.id.textview6);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
// 			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final FrameLayout linear10 = _view.findViewById(R.id.linear10);
			final TextView badge = _view.findViewById(R.id.badge);
			final ImageView picture = _view.findViewById(R.id.picture);
			final LinearLayout cb_container = _view.findViewById(R.id.cb_container);
			final CheckBox checkbox1 = _view.findViewById(R.id.checkbox1);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout linear11 = _view.findViewById(R.id.linear11);
			final LinearLayout tl = _view.findViewById(R.id.tl);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView length = _view.findViewById(R.id.length);
			final TextView date = _view.findViewById(R.id.date);
			
			textview1.setText(_data.get((int)_position).get("name").toString());
			checkbox1.setChecked(true);
			if (isGridMode) {
				swipe_reveal1.setSlidingEnabled(false);
				linear1.setOrientation(LinearLayout.VERTICAL);
				linear6.setOrientation(LinearLayout.VERTICAL);
				_ViewWidthHeight(linear1, ViewGroup.LayoutParams.MATCH_PARENT, SketchwareUtil.getDip(getApplicationContext(), (int)(160)));
				textview1.setTextSize((int)12);
				length.setTextSize((int)8);
				date.setTextSize((int)8);
				tl.setOrientation(LinearLayout.HORIZONTAL);
				tl.setGravity(Gravity.CENTER);
			}
			else {
				swipe_reveal1.setSlidingEnabled(true);
				linear1.setOrientation(LinearLayout.HORIZONTAL);
				linear6.setOrientation(LinearLayout.HORIZONTAL);
				_ViewWidthHeight(linear1, ViewGroup.LayoutParams.MATCH_PARENT, SketchwareUtil.getDip(getApplicationContext(), (int)(80)));
				textview1.setTextSize((int)16);
				length.setTextSize((int)12);
				date.setTextSize((int)12);
				tl.setOrientation(LinearLayout.VERTICAL);
				tl.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
			}
			_setEllipsize(length);
			_setEllipsize(date);
			textview1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
			subtitle.setMarqueeRepeatLimit(-1);
			textview1.setHorizontallyScrolling(true);
			textview1.setSelected(true);
			badge.setSelected(true);
			background1.setClipToOutline(true);
			swipe_reveal1.setDragEdge(SwipeRevealLayout.DRAG_EDGE_RIGHT);
			swipe_reveal1.setSwipeRevealMode(SwipeRevealLayout.MODE_SAME_LEVEL);
			
			android.graphics.drawable.GradientDrawable arcUi = new android.graphics.drawable.GradientDrawable();
			int ard = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			arcUi.setColor(0xCFFF0000);arcUi.setCornerRadii(new float[]{
				ard*10,ard*10,ard*0 ,ard*0,ard*0,ard*0 ,ard*10,ard*10});
			
			archive.setBackground(arcUi);
			swipe_reveal1.close(true); 
			if (select && selected_items.containsKey(String.valueOf(_position))) {
					background1.setBackgroundColor(0x80FF0000);
					cb_container.setVisibility(View.VISIBLE);
			}
			else {
					background1.setBackgroundColor(Color.TRANSPARENT);
					cb_container.setVisibility(View.GONE);
			}
			mainDirPathParent = FileUtil.getExternalStorageDir();
			primaryDirPathParent = Environment.getExternalStorageDirectory().getAbsolutePath();
			String folderName = textview1.getText().toString();
			boolean is_directory, is_file;
			if (usingUriToNavigate) {
					 is_directory = _data.get((int)_position).get("mime_type").toString().equals(android.provider.DocumentsContract.Document.MIME_TYPE_DIR);
					try {
							is_file = androidx.documentfile.provider.DocumentFile.fromTreeUri(MainActivity.this, Uri.parse(_data.get((int)_position).get("path").toString())).isFile();
					} catch (Exception w){
							is_file = false;
					}
			}
			else {
					 is_directory = FileUtil.isDirectory(_data.get((int)_position).get("path").toString());
					is_file = FileUtil.isFile(_data.get((int)_position).get("path").toString());
			}
			if (is_directory) {
				badge.setVisibility(View.GONE);
				length.setText("<DIR>");
				date.setText(_data.get((int)_position).get("date").toString());
				if (currentdir.equals(mainDirPathParent)) {
					_DirIconPlaceholder(picture, _data.get((int)_position).get("name").toString());
				}
				else {
					_DefaultDirIconPlaceholder(picture, _data.get((int)_position).get("name").toString());
				}
			}
			else {
				length.setText(_data.get((int)_position).get("size").toString());
				date.setText(_data.get((int)_position).get("date").toString());
				_FileIconPlaceholder(picture, filelist.get((int)_position).get("path").toString());
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
			checkbox1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							if (selected_items.containsKey(String.valueOf(_position))) {
									selected_items.remove(String.valueOf(_position));
									if (selected_items.size() < 1) {
											_setSelectionMode(0);
									}
									else {
											_setSelectionMode(2);
									}
							}
					}
			});
			linear1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							if (swipe_reveal1.getState() != SwipeRevealLayout.STATE_CLOSE) {
									swipe_reveal1.close(true); 
							}
							else {
									if (select) {
											if (selected_items.containsKey(String.valueOf(_position))) {
													selected_items.remove(String.valueOf(_position));
													if (selected_items.size() < 1) {
															_setSelectionMode(0);
													}
													else {
															_setSelectionMode(2);
													}
											}
											else {
													selected_items.put(String.valueOf(_position), filelist.get((int)_position).get("path").toString());
													_setSelectionMode(2);
											}
									}
									else {
											if (usingUriToNavigate) {
													if (filelist.get((int)_position).get("mime_type").toString().equals(android.provider.DocumentsContract.Document.MIME_TYPE_DIR)) {
															_openDir(Uri.parse(values.getString(lastdir + "_uri", "")), filelist.get((int)_position).get("path").toString());
													}
													else {
															_openFile(filelist.get((int)_position).get("path").toString(), filelist.get((int)_position).get("mime_type").toString());
													}
											}
											else {
													if (values.getString(filelist.get((int)_position).get("path").toString().concat("_uri"), "").equals("")) {
															_openFile(filelist.get((int)_position).get("path").toString(), filelist.get((int)_position).get("mime_type").toString());
													}
													else {
															lastdir = filelist.get((int)_position).get("path").toString();
															usingUriToNavigate = true;
															Uri uri1 = Uri.parse(values.getString(lastdir + "_uri", ""));
															_openDir(uri1, android.provider.DocumentsContract.getTreeDocumentId(uri1));
													}
											}
									}
							}
					}
			});
			if (select || isFileWalking) {
				selectMode.setVisibility(View.GONE);
				copyMode.setVisibility(View.GONE);
				cutMode.setVisibility(View.GONE);
				detailsMode.setVisibility(View.GONE);
			}
			else {
				selectMode.setVisibility(View.VISIBLE);
				selectMode.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
								if (select && selected_items.containsKey(String.valueOf(_position))) {
										if (selected_items.size() == 1) {
												_fileContextMenu(Double.parseDouble(String.valueOf(selected_items.keySet().toArray()[0])));
										}
										else {
												_fileContextMenu(-1);
										}
								}
								else {
										selected_items.put(String.valueOf(_position), filelist.get((int)_position).get("path").toString());
										_setSelectionMode(1);
								}
						}
				});
				if (is_directory) {
					cutMode.setVisibility(View.GONE);
				}
				else {
					cutMode.setVisibility(View.VISIBLE);
				}
				detailsMode.setVisibility(View.VISIBLE);
				copyMode.setVisibility(View.VISIBLE);
			}
			moreMode.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (!isFileWalking) {
						_fileContextMenu(Double.parseDouble(String.valueOf(_position)));
						fileContextMenuOnly = true;
						selected_items.put(String.valueOf(_position), filelist.get((int)_position).get("path").toString());
					}
				}
			});
			picture.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					if (!isFileWalking) {
						if (select && selected_items.containsKey(String.valueOf(_position))) {
										if (selected_items.size() == 1) {
												_fileContextMenu(Double.parseDouble(String.valueOf(selected_items.keySet().toArray()[0])));
										}
										else {
												_fileContextMenu(-1);
										}
								}
								else {
										selected_items.put(String.valueOf(_position), filelist.get((int)_position).get("path").toString());
										_setSelectionMode(1);
								}
						fileContextMenuOnly = false;
					}
					return true;
				}
			});
			picture.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
							if (select) {
									if (selected_items.containsKey(String.valueOf(_position))) {
											selected_items.remove(String.valueOf(_position));
											if (selected_items.size() < 1) {
													_setSelectionMode(0);
											}
											else {
													_setSelectionMode(2);
											}
									}
									else {
											selected_items.put(String.valueOf(_position), filelist.get((int)_position).get("path").toString());
											_setSelectionMode(2);
									}
							}
							else {
									if (usingUriToNavigate) {
											if (filelist.get((int)_position).get("mime_type").toString().equals(android.provider.DocumentsContract.Document.MIME_TYPE_DIR)) {
													_openDir(Uri.parse(values.getString(lastdir + "_uri", "")), filelist.get((int)_position).get("path").toString());
											}
											else {
													_openFile(filelist.get((int)_position).get("path").toString(), filelist.get((int)_position).get("mime_type").toString());
											}
									}
									else {
											if (values.getString(filelist.get((int)_position).get("path").toString().concat("_uri"), "").equals("")) {
													_openFile(filelist.get((int)_position).get("path").toString(), filelist.get((int)_position).get("mime_type").toString());
											}
											else {
													lastdir = filelist.get((int)_position).get("path").toString();
													usingUriToNavigate = true;
													Uri uri1 = Uri.parse(values.getString(lastdir + "_uri", ""));
													_openDir(uri1, android.provider.DocumentsContract.getTreeDocumentId(uri1));
											}
									}
							}
					}
			});
			detailsMode.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					open_file.putExtra("tpath", filelist.get((int)_position).get("path").toString());
					open_file.setClass(getApplicationContext(), FileInfoActivity.class);
					startActivity(open_file);
					overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
				}
			});
			deleteMode.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					del.setTitle("Following item will be deleted:");
					del.setMessage(_data.get((int)_position).get("name").toString().concat(" ~ ".concat(filelist.get((int)_position).get("path").toString().concat("\nAttention! Once the item is deleted, you will no longer be able to undo this action!\nAre you sure to perform this action?"))));
					del.setPositiveButton("Proceed (Delete)", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							_AppDesignerToast("Deleted".concat(filelist.get((int)_position).get("path").toString().concat(", successfully.")));
							FileUtil.deleteFile(filelist.get((int)_position).get("path").toString());
							_refreshItems("");
						}
					});
					del.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					_CXYZ_dialog_theme(del);
				}
			});
			linear1.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View _view) {
					if (!isFileWalking) {
						selected_items.put(String.valueOf(_position), filelist.get((int)_position).get("path").toString());
						_setSelectionMode(1);
						if (selected_items.size() < 1) {
							_fileContextMenu(-1);
						}
						else {
							_fileContextMenu(Double.parseDouble(String.valueOf(selected_items.keySet().toArray()[0])));
						}
						fileContextMenuOnly = true;
					}
					return true;
				}
			});
			copyMode.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					ArrayList<String> keys = new ArrayList<String>();
					selected_items.put(String.valueOf(_position), filelist.get((int)_position).get("path").toString());
					file_operation = 1;
					SketchwareUtil.getAllKeysFromMap(selected_items, keys);
					for(int _repeat158 = 0; _repeat158 < (int)(selected_items.size()); _repeat158++) {
						list_of_selected_items.add(selected_items.get(keys.get((int)(_repeat158))).toString());
					}
					paste.setVisibility(usingUriToNavigate || new java.io.File(currentdir).canWrite() ? View.VISIBLE : View.GONE);
					cancel.setVisibility(View.VISIBLE);
					swipe_reveal1.close(true); 
					isFileWalking = true;
				}
			});
			cutMode.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					ArrayList<String> keys = new ArrayList<String>();
					selected_items.put(String.valueOf(_position), filelist.get((int)_position).get("path").toString());
					file_operation = 3;
					SketchwareUtil.getAllKeysFromMap(selected_items, keys);
					for(int _repeat178 = 0; _repeat178 < (int)(selected_items.size()); _repeat178++) {
						list_of_selected_items.add(selected_items.get(keys.get((int)(_repeat178))).toString());
					}
					paste.setVisibility(usingUriToNavigate || new java.io.File(currentdir).canWrite() ? View.VISIBLE : View.GONE);
					cancel.setVisibility(View.VISIBLE);
					swipe_reveal1.close(true); 
					isFileWalking = true;
				}
			});
			Animation slideL = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_left);
			tl.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (!isGridMode) {
						if (swipe_reveal1.getState() != SwipeRevealLayout.STATE_CLOSE) {
							swipe_reveal1.close(true);
						}
						else {
							swipe_reveal1.open(true);
							more.startAnimation(slideL);
							l15.setVisibility(View.GONE);
							more.setVisibility(View.VISIBLE);
						}
					}
				}
			});
			more.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					l15.startAnimation(slideL);
					l15.setVisibility(View.VISIBLE);
					more.setVisibility(View.GONE);
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
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
