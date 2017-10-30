package com.wenyan.slidingmenulib;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 家胜 on 2016/1/13.
 */
public class MainFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        try {
            //String url = "mqqwpa://im/chat?chat_type=wpa&uin=501863587";
            String url = "tencent://Message/?Uin=574201314&websiteName=www.oicqzone.com&Menu=yes";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
