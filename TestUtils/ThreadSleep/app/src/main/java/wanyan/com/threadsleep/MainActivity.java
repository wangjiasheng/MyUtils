package wanyan.com.threadsleep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import wanyan.com.networkmodel.util.HttpUtils;

public class MainActivity extends AppCompatActivity {
    Thread tread=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tread=new Thread(){
            @Override
            public void run() {
                HttpUtils.doGet("");
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        };
        tread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tread.interrupt();
    }
}
