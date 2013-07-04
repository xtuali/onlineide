package org.webteam.service;

import org.springframework.stereotype.Service;
import org.webteam.entity.User;

@Service("userService")
public interface IUserService {
	
	User login(User user); 
}
