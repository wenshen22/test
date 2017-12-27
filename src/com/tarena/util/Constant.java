package com.tarena.util;

/**
 * 声明常量,在以后中用
 */
public class Constant {
	//用户等级
	public static final int NORMAL  =  0;
	public static final int VIP     =  1;
	//订单状态
	public static final int CONFIRM =  1;
	public static final int WAIT    =  2;
	public static final int SEND    =  3;
	public static final int UNPAY   =  4;
	public static final int PAY     =  5;
	public static final int BACK    =  6;
	//Session中的key
	public static final String CODE_KEY = "code";
	public static final String USER_KEY = "loginUser";
	public static final String CART_KEY = "cart";
	public static final String OPTS_KEY = "opts";
	public static final String SELE_KEY = "select";
	//放在cookie中的cart的key
	public static final String COOKIE_CART = "cart";
}
