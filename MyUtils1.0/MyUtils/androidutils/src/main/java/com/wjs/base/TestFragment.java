package com.wjs.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.wjs.manager.FragmentUtils;
import com.wjs.wenyan.androidutils.R;

/**
 * Created by WJS on 2016/11/21.
 */

public class TestFragment  extends Fragment
{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.inject(this,R.layout.support_simple_spinner_dropdown_item);
        FragmentUtils.inject(this,new TextView(getContext()));
        FragmentUtils.inject(this,LayoutInflater.from(getContext()),null,3);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentUtils.unInject(this,new TextView(getContext()));
    }
}
