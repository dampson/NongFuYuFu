package com.tanghai.uisevice.manager;

/**
 * 事件对象为空异常
 * 
 * @author fei.wang
 * @date 2015.3.25
 * 
 */
class EventObjNullException extends Exception {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	public EventObjNullException() {
		super();
	}

	public EventObjNullException(String msg) {
		super(msg);
	}

	public EventObjNullException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public EventObjNullException(Throwable cause) {
		super(cause);
	}

}
