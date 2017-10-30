package com.wjs.bean;

import android.content.Intent;

public class ActivityInfo
{
	private String packageName=null;
	private String activityName=null;
	private String lable=null;
	public ActivityInfo() {
	}
	public ActivityInfo(String packageName, String activityName,
			String lable) {
		super();
		this.packageName = packageName;
		this.activityName = activityName;
		this.lable = lable;
	}
	public String getPackageName() {
		return packageName;
	}
	public String getActivityName() {
		return activityName;
	}
	public CharSequence getLable() {
		return lable;
	}
	public Intent getIntent()
	{
		Intent intent=new Intent();
		intent.setClassName(packageName,activityName);
		return intent;
	}
}