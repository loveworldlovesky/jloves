package com.free.love.core.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebContextHolder {
	private static ThreadLocal<Map<String, Object>> contextHolder = new ThreadLocal<Map<String, Object>>();
	private static ServletContext servletContext;

	public static void remove() {
		contextHolder.remove();
	}

	private static void setContextObject(String varName, Object obj) {
		Map<String, Object> map = (Map<String, Object>) contextHolder.get();
		if (map == null) {
			map = new HashMap<String, Object>();
			contextHolder.set(map);
		}

		map.put(varName, obj);
	}

	private static <E> Object getContextObject(String varName) {
		Object obj = null;

		Map<String, Object> map = (Map<String, Object>) contextHolder.get();
		if (map != null)
			obj = map.get(varName);

		return obj;
	}

	public static void setRequest(HttpServletRequest request) {
		setContextObject("request", request);
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) getContextObject("request");
		return request;
	}

	public static void setResponse(HttpServletResponse response) {
		setContextObject("response", response);
	}

	public static HttpServletResponse getResponse() {
		HttpServletResponse response = (HttpServletResponse) getContextObject("response");
		return response;
	}

	public static HttpSession getSession() {
		HttpSession session = null;
		HttpServletRequest request = getRequest();
		if (request != null){
			try {
				session = request.getSession();
			} catch (IllegalStateException ignored) {
				ignored.printStackTrace();
			}
		}
		return session;
	}

	public static void setServletContext(ServletContext sc) {
		servletContext = sc;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static String getWebAppPath() {
		String path = getServletContext().getRealPath("/");
		int pos = path.indexOf("WEB-INF/classes/");
		if (pos > 0)
			path = path.substring(0, pos);

		pos = path.indexOf("file:");
		if (pos > -1)
			path = path.substring(pos + 5);

//		path = UtilFile.convertDOSPath(path);
//		path = UtilURL.encode(path, "utf-8");

		return path;
	}

	public static void setRequestAttribute(String attributeName, Object value) {
		HttpServletRequest request = getRequest();
		if (request != null)
			if (value != null)
				request.setAttribute(attributeName, value);
			else
				request.removeAttribute(attributeName);
	}

	public static <E> Object getRequestAttribute(String attributeName) {
		Object value = null;
		HttpServletRequest request = getRequest();
		if (request != null)
			value = request.getAttribute(attributeName);

		return value;
	}

	public static String getRequestParameter(String name) {
		String value = null;
		HttpServletRequest request = getRequest();
		if (request != null)
			value = request.getParameter(name);

		return value;
	}

	public static void setSessionAttribute(String attributeName, Object value) {
		HttpSession session = getSession();
		if (session != null){
			if (value != null){
				session.setAttribute(attributeName, value);
			}else{
				session.removeAttribute(attributeName);
			}
		}else{
			throw new RuntimeException("session is null !!");
		}
	}

	public static <E> Object getSessionAttribute(String attributeName) {
		Object value = null;
		HttpSession session = getSession();
		if (session != null){
			value = session.getAttribute(attributeName);
			long lastAccessedTime = session.getLastAccessedTime();
			long creationTime = session.getCreationTime();
		}

		return value;
	}


	public static String getLoginId(String key) {
		String loginId = (String) getSessionAttribute(key);
		return loginId;
	}
	public static String getLoginId() {
		String loginId = (String) getSessionAttribute("mfLoginId");
		return loginId;
	}

	public static void setLoginId(String key,String id) {
		if(key == null){
			key = "mfLoginId";
		}
		setSessionAttribute(key, id);
	}
	public static void setLoginId(String id) {
		setSessionAttribute("mfLoginId", id);
	}
}