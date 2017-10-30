package com.wjs.network.upload;

import android.content.Context;
import android.os.AsyncTask;

import com.wjs.network.http.HttpUtils;
import com.wjs.network.json.HttpMethod;
import com.wjs.network.task.HttpTaskCallback;
import com.wjs.network.task.Message;
import com.wjs.network.task.Response;
import com.wjs.utils.NetworkUtils;
import com.wjs.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 家胜 on 2016/6/29.
 */
public class FileUpload extends AsyncTask<Void,Void,Message>
{
    private HttpTaskCallback callback;
    private String requestURL;
    private Map<String,String> requestParam;
    private Map<String,File> fileparam;
    private Context context;
    public FileUpload(Context context, String requestURL, Map<String,String> requestParam,Map<String,File> fileparam, HttpTaskCallback callback)
    {
        super();
        this.context=context;
        this.callback=callback;
        this.requestURL=requestURL;
        this.requestParam=requestParam;
        this.fileparam=fileparam;
    }
    public FileUpload submit()
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
            if(fileparam!=null)
            {
                fileparam.clear();
            }
            fileparam=null;
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
    public static FileUpload post(Context context, String requestURL, Map<String,String> requestParam,Map<String,File> fileparam, HttpTaskCallback callback)
    {
        try {
            FileUpload fileUpload = new FileUpload(context, requestURL, requestParam,fileparam,callback).submit();
            return fileUpload;
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
        Message message=new Message();
        try {
            if (callback != null) {
                callback.onSleeping();
            }
            long time = System.currentTimeMillis();
            StringBuilder builder = new StringBuilder();
            if (requestParam != null) {
                Set<Map.Entry<String, String>> set = requestParam.entrySet();
                Iterator<Map.Entry<String, String>> iterator = set.iterator();
                if (StringUtils.isNotNull(requestURL)) {
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> entry = iterator.next();
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (callback != null) {
                            value = callback.onLockParams(key,value);
                            if(value==null)
                            {
                                if(callback!=null)
                                {
                                    message.setStatus(false);
                                    message.setCode(1007);
                                    message.setMessage("数据加密或解密失败");
                                    return message;
                                }
                            }
                        }
                        if (builder.length()==0)
                        {
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
                }
                if (callback != null) {
                    if (requestURL.indexOf("?") > 0) {
                        callback.onRequestURL(requestURL + "&" + builder.toString());
                    } else {
                        callback.onRequestURL(requestURL + "?" + builder.toString());
                    }
                }
            }
            if (!isCancelled())
            {
                List<String> sCookie =null;
                if(callback!=null)
                {
                    sCookie = callback.onLoadCookie();
                }
                String requestResult = null;
                if (NetworkUtils.isNetworkAvailable(context) && StringUtils.isNotNull(requestURL))
                {
                    Response response=new Response();
                    UploadImage uploadImage=new UploadImage(requestResult);
                    if(requestParam!=null)
                    {
                       Set<String> keySet=requestParam.keySet();
                        Iterator<String> iterator=keySet.iterator();
                        while(iterator.hasNext())
                        {
                            String key=iterator.next();
                            String value=requestParam.get(key);
                            uploadImage.addTextParameter(key,value);
                        }
                    }
                    if(fileparam!=null)
                    {
                        Set<String> keySet=fileparam.keySet();
                        Iterator<String> iterator=keySet.iterator();
                        while(iterator.hasNext())
                        {
                            String key=iterator.next();
                            File value=fileparam.get(key);
                            uploadImage.addFileParameter(key,value);
                        }
                    }
                   byte[] bytes=uploadImage.send();
                    String result = new String(bytes);
                    response.setResult(result);
                    response.setCookie(new ArrayList<String>());
                    if(response!=null)
                    {
                        requestResult= response.getResult();
                        List<String> mCookei=response.getCookie();
                        if(callback!=null)
                        {
                            if(mCookei!=null)
                            {
                                callback.onSaveCookie(mCookei);
                            }
                            else
                            {
                                callback.onSaveCookie(new ArrayList<String>());
                            }

                        }
                    }
                    else
                    {
                        message.setStatus(false);
                        message.setCode(1006);
                        message.setMessage("服务器返回数据为空,可能网络不稳定或者服务器不可用");
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
                if (!isCancelled()) {
                    if (StringUtils.isNotNull(requestResult))
                    {
                        if (callback != null)
                        {
                            if (!isCancelled())
                            {
                                Object t = callback.onCreateBean(requestResult);
                                if (callback != null)
                                {
                                    callback.onBackground(System.currentTimeMillis() - time);
                                }
                                message.setStatus(true);
                                message.setData(t);
                                return message;
                            }
                        }
                    }
                    else
                    {
                        if (callback != null)
                        {
                            callback.onBackground(System.currentTimeMillis() - time);
                            message.setStatus(false);
                            message.setCode(1004);
                            message.setMessage("服务器返回数据为空,可能网络不稳定或者服务器不可用");
                            return message;
                        }
                    }
                }
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
            message.setMessage("Exception");
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            message.setStatus(false);
            message.setCode(1002);
            message.setMessage("Error");
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            message.setStatus(false);
            message.setCode(1003);
            message.setMessage("Throwable");
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
}
