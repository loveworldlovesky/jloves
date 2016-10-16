package com.free.love.core.utils;
 
 import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.free.love.core.utils.json.UtilJson;
 
 public class UtilResponse
 {
   public static final String TEXT_TYPE = "text/plain";
   public static final String JSON_TYPE = "application/json";
   public static final String XML_TYPE = "text/xml";
   public static final String HTML_TYPE = "text/html";
   public static final String JS_TYPE = "text/javascript";
 
   public static void render(HttpServletResponse response, String contentType, String content)
   {
     initResponseHeader(response, contentType);
     try {
       response.getWriter().write(content);
       response.getWriter().flush();
     }
     catch (IOException e) {
       throw new RuntimeException(e.getMessage(), e);
     }
   }
 
   public static void renderJson(HttpServletResponse response, Object object)
   {
     String json = UtilJson.toJson(object);
 
     render(response, "text/plain", json);
   }
 
   private static void initResponseHeader(HttpServletResponse response, String contentType)
   {
     String fullContentType = contentType + ";charset=utf-8";
     response.setContentType(fullContentType);
     setNoCacheHeader(response);
   }
 
   public static void setNoCacheHeader(HttpServletResponse response)
   {
     response.setDateHeader("Expires", 0L);
     response.addHeader("Pragma", "no-cache");
 
     response.setHeader("Cache-Control", "no-cache");
   }
 }
