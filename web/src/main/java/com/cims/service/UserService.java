package com.cims.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cims.mapper.UserMapper;
import com.cims.model.User;
import com.cims.util.DigestUtils;

/**
 * 用户类(User)的业务逻辑层.
 * @author Luo Guofu
 */
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
	 * @param username - 用户名
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
			if ( user != null && user.getPassword().equals(password) ) {
				result.put("isAccountValid", true);
				result.put("isAllowedToAccess", true);
				result.put("isSuccessful", true);
			}
		}
		return result;
	}
    
	/**
	 * 通过用户名获取用户对象.
	 * @param username - 用户名
	 * @return 预期的用户对象或空引用
	 */
    public User getByUsername(String username) {
		return userMapper.getByUsername(username);
	}
    
    /**
     * 保存用户信息.
     * @param username - 用户名
     * @param password - 未加密密码
     * @param email    - 邮箱
     * @return 一个包含保存用户结果的Map<String, Boolean>对象
     */
    public Map<String, Boolean> create(String username, String password,
			String email) {
    	User user = new User(username, DigestUtils.md5Hex(password), email);
    	Map<String, Boolean> result = new HashMap<String, Boolean>();
    	result.put("isUsernameExists", isUsernameExists(user.getUsername()));
    	log.debug(user.getUsername() + "---" + result.get("isUsernameExists"));
    	result.put("isEmailExists", isEmailExists(user.getEmail()));
    	log.debug(user.getEmail() + "---" + result.get("isEmailExists"));
    	boolean isExists = result.get("isUsernameExists") || result.get("isEmailExists");
    	result.put("isSuccessful", !isExists);
    	
    	if ( result.get("isSuccessful") ) {
			userMapper.create(user);
		}
		return result;
	}
    
    /**
	 * 检查用户名是否存在.
	 * @param username - 用户名
	 * @return 用户名是否存在
	 */
	private boolean isUsernameExists(String username) {
		User user = userMapper.getByUsername(username);
		return user != null;
	}
	
	/**
	 * 检查电子邮件地址是否存在.
	 * 说明: 仅用于用户创建新账户
	 * @param email - 电子邮件地址
	 * @return 电子邮件地址是否存在
	 */
	private boolean isEmailExists(String email) {
		User user = userMapper.getByEmail(email);
		return user != null;
	}
	
	/**
	 * 设置用户密码
	 * @param user         - 用户对象
	 * @param oldPassword  - MD5加密的原始密码
	 * @param password     - 未加密新密码
	 * @return  包含设置用户密码结果的Map<String, Boolean>对象
	 */
	public Map<String, Boolean> resetPassword(User user, String oldPassword, String password) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		result.put("isPasswordEmpty", password.isEmpty());
		if (user == null || !user.getPassword().equals(oldPassword) || password.isEmpty()) {
			result.put("isSuccessful", false);
		} else {
			result.put("isSuccessful", true);
			user.setPassword(DigestUtils.md5Hex(password));
			userMapper.update(user);
		}
		return result;
	}
	
	public static Logger log = Logger.getLogger(UserService.class);

    /**
     * 自动注入的UserMapper对象.
     * 用于获取用户基本信息.
     */
    @Autowired
    private UserMapper userMapper;
}
