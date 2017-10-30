package com.wjs.wenyan.weichat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXVideoObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by 家胜 on 2016/4/28.
 */
public class ShareVideo
{
    public static void shareVideo(Context context,String title,String discript,Bitmap bitmap,String videoUrl,boolean function)
    {

        IWXAPI api = WXAPIFactory.createWXAPI(context, context.getString(R.string.app_name,"wxa0ebcc39b6f69830",true));
        api.registerApp("wxa0ebcc39b6f69830");
        //api.handleIntent(context.geti, this);
        WXVideoObject video = new WXVideoObject();
        video.videoUrl = videoUrl;

        WXMediaMessage msg = new WXMediaMessage(video);
        msg.title = title;
        msg.description = discript;
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
        bitmap.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("video");
        req.message = msg;
        req.scene = function ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }
    /**
     * 发送低宽带音乐url
     */
    public static void shareNtNdVideo(Context context,String title,String discript,Bitmap bitmap,boolean function)
    {
        WXVideoObject video = new WXVideoObject();
        video.videoLowBandUrl = "http://www.baidu.com";
        WXMediaMessage msg = new WXMediaMessage(video);
        msg.title = title;
        msg.description = discript;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("video");
        req.message = msg;
        req.scene = function ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        WeiChatClient.getInstanse().sendReq(req);
    }
    private static String buildTransaction(final String type)
    {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
