package com.free.love.hello.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.free.love.hello.manager.HelloManager;

public class HelloAction   implements Controller{
	
	public HelloManager entityManager;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("haha");
		return null;
	}

	public void setEntityManager(HelloManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
