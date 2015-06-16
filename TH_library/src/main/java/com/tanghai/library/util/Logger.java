package com.tanghai.library.util;

import android.os.Environment;
import android.util.Log;

import com.tanghai.library.Constants;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * log适配类
 * 
 * @time 2013年 下午3:51:16
 * @author wang_fei
 */
public class Logger {

	private static final String TAG = "LeQu";

	private static boolean isLog = false;

	private static final boolean WRITE_SDCARD_LOG = true;

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ms");

	// 异常日志大小限制在5M
	private static final float ERR_LOG_SIZE = 5 * 1024.0f;

	/**
	 * <code>true</code> 开启日志功能 <code>false</code> 关闭日志功能
	 */
	static {
		isLog = Constants.LOG;
	}

	private Logger() {
	}

	private static String buildMsg(String msg) {
		StringBuilder buffer = new StringBuilder();
		Date date = new Date();
		String time = sdf.format(date);

		buffer.append(time);
		final StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
		buffer.append(" [");
		buffer.append(Thread.currentThread().getName());
		buffer.append(":");
		buffer.append(stackTraceElement.getFileName());
		buffer.append(":");
		buffer.append(stackTraceElement.getLineNumber());
		buffer.append(":");
		buffer.append(stackTraceElement.getMethodName());
		buffer.append("()] ");
		buffer.append(msg);
		buffer.append("\n");
		return buffer.toString();
	}

	public static void v(String msg) {
		if (isLog && Log.isLoggable(TAG, Log.VERBOSE)) {
			Log.v(TAG, buildMsg(msg));
		}
	}

	public static void v(String tag, String msg) {
		if (isLog && Log.isLoggable(TAG, Log.VERBOSE)) {
			Log.v(tag, buildMsg(msg));
		}
	}

	public static void d(String msg) {
		if (isLog && Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(TAG, buildMsg(msg));
		}
	}

	public static void d(String tag, String msg) {
		if (isLog && Log.isLoggable(TAG, Log.DEBUG)) {
			Log.d(tag, buildMsg(msg));
		}
	}

	public static void i(String msg) {
		if (isLog && Log.isLoggable(TAG, Log.INFO)) {
			Log.i(TAG, buildMsg(msg));
		}
	}

	public static void i(String tag, String msg) {
		if (isLog && Log.isLoggable(TAG, Log.INFO)) {
			Log.i(tag, buildMsg(msg));
		}
	}

	public static void w(String msg) {
		if (isLog && Log.isLoggable(TAG, Log.WARN)) {
			String info = buildMsg(msg);
			Log.w(TAG, info);

			if (WRITE_SDCARD_LOG) {
				writeFileToSD(info);
			}
		}
	}

	public static void w(String tag, String msg) {
		if (isLog && Log.isLoggable(TAG, Log.WARN)) {
			String info = buildMsg(msg);
			Log.w(tag, info);

			if (WRITE_SDCARD_LOG) {
				writeFileToSD(info);
			}
		}
	}

	public static void w(String msg, Exception e) {
		if (isLog && Log.isLoggable(TAG, Log.WARN)) {
			String info = buildMsg(msg);
			Log.w(TAG, info, e);

			if (WRITE_SDCARD_LOG) {
				writeFileToSD(info + StringUtil.getStackTrace(e));
			}
		}
	}

	public static void w(String tag, String msg, Exception e) {
		if (isLog && Log.isLoggable(TAG, Log.WARN)) {
			String info = buildMsg(msg);
			Log.w(tag, info, e);

			if (WRITE_SDCARD_LOG) {
				writeFileToSD(info + StringUtil.getStackTrace(e));
			}
		}
	}

	public static void e(String msg) {
		if (isLog && Log.isLoggable(TAG, Log.ERROR)) {
			String info = buildMsg(msg);
			Log.e(TAG, info);

			if (WRITE_SDCARD_LOG) {
				writeFileToSD(info);
			}
		}
	}

	public static void e(String tag, String msg) {
		if (isLog && Log.isLoggable(TAG, Log.ERROR)) {
			String info = buildMsg(msg);
			Log.e(tag, info);

			if (WRITE_SDCARD_LOG) {
				writeFileToSD(info);
			}
		}
	}

	public static void e(String msg, Exception e) {
		if (isLog && Log.isLoggable(TAG, Log.ERROR)) {
			String info = buildMsg(msg);
			Log.e(TAG, info, e);

			if (WRITE_SDCARD_LOG) {
				writeFileToSD(info + StringUtil.getStackTrace(e));
			}
		}
	}

	public static void e(String tag, String msg, Exception e) {
		if (isLog && Log.isLoggable(TAG, Log.ERROR)) {
			String info = buildMsg(msg);
			Log.e(tag, info, e);

			if (WRITE_SDCARD_LOG) {
				writeFileToSD(info + StringUtil.getStackTrace(e));
			}
		}
	}

	/**
	 * 
	 * @param context
	 */
	private static void writeFileToSD(String context) {
		RandomAccessFile raf = null;
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
			Log.d(TAG, "SD card is not avaiable right now.");
			return;
		}
		try {
			String pathName = "/sdcard/lequ/";
			String fileName = "err.log";
			File path = new File(pathName);
			File file = new File(pathName + fileName);
			if (!path.exists()) {
				Log.d(TAG, "Create the path:" + pathName);
				path.mkdir();
			}
			if (!file.exists()) {
				Log.d(TAG, "Create the file:" + fileName);
				file.createNewFile();
			}

			raf = new RandomAccessFile(file, "rw");
			if (file.length() > ERR_LOG_SIZE) {
				raf.seek(0);
			} else {
				raf.seek(file.length());
			}

			raf.write(context.getBytes());
		} catch (Exception e) {
			Log.e(TAG, "Error to write SD card.");
		} finally {
			if (null != raf) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
