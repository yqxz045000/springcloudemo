package com.cfyj.demo.springcloud.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cfyj.demo.springcloud.zuul.domain.AuthRequestResp;
import com.cfyj.demo.springcloud.zuul.domain.CodeDict;
import com.cfyj.demo.springcloud.zuul.domain.CommonResp;
import com.cfyj.demo.springcloud.zuul.utils.IPUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 监控的前置请求,记录请求的基本参数,如果未经过授权，则post过滤器都不执行
 * 
 * @author ls
 * @2018年10月22日
 */
@Component
@Slf4j
public class MonitorPreFilter extends ZuulFilter {


	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();
		CommonResp<AuthRequestResp> authorizeResp = null;
		HttpServletRequest request = ctx.getRequest();
		
		ctx.set("ServiceRequestTime", System.currentTimeMillis() + "");
		ctx.set("isSuccess", true);
		ctx.set("postExecuteFlag", true);
		authorizeResp = (CommonResp<AuthRequestResp>) ctx.get("authorizeResp");
		if (authorizeResp == null) {
			authorizeResp = new CommonResp(CodeDict.AUTHSERVICE_REQUESTFAIL);
		}
		
		try {

			log.info("domain:{}，请求接口:{},请求ip:{},source:{},请求appid:{},授权码:{},授权结果:{}",
					request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 7)),
					request.getRequestURI(), IPUtils.getIpFromRequest(request),
					request.getHeader("source"),
					request.getHeader("appid"), authorizeResp.getCode(),
					authorizeResp.getMsg());
		} catch (Exception e) {
			log.info("MonitorPreFilter 监控网关之前处理Filter异常", e);

		} finally {
			
			switch (authorizeResp.getCode()) {
				case "10004": {
					// 无需授权，放行
					break;
				}
	
				case "10003": {
					//
					break;
				}
	
				default: {
					// 授权不通过，直接返回
					log.info("无权限访问资源，直接返回，appid:{}，uri：{}", request.getHeader("appid"),request.getRequestURI());
					ctx.setSendZuulResponse(false); // 默认为true，设置为false则不将请求下发到下游服务,但仍会执行post过滤器，所以需要将所有的post过滤器关闭
					ctx.setResponseStatusCode(200);
					authorizeResp.setData(null);
					ctx.setResponseBody(JSON.toJSONString(authorizeResp));
					ctx.set("postExecuteFlag", false);
					break;
				}
			}

		}

		return null;
	}

	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public int filterOrder() {
		/**
		 * 数字越大，优先级越低,此filter过滤器顺序别改
		 */
		return 16;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
