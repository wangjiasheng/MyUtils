package com.wjs.wenyan.myutils.demo;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

import com.wjs.utils.ShakeListener;
import com.wjs.wenyan.myutils.R;

public class ShakeListenerActivity extends Activity {
    private ShakeListener mShakeListener=null;
    private TextView MyTextView;
    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        MyTextView=(TextView)findViewById(R.id.myTextView);

        mShakeListener = new ShakeListener(this);

        setListeners();


    }
    private void setListeners() {

        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {

            public void onShake() {

                Vibrator vVi = (Vibrator) ShakeListenerActivity.this.getSystemService(Service.VIBRATOR_SERVICE);

                vVi.vibrate(new long[]{1000,100,10,80,10,40,10,20,10,10}, -1);

                MyTextView.setText("Hello World!!!");

            }

        });

    }
}