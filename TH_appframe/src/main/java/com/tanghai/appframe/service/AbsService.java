/**
 * 
 */
package com.tanghai.appframe.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Debug;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import com.tanghai.appframe.MyApplication;

import de.greenrobot.event.EventBus;

/**
 * 后台服务抽象基类
 * 
 * @author fei.wang
 * @date 2015.3.16
 * 
 */
public abstract class AbsService extends Service implements Callback {
	private static final String TAG = AbsService.class.getName();

	// 通知管理器
	public NotificationManager notificationManager;
	// 网络连接管理器
	public ConnectivityManager connectivityManager;
	// 系统引用
	public MyApplication application;
	// 主线程消息处理器
	public Handler mainHandler;
	// 后台线程消息处理器
	public Handler workHandler;

	// 电源锁
	private WakeLock wakeLock;

	@Override
	public void onCreate() {
		super.onCreate();
		Debug.o(new Exception());
		// 获取通知管理器
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// 获取网络管事器
		connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		application = (MyApplication) getApplication();
		// 创建主线程消息处理器
		mainHandler = new Handler(this);
		Looper looper = application.getWorkLooper();
		// 创建后台线程消息处理器
		workHandler = new Handler(looper, new Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				return handleAsynMsg(msg);
			}
		});

		// acquireWakeLock();

		// 注册兼听器
		registerListener();
		// 注册事件处理回调
		EventBus.getDefault().register(this);
	}

	public void onDestroy() {
		super.onDestroy();
		Debug.o(new Exception());
		// releaseWakeLock();
		// 解除兼听听
		unregisterListener();
		// 解除事件处理回调
		EventBus.getDefault().unregister(this);
	}

	/**
	 * 获取部分电源锁，服务在后以运行时需要F
	 */
	private void acquireWakeLock() {
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		if (wakeLock == null) {
			wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
			wakeLock.acquire();
		}
	}

	/**
	 * 释放电源锁
	 */
	private void releaseWakeLock() {
		if (wakeLock != null && wakeLock.isHeld()) {
			wakeLock.release();
			wakeLock = null;
		}
	}

	/**
	 * 注册监听器，如：监听网络变化的broadcast
	 */
	public abstract void registerListener();

	/**
	 * 解除监听器，如：监听网络变化的broadcast
	 */
	public abstract void unregisterListener();

	/**
	 * 处事后台线程循环体投递过来的消息
	 * 
	 * @param msg
	 * @return
	 */
	public abstract boolean handleAsynMsg(Message msg);

	/**
	 * 事件回调处理器
	 * 
	 * @param evt
	 */
	public abstract void onEventMainThread(Object evt);
}
