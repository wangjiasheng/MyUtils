package com.wjs.updatelib;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat.Builder;

public class InstallApkNotification {
    private NotificationManager mNotificationManager;
    private Builder mBuilder;
    private Context context;
    private int drawable;
    private final int NOTIFYCATIONID = 314232332;
    private String name;

    public InstallApkNotification(String name, Context context, int drawable) {
        this.context = context;
        this.drawable = drawable;
        this.name = name;
        this.initNotifycation();
    }

    private void initNotifycation() {
        this.mNotificationManager = (NotificationManager)this.context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.mBuilder = new Builder(this.context);
        this.mBuilder.setWhen(System.currentTimeMillis()).setSmallIcon(this.drawable);
    }

    public void updateNotification(int progress) {
        Notification mNotification = this.mBuilder.build();
        mNotification.flags = 2;
        this.mBuilder.setProgress(100, progress, false);
        this.mBuilder.setContentText("下载中...").setContentTitle(this.name);
        this.mNotificationManager.notify(NOTIFYCATIONID, mNotification);
    }

    public void cancleNotifacation() {
        this.mNotificationManager.cancel(NOTIFYCATIONID);
    }
}
