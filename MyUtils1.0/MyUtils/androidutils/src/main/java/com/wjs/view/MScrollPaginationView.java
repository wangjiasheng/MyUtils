package com.wjs.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
/**
 * @author 314232332
 * ������ҳ��ѯ
 */
public class MScrollPaginationView extends LinearLayout implements OnPageChangeListener
{
	private ViewInitRequestListener mViewInitRequestListener=null;
	List<View> list=null;
	LayoutInflater inflater=null;
	View myView=null;
	public int PagerNumber=4;
	private int layoutid;
	public interface ViewInitRequestListener
	{
		public void onViewInitRequestListener(View view, int pagerNumber);
	}
	/**
	 * @param mViewInitRequestListener �ص������������ڴ˴��õ�����Դ�Ĵ������ͷţ����Բ��ÿ����̵߳�˳��
	 */
	public void setOnViewInitRequestListener(ViewInitRequestListener mViewInitRequestListener) 
	{
		this.mViewInitRequestListener = mViewInitRequestListener;
		setUp();
	}
	public void setPagerNumber(int pagerNumber) 
	{
		PagerNumber = pagerNumber;
	}
	public MScrollPaginationView(Context context)
	{
		super(context);
		init(context);
	}
	public MScrollPaginationView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		init(context);
	}
	public void init(Context context)
	{
		myView=new ViewPager(context);
		LayoutParams params = (LayoutParams) getLayoutParams();
		if(params!=null)
		{
			LayoutParams layoutparams=new LayoutParams(params.width, params.height);
			myView.setLayoutParams(layoutparams);
		}
		((ViewPager)myView).setOnPageChangeListener(this);
		this.addView(myView);
		inflater=LayoutInflater.from(context);
	}
	public void setView(int layoutid)
	{
		this.layoutid=layoutid;
	}
	public void setUp()
	{
		list=new ArrayList<View>();
		for(int i=0;i<PagerNumber;i++)
		{
			try 
			{
				View view = inflater.inflate(layoutid,this,false);
				list.add(view);
			} 
			catch (Exception e) 
			{
			}
		}
		if(list.size()>0)
		{
			((ViewPager)myView).setAdapter(new mAdapter(list));
		}
		else
		{
			throw new IdIsNotLayoutException();
		}
	}
	class mAdapter extends PagerAdapter
	{
		private List<View> view=null;
		public mAdapter(List<View> view) 
		{
			this.view=view;
		}
		@Override
		public int getCount() 
		{
			return PagerNumber;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) 
		{
			View getView = view.get(position%4);
			container.removeView(getView);
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View getView = view.get(position%4);
			if(mViewInitRequestListener!=null)
			{
				mViewInitRequestListener.onViewInitRequestListener(getView, position);
			}
			container.addView(getView);
			return getView;
		}
	}
	@Override
	public void onPageScrollStateChanged(int arg0)
	{
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) 
	{
		
	}
	@Override
	public void onPageSelected(int arg0) 
	{
		
	}
	class IdIsNotLayoutException extends RuntimeException
	{
		private static final long serialVersionUID = -6418989022108332323L;

		public IdIsNotLayoutException() 
		{
			super("id����layout��id");
		}
	}
}
