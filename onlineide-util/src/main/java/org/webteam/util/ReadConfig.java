package org.webteam.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {
	/*
	 * 根据参数读取配置文件中某一项的值
	 */
	public static String readValue(String key) {
		Properties properties = new Properties();
		try {
			properties.load(ReadConfig.class.getClassLoader()
					.getResourceAsStream("projectconfig.properties"));
			String value = properties.getProperty(key);
			return value;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void setValue(String key, String value) {
		Properties properties = new Properties();
		try {
			File file = new File(ReadConfig.readValue("commonFile"));
			InputStream is = new FileInputStream(file);
			properties.load(is);
			
			properties.setProperty(key, value);
			FileOutputStream fos = new FileOutputStream(file);
			properties.store(fos, "xtuali");
			fos.flush();
			fos.close();
			System.out.println(readValue("zipFile"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		setValue("zipFile", "test2.zip");
	}
}
