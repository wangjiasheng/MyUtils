package com.wjs.base;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by WJS on 2016/9/29.
 */

public class BaseApplication extends Application
{
    /**
     * activity容器，在Application退出的时候将所有activity销毁
     */
    private static List<Activity> list=new ArrayList<Activity>();
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static void addActivity(Activity activity)
    {
        if(!list.contains(activity)) {
            list.add(activity);
        }
    }
    public static void removeActivity(Activity activity)
    {
        if(list.contains(activity))
        {
            list.remove(activity);
        }
    }
    public static void ExitApp()
    {
        for (int i = 0; i < list.size(); i++)
        {
            Activity activity = list.get(i);
            if (activity != null)
            {
                activity.finish();
            }
        }
        list.clear();
    }
}
