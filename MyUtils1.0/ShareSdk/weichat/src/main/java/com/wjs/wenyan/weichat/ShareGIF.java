package com.wjs.wenyan.weichat;

/**
 * Created by 家胜 on 2016/4/28.
 */

import android.content.Context;
import android.graphics.Bitmap;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXEmojiObject;
import com.tencent.mm.sdk.modelmsg.WXEmojiSharedObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 表情分享
 */
public class ShareGIF
{
    public static void sharePath(Context context,boolean function)
    {
        IWXAPI api = WXAPIFactory.createWXAPI(context, context.getString(R.string.app_name, "wxa0ebcc39b6f69830", true));
        api.registerApp("wxa0ebcc39b6f69830");
        WXEmojiSharedObject obj=new WXEmojiSharedObject();
        WXEmojiObject emoji = new WXEmojiObject();
        emoji.emojiPath = "sdk";
        WXMediaMessage msg = new WXMediaMessage(emoji);
        msg.title = "Emoji Title";
        msg.description = "Emoji Description";
        msg.thumbData = Util.bmpToByteArray(null, true);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("emoji");
        req.message = msg;
        req.scene = function ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }
    public static void shareBit(Context context,boolean function)
    {
        IWXAPI api = WXAPIFactory.createWXAPI(context, context.getString(R.string.app_name, "wxa0ebcc39b6f69830", true));
        api.registerApp("wxa0ebcc39b6f69830");
        WXEmojiObject emoji = new WXEmojiObject();
        //emoji.emojiData = Util.readFromFile("/storage/emulated/0/test.jpg", 0, (int) new java.io.File("/storage/emulated/0/test.jpg").length());
        emoji.emojiPath="http://www.paigu.com/imgs/c0b6e8ad0c2da14c89321d51a45c00445fdd9e2516899c73fcfc353414b6601032c770da15c4901e585ef832941e846ded2898d7a695769564dc18d106f5360061e8c0dce759cec128acb075045a0ac413405724d4/6456d103.png";
        WXMediaMessage msg = new WXMediaMessage(emoji);

        msg.title = "Emoji Title";
        msg.description = "Emoji Description";
        msg.thumbData = Util.readFromFile("/storage/emulated/0/test.png", 0, (int) new java.io. File("/storage/emulated/0/test.png").length());

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("emoji");
        req.message = msg;
        req.scene = function ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }
    private static String buildTransaction(final String type)
    {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
