package com.cfyj.demo.springcloud.zuul.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cfyj.demo.springcloud.zuul.client.AuthorizeClient;
import com.cfyj.demo.springcloud.zuul.domain.AuthRequestReq;
import com.cfyj.demo.springcloud.zuul.domain.AuthRequestResp;
import com.cfyj.demo.springcloud.zuul.domain.CodeDict;
import com.cfyj.demo.springcloud.zuul.domain.CommonResp;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 授权的filter
 * 
 * @author ls
 * @2018年10月22日
 */
@Slf4j
@Component
public class AuthorizeFilter extends ZuulFilter {
	
	@Autowired
	private AuthorizeClient authClient;
	
	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public Object run() {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		CommonResp<AuthRequestResp> resp = null;

		if("OPTIONS".equals(ctx.getRequest().getMethod())) {
			resp = new CommonResp(CodeDict.AUTHSERVICE_NEEDAUTHORIZE_NO);
			resp.setMsg("OPTIONS 请求放行---------------");
			ctx.put("authorizeResp", resp);
			return null ; 
		}else if(!ctx.getRequest().getRequestURI().endsWith(".do")) {
			resp = new CommonResp(CodeDict.AUTHSERVICE_NEEDAUTHORIZE_NO);
			resp.setMsg("非.do请求放行---------------");
			ctx.put("authorizeResp", resp);
			return null ; 
			
		}
		try {
			AuthRequestReq req = setData(ctx.getRequest());
			resp = authClient.authorizeRequest(req);
			ctx.put("authorizeResp", resp);

		} catch (Exception e) {
			log.error("调用远程授权服务失败，本次请求失败", e);
			resp = new CommonResp(CodeDict.AUTHSERVICE_REQUESTFAIL);
			ctx.put("authorizeResp", resp);
		}
	
		return null;
	}

	private AuthRequestReq setData(HttpServletRequest request) {
		Enumeration<String> headerNames = request.getHeaderNames();
		AuthRequestReq req = new AuthRequestReq();
		Map<String,String> headersMap = new HashMap<String,String>();
		
		while(headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			if(StringUtils.isNotBlank(headerName)) {
				String value  = request.getHeader(headerName);
				headersMap.put(headerName.toUpperCase(), value);						
			}
		}
		headersMap.put("URI", request.getRequestURI());
		req.setHeadersMap(headersMap);
		return req; 
	}
	
	
	
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {	
		return 15;
	}
	

}
