package org.webteam.io;

import java.io.File;

public class CreateDir {

	public static void mkdir(String dir) {
		File file = new File(dir);
		if (!file.exists()){
			boolean result = file.mkdirs();
			System.out.println(result);
		}else{
		}
	}
	public static void main(String[] args) {
		CreateDir.mkdir("D:/Program Files/tomcat/webapps/onlineide/programfile/haha/");
	}
}
