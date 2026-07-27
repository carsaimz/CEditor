package com.ceditor;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;


public class ShimmerTextView extends TextView {
	private LinearGradient mLinearGradient;
	private Matrix mGradientMatrix;
	private int mViewWidth = 0;
	private Paint mPaint;
	private ValueAnimator shimmerAnimator;
	

	public ShimmerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		post(this::checkParentBackground);
	}

	
	private void checkParentBackground() {
		ViewGroup parent = (ViewGroup) getParent();
		if (parent != null) {
			int bgColor = Color.BLACK;
			Drawable background = parent.getBackground();
			if (background instanceof ColorDrawable) {
				bgColor = ((ColorDrawable) background).getColor();
			}
			
			if (isDark(bgColor)) {
				setupGradient(Color.DKGRAY, Color.WHITE, Color.DKGRAY);
			} else {
				setupGradient(Color.LTGRAY, Color.BLACK, Color.LTGRAY);
			}
		}
	}

	
	private boolean isDark(int color) {
		double brightness = (Color.red(color) * 0.299 +
		Color.green(color) * 0.587 +
		Color.blue(color) * 0.114);
		return brightness < 186;
	}

	
	private void setupGradient(int start, int middle, int end) {
		mPaint = getPaint();
		mLinearGradient = new LinearGradient(
		-mViewWidth, 0, 0, 0,
		new int[]{start, middle, end},
		new float[]{0, 0.5f, 1f},
		Shader.TileMode.CLAMP
		);
		mPaint.setShader(mLinearGradient);
		mGradientMatrix = new Matrix();
		setupAnimator();
	}
	
    
	private void setupAnimator() {
		if (shimmerAnimator != null) shimmerAnimator.cancel();
		
		shimmerAnimator = ValueAnimator.ofFloat(0, 2 * mViewWidth);
		shimmerAnimator.setDuration(1500);
		shimmerAnimator.setRepeatCount(ValueAnimator.INFINITE);
		shimmerAnimator.setInterpolator(new android.view.animation.LinearInterpolator());
		shimmerAnimator.addUpdateListener(animation -> {
			float translate = (float) animation.getAnimatedValue();
			if (mGradientMatrix != null && mLinearGradient != null) {
				mGradientMatrix.setTranslate(translate, 0);
				mLinearGradient.setLocalMatrix(mGradientMatrix);
				invalidate();
			}
		});
		shimmerAnimator.start();
	}
	
    
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if (w > 0 && mViewWidth == 0) {
			mViewWidth = w;
			post(this::checkParentBackground);
		}
	}
	
    
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if (shimmerAnimator != null) {
			shimmerAnimator.cancel();
			shimmerAnimator = null;
		}
	}
}