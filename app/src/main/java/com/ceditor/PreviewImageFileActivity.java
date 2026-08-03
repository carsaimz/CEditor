package com.ceditor;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.robinhood.ticker.*;
import io.github.rosemoe.sora.*;
import io.github.rosemoe.sora.langs.textmate.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import com.bumptech.glide.Glide;

public class PreviewImageFileActivity extends AppCompatActivity {
	
	private String path = "";
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private FrameLayout linear7;
	private ImageView imageview2;
	private LinearLayout linear4;
	private TextView textview1;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private ImageView imageview1;
	private LinearLayout linear8;
	private LinearLayout action;
	private ImageView imageview4;
	private ImageView imageview3;
	private TextView textview2;
	
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.preview_image_file);
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
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear7 = findViewById(R.id.linear7);
		imageview2 = findViewById(R.id.imageview2);
		linear4 = findViewById(R.id.linear4);
		textview1 = findViewById(R.id.textview1);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		imageview1 = findViewById(R.id.imageview1);
		linear8 = findViewById(R.id.linear8);
		action = findViewById(R.id.action);
		imageview4 = findViewById(R.id.imageview4);
		imageview3 = findViewById(R.id.imageview3);
		textview2 = findViewById(R.id.textview2);
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), ContentViewerActivity.class);
				intent.putExtra("path", path);
				intent.putExtra("type", "image");
				startActivity(intent);
			}
		});
		
		action.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), EditimageActivity.class);
				intent.putExtra("path", path);
				startActivity(intent);
			}
		});
	}
	
	private void initializeLogic() {
		Window window = getWindow();
		window.setStatusBarColor(Color.TRANSPARENT);
		window.getDecorView().setSystemUiVisibility(
		View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		
		
		linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)1, 0xFFE0E0E0, 0xFF111111));
		path = getIntent().getStringExtra("path");
		textview1.setText(Uri.parse(path).getLastPathSegment());
		_setImageFromFile(imageview1, path);
		_convertToBottomSheet();
		_ripple(imageview2);
		action.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)1, 0xFFE0E0E0, 0xCA161616));
		linear8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)1, 0xFFE0E0E0, 0xCA161616));
		textview1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		textview1.setMarqueeRepeatLimit(-1);
		textview1.setHorizontallyScrolling(true);
		textview1.setSelected(true);
	}
	
	public void _setImageFromFile(final ImageView _img, final String _path) {
		java.io.File file = new java.io.File(_path);
		Uri imageUri = Uri.fromFile(file);
		Glide.with(getApplicationContext())
		.load(imageUri)
		.error(R.drawable.imagemissing_92832)
		.into(_img);
		_img.clearColorFilter();
	}
	
	
	public void _ViewWidthHeight(final View _view, final double _w, final double _h) {
		_view.getLayoutParams().width = (int)_w;
		_view.getLayoutParams().height = (int)_h;
		_view.requestLayout();
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
	
	
	public void _convertToBottomSheet() {
	}
	
	
	private androidx.coordinatorlayout.widget.CoordinatorLayout mCoordinatorLayout;
	
	@Override
	public void finish(){
        if (mCoordinatorLayout != null && mCoordinatorLayout.getChildCount() > 0) {
            com.google.android.material.bottomsheet.BottomSheetBehavior.from(mCoordinatorLayout.getChildAt(0)).setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            superFinish();
        }
	}
	
	
	private void superFinish(){
			super.finish();
	}
	
	
	@Override
	public void setContentView(int layId){
			if(mCoordinatorLayout == null){
					overridePendingTransition(0,0);
					mCoordinatorLayout = new androidx.coordinatorlayout.widget.CoordinatorLayout(this);
					mCoordinatorLayout.setBackgroundColor(0x00000000);
					mCoordinatorLayout.setOnClickListener(new View.OnClickListener(){
							
							@Override
							public void onClick (View v){
									finish();
							}
					});
			}
			
			mCoordinatorLayout.removeAllViews();
			androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams params = new androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
			
			final com.google.android.material.bottomsheet.BottomSheetBehavior behavior = new com.google.android.material.bottomsheet.BottomSheetBehavior();
			params.setBehavior(behavior);
			behavior.setBottomSheetCallback(new com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback(){
					
					@Override
					public void onSlide(View bottomSheet, float slideOffset){
							
					}
					
					@Override
					public void onStateChanged(View bottomSheet, int newState){
							if(newState == com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED){
									superFinish();
									overridePendingTransition(0,0);
							}
					}
			});
			
			View inflated = getLayoutInflater().inflate(layId,null);	
			mCoordinatorLayout.addView(inflated,params);
			
			if(mCoordinatorLayout.getParent()!= null)((ViewGroup)mCoordinatorLayout.getParent()).removeView(mCoordinatorLayout);
			setContentView(mCoordinatorLayout);
			inflated.post(new Runnable(){
					
					@Override
					public void run() {
							behavior.setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED);
					}});
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
}
