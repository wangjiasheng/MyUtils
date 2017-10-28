package com.wjs.view;

import com.wjs.view.SingleButton.SingleButtonClickListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingleProgress extends LinearLayout
{
	public static final int ButtonTag=100;
	public int currentSelectItem=0;
	Drawable drawable_select=null;
	Drawable drawable_unselect=null;
	private int singleprogresspadding;
	SingleProgressClickListener listener=null;
	@SuppressLint("NewApi")
	public SingleProgress(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init();
	}
	public interface SingleProgressClickListener
	{
		public void onClick(View view, int positeion);
	}
	public SingleProgress(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		init();
	}
	public SingleProgress(Context context) 
	{
		super(context);
		init();
	}
	public void init()
	{
		this.setOrientation(HORIZONTAL);
		this.setGravity(Gravity.CENTER);
	}
	public void setOnSingleButtonClickListener(SingleProgressClickListener listener)
	{
		this.listener=listener;
	}
	public void setItemCount(int count)
	{
		int childCount = this.getChildCount();
		int result=count-childCount;
		if(result>0)
		{
			for(int i=0;i<result;i++)
			{
				TextView tv=new TextView(getContext());
				tv.setTag(ButtonTag);
				tv.setId(100000000+i);
				tv.setOnClickListener(new SingleProgressOnclick());
				this.addView(tv);
				setBodyDrable();
				setBodyDrable();
			}
		}
		else
		{
			for(int i=0;i<-result;i++)
			{
				this.removeViewAt(0);
			}
		}
		if(currentSelectItem<this.getChildCount())
		{
			selectItem(currentSelectItem);
		}
		else
		{
			selectItem(this.getChildCount()-1);
		}
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
		currentSelectItem=index;
	}
	public void selectItem(View view)
	{
		int childCount = this.getChildCount();
		for(int i=0;i<childCount;i++)
		{
		    View childAt = this.getChildAt(i);
		    if(view==childAt)
		    {
		    	view.setEnabled(false);
		    	currentSelectItem=i;
		    }
		    else
		    {
		    	childAt.setEnabled(true);
		    }
		}
	}
	public void setBodyDrable(Drawable drawable_select,Drawable drawable_unselect)
	{
	    this.drawable_select=drawable_select;
	    this.drawable_unselect=drawable_unselect;
	    setBodyDrable();
	}
	private void setBodyDrable()
	{
		if(drawable_select!=null&&drawable_unselect!=null)
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
	}
	private void setPadding()
	{
		if(singleprogresspadding>0)
		for(int i=0;i<this.getChildCount();i++)
		{
			View view = this.getChildAt(i);
	    	if(view.getTag()==(Object)ButtonTag)
	    	{
	    		TextView tv=(TextView)view;
	    		LayoutParams layoutParams = (LayoutParams) tv.getLayoutParams();
	    		if(layoutParams==null)
	    		{
	    			layoutParams=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    		}
	    		layoutParams.leftMargin=singleprogresspadding;
	    		layoutParams.rightMargin=singleprogresspadding;
	    		tv.setLayoutParams(layoutParams);
	    	}
		}
	}
	class SingleProgressOnclick implements OnClickListener
	{
		@Override
		public void onClick(View arg0) 
		{
			if(listener!=null)
			{
				listener.onClick(arg0,arg0.getId()-100000000);
			}
			selectItem(arg0);
		}
	}
	public void setSingleProgressPadding(int padding)
	{
		this.singleprogresspadding=padding;
		setPadding();
	}
}
