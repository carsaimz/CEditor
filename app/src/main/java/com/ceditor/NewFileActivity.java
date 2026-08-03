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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import androidx.core.widget.NestedScrollView;



public class NewFileActivity extends AppCompatActivity {
	
	private boolean h = false;
	private String type = "";
	private String path = "";
	
	private LinearLayout LinearLayout20;
	private LinearLayout linear8;
	private LinearLayout LinearLayout10;
	private LinearLayout LinearLayout7;
	private TextView textview1;
	private LinearLayout nfile;
	private TextView textview17;
	private LinearLayout nfolder;
	private ImageView imageview6;
	private TextView textview14;
	private ImageView imageview7;
	private TextView textview16;
	private LinearLayout main_page;
	private LinearLayout miscellaneous_page;
	private LinearLayout b1;
	private LinearLayout b2;
	private NestedScrollView ScrollView2;
	private LinearLayout main_fore;
	private LinearLayout linear3;
	private LinearLayout LinearLayout9;
	private LinearLayout linear6;
	private EditText edittext2;
	private ImageView imageview1;
	private TextView textview2;
	private TextView hiddenDot;
	private EditText edittext1;
	private ImageView imageview2;
	private TextView textview3;
	private NestedScrollView vscroll1;
	private LinearLayout miscellaneous_fore;
	private TextView textview11;
	private LinearLayout adv1;
	private ImageView imageview8;
	private CheckBox hidden;
	private LinearLayout cancel;
	private LinearLayout LinearLayout8;
	private LinearLayout create;
	private ImageView imageview9;
	private TextView textview18;
	private ImageView imageview10;
	private TextView textview19;
	
