package wanyan.com.networkmodel.util;

import java.text.MessageFormat;

public class StringUtils {
    public StringUtils() {
    }

    public static boolean isNotNull(String str1) {
        return str1 != null && !str1.equalsIgnoreCase("");
    }

    public static boolean equals(String str1, String str2) {
        return str1 != null && str1.equalsIgnoreCase(str2);
    }

    public static String insertString(String needinsert, Object[] insert) {
        return MessageFormat.format(needinsert, insert);
    }
}
