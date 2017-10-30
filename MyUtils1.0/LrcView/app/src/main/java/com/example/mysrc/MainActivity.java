package com.example.mysrc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.List;

import com.wjs.lrclib.util.DensityUtils;
import com.wjs.lrclib.view.FIXLengthLrcBuilder;
import com.wjs.lrclib.view.ILrcView;
import com.wjs.lrclib.view.LrcRow;
import com.wjs.lrclib.view.LrcView;
import com.wjs.lrcviewlib.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;

public class MainActivity extends Activity implements ILrcView.LrcViewListener {
	Handler hander=new Handler()
	{
		public void handleMessage(android.os.Message msg) 
		{
			switch(msg.what)
			{
			case 100:
				view.seekLrcToTime(msg.arg1);
				break;
			}
		}
	};
	LrcView view ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 view = (LrcView) findViewById(R.id.srcview);
		view.setListener(this);
		view.setTextSize(DensityUtils.sp2px(this, 16));
		view.setSeekBarTextSize(DensityUtils.sp2px(this, 9));
		try {
			InputStream stream = getResources().getAssets().open("text.lrc");
			StringWriter sw=new StringWriter();
			InputStreamReader reader=new InputStreamReader(stream,"gbk");
			BufferedReader br=new BufferedReader(reader);
			String str;
			while((str=br.readLine())!=null)
			{
				sw.write(str);
				sw.write("\r\n");
			}
			br.close();
			reader.close();
			stream.close();
			sw.flush();
			String string = sw.toString();
			sw.close();
			
			
			FIXLengthLrcBuilder builder = new FIXLengthLrcBuilder();
			String strf=new String(string.getBytes(), "utf-8");
	         List<LrcRow> rows = builder.getLrcRows(strf,15,4*60*1000);
			view.setLrc(rows);
			view.seekLrcToTime(2*60*1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				for(int i=0;i<4*60*1000;i+=1000)
				{
					Message me=hander.obtainMessage();
					me.what=100;
					me.arg1=i;
					try {
						Thread.sleep(200);
						Log.i("wjs", i+"");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					hander.sendMessage(me);
				}
			}
		}).start();
	}
	@Override
	public void onLrcSeeked(int newPosition, LrcRow row)
	{
		
	}

	@Override
	public void onLrcViewScoll(int newPosition, LrcRow row, long currentTime) 
	{
		Log.i("wjs", ""+currentTime);
	}

}
