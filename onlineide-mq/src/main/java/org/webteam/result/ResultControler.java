package org.webteam.result;

import java.util.Hashtable;

import org.webteam.message.RunResults;

public final class ResultControler {
	private static Hashtable<String, RunResults> controler = new Hashtable<String, RunResults>();

	private ResultControler() {

	}

	public static Hashtable<String, RunResults> getControler() {
		if (controler == null)
			controler = new Hashtable<String, RunResults>();
		return controler;
	}

	public static StringBuffer toJSON(RunResults rrs) {
		StringBuffer json = new StringBuffer();
		if (rrs != null) {

			synchronized (rrs) {
				json.append("{").append("Result:").append(
						"'" + rrs.getResults() + "'").append(",").append(
						"IsRunOver:").append(
						"'" + String.valueOf(rrs.isRunover() + "'"))
						.append("}");
			}
			rrs.setResults("");
		}
		return json;
	}

	public static StringBuffer toAJAX(RunResults rrs) {
		StringBuffer ajax = new StringBuffer();
		if (rrs != null) {
			synchronized (rrs) {
				ajax.append(rrs.getResults()).append(",").append(
						String.valueOf(rrs.isRunover()));
			}
			rrs.setResults("");
		}
		return ajax;
	}
}
