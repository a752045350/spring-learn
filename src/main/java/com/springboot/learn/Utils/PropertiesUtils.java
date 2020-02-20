package com.springboot.learn.Utils;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class PropertiesUtils {

	/**
	 * 根据文件路径获取相关属性
	 * @param filePath
	 * @return
	 */
	public static Map<String,String> getProperties(String filePath){
		//保存返回结果
		Map<String,String> map = new HashMap<String,String>();
		
		try
		{
			java.util.Properties pps = new java.util.Properties();
	        pps.load(new FileInputStream(filePath));
	        Enumeration<?> enum1 = pps.propertyNames();//得到配置文件的名字
	        while(enum1.hasMoreElements()) {
	            String strKey = (String) enum1.nextElement();
	            String strValue = pps.getProperty(strKey);
	            map.put(strKey, strValue);
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return map;
	}
	
}
