package com.tanghai.appframe.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.view.View.OnClickListener;
import android.view.Window;

import com.tanghai.appframe.utils.DigiImageLoader;

import de.greenrobot.event.EventBus;

public abstract class AbsDialog extends Dialog implements OnClickListener, Callback{
	
	public Context context;
	public Handler handler;
	public ProgressDialog progressDialog;
	public DigiImageLoader digiImageLoader;

	public AbsDialog(Context context) {
		super(context);
		this.context = context;
	}
	
	public AbsDialog(Context context,int theme) {
		super(context,  theme);
		this.context = context;
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		// 注册事件处理回调
		EventBus.getDefault().register(this);
		// 创建主线程消息处理器
		handler = new Handler(this);
		// 获取图片加载器实例
		digiImageLoader = DigiImageLoader.getInstance();
		
		// 子类设置布局
		setContentView();
		// 子类初始化界面
		initView();
		// 子类初始化数据
		initData();
	}
	
	/**
	 * 显示加载框
	 */
	public void showProgressDialog() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(context);
			// progressDialog.setTitle(R.string.please_wait);
			// progressDialog.setMessage(getString(R.string.please_wait));
			progressDialog.setIndeterminate(true);
		}
		progressDialog.show();
	}

	/**
	 * 隐藏加载框
	 */
	public void dismissProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}
	
	/**
	 * 设置acitivty的布局文件
	 */
	public abstract void setContentView();
	
	/**
	 * 实始化界面，如:findViewById, setOnClickListener
	 */
	public abstract void initView();
	
	/**
	 * 初始化数据
	 */
	public abstract void initData();

	/**
	 * 事件处理回调
	 * 
	 * @param evt
	 */
	public abstract void onEventMainThread(Object evt);
	
	@Override
	protected void onStop() {
		super.onStop();
		// 解除事件处理回调
		EventBus.getDefault().unregister(this);
		context = null;
	}

}
