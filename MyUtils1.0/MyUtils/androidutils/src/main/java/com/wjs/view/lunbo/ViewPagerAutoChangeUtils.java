package com.wjs.view.lunbo;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import com.wjs.view.SingleProgress;

import java.lang.reflect.Field;
import java.util.List;

public class ViewPagerAutoChangeUtils implements ViewPager.OnPageChangeListener, SingleProgress.SingleProgressClickListener
{
	ViewPagerHandler mHandler=null;
	int mPhotoChangeTime=0;
	SingleProgress mSingleProgress;
	ViewPager viewPager;
	public ViewPagerAutoChangeUtils(Context context, Looper looper, ViewPager viewpager, SingleProgress progress, List<ImageView> viewlist, int mPhotoChangeTime, boolean jiasu, @DrawableRes int loadsucess, @DrawableRes int loadfailt)
	{
		this.viewPager=viewpager;
		mSingleProgress=progress;
		viewpager.setOnPageChangeListener(this);
		mSingleProgress.setOnSingleButtonClickListener(this);
		initProgress(context,viewpager,progress,viewlist,loadsucess,loadfailt);
		viewpager.setAdapter(new MainActivityPagerAdapter(viewlist));
		mHandler=new ViewPagerHandler(looper,viewpager,mPhotoChangeTime);
		this.mPhotoChangeTime=mPhotoChangeTime;
		setUp(viewpager,jiasu);
	}
	public void setAdapter(ViewPager viewpager, List<ImageView> viewlist)
	{
		viewpager.setAdapter(new MainActivityPagerAdapter(viewlist));
	}
	public void initProgress(Context context, ViewPager viewpager, SingleProgress progress, List<ImageView> viewlist, @DrawableRes int loadsucess, @DrawableRes int loadfailt)
	{
		mSingleProgress.setItemCount(viewlist.size());
		mSingleProgress.setBodyDrable(context.getResources().getDrawable(loadsucess), context.getResources().getDrawable(loadfailt));
		mSingleProgress.selectItem(0);
		mSingleProgress.setSingleProgressPadding(6);
	}
	public void setUp(ViewPager viewpager, boolean jiasu)
	{
		viewpager.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				switch(event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						mHandler.removeMessages(1000);
						break;
					case MotionEvent.ACTION_UP:
						Message message1=mHandler.obtainMessage();
						message1.what=1000;
						mHandler.sendMessageDelayed(message1,mPhotoChangeTime);
					case MotionEvent.ACTION_CANCEL:
						Message message=mHandler.obtainMessage();
						message.what=1000;
						mHandler.sendMessageDelayed(message,mPhotoChangeTime);
						break;
				}
				return false;
			}
		});
		if(jiasu)
		{
			try {
				Field mScroller = null;
				mScroller = ViewPager.class.getDeclaredField("mScroller");
				mScroller.setAccessible(true);
				ViewPagerScroller scroller = new ViewPagerScroller(viewpager.getContext());
				mScroller.set(viewpager, scroller);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	public void onResume() 
	{
		Message message=mHandler.obtainMessage();
		message.what=1000;
		mHandler.sendMessageDelayed(message,mPhotoChangeTime);
	}
	public void onPause() 
	{
		mHandler.removeCallbacksAndMessages(null);
	}
	public void onDestroy()
	{
		mHandler.removeMessages(1000);
		mHandler.removeCallbacksAndMessages(null);
		mHandler=null;
		viewPager.setOnPageChangeListener(null);
		mSingleProgress.setOnSingleButtonClickListener(null);
		mSingleProgress=null;
		viewPager=null;
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		mSingleProgress.selectItem(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	@Override
	public void onClick(View view, int i) {
		viewPager.setCurrentItem(i);
	}
}