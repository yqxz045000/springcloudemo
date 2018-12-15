package com.cfyj.demo.springcloud.zuul.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value= "响应对象")
public class Response {
	
	@ApiModelProperty(notes = "响应code码 ，0成功，1失败，2异常,9未登录，10无权限，-1不允许操作" , required = true , example = "1")
	private String code = CodeDict.SUCCESS.getCode();
	@ApiModelProperty(value = "响应消息" , required = true )
	private String msg = CodeDict.SUCCESS.getMsg();

	public Response(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public Response(CodeDict code) {
		this.code = code.getCode();
		this.msg =code.getMsg();
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Response() {
		
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", msg=" + msg + "]";
	}

}
