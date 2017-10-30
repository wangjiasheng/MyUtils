package com.wjs.lrclib.view;



public class TimeUtils {
	public static final long ONEHOUR=1000l*60l*60l;
	public static final long MINUTE=1000l*60l;
	public static final long SECONDS=1000l*1l;
	public static String getPlayerPlayTimeOOMMSS(long time)
	{
		long hour=0;
		long minte=0;
		long second=0;
		String os=null;
		String ms=null;
		String ss=null;
		if(time/ONEHOUR>0)
		{
			hour=time/ONEHOUR;
			long temp=time%ONEHOUR;
			if(temp/MINUTE>0)
			{
				minte=temp/MINUTE;
				long temp2=temp%MINUTE;
				if(temp2/SECONDS>0)
				{
					second=temp2/SECONDS;
				}
			}
			else if(temp/SECONDS>0)
			{
				second=temp/SECONDS;
			}
		}
		else if(time/MINUTE>0)
		{
			minte=time/MINUTE;
			long temp=time%MINUTE;
			if(temp/SECONDS>0)
			{
				second=temp/SECONDS;
			}
		}
		else if(time/SECONDS>0)
		{
			second=time/SECONDS;
		}
		if(hour>9)
		{
			os=String.valueOf(hour);
		}
		else
		{
			os="0"+String.valueOf(hour);
		}
		if(minte>9)
		{
			ms=String.valueOf(minte);
		}
		else
		{
			ms="0"+String.valueOf(minte);
		}
		if(second>9)
		{
			ss=String.valueOf(second);
		}
		else
		{
			ss="0"+String.valueOf(second);
		}
		return os+":"+ms+":"+ss;
	}
	public static String getPlayerPlayTimeMMSS(long timemi) {
		String hourString = null;
		String minString = null;
		int hour = 0;
		int minute = 0;

		hour = (int) Math.floor(timemi / MINUTE);
		double less = timemi % MINUTE;
		if (less > 0) {
			minute = (int) Math.floor(less / SECONDS);
		}
		if(hour>9)
		{
			hourString=""+hour;
		}
		else
		{
			hourString="0"+hour;
		}
		if (minute > 9) {
			minString = ""+minute;
		} else {
			minString = "0" + minute;
		}
		return hourString+":"+minString;
	}
}