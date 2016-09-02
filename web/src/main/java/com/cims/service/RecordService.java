package com.cims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cims.mapper.RecordMapper;
import com.cims.model.JSONRecord;

/**
 * 历史信息类的业务逻辑层.
 * @author Luo Guofu
 */
@Service
@Transactional
public class RecordService {
	/**
	 * 保存历史信息.
	 * @param record 历史信息对象
	 */
	public void saveRecord(JSONRecord record) {
		recordMapper.create(record);
	}
	
	/**
	 * 查询用户的所有历史数据.
	 * @param uid 用户唯一标识符
	 * @return 用户历史数据列表
	 */
	public List<JSONRecord> searchAll(long uid) {
		return recordMapper.searchAll(uid);
	}

	@Autowired
	private RecordMapper recordMapper;
}
