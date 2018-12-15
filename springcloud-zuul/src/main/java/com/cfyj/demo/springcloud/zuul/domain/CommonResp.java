package com.cfyj.demo.springcloud.zuul.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;

@ApiModel
@Slf4j
public class CommonResp<T> extends Response {
	
	@ApiModelProperty("响应数据，类型不固定")
	private T data ;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public CommonResp() {
	}
	
	public CommonResp(CodeDict code) {
		super(code);
	}
	
	public CommonResp(String code, String msg, T data) {
		super(code, msg);
		this.data = data;
	}
	public CommonResp(String code, String msg) {
		super(code, msg);
	}

	public boolean isSuccess() {
		if (CodeDict.SUCCESS.getCode().equals(this.getCode())) {
			return true;
		}
		return false;
	}

	/**
	 * 成功
	 * @return
	 */
	public static CommonResp ok() {
		return new CommonResp(CodeDict.SUCCESS.getCode(), CodeDict.SUCCESS.getMsg());
	}
	/**
	 * 成功+msg
	 * @param msg
	 * @return
	 */
	public static CommonResp ok(String msg) {
		return new CommonResp(CodeDict.SUCCESS.getCode(), msg);
	}
	/**
	 * 成功+msg+data
	 * @param msg
	 * @return
	 */
	public static CommonResp ok(String msg, Object data) {
		return new CommonResp(CodeDict.SUCCESS.getCode(), msg, data);
	}
	/**
	 * 成功code+msg+data
	 * @return
	 */
	public static CommonResp ok(CodeDict codeDict, Object data) {
		return new CommonResp(codeDict.getCode(), codeDict.getMsg(), data);
	}
	/**
	 * 成功code+msg+data
	 * @return
	 */
	public static CommonResp ok(String code, String msg, Object data) {
		return new CommonResp(code, msg, data);
	}
	/**
	 * 失败
	 * @return
	 */
	public static CommonResp fail() {
		return new CommonResp(CodeDict.FAILED.getCode(), CodeDict.FAILED.getMsg());
	}
	/**
	 * 失败+msg
	 * @param msg
	 * @return
	 */
	public static CommonResp fail(String msg) {
		log.error("msg:{}", msg);
		return new CommonResp(CodeDict.FAILED.getCode(), msg);
	}
	/**
	 * 失败+msg
	 * @param msg
	 * @return
	 */
	public static CommonResp failMsgData(String msg, Object data) {
		log.error("msg:{}", msg);
		return new CommonResp(CodeDict.FAILED.getCode(), msg, data);
	}
	/**
	 * 失败+msg
	 * @param msg
	 * @return
	 */
	public static CommonResp failCodeMsgData(String code, String msg, Object data) {
		log.error("code:{},msg:{}", code, msg);
		return new CommonResp(code, msg, data);
	}

	/**
	 * 失败code+msg
	 * @param msg
	 * @return
	 */
	public static CommonResp fail(String code, String msg) {
		log.error("code:{},msg:{}", code, msg);
		return new CommonResp(code, msg);
	}
	/**
	 * 失败code+msg
	 * @param codeDict 枚举
	 * @return
	 */
	public static CommonResp fail(CodeDict codeDict) {
		log.error("codeDict:{},msg:{}", codeDict.getCode(), codeDict.getMsg());
		return new CommonResp(codeDict.getCode(), codeDict.getMsg());
	}
	/**
	 * 失败code+msg+数据
	 * @param codeDict
	 * @param data
	 * @return
	 */
	public static CommonResp fail(CodeDict codeDict, Object data) {
		log.error("codeDict:{},msg:{}", codeDict.getCode(), codeDict.getMsg());
		return new CommonResp(codeDict.getCode(), codeDict.getMsg(), data);
	}

}
