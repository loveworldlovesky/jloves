package com.free.love.login.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.free.love.core.web.WebContextHolder;
import com.free.love.login.manager.LoginManager;

public class LoginAction  implements Controller{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	public LoginManager entityManager;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String loginid = request.getParameter("loginid");
		log.info("loginid........."+loginid);
		WebContextHolder.setLoginId("loginid",loginid);
		return null;
	}
	public void setEntityManager(LoginManager entityManager) {
		this.entityManager = entityManager;
	}
}
