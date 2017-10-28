package com.wjs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import com.wjs.network.task.HttpTask;
import com.wjs.network.task.HttpTaskCallback;
import com.wjs.network.upload.FileUpload;
import com.wjs.network.upload.UploadImage;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by WJS on 2017/1/22.
 */

public class CollectPhoneInfo
{
    public static void sendScreenInfo(Activity context,String url,String phoneinfo)
    {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;
        Map<String,String> map=new LinkedHashMap<String,String>();
        map.put("width",""+width);
        map.put("height",""+height);
        map.put("phoneinfo",phoneinfo);
        HttpTask.post(context,url, map, new HttpTaskCallback() {
            @Override
            public void onSucess(Object bean) {
            }

            @Override
            public void onFaield(Throwable ex) {
                ex.printStackTrace();
            }
        });
    }
    public static Map<String,String> collectDeviceInfo(Context ctx) {
        Map<String, String> infos = new HashMap<String, String>();
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("wjs", "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d("wjs", field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e("wjs", "an error occured when collect crash info", e);
            }
        }
        return infos;
    }
    public static String mapToJson(Map<String,String> map)
    {
        StringBuffer buffer=new StringBuffer();
        buffer.append("{");
        Set<String> keyset=map.keySet();
        Iterator<String> iterator=keyset.iterator();
        while (iterator.hasNext())
        {
            String key=iterator.next();
            String value=map.get(key);
            buffer.append("\""+key+"\""+":"+"\""+value+"\",");
        }
        buffer.delete(buffer.length()-1,buffer.length());
        buffer.append("}");
        return buffer.toString();
    }
    public static void sendError(final String url, final String phoneinfo, final String errorPath)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                File file=new File(errorPath);
                File[] files=file.listFiles();
                try
                {
                    UploadImage uploadImage=new UploadImage(url);
                    for(int i=0;i<files.length;i++)
                    {
                        uploadImage.addFileParameter("file"+(i+1),files[i].getAbsoluteFile());
                    }
                    uploadImage.addTextParameter("phoneinfo",phoneinfo);
                    byte[] b = uploadImage.send();
                    String result = new String(b);
                    System.out.println(result);
                    for(int i=0;i<files.length;i++)
                    {
                        files[i].delete();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
