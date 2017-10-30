package wjs.file.filemanager;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by 家胜 on 2016/4/26.
 */
public class BaseApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        LeakCanary.install(this);
    }
}
