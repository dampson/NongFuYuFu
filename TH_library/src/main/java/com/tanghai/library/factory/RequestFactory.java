package com.tanghai.library.factory;

import com.loopj.android.http.RequestParams;

/**
 * 网络请求参数组装抽象类
 * 
 * @author fei.wang
 * @date 2015.3.25
 * 
 */
public abstract class RequestFactory implements ParamsFactory {
	
	protected RequestParams mRequestParams;

	public RequestFactory() {
		mRequestParams = new RequestParams();
	}
}
