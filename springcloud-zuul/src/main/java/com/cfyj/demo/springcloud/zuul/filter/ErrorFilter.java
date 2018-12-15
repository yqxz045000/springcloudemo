package com.cfyj.demo.springcloud.zuul.filter;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cfyj.demo.springcloud.zuul.domain.CommonResp;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ErrorFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {

		return RequestContext.getCurrentContext().containsKey("throwable");
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext ctx = RequestContext.getCurrentContext();
		Object e = ctx.get("throwable");

		if (e != null) {
			log.error("拦截网关全局异常，其后的post过滤器不再执行,", e);
			// 删除该异常信息,不然在下一个过滤器中还会被执行处理
			ctx.remove("throwable");

			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(200);
			ctx.setResponseBody(JSON.toJSONString(CommonResp.fail("请求繁忙，请稍后访问")));
			ctx.set("isExecuteFlag", false);
		}

		return null;
	}

	@Override
	public String filterType() {

		return "error";
	}

	@Override
	public int filterOrder() {

		return -1;
	}
	

}
