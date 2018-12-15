package com.cfyj.demo.springcloud.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 监控post
 * @author ls
 * @2018年10月22日
 */
@Component
@Slf4j
public class MonitorPostFilter extends ZuulFilter{
	

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		boolean flag = false; 
		if(ctx.get("isExecuteFlag")!=null) {
			if( (boolean)ctx.get("isExecuteFlag")==false) {
				return false;
			}
		}
		
		if(ctx.get("postExecuteFlag")!=null) {
			flag = (boolean) ctx.get("postExecuteFlag");
		}
		return flag ;
	}

	@Override
	public Object run() {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		long time = 0 ; 
		
		if(ctx.get("ServiceRequestTime")!=null ) {		
			time = System.currentTimeMillis() - Long.parseLong((String)ctx.get("ServiceRequestTime"));	
		}
		
		log.info("调用下游服务成功,uri:{},请求appid:{},本次响应耗时:{}",request.getRequestURI(),request.getHeader("appId"),time);	
	
		//如果需要取出响应数据，需要使用方式如下
//		Object zuulResponse = RequestContext.getCurrentContext().get("zuulResponse");
//		if (zuulResponse != null) {
//			RibbonHttpResponse resp = (RibbonHttpResponse) zuulResponse;
//			String serviceRequesTime = resp.getHeaders().get("ServiceRequestTime").get(0);
//	     }

		return null;
	}

	@Override
	public String filterType() {
		
		return "post";
	}

	@Override
	public int filterOrder() {
		return 15;
	}

}
