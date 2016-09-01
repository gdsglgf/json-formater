package com.cims.dto;

import com.cims.model.JSONRecord;
import com.cims.util.DateUtils;

public class RecordDTO {
	public RecordDTO() {
	}

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
