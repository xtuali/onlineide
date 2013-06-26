package org.webteam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webteam.entity.User;
import org.webteam.service.IUserService;
import org.webteam.service.impl.UserServiceImpl;

@Controller("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@RequestMapping("/user/login.do")
	@ResponseBody
	public User login(User user){
		user = userService.login(user);
		System.out.println(user.getUserid());
		return user;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
}
