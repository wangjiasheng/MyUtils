package com.wjs.wenyan.weichat;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;

/**
 * Created by 家胜 on 2016/4/28.
 */
public class ShareBit
{
    /**
     * 发送文件路径
     */
    public static void sharePath(String path,boolean function)
    {
        final WXAppExtendObject appdata = new WXAppExtendObject();
        appdata.filePath = path;
        appdata.extInfo = "this is ext info";

        final WXMediaMessage msg = new WXMediaMessage();
        msg.setThumbImage(Util.extractThumbNail(path, 150, 150, true));
        msg.title = "this is title";
        msg.description = "this is description";
        msg.mediaObject = appdata;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("appdata");
        req.message = msg;
        req.scene = function ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        WeiChatClient.getInstanse().sendReq(req);
    }

    /**
     * 发送二进制数据
     */
    public static void shareDate(boolean function)
    {
        final WXAppExtendObject appdata = new WXAppExtendObject();
        appdata.fileData = Util.readFromFile("/storage/emulated/0/test.gif", 0, -1);
        appdata.extInfo = "this is ext info";

        final WXMediaMessage msg = new WXMediaMessage();
        msg.setThumbImage(Util.extractThumbNail("/storage/emulated/0/test.png", 150, 150, true));
        msg.title = "this is title";
        msg.description = "this is description sjgksgj sklgjl sjgsgskl gslgj sklgj sjglsjgs kl gjksss ssssssss sjskgs kgjsj jskgjs kjgk sgjsk Very Long Very Long Very Long Very Longgj skjgks kgsk lgskg jslgj";
        msg.mediaObject = appdata;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("appdata");
        req.message = msg;
        req.scene = function ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        WeiChatClient.getInstanse().sendReq(req);
    }
    public void shareNotData(boolean function)
    {
        final WXAppExtendObject appdata = new WXAppExtendObject();
        appdata.extInfo = "this is ext info";
        final WXMediaMessage msg = new WXMediaMessage();
        msg.title = "this is title";
        msg.description = "this is description";
        msg.mediaObject = appdata;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("appdata");
        req.message = msg;
        req.scene = function ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        WeiChatClient.getInstanse().sendReq(req);
    }
    private static String buildTransaction(final String type)
    {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
