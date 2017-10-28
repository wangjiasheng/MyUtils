package wanyan.com.threadstop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Object object=new Object();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    synchronized (object)
                    {
                        object.wait(2000);
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);
                    }
                }
                catch
                        (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try
        {
            synchronized (object)
            {
                object.notify();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
