package org.webteam.entity;

public class UploadInfo {
	private long totalSize;										//总大小
	private long startTime = System.currentTimeMillis();		//开始时间
	private long uploadSize;									//已上传的大小
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getUploadSize() {
		return uploadSize;
	}
	public void setUploadSize(long uploadSize) {
		this.uploadSize = uploadSize;
	}
}
