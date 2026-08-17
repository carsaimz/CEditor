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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import android.app.Activity;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.EnumMap;
import java.util.Map;
import java.util.*;
import java.lang.Math;
import java.util.Stack;
import java.util.StringTokenizer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.inputmethod.InputMethodManager;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import java.io.*;
import java.io.File;
import java.lang.Process;
import java.lang.ProcessBuilder;



public class TerminalActivity extends AppCompatActivity {
	
	private ScaleGestureDetector scaleGestureDetector;
	private float textSize = 14f;
	private SensorManager sensorManager;
	
	private Handler handler = new Handler();
	private Runnable resetBackPressed = new Runnable() {
			@Override
			public void run() {
					amountOfBackPressed = 0;
			        out_text.append("\n[Action canceled.]");
			}
	};
	private String out = "";
	private String output = "";
	private String pwd = "";
	private String command = "";
	private String string = "";
	private double amountOfBackPressed = 0;
	
	private LinearLayout t_line;
	private FrameLayout base;
	private LinearLayout b_line;
	private LinearLayout p1;
	private LinearLayout p2;
	private RigidScrollView vscroll1;
	private LinearLayout linear2;
	private TerminalTextView out_text;
	private TerminalTextView textview3;
	private EditText terminal;
	private ImageView focus;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.terminal);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		t_line = findViewById(R.id.t_line);
		base = findViewById(R.id.base);
		b_line = findViewById(R.id.b_line);
		p1 = findViewById(R.id.p1);
		p2 = findViewById(R.id.p2);
		vscroll1 = findViewById(R.id.vscroll1);
		linear2 = findViewById(R.id.linear2);
		out_text = findViewById(R.id.out_text);
		textview3 = findViewById(R.id.textview3);
		terminal = findViewById(R.id.terminal);
		focus = findViewById(R.id.focus);
		
		t_line.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				vscroll1.post(() -> vscroll1.fullScroll(View.FOCUS_DOWN));
				terminal.requestFocus();
			}
		});
		
		linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				vscroll1.post(() -> vscroll1.fullScroll(View.FOCUS_DOWN));
				terminal.requestFocus();
				terminal.post(() ->
				_showKeyboard(terminal));
			}
		});
		
		out_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				vscroll1.post(() -> vscroll1.fullScroll(View.FOCUS_DOWN));
				terminal.requestFocus();
				terminal.post(() ->
				_showKeyboard(terminal));
			}
		});
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				vscroll1.post(() -> vscroll1.fullScroll(View.FOCUS_DOWN));
				terminal.requestFocus();
				terminal.post(() ->
				_showKeyboard(terminal));
			}
		});
		
		terminal.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				string = _charSeq;
				if (_charSeq.contains("\n")) {
					if (!_charSeq.trim().isEmpty()) {
						_executeCommand(string.trim());
						out_text.setText(out);
						textview3.setText(string);
						terminal.setText("");
						string = "";
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
		
		focus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				vscroll1.post(() -> vscroll1.fullScroll(View.FOCUS_DOWN));
				terminal.requestFocus();
				terminal.post(() ->
				_showKeyboard(terminal));
			}
		});
	}
	
	private void initializeLogic() {
		
		_hideKeyboard(terminal);
		terminal.requestFocus();
		scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
		out_text.setOnTouchListener((v, event) -> {
				if (event.getPointerCount() > 1) {
						vscroll1.requestDisallowInterceptTouchEvent(true);
						return scaleGestureDetector.onTouchEvent(event);
				} else {
						vscroll1.requestDisallowInterceptTouchEvent(false);
				}
				return false;
		});
		
		vscroll1.setSnappingEnabled(true);
		out_text.setElegantTextHeight(true);
		pwd = getIntent().getStringExtra("path");
		if (pwd == null) {
			pwd = "/storage/emulated/0";
		}
		out = startUp(this);
		out_text.setText(out);
		_ripple(focus);
	}
	
	
	public void typeText(final TextView textView, final String message, final int delay) {
		    final Handler handler = new Handler();
		    final Runnable runnable = new Runnable() {
			        int i = 0;
			
			        @Override
			        public void run() {
				            if (i < message.length()) {
					                textView.append(String.valueOf(message.charAt(i)));
					                i++;
					                handler.postDelayed(this, delay);
					            }
				        }
			    };
		    handler.post(runnable);
	}
	
	
	private Bitmap loadBitmapFromFile(String filePath) {
			try {
					File imgFile = new File(filePath);
					if (imgFile.exists()) {
							return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
					} else {
							throw new IOException("File not found: " + filePath);
					}
			} catch (IOException e) {
					e.printStackTrace();
					return null;
			}
	}
	 @Override
	public boolean onTouchEvent(MotionEvent event) {
			return scaleGestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
	}
	
	
	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
			@Override
			public boolean onScale(ScaleGestureDetector detector) {
					float scaleFactor = detector.getScaleFactor();
					textSize *= scaleFactor;
					textSize = Math.max(1f, Math.min(textSize, 70f));
					
					out_text.setTextSize(textSize);
					textview3.setTextSize(textSize);
					terminal.setTextSize(textSize);
					
					out_text.post(() -> {
							vscroll1.setScrollStep(out_text.getLineHeight());
							vscroll1.snapToNearestImmediate();
					});
					
					return true;
			}
	}
	
	
	@Override
	public void onBackPressed() {
		if (amountOfBackPressed == 0) {
			amountOfBackPressed++;
			out_text.append("\n\n[Press back again to exit the terminal.]");
			handler.postDelayed(resetBackPressed, 4000);
		}
		else {
			out_text.append("\n[Session finished.]");
			new Handler(Looper.getMainLooper()).postDelayed(() -> System.exit(0), 500);
			overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
		}
	}
	public void _ViewWidthHeight(final View _view, final double _w, final double _h) {
		_view.getLayoutParams().width = (int)_w;
		_view.getLayoutParams().height = (int)_h;
		_view.requestLayout();
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
	
	
	public void _executeCommand(final String _command) {
		StringBuilder output = new StringBuilder(this.out);
		storeCommand(_command);
		String user = "", hostname = "", prompt = "";
		
		if (pwd == null || pwd.isEmpty()) {
				pwd = System.getProperty("user.home");
		}
		
		try {
				user = runCommand("whoami").trim();
				hostname = runCommand("hostname").trim();
				
				prompt = "\n" + user + "@" + hostname + " > ";
				output.append(prompt).append(_command);
				
				String[] parts = _command.trim().split("\\s+");
				boolean customCommandHandled = false;
				
				if (_command.trim().equalsIgnoreCase("clear")) {
						output.setLength(0);
						output.append(prompt);
						this.out = output.toString();
						return;
				}
				
				if (_command.trim().equalsIgnoreCase("exit")) {
						output.append("\nExiting...");
						new Handler(Looper.getMainLooper()).postDelayed(() -> System.exit(0), 1000);
						this.out = output.toString();
						return;
				}
				
				if (parts.length > 0) {
						switch (parts[0].toLowerCase()) {
								case "asciiart":
								if (parts.length > 1) handleAsciiArtCommand(output, parts);
								customCommandHandled = true;
								break;
								case "cd":
								if (parts.length > 1) handleCdCommand(output, parts[1]);
								customCommandHandled = true;
								break;
								case "cat":
								if (parts.length > 1) handleCatCommand(output, parts[1]);
								customCommandHandled = true;
								break;
								case "matrix":
								handleMatrixCommand(output, parts);
								customCommandHandled = true;
								break;
								case "cow":
								handleCowCommand(output, parts);
								customCommandHandled = true;
								break;
								case "fortune":
								handleFortuneCommand(output);
								customCommandHandled = true;
								break;
								case "brightness":
								handleBrightnessCommand(output, parts, this);
								customCommandHandled = true;
								break;
								case "date":
								handleDateCommand(output);
								customCommandHandled = true;
								break;
								case "reverse":
								handleReverseCommand(output, parts);
								customCommandHandled = true;
								break;
								case "calc":
								handleCalcCommand(output, parts);
								customCommandHandled = true;
								break;
								case "dice":
								handleDiceCommand(output);
								customCommandHandled = true;
								break;
								case "deviceinfo":
								handleDeviceInfoCommand(output, this);
								customCommandHandled = true;
								break;
								case "history":
								handleHistoryCommand(output);
								customCommandHandled = true;
								break;
								case "neofetch":
								handleNeofetchCommand(output);
								customCommandHandled = true;
								break;
								case "battery":
								handleBatteryCommand(output, this);
								customCommandHandled = true;
								break;
								case "storage":
								handleStorageCommand(output);
								customCommandHandled = true;
								break;
								case "ram":
								handleRamCommand(output, this);
								customCommandHandled = true;
								break;
								case "apps":
								handleAppsCommand(output, this);
								customCommandHandled = true;
								break;
								case "reboot":
								handleRebootCommand(output);
								customCommandHandled = true;
								break;
								case "shutdown":
								handleShutdownCommand(output);
								customCommandHandled = true;
								break;
								case "clear_cache":
								handleClearCacheCommand(output, this);
								customCommandHandled = true;
								break;
								case "logcat":
								handleLogcatCommand(output);
								customCommandHandled = true;
								break;
								case "cpu":
								handleCpuCommand(output);
								customCommandHandled = true;
								break;
								case "netstat":
								handleNetstatCommand(output);
								customCommandHandled = true;
								break;
								case "processes":
								handleProcessesCommand(output);
								customCommandHandled = true;
								break;
								case "kill":
								handleKillCommand(output, parts);
								customCommandHandled = true;
								break;
								case "temp":
								handleTempCommand(output);
								customCommandHandled = true;
								break;
								case "ping":
								handlePingCommand(output, parts);
								customCommandHandled = true;
								break;
								case "sensors":
								handleSensorsCommand(output, this);
								customCommandHandled = true;
								break;
					            case "qt":
								handleFortuneCommand(output);
								customCommandHandled = true;
								break;
					            case "help":
					            handleHelpCommand(output, parts);
					            customCommandHandled = true;
					            break;
					            case "wifi":
					            handleWifiCommand(output, parts, this);
					            customCommandHandled = true;
					            break;
					            case "bluetooth":
					            handleBluetoothCommand(output, parts, this);
					            break;
						}
				}
				
				if (!customCommandHandled) {
						executeShellCommand(output, _command);
				}
				
				this.out = output.toString();
				
		} catch (IOException | InterruptedException e) {
				output.append("\nError: ").append(e.getMessage());
				this.out = output.toString();
		}
	}
	
	
	private void handleAsciiArtCommand(StringBuilder output, String[] parts) {
		    String filename = parts[1];
		    String mode = (parts.length > 2) ? parts[2].toLowerCase() : "symbol";
		    int size;
		    
		    try {
			        size = (parts.length > 3) ? Integer.parseInt(parts[3].trim()) : 80;
			        size = Math.max(10, Math.min(size, 300));
			    } catch (NumberFormatException e) {
			        output.append("\nError: Invalid size parameter. Use a number between 10 and 300.");
			        return;
			    }
		    
		    File imgFile = new File(pwd + "/" + filename);
		    if (!imgFile.exists()) {
			        output.append("\nError: File not found in ").append(pwd);
			        return;
			    }
		    
		    Bitmap image = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		    if (image == null) {
			        output.append("\nError: Not a valid image file.");
			        return;
			    }
		    
		    int newWidth = size;
		    int newHeight = (image.getHeight() * newWidth) / image.getWidth();
		    
		    if (!mode.equals("braille")) {
			        newHeight = (int) (newHeight * 0.55);
			    }
		    
		    image = Bitmap.createScaledBitmap(image, newWidth, newHeight, true);
		    
		    output.append("\n[ASCII ART - ").append(mode.equals("braille") ? "Braille" : mode.equals("block") ? "Block" : mode.equals("line") ? "Line" : "Symbols").append("]\n");
		    
		    switch (mode) {
			        case "braille":
			            output.append(generateBrailleAscii(image));
			            break;
			        case "block":
			            output.append(generateBlockAscii(image));
			            break;
			        case "line":
			            output.append(generateLineAscii(image));
			            break;
			        case "detailed":
			            output.append(generateDetailedAscii(image));
			            break;
			        case "symbol":
			        default:
			            output.append(generateSymbolAscii(image));
			            break;
			    }
	}
	
	
	private String generateSymbolAscii(Bitmap image) {
		    String chars = "@#%WM8B$&AHXpqdbkO0QLCJUYXzcvunxrjft/|()1{}[]?-_+<>~i!lI;:,\"^`'. ";
		    int charCount = chars.length();
		    StringBuilder ascii = new StringBuilder();
		
		    for (int y = 0; y < image.getHeight(); y++) {
			        for (int x = 0; x < image.getWidth(); x++) {
				            int pixel = image.getPixel(x, y);
				            int gray = (((pixel >> 16) & 0xff) + ((pixel >> 8) & 0xff) + (pixel & 0xff)) / 3;
				            int index = (gray * (charCount - 1)) / 255;
				            ascii.append(chars.charAt(index));
				        }
			        ascii.append("\n");
			    }
		    return ascii.toString();
	}
	
	
	private String generateBrailleAscii(Bitmap image) {
			StringBuilder ascii = new StringBuilder();
			int width = image.getWidth();
			int height = image.getHeight();
			
			for (int y = 0; y < height; y += 4) {
					for (int x = 0; x < width; x += 2) {
							int brailleCode = 0x2800;
							
							for (int dy = 0; dy < 4; dy++) {
									for (int dx = 0; dx < 2; dx++) {
											int px = x + dx;
											int py = y + dy;
											if (px < width && py < height) {
													int pixel = image.getPixel(px, py);
													int red = (pixel >> 16) & 0xff;
													int green = (pixel >> 8) & 0xff;
													int blue = pixel & 0xff;
													int gray = (red + green + blue) / 3;
													
													if (gray < 128) { // Darker pixels become Braille dots
															int bitIndex = getBrailleBitIndex(dx, dy);
															brailleCode |= (1 << bitIndex);
													}
											}
									}
							}
							ascii.append((char) brailleCode);
					}
					ascii.append("\n");
			}
			return ascii.toString();
	}
	
	
	private int getBrailleBitIndex(int x, int y) {
		    final int[] bitPositions = {0, 1, 2, 3, 4, 5, 6, 7};
		    return bitPositions[y + x * 4];
	}
	
	
	private String generateLineAscii(Bitmap image) {
		    StringBuilder ascii = new StringBuilder();
		    
		    for (int y = 1; y < image.getHeight() - 1; y++) {
			        for (int x = 1; x < image.getWidth() - 1; x++) {
				            int pixel = image.getPixel(x, y);
				            int neighborPixel1 = image.getPixel(x + 1, y);
				            int neighborPixel2 = image.getPixel(x, y + 1);
				            int currentGray = ((pixel >> 16) & 0xff + (pixel >> 8) & 0xff + pixel & 0xff) / 3;
				            int rightGray = ((neighborPixel1 >> 16) & 0xff + (neighborPixel1 >> 8) & 0xff + neighborPixel1 & 0xff) / 3;
				            int downGray = ((neighborPixel2 >> 16) & 0xff + (neighborPixel2 >> 8) & 0xff + neighborPixel2 & 0xff) / 3;
				            if (Math.abs(currentGray - rightGray) > 30 || Math.abs(currentGray - downGray) > 30) {
					                ascii.append("|");
					            } else {
					                ascii.append(" ");
					            }
				        }
			        ascii.append("\n");
			    }
		    return ascii.toString();
	}
	
	
	private String generateBlockAscii(Bitmap image) {
		    StringBuilder ascii = new StringBuilder();
		    String blocks = "█▓▒░‎";
		    int blockCount = blocks.length();
		    
		    for (int y = 0; y < image.getHeight(); y++) {
			        for (int x = 0; x < image.getWidth(); x++) {
				            int pixel = image.getPixel(x, y);
				            int red = (pixel >> 16) & 0xff;
				            int green = (pixel >> 8) & 0xff;
				            int blue = pixel & 0xff;
				            int gray = (red + green + blue) / 3;
				
				            int index = (gray * (blockCount - 1)) / 255;
				            ascii.append(blocks.charAt(index));
				        }
			        ascii.append("\n");
			    }
		    return ascii.toString();
	}
	
	
	private String generateDetailedAscii(Bitmap image) {
		    StringBuilder ascii = new StringBuilder();
		    String chars = "█ ▇ ▆ ▅ ▄ ▃ ▂ ▁ ▉ ▊ ▋ ▌ ▍ ▎ ▏ ▙ ▚ ▛ ▜ ▖ ▗ ▘ ▝ ▞ ▟ ▐ ■ ▀";
		    String[] blocks = chars.split(" ");
		    int blockCount = blocks.length;
		    
		    for (int y = 0; y < image.getHeight(); y++) {
			        for (int x = 0; x < image.getWidth(); x++) {
				            int pixel = image.getPixel(x, y);
				            int red = (pixel >> 16) & 0xff;
				            int green = (pixel >> 8) & 0xff;
				            int blue = pixel & 0xff;
				            int gray = (red + green + blue) / 3;
				            int index = (gray * (blockCount - 1)) / 255;
				            ascii.append(blocks[index]);
				        }
			        ascii.append("\n");
			    }
		    return ascii.toString();
	}
	
	
	
	private void handleCdCommand(StringBuilder output, String newPath) {
			File newDir = new File(pwd, newPath);
			if (newDir.isDirectory()) {
					pwd = newDir.getAbsolutePath();
			} else {
					output.append("\nError: ").append(newPath).append(" is not a directory.");
			}
	}
	
	
	private void handleCatCommand(StringBuilder output, String filename) throws IOException {
			File file = new File(pwd + "/" + filename);
			if (!file.exists() || file.isDirectory()) {
					output.append("\nError: File not found or is a directory.");
					return;
			}
			
			long fileSize = file.length();
			if (fileSize > 500 * 1024) {
					output.append("\nWarning: File is large (").append(fileSize / 1024).append(" KB). Continue? (yes/no)");
					this.out = output.toString();
					return;
			}
			
			BufferedReader fileReader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = fileReader.readLine()) != null) {
					output.append("\n").append(line);
			}
			fileReader.close();
	}
	
	
	private void executeShellCommand(StringBuilder output, String command) throws IOException, InterruptedException {
		    String[] commands = command.split("&&");
		    
		    for (String cmd : commands) {
			        cmd = cmd.trim();
			        if (cmd.isEmpty()) continue;
			
			        ProcessBuilder builder = new ProcessBuilder("/bin/sh", "-c", cmd);
			        builder.directory(new File(pwd));
			        builder.redirectErrorStream(true);
			        Process process = builder.start();
			        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			        String line;
			        while ((line = reader.readLine()) != null) {
				            output.append("\n").append(line);
				        }
			        process.waitFor();
			    }
	}
	
	
	private void handleCowCommand(StringBuilder output, String[] parts) {
			String message = (parts.length > 1) ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)) : "Moo!";
			
			String cow = 
			"\n\n  " + message +
			"\n   \\   ^__^ " +
			"\n    \\  (oo)\\_______ " +
			"\n       (__)\\       )\\/\\ " +
			"\n           ||----w | " +
			"\n           ||     || \n";
			
			output.append(cow);
	}
	
	
	private void handleFortuneCommand(StringBuilder output) {
		    Random random = new Random();
		    String[] quotes = {
			        "Believe in yourself!",
			        "The best time to start was yesterday. The next best time is now.",
			        "Keep pushing forward!",
			        "You miss 100% of the shots you don’t take.",
			        "Stay positive and work hard!",
			        "Success is the sum of small efforts, repeated day in and day out.",
			        "Hard work beats talent when talent doesn’t work hard.",
			        "Your limitation—it’s only your imagination.",
			        "Push yourself, because no one else is going to do it for you.",
			        "Great things never come from comfort zones.",
			        "Dream it. Wish it. Do it.",
			        "Success doesn’t just find you. You have to go out and get it.",
			        "The harder you work for something, the greater you’ll feel when you achieve it.",
			        "Dream bigger. Do bigger.",
			        "Don’t stop when you’re tired. Stop when you’re done.",
			        "Wake up with determination. Go to bed with satisfaction.",
			        "Little things make big days.",
			        "It’s going to be hard, but hard does not mean impossible.",
			        "Don’t wait for opportunity. Create it.",
			        "Sometimes we’re tested not to show our weaknesses, but to discover our strengths.",
			        "The key to success is to focus on goals, not obstacles.",
			        "Dream it. Believe it. Build it.",
			        "Code is like humor. When you have to explain it, it’s bad.",
			        "The best way to predict the future is to invent it.",
			        "Talk is cheap. Show me the code.",
			        "First, solve the problem. Then, write the code.",
			        "Simplicity is the soul of efficiency.",
			        "Programming isn’t about what you know; it’s about what you can figure out.",
			        "It’s not a bug, it’s an undocumented feature.",
			        "Good code is its own best documentation.",
			        "Before software can be reusable, it first has to be usable.",
			        "Don’t comment bad code—rewrite it.",
			        "The only way to learn a new programming language is by writing programs in it.",
			        "Software is a great combination between artistry and engineering.",
			        "Code never lies, comments sometimes do.",
			        "Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful.",
			        "The only limit to our realization of tomorrow is our doubts of today.",
			        "You can't be a great programmer without writing great code.",
			        "The problem is not the problem. The problem is your attitude about the problem.",
			        "If you think your code is perfect, you just haven’t debugged it enough.",
			        "Don’t worry if it doesn’t work right. If everything did, you’d be out of a job.",
			        "Code is poetry.",
			        "Your most unhappy customers are your greatest source of learning.",
			        "Debugging is like being the detective in a crime movie where you are also the murderer.",
			        "The sooner you start coding, the sooner you’ll start solving problems.",
			        "Every time you write code, you are writing history.",
			        "You can’t fix what you don’t understand.",
			        "Great developers make things easy, but the best developers make things look easy.",
			        "There are only two hard things in computer science: cache invalidation, naming things, and off-by-one errors.",
			        "If you can’t explain it simply, you don’t understand it well enough.",
			        "Perfection is achieved not when there is nothing more to add, but when there is nothing left to take away.",
			        "Computers are fast; programmers keep it slow."
			    };
		    
		    output.append("\n\n❝ ").append(quotes[random.nextInt(quotes.length)]).append(" ❞\n\n");
	}
	
	
	private void handleDateCommand(StringBuilder output) {
			output.append("\n").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
	}
	
	
	private void handleReverseCommand(StringBuilder output, String[] parts) {
			if (parts.length < 2) {
					output.append("\nUsage: reverse <text>");
					return;
			}
			
			String reversed = new StringBuilder(parts[1]).reverse().toString();
			output.append("\n").append(reversed);
	}
	
	
	private void handleDiceCommand(StringBuilder output) {
			int result = new Random().nextInt(6) + 1;
			output.append("\nYou rolled a ").append(result);
	}
	
	
	private List<String> commandHistory = new ArrayList<>();
	
	
	private void handleHistoryCommand(StringBuilder output) {
			output.append("\nCommand History:");
			for (int i = 0; i < commandHistory.size(); i++) {
					output.append("\n").append(i + 1).append(": ").append(commandHistory.get(i));
			}
	}
	
	
	private void storeCommand(String command) {
			if (!command.trim().isEmpty()) {
					commandHistory.add(command);
			}
	}
	
	private void handleNeofetchCommand(StringBuilder output) {
		    try {
			        String osVersion = System.getProperty("os.version", "Unknown");
			        String kernelVersion = getKernelVersion();
			        String cpuArchitecture = Build.CPU_ABI;
			        String memory = getMemory();
			        output.append("\n")
			            .append("OS: Android ").append(osVersion).append("\n")
			            .append("Kernel: ").append(kernelVersion).append("\n")
			            .append("CPU: ").append(cpuArchitecture).append("\n")
			            .append("Memory: ").append(memory).append("\n")
			            .append("Shell: InFlat CLI\n");
			    } catch (Exception e) {
			        output.append("\nError: Could not retrieve system info.");
			    }
	}
	
	private String getKernelVersion() {
		    try {
			        Process process = Runtime.getRuntime().exec("uname -r");
			        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			        String kernelVersion = reader.readLine();
			        reader.close();
			        return kernelVersion != null ? kernelVersion : "Unknown";
			    } catch (IOException e) {
			        return "Unknown";
			    }
	}
	
	private String getMemory() {
		    try {
			        Process process = Runtime.getRuntime().exec("cat /proc/meminfo");
			        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			        String line;
			        while ((line = reader.readLine()) != null) {
				            if (line.startsWith("MemTotal")) {
					                String[] parts = line.split(":");
					                return parts[1].trim();
					            }
				        }
			        reader.close();
			    } catch (IOException e) {
			        return "Unknown";
			    }
		    return "Unknown";
	}
	
	
	private void handleBatteryCommand(StringBuilder output, Context context) {
		    IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		    Intent batteryStatus = context.registerReceiver(null, filter);
		
		    int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		    int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
		    int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		
		    boolean isCharging = (status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL);
		    int batteryPercent = (int) ((level / (float) scale) * 100);
		
		    output.append("\nBattery        : ").append(batteryPercent).append("%");
		    output.append("\nCharging       : ").append(isCharging ? "Yes" : "No");
	}
	
	
	private void handleBrightnessCommand(StringBuilder output, String[] parts, Context context) {
		    if (parts.length < 2) {
			        output.append("\nUsage: brightness <value>");
			        return;
			    }
		
		    if (!Settings.System.canWrite(context)) {
			        output.append("\nError: Permission required to modify system settings. Grant permission in device settings.");
			        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
			        intent.setData(Uri.parse("package:" + context.getPackageName()));
			        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			        context.startActivity(intent);
			        return;
			    }
		
		    try {
			        int brightness = Integer.parseInt(parts[1]);
			        brightness = Math.max(0, Math.min(brightness, 255));
			
			        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);
			        output.append("\nBrightness set to ").append(brightness);
			    } catch (Exception e) {
			        output.append("\nError: Could not change brightness.");
			    }
	}
	
	
	private void handleWifiCommand(StringBuilder output, String[] parts, Context context) {
		    if (parts.length < 2) {
			        output.append("\nUsage: wifi <on/off>");
			        return;
			    }
		
		    boolean enable = parts[1].equalsIgnoreCase("on");
		    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		
		    if (wifiManager == null) {
			        output.append("\nError: Wi-Fi manager not available.");
			        return;
			    }
		
		    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {  // Android 9 and below
			        wifiManager.setWifiEnabled(enable);
			        output.append("\nWi-Fi ").append(enable ? "enabled" : "disabled");
			    } else { 
			        output.append("\nCannot toggle Wi-Fi directly on Android 10+.");
			        output.append("\nOpening Wi-Fi settings...");
			        Intent panelIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
			        panelIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			        context.startActivity(panelIntent);
			    }
	}
	
	
	private void handleStorageCommand(StringBuilder output) {
			File path = Environment.getDataDirectory();
			StatFs stat = new StatFs(path.getPath());
			
			long blockSize = stat.getBlockSizeLong();
			long totalBlocks = stat.getBlockCountLong();
			long availableBlocks = stat.getAvailableBlocksLong();
			
			long totalSpace = totalBlocks * blockSize / (1024 * 1024);
			long freeSpace = availableBlocks * blockSize / (1024 * 1024);
			
			output.append("\nStorage: ").append(freeSpace).append("MB free / ").append(totalSpace).append("MB total");
	}
	
	
	private void handleRamCommand(StringBuilder output, Context context) {
			ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
			activityManager.getMemoryInfo(memoryInfo);
			
			long availableMb = memoryInfo.availMem / (1024 * 1024);
			long totalMb = memoryInfo.totalMem / (1024 * 1024);
			
			output.append("\nRAM: ").append(availableMb).append("MB free / ").append(totalMb).append("MB total");
	}
	
	
	private void handleAppsCommand(StringBuilder output, Context context) {
			PackageManager pm = context.getPackageManager();
			List<ApplicationInfo> apps = pm.getInstalledApplications(0);
			
			for (ApplicationInfo app : apps) {
					output.append("\n").append(app.loadLabel(pm)).append(" (").append(app.packageName).append(")");
			}
	}
	
	
	private void handleRebootCommand(StringBuilder output) {
			try {
					Process process = Runtime.getRuntime().exec("su -c reboot");
					process.waitFor();
					output.append("\nRebooting...");
			} catch (Exception e) {
					output.append("\nError: Root access required.");
			}
	}
	
	private void handleShutdownCommand(StringBuilder output) {
			try {
					Process process = Runtime.getRuntime().exec("su -c reboot -p");
					process.waitFor();
					output.append("\nShutting down...");
			} catch (Exception e) {
					output.append("\nError: Root access required.");
			}
	}
	
	
	private void handleClearCacheCommand(StringBuilder output, Context context) {
			try {
					File cacheDir = context.getCacheDir();
					deleteRecursive(cacheDir);
					
					output.append("\nCache cleared.");
			} catch (Exception e) {
					output.append("\nError: Could not clear cache.");
			}
	}
	
	
	private void deleteRecursive(File file) {
			if (file.isDirectory()) {
					for (File child : file.listFiles()) {
							deleteRecursive(child);
					}
			}
			file.delete();
	}
	
	
	private void handleLogcatCommand(StringBuilder output) {
			try {
					Process process = Runtime.getRuntime().exec("logcat -d");
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					
					String line;
					int lines = 0;
					while ((line = reader.readLine()) != null && lines < 50) {
							output.append("\n").append(line);
							lines++;
					}
			} catch (Exception e) {
					output.append("\nError: Could not retrieve logs.");
			}
	}
	
	private void handleCpuCommand(StringBuilder output) {
		    try {
			        BufferedReader reader = new BufferedReader(new FileReader("/proc/stat"));
			        String line = reader.readLine();
			        
			        if (line != null && line.startsWith("cpu")) {
				            String[] tokens = line.split("\\s+");
				            long user = Long.parseLong(tokens[1]);
				            long nice = Long.parseLong(tokens[2]);
				            long system = Long.parseLong(tokens[3]);
				            long idle = Long.parseLong(tokens[4]);
				            long iowait = Long.parseLong(tokens[5]);
				            long irq = Long.parseLong(tokens[6]);
				            long softirq = Long.parseLong(tokens[7]);
				
				            long total = user + nice + system + idle + iowait + irq + softirq;
				            long used = total - idle;
				
				            double cpuUsage = (used * 100.0) / total;
				            output.append("\nCPU Usage: ").append(String.format("%.2f", cpuUsage)).append("%");
				        } else {
				            output.append("\nError: Could not read CPU stats.");
				        }
			
			        reader.close();
			    } catch (Exception e) {
			        output.append("\nError: Could not retrieve CPU info.");
			    }
	}
	
	
	private void handleNetstatCommand(StringBuilder output) {
		    try {
			        output.append("\nActive TCP Connections:");
			        readNetStatFile(output, "/proc/net/tcp");
			
			        output.append("\n\nActive UDP Connections:");
			        readNetStatFile(output, "/proc/net/udp");
			    } catch (Exception e) {
			        output.append("\nError: Could not retrieve network stats.");
			    }
	}
	
	private void readNetStatFile(StringBuilder output, String filePath) {
		    try {
			        BufferedReader reader = new BufferedReader(new FileReader(filePath));
			        String line;
			        int lineCount = 0;
			
			        while ((line = reader.readLine()) != null) {
				            if (lineCount++ == 0) continue; // Skip the first line (headers)
				
				            String[] columns = line.trim().split("\\s+");
				            if (columns.length > 1) {
					                String localAddress = parseIp(columns[1]);
					                String remoteAddress = parseIp(columns[2]);
					                output.append("\n").append(localAddress).append(" -> ").append(remoteAddress);
					            }
				        }
			
			        reader.close();
			    } catch (IOException e) {
			        output.append("\nError reading ").append(filePath);
			    }
	}
	
	private String parseIp(String hexAddress) {
		    try {
			        String[] parts = hexAddress.split(":");
			        long ip = Long.parseLong(parts[0], 16);
			        int port = Integer.parseInt(parts[1], 16);
			
			        return String.format("%d.%d.%d.%d:%d",
			                (ip >> 0) & 0xFF,
			                (ip >> 8) & 0xFF,
			                (ip >> 16) & 0xFF,
			                (ip >> 24) & 0xFF,
			                port);
			    } catch (Exception e) {
			        return "Unknown";
			    }
	}
	
	
	private void handleProcessesCommand(StringBuilder output) {
			try {
					Process process = Runtime.getRuntime().exec("ps");
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					
					String line;
					while ((line = reader.readLine()) != null) {
							output.append("\n").append(line);
					}
			} catch (Exception e) {
					output.append("\nError: Could not retrieve processes.");
			}
	}
	
	
	private void handleKillCommand(StringBuilder output, String[] parts) {
			if (parts.length < 2) {
					output.append("\nUsage: kill <PID>");
					return;
			}
			
			try {
					int pid = Integer.parseInt(parts[1]);
					Process process = Runtime.getRuntime().exec("kill -9 " + pid);
					process.waitFor();
					output.append("\nProcess ").append(pid).append(" killed.");
			} catch (Exception e) {
					output.append("\nError: Could not kill process.");
			}
	}
	
	
	private void handleTempCommand(StringBuilder output) {
			try {
					Process process = Runtime.getRuntime().exec("cat /sys/class/thermal/thermal_zone0/temp");
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					
					String line = reader.readLine();
					if (line != null) {
							float tempC = Float.parseFloat(line) / 1000;
							output.append("\nCPU Temp: ").append(tempC).append("°C");
					}
			} catch (Exception e) {
					output.append("\nError: Could not retrieve temperature.");
			}
	}
	
	
	private void handleBluetoothCommand(StringBuilder output, String[] parts, Context context) {
			if (parts.length < 2) {
					output.append("\nUsage: bluetooth <on/off>");
					return;
			}
			
			BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			boolean enable = parts[1].equalsIgnoreCase("on");
			
			if (bluetoothAdapter != null) {
					if (enable) {
							bluetoothAdapter.enable();
					} else {
							bluetoothAdapter.disable();
					}
					output.append("\nBluetooth ").append(enable ? "enabled" : "disabled");
			} else {
					output.append("\nError: Bluetooth not supported.");
			}
	}
	
	
	private void handlePingCommand(StringBuilder output, String[] parts) {
			if (parts.length < 2) {
					output.append("\nUsage: ping <hostname>");
					return;
			}
			
			try {
					Process process = Runtime.getRuntime().exec("ping -c 4 " + parts[1]);
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					
					String line;
					while ((line = reader.readLine()) != null) {
							output.append("\n").append(line);
					}
			} catch (Exception e) {
					output.append("\nError: Could not ping.");
			}
	}
	
	
	private void handleSensorsCommand(StringBuilder output, Context context) {
		    if (context == null) {
			        output.append("\nError: Context is NULL! Ensure you're calling this from an Activity or Service.");
			        Log.e("SensorDebug", "Context is NULL! Are you calling this from an activity?");
			        return;
			    }
		
		    SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		
		    if (sensorManager == null) {
			        output.append("\nError: SensorManager is not available.");
			        Log.e("SensorDebug", "SensorManager is NULL! getSystemService() failed.");
			        return;
			    }
		
		    List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		    
		    if (sensors.isEmpty()) {
			        output.append("\nNo sensors found on this device.");
			        return;
			    }
		
		    output.append("\nAvailable Sensors:\n-------------------");
		    for (Sensor sensor : sensors) {
			        output.append("\n• ").append(sensor.getName())
			              .append(" (Type: ").append(sensor.getType()).append(")");
			    }
	}
	private void handleDeviceInfoCommand(StringBuilder output, Context context) {
		    output.append("\n\n=== Device Information ===");
		    output.append("\nModel:           ").append(Build.MODEL);
		    output.append("\nManufacturer:    ").append(Build.MANUFACTURER);
		    output.append("\nBrand:           ").append(Build.BRAND);
		    output.append("\nDevice:          ").append(Build.DEVICE);
		    output.append("\nBoard:           ").append(Build.BOARD);
		    output.append("\nHardware:        ").append(Build.HARDWARE);
		    output.append("\nBootloader:      ").append(Build.BOOTLOADER);
		    output.append("\nAndroid Version: ").append(Build.VERSION.RELEASE);
		    output.append("\nSDK Level:       ").append(Build.VERSION.SDK_INT);
		    output.append("\nSecurity Patch:  ").append(Build.VERSION.SECURITY_PATCH);
		    output.append("\nFingerprint:     ").append(Build.FINGERPRINT);
		    
		    DisplayMetrics metrics = new DisplayMetrics();
		    WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		    if (windowManager != null) {
			        windowManager.getDefaultDisplay().getMetrics(metrics);
			        output.append("\n\n=== Screen Info ===");
			        output.append("\nResolution:      ").append(metrics.widthPixels).append("x").append(metrics.heightPixels);
			        output.append("\nDensity:         ").append(metrics.densityDpi).append(" DPI");
			    }
		
		    ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
		    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		    if (activityManager != null) {
			        activityManager.getMemoryInfo(memoryInfo);
			        long availableRam = memoryInfo.availMem / (1024 * 1024);
			        long totalRam = memoryInfo.totalMem / (1024 * 1024);
			        output.append("\n\n=== RAM Info ===");
			        output.append("\nTotal RAM:       ").append(totalRam).append(" MB");
			        output.append("\nAvailable RAM:   ").append(availableRam).append(" MB");
			    }
		
		    File storage = Environment.getExternalStorageDirectory();
		    StatFs stat = new StatFs(storage.getPath());
		    long blockSize = stat.getBlockSizeLong();
		    long totalBlocks = stat.getBlockCountLong();
		    long freeBlocks = stat.getAvailableBlocksLong();
		    long totalSpace = totalBlocks * blockSize / (1024 * 1024);
		    long freeSpace = freeBlocks * blockSize / (1024 * 1024);
		    output.append("\n\n=== Storage Info ===");
		    output.append("\nTotal Storage:   ").append(totalSpace).append(" MB");
		    output.append("\nFree Storage:    ").append(freeSpace).append(" MB");
		
		    IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		    Intent batteryStatus = context.registerReceiver(null, ifilter);
		    if (batteryStatus != null) {
			        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
			        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
			        int batteryPct = (int) ((level / (float) scale) * 100);
			        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
			        String statusString = (status == BatteryManager.BATTERY_STATUS_CHARGING) ? "Charging" : "Not Charging";
			        output.append("\n\n=== Battery Info ===");
			        output.append("\nBattery Level:   ").append(batteryPct).append("%");
			        output.append("\nStatus:          ").append(statusString);
			    }
		
		    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    if (connectivityManager != null) {
			        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
			        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
			        output.append("\n\n=== Network Info ===");
			        output.append("\nConnected:       ").append(isConnected ? "Yes" : "No");
			        if (isConnected) {
				            output.append("\nType:            ").append(activeNetwork.getTypeName());
				        }
			    }
		
		    CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
		    try {
			        String[] cameraIdList = cameraManager.getCameraIdList();
			        output.append("\n\n=== Camera Info ===");
			        output.append("\nTotal Cameras:   ").append(cameraIdList.length).append("\n");
			    } catch (Exception e) {
			        output.append("\nError: Unable to retrieve camera info.");
			    }
	}
	
	
	private void handleCalcCommand(StringBuilder output, String[] parts) {
			if (parts.length < 2) {
					output.append("\nUsage: calc <expression>");
					return;
			}
			
			try {
					String expression = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))
					.replace("×", "*")
					.replace("÷", "/")
					.replace("mod", "%")
					.replace("power", "^")
					.replace("√", "sqrt");
					
					double result = evaluateMathExpression(expression);
					
					if (result == (int) result) {
							output.append("\nResult: ").append((int) result);
					} else {
							output.append("\nResult: ").append(result);
					}
					
			} catch (Exception e) {
					output.append("\nError: Invalid expression.");
			}
	}
	
	
	private double evaluateMathExpression(String expression) {
			Stack<Double> values = new Stack<>();
			Stack<Character> operators = new Stack<>();
			StringTokenizer tokenizer = new StringTokenizer(expression, " +-*/%^()", true);
			
			while (tokenizer.hasMoreTokens()) {
					String token = tokenizer.nextToken().trim();
					if (token.isEmpty()) continue;
					
					if (isNumber(token)) {
							values.push(Double.parseDouble(token));
					} else if (token.equals("(")) {
							operators.push('(');
					} else if (token.equals(")")) {
							while (!operators.isEmpty() && operators.peek() != '(') {
									values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
							}
							operators.pop();
					} else if (isOperator(token.charAt(0))) {
							while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.charAt(0))) {
									values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
							}
							operators.push(token.charAt(0));
					}
			}
			
			while (!operators.isEmpty()) {
					values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
			}
			
			return values.pop();
	}
	
	
	private boolean isNumber(String str) {
			try {
					Double.parseDouble(str);
					return true;
			} catch (NumberFormatException e) {
					return false;
			}
	}
	
	
	private boolean isOperator(char ch) {
			return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%' || ch == '^';
	}
	
	
	private int precedence(char op) {
			switch (op) {
					case '+': case '-': return 1;
					case '*': case '/': case '%': return 2;
					case '^': return 3;
					default: return -1;
			}
	}
	
	
	private double applyOperation(char op, double b, double a) {
			switch (op) {
					case '+': return a + b;
					case '-': return a - b;
					case '*': return a * b;
					case '/': return b == 0 ? Double.NaN : a / b;
					case '%': return a % b;
					case '^': return Math.pow(a, b);
					default: return 0;
			}
	}
	
	
	private int gcd(int a, int b) {
			while (b != 0) {
					int temp = b;
					b = a % b;
					a = temp;
			}
			return a;
	}
	
	
	private int lcm(int a, int b) {
			return (a * b) / gcd(a, b);
	}
	
	
	private int factorial(int n) {
			if (n < 0) return -1;
			int fact = 1;
			for (int i = 2; i <= n; i++) {
					fact *= i;
			}
			return fact;
	}
	
	
	private boolean isPrime(int n) {
			if (n <= 1) return false;
			for (int i = 2; i * i <= n; i++) {
					if (n % i == 0) return false;
			}
			return true;
	}
	
	
	private void handleMatrixCommand(StringBuilder output, String[] parts) {
			if (parts.length < 4) {
					output.append("\nUsage: matrix <operation> <size> <values...>");
					return;
			}
			
			String operation = parts[1];
			int size = Integer.parseInt(parts[2]);
			int totalElements = size * size;
			
			if (parts.length < 3 + totalElements) {
					output.append("\nError: Not enough matrix elements provided.");
					return;
			}
			
			double[][] matrix = new double[size][size];
			int index = 3;
			
			for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
							matrix[i][j] = Double.parseDouble(parts[index++]);
					}
			}
			
			switch (operation) {
					case "det":
					output.append("\nDeterminant: ").append(determinant(matrix, size));
					break;
					case "inverse":
					double[][] inverseMatrix = inverse(matrix, size);
					if (inverseMatrix == null) {
							output.append("\nError: Matrix is singular (no inverse).");
					} else {
							output.append("\nInverse Matrix:\n").append(matrixToString(inverseMatrix));
					}
					break;
					default:
					output.append("\nError: Invalid matrix operation.");
			}
	}
	
	
	private double determinant(double[][] matrix, int n) {
			if (n == 1) return matrix[0][0];
			if (n == 2) return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
			
			double det = 0;
			for (int col = 0; col < n; col++) {
					double[][] subMatrix = new double[n - 1][n - 1];
					for (int i = 1; i < n; i++) {
							for (int j = 0, subCol = 0; j < n; j++) {
									if (j == col) continue;
									subMatrix[i - 1][subCol++] = matrix[i][j];
							}
					}
					det += (col % 2 == 0 ? 1 : -1) * matrix[0][col] * determinant(subMatrix, n - 1);
			}
			return det;
	}
	
	
	private double[][] inverse(double[][] matrix, int n) {
			double det = determinant(matrix, n);
			if (det == 0) return null;
			
			double[][] adj = new double[n][n];
			if (n == 2) {
					adj[0][0] = matrix[1][1]; adj[0][1] = -matrix[0][1];
					adj[1][0] = -matrix[1][0]; adj[1][1] = matrix[0][0];
			} else {
			}
			
			for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
			adj[i][j] /= det;
			
			return adj;
	}
	
	
	private String matrixToString(double[][] matrix) {
			StringBuilder sb = new StringBuilder();
			for (double[] row : matrix) {
					for (double val : row) {
							sb.append(String.format("%.2f ", val));
					}
					sb.append("\n");
			}
			return sb.toString();
	}
	
	
	private void handleHelpCommand(StringBuilder output, String[] parts) {
			if (parts.length < 2) {
					output.append("\nAvailable commands:");
					output.append("\n- cd <path>: Change directory path");
					output.append("\n- cd ..: Return to the previous directory");
					output.append("\n- cat <file-name>: Display file contents");
					output.append("\n- print <string>: Display text on console");
					output.append("\n- echo <string>: Display text on console");
					output.append("\n- calc: Perform basic and advanced calculations");
					output.append("\n- matrix: Perform matrix operations");
					output.append("\n- asciiart: Generate ASCII art");
					output.append("\n- cow: Display a talking ASCII cow");
					output.append("\n- fortune: Display a random fortune");
					output.append("\n- date: Show the current date and time");
					output.append("\n- reverse <text>: Reverse a string");
					output.append("\n- brightness <value>: Change screen brightness (0-255)");
					output.append("\n- neofetch: Display system information");
					output.append("\n- battery: Show battery status");
					output.append("\n- storage: Show storage information");
					output.append("\n- ram: Show RAM usage");
					output.append("\n- deviceinfo: Show detailed device info");
					output.append("\n- history: Show command history");
					output.append("\n- reboot: Restart the device");
					output.append("\n- shutdown: Shut down the device");
					output.append("\n- clear_cache: Clear cache memory");
					output.append("\n- logcat: Show system logs");
					output.append("\n- cpu: Show CPU information");
					output.append("\n- netstat: Show network statistics");
					output.append("\n- processes: Show running processes");
					output.append("\n- kill <PID>: Terminate a process");
					output.append("\n- temp: Show system temperature");
					output.append("\n- ping <host>: Check network connectivity");
					output.append("\n- sensors: Show sensor data");
					output.append("\n- uptime: Show system uptime");
					output.append("\n- hostname: Show device hostname");
					output.append("\n- whoami: Show current user");
					output.append("\n- id: Show user and group ID");
					output.append("\n- getprop <property>: Show system properties");
					output.append("\n- setprop <property> <value>: Change system property (root required)");
					output.append("\n- wm size: Show screen resolution");
					output.append("\n- wm density: Show or change screen DPI");
					output.append("\n- svc wifi enable/disable: Toggle WiFi");
					output.append("\n- svc data enable/disable: Toggle mobile data");
					output.append("\n- df -h: Show disk space usage");
					output.append("\n- ps -A: List running processes");
					output.append("\n- am force-stop <package>: Force stop an app");
					output.append("\n- pm list packages: List installed apps");
					output.append("\n- screenrecord <file>: Record the screen");
					output.append("\n- screencap <file>: Capture a screenshot");
					output.append("\n\nUse 'help <command>' for details.");
			        output.append("\n\n Warning: Some commands require root privileges!\n\n" + "Note: Not all commands are indexed here, and the \"help <command>\" function may not cover all command types.\n\n");
					return;
			}
			
			switch (parts[1].toLowerCase()) {
					case "calc":
					output.append("\n\nUsage: calc <num1> <operator> <num2>");
					output.append("\nOperators: +, -, *, /, ^, %, gcd, lcm, √, log, ln, !");
					output.append("\nExample: calc 5 + 3 -> Result: 8");
					break;
					
					case "matrix":
					output.append("\n\nUsage: matrix <operation> <size> <values...>");
					output.append("\nOperations: det (determinant), inverse");
					break;
					
					case "asciiart":
					output.append("\n\nUsage: asciiart <image-path> <style> <size>");
					output.append("\nDescription: Generates ASCII art from an image.");
					output.append("\nAvailable styles: symbol (default), braille, line, block, detailed.");
					output.append("\nSize: Adjusts the output resolution (10 - 300).");
					break;
					
					case "uptime":
					output.append("\n\nUsage: uptime");
					output.append("\nDescription: Displays how long the system has been running.");
					break;
					
					case "battery":
					output.append("\n\nUsage: dumpsys battery");
					output.append("\nDescription: Shows battery status and power details.");
					break;
					
					case "storage":
					output.append("\n\nUsage: df -h");
					output.append("\nDescription: Shows disk space usage.");
					break;
					
					case "ram":
					output.append("\n\nUsage: dumpsys meminfo");
					output.append("\nDescription: Displays RAM usage.");
					break;
					
					case "brightness":
					output.append("\n\nUsage: brightness <value>");
					output.append("\nDescription: Adjust screen brightness (0-255).");
					output.append("\nNote: Requires CHANGE-SYSTEM-SETTINGS permission.");
					break;
					
					case "hostname":
					output.append("\n\nUsage: hostname");
					output.append("\nDescription: Shows the device hostname.");
					break;
					
					case "whoami":
					output.append("\n\nUsage: whoami");
					output.append("\nDescription: Displays the current user.");
					break;
					
					case "id":
					output.append("\n\nUsage: id");
					output.append("\nDescription: Shows user and group IDs.");
					break;
					
					case "getprop":
					output.append("\n\nUsage: getprop <property>");
					output.append("\nDescription: Retrieves system property values.");
					break;
					
					case "setprop":
					output.append("\n\nUsage: setprop <property> <value>");
					output.append("\nDescription: Modifies a system property (root required).");
					break;
					
					case "netstat":
					output.append("\n\nUsage: netstat");
					output.append("\nDescription: Shows network statistics.");
					break;
					
					case "ping":
					output.append("\n\nUsage: ping <host>");
					output.append("\nDescription: Checks network connectivity.");
					break;
					
					case "svc":
					output.append("\n\nUsage: svc wifi/data enable/disable");
					output.append("\nDescription: Enables or disables WiFi or mobile data.");
					break;
					
					case "ps":
					output.append("\n\nUsage: ps -A");
					output.append("\nDescription: Lists running processes.");
					break;
					
					case "kill":
					output.append("\n\nUsage: kill <PID>");
					output.append("\nDescription: Terminates a process by its ID.");
					break;
					
					case "am":
					output.append("\n\nUsage: am force-stop <package>");
					output.append("\nDescription: Force stops an application.");
					break;
					
					case "pm":
					output.append("\n\nUsage: pm list packages");
					output.append("\nDescription: Lists installed applications.");
					break;
					
					case "screenrecord":
					output.append("\n\nUsage: screenrecord <file-path>");
					output.append("\nDescription: Records the screen and saves it to a file.");
					break;
					
					case "screencap":
					output.append("\n\nUsage: screencap <file-path>");
					output.append("\nDescription: Captures a screenshot.");
					break;
					
					default:
					output.append("\nError: Command not found.");
					break;
			}
	}
	
	
	public String startUp(Context context) {
		    StringBuilder stup = new StringBuilder();
		    
		    try {
			        PackageManager packageManager = context.getPackageManager();
			        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			        String packageName = packageInfo.packageName;
			        String versionName = packageInfo.versionName;
			
			        stup.append("Welcome to the Terminal!\n\n\n");
			        stup.append("Package Name       : ").append(packageName).append("\n");
			        stup.append("Version            : ").append(versionName).append("\n");
			        stup.append("Developer          : InfLps\n");
			        stup.append("Release Date       : February 2025\n\n");
			        stup.append("⚠ WARNING: This terminal release is not complete! ⚠\n\n");
			        stup.append("You may encounter bugs and incomplete features.\n\n");
			        stup.append("License            : Apache 2.0 License\n");
			        stup.append("Support            : inflps.official@gmail.com\n");
			        stup.append("Report issues at   : https://github.com/InfiniteLoops87/CEditor/issues\n\n");
			        stup.append("\nTip: Pinch and zoom to resize the console.\n");
			
			    } catch (PackageManager.NameNotFoundException e) {
			        return "Error: Startup processing failed!\nYou can still use the terminal, but some features may not work correctly.";
			    }
		    
		    return stup.toString();
	}
	private String runCommand(String cmd) throws IOException {
		Process process = Runtime.getRuntime().exec(cmd);
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		return reader.readLine();
	}
	
	
	public void _showKeyboard(final TextView _editText) {
		_editText.requestFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		if (imm != null) {
			imm.showSoftInput(_editText, InputMethodManager.SHOW_IMPLICIT);
		}
	}
	
	
	public void _hideKeyboard(final TextView _editText) {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		if (imm != null) {
			imm.hideSoftInputFromWindow(_editText.getWindowToken(), 0);
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
