package com.tanghai.uisevice.manager;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.tanghai.library.util.FileUtils;
import com.tanghai.uisevice.R;
import com.tanghai.uisevice.config.Constants;

/**
 * 乐趣管理类
 * 
 * @author fei.wang
 * @date 2015.3.25
 * 
 */
public class AppManager {

	/**
	 * 初始化乐趣管理类
	 * 
	 * @param ctx
	 */
	public static void init(Context ctx) {
		createAppDir(ctx);
	}

	/**
	 * 创建APP常用目录
	 * 
	 * @param ctx
	 */
	public static void createAppDir(Context context) {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && Environment.getExternalStorageDirectory().canWrite()) {
			FileUtils.createDir(Constants.APP_DIR);
			FileUtils.createDir(Constants.IMAGE_DIR);
			//TXFileUtils.createDir(Constants.MUSIC_DIR);
			//TXFileUtils.createDir(Constants.VIDEO_DIR);
			FileUtils.createDir(Constants.CACHE_DIR);
			FileUtils.createDir(Constants.APK_DIR);
			FileUtils.createDir(Constants.DB_DIR);
			FileUtils.createDir(Constants.LOG_DIR);
		} else {
			Toast.makeText(context, R.string.cannot_write_sdcard, Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 提示超时
	 * 
	 * @param context
	 */
	public static void toastTimeout(Context context) {
		Toast.makeText(context, R.string.network_timeout, Toast.LENGTH_LONG).show();
	}

	/**
	 * 提示开发中
	 * 
	 * @param context
	 */
	public static void toastDvlp(Context context) {
		Toast.makeText(context, R.string.developing, Toast.LENGTH_LONG).show();
	}
}
