package wanyan.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;
            TextView textView = (TextView) findViewById(R.id.text);
            textView.setText(dm.widthPixels + "x" + dm.heightPixels);
            TextView textView2= (TextView) findViewById(R.id.text1);
            textView2.setText(dm.widthPixels+"x"+dm.heightPixels);
    }
}
