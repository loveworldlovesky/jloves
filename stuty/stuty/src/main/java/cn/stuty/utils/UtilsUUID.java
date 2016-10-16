package cn.stuty.utils;


import java.util.UUID;

public class UtilsUUID {
	/**
	 * 生成32位uuid值
	 * @return String
	 * @author zwc
	 * 2016年8月31日
	 */
	public static String getId(){
		String s = UUID.randomUUID().toString().replace("-", "");
		
		return s;
	}
	public static void main(String[] args) {
		String s = getId();
		System.out.println(s.length()+","+s);
	}
}
