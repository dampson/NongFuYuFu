/**
 * 
 */
package com.tanghai.uisevice.manager;

/**
 * 事件通知消息类
 * 
 * @author fei.wang
 * @date 2015.3.25
 * 
 */
public class HttpResponseEvent {

	// 网络请求成功码
	public static final int REQUET_OK_CODE = 200;
	// 请求业务类型
	public int requestType;
	// 业务状态码
	public int statusCode;
	// 错误信息
	public String message;
	// 业务数据
	public Object[] object;

	public HttpResponseEvent(int type, int code, String msg, Object... obj) {
		requestType = type;
		statusCode = code;
		message = msg;
		object = obj;
	}

	public HttpResponseEvent(int type, int code, Object... obj) {
		requestType = type;
		statusCode = code;
		object = obj;
	}

	public HttpResponseEvent(int type, int code, String msg) {
		requestType = type;
		statusCode = code;
		message = msg;
	}

	public HttpResponseEvent(int type, int code) {
		requestType = type;
		statusCode = code;
	}

	public String toString() {
		return "[" + requestType + ", " + statusCode + "]";
	}
}
