package com.wjs.handler;

import android.app.Activity;
import android.os.Handler;
import android.widget.Toast;

import com.wjs.base.BaseApplication;

import java.lang.ref.WeakReference;

/**
 * Created by WJS on 2016/10/8.
 */

public class DoubleClickExitHandler extends Handler {
    /**
     * 利用计算时间差实现
     */
    private long exitTime = 0;
    WeakReference<Activity> weak = null;

    public DoubleClickExitHandler(Activity activity) {
        weak = new WeakReference<Activity>(activity);
    }
    public void handleMessage(android.os.Message msg) {
        switch (msg.what) {
            case 12631870:
                Activity activity = weak.get();
                if (activity != null) {
                    Toast.makeText(
                            activity,
                            "再按一次退出程序",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    };
    /**
     * 调用退出APP
     */
    public void canExitApp()
    {
        if ((System.currentTimeMillis() - exitTime) > 500)
        {
            this.sendEmptyMessageDelayed(12631870, 500);
            exitTime = System.currentTimeMillis();
        }
        else
        {
            this.removeMessages(12631870);
            BaseApplication.ExitApp();
        }
    }
}
