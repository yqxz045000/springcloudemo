package com.cfyj.demo.springcloud.zuul.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class AuthRequestReq {
	
	private Map<String,String> headersMap = new HashMap<String,String>();

//	private String uri;
//
//	private String accessToken;
//
//	private String appid;
//	
//	private String appMuId;  //唯一标识
//	
//	private int source ; 
//	
////	private AppNameEnum appName; //客户端类型
//    private String appVersion; //客户端版本
//    private String osVersion; //系统版本
//    private String phoneModel; //手机型号
//    private String releaseTime; //app打包时间
//    private String xposedExist; //是否安装xpose
//    private String root; //检查是否root
//    private String tinkerId; //补丁id
    
    
    
}
