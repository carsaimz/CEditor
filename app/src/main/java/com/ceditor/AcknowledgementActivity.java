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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;
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
// import io.github.rosemoe.sora.langs.textmate.*; // Commented out due to missing language-textmate library
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class AcknowledgementActivity extends AppCompatActivity {
	
	private FrameLayout LinearLayout1;
	private LinearLayout scrollviewcontainer;
	private LinearLayout toolbar;
	private WebView WebView1;
	private LinearLayout LinearLayout7;
	private LinearLayout topline;
	private ImageView close;
	private LinearLayout LinearLayout6;
	private TextView title;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.acknowledgement);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		LinearLayout1 = findViewById(R.id.LinearLayout1);
		scrollviewcontainer = findViewById(R.id.scrollviewcontainer);
		toolbar = findViewById(R.id.toolbar);
		WebView1 = findViewById(R.id.WebView1);
		WebView1.getSettings().setJavaScriptEnabled(true);
		WebView1.getSettings().setSupportZoom(true);
		LinearLayout7 = findViewById(R.id.LinearLayout7);
		topline = findViewById(R.id.topline);
		close = findViewById(R.id.close);
		LinearLayout6 = findViewById(R.id.LinearLayout6);
		title = findViewById(R.id.title);
		
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
		
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		try {
				java.io.InputStream WebView1In = this.getAssets().open("acknowledgement.html");
				int WebView1Si = WebView1In.available();
				byte[] WebView1Bu = new byte[WebView1Si];
				WebView1In.read(WebView1Bu);
				WebView1In.close();
				final String WebView1Str = new String(WebView1Bu, "UTF-8");
				WebSettings WebView1Ss = WebView1.getSettings(); 
				WebView1Ss.setJavaScriptEnabled(true); 
				WebView1Ss.setJavaScriptCanOpenWindowsAutomatically(true);
				if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
						WebView1Ss.setAllowFileAccessFromFileURLs(true); 
						WebView1Ss.setAllowUniversalAccessFromFileURLs(true);
				}
				WebView1.loadDataWithBaseURL(null, WebView1Str, "text/html", "UTF-8", null);
		} catch(Exception e) {
				e.printStackTrace();
		}
		_ripple(close);
		toolbar.setBackgroundColor(0xD9111111);
	}
	
	@Override
	public void onBackPressed() {
		if (WebView1.canGoBack()) {
			WebView1.goBack();
		}
		else {
			finish();
			overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
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
