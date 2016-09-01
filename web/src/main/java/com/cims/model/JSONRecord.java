package com.cims.model;

import java.util.Date;

public class JSONRecord {
	public JSONRecord() {}

	public JSONRecord(long uid, String content) {
		this.uid = uid;
		this.content = content;
	}

	public long getHid() {
		return hid;
	}

	public void setHid(long hid) {
		this.hid = hid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private long hid;
	private long uid;
	private Date submitTime;
	private String content;
}
