package com.wjs.sms.message;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.wjs.utils.SharedPreferencesUtils;

import java.text.MessageFormat;

/**
 * Created by 家胜 on 2016/4/21.
 */
public class ShowCalcTime extends AsyncTask<Void,Integer,Void>
{
    int YTime=0;
    TextView button;
    String showtext;
    String textformat;
    boolean exit=false;
    /**
     * @param context    上下文对象
     * @param button     要显示的TextView
     * @param key        获取时间的关键值
     * @param time       验证码时间，如果放在非点击事件里面务必设置为0
     * @param showtext  例如：获取验证码
     * @param textformat 例如:剩余{0}秒
     */
    public void show(Context context, TextView button, String key, int time, String showtext, String textformat)
    {
        YTime=time;
        this.button=button;
        this.showtext=showtext;
        this.textformat=textformat;
        long cutime= System.currentTimeMillis();
        long totime=(long) SharedPreferencesUtils.get(context, key, cutime);
        if(totime>cutime)
        {
            button.setEnabled(false);
            YTime=(int)((totime-cutime)/1000);
            button.setText(MessageFormat.format(textformat, new Object[]{YTime}));
            execute();
        }
        else
        {
            if(time!=0)
            {
                button.setEnabled(false);
                YTime=time;
                button.setText(MessageFormat.format(textformat, new Object[]{YTime}));
                SharedPreferencesUtils.put(context, key, cutime + (time * 1000));
                execute();
            }
            else
            {
                button.setEnabled(true);
                button.setText(showtext);
            }
        }
    }
    @Override
    protected Void doInBackground(Void... params)
    {
        while (YTime > 0&&!exit)
        {
            try
            {
                YTime--;
                Thread.sleep(1000);
                publishProgress(YTime);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public void onDestroy()
    {
        exit=true;
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
        button.setText(MessageFormat.format(textformat, new Object[]{values[0]}));
        Log.i("wjs","wjs"+ MessageFormat.format(textformat, new Object[]{values[0]}));
    }
    @Override
    protected void onPostExecute(Void aVoid)
    {
        button.setText(showtext);
        button.setEnabled(true);
    }
}