/*
 * Copyright 2012-2020 the original author xtuali.
 *
 */

package org.webteam.dao;

import org.webteam.entity.User;

public interface UserDao extends DefaultDao<User> {
	User isExist(User user);
}
