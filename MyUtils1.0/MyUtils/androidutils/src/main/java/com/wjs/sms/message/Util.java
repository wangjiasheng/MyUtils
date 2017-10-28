package com.wjs.sms.message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 家胜 on 2016/4/21.
 */
public class Util {
    public static String getYanZhengma(String message)
    {
        Pattern p6 = Pattern.compile("(?<![0-9])([0-9]{6})(?![0-9])");
        Pattern p4 = Pattern.compile("(?<![0-9])([0-9]{4})(?![0-9])");
        Matcher m6 = p6.matcher(message);
        if (m6.find()) {
            return m6.group(0);
        }
        Matcher m4 = p4.matcher(message);
        if (m4.find()) {
            return m4.group(0);
        }
        return "";
    }
}
