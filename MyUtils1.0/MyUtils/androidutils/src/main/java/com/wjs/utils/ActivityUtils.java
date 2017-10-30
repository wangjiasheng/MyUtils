package com.wjs.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.wjs.bean.ActivityInfo;

public class ActivityUtils
{
	/**
	 * @param context 暂无
	 * @param action 需要读取的Activity的action
	 * @param category 需要读取的Activity的category
	 * @return 拥有相应action和category的activity集合
	 */
	public static List<ActivityInfo> queryActivitys(Context context,String action,String category)
	{
		ArrayList<ActivityInfo> intentList=new ArrayList<ActivityInfo>();
		Intent mainIntent = new Intent(action, null);
		mainIntent.addCategory(category);
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> matches = pm.queryIntentActivities(mainIntent,PackageManager.GET_ACTIVITIES);
		for (ResolveInfo match : matches)
		{
			ActivityInfo mActivityInfo=new ActivityInfo(match.activityInfo.packageName, match.activityInfo.name, match.loadLabel(pm).toString());
			intentList.add(mActivityInfo);
		}
		return intentList;
	}
}
