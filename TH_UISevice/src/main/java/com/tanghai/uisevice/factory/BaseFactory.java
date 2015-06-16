package com.tanghai.uisevice.factory;

import android.text.TextUtils;

import com.tanghai.library.factory.RequestFactory;

/**
 * 接口请求数据基类
 * 
 * @author fei.wang
 * @date 2015.3.17
 * 
 */
public abstract class BaseFactory extends RequestFactory {

	/**
	 * 获取接口请求地址
	 * 
	 * @param url
	 * @return
	 */
	public String getUrlWithQueryString(String url) {
		if (!TextUtils.isEmpty(url)) {
			return url;
		}
		return null;
	}

}
