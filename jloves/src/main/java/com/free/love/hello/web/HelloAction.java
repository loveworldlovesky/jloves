package com.free.love.hello.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.free.love.hello.manager.HelloManager;

public class HelloAction   implements Controller{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	public HelloManager entityManager;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("haha");
		log.info("info:"+"HelloAction.handleRequest");
		log.debug("info:"+"HelloAction.handleRequest");
		log.error("info:"+"HelloAction.handleRequest");
		ModelAndView model = new ModelAndView();
		model.setViewName("/love/module/hello/key2.html");
		return model;
	}

	public void setEntityManager(HelloManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
