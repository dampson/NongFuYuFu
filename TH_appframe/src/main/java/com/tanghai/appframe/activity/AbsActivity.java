package com.tanghai.appframe.activity;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.tanghai.appframe.MyApplication;
import com.tanghai.appframe.R;
import com.tanghai.appframe.utils.DigiImageLoader;

import de.greenrobot.event.EventBus;

/**
 * 应用框架层Activity抽象基类，定义一些常用方法及预处理
 * 
 * @author fei.wang
 * @date 2015.3.25
 * 
 */
public abstract class AbsActivity extends FragmentActivity implements OnClickListener, Callback {
	/** 应用程序引用 **/
	public MyApplication mApplication;
	/** 主线程消息处理器 **/
	public Handler mMainHandler;
	/** 后台线程消息处理器 **/
	public Handler mWorkHandler;
	/** 异步图片加载器 **/
	public DigiImageLoader digiImageLoader;

	/** 常用进度条，如：网络加载时所用的加载框 **/
	public ProgressDialog mProgressDialog;

	/** 返回类型 默认 1:返回上个页面 2:连续返回两次就可以退出 3:返回时退出，但是需要二次确认 4:页面自行处理返回操作 **/
	private int backType = 1;

	/** 返回两次就退出app **/
	private int exitCount = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApplication = (MyApplication) getApplication();
		// 注册事件理处回调
		EventBus.getDefault().register(this);
		// 创建主线程消息处理器
		mMainHandler = new Handler(this);

		Looper looper = mApplication.getWorkLooper();
		// 创建后台线程消息处理器
		mWorkHandler = new Handler(looper, new Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				return handleAsynMsg(msg);
			}
		});

		digiImageLoader = DigiImageLoader.getInstance();

		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setCanceledOnTouchOutside(false);
		// 子类设置布局
		setContentView();
		// 子类初始化界面
		initView();
		// 子类初始化数据
		initData();

	}

	@Override
	public void onResume() {
		super.onResume();
		// 子类更新界面
		updateView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// 解除事件处理回调
		EventBus.getDefault().unregister(this);
	}

	/**
	 * 设置acitivty的布局文件
	 */
	public abstract void setContentView();

	/**
	 * 初始化数据
	 */
	public abstract void initData();

	/**
	 * 实始化界面，如:findViewById, setOnClickListener
	 */
	public abstract void initView();

	/**
	 * 更新界面，如:activity从后台进入时在onResume中调用
	 */
	public abstract void updateView();

	/**
	 * 处理后台线程中的消息
	 * 
	 * @param msg
	 * @return
	 */
	public abstract boolean handleAsynMsg(Message msg);

	/**
	 * 消息处理回调函数，用于入理EventBus.getDefault().post(evt)传递过来的事件
	 * 
	 * @param evt
	 */
	public abstract void onEventMainThread(Object evt);

	/**
	 * 显示dialog 但是返回时，留在当前页面
	 */
	public void showLoadingAndStay() {
		mProgressDialog.setMessage(getString(R.string.tip_loading));
		mProgressDialog.show();
	}

	/**
	 * 显示dialog 并且屏蔽返回按钮
	 */
	public void showLoadingAndDisableKey() {
		mProgressDialog.setMessage(getString(R.string.tip_loading));
		mProgressDialog.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_MENU)) {
					return true;
				}
				return false;
			}
		});
		mProgressDialog.show();

	}

	/**
	 * 显示dialog但是返回时，返回上个页面
	 */
	public void showLoading() {
		mProgressDialog.setMessage(getString(R.string.tip_loading));
		mProgressDialog.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					mProgressDialog.dismiss();
					backPage();
					return true;
				}
				return false;
			}
		});
		mProgressDialog.show();
	}

	/**
	 * 显示dialog 但是返回时，留在当前页面
	 * 
	 * @param content
	 *            加载显示的内容
	 */
	public void showLoadingAndStay(String content) {
		mProgressDialog.setMessage(content);
		mProgressDialog.show();
	}

	/**
	 * 显示dialog 并且屏蔽返回按钮
	 * 
	 * @param content
	 *            加载显示的内容
	 */
	public void showLoadingAndDisableKey(String content) {
		mProgressDialog.setMessage(content);
		mProgressDialog.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_MENU)) {
					return true;
				}
				return false;
			}
		});
		mProgressDialog.show();
	}

	/**
	 * 显示dialog但是返回时，返回上个页面
	 * 
	 * @param content
	 *            加载显示的内容
	 */
	public void showLoading(String content) {
		mProgressDialog.setMessage(content);
		mProgressDialog.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					mProgressDialog.dismiss();
					backPage();
					return true;
				}
				return false;
			}
		});
		mProgressDialog.show();
	}

	/**
	 * 隐藏dialog
	 */
	public void hideLoading() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	/**
	 * 返回上个页面
	 */
	public void backPage() {
		if (backType == 1) {
			// 普通的返回
			finish();
		} else if (backType == 2) {
			// 需要连续点击两次返回
			exitCount++;
			if (exitCount == 1) {
				Toast.makeText(this, getString(R.string.tip_exit), Toast.LENGTH_SHORT).show();
				new CountDownTimer(2000, 500) {
					public void onTick(long millisUntilFinished) {

					}

					public void onFinish() {
						exitCount = 0;
					}

				}.start();
			}
			if (exitCount == 2) {
				// 退出程序
				exitApp();
			}
		} else if (backType == 3) {
			// 返回需要二次确认
			showExitDialogBy();
		} else if (backType == 4) {
			// 页面自行处理
			processBackPage();
		}

	}

	/**
	 * 退出确认框
	 */
	public void showExitDialogBy() {
		// 系统默认类型
		Builder builder = new Builder(this);
		builder.setMessage(R.string.tip_exit);
		builder.setTitle(getString(R.string.dialog_title));
		builder.setPositiveButton(getString(R.string.btn_sure), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				exitApp();
			}
		});
		builder.setNegativeButton(getString(R.string.btn_cancel), new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();

	}

	/**
	 * 退出整个APP，做一些清理工作
	 */
	public abstract void exitApp();

	/**
	 * 处理点击返回键时的操作
	 */
	public void processBackPage() {
	}

	/**
	 * 设置页面返回类型
	 * 
	 * @param backType
	 *            1:返回上个页面 <br>
	 *            2:连续返回两次就可以退出 <br>
	 *            3:返回时退出，但是需要二次确认 <br>
	 *            4:页面自行处理返回操作
	 */
	public void setBackType(int backType) {
		this.backType = backType;
	}
}
