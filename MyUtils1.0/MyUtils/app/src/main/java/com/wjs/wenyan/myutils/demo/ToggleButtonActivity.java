package com.wjs.wenyan.myutils.demo;

import android.app.Activity;
import android.widget.Toast;

import com.wjs.view.ToggleButton;
import com.wjs.view.ToggleButton.ToggleButtonListener;
import com.wjs.wenyan.myutils.R;

public class ToggleButtonActivity extends Activity
{
	protected void onCreate(android.os.Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		/**
		 * ToggleButton����һ��Id�����������ĸ���ť
		 * �� Ҫʹ�ö��Togglebuttonÿ����Ҫ���Լ���id
		 */
		setContentView(R.layout.togglebuttonlayout);
		ToggleButton mBtnPush = (ToggleButton) findViewById(R.id.btnPush);
		mBtnPush.setOnToggleButtonListener(new ToggleButtonListener() 
		{
			@Override
			public void isSelect(ToggleButton togglebutton, boolean isselect) 
			{
				Toast.makeText(ToggleButtonActivity.this, ""+isselect, Toast.LENGTH_SHORT).show();
			}
		});
		
		
		
	}
}
