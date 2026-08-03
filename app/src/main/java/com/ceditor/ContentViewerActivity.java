package com.ceditor;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.robinhood.ticker.*;
import io.github.rosemoe.sora.*;
import io.github.rosemoe.sora.langs.textmate.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class ContentViewerActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private boolean mute = false;
	
	private LinearLayout toolbar1;
	private FrameLayout FrameLayout;
	private LinearLayout toolbar1_inner;
	private ImageView close;
	private LinearLayout toolbar1_info;
	private ImageView media_controls;
	private ImageView imageview1;
	private TextView title;
	private TextView subtitle;
	private LinearLayout LinearWebViewContainer;
	private LinearLayout MediaControlsContainer;
	private WebView webview1;
	private LinearLayout LinearLayout1;
	private LinearLayout mediacontrols;
	private ImageView play_pause;
	private ImageView stop;
	private ImageView mute_unmute;
	private HorizontalScrollView HorizontalScrollView1;
	private ImageView hide;
	private LinearLayout playback_group;
	private TextView playback0_25;
	private TextView playback0_5;
	private TextView playback1_normal;
	private TextView playback1_25;
	private TextView playback1_5;
	private TextView playback1_75;
	private TextView playback2;
	private TextView playback3;
	private TextView playback4;
	
	private TimerTask timer;
	private SharedPreferences data;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.content_viewer);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		toolbar1 = findViewById(R.id.toolbar1);
		FrameLayout = findViewById(R.id.FrameLayout);
		toolbar1_inner = findViewById(R.id.toolbar1_inner);
		close = findViewById(R.id.close);
		toolbar1_info = findViewById(R.id.toolbar1_info);
		media_controls = findViewById(R.id.media_controls);
		imageview1 = findViewById(R.id.imageview1);
		title = findViewById(R.id.title);
		subtitle = findViewById(R.id.subtitle);
		LinearWebViewContainer = findViewById(R.id.LinearWebViewContainer);
		MediaControlsContainer = findViewById(R.id.MediaControlsContainer);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		LinearLayout1 = findViewById(R.id.LinearLayout1);
		mediacontrols = findViewById(R.id.mediacontrols);
		play_pause = findViewById(R.id.play_pause);
		stop = findViewById(R.id.stop);
		mute_unmute = findViewById(R.id.mute_unmute);
		HorizontalScrollView1 = findViewById(R.id.HorizontalScrollView1);
		hide = findViewById(R.id.hide);
		playback_group = findViewById(R.id.playback_group);
		playback0_25 = findViewById(R.id.playback0_25);
		playback0_5 = findViewById(R.id.playback0_5);
		playback1_normal = findViewById(R.id.playback1_normal);
		playback1_25 = findViewById(R.id.playback1_25);
		playback1_5 = findViewById(R.id.playback1_5);
		playback1_75 = findViewById(R.id.playback1_75);
		playback2 = findViewById(R.id.playback2);
		playback3 = findViewById(R.id.playback3);
		playback4 = findViewById(R.id.playback4);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
		media_controls.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
				Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
				mediacontrols.startAnimation(slideUp);
				mediacontrols.setVisibility(View.VISIBLE);
				media_controls.startAnimation(slideDown);
				media_controls.setVisibility(View.GONE);
				
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				toggleFullscreen();
			}
		});
		
		webview1.setWebViewClient(new WebViewClient() {
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
		
		play_pause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript(
				    "(function() {" +
				    "  var video = document.querySelector('video');" +
				    "  if (video.paused) {" +
				    "    video.play();" +
				    "    return 'playing';" +
				    "  } else {" +
				    "    video.pause();" +
				    "    return 'paused';" +
				    "  }" +
				    "})()",
				value -> {
					if ("\"playing\"".equals(value)) {
						play_pause.setImageResource(R.drawable.content_pause);
					}
					else {
						if ("\"paused\"".equals(value)) {
							play_pause.setImageResource(R.drawable.content_play);
						}
					}
				}
				);
			}
		});
		
		stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript(
				    "(function() {" +
				    "  var video = document.querySelector('video');" +
				    "  video.pause();" +          // Pause the video
				    "  video.currentTime = 0;" + // Reset playback position
				    "})()",
				    null
				);
				play_pause.setImageResource(R.drawable.content_play);
			}
		});
		
		mute_unmute.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mute = !mute;
				webview1.evaluateJavascript(
				    "document.querySelector('video').muted = " + (mute ? "true" : "false") + ";",
				    null
				);
				
				int icon = mute ? R.drawable.content_volume_state_1 : R.drawable.content_volume_state_2;
				mute_unmute.setImageResource(icon);
			}
		});
		
		hide.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
				Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
				mediacontrols.startAnimation(slideDown);
				mediacontrols.setVisibility(View.GONE);
				media_controls.startAnimation(slideUp);
				media_controls.setVisibility(View.VISIBLE);
			}
		});
		
		playback0_25.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("document.querySelector('video').playbackRate = 0.25;", null);
				playback0_25.setTextColor(0xFFFF0000);
				playback0_5.setTextColor(0xFFFFFFFF);
				playback1_normal.setTextColor(0xFFFFFFFF);
				playback1_5.setTextColor(0xFFFFFFFF);
				playback2.setTextColor(0xFFFFFFFF);
				playback1_25.setTextColor(0xFFFFFFFF);
				playback1_75.setTextColor(0xFFFFFFFF);
				playback3.setTextColor(0xFFFFFFFF);
				playback4.setTextColor(0xFFFFFFFF);
			}
		});
		
		playback0_5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("document.querySelector('video').playbackRate = 0.5;", null);
				playback0_25.setTextColor(0xFFFFFFFF);
				playback0_5.setTextColor(0xFFFF0000);
				playback1_normal.setTextColor(0xFFFFFFFF);
				playback1_5.setTextColor(0xFFFFFFFF);
				playback2.setTextColor(0xFFFFFFFF);
				playback1_25.setTextColor(0xFFFFFFFF);
				playback1_75.setTextColor(0xFFFFFFFF);
				playback3.setTextColor(0xFFFFFFFF);
				playback4.setTextColor(0xFFFFFFFF);
			}
		});
		
		playback1_normal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("document.querySelector('video').playbackRate = 1;", null);
				playback0_25.setTextColor(0xFFFFFFFF);
				playback0_5.setTextColor(0xFFFFFFFF);
				playback1_normal.setTextColor(0xFFFF0000);
				playback1_5.setTextColor(0xFFFFFFFF);
				playback2.setTextColor(0xFFFFFFFF);
				playback1_25.setTextColor(0xFFFFFFFF);
				playback1_75.setTextColor(0xFFFFFFFF);
				playback3.setTextColor(0xFFFFFFFF);
				playback4.setTextColor(0xFFFFFFFF);
			}
		});
		
		playback1_25.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("document.querySelector('video').playbackRate = 1.25;", null);
				playback0_25.setTextColor(0xFFFFFFFF);
				playback0_5.setTextColor(0xFFFFFFFF);
				playback1_normal.setTextColor(0xFFFFFFFF);
				playback1_5.setTextColor(0xFFFFFFFF);
				playback2.setTextColor(0xFFFFFFFF);
				playback1_25.setTextColor(0xFFFF0000);
				playback1_75.setTextColor(0xFFFFFFFF);
				playback3.setTextColor(0xFFFFFFFF);
				playback4.setTextColor(0xFFFFFFFF);
			}
		});
		
		playback1_5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("document.querySelector('video').playbackRate = 1.5;", null);
				playback0_5.setTextColor(0xFFFFFFFF);
				playback1_normal.setTextColor(0xFFFFFFFF);
				playback1_5.setTextColor(0xFFFF0000);
				playback2.setTextColor(0xFFFFFFFF);
				playback1_25.setTextColor(0xFFFFFFFF);
				playback1_75.setTextColor(0xFFFFFFFF);
				playback3.setTextColor(0xFFFFFFFF);
				playback4.setTextColor(0xFFFFFFFF);
				playback0_25.setTextColor(0xFFFFFFFF);
			}
		});
		
		playback1_75.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("document.querySelector('video').playbackRate = 1.75;", null);
				playback0_25.setTextColor(0xFFFFFFFF);
				playback0_5.setTextColor(0xFFFFFFFF);
				playback1_normal.setTextColor(0xFFFFFFFF);
				playback1_5.setTextColor(0xFFFFFFFF);
				playback2.setTextColor(0xFFFFFFFF);
				playback1_25.setTextColor(0xFFFFFFFF);
				playback1_75.setTextColor(0xFFFF0000);
				playback3.setTextColor(0xFFFFFFFF);
				playback4.setTextColor(0xFFFFFFFF);
			}
		});
		
		playback2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("document.querySelector('video').playbackRate = 2;", null);
				playback0_5.setTextColor(0xFFFFFFFF);
				playback1_normal.setTextColor(0xFFFFFFFF);
				playback1_5.setTextColor(0xFFFFFFFF);
				playback2.setTextColor(0xFFFF0000);
				playback1_25.setTextColor(0xFFFFFFFF);
				playback1_75.setTextColor(0xFFFFFFFF);
				playback3.setTextColor(0xFFFFFFFF);
				playback4.setTextColor(0xFFFFFFFF);
			}
		});
		
		playback3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("document.querySelector('video').playbackRate = 3;", null);
				playback0_25.setTextColor(0xFFFFFFFF);
				playback0_5.setTextColor(0xFFFFFFFF);
				playback1_normal.setTextColor(0xFFFFFFFF);
				playback1_5.setTextColor(0xFFFFFFFF);
				playback2.setTextColor(0xFFFFFFFF);
				playback1_25.setTextColor(0xFFFFFFFF);
				playback1_75.setTextColor(0xFFFFFFFF);
				playback3.setTextColor(0xFFFF0000);
				playback4.setTextColor(0xFFFFFFFF);
			}
		});
		
		playback4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				webview1.evaluateJavascript("document.querySelector('video').playbackRate = 4;", null);
				playback0_25.setTextColor(0xFFFFFFFF);
				playback0_5.setTextColor(0xFFFFFFFF);
				playback1_normal.setTextColor(0xFFFFFFFF);
				playback1_5.setTextColor(0xFFFFFFFF);
				playback2.setTextColor(0xFFFFFFFF);
				playback1_25.setTextColor(0xFFFFFFFF);
				playback1_75.setTextColor(0xFFFFFFFF);
				playback3.setTextColor(0xFFFFFFFF);
				playback4.setTextColor(0xFFFF0000);
			}
		});
	}
	
	private void initializeLogic() {
		webview1.loadUrl("data:text/html, <h1> Cannot open file.\nPress back to main menu. </h1>");
		mediacontrols.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)1, 0xFF474747, 0xA9111111));
		HorizontalScrollView1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, 0xD9111111));
		media_controls.setVisibility(View.GONE);
		webview1.getSettings().setSupportZoom(true);
		webview1.getSettings().setBuiltInZoomControls(true);
		webview1.getSettings().setDisplayZoomControls(false);
		webview1.getSettings().setDomStorageEnabled(true);
		webview1.getSettings().setJavaScriptEnabled(true);
		mute = false;
		if (getIntent().hasExtra("type") && getIntent().hasExtra("path")) {
			webview1.loadUrl("file://".concat(getIntent().getStringExtra("path")));
			if (getIntent().getStringExtra("type").equals("image")) {
				title.setText(MainActivity.getFileName(getIntent().getStringExtra("path")));
				subtitle.setText("Image");
				mediacontrols.setVisibility(View.GONE);
				webview1.getSettings().setLoadWithOverviewMode(true);
				webview1.getSettings().setUseWideViewPort(true);
			}
			if (getIntent().getStringExtra("type").equals("video")) {
				title.setText(MainActivity.getFileName(getIntent().getStringExtra("path")));
				subtitle.setText("Video");
				mediacontrols.setVisibility(View.VISIBLE);
				webview1.evaluateJavascript(
				"var video = document.querySelector('video');" +
				"video.loop = true;" +
				"video.autoplay = true;" +
				"video.play();", 
				null
				);
			}
		}
		_setEllipsize(title);
		_setEllipsize(subtitle);
		_ripple(close);
		_ripple(play_pause);
		_ripple(stop);
		_ripple(mute_unmute);
		_ripple(hide);
		_ripple(media_controls);
		_ripple(imageview1);
	}
	
	
	boolean isFullscreen = false;
	
	
	public void toggleFullscreen() {
		    if (isFullscreen) {
			        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
			    } else {
			        getWindow().getDecorView().setSystemUiVisibility(
			            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
			            View.SYSTEM_UI_FLAG_FULLSCREEN |
			            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
			        );
			    }
		    isFullscreen = !isFullscreen;
	}
	
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
	}
	public void _gradDrawable(final String _bgColor, final String _strokeColor, final double _stroke, final double _rad, final double _elv, final boolean _rpl, final View _view) {
		MainActivity.gradDrawable(getApplicationContext(), _bgColor, _strokeColor, _stroke, _rad, _elv, _rpl, _view);
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
