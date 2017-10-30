/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wjs.wenyan.sina;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboHandler.Response;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.Utility;

/**
 * 该类演示了第三方应用如何通过微博客户端分享文字、图片、视频、音乐等。
 * 执行流程： 从本应用->微博->本应用
 * 
 * @author SINA
 * @since 2013-10-22
 */
public class SinaShareTest extends  Activity implements Response{
	 private IWeiboShareAPI mWeiboShareAPI;
	    public static final String SCOPE =
	            "email,direct_messages_read,direct_messages_write,"
	                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
	                    + "follow_app_official_microblog," + "invitation_write";
	    @Override
	    protected void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sinalayout);
	        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this,"346078386");
	        mWeiboShareAPI.registerApp();
	        boolean isInstalledWeibo = mWeiboShareAPI.isWeiboAppInstalled();//判断本地是否安装了微博客户端
	        if (savedInstanceState != null) {
	            mWeiboShareAPI.handleWeiboResponse(getIntent(), this);
	        }
	        findViewById(R.id.sinamusic).setOnClickListener(new OnClickListener()
	        {
	            @Override
	            public void onClick(View v)
	            {
	                shareMusic();
	            }
	        });
	    }
	    public void shareMusic()
	    {
	        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
	        TextObject textObject = new TextObject();
	        textObject.text = "卧槽";
	        weiboMessage.textObject =textObject;
	        weiboMessage.mediaObject = getMusicObj();


	        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
	        // 用transaction唯一标识一个请求
	        request.transaction = String.valueOf(System.currentTimeMillis());
	        request.multiMessage = weiboMessage;

	        // 3. 发送请求消息到微博，唤起微博分享界面
	        AuthInfo authInfo = new AuthInfo(this, "346078385","http://www.tourbjxch.com.cn",SCOPE);
	        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(getApplicationContext());
	        String token = "";
	        if (accessToken != null) {
	            token = accessToken.getToken();
	        }
	        mWeiboShareAPI.sendRequest(this, request, authInfo, token, new WeiboAuthListener() {

	            @Override
	            public void onWeiboException(WeiboException arg0) {
	            }

	            @Override
	            public void onComplete(Bundle bundle) {
	                // TODO Auto-generated method stub
	                Oauth2AccessToken newToken = Oauth2AccessToken.parseAccessToken(bundle);
	                AccessTokenKeeper.writeAccessToken(getApplicationContext(), newToken);
	                Toast.makeText(getApplicationContext(), "onAuthorizeComplete token = " + newToken.getToken(), Toast.LENGTH_SHORT).show();
	            }

	            @Override
	            public void onCancel() {
	            }
	        });
	    }
	    private MusicObject getMusicObj() {
	        // 创建媒体消息
	        MusicObject musicObject = new MusicObject();
	        musicObject.identify = Utility.generateGUID();
	        musicObject.title = "操你妈";
	        musicObject.description = "狗日的";

	        Bitmap  bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_share_voice_thumb);



	        // 设置 Bitmap 类型的图片到视频对象里        设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
	        musicObject.setThumbImage(bitmap);
	        //http://music.sina.com.cn/yueku/i/2850305.html
	        musicObject.actionUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
	        musicObject.dataUrl = "www.weibo.com";
	        musicObject.dataHdUrl = "www.weibo.com";
	        musicObject.duration = 10;
	        musicObject.defaultText = "Music 默认文案";
	        return musicObject;
	    }
	    @Override
	    public void onResponse(BaseResponse baseResponse)
	    {
	        if(baseResponse!= null){
	            switch (baseResponse.errCode) {
	                case WBConstants.ErrorCode.ERR_OK:
	                    Toast.makeText(this, "分享成功", Toast.LENGTH_LONG).show();
	                    break;
	                case WBConstants.ErrorCode.ERR_CANCEL:
	                    Toast.makeText(this,"分享取消", Toast.LENGTH_LONG).show();
	                    break;
	                case WBConstants.ErrorCode.ERR_FAIL:
	                    Toast.makeText(this,"分享失败" + baseResponse.errMsg,Toast.LENGTH_LONG).show();
	                    break;
	            }
	        }
	    }
	    @Override
	    protected void onNewIntent(Intent intent) {
	        super.onNewIntent(intent);
	        mWeiboShareAPI.handleWeiboResponse(intent, this);
	    }
}
