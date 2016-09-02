package com.cims.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;

import com.cims.model.JSONRecord;

/**
 * JSONRecord Data Access Object.
 * 
 * @author Luo Guofu
 */
@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface RecordMapper {
	/**
	 * 保存历史信息.
	 * @param record 历史信息对象
	 */
	public void create(JSONRecord record);
	
	/**
	 * 查询用户的所有历史数据.
	 * @param uid 用户唯一标识符
	 * @return 用户历史数据列表
	 */
	public List<JSONRecord> searchAll(long uid);
}
