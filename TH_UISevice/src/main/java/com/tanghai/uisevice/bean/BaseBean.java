package com.tanghai.uisevice.bean;

import java.io.Serializable;

/**
 * 数据bean基类
 * 
 * @author fei.wang
 * @date 2015.3.18
 * 
 */
public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// 接口异常code
	public String errcode;
	// 接口异常描述
	public String errmsg;

}
