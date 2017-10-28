package com.wjs.wenyan.myutils.manager;

import android.widget.BaseAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by WJS on 2017/7/25.
 */

public abstract class mPulldownRefreshCallback
{
    /**
     * UI 线程 第一次显示progressBar
     */
    public void showFirstProgress(){};
    public void hideFirstProgress(){};
    public abstract BaseAdapter getAdapter();
    public abstract String getRequestURL();
    public String getPageSizeKey(){return "size";};
    public String getPageNumKey(){return "num";};
    public abstract Object parseJSON(String requestResult);
    public abstract String getCookie();
    public Map<String,String> getParams(){return null;};
    public void networkNotUse(){};
    public abstract void onFaile(Exception ex);
    public void onRequestURL(String requestURL){};
}
