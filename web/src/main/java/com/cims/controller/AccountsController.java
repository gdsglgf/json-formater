package com.cims.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cims.model.User;
import com.cims.service.UserService;

@Controller
@RequestMapping(value = "/accounts")
public class AccountsController {
    public static Logger log = Logger.getLogger(AccountsController.class);
    
    @Autowired
	private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView() {
        log.debug("request login view");
        return new ModelAndView("accounts/login");
    }
    
    /**
	 * 处理用户的登录请求.
	 * @param username - 用户名
	 * @param password - 密码(已使用MD5加密)
	 * @param request - HttpServletRequest对象
	 * @return 一个包含登录验证结果的Map<String, Boolean>对象
	 */
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> loginAction(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request) {
		String ipAddress = request.getRemoteAddr();
		Map<String, Boolean> result = userService.isAllowedToLogin(username, password);
		log.info(String.format("User: [Username=%s] tried to log in at %s", new Object[] {username, ipAddress}));
		if ( result.get("isSuccessful") ) {
			User user = userService.getByUsername(username);
			getSession(request, user);
		}
		return result;
	}
	
	/**
	 * 为登录的用户创建Session.
	 * @param request - HttpServletRequest对象
	 * @param user - 一个User对象, 包含用户的基本信息
	 * @param isAutoLoginAllowed - 是否保存登录状态
	 */
	private void getSession(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		session.setAttribute("isLoggedIn", true);
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		
		String ipAddress = request.getRemoteAddr();
		log.info(String.format("%s logged in at %s", new Object[] {user, ipAddress}));
	}
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerView() {
        log.debug("request register view");
        return new ModelAndView("accounts/register");
    }
    
    @RequestMapping(value = "{uid}", method = RequestMethod.GET)
    public @ResponseBody String getUserAction(
            @PathVariable("uid") int uid,
            HttpServletRequest request, HttpServletResponse response) {
        return "user-" + uid;
    }
    
    @RequestMapping(value = "/test-{uid}", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> test(
            @PathVariable("uid") int uid,
            HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("uid", uid);
        result.put("username", "user-" + uid);
        return result;
    }
}
