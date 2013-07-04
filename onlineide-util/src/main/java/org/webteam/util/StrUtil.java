/*
 * Copyright 2009-2010 the original author xtuali.
 *
 */
package org.webteam.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * 工具类
 */
public class StrUtil {

	/**
	 * 格式化日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateFormat(Date date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}
	
	public static String dateFormat(long date, String pattern) {
		return dateFormat(new Date(date), pattern);
	}
	
	public static Date dateParse(String date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			Date d = df.parse(date);
			return d;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 计算字符串长度，基于中文编码的情况下，即中文算2个字符
	 * 
	 * @param s
	 * @return
	 */
	public static int strlen(String s) {
		if (s == null) {
			return 0;
		}
        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
	}
	
	/**
	 * 判断字符串是否为null或空白字符
	 * 
	 * @param s 需要判断的字符串
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串是否为null或去掉行尾空白后是否为空白字符
	 * 
	 * @param s 需要判断的字符串
	 * @return
	 */
	public static boolean isEmptyTrim(String s) {
		if (s == null || s.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isNumeric(String s) {
		if (s == null || s.trim().length() == 0) return false;
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) return false;
		}
		return true;
	}


	public static boolean isEmptyCollection(Collection<?> paraList) {
		if (paraList == null || paraList.size() == 0) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * 根据表的字段名取得实体对应的属性名,判断主键
	 * @param tableFieldName
	 * @return
	 */
	public static String getEntityField(Class clazz, String fieldname){
		
		String filedName = getEntityFieldUsual(fieldname);
		
		//验证是否有这个字段
		try {
			clazz.getDeclaredField(filedName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			//若无此字段就是主键
			filedName = "id." + filedName;
		}
		return filedName;
	}
	
	/**
	 * 根据表的字段名取得实体对应的属性名
	 * @param tableFieldName
	 * @return
	 */
	public static String getEntityFieldUsual(String fieldname){
		
		StringBuffer _fieldname = new StringBuffer();
		String filedName = "";
		String[] _segmentsStrings = fieldname.toLowerCase().split("_");
		for(int j=0; j<_segmentsStrings.length; j++){
			String _fieldname_seg = "";
			if(j==0){
				_fieldname_seg = _segmentsStrings[j];
			}else{
				_fieldname_seg = _segmentsStrings[j].substring(0,1).toUpperCase()+_segmentsStrings[j].substring(1);
			}
			filedName = filedName+_fieldname_seg;
		}
		return filedName;
	}
	
	/**
	 * 根据表名获得实体名
	 * @param tableName
	 * @return
	 */
	public static String getEntityName(String tableName){
		StringBuffer _tableName = new StringBuffer();
		String[] _segmentTables = tableName.toLowerCase().split("_");
		for(int k=0;k<_segmentTables.length;k++){
			String _tablename_seg = "";
			if(k==0||k==1){
				_tablename_seg = _segmentTables[k].substring(0,1).toUpperCase()+_segmentTables[k].substring(1);
			}else {
				_tablename_seg = _segmentTables[k];
			}
			_tableName = _tableName.append(_tablename_seg);
		}
		return _tableName.toString();
	}
}
