package org.webteam.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;


public class DefaultAction{
	private Logger defaultActionlogger = Logger.getLogger(DefaultAction.class);
	private static final long serialVersionUID = 1L;
	//
	public PrintWriter responseStream ;
	
	//是否详细信息指示
	protected String detailTag;
	//页码数
	protected int page;
	//每页记录数
	protected int pagesize;
	//总记录数
	protected Long totalCount;
	//用于查询和排序
	protected Map<String,String> clause;

	public Map<String, String> getClause() {
		return clause;
	}
	public void setClause(Map<String, String> clause) {
		this.clause = clause;
	}
	
	public void setDetailTag(String detailTag) {
		this.detailTag = detailTag;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	//每页条数
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	

	
	//包装成JSON对象
	public String parseJSON(Object obj){
		JSONObject jo = JSONObject.fromObject(obj);
		defaultActionlogger.debug(obj.getClass()+" parseResult:"+jo.toString());
		return jo.toString();
	}
	//将list包装成JSON数组
	public String parseJSONList(List list){
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			jsonArray.add(i,list.get(i));
		}
		return jsonArray.toString();
	}
	
	public String getDetailTag(){
		return detailTag;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}

}
