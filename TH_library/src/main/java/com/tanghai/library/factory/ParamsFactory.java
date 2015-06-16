package com.tanghai.library.factory;

import com.loopj.android.http.RequestParams;

/**
 * 网络请求参数组装接口
 * 
 * @author fei.wang
 * @date 2015.3.25
 *  
 */
public interface ParamsFactory {
	/**
	 * 网络请求参数组装方法
	 */
	public abstract RequestParams create();
}
