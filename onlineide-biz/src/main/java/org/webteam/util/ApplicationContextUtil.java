/**
 * 文件名：DynamicClassUtil.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：动态生成类工具
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 18, 2012
 */
package org.webteam.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;


/**
 * 动态生成类工具
 * 在目前框架下为通过bean的ID获取实例
 * @author    xtuali
 * @version   1.0  Feb 18, 2012
 */

public class ApplicationContextUtil implements ApplicationContextAware {
	private static ApplicationContext context = null;
	private static Logger logger = Logger.getLogger(ApplicationContextUtil.class);
	private final static String SESSION_FACTORY_BEAN="sessionFactory";
	
	public static <T> T getInstanceByBeanId(String beanId, Class<T> t){
		return context.getBean(beanId,t);
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
		logger.debug("load the spring context in the util");
	}
	
	public static ApplicationContext getApplicationContext(){
		return context;
	}
	
	public static Session getSession(){
		SessionFactory sessionFactory  = getInstanceByBeanId(SESSION_FACTORY_BEAN,SessionFactory.class);
		return sessionFactory.getCurrentSession();
	}
	
	public static Session openSession() {
		SessionFactory sessionFactory  = getInstanceByBeanId(SESSION_FACTORY_BEAN,SessionFactory.class);
		return sessionFactory.openSession();
	}
}
