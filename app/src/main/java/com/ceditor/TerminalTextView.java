package com.ceditor;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;


public class TerminalTextView extends AppCompatTextView {
	private static final String TAG = "TerminalTextView";  
	
	private int selStart = -1;
	private int selEnd = -1;
	
	private Paint cursorPaint;

	
	public TerminalTextView(Context context) {  
		super(context);  
		init();
	}  

	
	public TerminalTextView(Context context, AttributeSet attrs) {  
		super(context, attrs);  
		init();
	}  

	
	public TerminalTextView(Context context, AttributeSet attrs, int defStyleAttr) {  
		super(context, attrs, defStyleAttr); 
		init();
	}  
	
    
	private void init() {
		cursorPaint = new Paint();
		cursorPaint.setColor(Color.BLACK);
		cursorPaint.setStrokeWidth(2f);
	}
	
    
	@Override  
	protected void onSelectionChanged(int start, int end) {  
		super.onSelectionChanged(start, end);  
		selStart = start;  
		selEnd = end;  
		Log.d(TAG, "Selection changed: start=" + selStart + ", end=" + selEnd);  
		invalidate();
	}  
	
    
	@Override  
	protected void onDraw(Canvas canvas) {  
		Layout layout = getLayout();  
		if (layout == null) {  
			super.onDraw(canvas);  
			return;  
		}  
		
		int paddingLeft = getPaddingLeft();  
		int paddingTop = getPaddingTop();  
        
		canvas.save();  
		canvas.translate(paddingLeft, paddingTop);  
		
		Paint textPaint = getPaint();  
		Paint highlightPaint = new Paint();  
		highlightPaint.setColor(Color.WHITE);  
		
		int selectionStart = Math.max(selStart, 0);  
		int selectionEnd = Math.max(selEnd, 0);  
		
		for (int line = 0; line < layout.getLineCount(); line++) {  
			int lineStartOffset = layout.getLineStart(line);  
			int lineEndOffset = layout.getLineEnd(line);  
			
			if (lineStartOffset >= selectionEnd || lineEndOffset <= selectionStart) {  
				drawPartialLine(canvas, layout, line, lineStartOffset, lineEndOffset, textPaint, false, null);  
			} else {  
				int selStartInLine = Math.max(lineStartOffset, selectionStart);  
				int selEndInLine = Math.min(lineEndOffset, selectionEnd);  
				
				if (selStartInLine > lineStartOffset) {  
					drawPartialLine(canvas, layout, line, lineStartOffset, selStartInLine, textPaint, false, null);  
				}  
				
				drawPartialLine(canvas, layout, line, selStartInLine, selEndInLine, textPaint, true, highlightPaint);  
				
				if (selEndInLine < lineEndOffset) {  
					drawPartialLine(canvas, layout, line, selEndInLine, lineEndOffset, textPaint, false, null);  
				}  
			}  
		}  
		canvas.restore();  
		
		if (selectionEnd < getText().length()) {
			float cursorX = layout.getPrimaryHorizontal(selectionEnd);
			float cursorY = layout.getLineBottom(layout.getLineForOffset(selectionEnd));
			canvas.drawLine(cursorX, cursorY - 10, cursorX, cursorY, cursorPaint);
		}
	}  

	
	private void drawPartialLine(Canvas canvas, Layout layout, int line, int start, int end, Paint paint, boolean isSelected, Paint highlightPaint) {
		if (start >= end) return;
		int contentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
		int lineStart = layout.getLineStart(line);
		int lineEnd = layout.getLineEnd(line);
		boolean isEntireLine = (start == lineStart && end == lineEnd);
		
		float left, right;
		if (isEntireLine) {
			left = 0;
			right = contentWidth;
		} else {
			left = layout.getPrimaryHorizontal(start);
			right = layout.getPrimaryHorizontal(end);
		}
		
		float top = layout.getLineTop(line);
		float bottom = layout.getLineBottom(line);
		
		if (isSelected && highlightPaint != null) {
			canvas.drawRect(left, top, right, bottom, highlightPaint);
		}
		
		int originalColor = paint.getColor();
		paint.setColor(isSelected ? Color.BLACK : getCurrentTextColor());
		
		if (isEntireLine) {
			
			String lineText = getText().subSequence(lineStart, lineEnd).toString();
			canvas.drawText(lineText, 0, bottom - paint.descent(), paint);
		} else {
			canvas.drawText(getText(), start, end, left, bottom - paint.descent(), paint);
		}
		
		paint.setColor(originalColor);
	}
}