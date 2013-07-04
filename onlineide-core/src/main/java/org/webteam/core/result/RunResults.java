package org.webteam.core.result;

import org.webteam.core.run.Run;

public class RunResults {

	private String results = "";

	private String username = "";

	private boolean needinput = false;

	private boolean runover = false;
	
	private Thread listener;
	
	private Thread threadOfRun;
	
	private String inputinfo = "";
	
	private Run exec;
	
	public Run getExec() {
		return exec;
	}

	public void setExec(Run exec) {
		this.exec = exec;
	}

	public boolean isNeedinput() {
		return needinput;
	}

	public void setNeedinput(boolean needinput) {
		this.needinput = needinput;
	}

	public boolean isRunover() {
		return runover;
	}

	public void setRunover(boolean runover) {
		this.runover = runover;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public RunResults(){}
	
	public RunResults(String username){
		needinput= false;
		results = "";
		runover = false;
		this.username = username;
	}

	public String getInputinfo() {
		return inputinfo;
	}

	public void setInputinfo(String inputinfo) {
		this.inputinfo = inputinfo;
	}

	public Thread getListener() {
		return listener;
	}

	public void setListener(Thread listener) {
		this.listener = listener;
	}

	public Thread getThreadOfRun() {
		return threadOfRun;
	}

	public void setThreadOfRun(Thread threadOfRun) {
		this.threadOfRun = threadOfRun;
	}
}
