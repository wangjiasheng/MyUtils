package wanyan.com.networklib;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownInfo
{
		private String name;
		private String url;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public DownInfo(String name, String url) {
			super();
			this.name = name;
			this.url = url;
		}
		public static List<DownInfo> createBean(String requestString)
		{
			String pattern="<a href=\"(.*?)\".*?(诛仙青云志.EP.*?.torrent)</a>";
			Pattern pat1=Pattern.compile(pattern);
			Matcher matc = pat1.matcher(requestString);
			List<DownInfo> list=new ArrayList<DownInfo>();
			while(matc.find())
			{
				String name=matc.group(2);
				DownInfo info=new DownInfo(name,matc.group(1));
				list.add(info);
			}
			return list;
		}

	@Override
	public String toString() {
		return "DownInfo{" +
				"name='" + name + '\'' +
				", url='" + url + '\'' +
				'}';
	}
}