package com.wjs.wenyan.tencent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Created by 家胜 on 2016/4/28.
 */
public class TencentShare extends Activity
{
    static Tencent mTencent=null;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tencentshare);
        //2THdiFcYtksiwevr
        mTencent=Tencent.createInstance("222222", this.getApplicationContext());
        result= (TextView) findViewById(R.id.result);
        Button button=(Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mTencent.login(TencentShare.this,"all",qqUserLogin);
            }
        });
        Button userinfo= (Button) findViewById(R.id.userinfo);
        userinfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UserInfo mInfo = new UserInfo(TencentShare.this, mTencent.getQQToken());
                boolean ready = mTencent.isSessionValid() && mTencent.getQQToken().getOpenId() != null;
                if(ready)
                {
                    mInfo.getUserInfo(qqShareListener);
                }
                else
                {
                    mInfo.getUserInfo(qqShareListener);
                    Toast.makeText(TencentShare.this,"登陆失败"+mTencent.getQQToken().getAccessToken(),Toast.LENGTH_SHORT).show();

                }
            }
        });
        Button exit=(Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mTencent.logout(TencentShare.this);
            }
        });
    }
    IUiListener qqUserLogin = new IUiListener()
    {
        @Override
        public void onCancel()
        {
            Toast.makeText(TencentShare.this,"登陆取消",Toast.LENGTH_LONG).show();
        }
        @Override
        public void onComplete(Object response)
        {
            if (null == response) {
                result.setText("登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0)
            {
                result.setText("登录失败");
                return;
            }
            result.setText("登陆成功:"+response.toString());
            try {
                String token = jsonResponse.getString(Constants.PARAM_ACCESS_TOKEN);
                String expires = jsonResponse.getString(Constants.PARAM_EXPIRES_IN);
                String openId = jsonResponse.getString(Constants.PARAM_OPEN_ID);
                if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)&& !TextUtils.isEmpty(openId))
                {
                    mTencent.setAccessToken(token, expires);
                    mTencent.setOpenId(openId);
                    Log.i("wjsinfo","token:"+token);
                    Log.i("wjsinfo","expires:"+expires);
                    Log.i("wjsinfo","openId:"+openId);
                }
            } catch(Exception e) {
            }
        }
        @Override
        public void onError(UiError e) {
            result.setText("登陆失败::"+e.errorMessage);
        }

    };
    IUiListener qqShareListener = new IUiListener()
    {
        @Override
        public void onCancel()
        {
            Toast.makeText(TencentShare.this,"取消",Toast.LENGTH_LONG).show();
        }
        @Override
        public void onComplete(Object response) {
            result.setText("成功:"+response.toString());
        }
        @Override
        public void onError(UiError e) {
            result.setText("失败::"+e.errorMessage);
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,qqShareListener);
        }
    }
}
