package com.wjs.wenyan.myutils.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

import com.wjs.view.ProgressWheel;
import com.wjs.wenyan.myutils.R;

public class ProgressWheelActivity extends Activity implements OnClickListener {
	Handler handler=new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				case 1000:
					pw.setProgress(msg.arg1);
					break;
			}
		};
	};
	ProgressWheel pw=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progresswheel);
		pw=(ProgressWheel) findViewById(R.id.progress);
		
		findViewById(R.id.reset).setOnClickListener(this);
		findViewById(R.id.start).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{
			case R.id.reset:
				pw.setProgress(0);
				break;
			case R.id.start:
				new Thread()
				{
					public void run()
					{
						for(int i=0;i<=100;i++)
						{
							try {
								sleep(50);
								Message message = handler.obtainMessage();
								message.what=1000;
								message.arg1=i;
								handler.sendMessage(message);
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							}
							
						}
					};
				}.start();
				break;
		}
	}
}
