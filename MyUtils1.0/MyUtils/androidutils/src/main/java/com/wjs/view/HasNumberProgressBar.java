package com.wjs.view;
import com.wjs.utils.DensityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class HasNumberProgressBar extends View
{
	private Bitmap srollbar=null;
	/**
	 * ������
	 */
	private int srollbarBitmapwidth=0;
	/**
	 * ����߶�
	 */
	private int srollbarBitmapheight=0;
	/**
	 * �ؼ��߶�
	 */
	private int screenwidth=0;
	
	/**
	 * �������߶�
	 */
	private int progressHeight=0;
	/**
	 * ����������
	 */
	private RectF progressRect=null;
	/**
	 * ������ؽ���
	 */
	private RectF networkProgress=null;
	/**
	 * ��������
	 */
	private RectF progressBackground=null;
	/**
	 * ��ƫ����
	 */
	private float leftOffset=0;
	
	/**
	 * �������������ƫ����
	 */
	private float networkleftOffset=0;
	/**
	 * ������
	 */
	private float maxProgress=0;
	/**
	 * ��ǰ����
	 */
	private float currentProgress=0;
	/**
	 * ���Ȼص�
	 */
	private ProgressChange listener=null;
	/**
	 * ������������ɫ
	 */
	private int progressbackgroundcolor=Color.rgb(121, 113, 112);
	/**
	 * ��������ɫ
	 */
	private int progresscolor=Color.rgb(255, 204, 0);
	/**
	 * �������������ɫ
	 */
	private int networkprogresscolor=Color.WHITE;
	/**
	 * ����������
	 */
	private Paint progressRectpaint=null;
	/**
	 * ����������Ļ���
	 */
	private Paint networkRectpaint=null;
	/**
	 * ��������������
	 */
	private Paint progressBackgroundpaint=null;
	/**
	 * �����������ϵ����ֵĻ���
	 */
	private Paint progressTextpaint=null;
	/**
	 * �������Ļ���
	 */
	private Paint bitmapPaint=null;
	/**
	 * ������������
	 */
	private String progressbarText="0:00";
	/**
	 * �����������ֵĴ�С
	 */
	private int progressbarTextSize=0;
	/**
	 * �����������ֵ���ɫ
	 */
	private int progressbarTextColor=Color.BLACK;
	/**
	 * ����������Ľ���
	 */
	private float networkprogress=0;
	public interface ProgressChange
	{
		public void progress(int currentProgress, int maxProgress);
	}
	public HasNumberProgressBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	public HasNumberProgressBar(Context context) {
		super(context);
		init();
	}
	public HasNumberProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public void init()
	{
		progressRect=new RectF();
		progressBackground=new RectF();
		networkProgress=new RectF();
		progressRectpaint = new Paint();
		progressBackgroundpaint = new Paint();
		progressTextpaint=new Paint();
		bitmapPaint= new Paint();
		networkRectpaint=new Paint();
		progressHeight=(int) DensityUtils.px2dp(getContext(), 36);
		maxProgress=100;
		progressbarTextSize=(int) DensityUtils.px2sp(getContext(), 120);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if(srollbar!=null)
		{
			srollbarBitmapwidth=srollbar.getWidth();
			srollbarBitmapheight=srollbar.getHeight();
			screenwidth=getResources().getDisplayMetrics().widthPixels;
			int top=srollbarBitmapheight/2-progressHeight/2;
			
			
			progressRect.left=0;
			progressRect.right=0;
			progressRect.top=top;
			progressRect.bottom=top+progressHeight;
	
			
			progressBackground.left=0;
			progressBackground.right=screenwidth;
			progressBackground.top=top;
			progressBackground.bottom=top+progressHeight;

			
			networkProgress.left=0;
			networkProgress.right=0;
			networkProgress.top=top;
			networkProgress.bottom=top+progressHeight;
		}
		setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
	}
	int measureWidth(int width)
	{
		int result=0;
		int specMode=MeasureSpec.getMode(width);
		int specSize=MeasureSpec.getSize(width);
		if(specMode==MeasureSpec.EXACTLY)
		{
			result=specSize;
		}
		return result;
	}
	int measureHeight(int height)
	{
		int result=0;
		int specMode=MeasureSpec.getMode(height);
		int specSize=MeasureSpec.getSize(height);
		if(specMode==MeasureSpec.EXACTLY)
		{
			result=specSize;
		}
		else
		{
			result=srollbarBitmapheight;
			if(specMode==MeasureSpec.AT_MOST)
			{
				result=Math.min(result, specSize);
			}
		}
		return result;
	}
	@Override
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		if(srollbar!=null)
		{
			progressTextpaint.setTextSize(progressbarTextSize);
			progressTextpaint.setColor(progressbarTextColor);
			progressRectpaint.setColor(progresscolor);
			networkRectpaint.setColor(networkprogresscolor);
			progressBackgroundpaint.setColor(progressbackgroundcolor);
			leftOffset=(int)(((1.0*currentProgress)/maxProgress)*(screenwidth-srollbarBitmapwidth));
			networkleftOffset=(int)(((1.0*networkprogress)/maxProgress)*screenwidth);
			progressRect.right=leftOffset;
			networkProgress.right=networkleftOffset;
			canvas.drawRoundRect(progressBackground, 0f, 0f, progressBackgroundpaint);
			canvas.drawRoundRect(networkProgress, 0f, 0f, networkRectpaint);
			canvas.drawRoundRect(progressRect, 0f, 0f, progressRectpaint);
			canvas.drawBitmap(srollbar, leftOffset, 0,bitmapPaint);
			FontMetrics fm = progressTextpaint.getFontMetrics(); 
			float widthoffset=progressTextpaint.measureText(progressbarText);
			canvas.drawText(progressbarText,leftOffset+srollbarBitmapwidth/2-widthoffset/2, srollbarBitmapheight/2  - (fm.descent + fm.ascent) / 2, progressTextpaint);
		}
		else
		{
			canvas.drawColor(Color.RED);
		}
	}
	public void setProgress(float progress)
	{
		if(progress>=0&&maxProgress>=0)
		{
			if(progress>maxProgress)
			{
				progress=maxProgress;
			}
			currentProgress=progress;
			invalidate();
		}
	}
	public void setNetwrokProgress(float progress)
	{
		if(progress>=0&&maxProgress>=0)
		{
			if(progress>maxProgress)
			{
				progress=maxProgress;
			}
			networkprogress=progress;
			invalidate();
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		switch(event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				currentProgress=((1.0f*event.getX())/screenwidth)*maxProgress;
				if(listener!=null)
				{
					listener.progress(Math.round(currentProgress), Math.round(maxProgress));
				}
				break;
		}
		if(currentProgress<0.5)
		{
			currentProgress=0;
		}
		if(currentProgress>maxProgress-0.5)
		{
			currentProgress=maxProgress;
		}
		invalidate();
		return true;
	}
	public void setOnProgressBarChange(ProgressChange progresschangelistener)
	{
		this.listener=progresschangelistener;
	}
	public void setMaxProgress(float maxprogress)
	{
		this.maxProgress=maxprogress;
	}
	public void setProgressHeight(int progressHeight)
	{
		this.progressHeight=progressHeight;
	}
	public void setProgressBackgroundColor(int color)
	{
		this.progressbackgroundcolor=color;
	}
	public void setProgressColor(int color)
	{
		this.progresscolor=color;
	}
	public void setProgressBar(Bitmap bitmap)
	{
		srollbar=bitmap;
	}
	public void setProgressBarText(String str)
	{
		this.progressbarText=str;
	}
	public void setProgressBarTextSize(int textSize)
	{
		this.progressbarTextSize=textSize;
	}
	public void setProgressBarTextColor(int progressbarTextColor)
	{
		this.progressbarTextColor=progressbarTextColor;
	}
	public void setNetworkprogressColor(int networkprogresscolor)
	{
		this.networkprogresscolor=networkprogresscolor;
	}
}
