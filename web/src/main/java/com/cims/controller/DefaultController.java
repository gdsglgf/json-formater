package com.cims.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class DefaultController {
	/**
	 * 显示应用程序的首页.
	 * @param request - HttpRequest对象
	 * @param response - HttpResponse对象
	 * @return 一个包含首页内容的ModelAndView对象
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView indexView(
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		HttpSession session = request.getSession();
		Boolean isLoggedIn = (Boolean)session.getAttribute("isLoggedIn");
		if ( isLoggedIn == null || !isLoggedIn.booleanValue() ) {
			view = new ModelAndView("accounts/login");
			view.addObject("isLogout", false);
		} else {
			view = new ModelAndView("index");
		}
		
		return view;
	}
}
