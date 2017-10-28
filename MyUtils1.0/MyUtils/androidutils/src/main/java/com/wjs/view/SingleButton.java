package com.wjs.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingleButton extends LinearLayout
{
	public interface SingleButtonClickListener
	{
		public void onClick(View view, int positeion);
	}
	SingleButtonClickListener listener=null;
	public static final int ButtonTag=100;
	public static final int SeparatorTag=200;
	public boolean issetSeparator=false;
	View selectItem=null;
	@SuppressLint("NewApi")
	public SingleButton(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init();
	}
	public SingleButton(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		init();
	}
	public SingleButton(Context context) 
	{
		super(context);
		init();
	}
	public void init()
	{
		this.setOrientation(HORIZONTAL);
	}
	public void setOnSingleButtonClickListener(SingleButtonClickListener listener)
	{
		this.listener=listener;
	}
	public void selectItem(int index)
	{
		int childCount = this.getChildCount();
		for(int i=0;i<childCount;i++)
		{
		    View childAt = this.getChildAt(i);
		    childAt.setEnabled(true);
		}
		if(index<childCount)
		{
			View view = this.getChildAt(index);
			view.setEnabled(false);
		}
	}
	public void selectItem(View view)
	{
		int childCount = this.getChildCount();
		for(int i=0;i<childCount;i++)
		{
		    View childAt = this.getChildAt(i);
		    childAt.setEnabled(true);
		}
		view.setEnabled(false);
	}
	public void setItems(String... str)
	{
		if(str==null)
			return;
		for(int i=0;i<str.length;i++)
		{
			TextView tv=new TextView(getContext());
			LayoutParams lp=new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
			tv.setGravity(Gravity.CENTER);
			tv.setLayoutParams(lp);
			tv.setText(str[i]);
			tv.setTag(ButtonTag);
			tv.setId(100000000+i);
			this.addView(tv);
			tv.setOnClickListener(new MyListener());
			if(i==0)
			{
				tv.setEnabled(false);
			}
			else
			{
				tv.setEnabled(true);
			}
		}
	}
	public void setSeparator(int layoutid)
	{
		if(!issetSeparator)
		{
			int viewcount=this.getChildCount();
			for(int i=1;i<viewcount;i++)
			{
				View view = LayoutInflater.from(getContext()).inflate(layoutid, null);
				LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
				view.setLayoutParams(lp);
				this.addView(view, 2*i-1);
			}
			issetSeparator=true;
		}
	}
	public void setTextColor(int color_select,int color_unselect)
	{
		int unenable=-android.R.attr.state_enabled;
		int enable=android.R.attr.state_enabled;
		int[][] colorstatus={{enable},{unenable}};
		int[] color={color_unselect,color_select};
		ColorStateList colorlist = new ColorStateList(colorstatus,color);
		int childCount = this.getChildCount();
	    for(int i=0;i<childCount;i++)
	    {
	    	View view = this.getChildAt(i);
	    	if(view.getTag()==(Object)ButtonTag)
	    	{
	    		TextView tv=(TextView)view;
	    		tv.setTextColor(colorlist);
	    	}
	    }
	}
	public void setBodyColor(int color_select,int color_unselect)
	{
		ColorDrawable cs=new ColorDrawable(color_select);
		ColorDrawable cu=new ColorDrawable(color_unselect);
		setBodyDrable(cs, cu);
	}
	public void setBodyDrable(Drawable drawable_select,Drawable drawable_unselect)
	{
		int unenable=-android.R.attr.state_enabled;
		int enable=android.R.attr.state_enabled;
	    int childCount = this.getChildCount();
	    for(int i=0;i<childCount;i++)
	    {
	    	View view = this.getChildAt(i);
	    	if(view.getTag()==(Object)ButtonTag)
	    	{
	    		TextView tv=(TextView)view;
	    		StateListDrawable drawable = new StateListDrawable();
	    		drawable.addState(new int[]{enable},drawable_unselect);
	    	    drawable.addState(new int[]{unenable},drawable_select);
	    		tv.setBackgroundDrawable(drawable);
	    		drawable.setCallback(tv);
	    	}
	    }
	}
	/**
	 * ���û����ĳ��Item �������������л�����Ҫ�ֶ�����selectComplete;
	 * @author 314232332
	 */
	class MyListener implements OnClickListener
	{
		@Override
		public void onClick(View arg0) 
		{
			if(listener!=null)
			{
				selectItem=arg0;
				listener.onClick(arg0,arg0.getId()-100000000);
			}
		}
	}
	public void selectComplete()
	{
		if(selectItem!=null)
		{
			selectItem(selectItem);
		}
	}
	public void setTextSize(int textsize)
	{
		int count = this.getChildCount();
		for(int i=0;i<count;i++)
		{
			View view = this.getChildAt(i);
			if(view instanceof TextView)
			{
				TextView tv=(TextView)view;
				tv.setTextSize(textsize);
			}
		}
	}
}
