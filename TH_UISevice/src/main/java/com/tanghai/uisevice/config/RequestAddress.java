package com.tanghai.uisevice.config;

/**
 * 获取或缓存请求地址。
 * 
 * @time 2013年 下午3:56:10
 * @author wang_fei
 */
public class RequestAddress {

	private static final String HOST_URL = "http://lequapi.digicircles.com/v1";//云
//	private static final String HOST_URL = "http://192.168.1.112:8080/v1";

	// 注册
	public static String REGISTER = HOST_URL + "/user/register";

}
