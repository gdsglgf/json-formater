package com.cims.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cims.model.User;
import com.cims.service.UserService;
import com.cims.util.DateUtils;
import com.cims.util.HttpRequestParser;
import com.cims.util.HttpSessionParser;

/**
 * 处理用户的登录/注册请求.
 * 
 * @author Luo Guofu
 */
@Controller
@RequestMapping(value = "/accounts")
public class AccountsController {
    public static Logger log = Logger.getLogger(AccountsController.class);
    
    @Autowired
	private UserService userService;

    /**
	 * 显示用户的登录页面.
	 * @param isLogout - 是否处于登出状态
	 * @param forwardUrl - 登录后跳转的地址(相对路径)
	 * @param request - HttpServletRequest对象
	 * @param response - HttpResponse对象
	 * @return 包含登录页面信息的ModelAndView对象
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginView(
			@RequestParam(value = "logout", required = false, defaultValue = "false") boolean isLogout,
			@RequestParam(value = "forward", required = false, defaultValue = "") String forwardUrl,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if ( isLogout ) {
			destroySession(request, session);
		}
		
		ModelAndView view = null;
		if ( HttpSessionParser.isLoggedIn(session) ) {
			view = new ModelAndView("redirect:/");
		} else {
			view = new ModelAndView("accounts/login");
			view.addObject("isLogout", isLogout);
			view.addObject("forwardUrl", forwardUrl);
		}
		return view;
	}
	
	/**
	 * 为注销的用户销毁Session.
	 * @param request - HttpServletRequest对象
	 * @param session - HttpSession 对象
	 */
	private void destroySession(HttpServletRequest request, HttpSession session) {
		User currentUser = HttpSessionParser.getCurrentUser(request.getSession());
		String ipAddress = HttpRequestParser.getRemoteAddr(request);
		log.info(String.format("%s logged out at %s", new Object[] {currentUser, ipAddress}));
		
		session.setAttribute("isLoggedIn", false);
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
		String ipAddress = HttpRequestParser.getRemoteAddr(request);
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
		
		String ipAddress = HttpRequestParser.getRemoteAddr(request);
		log.info(String.format("%s logged in at %s", new Object[] {user, ipAddress}));
	}
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerView() {
        log.debug("request register view");
        return new ModelAndView("accounts/register");
    }
    
    /**
	 * 处理用户注册的请求.
	 * @param username - 用户名
	 * @param password - 密码
	 * @param email - 电子邮件地址
	 * @param request - HttpServletRequest对象
	 * @return 一个包含账户创建结果的Map<String, Boolean>对象
	 */
	@RequestMapping(value = "/register.action", method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> registerAction(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "email", required = true) String email,
			HttpServletRequest request) {
		Map<String, Boolean> result = userService.create(username, password, email);

		if ( result.get("isSuccessful") ) {
			User user = userService.getByUsername(username);
			getSession(request, user);

			log.info(String.format("Create %s at %S", user, DateUtils.format(user.getRegisterTime())));
		}
		return result;
	}
	
	/**
	 * 显示用户信息页面
	 * @param request - HttpServletRequest对象
	 * @param response - HttpResponse对象
	 * @return 包含用户信息页面的ModelAndView对象
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profileView(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		HttpSession session = request.getSession();
		
		if (HttpSessionParser.isLoggedIn(session)) {
			view = new ModelAndView("accounts/profile");
			long uid = (long) session.getAttribute("uid");
			User user = userService.getByUid(uid);
			view.addObject("username", user.getUsername());
			view.addObject("email", user.getEmail());
			view.addObject("registerTime", DateUtils.format(user.getRegisterTime()));
		} else {
			view = new ModelAndView("accounts/login");
			view.addObject("isLogout", false);
		}
		
		return view;
	}
	
	/**
	 * 显示设置密码页面.
	 * @param request - HttpServletRequest对象
	 * @param response - HttpResponse对象
	 * @return 包含设置密码页面的ModelAndView对象
	 */
	@RequestMapping(value = "/reset-password", method = RequestMethod.GET)
	public ModelAndView resetPasswordView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		HttpSession session = request.getSession();
		
		if (HttpSessionParser.isLoggedIn(session)) {
			view = new ModelAndView("accounts/reset-password");
		} else {
			view = new ModelAndView("accounts/login");
		}
		
		return view;
	}
	
	/**
	 * 设置新密码
	 * @param oldPassword - 原始密码(已使用MD5加密)
	 * @param password    - 未加密的新密码
	 * @param request     - HttpServletRequest对象
	 * @return  包含设置密码信息的Map<String, Boolean>对象
	 */
	@RequestMapping(value = "/reset-password.action", method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> resetPasswordAction(
			@RequestParam(value = "oldPassword", required = true) String oldPassword,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request) {
		User currentUser = HttpSessionParser.getCurrentUser(request.getSession());
		Map<String, Boolean> result = userService.resetPassword(currentUser, oldPassword, password);
		log.info(String.format("%s reset password %s", currentUser, result.get("isSuccessful")));
		return result;
	}
}
