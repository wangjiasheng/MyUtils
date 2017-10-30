package com.wjs.lrclib.view;


import java.util.ArrayList;
import java.util.List;


public class FIXLengthLrcBuilder implements ILrcBuilder
{
	@Override
	public List<LrcRow> getLrcRows(String rawLrc) 
	{
	        return null;
	}
	public List<LrcRow> getLrcRows(String rawLrc,int fixlen,int timelen) 
	{
	     if(rawLrc == null || rawLrc.length() == 0)
	     {
	            return null;
	     }
	     List<LrcRow> rows = new ArrayList<LrcRow>();
	     int pagesize=(int)Math.ceil(rawLrc.length()/fixlen);
	     int times=(int)Math.ceil(timelen/pagesize);
	     for(int i=0;i<rawLrc.length();i=i+fixlen)
	     {
	    	 String substring;
	    	 if(i+fixlen>rawLrc.length())
	    	 {
	    		 substring = rawLrc.substring(i, rawLrc.length());
	    	 }
	    	 else
	    	 {
	    		 substring = rawLrc.substring(i, i+fixlen);
	    	 }
	    	 int st=(i/fixlen+1)*times;
	    	 if(st>timelen)
	    	 {
	    		 st=timelen;
	    	 }
	    	 String string = TimeUtils.getPlayerPlayTimeOOMMSS(st);
			 String[] split = string.split(":");
			 String str=split[1]+":"+split[2]+".00";
	    	 LrcRow row=new LrcRow(str,st,substring);
	    	 rows.add(row);
	     }
	     return rows;
	}
}
