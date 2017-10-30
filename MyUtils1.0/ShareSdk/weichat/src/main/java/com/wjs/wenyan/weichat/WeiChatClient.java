package com.wjs.wenyan.weichat;

import android.content.Context;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by 家胜 on 2016/4/28.
 */
public class WeiChatClient
{
    private static IWXAPI api;
    public static IWXAPI getInstanse()
    {
        if(api==null)
        {
           // api = WXAPIFactory.createWXAPI(context, context.getString(R.string.app_name));
        }
        return api;
    }
    public static void regiest()
    {
        api.registerApp("");
    }
    public void getAuth()
    {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "post_timeline";
        req.state = "none";
        api.sendReq(req);
    }
    public void unRegiest()
    {
        api.unregisterApp();
    }
    public void openWeixin()
    {
        api.openWXApp();
    }
    public boolean can_user_pengyouquan()
    {
        int TIMELINE_SUPPORTED_VERSION = 0x21020001;
        int wxSdkVersion = api.getWXAppSupportAPI();
        if (wxSdkVersion >= TIMELINE_SUPPORTED_VERSION) {
            return true;
        }
        return false;
    }
}
