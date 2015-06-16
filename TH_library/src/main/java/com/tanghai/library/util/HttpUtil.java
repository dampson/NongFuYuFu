/**
 * 
 */
package com.tanghai.library.util;

import org.apache.http.HttpEntity;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

/**
 * 网络请求工具类
 * 
 * @author fei.wang
 * @date 2015.3.25
 * 
 */
public class HttpUtil {

	private static HttpUtil httpUtil;

	private AsyncHttpClient asyncHttpClient;

	private HttpUtil() {
		asyncHttpClient = new AsyncHttpClient();
		asyncHttpClient.setTimeout(20000);
	}

	/**
	 * 获取对象单子实例
	 * 
	 * @return
	 */
	public static HttpUtil getInstance() {
		if (httpUtil == null) {
			httpUtil = new HttpUtil();
		}
		return httpUtil;
	}

	/**
	 * 以Get方式进行络请求
	 * 
	 * @param url
	 *            网络地址
	 * @param params
	 *            请求参数
	 * @param responseHandler
	 *            结果处理器
	 */
	public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		asyncHttpClient.get(url, params, responseHandler);
	}

	/**
	 * 以Post方式进行络请求
	 * 
	 * @param url
	 *            网络地址
	 * @param params
	 *            请求参数
	 * @param responseHandler
	 *            结果处理器
	 */
	public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		asyncHttpClient.post(url, params, responseHandler);
	}

	/**
	 * 以Post方式进行络请求(参数entity类型)
	 * 
	 * @param context
	 * @param url
	 *            网络地址
	 * @param entity
	 *            请求参数
	 * @param contentType
	 * @param responseHandler
	 */
	public void post(Context context, String url, HttpEntity entity, String contentType, AsyncHttpResponseHandler responseHandler) {
		
		asyncHttpClient.post(context, url, entity, contentType, responseHandler);
	}

	/**
	 * @return 异步网络请求客户端
	 */
	public AsyncHttpClient getAsyncHttpClient() {
		return asyncHttpClient;
	}

	/**
	 * @return 同步网络请求客户端
	 */
	public AsyncHttpClient getSyncHttpClient() {
		return new SyncHttpClient();
	}

}
