package com.wjs.wenyan.myutils.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wjs.bean.ActivityInfo;
import com.wjs.utils.ActivityUtils;
import com.wjs.wenyan.myutils.R;

import java.util.List;

public class MainActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
		ListView listview=(ListView) findViewById(R.id.listview);
	    List<ActivityInfo> list = ActivityUtils.queryActivitys(this, "android.intent.action.MAIN","com.wjs.test.activity");
	    Adapter adapter=new Adapter(this, list);
	    listview.setAdapter(adapter);
	    listview.setOnItemClickListener(this);
	}
	class Adapter extends BaseAdapter
	{
		List<ActivityInfo> list=null;
		LayoutInflater inflater=null;
		public Adapter(Context context,List<ActivityInfo> list) 
		{
			this.list=list;
			inflater=LayoutInflater.from(context);
		}
		@Override
		public int getCount() 
		{
			return list.size();
		}
		@Override
		public Object getItem(int position) 
		{
			return list.get(position);
		}
		@Override
		public long getItemId(int position)
		{
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			Holder holder=null;
			if(convertView==null)
			{
				holder=new Holder();
				convertView= inflater.inflate(R.layout.mainlayoutitem,parent,false);
				holder.imageview=(ImageView)convertView.findViewById(R.id.imageview);
				holder.textview=(TextView) convertView.findViewById(R.id.textview);
				convertView.setTag(holder);
			}
			else
			{
				holder=(Holder) convertView.getTag();
			}
			ActivityInfo activityInfo = list.get(position);
			holder.textview.setText(activityInfo.getActivityName());
			return convertView;
		}
	}
	class Holder
	{
		ImageView imageview=null;
		TextView textview=null;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) 
	{
		ActivityInfo item = (ActivityInfo) parent.getAdapter().getItem(position);
		Intent intent = item.getIntent();
		startActivity(intent);
	}
}
