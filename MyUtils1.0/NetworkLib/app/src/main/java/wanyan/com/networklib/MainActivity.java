package wanyan.com.networklib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import wanyan.com.networkmodel.HttpTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar mydialog= (ProgressBar) findViewById(R.id.mydialog);
        final ListView listView= (ListView) findViewById(R.id.listview);
        HttpTask tast=HttpTask.post(this,"http://www.btbtt.la/thread-index-fid-950-tid-4305735.htm",null,null,new HttpTask.HttpTaskCallback<List<DownInfo>>(){
            @Override
            public void onSucess(List<DownInfo> bean, List<String> sCookie)
            {
                for(int i=0;i<bean.size();i++)
                {
                    listView.setAdapter(new Adapter(MainActivity.this,bean));
                    mydialog.setVisibility(View.GONE);
                }
            }
            @Override
            public Object onCreateBean(String requestResult) {
                return DownInfo.createBean(requestResult);
            }
            @Override
            public void onFaield()
            {
            }
        });
    }
}
