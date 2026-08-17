package com.ceditor;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.media.MediaPlayer;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class AudioplayerActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private String path = "";
	
	private LinearLayout layout;
	private LinearLayout linear1;
	private FrameLayout linear2;
	private LinearLayout linear3;
	private ImageView close;
	private TextView title;
	private TextView error;
	private SeekBar seekbar1;
	private WaveView mWave;
	private TextView duration;
	private LinearLayout play_pause;
	private ImageView imageview1;
	
	private MediaPlayer mp;
	private TimerTask t;
	private Calendar current_duration = Calendar.getInstance();
	private Calendar max_duration = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.audioplayer);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		layout = findViewById(R.id.layout);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		close = findViewById(R.id.close);
		title = findViewById(R.id.title);
		error = findViewById(R.id.error);
		seekbar1 = findViewById(R.id.seekbar1);
		mWave = findViewById(R.id.mWave);
		duration = findViewById(R.id.duration);
		play_pause = findViewById(R.id.play_pause);
		imageview1 = findViewById(R.id.imageview1);
		
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
		seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				current_duration.setTimeInMillis((long)(mp.getCurrentPosition()));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				mp.seekTo((int)(seekbar1.getProgress() * 1000));
			}
		});
		
		play_pause.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (mp.isPlaying()) {
					mp.pause();
					imageview1.setImageResource(R.drawable.content_play);
				}
				else {
					mp.start();
					imageview1.setImageResource(R.drawable.content_pause);
				}
			}
		});
	}
	
	private void initializeLogic() {
		Window window = getWindow();
		window.setStatusBarColor(Color.TRANSPARENT);
		window.getDecorView().setSystemUiVisibility(
		View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		
		
		path = getIntent().getStringExtra("path");
		title.setText(getIntent().getStringExtra("title"));
		_setEllipsize(title);
		seekbar1.setForeground(null);
		_ripple(play_pause);
		_ripple(close);
		layout.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)1, 0xFFE0E0E0, 0xFF111111));
	}
	
	@Override
	public void onStart() {
		super.onStart();
		mp = new MediaPlayer().create(getApplicationContext(), Uri.parse(path));
		if (mp!=null) {
			error.setVisibility(View.GONE);
			seekbar1.setMax((int)Math.round(mp.getDuration() / 1000));
			current_duration.setTimeInMillis((long)(0));
			max_duration.setTimeInMillis((long)(mp.getDuration()));
			mp.start();
			imageview1.setImageResource(R.drawable.content_pause);
			mp.setLooping(true);
			SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss", Locale.getDefault());
			mWave.updateVisualizer(mWave.fileToByte(path));
			t = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							float start = mp.getCurrentPosition() ;
							float end = mp.getDuration();
							float FF = start / end ;
							float f = FF;
							mWave.updatePlayerPercent(f);
							String currentTime = timeFormat.format(new Date((long) start));
							String totalTime = timeFormat.format(new Date((long) end));
							duration.setText(String.format("%s / %s", currentTime, totalTime));
						}
					});
				}
			};
			_timer.scheduleAtFixedRate(t, (int)(0), (int)(100));
			mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
				 @Override
				public void onPrepared(MediaPlayer mp3){
					mp3.start();
				}
			});
		}
		else {
			error.setVisibility(View.VISIBLE);
			seekbar1.setVisibility(View.GONE);
			mWave.setVisibility(View.GONE);
			error.setText("Unexpected error: Audio file is null or corrupted!");
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mp.isPlaying()) {
			mp.stop();
		}
	}
	
	@Override
	public void onBackPressed() {
		if (mp.isPlaying()) {
			mp.stop();
		}
		finish();
		overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (mp.isPlaying() && mp != null) {
			mp.pause();
		}
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
