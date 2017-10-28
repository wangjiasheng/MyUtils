package com.wjs.wenyan.myutils.demo;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wjs.view.MScrollPaginationView;
import com.wjs.view.MScrollPaginationView.ViewInitRequestListener;
import com.wjs.wenyan.myutils.R;

public class ScrollPaginationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrollpaginationviewlayout);
		MScrollPaginationView text=(MScrollPaginationView) findViewById(R.id.text);
		text.setPagerNumber(5);
		text.setView(R.layout.scrollpaginationviewlayoutitem);
		text.setOnViewInitRequestListener(new ViewInitRequestListener()
		{
			@Override
			public void onViewInitRequestListener(View view, int pagerNumber) 
			{
				TextView textview=(TextView) view.findViewById(R.id.textview);
				textview.setText("TextView"+pagerNumber);
			}
		});
	}
}
