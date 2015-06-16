package com.tanghai.uisevice.config;

import android.os.Environment;

/**
 * 常用变量类
 * 
 * @author fei.wang
 * @date 2015.3.18
 * 
 */
public class Constants {
	// 应用调试开关
	public static final boolean DEBUG = true;

	public static String ROOT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();

	public static String APP_DIR = ROOT_DIR + "/lequ/";
	public static String MUSIC_DIR = APP_DIR + "music/";
	public static String IMAGE_DIR = APP_DIR + "image/";
	public static String VIDEO_DIR = APP_DIR + "video/";
	public static String CACHE_DIR = APP_DIR + "cache/";
	public static String APK_DIR = APP_DIR + "apk/";
	public static String DB_DIR = APP_DIR + "db/";
	public static String LOG_DIR = APP_DIR + "log/";
	
	//ShareFileUtil 配置
	public static String USER_EMAIL = "user_email";
	public static String USER_PASSWORD = "user_password";
	public static String USER_ID = "user_id";
	public static String USER_NICE_NAME = "nice_name";
	public static String USER_SEX = "user_sex";
	public static String USER_ICON_URL = "user_icon_url";
	public static String USER_SCHOOL_NAME = "user_school_name";
	
	//环信
	public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
	public static final String GROUP_USERNAME = "item_groups";
	public static final String MESSAGE_ATTR_IS_VOICE_CALL = "is_voice_call";
	public static final String MESSAGE_ATTR_IS_VIDEO_CALL = "is_video_call";
	public static final String ACCOUNT_REMOVED = "account_removed";

	//极光推送生成的唯一ID
	public static final String JPUSH_ID = "JPUSH_ID";
}
