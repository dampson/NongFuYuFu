package com.tanghai.appframe.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;

import com.tanghai.appframe.utils.DigiImageLoader;

import de.greenrobot.event.EventBus;

/**
 * 抽象碎片布局基类
 *
 * @author hui.wang
 * @date 2015.3.16
 */
public abstract class AbsFragment extends Fragment implements OnClickListener, Callback {

    public Context context;
    public Handler handler;
    public ProgressDialog progressDialog;
    public DigiImageLoader digiImageLoader;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注册事件处理回调
        EventBus.getDefault().register(this);
        // 创建主线程消息处理器
        handler = new Handler(this);
        // 获取图片加载器实例
        digiImageLoader = DigiImageLoader.getInstance();
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

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
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
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 更新界面
     */
    public abstract void updateView();

    /**
     * 事件处理回调
     *
     * @param evt
     */
    public abstract void onEventMainThread(Object evt);

}
