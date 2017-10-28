package wanyan.com.zxing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cn.hugo.android.scanner.CaptureActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentCapture=new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intentCapture, 8000);

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==8000&&resultCode== CaptureActivity.Result_Code) {
            String zxing=data.getStringExtra(CaptureActivity.Result_key);
            Toast.makeText(MainActivity.this,zxing,Toast.LENGTH_SHORT).show();
        }
    }
}
