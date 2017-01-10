package com.cims.util;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class LoginFilter implements Filter {
	private static Logger log = Logger.getLogger(LoginFilter.class);
	// 过滤登录页面, css, js, image, 静态页面等资源资源文件
	private static String[] ignoreURI = new String[]{};
	
	private boolean isIgnoreURI(String uri) {
		for (String s : ignoreURI) {
			if (uri.contains(s)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
			throw new ServletException("OncePerRequestFilter just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
		HttpSession session = httpRequest.getSession(true);

		String uri = httpRequest.getRequestURI();
		
		if (HttpSessionParser.isLoggedIn(session) || isIgnoreURI(uri)) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			log.debug(String.format("filer uri:%s", uri));
			// 未登录拦截
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/accounts/login");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String ignoreURIParam = filterConfig.getInitParameter("ignoreURI");

		if (ignoreURI != null) {
			ignoreURI = ignoreURIParam.split("\\s*,\\s*");
			log.debug(String.format("ignoreURIParam:[%s], ignoreURI:%s, length:%d", 
					ignoreURIParam, Arrays.toString(ignoreURI), ignoreURI.length));
		}
	}

	@Override
	public void destroy() {
		
	}
}