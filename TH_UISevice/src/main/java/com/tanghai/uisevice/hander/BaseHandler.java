package com.tanghai.uisevice.hander;

import com.tanghai.library.handler.ResultHandler;
import com.tanghai.library.util.Debug;
import com.tanghai.uisevice.bean.ErrorBean;

import org.json.JSONObject;

/**
 * 相应数据处理基类
 * 
 * @date 2015.3.19
 * 
 */
public abstract class BaseHandler extends ResultHandler {

	@Override
	public void onSuccess(int statusCode, JSONObject response) {
		if (response != null) {
			Debug.o("response", response.toString());
			String errcode = response.optString("errcode");
			if (errcode != null && errcode.length() != 0 && !"0".equals(errcode)) {
				// 出现业务异常了
				String errmsg = response.optString("errmsg");
				onBusinessFailure(Integer.parseInt(errcode), errmsg);
			} else {
				// 获取成功，解析数据
				Integer message_type = response.optInt("message_type");
				Object rObj = null;
				if(message_type==0) {
					//成功
					rObj = parseBusinessData(response);
				}else {
					rObj = parseErrorData(response);
				}
				onNetSuccess(rObj);
			}
		}

	}

	private Object parseErrorData(JSONObject response) {
		JSONObject error_object = response.optJSONObject("error_object");
		ErrorBean errorBean = new ErrorBean();
		errorBean.errmsg = error_object.optString("error_message");
		return errorBean;
	}

	@Override
	public void onFailure(int statusCode, JSONObject errorResponse) {
		String errMsg = "网络异常";
		ErrorBean errorBean = new ErrorBean();
		errorBean.errmsg = errMsg;
		onNetFailure(statusCode, errMsg);
	}

	/**
	 * 接口获取数据成功
	 * 
	 * @param dataBean
	 */
	public abstract void onNetSuccess(Object dataBean);

	/**
	 * 接口获取数据失败
	 * 
	 * @param errorCode
	 * @param errorMsg
	 */
	public abstract void onNetFailure(int errorCode, String errorMsg);

	/**
	 * 业务数据返回异常
	 * 
	 * @param errorCode
	 * @param errorMsg
	 */
	public abstract void onBusinessFailure(int errorCode, String errorMsg);
	
	/**
	 * 解析业务数据
	 * @param response
	 */
	public abstract Object parseBusinessData(JSONObject response);

}
