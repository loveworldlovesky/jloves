package com.free.love.core.utils;

 
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Map;
 import java.util.Set;
 import javax.servlet.http.HttpServletRequest;
 
 public class UtilRequest
 {
   public static Map<String, String> getParameterMap(HttpServletRequest request)
   {
     Map map = new HashMap();
 
     Map param = request.getParameterMap();
     Set<String> keySet = param.keySet();
     for (String key : keySet) {
       String value = getParameterValues(request, key);
       map.put(key, value);
     }
 
     return map;
   }
 
   public static String getParameterValues(HttpServletRequest request, String key)
   {
     String[] values = request.getParameterValues(key);
     if (values == null) return null;
 
     Set set = new HashSet();
 
     StringBuilder sb = new StringBuilder();
     for (int i = 0; i < values.length; i++) {
       String val = values[i];
       if (i > 0) {
         if (!set.contains(val))
           sb.append(",");
       } else {
         set.add(val);
         sb.append(val);
       }
     }
     String value = sb.toString();
     return value;
   }
 }

