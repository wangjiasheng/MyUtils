package wanyan.com.updatelib_incre;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.wjs.upatetask.NetworkTask;
import com.wjs.upatetask.URLBuilder;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String updateURL=new URLBuilder(this).builderHost("http://192.168.5.174:8080/AppUpdate/CheckUpdate").incremental_update(true).build();
        TextView tv= (TextView) findViewById(R.id.textView);
        tv.setText(getPackageCodePath());
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                new NetworkTask(MainActivity.this,updateURL).execute();
            }
        });
    }
}
