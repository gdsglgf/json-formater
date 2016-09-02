package com.cims.model;

import java.util.Date;

/**
 * 历史数据Model.
 * 对应数据库中的t_history数据表.
 * 
 * @author Luo Guofu
 */
public class JSONRecord {
	/**
	 * 历史数据的默认构造函数. 
	 */
	public JSONRecord() {}

	/**
	 * 历史数据的构造函数.
	 * @param uid 用户唯一标识符
	 * @param content 用户输入的json字符串
	 */
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

	/** 历史信息唯一标识符 */
	private long hid;
	
	/** 用户唯一标识符 */
	private long uid;
	
	/** 历史信息提交时间 */
	private Date submitTime;
	
	/** 历史数据(json字符串) */
	private String content;
}
