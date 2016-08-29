package com.cims.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/accounts")
public class AccountsController {
    public static Logger log = Logger.getLogger(AccountsController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginView() {
        log.debug("request login view");
        return new ModelAndView("accounts/login");
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
