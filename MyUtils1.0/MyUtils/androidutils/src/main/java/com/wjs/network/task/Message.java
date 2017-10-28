package com.wjs.network.task;

import java.util.List;

/**
 * Created by WJS on 2016/7/25.
 */
public class Message<T>
{
    private boolean status;
    private int code;
    private String message;
    private Object data;
    public Message()
    {
        status=false;
        code=1000;
        message="服务器数据异常";
    }
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
