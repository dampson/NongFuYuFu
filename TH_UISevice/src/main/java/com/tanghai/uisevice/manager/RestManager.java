package com.tanghai.uisevice.manager;

import android.content.Context;

import com.tanghai.library.handler.ResultHandler;
import com.tanghai.library.util.HttpUtil;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

/**
 * HTTP Restful请求管理器
 * 
 * @author hui.wang
 * @date 2015.3.18
 * 
 */
public class RestManager {

	/**
	 * 请求远程接口数据
	 * 
	 * @param context
	 *            上下文
	 * @param url
	 *            请求地址
	 * @param entity
	 *            请求参数
	 * @param handler
	 *            解析类
	 */
	public static void requestRemoteData(Context context, String url, HttpEntity entity, ResultHandler handler) {
		((ByteArrayEntity)entity).setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		HttpUtil.getInstance().post(context, url, entity, "application/json", handler);
	}

}
