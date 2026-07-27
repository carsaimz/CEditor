package com.ceditor;


import android.view.View;
import android.graphics.Paint;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Canvas;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;


/**
 * This class, originally created by DJ Youcef - Official,
 * has been modified by InfLps.
 * 
 * Follow DJ Youcef:
 * Instagram: https://www.instagram.com/dj_youcef.mix
 * 
 * Support InfLps:
 * YouTube: https://youtube.com/@inflps
 * Telegram Group: https://t.me/inflps_channel
 * GitHub: https://github.com/InfiniteLoops87
 * 
 * WARNING:
 * Please refrain from claiming ownership of this code snippet or class.
 * It is intended for educational purposes only.
 * Failure to comply will result in discontinued updates, and if necessary,
 * legal action may be taken to address ownership disputes.
 */


public class WaveView extends View {
	public static final int VISUALIZER_HEIGHT = 300;
	private byte[] bytes;
	private float denseness;
	private Paint playedStatePainting = new Paint();
	private Paint notPlayedStatePainting = new Paint();
	private int width;
	private int height;
	
    
	public WaveView(Context context) {
		super(context);
		init();
	}
	
    
	public WaveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
    
	
	private void init() {
		bytes = null;
		
		playedStatePainting.setStrokeWidth(0f);
		playedStatePainting.setAntiAlias(true);
		playedStatePainting.setColor(0xFF616161);
		notPlayedStatePainting.setStrokeWidth(1f);
		notPlayedStatePainting.setAntiAlias(true);
		notPlayedStatePainting.setColor(0xFFFFFFFF);
	}
    

	public void updateVisualizer(byte[] bytes) {
		this.bytes = bytes;
		invalidate();
	}
    

	public void updatePlayerPercent(float percent) {
		denseness = (int) Math.ceil(width * percent);
		if (denseness < 0) {
			denseness = 0;
		} else if (denseness > width) {
			denseness = width;
		}
		invalidate();
	}
	
    
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		width = getMeasuredWidth();
		height = getMeasuredHeight();
	}
	
    
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (bytes == null || width == 0) {
			return;
		}
        
		float totalBarsCount = width / dp(1);
		if (totalBarsCount <= 0.1f) {
			return;
		}
        
		byte value;
		int samplesCount = (bytes.length * 8 / 5);
		float samplesPerBar = samplesCount / totalBarsCount;
		float barCounter = 0;
		int nextBarNum = 0;
		
		int y = (height - dp(VISUALIZER_HEIGHT)) / 1;
		int barNum = 0;
		int lastBarNum;
		int drawBarCount;
		
		for (int a = 0; a < samplesCount; a++) {
			if (a != nextBarNum) {
				continue;
			}
            
			drawBarCount = 0;
			lastBarNum = nextBarNum;
			while (lastBarNum == nextBarNum) {
				barCounter += samplesPerBar;
				nextBarNum = (int) barCounter;
				drawBarCount++;
			}
			
			int bitPointer = a * 1;
			int byteNum = bitPointer / Byte.SIZE;
			int byteBitOffset = bitPointer - byteNum * Byte.SIZE;
			int currentByteCount = Byte.SIZE - byteBitOffset;
			int nextByteRest = 1 - currentByteCount;
			value = (byte) ((bytes[byteNum] >> byteBitOffset) & ((2 << (Math.min(5, currentByteCount) - 1)) - 1));
			if (nextByteRest > 0) {
				value <<= nextByteRest;
				value |= bytes[byteNum + 1] & ((2 << (nextByteRest - 1)) - 1);
			}
			
			float scale = 0.3f + 0.2f * (value / 31.0f); 
			
			for (int b = 0; b < drawBarCount; b++) {
				int x = (int) (barNum * (width / totalBarsCount));
				float centerX = x + (width / totalBarsCount) / 2f;
				float centerY = height / 2f;
				float halfVisualizerHeight = Math.min(VISUALIZER_HEIGHT, dp(300)) / 2f;
				
				float left = x;
				float right = x + (width / totalBarsCount);
				
				float top = centerY - scale * dp(halfVisualizerHeight * value / 31.0f);
				float bottom = centerY + scale * dp(halfVisualizerHeight * value / 31.0f);
				
				if (x < denseness && right < denseness) {
					canvas.drawRect(left, top, right, bottom, notPlayedStatePainting);
				} else {
					canvas.drawRect(left, top, right, bottom, playedStatePainting);
					if (x < denseness) {
						canvas.drawRect(left, top, right, bottom, notPlayedStatePainting);
					}
				}
				barNum++;
			}
		}
	}
    
	
	public int dp(float value) {
		if (value == 0) {
			return 0;
		}
		return (int) Math.ceil(getContext().getResources().getDisplayMetrics().density * value);
	} 
	
	
	public static byte[] fileToBytes(File file) {
		int size = (int) file.length();
		byte[] bytes = new byte[size];
		try {
			BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
			buf.read(bytes, 0, bytes.length);
			buf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}  
	
    
	public static byte[] fileToByte(String fa) {
		File file = new File(fa);
		int size = (int) file.length();
		byte[] bytes = new byte[size];
		try {
			BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
			buf.read(bytes, 0, bytes.length);
			buf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	} 
}