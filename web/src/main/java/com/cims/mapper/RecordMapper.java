package com.cims.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;

import com.cims.model.JSONRecord;

@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface RecordMapper {

	public void create(JSONRecord record);
	
	public List<JSONRecord> searchAll(long uid);
}
