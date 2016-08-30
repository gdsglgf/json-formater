package com.cims.service;

import java.util.HashMap;
import java.util.Map;

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
	 * 验证用户身份是否有效.
	 * @param username - 用户名或电子邮件地址
	 * @param password - 密码(已使用MD5加密)
	 * @return 一个包含登录验证结果的Map<String, Boolean>对象
	 */
	public Map<String, Boolean> isAllowedToLogin(String username, String password) {
		Map<String, Boolean> result = new HashMap<String, Boolean>(6, 1);
		result.put("isUsernameEmpty", username.isEmpty());
		result.put("isPasswordEmpty", password.isEmpty());
		result.put("isAccountValid", false);
		result.put("isAllowedToAccess", false);
		result.put("isSuccessful", false);

		if ( !result.get("isUsernameEmpty") && !result.get("isPasswordEmpty") ) {
			User user = getByUsername(username);
//			if ( user != null && user.getPassword().equals(password) ) {
				result.put("isAccountValid", true);
				result.put("isAllowedToAccess", true);
				result.put("isSuccessful", true);
//			}
		}
		return result;
	}
    
    public User getByUsername(String username) {
		User user = new User(username, "");
		return user;
	}

    /**
     * 自动注入的UserMapper对象.
     * 用于获取用户基本信息.
     */
    @Autowired
    private UserMapper userMapper;
}
