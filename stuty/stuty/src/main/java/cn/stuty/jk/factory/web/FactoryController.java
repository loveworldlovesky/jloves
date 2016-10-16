package cn.stuty.jk.factory.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cn.stuty.jk.factory.entity.Factory;
import cn.stuty.jk.factory.manager.FactoryManager;

public class FactoryController implements Controller{
	
	FactoryManager entityManager;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("hahaha.........");
		Factory factory = entityManager.getById("1");
		System.out.println("name = "+factory.getFactoryName());
		
//		entityManager.add();
		return null;
	}


	public void setEntityManager(FactoryManager entityManager) {
		this.entityManager = entityManager;
	}
	
}
