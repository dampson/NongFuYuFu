/**
 * 
 */
package com.tanghai.library.handler;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.tanghai.library.util.Debug;

import org.apache.http.Header;
import org.json.JSONObject;


/**
 * 网络请求结果处理抽象类
 * 
 * @author fei.wang
 * @date 2015.3.25
 * 
 */
public abstract class ResultHandler extends JsonHttpResponseHandler {
	/**
	 * 服务器以对象的形式返回数据
	 * 
	 * @param statusCode
	 *            http response status line
	 * @param headers
	 *            response headers if any
	 * @param response
	 *            parsed response if any
	 */
	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
		Debug.o(new Exception(), "statusCode = " + statusCode + ", response = " + response);
		onSuccess(statusCode, response);
	}

	/**
	 * 服务器返回数据出错
	 * 
	 * @param statusCode
	 *            http response status line
	 * @param headers
	 *            response headers if any
	 * @param throwable
	 *            throwable describing the way request failed
	 * @param errorResponse
	 *            parsed response if any
	 */
	public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
		Debug.o(new Exception(), "statusCode = " + statusCode + ", errorResponse = " + errorResponse);
		onFailure(statusCode, errorResponse);
	}

	/**
	 * 网络请求成功回调
	 * 
	 * @param statusCode
	 *            状态码
	 * @param response
	 *            返回结果
	 */
	public abstract void onSuccess(int statusCode, JSONObject response);

	/**
	 * 网络请求失败回调
	 * 
	 * @param statusCode
	 *            状态码
	 * @param errorResponse
	 *            返回结果
	 */
	public abstract void onFailure(int statusCode, JSONObject errorResponse);

}