	private Intent i = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.new_file);
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
		LinearLayout20 = findViewById(R.id.LinearLayout20);
		linear8 = findViewById(R.id.linear8);
		LinearLayout10 = findViewById(R.id.LinearLayout10);
		LinearLayout7 = findViewById(R.id.LinearLayout7);
		textview1 = findViewById(R.id.textview1);
		nfile = findViewById(R.id.nfile);
		textview17 = findViewById(R.id.textview17);
		nfolder = findViewById(R.id.nfolder);
		imageview6 = findViewById(R.id.imageview6);
		textview14 = findViewById(R.id.textview14);
		imageview7 = findViewById(R.id.imageview7);
		textview16 = findViewById(R.id.textview16);
		main_page = findViewById(R.id.main_page);
		miscellaneous_page = findViewById(R.id.miscellaneous_page);
		b1 = findViewById(R.id.b1);
		b2 = findViewById(R.id.b2);
		ScrollView2 = findViewById(R.id.ScrollView2);
		main_fore = findViewById(R.id.main_fore);
		linear3 = findViewById(R.id.linear3);
		LinearLayout9 = findViewById(R.id.LinearLayout9);
		linear6 = findViewById(R.id.linear6);
		edittext2 = findViewById(R.id.edittext2);
		imageview1 = findViewById(R.id.imageview1);
		textview2 = findViewById(R.id.textview2);
		hiddenDot = findViewById(R.id.hiddenDot);
		edittext1 = findViewById(R.id.edittext1);
		imageview2 = findViewById(R.id.imageview2);
		textview3 = findViewById(R.id.textview3);
		vscroll1 = findViewById(R.id.vscroll1);
		miscellaneous_fore = findViewById(R.id.miscellaneous_fore);
		textview11 = findViewById(R.id.textview11);
		adv1 = findViewById(R.id.adv1);
		imageview8 = findViewById(R.id.imageview8);
		hidden = findViewById(R.id.hidden);
		cancel = findViewById(R.id.cancel);
		LinearLayout8 = findViewById(R.id.LinearLayout8);
		create = findViewById(R.id.create);
		imageview9 = findViewById(R.id.imageview9);
		textview18 = findViewById(R.id.textview18);
		imageview10 = findViewById(R.id.imageview10);
		textview19 = findViewById(R.id.textview19);
		
		nfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				nfile.setBackground(new GradientDrawable() {
					    public GradientDrawable getIns(int a, int b, int c, int d) {
						        this.setCornerRadius(a);
						        this.setStroke(b, c);
						        this.setColor(d);
						        return this;
						    }
				}.getIns(15, 3, 0xFFFF0000, 0xFF3F1616));
				textview14.setTextColor(0xFFFF0000);
				imageview6.setColorFilter(0xFFFF0000, PorterDuff.Mode.SRC_IN);
				nfolder.setBackground(new GradientDrawable() {
					    public GradientDrawable getIns(int a, int b, int c, int d) {
						        this.setCornerRadius(a);
						        this.setStroke(b, c);
						        this.setColor(d);
						        return this;
						    }
				}.getIns(15, 3, 0x80141414, 0xFF212121));
				textview16.setTextColor(0xFFFFFFFF);
				imageview7.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_IN);
				linear6.setEnabled(true);
				edittext2.setEnabled(true);
				linear6.setAlpha((float)(1));
				edittext2.setAlpha((float)(1));
				textview2.setText("Type file name...");
				edittext1.setHint("please type the filename");
				type = "file";
			}
		});
		
		nfolder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				nfile.setBackground(new GradientDrawable() {
					    public GradientDrawable getIns(int a, int b, int c, int d) {
						        this.setCornerRadius(a);
						        this.setStroke(b, c);
						        this.setColor(d);
						        return this;
						    }
				}.getIns(15, 3, 0x80141414, 0xFF212121));
				textview14.setTextColor(0xFFFFFFFF);
				imageview6.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_IN);
				nfolder.setBackground(new GradientDrawable() {
					    public GradientDrawable getIns(int a, int b, int c, int d) {
						        this.setCornerRadius(a);
						        this.setStroke(b, c);
						        this.setColor(d);
						        return this;
						    }
				}.getIns(15, 3, 0xFFFF0000, 0xFF3F1616));
				textview16.setTextColor(0xFFFF0000);
				imageview7.setColorFilter(0xFFFF0000, PorterDuff.Mode.SRC_IN);
				linear6.setEnabled(false);
				edittext2.setEnabled(false);
				linear6.setAlpha((float)(0.3d));
				edittext2.setAlpha((float)(0.3d));
				textview2.setText("Type folder name...");
				edittext1.setHint("please type the foldername");
				type = "folder";
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (type.equals("file")) {
					int dotIndex = _charSeq.toString().indexOf(".");
					if (dotIndex != -1) {
						String textAfterDot = _charSeq.toString().substring(dotIndex + 1);
						edittext2.setText(textAfterDot);
						edittext1.setSelection(edittext1.getText().length());
						edittext1.setText(_charSeq.toString().substring(0, dotIndex));
						edittext2.requestFocus();
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
		
		hidden.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				h = _isChecked;
				if (_isChecked) {
					hiddenDot.setVisibility(View.VISIBLE);
				}
				else {
					hiddenDot.setVisibility(View.GONE);
				}
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		create.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (type.equals("file")) {
					if (edittext1.getText().toString().equals("")) {
						((EditText)edittext1).setError("The file name cannot be blank!");
					}
					else {
						i.putExtra("title", edittext1.getText().toString());
						i.putExtra("path", path);
						i.putExtra("type", edittext2.getText().toString());
						i.putExtra("hidden", h ? "true" : "false");
						i.putExtra("filescope", "none");
						i.setClass(getApplicationContext(), EditorActivity.class);
						startActivity(i);
						finish();
						overridePendingTransition(0, R.anim.slide_to_top);
					}
				}
				else {
					if (edittext1.getText().toString().equals("")) {
						((EditText)edittext1).setError("The folder name cannot be blank!");
					}
					else {
						if (h) {
							FileUtil.makeDir(path.concat("/.".concat(edittext1.getText().toString())));
						}
						else {
							FileUtil.makeDir(path.concat("/".concat(edittext1.getText().toString())));
						}
						_AppDesignerToast("Folder created successfully.");
						finish();
					}
				}
			}
		});
	}
	
	private void initializeLogic() {
		Window window = getWindow();
		window.setStatusBarColor(Color.TRANSPARENT);
		window.getDecorView().setSystemUiVisibility(
		View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		
		
		hiddenDot.setVisibility(View.GONE);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, cancel);
		_gradDrawable("#00000000", "#474747", 1, 15, 3, true, create);
		h = false;
		path = getIntent().getStringExtra("path");
		type = "file";
		nfile.setBackground(new GradientDrawable() {
			    public GradientDrawable getIns(int a, int b, int c, int d) {
				        this.setCornerRadius(a);
				        this.setStroke(b, c);
				        this.setColor(d);
				        return this;
				    }
		}.getIns(15, 3, 0xFFFF0000, 0xFF3F1616));
		textview14.setTextColor(0xFFFF0000);
		imageview6.setColorFilter(0xFFFF0000, PorterDuff.Mode.SRC_IN);
		nfolder.setBackground(new GradientDrawable() {
			    public GradientDrawable getIns(int a, int b, int c, int d) {
				        this.setCornerRadius(a);
				        this.setStroke(b, c);
				        this.setColor(d);
				        return this;
				    }
		}.getIns(15, 3, 0x80141414, 0xFF212121));
		textview16.setTextColor(0xFFFFFFFF);
		imageview7.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_IN);
		_ripple(adv1);
		_tablayout();
		if (path == null || path.equals("")) {
			nfolder.setEnabled(false);
			nfolder.setAlpha((float)(0.3d));
			path = "/storage/emulated/0/CEditor";
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
	}
	
	public void _convertToBottomSheet() {
	}
	
	
	private androidx.coordinatorlayout.widget.CoordinatorLayout mCoordinatorLayout;
	
	@Override
	public void finish(){
			com.google.android.material.bottomsheet.BottomSheetBehavior.from(mCoordinatorLayout.getChildAt(0)).setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED);
	}
	
	
	private void superFinish(){
			super.finish();
	}
	
	
	@Override
	public void setContentView(int layId){
			if(mCoordinatorLayout == null){
					overridePendingTransition(0,0);
					mCoordinatorLayout = new androidx.coordinatorlayout.widget.CoordinatorLayout(this);
					// DEPRECATED: makeActivityTransparent();
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
		/* @Deprecated in api 35!
}


private void makeActivityTransparent(){
getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(0));
	try {
		java.lang.reflect.Method getActivityOptions = Activity.class.getDeclaredMethod("getActivityOptions"); 
		getActivityOptions.setAccessible(true);
		Object options = getActivityOptions.invoke(this);
		Class<?>[] classes = Activity.class.getDeclaredClasses();
		Class<?> translucentConversionListenerClazz = null;
		for (Class clazz : classes) { 
			if (clazz.getSimpleName().contains("TranslucentConversionListener")) { 
				translucentConversionListenerClazz = clazz;
			} 
		} 
		java.lang.reflect.Method convertToTranslucent = Activity.class.getDeclaredMethod("convertToTranslucent", translucentConversionListenerClazz, ActivityOptions.class); 
		convertToTranslucent.setAccessible(true); 
		convertToTranslucent.invoke(this, null, options); 
	} catch (Throwable t) {
	}*/
	}
	
	
	public void _tablayout() {
		viewPager = new androidx.viewpager.widget.ViewPager(this);
		int pxValue = dpToPx(200);
		viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, pxValue));
		MyPagerAdapter adapter = new MyPagerAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
		b1.addView(viewPager);
	}
	
	
	private androidx.viewpager.widget.ViewPager viewPager;
	
	private class MyPagerAdapter extends androidx.viewpager.widget.PagerAdapter {
			public int getCount() {
					return 2;
					
			}
			
			@Override public Object instantiateItem(ViewGroup collection, int position) {
					LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View v = inflater.inflate(R.layout.test, null);
					LinearLayout container = (LinearLayout) v.findViewById(R.id.linear1);
					
					if (position == 0) {
							ViewGroup parent = (ViewGroup) main_page.getParent();
							if (parent != null) {
									parent.removeView(main_page);
							}container.addView(main_page);} 
					
					else if (position == 1) {
							ViewGroup parent = (ViewGroup) miscellaneous_page.getParent();
							if (parent != null) {
									parent.removeView(miscellaneous_page);
							}
							container.addView(miscellaneous_page);}
					
					collection.addView(v, 0);
					return v;
			}
			@Override public void destroyItem(ViewGroup collection, int position, Object view) {
					collection.removeView((View) view);
					b2.addView((View) view);
			}
			@Override public CharSequence getPageTitle(int position) {
					switch (position) {
							case 0:
							return "Main";
							case 1:
							return "Miscellaneous";
							default:
							return null;
					}
					
			}
			@Override public boolean isViewFromObject(View arg0, Object arg1) {
					return arg0 == ((View) arg1);}
			@Override public Parcelable saveState() {
					return null;
			}
	}
	
	
	private int pxToDp(int px) {
		return (int) (px / getResources().getDisplayMetrics().density);
	}
	
	
	private int dpToPx(int dp) {
		return (int) (dp * getResources().getDisplayMetrics().density);
	}
	
	
	public void _AppDesignerToast(final String _s) {
		// Code generated by App Designer
			DisplayMetrics screen = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(screen);
			double dp = 10;
			double logicalDensity = screen.density;
			int px = (int) Math.ceil(dp * logicalDensity);
			
			Toast customToast = Toast.makeText(NewFileActivity.this, _s, Toast.LENGTH_SHORT);
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
