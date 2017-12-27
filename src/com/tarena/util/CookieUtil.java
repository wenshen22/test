package com.tarena.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类，提供了cookie的创建，查找，删除方法
 * @author Administrator
 *
 */
public class CookieUtil {
	//缺省的路径
	private static String defalut_path="/ssh_shoppingcart";
	//缺省的生存时间
	private static int default_age = 365*24*3600;
	
	/**
	 * 添加一个cookie，考虑了编码问题
	 * @param name
	 * @param value
	 * @param response
	 * @param age
	 * @throws UnsupportedEncodingException
	 */
	public static void addCookie(String name, String value, HttpServletResponse response,
				int age) throws UnsupportedEncodingException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value,"utf-8"));
		cookie.setMaxAge(age);
		cookie.setPath(defalut_path);
		response.addCookie(cookie);
	}
	/**
	 * 使用缺省时间来创建cookie
	 */
	public static void addCookie(String name, String value, HttpServletResponse response) 
			throws UnsupportedEncodingException{
		addCookie(name, value, response, default_age);
	}
	
	/**
	 * 依据cookie的名字，查找cookie的值
	 * 如果name对应的cookie不存在，则返回null
	 * @param name
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String findCookie(String name, HttpServletRequest request) 
			throws UnsupportedEncodingException{
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie curr = cookies[i];
				if (curr.getName().equals(name)) {
					value = URLDecoder.decode(curr.getValue(),"utf-8");
				}
			}
		}
		return value;
	}
	
	/**
	 * 依据cookie的名称删除该cookie
	 * @param name
	 * @param response
	 */
	public static void deleteCookie(String name, HttpServletResponse response){
		Cookie cookie = new Cookie(name, "");
		cookie.setPath(defalut_path);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

}
