package com.wjs.network.exception;

import com.wjs.annotation.Network;

/**
 * Created by WJS on 2017/7/24.
 */

public class NetworkNotUseException extends Exception
{
    public NetworkNotUseException()
    {
        super("网络不可用");
    }
}
