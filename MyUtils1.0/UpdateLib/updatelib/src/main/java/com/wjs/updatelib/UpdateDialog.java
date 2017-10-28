package com.wjs.updatelib;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 家胜 on 2016/3/18.
 */
public class UpdateDialog
{
    /**
     * @param context 上下文对象
     * @param serverVersion 升级的版本号码
     * @param serverName 上级的版本名称
     * @param updateMessage 升级的错误列表
     * @param downUrl 下载的ApkURL
     */
    public void updateApp(Context context, int serverVersion,String serverName,List<String> updateMessage, String downUrl) {
        int localVersion = Util.getVerCode(context);
        if (serverVersion > localVersion)
        {
            if(updateMessage==null||updateMessage.size()==0)
            {
                showConfigDialog(context,serverName,downUrl);
            }
            else
            {
                showConfigDialog(context,serverName,updateMessage,downUrl);
            }
        }
    }

    /**
     * @param context 上下文对象
     * @param versionName 上级的版本名称
     * @param downUrl 下载的ApkURL
     */
    private void showConfigDialog(final Context context,String versionName, final String downUrl) {
        StringBuffer sb = new StringBuffer();
        sb.append("当前版本:");
        sb.append(Util.getVerName(context));
        sb.append("\n");
        sb.append("发现新版本:");
        sb.append(versionName);
        sb.append("\n");
        sb.append("是否更新?");
        Dialog dialog = new AlertDialog.Builder(context).setTitle("软件更新").setMessage(sb.toString())
                .setPositiveButton("软件更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, MyService.class);
                        intent.putExtra(MyService.URLPath, downUrl);
                        context.startService(intent);
                    }
                })
                .setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * @param context 上下文对象
     * @param versionName 上级的版本名称
     * @param updateMessage 升级的错误列表
     * @param downUrl 下载的ApkURL
     */
    private void showConfigDialog(final Context context,String versionName,List<String> updateMessage, final String downUrl) {
        LinearLayout layout= (LinearLayout) LayoutInflater.from(context).inflate(R.layout.updatelayout,null);
        TextView currentVersion= (TextView) layout.findViewById(R.id.currentVersion);
        TextView newVersion= (TextView) layout.findViewById(R.id.newVersion);
        TextView newUpdate= (TextView) layout.findViewById(R.id.newUpdate);
        TextView newUpdateMessage= (TextView) layout.findViewById(R.id.newUpdateMessage);
        currentVersion.setText("当前版本:"+Util.getVerName(context));
        newVersion.setText("发现新版本:" + versionName);
        if(updateMessage!=null&&updateMessage.size()!=0)
        {
            StringBuilder builder=new StringBuilder();
            for (int i = 0;i<updateMessage.size();i++)
            {
                builder.append("【" + (i + 1) + "】" + updateMessage.get(i));
                if(i!=updateMessage.size()-1) {
                    builder.append("\r\n");
                }
            }
            newUpdateMessage.setText(builder.toString());
        }
        else
        {
            newUpdateMessage.setVisibility(View.GONE);
        }
        newUpdate.setText("是否更新?");
        Dialog dialog = new AlertDialog.Builder(context).setTitle("软件更新")
                .setPositiveButton("软件更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, MyService.class);
                        intent.putExtra(MyService.URLPath, downUrl);
                        context.startService(intent);
                    }
                })
                .setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setView(layout).create();
        dialog.show();
    }
}
