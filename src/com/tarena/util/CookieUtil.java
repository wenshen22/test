package com.tarena.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie�����࣬�ṩ��cookie�Ĵ��������ң�ɾ������
 * @author Administrator
 *
 */
public class CookieUtil {
	//ȱʡ��·��
	private static String defalut_path="/ssh_shoppingcart";
	//ȱʡ������ʱ��
	private static int default_age = 365*24*3600;
	
	/**
	 * ���һ��cookie�������˱�������
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
	 * ʹ��ȱʡʱ��������cookie
	 */
	public static void addCookie(String name, String value, HttpServletResponse response) 
			throws UnsupportedEncodingException{
		addCookie(name, value, response, default_age);
	}
	
	/**
	 * ����cookie�����֣�����cookie��ֵ
	 * ���name��Ӧ��cookie�����ڣ��򷵻�null
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
	 * ����cookie������ɾ����cookie
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
