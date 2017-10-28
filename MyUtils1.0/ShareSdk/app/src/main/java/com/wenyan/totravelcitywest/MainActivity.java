package com.wenyan.totravelcitywest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.open.utils.ThreadManager;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.wenyan.totravelcitywest.wxapi.WXEntryActivity;
import com.wjs.wenyan.sina.SinaShareTest;
import com.wjs.wenyan.tencent.TencentShare;
import com.wjs.wenyan.weichat.BitmapUtils;
import com.wjs.wenyan.weichat.ShareMusic;
import com.wjs.wenyan.weichat.WeiChatClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    View contentVIew;
    Button button;
    PopupWindow window;
    private IWXAPI api;
    Tencent mTencent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentVIew=LayoutInflater.from(this).inflate(R.layout.activity_main,null);
        button= (Button) contentVIew.findViewById(R.id.button);
        setContentView(contentVIew);
        popupWindow();
        api = WXAPIFactory.createWXAPI(this, "wx45e4582c89eed670",false);
        api.registerApp("wx45e4582c89eed670");
        mTencent=Tencent.createInstance("1105224708", this.getApplicationContext());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.showAtLocation(contentVIew, Gravity.BOTTOM,0,0);
            }
        });
    }
    public void popupWindow()
    {

        GridView gridView=new GridView(this);
        gridView.setPadding(10,10,10,40);
        gridView.setNumColumns(4);
        window=new PopupWindow(this);
        window.setFocusable(true);
        window.setTouchable(true);
        window.setOutsideTouchable(true);
        window.setContentView(gridView);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setAnimationStyle(R.style.PopupAnimation);
        List<ShareBean> beanList=new ArrayList<ShareBean>();
        {
            ShareBean bean = new ShareBean();
            bean.setDrableId(R.mipmap.xinlang);
            bean.setTextName("新浪");
            beanList.add(bean);
        }
        {
            ShareBean bean = new ShareBean();
            bean.setDrableId(R.mipmap.weixin);
            bean.setTextName("微信好友");
            beanList.add(bean);
        }
        {
            ShareBean bean = new ShareBean();
            bean.setDrableId(R.mipmap.pengyouq);
            bean.setTextName("微信朋友圈");
            beanList.add(bean);
        }
        {
            ShareBean bean = new ShareBean();
            bean.setDrableId(R.mipmap.qq);
            bean.setTextName("QQ");
            beanList.add(bean);
        }
        {
            ShareBean bean = new ShareBean();
            bean.setDrableId(R.mipmap.duanxin);
            bean.setTextName("短信");
            beanList.add(bean);
        }
        gridView.setAdapter(new ShareAdapter(this,beanList));
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        switch(position)
        {
            case 0:
                Intent intent=new Intent(MainActivity.this,SinaShareTest.class);
                startActivity(intent);
                break;
            case 1:
                {
                    if(api.isWXAppInstalled())
                    {
                        WXMusicObject music = new WXMusicObject();
                        music.musicUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
                        WXMediaMessage msg = new WXMediaMessage();
                        msg.mediaObject = music;
                        msg.title = "Musicg Verong";
                        msg.description = "Musng Very Long Very Long Very Long";
                        msg.thumbData = BitmapUtils.drawableToByte(getResources(), R.mipmap.weixin);
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = buildTransaction("music");
                        req.message = msg;
                        req.scene = false ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
                        api.sendReq(req);
                    }
                }
                break;
            case 2:
                {
                    if(api.isWXAppInstalled())
                    {
                        WXMusicObject music = new WXMusicObject();
                        music.musicUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
                        WXMediaMessage msg = new WXMediaMessage();
                        msg.mediaObject = music;
                        msg.title = "Musicg Verong";
                        msg.description = "Musng Very Long Very Long Very Long";
                        msg.thumbData = BitmapUtils.drawableToByte(getResources(), R.mipmap.weixin);
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = buildTransaction("music");
                        req.message = msg;
                        req.scene = true ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
                        api.sendReq(req);
                    }
                }
                break;
            case 3:
                final Bundle params = new Bundle();
                params.putString(QQShare.SHARE_TO_QQ_TITLE, "不要说话");
                params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.baidu.com");//点击跳转
                params.putString(QQShare.SHARE_TO_QQ_SUMMARY,"专辑名：不想放手歌手名：陈奕迅");
                params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/music/photo/mid_album_300/V/E/000J1pJ50cDCVE.jpg");
                params.putString(QQShare.SHARE_TO_QQ_APP_NAME,"QQ音乐王家胜");
                params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_AUDIO);
                params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, 0);
                params.putString(QQShare.SHARE_TO_QQ_AUDIO_URL, "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3");
                ThreadManager.getMainHandler().post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (null != mTencent)
                        {
                            mTencent.shareToQQ(MainActivity.this, params, qqShareListener);
                        }
                    }
                });
                break;
            case 4:
                Intent It=new Intent(MainActivity.this,TencentShare.class);
                startActivity(It);
                break;
        }
        if(window.isShowing())
        {
            window.dismiss();
        }
    }
    IUiListener qqShareListener = new IUiListener()
    {
        @Override
        public void onCancel()
        {
            Toast.makeText(MainActivity.this,"分享取消",Toast.LENGTH_LONG).show();
        }
        @Override
        public void onComplete(Object response) {
            Toast.makeText(MainActivity.this,"分享成功",Toast.LENGTH_LONG).show();
        }
        @Override
        public void onError(UiError e) {
            Toast.makeText(MainActivity.this,"分享失败",Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onDestroy() {
        api.unregisterApp();
        if(window.isShowing())
        {
            window.dismiss();
        }
        super.onDestroy();
    }
    private static String buildTransaction(final String type)
    {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == Constants.REQUEST_QQ_SHARE)
        {
            Tencent.onActivityResultData(requestCode,resultCode,data,qqShareListener);
        } else if (requestCode == 0) {
            String path = null;
            if (resultCode == Activity.RESULT_OK) {
                if (data != null && data.getData() != null) {
                    Toast.makeText(this,"我擦1",Toast.LENGTH_SHORT).show();
                }
            }
            if (path != null) {
                Toast.makeText(this,"我擦2",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"我擦3",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
