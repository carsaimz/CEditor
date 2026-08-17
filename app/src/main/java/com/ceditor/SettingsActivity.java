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
import android.widget.*;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.android.material.appbar.AppBarLayout;
import com.robinhood.ticker.*;
import io.github.rosemoe.sora.*;
// import io.github.rosemoe.sora.langs.textmate.*; // Commented out due to missing language-textmate library
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.core.widget.NestedScrollView;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.MotionEvent;
import android.text.method.LinkMovementMethod;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.util.Linkify;
import java.io.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class SettingsActivity extends AppCompatActivity {
	
	private static final int SCRIM_COLOR = 0xFF000000;
	private static final int MAX_SCRIM_ALPHA = 80;
	private static final int SCRIM_R = Color.red(SCRIM_COLOR);
	private static final int SCRIM_G = Color.green(SCRIM_COLOR);
	private static final int SCRIM_B = Color.blue(SCRIM_COLOR);
	private float MIN_DISTANCE = 10;
	private float downX, downY;
	private boolean enableSwipe = false;
	private boolean lockSwipe = false;
	private int maxAlpha;
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String cl = "";
	
	private LinearLayout LinearLayout90;
	private NestedScrollView vscroll1;
	private LinearLayout linear1;
	private TextView textview11;
	private LinearLayout s_general_group;
	private TextView textview3;
	private LinearLayout s_filelist_group;
	private TextView textview1;
	private LinearLayout s_navpref_group;
	private TextView textview45;
	private LinearLayout s_editor_group;
	private TextView textview38;
	private LinearLayout s_permissions_group;
	private LinearLayout d3b0661n6_l1n34r6r0u9;
	private TextView textview35;
	private LinearLayout s_unstablefeatures_group;
	private TextView textview59;
	private LinearLayout s_privacygroup;
	private TextView textview10;
	private LinearLayout s_about_group;
	private TextView textview9;
	private LinearLayout s_dangerzone_group;
	private TextView textview_lang_header;
	private LinearLayout s_language_group;
	private LinearLayout linear_change_lang;
	private TextView textview_change_lang;
	private TextView textview_current_lang;
	private LinearLayout linear15ccv;
	private LinearLayout linear46;
	private LinearLayout linear47;
	private LinearLayout linear75;
	private LinearLayout linear15;
	private LinearLayout linear97;
	private LinearLayout ubdayp;
	private LinearLayout ubday;
	private ImageView imageview18;
	private Switch flushCacheSwitch;
	private ImageView imageview39;
	private LinearLayout LinearLayout73;
	private Switch collapsingTollbarSwich;
	private TextView TextView56;
	private ImageView imageview40;
	private Switch swipeBottomSwitch;
	private ImageView imageview69;
	private Switch swipeToRefreshLargeSwitch;
	private ImageView imageview68;
	private Switch fabSizeAutoSwitch;
	private ImageView imageview84;
	private LinearLayout linear104;
	private Switch switch4;
	private TextView textview64;
	private TextView textview65;
	private ImageView imageview85;
	private LinearLayout linear103;
	private Switch switch5;
	private TextView textview66;
	private ImageView imageview87;
	private LinearLayout linear102;
	private TextView textview62;
	private TextView textview61;
	private LinearLayout linear10;
	private LinearLayout linear37;
	private LinearLayout linear39;
	private LinearLayout linear40;
	private LinearLayout linear42;
	private LinearLayout linear43cvx;
	private ImageView imageview13;
	private Switch disableThumbnailSwitch;
	private ImageView imageview31;
	private Switch disableImgThumbnailSwitch;
	private ImageView imageview33;
	private Switch disableVidThumbnailSwitch;
	private ImageView imageview34;
	private Switch disableMscThumbnailSwitch;
	private ImageView imageview36;
	private Switch disablePdfThumbnailSwitch;
	private ImageView imageview37;
	private Switch disableApkThumbnailSwitch;
	private LinearLayout linear13ccv;
	private LinearLayout linear14cvx;
	private ImageView imageview16;
	private Switch autoRefreshSwitch;
	private ImageView imageview17;
	private Switch saveLastPathSwitch;
	private LinearLayout linear54;
	private LinearLayout linear56;
	private LinearLayout linear60;
	private LinearLayout linear61;
	private LinearLayout linear62;
	private LinearLayout linear63;
	private LinearLayout linear64;
	private LinearLayout linear73cvx;
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
	private ImageView imageview57;
	private Switch symbTabsSwitch;
	private ImageView imageview64;
	private TextView textview54;
	private LinearLayout linear48ccv;
	private ImageView imageview41;
	private Switch clipboardPermissionSwitch;
	private TextView textview56;
	private LinearLayout linear79;
	private LinearLayout linear84;
	private LinearLayout linear89;
	private ImageView imageview76;
	private TextView textview57;
	private ImageView imageview80;
	private TextView textview58;
	private LinearLayout linear44;
	private LinearLayout linear77;
	private ImageView imageview38;
	private LinearLayout linear45;
	private Switch allowWalkDirSwitch;
	private TextView textview37;
	private ImageView imageview71;
	private Switch d3b165w17c3r;
	private LinearLayout linear96;
	private ImageView imageview83;
	private LinearLayout LinearLayout98;
	private Switch switch3;
	private TextView TextView60;
	private LinearLayout linear18ccv;
	private LinearLayout linear19cvx;
	private ImageView imageview21;
	private TextView textview26;
	private ImageView imageview22;
	private TextView textview27;
	private LinearLayout linear76;
	private LinearLayout linear20;
	private ImageView imageview70;
	private TextView textview55;
	private ImageView imageview23;
	private TextView textview28;
	
	private Intent intent = new Intent();
	private SharedPreferences data;
	private Intent intent_activity = new Intent();
	private AlertDialog.Builder warn;
	private SharedPreferences response;
	private AlertDialog.Builder info;
	private AlertDialog.Builder about;
	
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

		setContentView(R.layout.settings);
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
		LinearLayout90 = findViewById(R.id.LinearLayout90);
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		textview11 = findViewById(R.id.textview11);
		s_general_group = findViewById(R.id.s_general_group);
		textview3 = findViewById(R.id.textview3);
		s_filelist_group = findViewById(R.id.s_filelist_group);
		textview1 = findViewById(R.id.textview1);
		s_navpref_group = findViewById(R.id.s_navpref_group);
		textview45 = findViewById(R.id.textview45);
		s_editor_group = findViewById(R.id.s_editor_group);
		textview38 = findViewById(R.id.textview38);
		s_permissions_group = findViewById(R.id.s_permissions_group);
		d3b0661n6_l1n34r6r0u9 = findViewById(R.id.d3b0661n6_l1n34r6r0u9);
		textview35 = findViewById(R.id.textview35);
		s_unstablefeatures_group = findViewById(R.id.s_unstablefeatures_group);
		textview59 = findViewById(R.id.textview59);
		s_privacygroup = findViewById(R.id.s_privacygroup);
		textview10 = findViewById(R.id.textview10);
		s_about_group = findViewById(R.id.s_about_group);
		textview9 = findViewById(R.id.textview9);
			s_dangerzone_group = findViewById(R.id.s_dangerzone_group);
			textview_lang_header = findViewById(R.id.textview_lang_header);
			s_language_group = findViewById(R.id.s_language_group);
			linear_change_lang = findViewById(R.id.linear_change_lang);
			textview_change_lang = findViewById(R.id.textview_change_lang);
			textview_current_lang = findViewById(R.id.textview_current_lang);
			linear15ccv = findViewById(R.id.linear15ccv);
		linear46 = findViewById(R.id.linear46);
		linear47 = findViewById(R.id.linear47);
		linear75 = findViewById(R.id.linear75);
		linear15 = findViewById(R.id.linear15);
		linear97 = findViewById(R.id.linear97);
		ubdayp = findViewById(R.id.ubdayp);
		ubday = findViewById(R.id.ubday);
		imageview18 = findViewById(R.id.imageview18);
		flushCacheSwitch = findViewById(R.id.flushCacheSwitch);
		imageview39 = findViewById(R.id.imageview39);
		LinearLayout73 = findViewById(R.id.LinearLayout73);
		collapsingTollbarSwich = findViewById(R.id.collapsingTollbarSwich);
		TextView56 = findViewById(R.id.TextView56);
		imageview40 = findViewById(R.id.imageview40);
		swipeBottomSwitch = findViewById(R.id.swipeBottomSwitch);
		imageview69 = findViewById(R.id.imageview69);
		swipeToRefreshLargeSwitch = findViewById(R.id.swipeToRefreshLargeSwitch);
		imageview68 = findViewById(R.id.imageview68);
		fabSizeAutoSwitch = findViewById(R.id.fabSizeAutoSwitch);
		imageview84 = findViewById(R.id.imageview84);
		linear104 = findViewById(R.id.linear104);
		switch4 = findViewById(R.id.switch4);
		textview64 = findViewById(R.id.textview64);
		textview65 = findViewById(R.id.textview65);
		imageview85 = findViewById(R.id.imageview85);
		linear103 = findViewById(R.id.linear103);
		switch5 = findViewById(R.id.switch5);
		textview66 = findViewById(R.id.textview66);
		imageview87 = findViewById(R.id.imageview87);
		linear102 = findViewById(R.id.linear102);
		textview62 = findViewById(R.id.textview62);
		textview61 = findViewById(R.id.textview61);
		linear10 = findViewById(R.id.linear10);
		linear37 = findViewById(R.id.linear37);
		linear39 = findViewById(R.id.linear39);
		linear40 = findViewById(R.id.linear40);
		linear42 = findViewById(R.id.linear42);
		linear43cvx = findViewById(R.id.linear43cvx);
		imageview13 = findViewById(R.id.imageview13);
		disableThumbnailSwitch = findViewById(R.id.disableThumbnailSwitch);
		imageview31 = findViewById(R.id.imageview31);
		disableImgThumbnailSwitch = findViewById(R.id.disableImgThumbnailSwitch);
		imageview33 = findViewById(R.id.imageview33);
		disableVidThumbnailSwitch = findViewById(R.id.disableVidThumbnailSwitch);
		imageview34 = findViewById(R.id.imageview34);
		disableMscThumbnailSwitch = findViewById(R.id.disableMscThumbnailSwitch);
		imageview36 = findViewById(R.id.imageview36);
		disablePdfThumbnailSwitch = findViewById(R.id.disablePdfThumbnailSwitch);
		imageview37 = findViewById(R.id.imageview37);
		disableApkThumbnailSwitch = findViewById(R.id.disableApkThumbnailSwitch);
		linear13ccv = findViewById(R.id.linear13ccv);
		linear14cvx = findViewById(R.id.linear14cvx);
		imageview16 = findViewById(R.id.imageview16);
		autoRefreshSwitch = findViewById(R.id.autoRefreshSwitch);
		imageview17 = findViewById(R.id.imageview17);
		saveLastPathSwitch = findViewById(R.id.saveLastPathSwitch);
		linear54 = findViewById(R.id.linear54);
		linear56 = findViewById(R.id.linear56);
		linear60 = findViewById(R.id.linear60);
		linear61 = findViewById(R.id.linear61);
		linear62 = findViewById(R.id.linear62);
		linear63 = findViewById(R.id.linear63);
		linear64 = findViewById(R.id.linear64);
		linear73cvx = findViewById(R.id.linear73cvx);
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
		imageview57 = findViewById(R.id.imageview57);
		symbTabsSwitch = findViewById(R.id.symbTabsSwitch);
		imageview64 = findViewById(R.id.imageview64);
		textview54 = findViewById(R.id.textview54);
		linear48ccv = findViewById(R.id.linear48ccv);
		imageview41 = findViewById(R.id.imageview41);
		clipboardPermissionSwitch = findViewById(R.id.clipboardPermissionSwitch);
		textview56 = findViewById(R.id.textview56);
		linear79 = findViewById(R.id.linear79);
		linear84 = findViewById(R.id.linear84);
		linear89 = findViewById(R.id.linear89);
		imageview76 = findViewById(R.id.imageview76);
		textview57 = findViewById(R.id.textview57);
		imageview80 = findViewById(R.id.imageview80);
		textview58 = findViewById(R.id.textview58);
		linear44 = findViewById(R.id.linear44);
		linear77 = findViewById(R.id.linear77);
		imageview38 = findViewById(R.id.imageview38);
		linear45 = findViewById(R.id.linear45);
		allowWalkDirSwitch = findViewById(R.id.allowWalkDirSwitch);
		textview37 = findViewById(R.id.textview37);
		imageview71 = findViewById(R.id.imageview71);
		d3b165w17c3r = findViewById(R.id.d3b165w17c3r);
		linear96 = findViewById(R.id.linear96);
		imageview83 = findViewById(R.id.imageview83);
		LinearLayout98 = findViewById(R.id.LinearLayout98);
		switch3 = findViewById(R.id.switch3);
		TextView60 = findViewById(R.id.TextView60);
		linear18ccv = findViewById(R.id.linear18ccv);
		linear19cvx = findViewById(R.id.linear19cvx);
		imageview21 = findViewById(R.id.imageview21);
		textview26 = findViewById(R.id.textview26);
		imageview22 = findViewById(R.id.imageview22);
		textview27 = findViewById(R.id.textview27);
		linear76 = findViewById(R.id.linear76);
		linear20 = findViewById(R.id.linear20);
		imageview70 = findViewById(R.id.imageview70);
		textview55 = findViewById(R.id.textview55);
		imageview23 = findViewById(R.id.imageview23);
		textview28 = findViewById(R.id.textview28);
		data = getSharedPreferences("data", Activity.MODE_PRIVATE);
		warn = new AlertDialog.Builder(this);
		response = getSharedPreferences("response", Activity.MODE_PRIVATE);
		info = new AlertDialog.Builder(this);
		about = new AlertDialog.Builder(this);
		
		linear15ccv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				flushCacheSwitch.performClick();
			}
		});
		
		linear46.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				collapsingTollbarSwich.performClick();
			}
		});
		
		linear47.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				swipeBottomSwitch.performClick();
			}
		});
		
		linear75.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				swipeToRefreshLargeSwitch.performClick();
			}
		});
		
		linear15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				fabSizeAutoSwitch.performClick();
			}
		});
		
		linear97.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		ubday.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				androidx.appcompat.app.AppCompatDialogFragment newFragment = new DatePickerFragment();
				newFragment.show(getSupportFragmentManager(), "Date Picker");
			}
		});
		
		flushCacheSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("flush_cache", "true").commit();
				}
				else {
					data.edit().putString("flush_cache", "false").commit();
				}
			}
		});
		
		collapsingTollbarSwich.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				TextView56.setVisibility(View.VISIBLE);
			}
		});
		
		collapsingTollbarSwich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("collapsing_toolbar", "true").commit();
				}
				else {
					data.edit().putString("collapsing_toolbar", "false").commit();
				}
			}
		});
		
		swipeBottomSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("swipe_bottom", "true").commit();
				}
				else {
					data.edit().putString("swipe_bottom", "false").commit();
				}
			}
		});
		
		swipeToRefreshLargeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("swipe2refresh_large", "true").commit();
				}
				else {
					data.edit().putString("swipe2refresh_large", "false").commit();
				}
			}
		});
		
		fabSizeAutoSwitch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		fabSizeAutoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("fab_size_auto", "true").commit();
				}
				else {
					data.edit().putString("fab_size_auto", "false").commit();
				}
			}
		});
		
		switch4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				textview65.setVisibility(View.VISIBLE);
			}
		});
		
		switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("falling_effect", "true").commit();
				}
				else {
					data.edit().putString("falling_effect", "false").commit();
				}
			}
		});
		
		switch5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				textview66.setVisibility(View.VISIBLE);
			}
		});
		
		switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("falling_effect_bday", "true").commit();
					ubday.setVisibility(View.VISIBLE);
					wrapContentLayoutParams(ubday);
				}
				else {
					data.edit().putString("falling_effect_bday", "false").commit();
					ubday.setVisibility(View.GONE);
				}
			}
		});
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				disableThumbnailSwitch.performClick();
			}
		});
		
		linear37.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				disableImgThumbnailSwitch.performClick();
			}
		});
		
		linear39.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				disableVidThumbnailSwitch.performClick();
			}
		});
		
		linear40.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				disableMscThumbnailSwitch.performClick();
			}
		});
		
		linear42.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				disablePdfThumbnailSwitch.performClick();
			}
		});
		
		linear43cvx.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				disableApkThumbnailSwitch.performClick();
			}
		});
		
		disableThumbnailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("disable_thumbnail", "true").commit();
					disableImgThumbnailSwitch.setChecked(true); disableVidThumbnailSwitch.setChecked(true); disableMscThumbnailSwitch.setChecked(true); disablePdfThumbnailSwitch.setChecked(true); disableApkThumbnailSwitch.setChecked(true);
					disableImgThumbnailSwitch.setAlpha((float)(0.6d)); disableVidThumbnailSwitch.setAlpha((float)(0.6d)); disableMscThumbnailSwitch.setAlpha((float)(0.6d)); disablePdfThumbnailSwitch.setAlpha((float)(0.6d)); disableApkThumbnailSwitch.setAlpha((float)(0.6d));
					disableImgThumbnailSwitch.setEnabled(false); disableVidThumbnailSwitch.setEnabled(false); disableMscThumbnailSwitch.setEnabled(false); disableApkThumbnailSwitch.setEnabled(false); disablePdfThumbnailSwitch.setEnabled(false);
					data.edit().putString("disable_thumbnail_image", "true").commit();
					data.edit().putString("disable_thumbnail_video", "true").commit();
					data.edit().putString("disable_thumbnail_audio", "true").commit();
					data.edit().putString("disable_thumbnail_pdf", "true").commit();
					data.edit().putString("disable_thumbnail_apk", "true").commit();
				}
				else {
					data.edit().putString("disable_thumbnail", "false").commit();
					disableImgThumbnailSwitch.setChecked(false); disableVidThumbnailSwitch.setChecked(false); disableMscThumbnailSwitch.setChecked(false); disablePdfThumbnailSwitch.setChecked(false); disableApkThumbnailSwitch.setChecked(false); 
					disableImgThumbnailSwitch.setAlpha((float)(1)); disableVidThumbnailSwitch.setAlpha((float)(1)); disableMscThumbnailSwitch.setAlpha((float)(1)); disablePdfThumbnailSwitch.setAlpha((float)(1)); disableApkThumbnailSwitch.setAlpha((float)(1)); 
					disableImgThumbnailSwitch.setEnabled(true); disableVidThumbnailSwitch.setEnabled(true); disableMscThumbnailSwitch.setEnabled(true); disablePdfThumbnailSwitch.setEnabled(true); disableApkThumbnailSwitch.setEnabled(true);
					data.edit().putString("disable_thumbnail_image", "false").commit();
					data.edit().putString("disable_thumbnail_video", "false").commit();
					data.edit().putString("disable_thumbnail_audio", "false").commit();
					data.edit().putString("disable_thumbnail_pdf", "false").commit();
					data.edit().putString("disable_thumbnail_apk", "false").commit();
				}
			}
		});
		
		disableImgThumbnailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("disable_thumbnail_image", "true").commit();
				}
				else {
					data.edit().putString("disable_thumbnail_image", "false").commit();
				}
			}
		});
		
		disableVidThumbnailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("disable_thumbnail_video", "true").commit();
				}
				else {
					data.edit().putString("disable_thumbnail_video", "false").commit();
				}
			}
		});
		
		disableMscThumbnailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("disable_thumbnail_music", "true").commit();
				}
				else {
					data.edit().putString("disable_thumbnail_music", "false").commit();
				}
			}
		});
		
		disablePdfThumbnailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("disable_thumbnail_pdf", "true").commit();
				}
				else {
					data.edit().putString("disable_thumbnail_pdf", "false").commit();
				}
			}
		});
		
		disableApkThumbnailSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("disable_thumbnail_apk", "true").commit();
				}
				else {
					data.edit().putString("disable_thumbnail_apk", "false").commit();
				}
			}
		});
		
		linear13ccv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				autoRefreshSwitch.performClick();
			}
		});
		
		linear14cvx.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				saveLastPathSwitch.performClick();
			}
		});
		
		autoRefreshSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
		
			saveLastPathSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

			linear_change_lang.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					final String[] langs = {"English", "Português"};
					AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
					builder.setTitle(R.string.change_language);
					builder.setItems(langs, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							String langCode = (which == 0) ? "en" : "pt";
							data.edit().putString("app_lang", langCode).commit();
							
							Locale locale = new Locale(langCode);
							Locale.setDefault(locale);
							Resources res = getResources();
							Configuration config = new Configuration(res.getConfiguration());
							config.setLocale(locale);
							res.updateConfiguration(config, res.getDisplayMetrics());
							
							recreate();
						}
					});
					_CXYZ_dialog_theme(builder);
				}
			});
		
		linear54.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				wrapTextSwitch.performClick();
			}
		});
		
		linear56.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				numberDividerSwitch.performClick();
			}
		});
		
		linear60.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ligatureSwitch.performClick();
			}
		});
		
		linear61.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				highlightBracketPairSwitch.performClick();
			}
		});
		
		linear62.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				fastScrollSwitch.performClick();
			}
		});
		
		linear63.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pinNumberDividerSwitch.performClick();
			}
		});
		
		linear64.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				symbTabsSwitch.performClick();
			}
		});
		
		linear73cvx.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SpannableString message = new SpannableString(
				    "Sora Editor (v0.14.0) is an advanced Android View library for code editing. It features instant syntax highlighting, real-time auto-completion, and a smooth, pluggable architecture ideal for building custom code editors on Android.\n\n" +
				    "Designed for performance and extensibility, it supports multiple programming languages and offers a flexible API for deeper integration.\n\n" +
				    "Documentation: https://project-sora.github.io/sora-editor-docs/guide/editor-overview\n" +
				    "Library GitHub: https://github.com/Rosemoe/sora-editor\n\n" +
				    "License: LGPL-2.1"
				);
				Linkify.addLinks(message, Linkify.WEB_URLS);
				info.setMessage(message);
				info.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				_CXYZ_dialog_theme(info);
			}
		});
		
		wrapTextSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("wrap_text", "true").commit();
				}
				else {
					data.edit().putString("wrap_text", "false").commit();
				}
			}
		});
		
		numberDividerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("number_divider", "true").commit();
				}
				else {
					data.edit().putString("number_divider", "false").commit();
				}
			}
		});
		
		ligatureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("ligature", "true").commit();
				}
				else {
					data.edit().putString("ligature", "false").commit();
				}
			}
		});
		
		highlightBracketPairSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("highlight_bracket_pair", "true").commit();
				}
				else {
					data.edit().putString("highlight_bracket_pair", "false").commit();
				}
			}
		});
		
		fastScrollSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("fast_scroll", "true").commit();
				}
				else {
					data.edit().putString("fast_scroll", "false").commit();
				}
			}
		});
		
		pinNumberDividerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("pin_number_divider", "true").commit();
				}
				else {
					data.edit().putString("pin_number_divider", "false").commit();
				}
			}
		});
		
		symbTabsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("symbol_tabs", "true").commit();
				}
				else {
					data.edit().putString("symbol_tabs", "false").commit();
				}
			}
		});
		
		linear48ccv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				clipboardPermissionSwitch.performClick();
			}
		});
		
		clipboardPermissionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("clipboard", "true").commit();
				}
				else {
					data.edit().putString("clipboard", "false").commit();
				}
			}
		});
		
		linear84.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				response.edit().putString("test", "true").commit();
				android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
				
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
			}
		});
		
		linear89.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				d3b0661n6_l1n34r6r0u9.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 
				0
				));
				d3b0661n6_l1n34r6r0u9.setVisibility(View.GONE);
			}
		});
		
		linear44.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				allowWalkDirSwitch.performClick();
			}
		});
		
		allowWalkDirSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("allow_walk_dir", "true").commit();
				}
				else {
					data.edit().putString("allow_walk_dir", "false").commit();
				}
			}
		});
		
		d3b165w17c3r.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					data.edit().putString("debug", "true").commit();
					d3b0661n6_l1n34r6r0u9.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, 
					LinearLayout.LayoutParams.WRAP_CONTENT
					));
					d3b0661n6_l1n34r6r0u9.setVisibility(View.VISIBLE);
				}
				else {
					data.edit().putString("debug", "false").commit();
					d3b0661n6_l1n34r6r0u9.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, 
					0
					));
					d3b0661n6_l1n34r6r0u9.setVisibility(View.GONE);
					linear77.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, 
					0
					));
					linear77.setVisibility(View.GONE);
				}
			}
		});
		
		linear96.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				switch3.performClick();
			}
		});
		
		switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					response.edit().putString("iso", "true").commit();
				}
				else {
					response.edit().putString("iso", "false").commit();
				}
			}
		});
		
		linear18ccv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					PackageManager pm = getPackageManager();
					PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
					ApplicationInfo ai = getApplicationInfo();
					String appName = getString(R.string.app_name);
					String versionName = pi.versionName;
					String packageName = getPackageName();
					int versionCode = pi.versionCode;
					long installTime = pi.firstInstallTime;
					String installDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date(installTime));
					long appSizeBytes = new File(ai.sourceDir).length();
					String appSize = String.format(Locale.getDefault(), "%.2f MB", appSizeBytes / (1024.0 * 1024.0));
					
					about.setTitle(" ");
					about.setIcon(R.drawable.app_logo_transparent);
					
					SpannableStringBuilder aboutText = new SpannableStringBuilder();
					int start = aboutText.length();
					aboutText.append(appName + " " + versionName + "\n");
					aboutText.setSpan(new StyleSpan(Typeface.BOLD), start, aboutText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					aboutText.setSpan(new RelativeSizeSpan(1.2f), start, aboutText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						aboutText.append(getString(R.string.package_str) + packageName + "\n");
						aboutText.append(getString(R.string.version_code_str) + versionCode + "\n");
						aboutText.append(getString(R.string.installed_str) + installDate + "\n");
						aboutText.append(getString(R.string.app_size_str) + appSize + "\n");
						aboutText.append(getString(R.string.update_duration_str) + "\n");
						start = aboutText.length();
						aboutText.append(getString(R.string.developer_str) + "\n");
						aboutText.setSpan(new StyleSpan(Typeface.ITALIC), start, aboutText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						aboutText.append(getString(R.string.license_str) + "\n\n");
						start = aboutText.length();
						aboutText.append(getString(R.string.copyright_str));
					aboutText.setSpan(new ForegroundColorSpan(Color.GRAY), start, aboutText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					aboutText.setSpan(new StyleSpan(Typeface.BOLD), start, aboutText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					about.setMessage(aboutText);
					about.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					_CXYZ_dialog_theme(about);
				} catch (PackageManager.NameNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
		linear19cvx.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent_activity.setClass(getApplicationContext(), AcknowledgementActivity.class);
				startActivity(intent_activity);
			}
		});
		
		linear76.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
					warn.setTitle(R.string.confirmation);
					warn.setMessage(R.string.clear_data_msg);
					warn.setPositiveButton(R.string.clear_app_data, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						clearData();
					}
				});
					warn.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				_CXYZ_dialog_theme(warn);
			}
		});
		
		linear20.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				String packageName = "com.ceditor";
				try {
					    Intent intent = new Intent(Intent.ACTION_DELETE);
					    intent.setData(Uri.parse("package:" + packageName));
					    startActivity(intent);
				} catch (ActivityNotFoundException e) {
				}
			}
		});
	}
	
	private void initializeLogic() {
		
		setTitle("Settings");
		Window window = getWindow();
		window.setStatusBarColor(Color.TRANSPARENT);
		_toolbar.setBackgroundColor(Color.parseColor("#111111"));
		
		_initSlideActivity();
		SharedPreferences prefs = getSharedPreferences("falling_prefs", MODE_PRIVATE);
		String birthDate = prefs.getString("birth_date", "01/01/2000");
		if (birthDate == null || birthDate.trim().isEmpty()) {
			    birthDate = "01/01/2000";
		}
		textview61.setText(birthDate);
		flushCacheSwitch.setChecked(!data.getString("flush_cache", "").equals("") && data.getString("flush_cache", "").equals("true"));
		disableThumbnailSwitch.setChecked(!data.getString("disable_thumbnail", "").equals("") && data.getString("disable_thumbnail", "").equals("true"));
		disableImgThumbnailSwitch.setChecked(!data.getString("disable_thumbnail_image", "").equals("") && data.getString("disable_thumbnail_image", "").equals("true"));
		disableVidThumbnailSwitch.setChecked(!data.getString("disable_thumbnail_video", "").equals("") && data.getString("disable_thumbnail_video", "").equals("true"));
		disableMscThumbnailSwitch.setChecked(!data.getString("disable_thumbnail_audio", "").equals("") && data.getString("disable_thumbnail_audio", "").equals("true"));
		disablePdfThumbnailSwitch.setChecked(!data.getString("disable_thumbnail_pdf", "").equals("") && data.getString("disable_thumbnail_pdf", "").equals("true"));
		disableApkThumbnailSwitch.setChecked(!data.getString("disable_thumbnail_apk", "").equals("") && data.getString("disable_thumbnail_apk", "").equals("true"));
		allowWalkDirSwitch.setChecked(!data.getString("allow_walk_dir", "").equals("") && data.getString("allow_walk_dir", "").equals("true"));
		saveLastPathSwitch.setChecked(!data.getString("save_path", "").equals("") && data.getString("save_path", "").equals("true"));
		autoRefreshSwitch.setChecked(!data.getString("auto_refresh", "").equals("") && data.getString("auto_refresh", "").equals("true"));
		collapsingTollbarSwich.setChecked(!data.getString("collapsing_toolbar", "").equals("") && data.getString("collapsing_toolbar", "").equals("true"));
		swipeBottomSwitch.setChecked(!data.getString("swipe_bottom", "").equals("") && data.getString("swipe_bottom", "").equals("true"));
		wrapTextSwitch.setChecked(!data.getString("wrap_text", "").equals("") && data.getString("wrap_text", "").equals("true"));
		numberDividerSwitch.setChecked(!data.getString("number_divider", "").equals("") && data.getString("number_divider", "").equals("true"));
		ligatureSwitch.setChecked(!data.getString("ligature", "").equals("") && data.getString("ligature", "").equals("true"));
		highlightBracketPairSwitch.setChecked(!data.getString("highlight_bracket_pair", "").equals("") && data.getString("highlight_bracket_pair", "").equals("true"));
		fastScrollSwitch.setChecked(!data.getString("fast_scroll", "").equals("") && data.getString("fast_scroll", "").equals("true"));
		pinNumberDividerSwitch.setChecked(!data.getString("pin_number_divider", "").equals("") && data.getString("pin_number_divider", "").equals("true"));
		symbTabsSwitch.setChecked(!data.getString("symbol_tabs", "").equals("") && data.getString("symbol_tabs", "").equals("true"));
		clipboardPermissionSwitch.setChecked(!data.getString("clipboard", "").equals("") && data.getString("clipboard", "").equals("true"));
		swipeToRefreshLargeSwitch.setChecked(!data.getString("swipe2refresh_large", "").equals("") && data.getString("swipe2refresh_large", "").equals("true"));
		fabSizeAutoSwitch.setChecked(!data.getString("fab_size_auto", "").equals("") && data.getString("fab_size_auto", "").equals("true"));
		switch3.setChecked(!response.getString("iso", "").equals("") && response.getString("iso", "").equals("true"));
		switch4.setChecked(!data.getString("falling_effect", "").equals("") && data.getString("falling_effect", "").equals("true"));
		switch5.setChecked(!data.getString("falling_effect_bday", "").equals("") && data.getString("falling_effect_bday", "").equals("true"));
		textview10.setOnTouchListener(new TwoFingerLongPressListener());
		_collapsingToolbar(vscroll1);
		cl = "#212121";
		_CRadius(s_general_group, cl);
		_CRadius(s_filelist_group, cl);
		_CRadius(s_navpref_group, cl);
		_CRadius(s_editor_group, cl);
		_CRadius(s_permissions_group, cl);
		_CRadius(s_unstablefeatures_group, cl);
		_CRadius(linear79, cl);
		_CRadius(s_about_group, cl);
			_CRadius(s_privacygroup, cl);
			_CRadius(s_language_group, cl);
			_Concave(s_dangerzone_group, cl);
			
			String currentLang = data.getString("app_lang", "en");
			textview_current_lang.setText(currentLang.equals("pt") ? "Português" : "English");
		}
	
	
	public static class DatePickerFragment extends androidx.appcompat.app.AppCompatDialogFragment implements DatePickerDialog.OnDateSetListener {
			
			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
					final Calendar c = Calendar.getInstance();
					int year = c.get(Calendar.YEAR);
					int month = c.get(Calendar.MONTH);
					int day = c.get(Calendar.DAY_OF_MONTH);
					
					DatePickerDialog datePickerDialog = new DatePickerDialog(
					getActivity(),
					AlertDialog.THEME_DEVICE_DEFAULT_DARK,
					this,
					year,
					month,
					day
					);
					
					Calendar today = Calendar.getInstance();
					datePickerDialog.getDatePicker().setMaxDate(today.getTimeInMillis());
					
					return datePickerDialog;
			}
			
			
			@Override
			public void onDateSet(DatePicker view, int year, int month, int day) {
					int mon = month + 1;
					String date = String.format(Locale.getDefault(), "%02d/%02d/%04d", day, mon, year);
					String key = "bday" + date;
					
					SharedPreferences prefs = getActivity().getSharedPreferences("falling_prefs", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putInt("birth_day", day);
					editor.putInt("birth_month", mon);
					editor.putInt("birth_year", year);
			        editor.putString("birth_date", date);
					editor.apply();
			        
					TextView textview61 = getActivity().findViewById(R.id.textview61);
					textview61.setText(date);
			}
	}
	
	private void clearData() {
			try {
					if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
							((ActivityManager)getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData();
					} else {
							Runtime.getRuntime().exec("pm clear " + getApplicationContext().getPackageName());
					}
			} catch (Exception e) {
					e.printStackTrace();
			}
	}
	
	
	private void wrapContentLayoutParams (View _v) {
		    _v.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT
				));
	}
	
	
	public class TwoFingerLongPressListener implements View.OnTouchListener {
			private static final int LONG_PRESS_TIMEOUT = 3000;
			private Handler handler = new Handler();
			private int pointerCount = 0;
			
			private Runnable longPressRunnable = new Runnable() {
					@Override
					public void run() {
							if (pointerCount == 2) {
									onTwoFingerLongPress();
							}
					}
			};
		    
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
					switch (event.getActionMasked()) {
							case MotionEvent.ACTION_POINTER_DOWN:
							pointerCount = event.getPointerCount();
							if (pointerCount == 2) {
									handler.postDelayed(longPressRunnable, LONG_PRESS_TIMEOUT);
							}
							break;
							case MotionEvent.ACTION_MOVE:
							break;
							
							case MotionEvent.ACTION_POINTER_UP:
							case MotionEvent.ACTION_UP:
							case MotionEvent.ACTION_CANCEL:
							pointerCount = event.getPointerCount() - 1;
							handler.removeCallbacks(longPressRunnable);
							break;
					}
					return true;
			}
			
		    
			private void onTwoFingerLongPress() {
					linear77.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, 
					LinearLayout.LayoutParams.WRAP_CONTENT
					));
					linear77.setVisibility(View.VISIBLE);
			}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_initSlideActivity();
	}
	
	
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
	}
	public void _gradDrawable(final String _bgColor, final String _strokeColor, final double _stroke, final double _rad, final double _elv, final boolean _rpl, final View _view) {
		MainActivity.gradDrawable(getApplicationContext(), _bgColor, _strokeColor, _stroke, _rad, _elv, _rpl, _view);
	}
	
	
	public void _extras() {
	}
	public static boolean moveFile(String src, String dest){
		FileUtil.copyFile(src, dest);
		if (FileUtil.isExistFile(dest)) {
			FileUtil.deleteFile(src);
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public void _initSlideActivity() {
		getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
		_coordinator = findViewById(R.id._coordinator);
		maxAlpha = (int) ((225.0d / 100.0d) * MAX_SCRIM_ALPHA);
		ViewConfiguration vc = ViewConfiguration.get(this);
		MIN_DISTANCE = vc.getScaledTouchSlop();
	}
	
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		    switch (event.getAction()) {
			        case MotionEvent.ACTION_DOWN:
			            downX = event.getRawX();
			            downY = event.getRawY();
			            enableSwipe = false;
			            lockSwipe = false;
			            break;
			
			        case MotionEvent.ACTION_MOVE:
			            float deltaX = event.getRawX() - downX;
			            float deltaY = event.getRawY() - downY;
			            if (Math.abs(deltaX) > Math.abs(deltaY)) {
				                enableSwipe = true;
				                if (Math.abs(deltaX) > MIN_DISTANCE && !lockSwipe) {
					                    float translation = deltaX - MIN_DISTANCE;
					                    if (translation >= _coordinator.getWidth() || translation <= 0) {
						                        _coordinator.setTranslationX(0);
						                    } else {
						                        _coordinator.setTranslationX(translation);
						                        updateScrimColor(translation);
						                    }
					                }
				            } else {
				                enableSwipe = false;
				            }
			            break;
			
			        case MotionEvent.ACTION_UP:
			            if (enableSwipe && _coordinator.getTranslationX() > _coordinator.getWidth() / 5) {
				                _coordinator.animate()
				                        .translationX(_coordinator.getWidth())
				                        .setListener(new AnimatorListenerAdapter() {
					                            @Override
					                            public void onAnimationEnd(Animator animation) {
						                                super.onAnimationEnd(animation);
						                                finish();
						                                overridePendingTransition(0, 0);
						                            }
					                        });
				            } else {
				                _coordinator.animate()
				                        .translationX(0)
				                        .setListener(new AnimatorListenerAdapter() {
					                            @Override
					                            public void onAnimationEnd(Animator animation) {
						                                super.onAnimationEnd(animation);
						                            }
					                        });
				            }
			            break;
			
			        default:
			            enableSwipe = false;
			            lockSwipe = false;
			            break;
			    }
		
		    if (enableSwipe) {
			        event.setAction(MotionEvent.ACTION_CANCEL);
			    }
		
		    return super.dispatchTouchEvent(event);
	}
	
	
	private void updateScrimColor(float translation) {
		    int distanceInPercentage = (int) (((double) translation / (double) _coordinator.getWidth()) * 100);
		    int alpha = (int) (((double) maxAlpha / 100.0d) * distanceInPercentage);
		    alpha = maxAlpha - alpha;
		    int scrimColor = Color.argb(alpha, SCRIM_R, SCRIM_G, SCRIM_B);
		    getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(scrimColor));
	}
	
	
	private void animateSwipeOut() {
			_coordinator.animate()
			.translationX(_coordinator.getWidth())
			.setDuration(300)
			.withEndAction(() -> {
					finish();
					overridePendingTransition(0, 0);
			});
	}
	
	
	private void animateSwipeReset() {
			_coordinator.animate()
			.translationX(0)
			.setDuration(300)
			.withEndAction(() -> {
					getWindow().getDecorView().setBackgroundResource(android.R.color.background_dark);
			});
	}
	
	
	public void _Concave(final View _view, final String _color) {
		android.graphics.drawable.GradientDrawable Concave = new android.graphics.drawable.GradientDrawable();
		int d_ccv = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		Concave.setColor(Color.parseColor(_color));
		Concave.setCornerRadii(new float[]{d_ccv*30,d_ccv*30,d_ccv*30 ,d_ccv*30,d_ccv*0,d_ccv*0 ,d_ccv*0,d_ccv*0});
		Concave.setStroke((int) (1 * d_ccv), Color.parseColor("#474747"));
		_view.setBackground(Concave);
	}
	
	
	public void _Convex(final View _view, final String _color) {
		android.graphics.drawable.GradientDrawable Convex = new android.graphics.drawable.GradientDrawable();
		int d_cvx = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		Convex.setColor(Color.parseColor(_color));
		Convex.setCornerRadii(new float[]{d_cvx*0,d_cvx*0,d_cvx*0 ,d_cvx*0,d_cvx*30,d_cvx*30 ,d_cvx*30,d_cvx*30});
		Convex.setStroke((int) (1 * d_cvx), Color.parseColor("#474747"));
		_view.setBackground(Convex);
	}
	
	
	public void _CRadius(final View _view, final String _color) {
		android.graphics.drawable.GradientDrawable CRadius = new android.graphics.drawable.GradientDrawable();
		int dcr = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		CRadius.setColor(Color.parseColor(_color));
		CRadius.setCornerRadii(new float[]{dcr * 30, dcr * 30, dcr * 30, dcr * 30, dcr * 30, dcr * 30, dcr * 30, dcr * 30});
		CRadius.setStroke((int) (1 * dcr), Color.parseColor("#474747"));
		_view.setBackground(CRadius);
	}
	
	
	public void _collapsingToolbar(final View _scroll) {
		try {
				com.google.android.material.appbar.AppBarLayout.LayoutParams params = (com.google.android.material.appbar.AppBarLayout.LayoutParams)_toolbar.getLayoutParams();
				params.setScrollFlags(com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS | com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
				androidx.core.widget.NestedScrollView nestedScrollView = new androidx.core.widget.NestedScrollView(this);
				
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
				nestedScrollView.setLayoutParams(layoutParams);
				androidx.core.view.ViewCompat.setNestedScrollingEnabled(_scroll, true);
		} catch(Exception e) {
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
