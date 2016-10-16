package com.free.love.hello.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.free.love.core.web.WebContextHolder;
import com.free.love.hello.manager.HelloManager;

public class HelloAction   implements Controller{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	public HelloManager entityManager;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		
		String loginId = WebContextHolder.getLoginId("loginid");
		System.out.println("haha:"+loginId);
		log.info("info:"+"HelloAction.handleRequest");
		log.debug("debug:"+"HelloAction.handleRequest");
		log.error("error:"+"HelloAction.handleRequest");
		ModelAndView model = new ModelAndView();
		model.addObject("message", "Hello World!"); 
		model.setViewName("hello/key1.jsp");
		return model;
	}

	public void setEntityManager(HelloManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
