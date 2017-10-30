package com.wjs.updatelibsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wjs.updatelib.UpdateDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UpdateDialog dialog=new UpdateDialog();
        List<String> list=new ArrayList<String>();
        //list.add("将时间设置只能下一天");
        //list.add("增加背景颜色");
        //list.add("修复一些已知Bug");
        dialog.updateApp(this,10,"10",list,"www.baiud.com");
    }
}
