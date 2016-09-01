package com.cims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cims.mapper.RecordMapper;
import com.cims.model.JSONRecord;

@Service
@Transactional
public class RecordService {
	public void saveRecord(JSONRecord record) {
		recordMapper.create(record);
	}
	
	public List<JSONRecord> searchAll(long uid) {
		return recordMapper.searchAll(uid);
	}

	@Autowired
	private RecordMapper recordMapper;
}
