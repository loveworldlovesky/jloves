package com.free.love.core.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.free.love.utils.DateUtils;
/**
 * 过滤器
 * 过滤是否登录
 * 过滤是否有权限
 * ...
 * @author zwc
 *
 */
public class WebContextHolderFilter extends OncePerRequestFilter {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		WebContextHolder.setRequest(request);
		WebContextHolder.setResponse(response);
		
		long creationTime = WebContextHolder.getSession().getCreationTime();
		long lastAccessedTime = WebContextHolder.getSession().getLastAccessedTime();
		
		String d1 = DateUtils.getDateByMillis(creationTime);
		String d2 = DateUtils.getDateByMillis(lastAccessedTime);
		
		log.info("session创建时间="+d1+"session上次访问时间="+d2);
		
		String requestURI = request.getRequestURI();
		String requestURL = request.getRequestURL().toString();
		log.info("requestURI="+requestURI);
		log.info("requestURL="+requestURL);
		
		String loginId = WebContextHolder.getLoginId();
		//不拦截登录页面
		if(requestURI.endsWith("logon.do") || requestURI.contains("login.do")){
			
		}else if(loginId == null){//如果没有登录,转向登录页面
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.do");
//			requestDispatcher.forward(request, response);
			response.sendRedirect("logon.do");
		}else{
			
		}
		String contextPath = request.getContextPath();
		log.info("contextPath............." + contextPath);
		request.setAttribute("ctx", request.getContextPath());
		
		try {
			filterChain.doFilter(request, response);
		} finally {
			// WebContextHolder.remove();
		}
	}

	public void destroy() {
		// WebContextHolder.setServletContext(null);
		// WebContextHolder.remove();

		super.destroy();
	}
}