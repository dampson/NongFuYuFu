package com.tanghai.appframe;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.tanghai.library.util.ShareFileUtil;

import de.greenrobot.event.EventBus;

/**
 * 应用程序抽象类，用来初始化一些应用框架所需要的配置
 * 
 * @author fei.wang
 * @date 2015.3.25
 * 
 */
public abstract class MyApplication extends Application implements Runnable {
	private static final String TAG = MyApplication.class.getName();
	// 后台线程消息处理器
	private Handler workHandler;
	// 线程的循环体
	private Looper looper;
	// 具有循环对列的后台线程
	private HandlerThread handlerThread;

	@Override
	public void onCreate() {
		super.onCreate();
		// 注册事件理处回调
		EventBus.getDefault().register(this);
		// 初始化ShareFile文件实例
		ShareFileUtil.getInstance().init(this);
		// 创建具有循环对列的后台线程
		handlerThread = new HandlerThread(TAG);
		// 开始后台线程体
		handlerThread.start();
		// 获取后台线程的循环体
		looper = handlerThread.getLooper();
		// 创建后台线程相关的Handler外理器
		workHandler = new Handler(looper);
		// 投递给后台线程一个runnable运行体，用来做一些初使化较耗时的动作
		workHandler.post(this);
	}

	/**
	 * 返回后台线程的Handler处理器
	 * 
	 * @return
	 */
	public Handler getWorkHandler() {
		return workHandler;
	}

	/**
	 * 返回后台线程的循环体
	 * 
	 * @return
	 */
	public Looper getWorkLooper() {
		return looper;
	}

	/**
	 * 消息处理回调函数，用于入理EventBus.getDefault().post(evt)传递过来的事件
	 * 
	 * @param evt
	 */
	public abstract void onEventMainThread(Object evt);

}
