package com.cims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cims.mapper.UserMapper;
import com.cims.model.User;

@Service
@Transactional
public class UserService {
    /**
     * 通过用户唯一标识符获取用户对象.
     * @param userId - 用户唯一标识符
     * @return 预期的用户对象或空引用
     */
    public User getByUid(long userId) {
        return userMapper.getByUid(userId);
    }

    /**
     * 自动注入的UserMapper对象.
     * 用于获取用户基本信息.
     */
    @Autowired
    private UserMapper userMapper;
}
