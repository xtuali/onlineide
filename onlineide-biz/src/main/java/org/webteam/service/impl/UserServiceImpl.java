package org.webteam.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.webteam.dao.UserDao;
import org.webteam.entity.User;
import org.webteam.service.IUserService;
import org.webteam.util.PWD;

@Service("userService")
public class UserServiceImpl implements IUserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(User user) {
		User template = new User();
		template.setUsername(user.getUsername());
		logger.debug("登录...["+user.getUsername()+"]");
		
		template = userDao.isExist(template);
		if(template != null){
			logger.debug("用户["+user.getUsername()+"]存在，进行密码匹配");
			System.out.println(PWD.enc("xtuali"));
			if(PWD.enc(user.getUserpass()).equals(template.getUserpass())){
				logger.debug("用户["+user.getUsername()+"]存登录成功");
				user = template;
			}
		}
		return user;
	}
	
	

}
