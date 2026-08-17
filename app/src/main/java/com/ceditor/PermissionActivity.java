package com.ceditor;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class PermissionActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private LinearLayout LinearLayout1;
	private LinearLayout linear6;
	private LinearLayout LinearLayout8;
	private ImageView imageview3;
	private TextView textview3;
	private TextView grand_permission_bttn;
	private LinearLayout LinearLayout7;
	private TextView TextView4;
	
	private AlertDialog.Builder permission;
	private Intent i = new Intent();
	private TimerTask t;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.permission);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		LinearLayout1 = findViewById(R.id.LinearLayout1);
		linear6 = findViewById(R.id.linear6);
		LinearLayout8 = findViewById(R.id.LinearLayout8);
		imageview3 = findViewById(R.id.imageview3);
		textview3 = findViewById(R.id.textview3);
		grand_permission_bttn = findViewById(R.id.grand_permission_bttn);
		LinearLayout7 = findViewById(R.id.LinearLayout7);
		TextView4 = findViewById(R.id.TextView4);
		permission = new AlertDialog.Builder(this);
		
		grand_permission_bttn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				grantStoragePermission();
			}
		});
	}
	
	private void initializeLogic() {
		checkPermission();
		_ripple(grand_permission_bttn);
	}
	
	
	private void checkPermission() {
		if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED || androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED) {
			
			android.graphics.drawable.GradientDrawable negativeUi = new android.graphics.drawable.GradientDrawable();
			int clrsr [] = {0xFFD50000,
				                0x00000000,
				                0x00000000};
			negativeUi = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, clrsr);
			linear6.setBackground(negativeUi);
			imageview3.setImageResource(R.drawable.content_dismiss);
			textview3.setText("Storage permission not granted or denied!");
			grand_permission_bttn.setVisibility(View.VISIBLE);
			permission.setTitle("Permission Required");
			permission.setMessage("You need to grant storage permission for the app to work properly!");
			permission.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					grantStoragePermission();
				}
			});
			permission.setNegativeButton("Leave", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					finish();
				}
			});
			_CXYZ_dialog_theme(permission);
		} else {
			android.graphics.drawable.GradientDrawable positiveUi = new android.graphics.drawable.GradientDrawable();
			int clrsr [] = {0xFF00A300,
				                0x00000000,
				                0x00000000};
			positiveUi = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, clrsr);
			linear6.setBackground(positiveUi);
			imageview3.setImageResource(R.drawable.content_check);
			textview3.setText("Storage permission granted!");
			grand_permission_bttn.setVisibility(View.GONE);
			i.setClass(getApplicationContext(), MainActivity.class);
			startActivity(i);
			overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			finish();
		}
	}
	
	
	 @Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1001) {
			if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED || androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED) {
				
				finish();
			} else {
				grantStoragePermission();
			}
		}
	}
	
	
	private void grantStoragePermission() {
		if (androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED || androidx.core.content.ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED) {
			
			androidx.core.app.ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
		} else {
			android.graphics.drawable.GradientDrawable positiveUi = new android.graphics.drawable.GradientDrawable();
			int clrsr [] = {0xFF00A300,
				                0x00000000,
				                0x00000000};
			positiveUi = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, clrsr);
			linear6.setBackground(positiveUi);
			imageview3.setImageResource(R.drawable.content_check);
			grand_permission_bttn.setVisibility(View.GONE);
			textview3.setText("Storage permission granted!");
			t = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							i.setClass(getApplicationContext(), MainActivity.class);
							startActivity(i);
							overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
							finish();
						}
					});
				}
			};
			_timer.schedule(t, (int)(500));
		}
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
