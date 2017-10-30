package com.wjs.network.exception;

/**
 * Created by WJS on 2017/7/24.
 */

public class ReturnNollStringException extends Exception
{
    public ReturnNollStringException()
    {
        super("服务器返回数据字符串为空");
    }
}
