package com.tanghai.library.util;

import android.util.Log;

import com.tanghai.library.Constants;

/**
 * 调试工具类
 * 
 * @author fei.wang
 * @date 2015.3.25
 * 
 */
public class Debug {
	private static final String TAG = "frame";

	public static int lineNumber(Exception ex) {
		return ex.getStackTrace()[0].getLineNumber();
	}

	public static String funcName(Exception ex) {
		return ex.getStackTrace()[0].getMethodName();
	}

	public static String className(Exception ex) {
		return ex.getStackTrace()[0].getClassName();
	}

	public static String fileName(Exception ex) {
		return ex.getStackTrace()[0].getFileName();
	}

	/**
	 * Print log message <filename>: <line number> : <method name> : msg
	 * 
	 * @param ex
	 * @param msg
	 */
	public static void o(Exception ex, String msg) {
		if (Constants.DEBUG) {
			Log.d(TAG, fileName(ex) + " : " + lineNumber(ex) + " : "
					+ funcName(ex) + " : " + msg);
		}
	}

	public static void o(Exception ex) {
		if (Constants.DEBUG) {
			Log.d(TAG, fileName(ex) + " : " + lineNumber(ex) + " : "
					+ funcName(ex));
		}
	}

	public static void o(String tag, String msg) {
		if (Constants.DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void o(String tag, String msg, Throwable tr) {
		if (Constants.DEBUG) {
			Log.d(tag, msg, tr);
		}
	}
}
