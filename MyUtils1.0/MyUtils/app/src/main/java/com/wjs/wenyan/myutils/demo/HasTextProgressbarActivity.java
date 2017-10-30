package com.wjs.wenyan.myutils.demo;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.wjs.view.HasNumberProgressBar;
import com.wjs.wenyan.myutils.R;

public class HasTextProgressbarActivity extends Activity
{
	HasNumberProgressBar mProgressbar=null;
	Handler handler=new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				case 100:
					mProgressbar.setNetwrokProgress(msg.arg1);
					break;
				}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hastextprogressbar);
		mProgressbar=(HasNumberProgressBar) findViewById(R.id.hasnumber);
		mProgressbar.setProgressBar(BitmapFactory.decodeResource(getResources(), R.drawable.srollbar));
		mProgressbar.setMaxProgress(100);
		mProgressbar.setProgress(0);
		new Thread()
		{
			public void run() 
			{
				for(int i=0;i<=100;i++)
				{
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Message message = handler.obtainMessage();
					message.what=100;
					message.arg1=i;
					handler.sendMessage(message);
				}
			};
		}.start();
	}
}
