package com.tanghai.appframe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.tanghai.appframe.utils.DigiImageLoader;

/**
 * 抽象数据控制器基类
 * 
 * @author fei.wang
 * @date 2015.3.25
 * 
 */
public abstract class AbsAdapter extends BaseAdapter {
	private LayoutInflater layoutInflater;
	private Context context;

	public DigiImageLoader digiImageLoader;

	public AbsAdapter(Context ctx) {
		context = ctx;
		layoutInflater = LayoutInflater.from(ctx);
		digiImageLoader = DigiImageLoader.getInstance();
	}

	public LayoutInflater getLayoutInflater() {
		return layoutInflater;
	}

	public Context getContext() {
		return context;
	}

}
