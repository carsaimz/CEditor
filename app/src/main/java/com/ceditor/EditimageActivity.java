package com.ceditor;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;



public class EditimageActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private float topLeftRadius;
	private float topRightRadius;
	private float bottomRightRadius;
	private float bottomLeftRadius;
	private File imgFile;
	private static final String[] colors = {
			"#FFFFFF", "#FEC1BF", "#FFE1BD", "#FEFEBD", "#BDFFC3", "#C3FDFE", 
			"#BFC1FB", "#FEBEFE", "#E1E1E1", "#FC8282", "#BDFFC3", "#C3FDFE", 
			"#80FD85", "#87FCF9", "#7E7EF8", "#FA87EF", "#C0C0C0", "#F90104", 
			"#FE8101", "#FEF812", "#07FB0D", "#0DFBFB", "#0001FD", "#FE00F5",
			"#808080", "#BD0203", "#B93F01", "#C0C100", "#00C600", "#05C1B9", 
			"#0100C1", "#BC07B4", "#404040", "#860101", "#863C03", "#837E0C", 
			"#008302", "#008485", "#02027E", "#7F0282", "#000000", "#3E0100", 
			"#7D423E", "#3B4500", "#024202", "#073E41", "#010140", "#3F023E"
	};
	
	private ProgressDialog pd;
	private String imagePath = "";
	private String path = "";
	private double degree = 0;
	private String image_string = "";
	private double undoType = 0;
	private String currentColor = "";
	private double width = 0;
	private double alpha = 0;
	private boolean istext = false;
	private String parentPath = "";
	private String imageFileName = "";
	private String substringExt = "";
	
	private ArrayList<HashMap<String, Object>> psize = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> tsize = new ArrayList<>();
	
	private LinearLayout toolbar1;
	private FrameLayout linear1;
	private LinearLayout crop_mb;
	private LinearLayout main_mb;
	private LinearLayout draw_mb;
	private LinearLayout text_mb;
	private LinearLayout toolbar1_inner;
	private ImageView close;
	private LinearLayout toolbar1_info;
	private ImageView rotate_left;
	private ImageView rotate_right;
	private ImageView undo;
	private ImageView redo;
	private ImageView proceed;
	private ImageView download;
	private TextView title;
	private TextView subtitle;
	private CropImageView cropImageView;
	private FrameLayout linear7;
	private LinearLayout image;
	private LinearLayout drawView;
	private ImageView close_crop_mb;
	private TextView ratio_none;
	private TextView ratio_1_1;
	private TextView ratio_3_2;
	private TextView ratio_3_4;
	private TextView ratio_4_3;
	private TextView ratio_4_5;
	private TextView ratio_9_16;
	private TextView ratio_16_9;
	private LinearLayout crop_op;
	private LinearLayout draw_op;
	private LinearLayout text_op;
	private ImageView imageview4;
	private TextView textview1;
	private ImageView imageview5;
	private TextView textview2;
	private ImageView imageview6;
	private TextView textview3;
	private ImageView close_draw_mb;
	private ImageView pencil;
	private ImageView eraser;
	private LinearLayout linear38;
	private LinearLayout linear24;
	private TextView textview9;
	private LinearLayout linear39;
	private TextView paintsize_output;
	private ImageView up_paint;
	private LinearLayout black_paint;
	private LinearLayout white_paint;
	private LinearLayout blue_paint;
	private LinearLayout green_paint;
	private LinearLayout yellow_paint;
	private LinearLayout red_paint;
	private LinearLayout linear31;
	private LinearLayout more_colors_paint;
	private LinearLayout black_paint_inner;
	private ImageView close_text_mb;
	private EditText edittext1;
	private LinearLayout linear10;
	private LinearLayout linear23;
	private TextView textview4;
	private LinearLayout linear37;
	private TextView textsize_output;
	private ImageView up_text;
	private LinearLayout black_text;
	private LinearLayout white_text;
	private LinearLayout blue_text;
	private LinearLayout green_text;
	private LinearLayout orange_text;
	private LinearLayout red_text;
	private LinearLayout linear17;
	private LinearLayout more_colors_text;
	private LinearLayout black_text_inner;
	
	private TimerTask timer;
	private AlertDialog.Builder d;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.editimage);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		toolbar1 = findViewById(R.id.toolbar1);
		linear1 = findViewById(R.id.linear1);
		crop_mb = findViewById(R.id.crop_mb);
		main_mb = findViewById(R.id.main_mb);
		draw_mb = findViewById(R.id.draw_mb);
		text_mb = findViewById(R.id.text_mb);
		toolbar1_inner = findViewById(R.id.toolbar1_inner);
		close = findViewById(R.id.close);
		toolbar1_info = findViewById(R.id.toolbar1_info);
		rotate_left = findViewById(R.id.rotate_left);
		rotate_right = findViewById(R.id.rotate_right);
		undo = findViewById(R.id.undo);
		redo = findViewById(R.id.redo);
		proceed = findViewById(R.id.proceed);
		download = findViewById(R.id.download);
		title = findViewById(R.id.title);
		subtitle = findViewById(R.id.subtitle);
		cropImageView = findViewById(R.id.cropImageView);
		linear7 = findViewById(R.id.linear7);
		image = findViewById(R.id.image);
		drawView = findViewById(R.id.drawView);
		close_crop_mb = findViewById(R.id.close_crop_mb);
		ratio_none = findViewById(R.id.ratio_none);
		ratio_1_1 = findViewById(R.id.ratio_1_1);
		ratio_3_2 = findViewById(R.id.ratio_3_2);
		ratio_3_4 = findViewById(R.id.ratio_3_4);
		ratio_4_3 = findViewById(R.id.ratio_4_3);
		ratio_4_5 = findViewById(R.id.ratio_4_5);
		ratio_9_16 = findViewById(R.id.ratio_9_16);
		ratio_16_9 = findViewById(R.id.ratio_16_9);
		crop_op = findViewById(R.id.crop_op);
		draw_op = findViewById(R.id.draw_op);
		text_op = findViewById(R.id.text_op);
		imageview4 = findViewById(R.id.imageview4);
		textview1 = findViewById(R.id.textview1);
		imageview5 = findViewById(R.id.imageview5);
		textview2 = findViewById(R.id.textview2);
		imageview6 = findViewById(R.id.imageview6);
		textview3 = findViewById(R.id.textview3);
		close_draw_mb = findViewById(R.id.close_draw_mb);
		pencil = findViewById(R.id.pencil);
		eraser = findViewById(R.id.eraser);
		linear38 = findViewById(R.id.linear38);
		linear24 = findViewById(R.id.linear24);
		textview9 = findViewById(R.id.textview9);
		linear39 = findViewById(R.id.linear39);
		paintsize_output = findViewById(R.id.paintsize_output);
		up_paint = findViewById(R.id.up_paint);
		black_paint = findViewById(R.id.black_paint);
		white_paint = findViewById(R.id.white_paint);
		blue_paint = findViewById(R.id.blue_paint);
		green_paint = findViewById(R.id.green_paint);
		yellow_paint = findViewById(R.id.yellow_paint);
		red_paint = findViewById(R.id.red_paint);
		linear31 = findViewById(R.id.linear31);
		more_colors_paint = findViewById(R.id.more_colors_paint);
		black_paint_inner = findViewById(R.id.black_paint_inner);
		close_text_mb = findViewById(R.id.close_text_mb);
		edittext1 = findViewById(R.id.edittext1);
		linear10 = findViewById(R.id.linear10);
		linear23 = findViewById(R.id.linear23);
		textview4 = findViewById(R.id.textview4);
		linear37 = findViewById(R.id.linear37);
		textsize_output = findViewById(R.id.textsize_output);
		up_text = findViewById(R.id.up_text);
		black_text = findViewById(R.id.black_text);
		white_text = findViewById(R.id.white_text);
		blue_text = findViewById(R.id.blue_text);
		green_text = findViewById(R.id.green_text);
		orange_text = findViewById(R.id.orange_text);
		red_text = findViewById(R.id.red_text);
		linear17 = findViewById(R.id.linear17);
		more_colors_text = findViewById(R.id.more_colors_text);
		black_text_inner = findViewById(R.id.black_text_inner);
		d = new AlertDialog.Builder(this);
		
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
		rotate_left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				degree = degree - 90;
				cropImageView.rotateImage((int)degree);
			}
		});
		
		rotate_right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				degree = degree + 90;
				cropImageView.rotateImage((int)degree);
			}
		});
		
		undo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (undoType == 1) {
					dv.undo();
				} else if (undoType == 108) {
					dv.undoText();
				}
			}
		});
		
		redo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (undoType == 1) {
					dv.redo();
				} else if (undoType == 108) {
					dv.redoText();
				}
			}
		});
		
		proceed.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pd = new ProgressDialog(EditimageActivity.this);
				pd.setMessage("Cropping..");
				pd.setCancelable(false);
				pd.setCanceledOnTouchOutside(false);
				pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				pd.show();
				class send_image extends AsyncTask<Void, Void, Void> { 
					@Override
					protected Void doInBackground(Void... arg0) {
						java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
						Bitmap bm = cropImageView.getCroppedImage();
						bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
						byte[] imageBytes = baos.toByteArray();
						image_string = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
						return null;
					}


					protected void onPreExecute() {
						return ;
					}


					protected void onPostExecute(Void result) {
						timer = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										pd.dismiss();
										byte[] imageBytes = android.util.Base64.decode(image_string, android.util.Base64.DEFAULT);
										Bitmap resultBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
										BitmapDrawable mBitmapDrawable = new BitmapDrawable(getResources(), resultBitmap);
										image.setBackground(mBitmapDrawable);
										cropImageView.setVisibility(View.GONE);
										image.setVisibility(View.VISIBLE);
										_ViewWidthHeight(drawView, image.getWidth(), image.getHeight());
										proceed.setVisibility(View.GONE);
										download.setVisibility(View.VISIBLE);
										rotate_left.setVisibility(View.GONE);
										main_mb.setVisibility(View.VISIBLE);
										crop_mb.setVisibility(View.GONE);
										rotate_right.setVisibility(View.GONE);
									}
								});
							}
						};
						_timer.schedule(timer, (int)(1111));
						return ;
					}
				}


				new send_image().execute();
			}
		});
		
		download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_SaveLinear(linear7, parentPath.concat("/".concat(substringExt.concat("_(Edited).png"))));
			}
		});
		
		close_crop_mb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Animation alphaIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_in);
				Animation alphaOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_out);
				crop_mb.startAnimation(alphaOut);
				crop_mb.setVisibility(View.GONE);
				draw_mb.setVisibility(View.GONE);
				text_mb.setVisibility(View.GONE);
				main_mb.startAnimation(alphaIn);
				main_mb.setVisibility(View.VISIBLE);
				image.setVisibility(View.VISIBLE);
				cropImageView.setVisibility(View.GONE);
				download.setVisibility(View.VISIBLE);
				proceed.setVisibility(View.GONE);
				rotate_left.setVisibility(View.GONE);
				rotate_right.setVisibility(View.GONE);
			}
		});
		
		ratio_none.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cropImageView.setFixedAspectRatio(false);
				ratio_none.setTextColor(0xFFFF0000); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_1_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cropImageView.setAspectRatio(1, 1);
				cropImageView.setFixedAspectRatio(true);
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFF0000); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_3_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cropImageView.setAspectRatio(3, 2);
				cropImageView.setFixedAspectRatio(true);
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFF0000); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_3_4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cropImageView.setAspectRatio(3, 4);
				cropImageView.setFixedAspectRatio(true);
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFF0000); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_4_3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cropImageView.setAspectRatio(4, 3);
				cropImageView.setFixedAspectRatio(true);
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFF0000); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_4_5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cropImageView.setAspectRatio(4, 5);
				cropImageView.setFixedAspectRatio(true);
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFF0000); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_9_16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cropImageView.setAspectRatio(9, 16);
				cropImageView.setFixedAspectRatio(true);
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFF0000); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_16_9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cropImageView.setAspectRatio(16, 9);
				cropImageView.setFixedAspectRatio(true);
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFF0000);
			}
		});
		
		crop_op.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Animation alphaIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_in);
				Animation alphaOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_out);
				crop_mb.startAnimation(alphaIn);
				main_mb.startAnimation(alphaOut);
				main_mb.setVisibility(View.GONE);
				draw_mb.setVisibility(View.GONE);
				text_mb.setVisibility(View.GONE);
				crop_mb.setVisibility(View.VISIBLE);
				close_crop_mb.setVisibility(View.VISIBLE);
				cropImageView.setVisibility(View.VISIBLE);
				image.setVisibility(View.GONE);
				proceed.setVisibility(View.VISIBLE);
				download.setVisibility(View.GONE);
				rotate_left.setVisibility(View.VISIBLE);
				rotate_right.setVisibility(View.VISIBLE);
			}
		});
		
		draw_op.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Animation alphaIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_in);
				Animation alphaOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_out);
				draw_mb.startAnimation(alphaIn);
				main_mb.startAnimation(alphaOut);
				main_mb.setVisibility(View.GONE);
				draw_mb.setVisibility(View.VISIBLE);
				text_mb.setVisibility(View.GONE);
				crop_mb.setVisibility(View.GONE);
				undo.setVisibility(View.VISIBLE);
				redo.setVisibility(View.VISIBLE);
				dv.enablePaint();
				undoType = 1;
			}
		});
		
		text_op.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Animation alphaIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_in);
				Animation alphaOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_out);
				text_mb.startAnimation(alphaIn);
				main_mb.startAnimation(alphaOut);
				main_mb.setVisibility(View.GONE);
				draw_mb.setVisibility(View.GONE);
				text_mb.setVisibility(View.VISIBLE);
				crop_mb.setVisibility(View.GONE);
				undo.setVisibility(View.VISIBLE);
				redo.setVisibility(View.VISIBLE);
				undoType = 108;
				if (!edittext1.getText().toString().equals("")) {
					dv.setText(edittext1.getText().toString());
				}
			}
		});
		
		close_draw_mb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Animation alphaIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_in);
				Animation alphaOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_out);
				draw_mb.startAnimation(alphaOut);
				main_mb.startAnimation(alphaIn);
				main_mb.setVisibility(View.VISIBLE);
				draw_mb.setVisibility(View.GONE);
				text_mb.setVisibility(View.GONE);
				crop_mb.setVisibility(View.GONE);
				undo.setVisibility(View.GONE);
				redo.setVisibility(View.GONE);
				dv.disablePaint();
				dv.disableMove();
			}
		});
		
		pencil.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pencil.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
				eraser.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				dv.resetPaintColor(currentColor);
				dv.enablePaint();
			}
		});
		
		eraser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dv.setEraserMode();
				dv.enablePaint();
				pencil.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
				eraser.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
			}
		});
		
		linear38.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_showPopUp(false, up_paint);
			}
		});
		
		black_paint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#000000";
				mPaint.setColor(Color.parseColor(currentColor));
				dv.setPathOpacity((int)alpha);
				_colorSelection(false, 1);
			}
		});
		
		white_paint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#FFFFFF";
				mPaint.setColor(Color.parseColor(currentColor));
				dv.setPathOpacity((int)alpha);
				_colorSelection(false, 2);
			}
		});
		
		blue_paint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#006EFC";
				mPaint.setColor(Color.parseColor(currentColor));
				dv.setPathOpacity((int)alpha);
				_colorSelection(false, 3);
			}
		});
		
		green_paint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#00AB01";
				mPaint.setColor(Color.parseColor(currentColor));
				dv.setPathOpacity((int)alpha);
				_colorSelection(false, 4);
			}
		});
		
		yellow_paint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#FFEA00";
				mPaint.setColor(Color.parseColor(currentColor));
				dv.setPathOpacity((int)alpha);
				_colorSelection(false, 5);
			}
		});
		
		red_paint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#FF0000";
				mPaint.setColor(Color.parseColor(currentColor));
				dv.setPathOpacity((int)alpha);
				_colorSelection(false, 6);
			}
		});
		
		more_colors_paint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_colorpicker(false);
			}
		});
		
		close_text_mb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Animation alphaIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_in);
				Animation alphaOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_out);
				text_mb.startAnimation(alphaOut);
				main_mb.startAnimation(alphaIn);
				main_mb.setVisibility(View.VISIBLE);
				draw_mb.setVisibility(View.GONE);
				text_mb.setVisibility(View.GONE);
				crop_mb.setVisibility(View.GONE);
				undo.setVisibility(View.GONE);
				redo.setVisibility(View.GONE);
				dv.deactivateTextMode();
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!_charSeq.equals("")) {
					dv.setText(_charSeq);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_showPopUp(true, up_text);
			}
		});
		
		linear23.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		black_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#000000";
				dv.setTextColor(currentColor);
				_colorSelection(true, 1);
			}
		});
		
		white_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#FFFFFF";
				dv.setTextColor(currentColor);
				_colorSelection(true, 2);
			}
		});
		
		blue_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#006EFC";
				dv.setTextColor(currentColor);
				_colorSelection(true, 3);
			}
		});
		
		green_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#00AB01";
				dv.setTextColor(currentColor);
				_colorSelection(true, 4);
			}
		});
		
		orange_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#FF9800";
				dv.setTextColor(currentColor);
				_colorSelection(true, 5);
			}
		});
		
		red_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				currentColor = "#FF0000";
				dv.setTextColor(currentColor);
				_colorSelection(true, 6);
			}
		});
		
		more_colors_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_colorpicker(true);
			}
		});
	}
	
	private void initializeLogic() {
		
		imagePath = getIntent().getStringExtra("path");
		imgFile = new File(imagePath);
		parentPath = imgFile.getParent();
		imageFileName = getFileName(imagePath);
		if (imageFileName != null && imageFileName.contains(".")) {
			substringExt = imageFileName.substring(0, imageFileName.lastIndexOf('.'));
		}
		CropImageView cropImageView = (CropImageView) findViewById(R.id.cropImageView);
		cropImageView.setFixedAspectRatio(false);
		cropImageView.setGuidelines(CropImageView.Guidelines.ON); 
		cropImageView.setCropShape(CropImageView.CropShape.RECTANGLE);
		cropImageView.setScaleType(CropImageView.ScaleType.FIT_CENTER);
		cropImageView.setAutoZoomEnabled(true);
		cropImageView.setShowProgressBar(true);
		cropImageView.setCropRect(new Rect(0, 0, 800, 500));
		int rotation = cropImageView.getRotatedDegrees();
		java.io.File file = new java.io.File(imagePath);
		Uri uri = Uri.fromFile(file);
		cropImageView.setImageUriAsync(uri);
		if (imgFile.exists()) {
			Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
			BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), myBitmap);
			image.setBackground(bitmapDrawable);
			int imageWidth = myBitmap.getWidth();
			int imageHeight = myBitmap.getHeight();
			float aspectRatio = (float) imageHeight / (float) imageWidth;
			image.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
							image.getViewTreeObserver().removeOnGlobalLayoutListener(this);
							int layoutWidth = image.getWidth();
							int layoutHeight = (int) (layoutWidth * aspectRatio);
							ViewGroup.LayoutParams params = image.getLayoutParams();
							params.height = layoutHeight;
							image.setLayoutParams(params);
					}
			});
		}
		dv = new DrawingView(this);
		image.addView(dv);
		
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setFilterBitmap(true); 
		mPaint.setDither(true); 
		mPaint.setColor(Color.RED); 
		mPaint.setStyle(Paint.Style.STROKE); 
		mPaint.setStrokeJoin(Paint.Join.ROUND); 
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(12);
		
		dv.disablePaint();
		dv.disableMove();
		dv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		_ripple(close);
		_ripple(rotate_left);
		_ripple(rotate_right);
		_ripple(undo);
		_ripple(redo);
		_ripple(proceed);
		_ripple(download);
		_ripple(close_crop_mb);
		_ripple(ratio_none);
		_ripple(ratio_1_1);
		_ripple(ratio_3_2);
		_ripple(ratio_3_4);
		_ripple(ratio_4_3);
		_ripple(ratio_4_5);
		_ripple(ratio_9_16);
		_ripple(ratio_16_9);
		_ripple(crop_op);
		_ripple(draw_op);
		_ripple(text_op);
		_ripple(linear10);
		_ripple(linear38);
		_ripple(close_text_mb);
		_ripple(close_draw_mb);
		title.setText(imageFileName);
		subtitle.setText(parentPath);
		width = 12;
		alpha = 255;
		currentColor = "#FF0000";
		cropImageView.setVisibility(View.GONE);
		pencil.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
		crop_mb.setVisibility(View.GONE);
		draw_mb.setVisibility(View.GONE);
		text_mb.setVisibility(View.GONE);
		undo.setVisibility(View.GONE);
		redo.setVisibility(View.GONE);
		rotate_left.setVisibility(View.GONE);
		rotate_right.setVisibility(View.GONE);
		proceed.setVisibility(View.GONE);
		_setEllipsize(title);
		_setEllipsize(subtitle);
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
	
	
	@Override
	public void onBackPressed() {
		d.setTitle("Confirmation");
		d.setMessage("Abandon the changes?\n\n\nRemember: If you continue, afterward you will not be able to undo this action!");
		d.setPositiveButton("Abandon", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				finish();
				overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_top);
			}
		});
		d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				
			}
		});
		_CXYZ_dialog_theme(d);
	}
	public void _SaveLinear(final View _view, final String _path) {
		Bitmap image = Bitmap.createBitmap(_view.getWidth(), _view.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(image);
		android.graphics.drawable.Drawable bgDrawable = _view.getBackground();
		if (bgDrawable!=null) {
				bgDrawable.draw(canvas);
		} else{
				canvas.drawColor(Color.TRANSPARENT);
		};
		_view.draw(canvas);
		java.io.File pictureFile = new java.io.File(_path);
		if (pictureFile == null) {
				showMessage("Error creating media file, check storage permissions");
				return;
		};
		java.io.File outputFile = new java.io.File(_path);
		if (!outputFile.getParentFile().exists()) {
				    if (!outputFile.getParentFile().mkdirs()) {
						        showMessage("Error creating media file directory");
						        return;
						    }
		}
		
		try {
				    java.io.FileOutputStream fos = new java.io.FileOutputStream(outputFile);
				    image.compress(Bitmap.CompressFormat.PNG, 100, fos);
				    fos.close();
				    showMessage("Saved successfully");
		} catch (java.io.FileNotFoundException e) {
				    showMessage("File not found");
		} catch (java.io.IOException e) {
				    showMessage("Error accessing file");
		}
	}
	
	
	public void _ViewWidthHeight(final View _view, final double _w, final double _h) {
		_view.getLayoutParams().width = (int)_w;
		_view.getLayoutParams().height = (int)_h;
		_view.requestLayout();
	}
	
	
	public void _DrawingView() {
		/*This void is blank...
Below is the class for paint (PCD)*/
	}
	
	// Code/snippet created and provided by InfLps (25.10.2024)
	DrawingView dv;
	private Paint mPaint;
	private Canvas mCanvas;
	private ArrayList<PathPaint> all_paths = new ArrayList<>();
	private ArrayList<PathPaint> undonePaths = new ArrayList<>();
	private ArrayList<TextPaint> all_texts = new ArrayList<>();
	private ArrayList<TextPaint> undoneTexts = new ArrayList<>();
	
	public class DrawingView extends View {
			private Bitmap mBitmap;
			private Paint mBitmapPaint;
			Context context;
			private Paint circlePaint;
			private Path circlePath;
			private Path mPath;
			private boolean isPaintEnabled = true;
			private PointF StartPT = new PointF();
			private PointF DownPT = new PointF();
			private PointF originalPosition = new PointF();
			private boolean isMoveEnabled = false;
			private boolean isPanModeActivated = false;
			private Paint mTextPaint;
			private String mText = "";
			private boolean isTextMode = false;
			private PointF textPosition = new PointF(100, 100);
			
			public DrawingView(Context c) {
					super(c);
					context = c;
					mPath = new Path();
					mBitmapPaint = new Paint(Paint.DITHER_FLAG);
					circlePath = new Path();
					circlePaint = new Paint();
					circlePaint.setAntiAlias(true);
					circlePaint.setColor(Color.parseColor("#00FFC107"));
					circlePaint.setStyle(Paint.Style.STROKE); 
					circlePaint.setStrokeJoin(Paint.Join.ROUND); 
					circlePaint.setStrokeWidth(0f);
					originalPosition.set(getX(), getY());
					
					initPaint();
					initTextPaint();
			}
			
			private void initTextPaint() {
					mTextPaint = new Paint();
					mTextPaint.setColor(Color.BLACK);
					mTextPaint.setTextSize(50);
					mTextPaint.setStyle(Paint.Style.FILL);
					mTextPaint.setAntiAlias(true);
					mTextPaint.setTextAlign(Paint.Align.LEFT);
			}
			
			@Override
			protected void onSizeChanged(int w, int h, int oldw, int oldh) {
					super.onSizeChanged(w, h, oldw, oldh);
					mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
					mCanvas = new Canvas(mBitmap);
			}
			
			@Override
			protected void onDraw(Canvas canvas) {
					super.onDraw(canvas);
					canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint); 
					canvas.drawPath(mPath, mPaint); 
					canvas.drawPath(circlePath, circlePaint);
					for (TextPaint text : all_texts) {
							canvas.drawText(text.getText(), text.getPosition().x, text.getPosition().y, text.getPaint());
					}
					
					if (isPanModeActivated || isMoveEnabled) {
							drawDashLines(canvas);
					}
					invalidate();
			}
			
			private float mX, mY;
			private static final float TOUCH_TOLERANCE = 1;
			
			@Override
			public boolean onTouchEvent(MotionEvent event) {
					float x = event.getX();
					float y = event.getY();
					
					switch (event.getAction()) {
							case MotionEvent.ACTION_DOWN:
							if (isPaintEnabled) {
									mPath.reset();
									mPath.moveTo(x, y);
									mX = x;
									mY = y;
									invalidate();
							} else if (isTextMode) {
									textPosition.set(x, y);
									invalidate();
							} else if (isMoveEnabled) {
									StartPT.set(getX(), getY());
									DownPT.set(x, y);
							}
							break;
							case MotionEvent.ACTION_MOVE:
							if (isPaintEnabled) {
									float dx = Math.abs(x - mX);
									float dy = Math.abs(y - mY);
									if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
											mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
											mX = x;
											mY = y;
											circlePath.reset();
											circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
									}
									invalidate();
							} else if (isMoveEnabled) {
									PointF mv = new PointF(x - DownPT.x, y - DownPT.y);
									setX((int) (StartPT.x + mv.x));
									setY((int) (StartPT.y + mv.y));
							}
							break;
							case MotionEvent.ACTION_UP:
							if (isPaintEnabled) {
									mPath.lineTo(mX, mY);
									circlePath.reset();
									Path p = new Path(mPath);
									Paint pnt = new Paint(mPaint);
									all_paths.add(new PathPaint(p, pnt));
									mCanvas.drawPath(mPath, mPaint);
									mPath.reset();
									invalidate();
							} else if (isTextMode) {
									addTextToCanvas();
							}
							break;
					}
					return true;
			}
			
			public void setText(String text) {
					this.mText = text;
					isTextMode = true;
					invalidate();
			}
			
			private void addTextToCanvas() {
					if (!mText.isEmpty()) {
							TextPaint textPaint = new TextPaint(mText, new PointF(textPosition.x, textPosition.y), new Paint(mTextPaint));
							all_texts.add(textPaint);
							undoneTexts.clear();
							invalidate();
					}
			}
			
			private void deactivateTextMode() {
					isTextMode = false;
					invalidate();
			}
			
			public void undoText() {
					if (all_texts.size() > 0) {
							undoneTexts.add(all_texts.remove(all_texts.size() - 1));
							invalidate();
					}
			}
			
			public void redoText() {
					if (undoneTexts.size() > 0) {
							all_texts.add(undoneTexts.remove(undoneTexts.size() - 1));
							invalidate();
					}
			}
			
			public void setTextSize(float size) {
					mTextPaint.setTextSize(size);
					invalidate();
			}
			
			public void setTextColor(String colorCode) {
					int color = Color.parseColor(colorCode);
					mTextPaint.setColor(color);
					invalidate();
			}
			
			public void setTextOpacity(int alpha) {
					alpha = Math.max(0, Math.min(255, alpha));
					mTextPaint.setAlpha(alpha);
					invalidate();
			}
			
			public void setTextTypeface(Typeface typeface) {
					mTextPaint.setTypeface(typeface);
					invalidate();
			}
			
			public void setTextAlign(Paint.Align align) {
					mTextPaint.setTextAlign(align);
					invalidate();
			}
			
			public void setPathOpacity(int alpha) {
					alpha = Math.max(0, Math.min(255, alpha));
					mPaint.setAlpha(alpha);
					for (PathPaint pathPaint : all_paths) {
							pathPaint.getPaint().setAlpha(alpha);
					}
					invalidate();
			}
			
			public void activatePanMode() {
					isPanModeActivated = true;
					invalidate();
			}
			
			public void deactivatePanMode() {
					isPanModeActivated = false;
					invalidate();
			}
			
			public void enableMove() {
					isMoveEnabled = true;
					activatePanMode();
			}
			
			public void disableMove() {
					isMoveEnabled = false;
					deactivatePanMode();
			}
			
			public void resetPosition() {
					setX((int) originalPosition.x);
					setY((int) originalPosition.y);
			}
			
			private void initPaint() {
					mPaint = new Paint();
					mPaint.setColor(Color.BLACK);
					mPaint.setStrokeWidth(5.0f);
					mPaint.setStyle(Paint.Style.STROKE);
			}
			
			private void resetPaint() {
					initPaint();  
					invalidate(); 
			}
			
			public void setEraserMode() {
					mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
			}
			
			public void resetPaintColor(String colorCode) {
					mPaint.setXfermode(null);
					int color = Color.parseColor(colorCode);
					mPaint.setColor(color);
			}
			
			public void enablePaint() {
					isPaintEnabled = true;
			}
			
			public void disablePaint() {
					isPaintEnabled = false;
			}
			
			public void undo() {
					if (all_paths.size() > 0) {
							undonePaths.add(all_paths.remove(all_paths.size() - 1));
							redrawCanvas();
					}
			}
			
			public void redo() {
					if (undonePaths.size() > 0) {
							all_paths.add(undonePaths.remove(undonePaths.size() - 1));
							redrawCanvas();
					}
			}
			
			private void redrawCanvas() {
					mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
					
					for (PathPaint pp : all_paths) {
							mCanvas.drawPath(pp.getPath(), pp.getPaint());
					}
					
					invalidate();
			}
			
			private void drawDashLines(Canvas canvas) {
					Paint dashLinePaint = new Paint();
					dashLinePaint.setColor(Color.BLACK);
					dashLinePaint.setStrokeWidth(2);
					dashLinePaint.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0));
					canvas.drawLine(0, 0, getWidth(), 0, dashLinePaint);
					canvas.drawLine(0, getHeight(), getWidth(), getHeight(), dashLinePaint);
					canvas.drawLine(0, 0, 0, getHeight(), dashLinePaint);
					canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), dashLinePaint);
			}
	}
	
	public class PathPaint {
			Path p;
			Paint pnt;
			public PathPaint(Path p, Paint pnt) {
					this.p = p;
					this.pnt = pnt;
			}
			
			public Path getPath() {
					return p;
			}
			
			public Paint getPaint() {
					return pnt;
			}
	}
	
	public class TextPaint {
			private String text;
			private PointF position;
			private Paint paint;
			
			public TextPaint(String text, PointF position, Paint paint) {
					this.text = text;
					this.position = position;
					this.paint = paint;
			}
			
			public String getText() {
					return text;
			}
			
			public PointF getPosition() {
					return position;
			}
			
			public Paint getPaint() {
					return paint;
			}
			
			
	}
	
	
	public void _showPopUp(final boolean _textmode, final View _v) {
		View SizePopupView = getLayoutInflater().inflate(R.layout.seekbar_popup, null);
		
		final PopupWindow SizePopup = new PopupWindow(SizePopupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		
		LinearLayout background = SizePopupView.findViewById(R.id.background);
		TextView in = SizePopupView.findViewById(R.id.increase);
		TextView de = SizePopupView.findViewById(R.id.decrease);
		TextView in_a = SizePopupView.findViewById(R.id.increase_alpha);
		TextView de_a = SizePopupView.findViewById(R.id.decrease_alpha);
		TextView sw_val = SizePopupView.findViewById(R.id.sw_val);
		TextView a_val = SizePopupView.findViewById(R.id.a_val);
		TextView textview1 = SizePopupView.findViewById(R.id.textview1);
		TextView textview3 = SizePopupView.findViewById(R.id.textview3);
		SeekBar seekbar = SizePopupView.findViewById(R.id.seekbar);
		SeekBar seekbar_alpha = SizePopupView.findViewById(R.id.seekbar_alpha);
		ImageView p1 = SizePopupView.findViewById(R.id.p1);
		ImageView p2 = SizePopupView.findViewById(R.id.p2);
		ImageView p3 = SizePopupView.findViewById(R.id.p3);
		ImageView p4 = SizePopupView.findViewById(R.id.p4);
		ImageView p5 = SizePopupView.findViewById(R.id.p5);
		ImageView p6 = SizePopupView.findViewById(R.id.p6);
		ImageView p7 = SizePopupView.findViewById(R.id.p7);
		ImageView p8 = SizePopupView.findViewById(R.id.p8);
		ImageView p9 = SizePopupView.findViewById(R.id.p9);
		HorizontalScrollView hscroll1 = SizePopupView.findViewById(R.id.hscroll1);
		
		_gradDrawable("#141414", "#373737", 1, 15, 5, false, background);
		
		
		seekbar.setProgress((int)width);
		seekbar_alpha.setProgress((int)alpha);
		sw_val.setText(String.valueOf((long)(width)));
		a_val.setText(String.valueOf((long)(alpha)));
		if (_textmode) {
			textview1.setText("Text size:");
			hscroll1.setVisibility(View.GONE);
			textview3.setVisibility(View.GONE);
		}
		else {
			textview1.setText("Stroke width:");
			hscroll1.setVisibility(View.VISIBLE);
			textview3.setVisibility(View.VISIBLE);
		}
		seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
				final int seekbarnnb = _param2;
				if (_textmode) {
					dv.setTextSize(_param2);
				}
				else {
					mPaint.setStrokeWidth(_param2);
				}
				width = _param2;
				sw_val.setText(String.valueOf((long)(seekbar.getProgress())));
				a_val.setText(String.valueOf((long)(seekbar_alpha.getProgress())));
				alpha = _param2;
			}
			 @Override
			public void onStartTrackingTouch(SeekBar _param1) {
			}
			 @Override
			public void onStopTrackingTouch(SeekBar _param2) {
			}
		});
		seekbar_alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
				final int seekbar_alphannb = _param2;
				if (_textmode) {
					dv.setTextOpacity(_param2);
				}
				else {
					dv.setPathOpacity(_param2);
				}
			}
			 @Override
			public void onStartTrackingTouch(SeekBar _param1) {
			}
			 @Override
			public void onStopTrackingTouch(SeekBar _param2) {
			}
		});
		in.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!((seekbar.getProgress() == 255) || (seekbar.getProgress() > 255))) {
					seekbar.setProgress((int)seekbar.getProgress() + 1);
				}
				sw_val.setText(String.valueOf((long)(seekbar.getProgress())));
			}
		});
		in_a.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!((seekbar_alpha.getProgress() == 255) || (seekbar_alpha.getProgress() > 255))) {
					seekbar_alpha.setProgress((int)seekbar_alpha.getProgress() + 1);
				}
				a_val.setText(String.valueOf((long)(seekbar_alpha.getProgress())));
			}
		});
		de.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(seekbar.getProgress() == 0)) {
					seekbar.setProgress((int)seekbar.getProgress() - 1);
				}
				sw_val.setText(String.valueOf((long)(seekbar.getProgress())));
			}
		});
		de_a.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!(seekbar_alpha.getProgress() == 0)) {
					seekbar_alpha.setProgress((int)seekbar_alpha.getProgress() - 1);
				}
				a_val.setText(String.valueOf((long)(seekbar_alpha.getProgress())));
			}
		});
		p1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p1.setBackgroundColor(0xFF711A14); p2.setBackgroundColor(Color.TRANSPARENT); p3.setBackgroundColor(Color.TRANSPARENT); p4.setBackgroundColor(Color.TRANSPARENT); p5.setBackgroundColor(Color.TRANSPARENT); p6.setBackgroundColor(Color.TRANSPARENT); p7.setBackgroundColor(Color.TRANSPARENT); p8.setBackgroundColor(Color.TRANSPARENT); p9.setBackgroundColor(Color.TRANSPARENT);
				mPaint = new Paint(); mPaint.setColor(Color.parseColor(currentColor)); mPaint.setStrokeWidth((int)width); mPaint.setAlpha((int)alpha); mPaint.setStyle(Paint.Style.STROKE); mPaint.setStrokeCap(Paint.Cap.ROUND);
			}
		});
		p2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p1.setBackgroundColor(Color.TRANSPARENT); p2.setBackgroundColor(0xFF711A14); p3.setBackgroundColor(Color.TRANSPARENT); p4.setBackgroundColor(Color.TRANSPARENT); p5.setBackgroundColor(Color.TRANSPARENT); p6.setBackgroundColor(Color.TRANSPARENT); p7.setBackgroundColor(Color.TRANSPARENT); p8.setBackgroundColor(Color.TRANSPARENT); p9.setBackgroundColor(Color.TRANSPARENT);
				mPaint = new Paint(); mPaint.setColor(Color.parseColor(currentColor)); mPaint.setStrokeWidth((int)width); mPaint.setAlpha((int)alpha); mPaint.setStyle(Paint.Style.STROKE); mPaint.setStrokeCap(Paint.Cap.ROUND); mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
			}
		});
		p3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p1.setBackgroundColor(Color.TRANSPARENT); p2.setBackgroundColor(Color.TRANSPARENT); p3.setBackgroundColor(0xFF711A14); p4.setBackgroundColor(Color.TRANSPARENT); p5.setBackgroundColor(Color.TRANSPARENT); p6.setBackgroundColor(Color.TRANSPARENT); p7.setBackgroundColor(Color.TRANSPARENT); p8.setBackgroundColor(Color.TRANSPARENT); p9.setBackgroundColor(Color.TRANSPARENT);
				mPaint = new Paint(); mPaint.setColor(Color.parseColor(currentColor)); mPaint.setStrokeWidth((int)width); mPaint.setAlpha((int)alpha); mPaint.setStyle(Paint.Style.STROKE); mPaint.setStrokeCap(Paint.Cap.ROUND); mPaint.setShadowLayer(50, 20, 20, Color.BLACK);
			}
		});
		p4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p1.setBackgroundColor(Color.TRANSPARENT); p2.setBackgroundColor(Color.TRANSPARENT); p3.setBackgroundColor(Color.TRANSPARENT); p4.setBackgroundColor(0xFF711A14); p5.setBackgroundColor(Color.TRANSPARENT); p6.setBackgroundColor(Color.TRANSPARENT); p7.setBackgroundColor(Color.TRANSPARENT); p8.setBackgroundColor(Color.TRANSPARENT); p9.setBackgroundColor(Color.TRANSPARENT);
				mPaint = new Paint(); mPaint.setColor(Color.parseColor(currentColor)); mPaint.setStrokeWidth((int)width); mPaint.setAlpha((int)alpha); mPaint.setStyle(Paint.Style.STROKE); mPaint.setStrokeCap(Paint.Cap.ROUND); mPaint.setShadowLayer(1, 3, 35, Color.BLACK);
			}
		});
		p5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p1.setBackgroundColor(Color.TRANSPARENT); p2.setBackgroundColor(Color.TRANSPARENT); p3.setBackgroundColor(Color.TRANSPARENT); p4.setBackgroundColor(Color.TRANSPARENT); p5.setBackgroundColor(0xFF711A14); p6.setBackgroundColor(Color.TRANSPARENT); p7.setBackgroundColor(Color.TRANSPARENT); p8.setBackgroundColor(Color.TRANSPARENT); p9.setBackgroundColor(Color.TRANSPARENT);
				mPaint = new Paint(); mPaint.setColor(Color.parseColor(currentColor)); mPaint.setStrokeWidth((int)width); mPaint.setAlpha((int)alpha); mPaint.setStyle(Paint.Style.STROKE); mPaint.setStrokeCap(Paint.Cap.ROUND); float segmentLength = 70.0f; float deviation = 9.0f; mPaint.setPathEffect(new DiscretePathEffect(segmentLength, deviation));
			}
		});
		p6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p1.setBackgroundColor(Color.TRANSPARENT); p2.setBackgroundColor(Color.TRANSPARENT); p3.setBackgroundColor(Color.TRANSPARENT); p4.setBackgroundColor(Color.TRANSPARENT); p5.setBackgroundColor(Color.TRANSPARENT); p6.setBackgroundColor(0xFF711A14); p7.setBackgroundColor(Color.TRANSPARENT); p8.setBackgroundColor(Color.TRANSPARENT); p9.setBackgroundColor(Color.TRANSPARENT);
				mPaint = new Paint(); mPaint.setColor(Color.parseColor(currentColor)); mPaint.setStrokeWidth((int)width); mPaint.setAlpha((int)alpha); mPaint.setStyle(Paint.Style.STROKE); mPaint.setStrokeCap(Paint.Cap.ROUND); mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.OUTER));
			}
		});
		p7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p1.setBackgroundColor(Color.TRANSPARENT); p2.setBackgroundColor(Color.TRANSPARENT); p3.setBackgroundColor(Color.TRANSPARENT); p4.setBackgroundColor(Color.TRANSPARENT); p5.setBackgroundColor(Color.TRANSPARENT); p6.setBackgroundColor(Color.TRANSPARENT); p7.setBackgroundColor(0xFF711A14); p8.setBackgroundColor(Color.TRANSPARENT); p9.setBackgroundColor(Color.TRANSPARENT);
				mPaint = new Paint(); mPaint.setColor(Color.parseColor(currentColor)); mPaint.setStrokeWidth((int)width); mPaint.setAlpha((int)alpha); mPaint.setStyle(Paint.Style.STROKE); mPaint.setStrokeCap(Paint.Cap.ROUND); mPaint.setPathEffect(new DashPathEffect(new float[]{20, 50}, 0));
			}
		});
		p8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p1.setBackgroundColor(Color.TRANSPARENT); p2.setBackgroundColor(Color.TRANSPARENT); p3.setBackgroundColor(Color.TRANSPARENT); p4.setBackgroundColor(Color.TRANSPARENT); p5.setBackgroundColor(Color.TRANSPARENT); p6.setBackgroundColor(Color.TRANSPARENT); p7.setBackgroundColor(Color.TRANSPARENT); p8.setBackgroundColor(0xFF711A14); p9.setBackgroundColor(Color.TRANSPARENT);
				mPaint = new Paint(); mPaint.setColor(Color.parseColor(currentColor)); mPaint.setStrokeWidth((int)width); mPaint.setAlpha((int)alpha); mPaint.setStyle(Paint.Style.STROKE); mPaint.setStrokeCap(Paint.Cap.ROUND); mPaint.setMaskFilter(new EmbossMaskFilter(new float[]{1, 1, 1}, 0.2f, 5, 3));
			}
		});
		p9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				p1.setBackgroundColor(Color.TRANSPARENT); p2.setBackgroundColor(Color.TRANSPARENT); p3.setBackgroundColor(Color.TRANSPARENT); p4.setBackgroundColor(Color.TRANSPARENT); p5.setBackgroundColor(Color.TRANSPARENT); p6.setBackgroundColor(Color.TRANSPARENT); p7.setBackgroundColor(Color.TRANSPARENT); p8.setBackgroundColor(Color.TRANSPARENT); p9.setBackgroundColor(0xFF711A14);
				mPaint = new Paint(); mPaint.setColor(Color.parseColor(currentColor)); mPaint.setStrokeWidth((int)width); mPaint.setAlpha((int)alpha); mPaint.setStyle(Paint.Style.STROKE); mPaint.setStrokeCap(Paint.Cap.ROUND); mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
			}
		});
		SizePopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				width = seekbar.getProgress();
				alpha = seekbar_alpha.getProgress();
				if (_textmode) {
					textsize_output.setText(String.valueOf((long)(seekbar.getProgress())));
				}
				else {
					paintsize_output.setText(String.valueOf((long)(seekbar.getProgress())));
				}
			}
		});
		SizePopupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		int popupHeight = SizePopupView.getMeasuredHeight();
		SizePopup.setAnimationStyle(R.style.PopupAnimation_BottomRightScale);
		SizePopup.setBackgroundDrawable(new BitmapDrawable());
		if (_textmode) {
			SizePopup.showAsDropDown(findViewById(R.id.up_text), 0, -popupHeight);
		}
		else {
			SizePopup.showAsDropDown(findViewById(R.id.up_paint), 0, -popupHeight);
		}
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
	
	
	public void _setTooltipCompatText(final View _Widget, final String _Text) {
		androidx.appcompat.widget.TooltipCompat.setTooltipText(_Widget,_Text);
	}
	
	
	public void _colorpicker(final boolean _istext) {
		View colorPickerPopupView = getLayoutInflater().inflate(R.layout.colorpicker_popup, null);
		
		final PopupWindow colorPickerPopup = new PopupWindow(
		    colorPickerPopupView, 
		    ViewGroup.LayoutParams.WRAP_CONTENT, 
		    ViewGroup.LayoutParams.WRAP_CONTENT, 
		    true
		);
		
		TextView title = colorPickerPopupView.findViewById(R.id.dialog_tittle);
		LinearLayout background = colorPickerPopupView.findViewById(R.id.background);
		_gradDrawable("#141414", "#373737", 1, 15, 5, false, background);
		istext = _istext;
		View.OnClickListener clickListener = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
						colorPickerPopup.dismiss();
						handleOptionsForLinearLayout((int) v.getTag());
				}
		};
		
		for (int i = 1; i <= colors.length; i++) {
				int resID = getResources().getIdentifier("c" + i, "id", getPackageName());
				LinearLayout colorOption = colorPickerPopupView.findViewById(resID);
				if (colorOption != null) {
						colorOption.setTag(i);
						colorOption.setOnClickListener(clickListener);
				}
		}
		colorPickerPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
			}
		});
		colorPickerPopupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		int popupHeight = colorPickerPopupView.getMeasuredHeight();
		colorPickerPopup.setAnimationStyle(R.style.PopupAnimation_BottomRightScale);
		colorPickerPopup.setBackgroundDrawable(new BitmapDrawable());
		if (_istext) {
			colorPickerPopup.showAsDropDown(findViewById(R.id.more_colors_text), 0, -popupHeight);
		}
		else {
			colorPickerPopup.showAsDropDown(findViewById(R.id.more_colors_paint), 0, -popupHeight);
		}
	}
	
	private void handleOptionsForLinearLayout(int index) {
		    if (index < 1 || index > colors.length) return;
		    String selectedColor = colors[index - 1];
		    setColor(selectedColor);
		    _colorSelection(istext, 7);
	}
	
	private void setColor(String color) {
			if (istext) {
					dv.setTextColor(color);
			} else {
					mPaint.setColor(Color.parseColor(color));
			}
			currentColor = color;
	}
	
	
	public void _colorSelection(final boolean _t, final double _n) {
		if (_n == 1) {
			if (_t) {
				black_text.setScaleY((float)(1.15d));
				white_text.setScaleY((float)(1));
				blue_text.setScaleY((float)(1));
				green_text.setScaleY((float)(1));
				orange_text.setScaleY((float)(1));
				red_text.setScaleY((float)(1));
				more_colors_text.setScaleY((float)(1));
			}
			else {
				black_paint.setScaleY((float)(1.15d));
				white_paint.setScaleY((float)(1));
				blue_paint.setScaleY((float)(1));
				green_paint.setScaleY((float)(1));
				yellow_paint.setScaleY((float)(1));
				red_paint.setScaleY((float)(1));
				more_colors_paint.setScaleY((float)(1));
			}
		}
		else {
			if (_n == 2) {
				if (_t) {
					black_text.setScaleY((float)(1));
					white_text.setScaleY((float)(1.15d));
					blue_text.setScaleY((float)(1));
					green_text.setScaleY((float)(1));
					orange_text.setScaleY((float)(1));
					red_text.setScaleY((float)(1));
					more_colors_text.setScaleY((float)(1));
				}
				else {
					black_paint.setScaleY((float)(1));
					white_paint.setScaleY((float)(1.15d));
					blue_paint.setScaleY((float)(1));
					green_paint.setScaleY((float)(1));
					yellow_paint.setScaleY((float)(1));
					red_paint.setScaleY((float)(1));
					more_colors_paint.setScaleY((float)(1));
				}
			}
			else {
				if (_n == 3) {
					if (_t) {
						black_text.setScaleY((float)(1));
						white_text.setScaleY((float)(1));
						blue_text.setScaleY((float)(1.15d));
						green_text.setScaleY((float)(1));
						orange_text.setScaleY((float)(1));
						red_text.setScaleY((float)(1));
						more_colors_text.setScaleY((float)(1));
					}
					else {
						black_paint.setScaleY((float)(1));
						white_paint.setScaleY((float)(1));
						blue_paint.setScaleY((float)(1.15d));
						green_paint.setScaleY((float)(1));
						yellow_paint.setScaleY((float)(1));
						red_paint.setScaleY((float)(1));
						more_colors_paint.setScaleY((float)(1));
					}
				}
				else {
					if (_n == 4) {
						if (_t) {
							black_text.setScaleY((float)(1));
							white_text.setScaleY((float)(1));
							blue_text.setScaleY((float)(1));
							green_text.setScaleY((float)(1.15d));
							orange_text.setScaleY((float)(1));
							red_text.setScaleY((float)(1));
							more_colors_text.setScaleY((float)(1));
						}
						else {
							black_paint.setScaleY((float)(1));
							white_paint.setScaleY((float)(1));
							blue_paint.setScaleY((float)(1));
							green_paint.setScaleY((float)(1.15d));
							yellow_paint.setScaleY((float)(1));
							red_paint.setScaleY((float)(1));
							more_colors_paint.setScaleY((float)(1));
						}
					}
					else {
						if (_n == 5) {
							if (_t) {
								black_text.setScaleY((float)(1));
								white_text.setScaleY((float)(1));
								blue_text.setScaleY((float)(1));
								green_text.setScaleY((float)(1));
								orange_text.setScaleY((float)(1.15d));
								red_text.setScaleY((float)(1));
								more_colors_text.setScaleY((float)(1));
							}
							else {
								black_paint.setScaleY((float)(1));
								white_paint.setScaleY((float)(1));
								blue_paint.setScaleY((float)(1));
								green_paint.setScaleY((float)(1));
								yellow_paint.setScaleY((float)(1.15d));
								red_paint.setScaleY((float)(1));
								more_colors_paint.setScaleY((float)(1));
							}
						}
						else {
							if (_n == 6) {
								if (_t) {
									black_text.setScaleY((float)(1));
									white_text.setScaleY((float)(1));
									blue_text.setScaleY((float)(1));
									green_text.setScaleY((float)(1));
									orange_text.setScaleY((float)(1));
									red_text.setScaleY((float)(1.15d));
									more_colors_text.setScaleY((float)(1));
								}
								else {
									black_paint.setScaleY((float)(1));
									white_paint.setScaleY((float)(1));
									blue_paint.setScaleY((float)(1));
									green_paint.setScaleY((float)(1));
									yellow_paint.setScaleY((float)(1));
									red_paint.setScaleY((float)(1.15d));
									more_colors_paint.setScaleY((float)(1));
								}
							}
							else {
								if (_n == 7) {
									if (_t) {
										black_text.setScaleY((float)(1));
										white_text.setScaleY((float)(1));
										blue_text.setScaleY((float)(1));
										green_text.setScaleY((float)(1));
										orange_text.setScaleY((float)(1));
										red_text.setScaleY((float)(1));
										more_colors_text.setScaleY((float)(1.15d));
									}
									else {
										black_paint.setScaleY((float)(1));
										white_paint.setScaleY((float)(1));
										blue_paint.setScaleY((float)(1));
										green_paint.setScaleY((float)(1));
										yellow_paint.setScaleY((float)(1));
										red_paint.setScaleY((float)(1));
										more_colors_paint.setScaleY((float)(1.15d));
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	public void _setEllipsize(final TextView _textview) {
		_textview.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		_textview.setMarqueeRepeatLimit(-1);
		_textview.setHorizontallyScrolling(true);
		_textview.setSelected(true);
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
	
	
	public void _AppDesignerToast(final String _s) {
		// Code generated by App Designer
			DisplayMetrics screen = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(screen);
			double dp = 10;
			double logicalDensity = screen.density;
			int px = (int) Math.ceil(dp * logicalDensity);
			
			Toast customToast = Toast.makeText(EditimageActivity.this, _s, Toast.LENGTH_SHORT);
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
