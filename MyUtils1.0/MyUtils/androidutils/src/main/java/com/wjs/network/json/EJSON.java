package com.wjs.network.json;

/**
 * Created by WJS on 2016/9/7.
 */
public enum EJSON
{
    /**
     * 网络连接错误
     */
    NETWORKNOTCONNECT
    {
        public String getName()
        {
            return "网络连接错误";
        }
    },
    /**
     * 请求成功
     */
    OK
    {
        public String getName()
        {
            return "请求成功";
        }
    },
    /**
     * json解析错误
     */
    JSONPARSEERROR
    {
        public String getName()
        {
            return "json解析错误";
        }
    };
    public abstract String getName();
}
