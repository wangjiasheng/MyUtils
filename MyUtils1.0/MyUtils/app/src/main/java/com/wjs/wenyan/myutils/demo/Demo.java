package com.wjs.wenyan.myutils.demo;

import android.app.Activity;
import android.os.Bundle;

import com.wjs.network.https.HttpsInit;
import com.wjs.network.task.HttpTask;
import com.wjs.network.task.HttpTaskCallback;

import java.util.List;

/**
 * Created by WJS on 2016/11/29.
 */

public class Demo extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        HttpsInit.initNosafe();//初始化Https引擎，只需要调用一次即可，也可以多次但是耗时间
        //Post
        HttpTask.post(this, "https://www.baidu.com", null, new HttpTaskCallback()
        {
            @Override
            public void onSucess(Object bean) {

            }

            @Override
            public void onFaield(Throwable ex) {
            }
        });
        //Get
        HttpTask.get(this, "https://www.baidu.com", new HttpTaskCallback() {
            @Override
            public void onSucess(Object bean)
            {

            }
            @Override
            public void onFaield(Throwable ex)
            {

            }
        });
    }
}
