package com.wjs.utils;

import android.app.Activity;
import android.content.Intent;

public class ReStartApplication
{
	/**
	 * 重启一个程序
	 * @param acitivyt 暂无
	 */
	public static void restartAppication(Activity acitivyt)
	{
		Intent i = acitivyt.getBaseContext().getPackageManager()
				.getLaunchIntentForPackage(acitivyt.getBaseContext().getPackageName());
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		acitivyt.startActivity(i);
	}
}
