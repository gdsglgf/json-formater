package com.cims.util;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cims.model.User;
import com.cims.service.UserService;

/**
 * HttpSession解析器.
 * @author Luo Guofu
 */
@Component
public class HttpSessionParser {
	/**
	 * HttpSessionParser的构造函数.
	 * @param userService 自动注入的UserService对象
	 */
	@Autowired
	private HttpSessionParser(UserService userService) {
		HttpSessionParser.userService = userService;
	}
	
	/**
	 * 获取Session中的用户对象.
	 * @param session HttpSession对象
	 * @return Session中的用户对象
	 */
	public static User getCurrentUser(HttpSession session) {
		Object isLoggedInAttribute = session.getAttribute("isLoggedIn");
		Object uidAttribute = session.getAttribute("uid");
		User user = null;
		
		if ( isLoggedInAttribute == null || uidAttribute == null ) {
			return null;
		}
		boolean isLoggedIn = (Boolean)isLoggedInAttribute;
		long uid = (Long)uidAttribute;
		
		if ( isLoggedIn ) {
			user = userService.getByUid(uid);
		}
		return user;
	}
	
	/**
	 * 检查用户是否已经登录.
	 * @param session HttpSession 对象
	 * @return 用户是否已经登录
	 */
	public static boolean isLoggedIn(HttpSession session) {
		Boolean isLoggedIn = (Boolean)session.getAttribute("isLoggedIn");
		if ( isLoggedIn == null || !isLoggedIn.booleanValue() ) {
			return false;
		}
		return true;
	}
	
	/**
	 * 返回登录用户的uid.
	 * @param session HttpSession 对象
	 * @return 登录用户的uid, 如果用户未登录, 返回null.
	 */
	public static Long getUid(HttpSession session) {
		Long uid = null;
		if (isLoggedIn(session)) {
			uid = (Long) session.getAttribute("uid");
		}
		return uid;
	}
	
	/**
	 * 自动注入的UserService对象.
	 */
	private static UserService userService;
}