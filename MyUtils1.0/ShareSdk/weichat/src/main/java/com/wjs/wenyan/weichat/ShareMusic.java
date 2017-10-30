package com.wjs.wenyan.weichat;

import android.content.res.Resources;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;

/**
 * Created by 家胜 on 2016/4/28.
 */
public class ShareMusic
{
    public void shareMusic(Resources res,int drawable,boolean function)
    {
        WXMusicObject music = new WXMusicObject();
        //music.musicUrl = "http://www.baidu.com";
        music.musicUrl="http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
        //music.musicUrl="http://120.196.211.49/XlFNM14sois/AKVPrOJ9CBnIN556OrWEuGhZvlDF02p5zIXwrZqLUTti4o6MOJ4g7C6FPXmtlh6vPtgbKQ==/31353278.mp3";

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = music;
        msg.title = "Musicg Verong";
        msg.description = "Musng Very Long Very Long Very Long";


        msg.thumbData = BitmapUtils.drawableToByte(res, drawable);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("music");
        req.message = msg;
        req.scene = function ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        WeiChatClient.getInstanse().sendReq(req);
    }
    /**
     * 发送低宽带音乐url
     */
    public void shareNtNdMusic(Resources res,int drawable,boolean function)
    {
        WXMusicObject music = new WXMusicObject();
        music.musicLowBandUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = music;
        msg.title = "Music Title";
        msg.description = "Music Album";

        msg.thumbData = BitmapUtils.drawableToByte(res, drawable);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("music");
        req.message = msg;
        req.scene = function ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        WeiChatClient.getInstanse().sendReq(req);
    }
    private static String buildTransaction(final String type)
    {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
