package com.wjs.utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
public class URLUtils
{
	public static Map<String, String> parseURL(String url)
	{
		Map<String, String> map=new HashMap<String, String>();
		if(StringUtils.isNotNull(url))
		{
			String[] split = url.split("[?&]");
			if(split.length>0)
			{
				map.put("host", split[0]);
			}
			for(int i=1;i<split.length;i++)
			{
				String[] strings = split[i].split("=");
				if(strings.length>1)
				{
					map.put(strings[0], strings[1]);
				}
			}
		}
		return map;
	}
	public static String parseMap(Map<String,String> map)
	{
		String url=map.get("host");
		if(StringUtils.isNotNull(url))
		{
			Set<Entry<String,String>> set = map.entrySet();
			Iterator<Entry<String, String>> iterator = set.iterator();
			while(iterator.hasNext())
			{
				Entry<String, String> next = iterator.next();
				String key = next.getKey();
				String value = next.getValue();
				if(StringUtils.isNotNull(key))
				{
					if(!key.equalsIgnoreCase("host"))
					{
						if(url.indexOf("?")!=-1)
						{
							url+="&"+key+"="+value;
						}
						else
						{
							url+="?"+key+"="+value;
						}
					}
				}
			}
		}
		return url;
	}
}
