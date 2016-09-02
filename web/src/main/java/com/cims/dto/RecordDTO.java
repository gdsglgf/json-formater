package com.cims.dto;

import com.cims.model.JSONRecord;
import com.cims.util.DateUtils;

/**
 * 历史数据DTO, 用于前端数据传输.
 * 
 * @author Luo Guofu
 */
public class RecordDTO {
	/**
	 * RecordDTO默认构造函数.
	 */
	public RecordDTO() {
	}

	/**
	 * RecordDTO构造函数.
	 * @param record 历史数据对象
	 */
	public RecordDTO(JSONRecord record) {
		this.hid = record.getHid();
		this.submitTime = DateUtils.format(record.getSubmitTime());
		this.content = record.getContent();
	}

	public long getHid() {
		return hid;
	}

	public void setHid(long hid) {
		this.hid = hid;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private long hid;
	private String submitTime;
	private String content;
}
