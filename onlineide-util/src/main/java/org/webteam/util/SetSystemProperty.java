package org.webteam.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author
 * @version 2008.11.14
 */
public class SetSystemProperty {
	// �����ļ���·��
	static String profilepath = "common.properties";
	static String profile = SetSystemProperty.class.getClassLoader().getResource("common.properties").getFile();
	/**
	 * ���þ�̬����
	 */
	private static Properties props = new Properties();
	static {
		System.out.println(profile);
		try {
			props.load(SetSystemProperty.class.getClassLoader()
					.getResourceAsStream(profilepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.exit(-1);
		}
	}

	/**
	 * ��ȡ�����ļ�����Ӧ����ֵ
	 * 
	 * @param key
	 *            ����
	 * @return String
	 */
	public static String getKeyValue(String key) {
		return props.getProperty(key);
	}

	/**
	 * ��������key��ȡ������ֵvalue
	 * 
	 * @param filePath
	 *            �����ļ�·��
	 * @param key
	 *            ����
	 */
	public static String readValue(String filePath, String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					SetSystemProperty.class.getClassLoader()
							.getResource(filePath).getFile()));
			props.load(in);
			String value = props.getProperty(key);
			System.out.println(key + "����ֵ�ǣ�" + value);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ���£�����룩һ��properties��Ϣ(���������ֵ) ����������Ѿ����ڣ����¸�������ֵ�� ��������������ڣ�����һ�Լ�ֵ��
	 * 
	 * @param keyname
	 *            ����
	 * @param keyvalue
	 *            ��ֵ
	 */
	public static void writeProperties(String keyname, String keyvalue) {
		try {
			// ���� Hashtable �ķ��� put��ʹ�� getProperty �����ṩ�����ԡ�
			// ǿ��Ҫ��Ϊ���Եļ���ֵʹ���ַ���������ֵ�� Hashtable ���� put �Ľ����
			OutputStream fos = new FileOutputStream(profile);
			props.setProperty(keyname, keyvalue);
			// ���ʺ�ʹ�� load �������ص� Properties ���еĸ�ʽ��
			// ���� Properties ���е������б�����Ԫ�ضԣ�д�������
			props.store(fos, "Update '" + keyname + "' value");
		} catch (IOException e) {
			System.err.println("�����ļ����´���");
		}
	}

	/**
	 * ����properties�ļ��ļ�ֵ�� ����������Ѿ����ڣ����¸�������ֵ�� ��������������ڣ�����һ�Լ�ֵ��
	 * 
	 * @param keyname
	 *            ����
	 * @param keyvalue
	 *            ��ֵ
	 */
	public void updateProperties(String keyname, String keyvalue) {
		try {
			props.load(new FileInputStream(profile));
			// ���� Hashtable �ķ��� put��ʹ�� getProperty �����ṩ�����ԡ�
			// ǿ��Ҫ��Ϊ���Եļ���ֵʹ���ַ���������ֵ�� Hashtable ���� put �Ľ����
			OutputStream fos = new FileOutputStream(profile);
			props.setProperty(keyname, keyvalue);
			// ���ʺ�ʹ�� load �������ص� Properties ���еĸ�ʽ��
			// ���� Properties ���е������б�����Ԫ�ضԣ�д�������
			props.store(fos, "Update '" + keyname + "' value");
		} catch (IOException e) {
			System.err.println("�����ļ����´���");
		}
	}

	// ���Դ���
	public static void main(String[] args) {
	//	readValue("common.properties", "zipFile");
		writeProperties("zipFile", "test1.zip");
	//	SetSystemProperty s = new SetSystemProperty();
	//	s.updateProperties("zip", "testhaha.zip");
		System.out.println("�������");
	}
}
