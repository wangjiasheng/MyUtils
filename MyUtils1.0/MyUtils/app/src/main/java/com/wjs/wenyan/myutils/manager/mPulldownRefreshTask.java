package com.wjs.wenyan.myutils.manager;

import android.os.AsyncTask;

import com.wjs.network.json.HttpMethod;
import com.wjs.utils.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by WJS on 2017/7/25.
 */
public class mPulldownRefreshTask<T> extends AsyncTask<Void,Void,Object>
{
    int pageIndex=1;
    int pageSize=25;
    HttpMethod method;
    mPulldownRefreshCallback callback;
    PtrClassicFrameLayout mPtrview;
    T requestone;
    T requesttwo;
    public mPulldownRefreshTask(int pageIndex)
    {
        this.pageIndex=pageIndex;
    }
    public String getRequestParamString(Map<String,String> requestParams)
    {
        if(requestParams!=null) {
            StringBuffer parasbuilder = new StringBuffer();
            Set<String> set = requestParams.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = requestParams.get(key);
                parasbuilder.append("&" + key + "=" + value);
            }
            if (parasbuilder.length() > 0) {
                return parasbuilder.deleteCharAt(0).toString();
            }
        }
        return null;
    }
    @Override
    protected Object doInBackground(Void... params)
    {
        if(pageIndex==1)
        {
            Map<String,String> requestParams=callback.getParams();
            String requestParamString=getRequestParamString(requestParams);
            if(method== HttpMethod.GET)
            {
                String requestURL1;
                if(StringUtils.isNotNull(requestParamString))
                {
                    requestURL1=callback.getRequestURL()+"?"+callback.getPageNumKey()+"=1&"+callback.getPageSizeKey()+"="+pageSize+"&"+requestParamString;
                }
                else
                {
                    requestURL1=callback.getRequestURL()+"?"+callback.getPageNumKey()+"=1&"+callback.getPageSizeKey()+"="+pageSize;
                }
                callback.onRequestURL(requestURL1);
                String requestResult1=mPullDownRefreshNetwork.sendGet(requestURL1);
                if(StringUtils.isNotNull(requestResult1))
                {
                    requestone= (T) callback.parseJSON(requestResult1);
                }
                else
                {
                    ErrorMessge message=new ErrorMessge();
                    message.code=100;
                    message.message="服务器GET请求返回为空";
                   return message;
                }

                String requestURL2;
                if(StringUtils.isNotNull(requestParamString))
                {
                    requestURL2=callback.getRequestURL()+"?"+callback.getPageNumKey()+"=2&"+callback.getPageSizeKey()+"="+pageSize+"&"+requestParamString;
                }
                else
                {
                    requestURL2=callback.getRequestURL()+"?"+callback.getPageNumKey()+"=2&"+callback.getPageSizeKey()+"="+pageSize;
                }
                callback.onRequestURL(requestURL2);
                String requestResult2=mPullDownRefreshNetwork.sendGet(requestURL2);
                if(StringUtils.isNotNull(requestResult2))
                {
                    requesttwo= (T) callback.parseJSON(requestResult2);
                }
                else
                {
                    ErrorMessge message=new ErrorMessge();
                    message.code=100;
                    message.message="服务器POST请求返回为空";
                    return message;
                }
                return requestone;
            }
            else
            {
                String requestURL1;
                if(StringUtils.isNotNull(requestParamString))
                {
                    requestURL1=callback.getPageNumKey()+"=1&"+callback.getPageSizeKey()+"="+pageSize+"&"+requestParamString;
                }
                else
                {
                    requestURL1=callback.getPageNumKey()+"=1&"+callback.getPageSizeKey()+"="+pageSize;
                }
                callback.onRequestURL(callback.getRequestURL()+"?"+requestURL1);
                String requestResult1=mPullDownRefreshNetwork.sendPost(callback.getRequestURL(),requestURL1);
                if(StringUtils.isNotNull(requestResult1))
                {
                    requestone= (T) callback.parseJSON(requestResult1);
                }
                else
                {
                    ErrorMessge message=new ErrorMessge();
                    message.code=100;
                    message.message="服务器GET请求返回为空";
                    return message;
                }

                String requestURL2;
                if(StringUtils.isNotNull(requestParamString))
                {
                    requestURL2=callback.getRequestURL()+"?"+callback.getPageNumKey()+"=2&"+callback.getPageSizeKey()+"="+pageSize+"&"+requestParamString;
                }
                else
                {
                    requestURL2=callback.getRequestURL()+"?"+callback.getPageNumKey()+"=2&"+callback.getPageSizeKey()+"="+pageSize;
                }
                callback.onRequestURL(requestURL2);
                String requestResult2=mPullDownRefreshNetwork.sendGet(requestURL2);
                if(StringUtils.isNotNull(requestResult2))
                {
                    requesttwo= (T) callback.parseJSON(requestResult2);
                }
                else
                {
                    ErrorMessge message=new ErrorMessge();
                    message.code=100;
                    message.message="服务器POST请求返回为空";
                    return message;
                }
                return requestone;
            }
        }
        else //pageSise >1
        {
            requestone=requesttwo;
            Map<String,String> requestParams=callback.getParams();
            String requestParamString=getRequestParamString(requestParams);
            if(method== HttpMethod.GET) //get request
            {
                String requestURL1;
                if(StringUtils.isNotNull(requestParamString))
                {
                    requestURL1=callback.getRequestURL()+"?"+callback.getPageNumKey()+"="+(pageIndex+1)+"&"+callback.getPageSizeKey()+"="+pageSize+"&"+requestParamString;
                }
                else
                {
                    requestURL1=callback.getRequestURL()+"?"+callback.getPageNumKey()+"="+(pageIndex+1)+"&"+callback.getPageSizeKey()+"="+pageSize;
                }
                callback.onRequestURL(requestURL1);
                String requestResult1=mPullDownRefreshNetwork.sendGet(requestURL1);
                if(StringUtils.isNotNull(requestResult1))
                {
                    requesttwo= (T) callback.parseJSON(requestResult1);
                }
                else
                {
                    ErrorMessge message=new ErrorMessge();
                    message.code=100;
                    message.message="服务器GET请求返回为空";
                    return message;
                }
                return requestone;
            }
            else
            {
                String requestURL1;
                if(StringUtils.isNotNull(requestParamString))
                {
                    requestURL1=callback.getPageNumKey()+"=1&"+callback.getPageSizeKey()+"="+pageSize+"&"+requestParamString;
                }
                else
                {
                    requestURL1=callback.getPageNumKey()+"=1&"+callback.getPageSizeKey()+"="+pageSize;
                }
                callback.onRequestURL(callback.getRequestURL()+"?"+requestURL1);
                String requestResult1=mPullDownRefreshNetwork.sendPost(callback.getRequestURL(),requestURL1);
                if(StringUtils.isNotNull(requestResult1))
                {
                    requesttwo= (T) callback.parseJSON(requestResult1);
                }
                else
                {
                    ErrorMessge message=new ErrorMessge();
                    message.code=100;
                    message.message="服务器POST请求返回为空";
                    return message;
                }
                return requestone;
            }
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(o==null)//请求结果为空
        {
            mPtrview.refreshComplete();
            return ;
        }
        if(o instanceof ErrorMessge)
        {
            callback.onFaile(new Exception(((ErrorMessge) o).message));
            mPtrview.refreshComplete();
        }
    }
}
