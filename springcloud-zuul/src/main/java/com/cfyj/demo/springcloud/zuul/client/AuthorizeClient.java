package com.cfyj.demo.springcloud.zuul.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cfyj.demo.springcloud.zuul.domain.AuthRequestReq;
import com.cfyj.demo.springcloud.zuul.domain.AuthRequestResp;
import com.cfyj.demo.springcloud.zuul.domain.CommonResp;

@Component
@FeignClient(name = "step-authorize")
public interface AuthorizeClient {
	
	@RequestMapping(value = "/authorize/authorizeRequest.do", method = RequestMethod.POST)
	CommonResp<AuthRequestResp> authorizeRequest(@RequestBody AuthRequestReq req);
	
	

}
