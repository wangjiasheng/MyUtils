package wanyan.com.networkmodel;

import android.content.Context;
import android.os.AsyncTask;
import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import wanyan.com.networkmodel.util.HttpUtils;
import wanyan.com.networkmodel.util.NetworkUtils;
import wanyan.com.networkmodel.util.StringUtils;

/**
 * Created by 家胜 on 2016/6/29.
 */
public class HttpTask extends AsyncTask<Void,Void,Message>
{
    @Inherited  //可以被继承
    public @interface MyAnnotation {
        String value();
    }
    @MyAnnotation(value = "网络请求")
    public static abstract class HttpTaskCallback<T>
    {
        @MyAnnotation(value = "后台线程，将参数加密")
        public String onLockParams(String params)
        {
            return params;
        }
        //UI线程
        public void onShowProgress(){};
        //后台线程
        public void requestURL(String url) {}
        //UI线程
        public abstract void onSucess(T bean,List<String> sCookie);
        //后台线程
        public Object onCreateBean(String requestResult){return requestResult;};
        //UI线程
        public abstract void onFaield();
        //UI线程
        public void onHideProgress(){};
        //UI线程
        public void onCancelled(){};
        //后台线程
        public void onBackground(long runtime){};
        //后台线程
        public void onSleeping(){};
    }
    private HttpTaskCallback callback;
    private String requestURL;
    private Map<String,String> requestParam;
    private Context context;
    private List<String> sCookie;
    public HttpTask(Context context, String requestURL, Map<String,String> requestParam, List<String> sCookie, HttpTaskCallback callback)
    {
        super();
        this.context=context;
        this.callback=callback;
        this.requestURL=requestURL;
        this.requestParam=requestParam;
        this.sCookie=sCookie;
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
            sCookie = null;
        }
        catch (NullPointerException ex)
        {
            ex.printStackTrace();
            if(callback!=null)
            {
                callback.onHideProgress();
                callback.onFaield();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield();
            }
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield();
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield();
            }
        }
    }
    public static HttpTask post(Context context, String requestURL, Map<String,String> requestParam, List<String> sCookie, HttpTaskCallback callback)
    {
        try {
            HttpTask httpTask = new HttpTask(context, requestURL, requestParam,sCookie, callback).submit();
            return httpTask;
        }
        catch (NullPointerException ex)
        {
            ex.printStackTrace();
            if(callback!=null)
            {
                callback.onHideProgress();
                callback.onFaield();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield();
            }
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield();
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield();
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
                callback.onFaield();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield();
            }
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield();
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            if(callback!=null) {
                callback.onHideProgress();
                callback.onFaield();
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
                            value = callback.onLockParams(value);
                            if(value==null)
                            {
                                if(callback!=null)
                                {
                                    message.setStatus(false);
                                    message.setCode(1000);
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
                        callback.requestURL(requestURL + "&" + builder.toString());
                    } else {
                        callback.requestURL(requestURL + "?" + builder.toString());
                    }
                }
            }
            if (!isCancelled()) {
                String requestResult = null;
                if (NetworkUtils.isNetworkAvailable(context) && StringUtils.isNotNull(requestURL)) {
                    Response response= HttpUtils.doPost(this,requestURL,builder.toString(),sCookie,10);
                    if(response!=null)
                    {
                        requestResult= response.getResult();
                        List<String> mCookei=response.getCookie();
                        if(mCookei!=null)
                        {
                            message.setsCookie(mCookei);
                        }
                        else
                        {
                            message.setsCookie(new ArrayList<String>());
                        }
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
            message.setCode(1000);
            message.setMessage("Exception");
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            message.setStatus(false);
            message.setCode(1000);
            message.setMessage("Error");
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            message.setStatus(false);
            message.setCode(1000);
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
                    callback.onSucess(aVoid.getData(),aVoid.getsCookie());
                    callback.onHideProgress();
                }
            }
            else
            {
                if (callback != null)
                {
                    callback.onFaield();
                    callback.onHideProgress();
                }
            }
        }
        catch (NullPointerException ex)
        {
            ex.printStackTrace();
            if(callback!=null)
            {
                callback.onFaield();
                callback.onHideProgress();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            callback.onFaield();
            callback.onHideProgress();
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            callback.onFaield();
            callback.onHideProgress();
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            callback.onFaield();
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
                callback.onFaield();
                callback.onHideProgress();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            callback.onFaield();
            callback.onHideProgress();
        }
        catch (Error ex)
        {
            ex.printStackTrace();
            callback.onFaield();
            callback.onHideProgress();
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            callback.onFaield();
            callback.onHideProgress();
        }
    }
}
