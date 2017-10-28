package com.wjs.view;

import com.wjs.utils.SharedPreferencesUtils;
import com.wjs.wenyan.androidutils.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class ToggleButton extends View
{
	private String TAG="ToggleButtonDefault";
	boolean select=false;
	private Bitmap setting_false_bg=null;
	private Bitmap setting_true_bg=null;
	ToggleButtonListener listener=null;
	public interface ToggleButtonListener
	{
		public void isSelect(ToggleButton togglebutton, boolean isselect);
	}
	public void init(Context context,AttributeSet attrs)
	{
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ToggleButton);  
		Drawable ToggleButton_selecticon = a.getDrawable(R.styleable.ToggleButton_selecticon);
		Drawable ToggleButton_unselecticon = a.getDrawable(R.styleable.ToggleButton_unselecticon);
		TAG = a.getInteger(R.styleable.ToggleButton_singlebuttonid, 0)+"";
        setting_false_bg = ((BitmapDrawable) ToggleButton_unselecticon).getBitmap();
    	setting_true_bg = ((BitmapDrawable) ToggleButton_selecticon).getBitmap();
        a.recycle();  
        init(context);
	}
    public void init(Context context)
    {
    	select = (Boolean) SharedPreferencesUtils.get(context, TAG, false);
    	setChecked(select);
    	this.setOnClickListener(new OnClickListener()
    	{
			@Override
			public void onClick(View arg0) 
			{
				setChecked(!select);
				if(listener!=null)
				{
					listener.isSelect(ToggleButton.this,select);
				}
			}
		});
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
    {
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
		else
		{
			result=(setting_false_bg.getWidth()<setting_true_bg.getWidth()?setting_false_bg.getWidth():setting_true_bg.getWidth())+getPaddingLeft()+getPaddingRight();
			if(specMode==MeasureSpec.AT_MOST)
			{
				result=Math.min(result, specSize);
			}
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
			result=(setting_false_bg.getHeight()<setting_true_bg.getHeight()?setting_false_bg.getHeight():setting_true_bg.getHeight())+getPaddingTop()+getPaddingBottom();
			if(specMode==MeasureSpec.AT_MOST)
			{
				result=Math.min(result, specSize);
			}
		}
		return result;
	}
	public ToggleButton(Context context) 
	{
		super(context);
		init(context);
	}
	public ToggleButton(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		init(context,attrs);
	}
	public ToggleButton(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init(context,attrs);
	}
	public void setChecked(boolean checked) 
	{
		if(select!=checked)
		{
			select=!select;
			SharedPreferencesUtils.put(getContext(), TAG, checked);
		}
		if(select)
		{
			setBackgroundDrawable(new BitmapDrawable(setting_true_bg));
		}
		else
		{
			setBackgroundDrawable(new BitmapDrawable(setting_false_bg));
		}
	}
    public void setOnToggleButtonListener(ToggleButtonListener listener)
    {
    	this.listener=listener;
    }
}
