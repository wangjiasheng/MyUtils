package com.wjs.utils;

public class TimeUtils {
	public static final long ONEHOUR=1000l*60l*60l;
	public static final long MINUTE=1000l*60l;
	public static final long SECONDS=1000l;
	public static String getPlayerPlayTimeOMM(long time)
	{
		long o=0;
		long m=0;
		long s=0;
		String os=null;
		String ms=null;
		String ss=null;
		if(time/ONEHOUR>0)
		{
			o=time/ONEHOUR;
			long temp=time%ONEHOUR;
			if(temp/MINUTE>0)
			{
				m=temp/MINUTE;
				long temp2=temp%MINUTE;
				if(temp2/SECONDS>0)
				{
					s=temp2/SECONDS;
				}
			}
			else if(o/SECONDS>0)
			{
				s=o/SECONDS;
			}
		}
		else if(time/MINUTE>0)
		{
			m=time/MINUTE;
			long temp=time%MINUTE;
			if(temp/SECONDS>0)
			{
				s=temp/SECONDS;
			}
		}
		else if(time/SECONDS>0)
		{
			s=time/SECONDS;
		}
		if(o>9)
		{
			os=String.valueOf(o);
		}
		else
		{
			os="0"+String.valueOf(o);
		}
		if(m>9)
		{
			ms=String.valueOf(m);
		}
		else
		{
			ms="0"+String.valueOf(m);
		}
		if(s>9)
		{
			ss=String.valueOf(s);
		}
		else
		{
			ss="0"+String.valueOf(s);
		}
		return os+":"+ms+":"+ss;
	}
	public static String getPlayerPlayTimeOOMMSS(long timemi) {
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
