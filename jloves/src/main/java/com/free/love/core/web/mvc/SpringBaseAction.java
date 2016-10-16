package com.free.love.core.web.mvc;

 import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.free.love.core.utils.UtilRequest;
import com.free.love.core.utils.UtilResponse;
import com.free.love.core.utils.UtilString;
 
 public abstract class SpringBaseAction
   implements Controller
 {
   protected final Logger logger = LoggerFactory.getLogger(getClass());
   protected HttpServletRequest request;
   protected HttpServletResponse response;
   private Map<String, Object> mapping;
   private String methodVarName = "method";
 
   public final ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
     throws Exception
   {
     this.request = request;
     this.response = response;
 
     Object result = execute();
     if (result == null) return null;
 
     if ((result instanceof String))
     {
       String path = getViewPath((String)result);
       result = new ModelAndView(path);
     }
 
     if ((result instanceof ModelAndView)) return (ModelAndView)result;
 
     UtilResponse.renderJson(response, result);
     return null;
   }
 
   protected String getViewPath(String name)
   {
     if ((name.startsWith("redirect:")) || (name.startsWith("forward:"))) return name;
 
     String path = (String)this.mapping.get(name);
     if (path != null) return path;
 
     path = resolveCommonPath(name);
     if (path == null) {
       this.logger.warn("未找到{}对应的view路径", name);
       path = name;
     }
 
     return path;
   }
 
   private String resolveCommonPath(String name)
   {
     String path = this.request.getServletPath();
     path = path.substring(1, path.indexOf("."));
 
     Map map = null;
     String[] paths = path.split("/");
 
     if (paths.length >= 2) {
       String pattern = UtilString.repeat("/*", paths.length);
       map = (Map)this.mapping.get(pattern);
     }
 
     if (map == null) map = (Map)this.mapping.get("*");
 
     path = (String)map.get(name);
     if (path == null) return null;
 
     for (int i = 0; i < paths.length; i++) {
       path = UtilString.replaceAll(path, "{" + (i + 1) + "}", paths[i]);
     }
 
     return path;
   }
 
   protected Object execute()
     throws Exception
   {
     Object result = null;
     try {
       Method method = getHandlerMethod();
       result = method.invoke(this, new Object[0]);
     }
     catch (InvocationTargetException e) {
       Throwable target = e.getTargetException();
       throw new RuntimeException(target);
     }
     return result;
   }
 
   private Method getHandlerMethod() throws Exception
   {
     String methodName = getMethodName();
     Class clazz = getClass();
     Method method = clazz.getMethod(methodName, (Class[])null);
     return method;
   }
 
   private String getMethodName()
   {
     String methodName = this.request.getParameter(this.methodVarName);
     if (methodName == null) methodName = "unspecified";
     return methodName;
   }
 
   public void setMethodVarName(String methodVarName) {
     this.methodVarName = methodVarName;
   }
 
   public Object unspecified()
     throws Exception
   {
     return null;
   }
 
   protected Map<String, String> getParameterMap()
   {
     Map map = UtilRequest.getParameterMap(this.request);
     return map;
   }
 
   public void setMapping(Map<String, Object> mapping) {
     this.mapping = mapping;
   }
 
   protected Map<String, List<MultipartFile>> getUploadFiles()
     throws Exception
   {
     Map map = null;
     map = new HashMap();
 
     if ((this.request instanceof MultipartHttpServletRequest)) {
       MultipartHttpServletRequest mRequest = null;
       mRequest = (MultipartHttpServletRequest)this.request;
 
       Iterator it = mRequest.getFileNames();
       while (it.hasNext()) {
         String name = (String)it.next();
         map.put(name, mRequest.getFiles(name));
       }
     }
 
     return map;
   }
 
   protected MultipartFile getUploadFile()
     throws Exception
   {
     if ((this.request instanceof MultipartHttpServletRequest)) {
       MultipartHttpServletRequest mRequest = null;
       mRequest = (MultipartHttpServletRequest)this.request;
 
       Iterator it = mRequest.getFileNames();
       while (it.hasNext()) {
         String name = (String)it.next();
         List files = mRequest.getFiles(name);
         if (files.size() > 0) return (MultipartFile)files.get(0);
       }
     }
 
     return null;
   }
 }

