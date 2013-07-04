/**
 * 文件名：SystemUtil.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：系统工具类
 * 修改人：xtuali
 * 修改内容：新增
 * 修改时间：Feb 19, 2012
 */
package org.webteam.util;

/**
 * 系统工具类
 * 系统相关操作
 * @author    xtuali
 * @version   1.0  Feb 19, 2012
 */

public class SystemUtil {
	private final static String SYSTEMLOCATION="systemConfigLocation";
	private final static String ROOTLOCATION="rootLocation";
	private final static String MAXTABCOUNT="maxTabCount";//页面最大标签数
	private final static String LOGINSIND = "loginsInd";
	
	private static String systemLocation = null;
	private static String rootLocation = null;
	private static String maxTabCount;
	private static String loginsInd = null;		//同一用户多处登录标识
	
	
	/**
	 * 获取系统参数
	 * @author: xtuali
	 * @param  key   系统环境变量key
	 * @return 环境变量值
	 */
	public static String getProperties(String key){
		return System.getProperty(key);
	}
	
	/**
	 * 获取系统根路径
	 * @author: xtuali
	 * @return 系统根路径
	 */
	public static String getRootLocation(){
		if(rootLocation == null)rootLocation=getProperties(ROOTLOCATION);
		return rootLocation;
	}
	
	/**
	 * 获取系统路径
	 * @author: xtuali
	 * @return 系统路径
	 */
	public static String getSystemLocation(){
		if(systemLocation == null)systemLocation=getProperties(SYSTEMLOCATION);
		return systemLocation;
	}
	/**
	 * 最大标签数
	 * @return
	 */
	public static String getMaxTabCount(){
		if(maxTabCount == null)maxTabCount=getProperties(MAXTABCOUNT);
		return maxTabCount;
	}
	
	/**
	 * 是否允许同一用户多处登录
	 * 修改日期：2012-8-19
	 * @author: xtuali
	 * @return
	 */
	public static String getLoginsInd() {
		if(loginsInd == null) {
			loginsInd = getProperties(LOGINSIND);
		}
		return loginsInd;
	}
}
