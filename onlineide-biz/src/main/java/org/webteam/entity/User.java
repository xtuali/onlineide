package org.webteam.entity;

import java.io.Serializable;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class User implements Serializable {
	
	private Integer userid;//
	private String username;//
	private String userpass;//
	
	public Integer getUserid() {
		return this.userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	public java.lang.String getUserpass() {
		return this.userpass;
	}
	public void setUserpass(java.lang.String userpass) {
		this.userpass = userpass;
	}

	public User() {
	}

	public User(Integer userid) {
		this.userid = userid;
	}

	public User(Integer userid, java.lang.String username, java.lang.String userpass) {
		this.userid = userid;
		this.username = username;
		this.userpass = userpass;
	}
}
