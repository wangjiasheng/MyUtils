package com.wjs.extendmenu.extendmenulib;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 家胜 on 2016/1/20.
 */
public class ExtendMenu extends ViewPager implements AdapterView.OnItemClickListener {
    private ExtendMenuInter listener;
    public interface ExtendMenuInter
    {
        public void onClick(MainMenu menu);
    }
    public ExtendMenu(Context context)
    {
        super(context);
    }
    public ExtendMenu(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    private List<GridView> createMenuList(List<MainMenu> mMainMenu)
    {
        List<GridView> mGridViewList = new ArrayList<GridView>();
        for (int i = 0; i < ((mMainMenu.size() % 4) != 0 ? (mMainMenu.size() / 4) + 1 : mMainMenu.size() / 4); i++) {
            List<MainMenu> list = new ArrayList<MainMenu>();
            for (int j = 4 * i; j < 4 * (i + 1) && j < mMainMenu.size(); j++) {
                list.add(mMainMenu.get(j));
            }
            MainMunuAdapter adapter = new MainMunuAdapter(getContext(), list);
            GridView mGridView = new GridView(getContext());
            mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
            mGridView.setNumColumns(4);
            mGridView.setGravity(Gravity.CENTER);
            mGridView.setAdapter(adapter);
            mGridView.setOnItemClickListener(this);
            mGridViewList.add(mGridView);
        }
        return mGridViewList;
    }
    public void setData(List<MainMenu> mMainMenu)
    {
        MainMenuViewAdapter adapter = new MainMenuViewAdapter( createMenuList(mMainMenu));
        setAdapter(adapter);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        MainMenu menu = (MainMenu) parent.getAdapter().getItem(position);
        if(listener!=null)
        {
            listener.onClick(menu);
        }
    }
    public void setOnClickListener(ExtendMenuInter listener)
    {
        this.listener=listener;
    }
}
