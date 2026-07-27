/*
This class is created by InfLps. 8-15/02/2025
*/
package com.ceditor;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import android.widget.TextView;


public class RigidScrollView extends ScrollView {
    private int SCROLL_STEP = 48;
    private boolean isSnapping = true;
    private boolean isTouching = false;


    public RigidScrollView(Context context) {
        super(context);
    }


    public RigidScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public RigidScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isTouching = true;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isTouching = false;
                snapToNearestSmooth();
                break;
        }
        return super.onTouchEvent(ev);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (isSnapping && !isTouching) {
            snapToNearestImmediate();
        }
    }


    public void snapToNearestImmediate() {
        int currentY = getScrollY();
        int remainder = currentY % SCROLL_STEP;
        if (remainder != 0) {
            int newY = currentY - remainder;
            if (remainder > SCROLL_STEP / 2) {
                newY += SCROLL_STEP;
            }
            newY = Math.min(newY, getMaxScrollY());
            scrollTo(0, newY);
        }
    }


    public void snapToNearestSmooth() {
        int currentY = getScrollY();
        int remainder = currentY % SCROLL_STEP;
        if (remainder != 0) {
            int newY = currentY - remainder;
            if (remainder > SCROLL_STEP / 2) {
                newY += SCROLL_STEP;
            }
            newY = Math.min(newY, getMaxScrollY());
            smoothScrollTo(0, newY);
        }
    }


    private int getMaxScrollY() {
        if (getChildCount() == 0) return 0;
        return Math.max(0, getChildAt(0).getHeight() - getHeight());
    }


    public void setScrollStep(int step) {
        if (step > 0) {
            this.SCROLL_STEP = step;
            snapToNearestImmediate();
        }
    }


    public int getScrollStep() {
        return SCROLL_STEP;
    }


    public void setSnappingEnabled(boolean enabled) {
        this.isSnapping = enabled;
        if (enabled) snapToNearestImmediate();
    }


    public boolean isSnappingEnabled() {
        return isSnapping;
    }


    public void setTextView(TextView textView) {
        if (textView != null) {
            SCROLL_STEP = textView.getLineHeight();
            snapToNearestImmediate();
        }
    }
}