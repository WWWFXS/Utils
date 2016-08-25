package com.example.andutils.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectBean2Get {
	
	/**
	 * ！要求：本方法使用的条件是bean field和对应的get方法的定义必须符合getXXX上field字符串表现形式(get[大写字母]field字符串)。
	 * @param field java bean 对应的字段
	 * @param obj  对应的java bean对象
	 * @return
	 */
	
	public static Method objField2Get(Field field,Object obj){
		try {
		Class clazz = obj.getClass();
		String fieldName = field.getName();
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		//获得和属性对应的getXXX()方法的名字
		String getMethodName = "get"+firstLetter+fieldName.substring(1);
		Method getMethod = clazz.getMethod(getMethodName, new Class[]{});
		return getMethod;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
