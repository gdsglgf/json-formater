package com.cims.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import com.cims.model.User;

/**
 * User Data Access Object.
 * 
 * @author Luo Guofu
 */
@CacheNamespace(implementation = org.mybatis.caches.ehcache.EhcacheCache.class)
public interface UserMapper {
    /**
     * 通过用户唯一标识符获取用户对象.
     * @param uid 用户唯一标识符
     * @return 预期的用户对象或空引用
     */
    public User getByUid(@Param("uid") long uid);
    
    /**
     * 通过用户名获取用户对象.
     * @param username 用户名
     * @return 预期的用户对象或空引用
     */
    public User getByUsername(@Param("username") String username);
    
    /**
     * 通过电子邮件地址获取用户对象.
     * @param email 邮箱地址
     * @return 预期的用户对象或空引用
     */
    public User getByEmail(@Param("email") String email);

    /**
     * 创建新用户对象.
     * @param user 待创建的用户对象
     */
    public void create(User user);
    
    /**
     * 更新用户对象.
     * @param user 待更新信息的用户对象
     */
    public void update(User user);
    
    /**
     * 删除用户对象.
     * @param uid 待删除用户的用户唯一标识符
     */
    public void delete(@Param("uid") long uid);
}
