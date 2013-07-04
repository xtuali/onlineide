package org.webteam.core.run;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.webteam.core.result.RunResults;
import org.webteam.util.Package2URL;
import org.webteam.util.ReadConfig;

public class Run {

	private RunResults results;;

	private Thread runprogram;

	private String cmd;

	private BufferedReader br;

	private InputStream sis;

	private Process proc;

	private OutputStream os;

	private BufferedWriter bw;

	private static final String BASE_DIR = ReadConfig.readValue("BaseDir");

	private static final String BIN_DIR = ReadConfig.readValue("bin");

	public Object[] exec(String username, String projectname,
			String packagename, String classname) {
		String path = BASE_DIR + username + "/" + projectname + "/" + BIN_DIR
				+ Package2URL.package2url(packagename) + "/" + classname;
		System.out.println(path);
		String jardir = BASE_DIR + username + "/" + projectname + "/bin";
		String libdir = BASE_DIR + username + "/" + projectname + "/lib";

		int lastindex = classname.lastIndexOf(".");
		classname = classname.substring(0, lastindex);

		System.out.println("����" + classname);
		//��װ������������
		cmd = "java -classpath " + libdir + " -cp " + jardir + " "
				+ packagename + "." + classname;
		System.out.println("cmd:" + cmd);
		
		//推送消息
/*		if (ResultControler.getControler().get("username") == null) {
			results = new RunResults(username);
			ResultControler.getControler().put(username, results);
		} else {
			results = ResultControler.getControler().get("username");
		}*/

		runprogram = new Thread(new Runnable() {
			public void run() {
				Runtime runtime = Runtime.getRuntime();
				try {
					proc = runtime.exec(cmd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("��ʼ��׽���н��");
				sis = proc.getInputStream();
				System.out.println(sis);
				br = new BufferedReader(new InputStreamReader(
						new BufferedInputStream(sis)));
				os = proc.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(
						new BufferedOutputStream(os)));
				try {
					while (true) {
						String value = br.readLine();
						System.out.println("value:"+value);
						if (null == value)
							break;
						setValue(value);	//����׽�Ľ��棬�Ա��û�����ȡ
						sleepawhile(200);
					}
					
					results.setRunover(true);
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					closeConnection(os, bw,sis,br);
				}
			}
		});
		runprogram.start();
		results.setExec(this);
		return new Object[] { results.getResults() };
	}

	private void setValue(String result) {
		if (results.getResults().equals(""))
			results.setResults(result + "\n");
		else
			results.setResults(results.getResults() + result + "\n");
	}
	/*
	 * @param data : �û���������
	 */
	public void sendInputInfo(String data) {
		try {
			os.write(data.getBytes());
			bw.newLine();
			System.out.println("������:"+data);
			flushConnection(os, bw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sleepawhile(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void flushConnection(OutputStream os,BufferedWriter bw){
		try {
			os.flush();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void closeConnection(OutputStream os, BufferedWriter bw,InputStream sis,BufferedReader br) {
		try {
			System.out.println("os:" + os);
			os.flush();
			bw.flush();
			os.close();
			bw.close();
			sis.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
