package com.wjs.sms.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.EditText;

import com.wjs.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 家胜 on 2016/4/21.
 *
 * need permission android.permission.RECEIVE_SMS
 */
public class SMSReceiver extends BroadcastReceiver
{
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    EditText editText;
    public void mOnreigestReciver(Context context, EditText editText)
    {
        this.editText=editText;
        IntentFilter filter = new IntentFilter(ACTION);
        filter.setPriority(1000);
        context.registerReceiver(this, filter);
    }
    public void setEditText(EditText editText)
    {
        this.editText=editText;
    }
    public void mOnunregiestReceiver(Context context)
    {
        context.unregisterReceiver(this);
    }
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bundle = intent.getExtras();
        SmsMessage msg = null;
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[]) object);
                Date date = new Date(msg.getTimestampMillis());//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
                System.out.println("number:" + msg.getOriginatingAddress()
                        + "   body:" + msg.getDisplayMessageBody() + "  time:"
                        + msg.getTimestampMillis());

                //在这里写自己的逻辑
                String phoneNumber=msg.getOriginatingAddress();
                String yanzhenma=Util.getYanZhengma(msg.getMessageBody());
                if(StringUtils.isNotNull(yanzhenma))
                {
                    if(editText!=null)
                    {
                        editText.setText(yanzhenma);
                    }
                }
                abortBroadcast();
            }
        }
    }
}