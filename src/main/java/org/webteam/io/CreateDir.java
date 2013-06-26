package org.webteam.io;

import java.io.File;

public class CreateDir {

	public static void mkdir(String dir) {
		File file = new File(dir);
		if (!file.exists()){
			boolean result = file.mkdirs();
			System.out.println(result);
			System.out.println("路径:"+dir+"创建成功");
		}else{
			System.out.println("路径存在"+dir);
		}
	}
	public static void main(String[] args) {
		CreateDir.mkdir("D:/Program Files/tomcat/webapps/onlineide/programfile/haha/");
	}
}
