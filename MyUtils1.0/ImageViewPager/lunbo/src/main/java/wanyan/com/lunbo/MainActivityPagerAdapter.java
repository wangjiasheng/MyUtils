package wanyan.com.lunbo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by WJS on 2016/10/14.
 */

public class MainActivityPagerAdapter extends PagerAdapter
{
    List<ImageView> mList=null;
    public MainActivityPagerAdapter(List<ImageView> list)
    {
        this.mList=list;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        container.addView(mList.get(position));
        return mList.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView(mList.get(position));
    }
    @Override
    public int getCount()
    {
        return mList.size();
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0==arg1;
    }
}