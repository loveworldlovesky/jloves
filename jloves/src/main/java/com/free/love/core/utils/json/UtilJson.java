package com.free.love.core.utils.json;

 
 import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
 
 public class UtilJson
 {
   private static final ObjectMapper mapper = new ObjectMapper();
 
   private void UtilJson()
   {
   }
 
   public static String toJson(Object bean)
   {
     return toJson(bean, true);
   }
 
   public static String toJson(Object bean, boolean includeNull)
   {
     try
     {
       if ((bean instanceof String)) return (String)bean;
 
       String json = null;
       ObjectMapper mapper = null;
       if (includeNull) {
         json = mapper.writeValueAsString(bean);
       }
       else
       {
         mapper = new ObjectMapper();
         mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
       }return mapper.writeValueAsString(bean);
     }
     catch (Exception e)
     {
       throw new RuntimeException(e);
     }
   }
 
   public static <E> Object parseJson(String json, Class clazz)
   {
     try
     {
       return mapper.readValue(json, clazz);
     }
     catch (Exception e) {
       throw new RuntimeException(e);
     }
   }
 }

