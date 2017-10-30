package com.wjs.utils;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{
	/**
	 * 判断一个字符串是否为空
	 * @param str1 需要判断的字符串
	 * @return 该字符串是否为空
	 */
	public static boolean isNotNull(String str1)
	{
		if(str1!=null&&!str1.equalsIgnoreCase(""))
		{
			return true;
		}
		return false;
	}
	/**
	 * 判断两个字符串是否相等
	 * @param str1 需要判断的第一个字符串
	 * @param str2 需要判断的第二个字符串
	 * @return 判断两个字符串是否相等
	 */
	public static boolean equals(String str1,String str2)
	{
		if(str1!=null&&str1.equalsIgnoreCase(str2))
		{
			return true;
		}
		return false;
	}
	/**
	 * 讲一个字符串进行替换
	 * @param needinsert 需要替换的字符串 例如:我是{0}{1}{2}，我的qq是314232332
	 * @param insert 替换的字符串 例如:new Object[]{王,加,胜};
	 * @return 替换后的字符串 例如:我是王家胜，我的qq是314232332
	 */
	public static String insertString(String needinsert,Object[] insert)
	{
		return MessageFormat.format(needinsert,insert);
	}

	/**
	 * @param email 邮箱地址
	 * @return boolan 是否是电子邮箱
     */
	public static boolean isEmail(String email)
	{
		String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * @param phone 电话号码
	 * @return boolean 是否是电话号码
     */
	public static boolean isPhoneNumber(String phone)
	{
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(phone);
		return m.matches();
	}

	/**
	 * @param date 日期
	 * @return boolean 判断是不是日期
     */
	public static boolean isDate(String date)
	{
		Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(date);
		return m.matches();
	}

	/**
	 * @param ip ip地址
	 * @return boolean 判断是不是ip地址
     */
	public static boolean isIp(String ip)
	{
		Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher m = pattern.matcher(ip);
		return m.matches();
	}
}
