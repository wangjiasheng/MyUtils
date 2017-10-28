package com.wjs.wenyan.myutils.demo;

import android.app.Activity;
import android.os.Bundle;

import com.wjs.view.SingleProgress;
import com.wjs.wenyan.myutils.R;

public class SingleProgressActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singleprogressactivity);
		SingleProgress view = (SingleProgress) findViewById(R.id.singleprogress);
		view.setItemCount(5);
		view.setBodyDrable(getResources().getDrawable(R.drawable.xzlunbo), getResources().getDrawable(R.drawable.wxzlunbo));
		view.selectItem(2);
		view.setSingleProgressPadding(5);
		
		
		
	}
}
