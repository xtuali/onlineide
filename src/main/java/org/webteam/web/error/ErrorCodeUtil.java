/**
 * 文件名：ErrorCodeUtil.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：错误码工具类
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：2012-3-19
 */
package org.webteam.web.error;

import java.util.Locale;

import org.webteam.util.ApplicationContextUtil;

/**
 * 错误码工具类
 * 进行错误码加载和处理
 * @author    xtuali
 * @version   1.0  2012-3-19
 */

public class ErrorCodeUtil {
	private final static String MODULE_NAME = "ACL";
	private final static Locale LOCALE = Locale.CHINA;
	private final static String REASON = "REASON";
	private final static String SOLUTION = "SOLUTION";
		
	/**
	 * 
	 * 从国际化文件取出对应键值错误码内容
	 * 修改日期：2012-3-19
	 * @author: xtuali
	 * @param errorCode 错误码
	 * @param arg 参数
	 * @return
	 */
	public static String getMessage(String errorCode,Object[] arg){
		return ApplicationContextUtil.getApplicationContext().getMessage(errorCode, arg, LOCALE);
	}
	
	/**
	 * 
	 * 获取错误码描述
	 * 修改日期：2012-3-19
	 * @author: xtuali
	 * @param errorCode 错误码
	 * @param arg 参数
	 * @return
	 */
	public static String getErrorMessage(String errorCode,Object[] arg){
		return getMessage(formatStr(errorCode),arg);
	}
	
	/**
	 * 
	 * 错误码格式化，如果长度小于5的话则前面加0
	 * 修改日期：2012-3-19
	 * @author: xtuali
	 * @param errorCode 错误码
	 * @return
	 */
	public static String formatStr(String errorCode){
		String t_errorCode = errorCode;
		if(t_errorCode.length()<5){
			t_errorCode =MODULE_NAME + String.format("%05d", t_errorCode);
		}
		return t_errorCode;
	}
	
	/**
	 * 
	 * 获取错误原因
	 * 修改日期：2012-3-19
	 * @author: xtuali
	 * @param errorCode 错误码
	 * @return
	 */
	public static String getErrorMessageReason(String errorCode){
		return getMessage(formatStr(errorCode)+"."+REASON,null);
	}
	
	/**
	 * 
	 * 获取错误解决办法
	 * 修改日期：2012-3-19
	 * @author: xtuali
	 * @param errorCode 错误码
	 * @return
	 */
	public static String getErrorMessageSolution(String errorCode){
		return getMessage(formatStr(errorCode)+"."+SOLUTION,null);
	}
}
