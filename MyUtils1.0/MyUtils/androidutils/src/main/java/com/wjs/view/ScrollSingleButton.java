package com.wjs.view;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;
public class ScrollSingleButton extends SingleButton
{
	public ScrollSingleButton(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}
	public ScrollSingleButton(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
	}
	public ScrollSingleButton(Context context) 
	{
		super(context);
	}
	public void setItems(String... str)
	{
		if(str==null)
			return;
		for(int i=0;i<str.length;i++)
		{
			TextView tv=new TextView(getContext());
			//LayoutParams lp=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			tv.setGravity(Gravity.CENTER);
			//tv.setLayoutParams(lp);
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
}
