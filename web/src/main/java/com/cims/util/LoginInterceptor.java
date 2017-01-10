package com.cims.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = Logger.getLogger(LoginInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		log.debug(String.format("intercept uri:%s", uri));
		boolean isLoggedIn = HttpSessionParser.isLoggedIn(request.getSession());
		System.out.println(">>>isLoggedIn:" + isLoggedIn);
		if (!isLoggedIn) {
			response.sendRedirect(request.getContextPath() + "/accounts/login");
		}
		return isLoggedIn;
	}
}