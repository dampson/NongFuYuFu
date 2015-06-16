package com.tanghai.uisevice.config;

/**
 * Handler、的宏定义。
 * 
 * @time 2013年 下午3:54:58
 * @author wang_fei
 */
public class MacroUtil {
	
	public static final int NETWORK_TIMEOUT = -200;

	public static final int ERR_CODE = -1;			
	public static final int LOGIN = 1001;// 登陆
	public static final int BIND_ACCOUNT = 1002;// 绑定账户
	public static final int SEACH_SCHOOL = 1003;// 根据关键字搜索学校
	public static final int LOCATION_SCHOOL = 1004;// 根据坐标搜索附近学校
	public static final int SCROLLBAR_LIST = 1005;// 首页顶部滚动条信息
	public static final int FOCUS_INFO = 1006;// 四个焦点信息区

	
	
	/*************** 页面之间数据传输宏定义 ********************/
	public static final String SCHOOL = "school";// 学校
	public static final String EMAIL = "email";// 邮箱
	public static final String PASSWORD = "password";// 密码

	
	

}
