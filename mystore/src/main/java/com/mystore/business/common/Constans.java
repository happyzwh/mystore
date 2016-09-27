package com.mystore.business.common;

public class Constans {
	
	//登录session前缀
	public static final String KEY_SESSION="key_session";
	//登录session缓存 单位(时)
	public static final Long VALUE_TIME_SESSION = 3l;
	//品牌log上传路径
	public static final String PATH_UPLOAD_LOGO_BRAND="upload/brand/logo/";
	//商品图片上传路径
	public static final String PATH_UPLOAD_IMG_PRO="upload/pro/img/";
	//上传文件大小
	public static final Double SIZE_MAX_IMG_UPLOAD = 2.0*1024*1024;
	//24时格式
	public static final String DATE_FORMATE_24h2b = "yyyy-MM-dd HH:mm:ss";
	//
	public static final String DATE_FORMATE = "yyyyMMddHHmmss";
	//验证码前缀
	public static final String KEY_VERIFYCODE="key_verifycode";
	//验证码缓存时长 单位(分)
	public static final Long VALUE_TIME_VERIFYCODE = 2l;
	//购物车键
	public static final String KEY_COOKIE_CART = "key_cookie_cart";
	//购物车缓存 单位(时)
	public static final Long VALUE_TIME_COOKIE_CART = 3l;
	//同步购物车到库键
	public static final String KEY_CART_SYN_USER = "key_cart_syn_user";
	//同步购物车到库 时长(分)
	public static final Long VALUE_CART_SYN_USER = 2l;
	//同步购物车到库 次数
	public static final Long COUNT_CART_SYN_USER = 5l;

}
