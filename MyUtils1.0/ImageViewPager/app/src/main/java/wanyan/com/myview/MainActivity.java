package wanyan.com.myview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wjs.view.SingleProgress;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import wanyan.com.lunbo.ChildViewPager;
import wanyan.com.lunbo.ViewPagerAutoChangeUtils;

public class MainActivity extends AppCompatActivity  {
    private ChildViewPager mViewpager=null;
    private ViewPagerAutoChangeUtils mViewPagerUtils=null;
    private SingleProgress mSingleProgress=null;
    int color[]={Color.RED,Color.BLACK,Color.YELLOW,Color.BLUE,Color.CYAN,Color.GRAY};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewpager= (ChildViewPager) findViewById(R.id.viewpager);
        mSingleProgress= (SingleProgress) findViewById(R.id.singleprogress);
        List<ImageView> mText=new ArrayList<ImageView>();
        for(int i=0;i<5;i++)
        {
            ImageView textView=new ImageView(this);
            textView.setImageDrawable(new ColorDrawable(color[i]));
            mText.add(textView);
        }
        if (mViewPagerUtils == null) {
            mViewPagerUtils = new ViewPagerAutoChangeUtils(this,Looper.getMainLooper(),mViewpager,mSingleProgress, mText, 5000,false,R.drawable.mainactivity_select,R.drawable.mainactivity_unselect);
        }
        else
        {
            mViewPagerUtils.setAdapter(mViewpager, mText);
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();
        if(mViewPagerUtils!=null)
        {
            mViewPagerUtils.onResume();
        }
    }
    @Override
    public void onPause()
    {
        super.onPause();
        if(mViewPagerUtils!=null)
        {
            mViewPagerUtils.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mViewPagerUtils!=null)
        {
            mViewPagerUtils.onDestroy();
        }
    }
}
