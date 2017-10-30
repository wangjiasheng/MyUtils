package com.wjs.lrclib.view;

import java.util.ArrayList;
import java.util.List;

public class LrcRow implements Comparable<LrcRow>{
    public final static String TAG = "LrcRow";
    public long time;
    public String content;
    public String strTime;
    public LrcRow(){}
    public LrcRow(String strTime,long time,String content){
        this.strTime = strTime;
        this.time = time;
        this.content = content;
    }
    public static List<LrcRow> createRows(String standardLrcLine){
        try{
            if(standardLrcLine.indexOf("[") != 0 || standardLrcLine.indexOf("]") != 9 ){
                return null;
            }
            int lastIndexOfRightBracket = standardLrcLine.lastIndexOf("]");
            String content = standardLrcLine.substring(lastIndexOfRightBracket + 1, standardLrcLine.length());
            
            // times [mm:ss.SS][mm:ss.SS] -> *mm:ss.SS**mm:ss.SS*
            String times = standardLrcLine.substring(0,lastIndexOfRightBracket + 1).replace("[", "-").replace("]", "-");
            String arrTimes[] = times.split("-");
            List<LrcRow> listTimes = new ArrayList<LrcRow>();
            for(String temp : arrTimes){
                if(temp.trim().length() == 0){
                    continue;
                }
                LrcRow lrcRow = new LrcRow(temp, timeConvert(temp), content);
                listTimes.add(lrcRow);
            }
            return listTimes;
        }catch(Exception e){
            return null;
        }
    }
    
    public static long timeConvert(String timeString){
        timeString = timeString.replace('.', ':');
        String[] times = timeString.split(":");
        // mm:ss:SS
        return Integer.valueOf(times[0]) * 60 * 1000 +
                Integer.valueOf(times[1]) * 1000 +
                Integer.valueOf(times[2]) ;
    }

    public int compareTo(LrcRow another) {
        return (int)(this.time - another.time);
    }
    
    
    
}