package org.webteam.action;

import javax.servlet.http.HttpServletRequest;

import org.webteam.annotation.Action;


@SuppressWarnings("serial")
@Action("loginAction")
public class LoginAction extends DefaultAction {

	private HttpServletRequest request;

	private String username;

	private String password;

	public String login() throws Exception {
		System.out.println(username+","+password);
		return "SUCCESS";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}
}
