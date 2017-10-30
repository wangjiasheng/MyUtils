package com.wjs.network.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.wjs.annotation.Network;
import com.wjs.network.exception.LockParamsExeption;
import com.wjs.network.exception.ReturnNollStringException;
import com.wjs.network.exception.ReturnNullException;
import com.wjs.network.http.HttpUtils;
import com.wjs.network.json.HttpMethod;
import com.wjs.utils.NetworkUtils;
import com.wjs.utils.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Created by 家胜 on 2016/6/29.
 */
public class HttpTask extends AsyncTask<Void,Void,Message>
{
    private HttpTaskCallback callback;
    private String requestURL;
    private Map<String,String> requestParam;
    private Context context;
    private HttpMethod method;
    public HttpTask(Context context, HttpMethod method, String requestURL, Map<String,String> requestParam, HttpTaskCallback callback)
    {
        super();
        this.context=context;
        this.callback=callback;
        this.requestURL=requestURL;
        this.requestParam=requestParam;
        this.method=method;
    }
    public HttpTask submit()
    {
        execute();
        return this;
    }
    public void cancle()
    {
        try
        {
            cancel(true);
            context = null;
            callback = null;
            requestURL = null;
            if(requestParam!=null) {
                requestParam.clear();
            }
            requestParam = null;
        }
        catch (NullPointerException ex)
        {
            ex.printStackTrace();
            if(callback!=null)
            {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
    }
    public static HttpTask post(Context context, String requestURL,HttpTaskCallback callback)
    {
        return post(context,requestURL,null,callback);
    }
    public static HttpTask post(Context context, String requestURL, Map<String,String> requestParam,HttpTaskCallback callback)
    {
        try {
            HttpTask httpTask = new HttpTask(context,HttpMethod.POST, requestURL, requestParam,callback).submit();
            return httpTask;
        }
        catch (NullPointerException ex)
        {
            ex.printStackTrace();
            if(callback!=null)
            {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        return null;
    }
    public static HttpTask get(Context context, String requestURL,HttpTaskCallback callback)
    {
        return get(context,requestURL,null,callback);
    }
    public static HttpTask get(Context context, String requestURL,Map<String,String> requestParam,HttpTaskCallback callback)
    {
        try {
            HttpTask httpTask = new HttpTask(context,HttpMethod.GET, requestURL, requestParam,callback).submit();
            return httpTask;
        }
        catch (NullPointerException ex)
        {
            ex.printStackTrace();
            if(callback!=null)
            {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try {
            if (callback != null) {
                callback.onShowProgress();
            }
        }
        catch (NullPointerException ex)
        {
            ex.printStackTrace();
            if(callback!=null)
            {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield(ex);
            }
        }
    }

    @Override
    protected Message doInBackground(Void... params)
    {
        Message message = new Message();
        try {
            if (callback != null) {  //首先执行暂停等待操作,例如某些地方需要让progressbar显示的更久一些
                callback.onSleeping();
            }
            long time = System.currentTimeMillis();//用于需要后台执行10s如果后台提前完成在onSleping(long)返回剩余时间
            if (NetworkUtils.isNetworkAvailable(context)) {//判断网络是否可用
                if (requestURL != null) {
                    return getRequestNetwork(time,message);
                } else {
                    message.setStatus(false);
                    message.setCode(1106);
                    message.setMessage("URL为空");
                    return message;
                }
            }
            else
            {
                message.setStatus(false);
                message.setCode(1005);
                message.setMessage("网络不可用");
                return message;
            }
        }
        catch (NullPointerException ex)
        {
            ex.printStackTrace();
            message.setStatus(false);
            message.setCode(1000);
            message.setMessage("NullPointerException");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            message.setStatus(false);
            message.setCode(1001);
            message.setMessage(ex.getMessage());
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            message.setStatus(false);
            message.setCode(1002);
            message.setMessage(ex.getMessage());
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            message.setStatus(false);
            message.setCode(1003);
            message.setMessage(ex.getMessage());
        }
        return message;
    }
    @Override
    protected void onPostExecute(Message aVoid)
    {
        super.onPostExecute(aVoid);
        try
        {
            if(isCancelled())
            {
                return;
            }
            if (aVoid != null&&aVoid.isStatus())
            {
                if (callback != null)
                {
                    callback.onSucess(aVoid.getData());
                    callback.onHideProgress();
                }
            }
            else
            {
                if (callback != null)
                {
                    if(aVoid!=null) {
                        callback.onFaield(new Exception(aVoid.getMessage()));
                    }
                    else
                    {
                        callback.onFaield(new Exception("服务器返回数据为空"));
                    }
                    callback.onHideProgress();
                }
            }
        }
        catch (NullPointerException ex)
        {
            ex.printStackTrace();
            if(callback!=null)
            {
                callback.onFaield(ex);
                callback.onHideProgress();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            callback.onFaield(ex);
            callback.onHideProgress();
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            callback.onFaield(ex);
            callback.onHideProgress();
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            callback.onFaield(ex);
            callback.onHideProgress();
        }
    }
    @Override
    protected void onCancelled() {
        super.onCancelled();
        try {
            if (callback != null) {
                callback.onCancelled();
                callback.onHideProgress();
            }
        }
        catch (NullPointerException ex)
        {
            ex.printStackTrace();
            if(callback!=null)
            {
                callback.onFaield(ex);
                callback.onHideProgress();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            callback.onFaield(ex);
            callback.onHideProgress();
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            callback.onFaield(ex);
            callback.onHideProgress();
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            callback.onFaield(ex);
            callback.onHideProgress();
        }
    }
    public String getParamsString(long time) throws LockParamsExeption {
        StringBuilder builder = new StringBuilder();
        if (requestParam != null)
        {
            Set<Map.Entry<String, String>> set = requestParam.entrySet();
            Iterator<Map.Entry<String, String>> iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                if (callback != null) {
                    value = callback.onLockParams(key, value);
                    if (value == null) {
                        if (callback != null) {
                            callback.onBackground(System.currentTimeMillis() - time);
                        }
                        throw new LockParamsExeption();
                    }
                }
                if (builder.length() == 0) {
                    builder.append(key);
                    builder.append("=");
                    builder.append(value);
                } else {
                    builder.append("&");
                    builder.append(key);
                    builder.append("=");
                    builder.append(value);
                }
            }
            return builder.toString();
        }
        return null;
    }
    public Message getRequestNetwork(long time,Message message) throws LockParamsExeption, ReturnNullException, ReturnNollStringException {
        String requestResult = null;
        Response response = null;

        String param=getParamsString(time);
        if(StringUtils.isNotNull(param))
        {
            if (callback != null) {
                if (requestURL.indexOf("?") > 0) {
                    callback.onRequestURL(requestURL + "&" + param);
                } else {
                    callback.onRequestURL(requestURL + "?" + param);
                }
                List<String> sCookie = null;
                if (callback != null) {
                    sCookie = callback.onLoadCookie();
                }
                if (method == HttpMethod.POST) {
                    response = HttpUtils.doPost(this, requestURL, param, sCookie, 10);
                } else {

                    if (requestURL.indexOf("?") > 0) {
                        response = HttpUtils.doGet(this, requestURL + "&" + param, sCookie, 10);
                    } else {
                        response = HttpUtils.doGet(this, requestURL + "?" + param, sCookie, 10);
                    }
                }
            }
        }
        else
        {
            if (callback != null) {
                callback.onRequestURL(requestURL);
            }
            List<String> sCookie = null;
            if (callback != null) {
                sCookie = callback.onLoadCookie();
            }
            if (method == HttpMethod.POST) {
                response = HttpUtils.doPost(this, requestURL, null, sCookie, 10);
            } else {
                response = HttpUtils.doGet(this, requestURL, sCookie, 10);
            }
        }
        if (response != null) {
            requestResult = response.getResult();
            List<String> mCookei = response.getCookie();
            if (callback != null) {
                if (mCookei != null) {
                    callback.onSaveCookie(mCookei);
                } else {
                    callback.onSaveCookie(new ArrayList<String>());
                }
            }
            if (!isCancelled()) {
                if (StringUtils.isNotNull(requestResult)) {
                    if (callback != null) {
                        if (!isCancelled()) {
                            Object t = callback.onCreateBean(requestResult);
                            if (callback != null) {
                                callback.onBackground(System.currentTimeMillis() - time);
                            }
                            message.setStatus(true);
                            message.setData(t);
                            return message;
                        }
                    }
                } else {
                    if (callback != null) {
                        callback.onBackground(System.currentTimeMillis() - time);
                    }
                    throw new ReturnNollStringException();
                }
            }
        } else {
            if (callback != null) {
                callback.onBackground(System.currentTimeMillis() - time);
            }
            throw new ReturnNullException();
        }
       return null;
    }
}
