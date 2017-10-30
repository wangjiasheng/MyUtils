package com.wjs.wenyan.myutils;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wjs.annotation.AutoRun;
import com.wjs.annotation.Backbutton;
import com.wjs.annotation.ViewClick;
import com.wjs.annotation.ViewInject;
import com.wjs.annotation.Network;
import com.wjs.base.BaseActivity;
import com.wjs.manager.LayoutUtils;
import com.wjs.network.task.HttpTask;
import com.wjs.network.task.HttpTaskCallback;
import com.wjs.utils.CollectPhoneInfo;
import com.wjs.utils.LogcatHelper;

import java.io.File;
import java.util.List;

public class HttpActivity extends BaseActivity {
    @ViewInject(R.id.textview)
    TextView textView;
    @Network
    HttpTask task;
    @Backbutton(R.id.button2)
    View button2;
    @ViewInject(R.id.myPicker)
    com.wjs.view.DatePicker myPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        LayoutUtils.inject(this);
        Log.i("wjs","dd");
        myPicker.setCurrentTime(2018,10,12,10,10);
    }
    public void setTimer()
    {
    }
    public void setDate()
    {
        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                setTime();
            }
        },2016,11,28);
        dialog.show();
    }
    public void setTime()
    {
        TimePickerDialog dialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        },10,12,false);
        dialog.show();
    }
    @ViewClick({R.id.button1,R.id.button2,R.id.button3,R.id.button4})
    public void click(View view)
    {
        final long i=System.currentTimeMillis();
        switch (view.getId())
        {
            case R.id.button1:
                CollectPhoneInfo.sendScreenInfo(HttpActivity.this,"http://w314232332.oicp.net/PhoneScreenSize/servlet/getScreenSize",CollectPhoneInfo.mapToJson(CollectPhoneInfo.collectDeviceInfo(HttpActivity.this)));
                break;
            case R.id.button2:
                CollectPhoneInfo.sendError("http://w314232332.oicp.net/PhoneScreenSize/commitErrorInfo",CollectPhoneInfo.mapToJson(CollectPhoneInfo.collectDeviceInfo(HttpActivity.this)),Environment.getExternalStorageDirectory()+File.separator+"error"+File.separator);
                break;
            case R.id.button3:
                try
                {
                    int max=1/0;
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                break;
            case R.id.button4:
                break;
        }
    }
    @AutoRun
    public void autoRun()
    {
        Toast.makeText(this,"zidong运行",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogcatHelper.getInstance(Environment.getExternalStorageDirectory()+File.separator+"error"+File.separator).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogcatHelper.getInstance(Environment.getExternalStorageDirectory()+File.separator+"error"+File.separator).stop();
    }
}
