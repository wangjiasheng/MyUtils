package com.wjs.updatelib;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MyService extends IntentService {
    public static final String URLPath = "URL";
    InstallApkNotification notificationControl;
    public MyService()
    {
        super("MyService");
    }
    @Override
    public void onCreate()
    {
        super.onCreate();
    }
    @Override
    protected void onHandleIntent(Intent intent)
    {
        String urlPath=intent.getStringExtra(URLPath);
        String filePath=createFilePath();
        if(Util.isNotNull(filePath))
        {
            notificationControl = new InstallApkNotification(Util.getAppName(this),this, Util.getAppIcon(this));
            downApk(urlPath,filePath);
            chmod(filePath);
            installApk(filePath, this);
            notificationControl.cancleNotifacation();
        }
    }
    public String createFilePath()
    {
        String filePath = null;
        File file = getExternalFilesDir("/");
        if(file != null) {
            filePath = file.getAbsolutePath() + File.separator + "Updata.apk";
        } else {
            filePath = getFilesDir().getAbsolutePath() + File.separator + "Updata.apk";
        }
        return filePath;
    }
    public void downApk(String urlPath,String savePath)
    {
        InputStream stream = null;
        FileOutputStream fos = null;
        try
        {
            URL e = new URL(urlPath);
            URLConnection openConnection = e.openConnection();
            openConnection.setDoInput(true);
            openConnection.setDoOutput(false);
            //openConnection.setRequestProperty("Cookie", this.cookie);
            stream = openConnection.getInputStream();
            int available = openConnection.getContentLength();
            int totle = 0;
            fos = new FileOutputStream(new File(savePath));
            byte[] by = new byte[102400];
            boolean len = true;

            int current;
            while((current = stream.read(by)) != -1) {
                totle += current;
                fos.write(by, 0, current);
                notificationControl.updateNotification((int)(100.0F * (float)totle / (float)available));
            }

            fos.flush();
        }
        catch (MalformedURLException var25) {
            var25.printStackTrace();
        } catch (IOException var26) {
            var26.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException var24) {
                    var24.printStackTrace();
                }
            }

            if(stream != null) {
                try {
                    stream.close();
                } catch (IOException var23) {
                    var23.printStackTrace();
                }
            }

        }
    }
    public void chmod(String filepath)
    {
        String[] command = new String[]{"chmod", "777",filepath};
        ProcessBuilder builder = new ProcessBuilder(command);
        try
        {
            builder.start();
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }
    public static void installApk(String urlPath, Context context) {
        Intent apkIntent = new Intent();
        apkIntent.addFlags(268435456);
        apkIntent.setAction("android.intent.action.VIEW");
        File apkFile = new File(urlPath);
        Log.i("jone", "apk length " + apkFile.length());
        Uri uri = Uri.fromFile(apkFile);
        apkIntent.setDataAndType(uri, "application/vnd.android.package-archive");
        context.startActivity(apkIntent);
    }
}
