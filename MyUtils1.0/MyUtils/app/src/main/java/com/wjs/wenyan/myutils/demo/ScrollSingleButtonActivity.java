package com.wjs.wenyan.myutils.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.wjs.view.ScrollSingleButton;
import com.wjs.view.SingleButton.SingleButtonClickListener;
import com.wjs.wenyan.myutils.R;

public class ScrollSingleButtonActivity extends Activity implements SingleButtonClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.scrollsinglebuttonlayout);
		
		ScrollSingleButton view = (ScrollSingleButton) findViewById(R.id.singlebutton);
		view.setItems("�����Ļ�������","�Ļ�����","Ϊ�Ϸ���","�������");
		view.setTextColor(Color.BLACK,Color.BLACK);
		view.setBodyColor(Color.WHITE, Color.WHITE);
		view.setBodyDrable(getResources().getDrawable(R.drawable.menu_select), getResources().getDrawable(R.drawable.menu_unselect));
		view.setSeparator(R.layout.separator);
		view.setTextSize(18);
		view.setOnSingleButtonClickListener(this);
		
	}

	@Override
	public void onClick(View view, int positeion)
	{
		Log.i("wjs", positeion+"");
	}
}
