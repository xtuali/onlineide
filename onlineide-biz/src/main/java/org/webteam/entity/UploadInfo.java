package org.webteam.entity;

public class UploadInfo {
	private long totalSize;										//�ܴ�С
	private long startTime = System.currentTimeMillis();		//��ʼʱ��
	private long uploadSize;									//���ϴ��Ĵ�С
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
