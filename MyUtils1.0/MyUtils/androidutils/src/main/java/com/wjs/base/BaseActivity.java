package com.wjs.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.wjs.annotation.AutoRun;
import com.wjs.handler.DoubleClickExitHandler;
import com.wjs.manager.LayoutUtils;
public class BaseActivity extends Activity
{
	/**
	 * 判断是否为主界面，如果是那么双击退出
	 */
	private boolean isdoubleclickexit=false;
	/**
	 * handler处理机制，用来实现双击退出
	 */
	private DoubleClickExitHandler handler=null;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		BaseApplication.addActivity(this);
		LayoutUtils.inject(this);
	}
	public void setDoubleClickExit(boolean ismain)
	{
		if(ismain)
		{
			if(handler==null) {
				handler = new DoubleClickExitHandler(this);
			}
		}
		this.isdoubleclickexit=ismain;
	}
	@Override
	public void onBackPressed()
	{
		if(isdoubleclickexit)
		{
			handler.canExitApp();
		}
		else
		{
			super.onBackPressed();
		}
	}

	@Override
	protected void onDestroy() {
		BaseApplication.removeActivity(this);
		LayoutUtils.unInject(this);
		super.onDestroy();
	}
	@AutoRun
	public void testAutuRun()
	{
		Toast.makeText(this,"superAutoRun",Toast.LENGTH_SHORT).show();
	}
}
