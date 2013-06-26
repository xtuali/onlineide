package org.webteam.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.webteam.entity.RunResults;
import org.webteam.result.ResultControler;

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

		System.out.println("类名" + classname);
		//组装程序运行命令
		cmd = "java -classpath " + libdir + " -cp " + jardir + " "
				+ packagename + "." + classname;
		System.out.println("cmd:" + cmd);
		if (ResultControler.getControler().get("username") == null) {
			results = new RunResults(username);
			ResultControler.getControler().put(username, results);
		} else {
			results = ResultControler.getControler().get("username");
		}

		runprogram = new Thread(new Runnable() {
			public void run() {
				Runtime runtime = Runtime.getRuntime();
				try {
					proc = runtime.exec(cmd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("开始捕捉运行结果");
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
						setValue(value);	//将捕捉的结果保存，以便用户来获取
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
	 * @param data : 用户输入的数据
	 */
	public void sendInputInfo(String data) {
		try {
			os.write(data.getBytes());
			bw.newLine();
			System.out.println("发送了:"+data);
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
