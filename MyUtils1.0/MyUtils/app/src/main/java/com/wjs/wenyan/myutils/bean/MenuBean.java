package com.wjs.wenyan.myutils.bean;


import com.wjs.network.json.BaseBean;
import com.wjs.network.json.EJSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WJS on 2016/9/8.
 */
public class MenuBean
{
    private String name;
    private String tag;
    private String param;
    private String icon;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getParam() {
        return param;
    }
    public void setParam(String param) {
        this.param = param;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public static BaseBean getBean(String json)
    {
        BaseBean<MenuBean> bean=new BaseBean<MenuBean>(false,null,"");
        try
        {
            JSONArray array=new JSONArray(json);
            List<MenuBean> list=new ArrayList<MenuBean>();
            for(int i=0;i<array.length();i++)
            {
                JSONObject object=array.getJSONObject(i);
                String name=object.getString("name");
                String tag=object.getString("tag");
                String param=object.getString("param");
                String icon=object.getString("icon");
                MenuBean noti=new MenuBean();
                noti.setName(name);
                noti.setTag(tag);
                noti.setParam(param);
                noti.setIcon(icon);
                list.add(noti);
            }
            bean.setStatus(true);
            bean.setStatuscode(EJSON.OK);
            bean.setData(list);
            return bean;
        }
        catch(Exception e)
        {
            bean.setStatus(false);
            bean.setStatuscode(EJSON.JSONPARSEERROR);
        }
        return null;
    }
}
