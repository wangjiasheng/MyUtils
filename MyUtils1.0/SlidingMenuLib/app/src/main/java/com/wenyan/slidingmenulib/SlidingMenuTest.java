package com.wenyan.slidingmenulib;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by 家胜 on 2016/1/13.
 */
public class SlidingMenuTest extends SlidingFragmentActivity
{
    private Fragment mContent;
    private SlidingMenu sm;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivitycontainer);
        if (findViewById(R.id.menu) == null)
        {
            setBehindContentView(R.layout.menulayoutcontainer);
            getSlidingMenu().setSlidingEnabled(true);
            getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }
        else
        {
            View v = new View(this);
            setBehindContentView(v);
            getSlidingMenu().setSlidingEnabled(false);
            getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
        if (savedInstanceState != null)
        {
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
        }

        if (mContent == null) {
            mContent = new MainFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main, mContent).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu, new MenuFragment()).commit();
        sm = getSlidingMenu();
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setFadeEnabled(false);
        sm.setBehindScrollScale(0.25f);
        sm.setFadeDegree(0.25f);
        sm.setBackgroundImage(R.mipmap.main_background);
        sm.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                float scale = (float) (percentOpen * 0.25 + 0.75);
                canvas.scale(scale, scale, -canvas.getWidth() / 2,
                        canvas.getHeight() / 2);
            }
        });
        sm.setAboveCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
                float scale = (float) (1 - percentOpen * 0.1);
                canvas.scale(scale, scale, 0, canvas.getHeight() / 2);
            }
        });

    }
}
