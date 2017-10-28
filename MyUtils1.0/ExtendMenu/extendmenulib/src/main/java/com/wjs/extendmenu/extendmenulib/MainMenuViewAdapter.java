package com.wjs.extendmenu.extendmenulib;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

/**
 * Created by 家胜 on 2015/11/11.
 */
public class MainMenuViewAdapter extends PagerAdapter
{
  private List<GridView> mGridView=null;
   public MainMenuViewAdapter(List<GridView> mGridView)
   {
       this.mGridView=mGridView;
   }
    @Override
    public int getCount() {
     return mGridView.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
     return view==object;
    }

 @Override
 public Object instantiateItem(ViewGroup container, int position) {
  container.addView(mGridView.get(position));
  return mGridView.get(position);
 }

 @Override
 public void destroyItem(ViewGroup container, int position, Object object) {
  container.removeView(mGridView.get(position));
 }
}
