package com.lkh.sboot.common;

import java.util.Map;

/**
 * $.ajax后需要接受的JSON
 * 
 * @author zhaoxiaohua
 * 
 */
public class AjaxJson {

	private boolean success = false;// 是否成功
	private String msg = "操作失败";// 提示信息
	private Object obj = null;// 其他信息
	private Map<String, Object> attributes;// 其他参数

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

}
