package com.wjs.network.json;


import java.util.List;

/**
 * Created by WJS on 2016/9/7.
 */
public class BaseBean<T>
{
    private boolean status;
    private EJSON statuscode;
    private String message;
    private Object data;
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public EJSON getStatuscode() {
        return statuscode;
    }
    public void setStatuscode(EJSON json) {
        this.statuscode = json;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public BaseBean(boolean status, EJSON statuscode, String message) {
        this.status = status;
        this.statuscode = statuscode;
        this.message = message;
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}
