/*
 * Copyright 2012-2020 the original author xtuali.
 *
 */

package org.webteam.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.webteam.dao.UserDao;
import org.webteam.entity.User;
import org.webteam.util.StrUtil;

@Repository("userDao")
public class UserDaoImpl extends DefaultDaoImpl<User> implements UserDao {
	@Override
	public User isExist(User user) {
		List<User> list = findByTemplate(user);
		if(StrUtil.isEmptyCollection(list))
			return null;
		else
			return list.get(0);
	}
}
