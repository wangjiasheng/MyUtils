package com.wjs.wenyan.myutils;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wjs.annotation.AutoRun;
import com.wjs.annotation.ViewClick;
import com.wjs.annotation.ViewInject;
import com.wjs.annotation.ContentView;
import com.wjs.annotation.Doubleclickexit;
import com.wjs.manager.LayoutUtils;
import com.wjs.network.https.HttpsInit;
import com.wjs.network.task.HttpTask;
import com.wjs.network.task.HttpTaskCallback;
import com.wjs.network.json.BaseBean;
import com.wjs.utils.ScreenUtils;
import com.wjs.wenyan.myutils.bean.MenuBean;
import java.util.List;
@Doubleclickexit
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity
{
    @ViewInject(R.id.fab)
    FloatingActionButton fab;
    @ViewInject(R.id.rvToDoList)
    RecyclerView rvToDoList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        LayoutUtils.inject(this);
        HttpsInit.initNosafe();
        Log.i("wjs",""+ ScreenUtils.getScreenWidth(this));
        Log.i("wjs",""+ ScreenUtils.getScreenHeight(this));

        HttpTask menutask= HttpTask.post(this,"http://www.qq.com/",null,new HttpTaskCallback<String>()
        {
            @Override
            public void onSleeping() {
                super.onSleeping();
                Log.i("wjs","onSleeping");
            }

            @Override
            public void onRequestURL(String url) {
                super.onRequestURL(url);
                Log.i("wjs","onRequestURL:"+url);
            }

            @Override
            public Object onCreateBean(String requestResult) {
                return requestResult;
            }
            @Override
            public void onSucess(String bean)
            {
                Toast.makeText(MainActivity.this,bean,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFaield(Throwable ex)
            {
                Toast.makeText(MainActivity.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        final String[] str=new String[100];
        for(int i=0;i<str.length;i++)
        {
            str[i]="1_"+i;
        }
        rvToDoList.setLayoutManager(new LinearLayoutManager(this));
        rvToDoList.setAdapter(new RecyclerView.Adapter<MyHolder>() {
            @Override
            public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new MyHolder(LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_2,parent,false));
            }

            @Override
            public void onBindViewHolder(MyHolder holder, int position) {
                holder.tv.setText(str[position]);
            }

            @Override
            public int getItemCount() {
                return str.length;
            }
        });

    }
    class MyHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
    @ViewClick({R.id.fab})
    private  void onToolbarClick(View view)
    {
        Snackbar.make(view, ""+(findViewById(R.id.fab)==fab), Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
    @AutoRun
    public void autoRun()
    {
        Toast.makeText(this,"zidong运行",Toast.LENGTH_LONG).show();
    }
}
