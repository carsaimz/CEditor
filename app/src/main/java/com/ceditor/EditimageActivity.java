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
import com.robinhood.ticker.*;
import io.github.rosemoe.sora.*;
// import io.github.rosemoe.sora.langs.textmate.*; // Commented out due to missing language-textmate library
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
	private View cropImageView; // Placeholder for CropImageView
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
    
    // Placeholder for dv (DrawingView)
    private DrawingViewPlaceholder dv = new DrawingViewPlaceholder();
	
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
			}
		});
		
		rotate_right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				degree = degree + 90;
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
						Bitmap bm = null; 
						if (bm != null) {
                            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] imageBytes = baos.toByteArray();
                            image_string = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
                        }
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
                                        if (imageBytes != null && imageBytes.length > 0) {
                                            Bitmap resultBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                                            BitmapDrawable mBitmapDrawable = new BitmapDrawable(getResources(), resultBitmap);
                                            image.setBackground(mBitmapDrawable);
                                        }
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
				ratio_none.setTextColor(0xFFFF0000); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_1_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFF0000); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_3_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFF0000); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_3_4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFF0000); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_4_3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFF0000); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_4_5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFF0000); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFFFFFF); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_9_16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ratio_none.setTextColor(0xFFFFFFFF); ratio_1_1.setTextColor(0xFFFFFFFF); ratio_3_2.setTextColor(0xFFFFFFFF); ratio_3_4.setTextColor(0xFFFFFFFF); ratio_4_5.setTextColor(0xFFFFFFFF); ratio_4_3.setTextColor(0xFFFFFFFF); ratio_9_16.setTextColor(0xFFFF0000); ratio_16_9.setTextColor(0xFFFFFFFF);
			}
		});
		
		ratio_16_9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
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
                // Placeholder
            }
        });
	}
    
    private void initializeLogic() {}
    private void _ViewWidthHeight(View v, int w, int h) {}
    private void _SaveLinear(View v, String s) {}

    // Inner placeholder class for DrawingView
    private class DrawingViewPlaceholder {
        public void undo() {}
        public void redo() {}
        public void undoText() {}
        public void redoText() {}
        public void enablePaint() {}
    }
}
