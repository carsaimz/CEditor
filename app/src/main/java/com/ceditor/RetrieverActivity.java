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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.android.view.materialrefreshlayout.*;
import com.robinhood.ticker.*;
import com.theartofdev.edmodo.cropper.*;
import com.zolad.zoominimageview.*;
import io.github.rosemoe.sora.*;
import io.github.rosemoe.sora.langs.textmate.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import  android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.Html;
import android.util.DisplayMetrics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import android.content.BroadcastReceiver;
import android.os.Handler;
import java.util.Locale;
import android.text.Html;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;



public class RetrieverActivity extends AppCompatActivity {
	
	private Handler handler;
	private Runnable updateRunnable;
	private BroadcastReceiver batteryReceiver;
	
	private String formatRow(String label, String value) {
			return "<tr><td><b><font color='#B00020'>" + label + "</font></b></td><td><code>" + value + "</code></td></tr>";
	}
	
	
	private String formatSizeMBGB(long mb) {
			double gb = mb / 1024.0;
			return mb + " MB / " + String.format("%.2f", gb) + " GB";
	}
	
	
	private String formatVoltage(int mv) {
			double v = mv / 1000.0;
			return mv + " mV / " + String.format("%.2f", v) + " V";
	}
	
	
	private String formatTempCelsiusFahrenheit(double c) {
			double f = (c * 9 / 5) + 32;
			return String.format("%.1f°C / %.1f°F", c, f);
	}
	
	
	private String getCpuHardware() {
			try (BufferedReader br = new BufferedReader(new FileReader("/proc/cpuinfo"))) {
					String line;
					while ((line = br.readLine()) != null) {
							if (line.startsWith("Hardware") || line.startsWith("Processor")) {
									return line.split(":")[1].trim();
							}
					}
			} catch (IOException e) {}
			return "Unknown";
	}
	
	private boolean landscape = false;
	
