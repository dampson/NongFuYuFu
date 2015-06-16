package com.tanghai.uisevice.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 提示工具类，方便以后自定义
 * 
 * @author fei.wang
 * @date 2015.3.18
 * 
 */
public class ToastUtil {
	public static final String PAGE_DOWN = "已经没有了，亲！";

	private static Toast mToast;

	/**
	 * 提示内容来自资源文件
	 * 
	 * @param resId
	 */
	public static void showMessage(Context context,int resId) {
		if (mToast == null) {
			mToast = Toast.makeText(context, resId, Toast.LENGTH_LONG);
		} else {
			mToast.setText(resId);
			mToast.setDuration(Toast.LENGTH_LONG);
		}

		mToast.show();
	}

	/**
	 * 提示内容来自字符串
	 * 
	 * @param resId
	 */
	public static void showMessage(Context context,String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
		} else {
			mToast.setText(msg);
			mToast.setDuration(Toast.LENGTH_LONG);
		}

		mToast.show();
	}
}
