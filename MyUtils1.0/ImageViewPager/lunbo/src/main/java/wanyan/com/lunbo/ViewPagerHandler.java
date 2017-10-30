package wanyan.com.lunbo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;

import java.lang.ref.WeakReference;

/**
 * Created by WJS on 2016/7/29.
 */
public class ViewPagerHandler extends Handler
{
    WeakReference<ViewPager> mWeek=null;
    private int mPhotoChangeTime=0;
    public ViewPagerHandler(Looper looper, ViewPager vipewpager, int mPhotoChangeTime)
    {
        super(looper);
        mWeek=new WeakReference<ViewPager>(vipewpager);
        this.mPhotoChangeTime=mPhotoChangeTime;
    }
    public void handleMessage(Message msg)
    {
        switch(msg.what)
        {
            case 1000:
                ViewPager mViewpager = mWeek.get();
                if(mViewpager!=null)
                {
                    int item = mViewpager.getCurrentItem();
                    int count=mViewpager.getAdapter().getCount();
                    int selectitem=(item+1)%count;
                    mViewpager.setCurrentItem(selectitem);
                    removeMessages(1000);
                    removeMessages(1000);
                    removeCallbacksAndMessages(null);
                    Message message=obtainMessage();
                    message.what=1000;
                    sendMessageDelayed(message,mPhotoChangeTime);
                }
                break;
        }
    };
};