	private FrameLayout LinearLayout1;
	private LinearLayout scrollviewcontainer;
	private LinearLayout toolbar;
	private ScrollView ScrollView1;
	private LinearLayout LinearLayout4;
	private TextView content;
	private LinearLayout device;
	private LinearLayout battery;
	private LinearLayout wifi;
	private TextView deviceInfo;
	private LinearLayout cbt;
	private TextView batteryHealth;
	private TextView batteryTech;
	private TextView batteryPlug;
	private TextView batteryCapacity;
	private LinearLayout LinearLayout3;
	private LinearLayout LinearLayout2;
	private TextView batteryProc;
	private ImageView chargingIcon;
	private LinearLayout LinearLayout5;
	private LinearLayout LinearLayout8;
	private TextView TextView1;
	private TextView celsius;
	private TextView fahrenheit;
	private ProgressBar batteryTemperature;
	private TextView TextView2;
	private TextView voltages;
	private ProgressBar batteryVoltage;
	private TextView wifiInfo;
	private LinearLayout LinearLayout7;
	private LinearLayout topline;
	private ImageView close;
	private LinearLayout LinearLayout6;
	private TextView title;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.retriever);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		LinearLayout1 = findViewById(R.id.LinearLayout1);
		scrollviewcontainer = findViewById(R.id.scrollviewcontainer);
		toolbar = findViewById(R.id.toolbar);
		ScrollView1 = findViewById(R.id.ScrollView1);
		LinearLayout4 = findViewById(R.id.LinearLayout4);
		content = findViewById(R.id.content);
		device = findViewById(R.id.device);
		battery = findViewById(R.id.battery);
		wifi = findViewById(R.id.wifi);
		deviceInfo = findViewById(R.id.deviceInfo);
		cbt = findViewById(R.id.cbt);
		batteryHealth = findViewById(R.id.batteryHealth);
		batteryTech = findViewById(R.id.batteryTech);
		batteryPlug = findViewById(R.id.batteryPlug);
		batteryCapacity = findViewById(R.id.batteryCapacity);
		LinearLayout3 = findViewById(R.id.LinearLayout3);
		LinearLayout2 = findViewById(R.id.LinearLayout2);
		batteryProc = findViewById(R.id.batteryProc);
		chargingIcon = findViewById(R.id.chargingIcon);
		LinearLayout5 = findViewById(R.id.LinearLayout5);
		LinearLayout8 = findViewById(R.id.LinearLayout8);
		TextView1 = findViewById(R.id.TextView1);
		celsius = findViewById(R.id.celsius);
		fahrenheit = findViewById(R.id.fahrenheit);
		batteryTemperature = findViewById(R.id.batteryTemperature);
		TextView2 = findViewById(R.id.TextView2);
		voltages = findViewById(R.id.voltages);
		batteryVoltage = findViewById(R.id.batteryVoltage);
		wifiInfo = findViewById(R.id.wifiInfo);
		LinearLayout7 = findViewById(R.id.LinearLayout7);
		topline = findViewById(R.id.topline);
		close = findViewById(R.id.close);
		LinearLayout6 = findViewById(R.id.LinearLayout6);
		title = findViewById(R.id.title);
		
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
	}
	
	private void initializeLogic() {
		
		LinearLayout7.setBackgroundColor(0xD9111111);
		if (getIntent().getStringExtra("retriever").equals("deviceinfo")) {
			StringBuilder html = new StringBuilder();
			
			html.append("<h2><font color='#B00020'>System Info</font></h2>");
			html.append("<b><font color='#FF4747 '>Manufacturer:</font></b> <code>" + Build.MANUFACTURER + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Brand:</font></b> <code>" + Build.BRAND + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Model:</font></b> <code>" + Build.MODEL + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Device:</font></b> <code>" + Build.DEVICE + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Product:</font></b> <code>" + Build.PRODUCT + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Android Version:</font></b> <code>" + Build.VERSION.RELEASE + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>API Level:</font></b> <code>" + Build.VERSION.SDK_INT + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Build ID:</font></b> <code>" + Build.ID + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Fingerprint:</font></b> <code>" + Build.FINGERPRINT + "</code><br/><br/><br/>");
			
			html.append("<h2><font color='#B00020'>CPU Info</font></h2>");
			html.append("<b><font color='#FF4747'>CPU ABI:</font></b> <code>" + Build.SUPPORTED_ABIS[0] + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Hardware:</font></b> <code>" + getCpuHardware() + "</code><br/><br/><br/>");
			
			DisplayMetrics metrics = getResources().getDisplayMetrics();
			int width = metrics.widthPixels;
			int height = metrics.heightPixels;
			float density = metrics.density;
			double xInches = width / metrics.xdpi;
			double yInches = height / metrics.ydpi;
			double diagonal = Math.sqrt(xInches * xInches + yInches * yInches);
			
			html.append("<h2><font color='#B00020'>Display</font></h2>");
			html.append("<b><font color='#FF4747'>Resolution:</font></b> <code>" + width + " x " + height + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Density:</font></b> <code>" + density + " (" + metrics.densityDpi + " dpi)</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Refresh Rate:</font></b> <code>" + String.format("%.2f Hz", getWindowManager().getDefaultDisplay().getRefreshRate()) + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Screen Size:</font></b> <code>" + String.format("%.2f inches", diagonal) + "</code><br/><br/><br/>");
			
			ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
			ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			am.getMemoryInfo(memInfo);
			long totalRam = memInfo.totalMem / (1024 * 1024);
			long availRam = memInfo.availMem / (1024 * 1024);
			
			html.append("<h2><font color='#B00020'>Memory</font></h2>");
			html.append("<b><font color='#FF4747'>Total RAM:</font></b> <code>" + formatSizeMBGB(totalRam) + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Available RAM:</font></b> <code>" + formatSizeMBGB(availRam) + "</code><br/><br/><br/>");
			
			StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
			long totalStorage = (stat.getBlockCountLong() * stat.getBlockSizeLong()) / (1024 * 1024);
			long availStorage = (stat.getAvailableBlocksLong() * stat.getBlockSizeLong()) / (1024 * 1024);
			
			html.append("<h2><font color='#B00020'>Storage</font></h2>");
			html.append("<b><font color='#FF4747'>Total Storage:</font></b> <code>" + formatSizeMBGB(totalStorage) + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Available Storage:</font></b> <code>" + formatSizeMBGB(availStorage) + "</code><br/><br/><br/>");
			
			Intent batteryStatus = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
			int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
			int voltage = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
			int temp = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
			double tempC = temp / 10.0;
			
			html.append("<h2><font color='#B00020'>Battery</font></h2>");
			html.append("<b><font color='#FF4747'>Level:</font></b> <code>" + level + "%</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Voltage:</font></b> <code>" + formatVoltage(voltage) + "</code><br/><br/>");
			html.append("<b><font color='#FF4747'>Temperature:</font></b> <code>" + formatTempCelsiusFahrenheit(tempC) + "</code><br/><br/>");
			
			deviceInfo.setText(Html.fromHtml(html.toString(), Html.FROM_HTML_MODE_LEGACY));
			deviceInfo.setTextIsSelectable(true);
			device.setVisibility(View.VISIBLE);
			wrapContentLayoutParams(device);
		}
		else {
			if (getIntent().getStringExtra("retriever").equals("battery")) {
				batteryHealth.setTextIsSelectable(true);
				batteryTech.setTextIsSelectable(true);
				batteryPlug.setTextIsSelectable(true);
				batteryCapacity.setTextIsSelectable(true);
				batteryReceiver = new BroadcastReceiver() {
						@Override
						public void onReceive(Context context, Intent intent) {
								updateBatteryData(intent);
						}
				};
				registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
				
				handler = new Handler();
				updateRunnable = new Runnable() {
						@Override
						public void run() {
								updateBatteryData(null);
								handler.postDelayed(this, 1000);
						}
				};
				handler.post(updateRunnable);
				battery.setVisibility(View.VISIBLE);
				wrapContentLayoutParams(battery);
				title.setText("Battery info");
			}
			else {
				if (getIntent().getStringExtra("retriever").equals("network")) {
					NetworkInfoProvider networkInfoProvider = new NetworkInfoProvider(this);
					updateNetworkInfo(networkInfoProvider);
					
					handler = new Handler();
					updateRunnable = new Runnable() {
							@Override
							public void run() {
									updateNetworkInfo(networkInfoProvider);
									handler.postDelayed(this, 1000);
							}
					};
					handler.post(updateRunnable);
					
					wifiInfo.setTextIsSelectable(true);
					wifi.setVisibility(View.VISIBLE);
					wrapContentLayoutParams(wifi);
					title.setText("Network info");
				}
			}
		}
		_ripple(close);
	}
	
	
	private void updateBatteryData(Intent batteryIntent) {
			Intent batteryStatus = batteryIntent != null
			? batteryIntent
			: registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
			
			if (batteryStatus != null) {
					int temp = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
					float tempC = temp / 10f;
					float tempF = (tempC * 9 / 5) + 32;
					int voltage = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
					
					if (temp > 0) {
							int tempColor = getTempColor(tempC);
							celsius.setText(String.format(Locale.US, "%.1f°C", tempC));
							fahrenheit.setText(String.format(Locale.US, "%.1f°F", tempF));
							batteryTemperature.setProgress((int) tempC);
							celsius.setTextColor(tempColor);
							fahrenheit.setTextColor(tempColor);
					}
					
					if (voltage > 0) {
							int voltColor = getVoltageColor(voltage);
							voltages.setText(String.format(Locale.US, "%d mV", voltage));
							batteryVoltage.setProgress(voltage);
							voltages.setTextColor(voltColor);
					}
			}
			
			int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);  
			int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);  
			int percent = (int) ((level / (float) scale) * 100);  
			batteryProc.setText(percent + "%");  
			
			int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);  
			boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||  
			status == BatteryManager.BATTERY_STATUS_FULL;  
			
			chargingIcon.setVisibility(isCharging ? View.VISIBLE : View.GONE);  
			
			int health = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);  
			String healthStr = "Unknown";  
			switch (health) {  
					case BatteryManager.BATTERY_HEALTH_GOOD: healthStr = "Good"; break;  
					case BatteryManager.BATTERY_HEALTH_OVERHEAT: healthStr = "Overheat"; break;  
					case BatteryManager.BATTERY_HEALTH_DEAD: healthStr = "Dead"; break;  
					case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE: healthStr = "Over Voltage"; break;  
					case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE: healthStr = "Failure"; break;  
			}  
			batteryHealth.setText("Health: " + healthStr);  
			
			String technology = batteryStatus.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);  
			batteryTech.setText("Technology: " + (technology != null ? technology : "Unknown"));  
			
			int plugType = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);  
			String plugStr = "Unplugged";  
			switch (plugType) {  
					case BatteryManager.BATTERY_PLUGGED_USB: plugStr = "USB"; break;  
					case BatteryManager.BATTERY_PLUGGED_AC: plugStr = "AC"; break;  
					case BatteryManager.BATTERY_PLUGGED_WIRELESS: plugStr = "Wireless"; break;  
			}  
			batteryPlug.setText("Plug Type: " + plugStr);  
			
			Intent powerProfileIntent = new Intent();  
			powerProfileIntent.setClassName("com.android.settings", "com.android.settings.fuelgauge.PowerUsageSummary");  
			try {  
					Object powerProfile = Class.forName("com.android.internal.os.PowerProfile")  
					.getConstructor(Context.class).newInstance(this);  
					double capacity = (double) Class.forName("com.android.internal.os.PowerProfile")  
					.getMethod("getBatteryCapacity").invoke(powerProfile);  
					batteryCapacity.setText(String.format(Locale.US, "Capacity: %.0f mAh", capacity));  
			} catch (Exception e) {  
					batteryCapacity.setText("Capacity: Unknown");  
			}
	}
	
	
	private int getTempColor(float temp) {
			if (temp < 30f) return Color.rgb(46, 204, 113);
			else if (temp < 40f) return Color.rgb(241, 196, 15);
			else return Color.rgb(231, 76, 60);
	}
	
	
	private int getVoltageColor(int voltage) {
			if (voltage < 3600) return Color.rgb(231, 76, 60);
			else if (voltage < 4000) return Color.rgb(241, 196, 15);
			else return Color.rgb(46, 204, 113);
	}
	
	
	private void updateNetworkInfo(NetworkInfoProvider networkInfoProvider) {
			String networkInfoHtml = networkInfoProvider.getNetworkInfoHtml();
			wifiInfo.setText(Html.fromHtml(networkInfoHtml, Html.FROM_HTML_MODE_LEGACY));
	}
	
	
	private void wrapContentLayoutParams (View _v) {
		    _v.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT
				));
	}
	
	
	public class NetworkInfoProvider {
			private final WifiManager wifiManager;
			private final ConnectivityManager connectivityManager;
			private final TelephonyManager telephonyManager;
			
			public NetworkInfoProvider(RetrieverActivity context) {
					wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
					connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
					telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			}
			
		
			public String getWifiInfoHtml() {
					WifiInfo wifiInfo = wifiManager.getConnectionInfo();
					
					String ssid = wifiInfo.getSSID();
					String bssid = wifiInfo.getBSSID();
					String ipAddress = Formatter.formatIpAddress(wifiInfo.getIpAddress());
					String mac = wifiInfo.getMacAddress();
					int rssi = wifiInfo.getRssi();
					int linkSpeed = wifiInfo.getLinkSpeed();
					int frequency = wifiInfo.getFrequency();
					
					StringBuilder html = new StringBuilder();
					html.append("<h2><font color='#B00020'>Wi-Fi Info</font></h2>");
					html.append("<b><font color='#FF4747'>SSID:</font></b> <code>").append(ssid).append("</code><br/><br/>");
					html.append("<b><font color='#FF4747'>BSSID:</font></b> <code>").append(bssid).append("</code><br/><br/>");
					html.append("<b><font color='#FF4747'>IP Address:</font></b> <code>").append(ipAddress).append("</code><br/><br/>");
					html.append("<b><font color='#FF4747'>MAC Address:</font></b> <code>").append(mac).append("</code><br/><br/>");
					html.append("<b><font color='#FF4747'>Link Speed:</font></b> <code>").append(linkSpeed).append(" Mbps</code><br/><br/>");
					html.append("<b><font color='#FF4747'>Frequency:</font></b> <code>").append(frequency).append(" MHz</code><br/><br/>");
					html.append("<b><font color='#FF4747'>Signal Strength:</font></b> <code>").append(rssi).append(" dBm</code><br/><br/>");
					
					return html.toString();
			}
			
		
			public String getMobileNetworkInfoHtml() {
					StringBuilder html = new StringBuilder();
					Network activeNetwork = connectivityManager.getActiveNetwork();
					
					if (activeNetwork != null) {
							NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
							if (capabilities != null) {
									boolean isMobile = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
									boolean isWifi = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
									
									if (isMobile) {
											int networkType = telephonyManager.getNetworkType();
											boolean isRoaming = telephonyManager.isNetworkRoaming();
											
											html.append("<h2><font color='#B00020'>Mobile Network Info</font></h2>");
											html.append("<b><font color='#FF4747'>Network Type:</font></b> <code>").append(getNetworkType(networkType)).append("</code><br/><br/>");
											html.append("<b><font color='#FF4747'>Is Roaming:</font></b> <code>").append(isRoaming ? "Yes" : "No").append("</code><br/><br/>");
									}
									
									if (isWifi) {
											html.append("<h2><font color='#B00020'>Wi-Fi Network Info</font></h2>");
											html.append("<b><font color='#FF4747'>Connected to Wi-Fi Network</font></b><br/><br/>");
									}
							}
					} else {
							html.append("<h2><font color='#B00020'>Network Info</font></h2>");
							html.append("<b><font color='#FF4747'>No network available</font></b><br/><br/>");
					}
					
					return html.toString();
			}
			
		    
			private String getNetworkType(int networkType) {
					switch (networkType) {
							case TelephonyManager.NETWORK_TYPE_GPRS: return "2G";
							case TelephonyManager.NETWORK_TYPE_EDGE: return "2G";
							case TelephonyManager.NETWORK_TYPE_UMTS: return "3G";
							case TelephonyManager.NETWORK_TYPE_HSDPA: return "3G";
							case TelephonyManager.NETWORK_TYPE_HSPA: return "3G";
							case TelephonyManager.NETWORK_TYPE_LTE: return "4G";
							case TelephonyManager.NETWORK_TYPE_NR: return "5G";
							default: return "Unknown";
					}
			}
			
		
			public String getNetworkInfoHtml() {
					return getWifiInfoHtml() + getMobileNetworkInfoHtml();
			}
	}
	
	
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
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
