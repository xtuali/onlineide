package org.webteam.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class AntDeploy {
	private static Object lock = new Object();
	public static void main(String[] args) throws Exception {
		String user = "xtuali";
		String fileName="test1.zip";
		deploy("compile",user,fileName);
	}

	public static boolean deploy(String task ,String user,String fileName) throws Exception {
		Process proc = null;
		String cmd = "ant.bat "+task+" -file "
				+ ReadConfig.readValue("buildFile");
		System.out.println(cmd);
		String project="";
		synchronized (lock) {
			ReadConfig.setValue("user", user);
			ReadConfig.setValue("zipFile", fileName);
			project=fileName.substring(0,fileName.indexOf("."));
			ReadConfig.setValue("project", project);
			proc = Runtime.getRuntime().exec(cmd);
		}
		
		InputStream sis = proc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new BufferedInputStream(sis)));
		
		while (true) {
			String value = br.readLine();
			System.out.println(value);
			if (null == value)
				break;
		}
		sis = proc.getErrorStream();
		br = new BufferedReader(new InputStreamReader(
				new BufferedInputStream(sis)));
		while (true) {
			String value = br.readLine();
			System.out.println(value);
			if (null == value)
				break;
		}
		return true;
	}
}
