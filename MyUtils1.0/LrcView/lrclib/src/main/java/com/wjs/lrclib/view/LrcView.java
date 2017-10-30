package com.wjs.lrclib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class LrcView extends View implements ILrcView{
	public final static String TAG = "LrcView";
	public final static int DISPLAY_MODE_NORMAL = 0;
	public final static int DISPLAY_MODE_SEEK = 1;
	public final static int DISPLAY_MODE_SCALE = 2;

	private List<LrcRow> mLrcRows; 	// all lrc rows of one lrc file
	private int mMinSeekFiredOffset = 10; // min offset for fire seek action, px;
	private int mHignlightRow = 0;   // current singing row , should be highlighted.
	private int mHignlightRowColor = Color.RED; 
	private int mNormalRowColor = Color.BLACK;
	private int mSeekLineColor = Color.CYAN;
	private int mSeekLineTextColor = Color.CYAN;
	private int mSeekLineTextSize = 15;
	private int mMinSeekLineTextSize = 13;
	private int mMaxSeekLineTextSize = 18;
	private int mLrcFontSize = 23; 	// font size of lrc
	private int mMinLrcFontSize = 15;
	private int mMaxLrcFontSize = 35;
	private int mPaddingY = 10;		// padding of each row
	private int mSeekLinePaddingX = 0; // Seek line padding x
	private int mDisplayMode = DISPLAY_MODE_NORMAL;
	private LrcViewListener mLrcViewListener;

	private String mLoadingLrcTip = "�������ظ��";

	private Paint mPaint;
	
	public LrcView(Context context,AttributeSet attr){
		super(context,attr);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setTextSize(mLrcFontSize);
	}
	public void setTextSize(int size)
	{
		mLrcFontSize=size;
	}
	public void setSeekBarTextSize(int size)
	{
		mSeekLineTextSize=size;
	}
	public void setListener(LrcViewListener l){
		mLrcViewListener = l;
	}
	
	public void setLoadingTipText(String text){
		mLoadingLrcTip = text;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		final int height = getHeight(); 
		final int width = getWidth() ; 
		if(mLrcRows == null || mLrcRows.size() == 0){
			if(mLoadingLrcTip != null){
				mPaint.setColor(mHignlightRowColor);
				mPaint.setTextSize(mLrcFontSize);
				mPaint.setTextAlign(Align.CENTER);
				canvas.drawText(mLoadingLrcTip, width / 2, height / 2 - mLrcFontSize, mPaint);
			}
			return;
		}

		int rowY = 0; 
		final int rowX = width / 2;
		int rowNum = 0;
		String highlightText = mLrcRows.get(mHignlightRow).content;
		int highlightRowY = height / 2 - mLrcFontSize;
		mPaint.setTextSize(mLrcFontSize+2);
		mPaint.setShadowLayer(1 ,1,1,Color.parseColor("#696969"));
		
		mPaint.setTextAlign(Align.CENTER);
		
		if(mDisplayMode==DISPLAY_MODE_SEEK)
		{
			mPaint.setColor(mHignlightRowColor);
			canvas.drawText(highlightText, rowX, highlightRowY, mPaint);
		}
		else
		{
			mPaint.setColor(mNormalRowColor);
			canvas.drawText(highlightText, rowX, highlightRowY, mPaint);
		}
		if(mDisplayMode == DISPLAY_MODE_SEEK)
		{
			mPaint.setColor(mSeekLineColor);
			canvas.drawLine(mSeekLinePaddingX, highlightRowY, width - mSeekLinePaddingX, highlightRowY, mPaint);
			mPaint.setColor(mSeekLineTextColor);
			mPaint.setTextSize(mSeekLineTextSize);
			mPaint.setTextAlign(Align.LEFT);
			canvas.drawText(mLrcRows.get(mHignlightRow).strTime, 0, highlightRowY, mPaint);
		}
		
		mPaint.setColor(mNormalRowColor);
		mPaint.setTextSize(mLrcFontSize);
		mPaint.setTextAlign(Align.CENTER);
		rowNum = mHignlightRow - 1;
		rowY = highlightRowY - mPaddingY - mLrcFontSize;
		while( rowY > -mLrcFontSize && rowNum >= 0){
			String text = mLrcRows.get(rowNum).content;
			canvas.drawText(text, rowX, rowY, mPaint);
			rowY -=  (mPaddingY + mLrcFontSize);
			rowNum --;
		}
		
		rowNum = mHignlightRow + 1;
		rowY = highlightRowY + mPaddingY + mLrcFontSize;
		while( rowY < height && rowNum < mLrcRows.size()){
			String text = mLrcRows.get(rowNum).content;
			canvas.drawText(text, rowX, rowY, mPaint);
			rowY += (mPaddingY + mLrcFontSize);
			rowNum ++;
		}
	}
	
	public void seekLrc(int position) {
	    if(mLrcRows == null || position < 0 || position > mLrcRows.size()) {
	        return;
	    }
		LrcRow lrcRow = mLrcRows.get(position);
		mHignlightRow = position;
		invalidate();
		if(mLrcViewListener != null){
			mLrcViewListener.onLrcSeeked(position, lrcRow);
		}
	}
	public void lrcViewSroll(int position) {
		if(mLrcRows == null || position < 0 || position > mLrcRows.size()) {
			return;
		}
		LrcRow lrcRow = mLrcRows.get(position);
		mHignlightRow = position;
		invalidate();
		if(mLrcViewListener != null){
			mLrcViewListener.onLrcViewScoll(position, lrcRow,LrcRow.timeConvert(lrcRow.strTime));
		}
	}
	
	private float mLastMotionY;
	private PointF mPointerOneLastMotion = new PointF();
	private PointF mPointerTwoLastMotion = new PointF();
	private boolean mIsFirstMove = false; 
										
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		if(mLrcRows == null || mLrcRows.size() == 0)
		{
			return super.onTouchEvent(event);
		}
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionY = event.getY();
			mIsFirstMove = true;
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			
			if(event.getPointerCount() == 2){
				doScale(event);
				return true;
			}

			if(mDisplayMode == DISPLAY_MODE_SCALE){
				return true;
			}
			
			doSeek(event);
			break;	
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			if(mDisplayMode == DISPLAY_MODE_SEEK){
				seekLrc(mHignlightRow);
			}
			if(mDisplayMode!=DISPLAY_MODE_SCALE)
			{
				//FIXME 
				if(mHignlightRow<mLrcRows.size())
				{
					lrcViewSroll(mHignlightRow);
				}
			}
			mDisplayMode = DISPLAY_MODE_NORMAL;
			invalidate();
			break;
		}
		return true;
	}

	private void doScale(MotionEvent event)
	{
		if(mDisplayMode == DISPLAY_MODE_SEEK)
		{
			mDisplayMode = DISPLAY_MODE_SCALE;
			return;
		}
		if(mIsFirstMove)
		{
			mDisplayMode = DISPLAY_MODE_SCALE;
			invalidate();
			mIsFirstMove = false;
			setTwoPointerLocation(event);
		}
		int scaleSize = getScale(event);
		if(scaleSize != 0)
		{
			setNewFontSize(scaleSize);
			invalidate();
		}		
		setTwoPointerLocation(event);
	}

	private void doSeek(MotionEvent event)
	{
		float y = event.getY();
		float offsetY = y - mLastMotionY;
		if(Math.abs(offsetY) < mMinSeekFiredOffset)
		{
			return;
		}
		mDisplayMode = DISPLAY_MODE_SEEK;
		int rowOffset = Math.abs((int) offsetY / mLrcFontSize); 
		if(offsetY < 0){
			mHignlightRow += rowOffset;
		}else if(offsetY > 0){
			mHignlightRow -= rowOffset;
		}
		mHignlightRow = Math.max(0, mHignlightRow);
		mHignlightRow = Math.min(mHignlightRow, mLrcRows.size() - 1);
		
		if(rowOffset > 0){
			mLastMotionY = y;
			invalidate();
		}
	}

	private void setTwoPointerLocation(MotionEvent event) {
		mPointerOneLastMotion.x = event.getX(0);
		mPointerOneLastMotion.y = event.getY(0);
		mPointerTwoLastMotion.x = event.getX(1);
		mPointerTwoLastMotion.y = event.getY(1);
	}
	
	private void setNewFontSize(int scaleSize){
		mLrcFontSize += scaleSize;
		mSeekLineTextSize += scaleSize;
		mLrcFontSize = Math.max(mLrcFontSize, mMinLrcFontSize);
		mLrcFontSize = Math.min(mLrcFontSize, mMaxLrcFontSize);
		mSeekLineTextSize = Math.max(mSeekLineTextSize, mMinSeekLineTextSize);
		mSeekLineTextSize = Math.min(mSeekLineTextSize, mMaxSeekLineTextSize);
	}
	private int getScale(MotionEvent event)
	{
		float x0 = event.getX(0);
		float y0 = event.getY(0);
		float x1 = event.getX(1);
		float y1 = event.getY(1);
		float maxOffset =  0;
		
		boolean zoomin = false; 
		
		float oldXOffset = Math.abs(mPointerOneLastMotion.x - mPointerTwoLastMotion.x);
		float newXoffset = Math.abs(x1 - x0);
		
		float oldYOffset = Math.abs(mPointerOneLastMotion.y - mPointerTwoLastMotion.y);
		float newYoffset = Math.abs(y1 - y0);
		
		maxOffset = Math.max(Math.abs(newXoffset - oldXOffset), Math.abs(newYoffset - oldYOffset));
		if(maxOffset == Math.abs(newXoffset - oldXOffset)){
			zoomin = newXoffset > oldXOffset ? true : false;
		}else{
			zoomin = newYoffset > oldYOffset ? true : false;
		}
		
		
		if(zoomin)
			return (int)(maxOffset / 10);
		else 
			return -(int)(maxOffset / 10);
	}

    public void setLrc(List<LrcRow> lrcRows) {
        mLrcRows = lrcRows;
        invalidate();
    }

    public void seekLrcToTime(long time) 
    {
        if(mLrcRows == null || mLrcRows.size() == 0) 
        {
            return;
        }
        
        if(mDisplayMode != DISPLAY_MODE_NORMAL) {
            return;
        }
        for(int i = 0; i < mLrcRows.size(); i++) 
        {
            LrcRow current = mLrcRows.get(i);
            LrcRow next = i + 1 == mLrcRows.size() ? null : mLrcRows.get(i + 1);
            
            if((time >= current.time && next != null && time < next.time) || (time > current.time && next == null)) 
            {
                seekLrc(i);
                return;
            }
        }
    }
}
