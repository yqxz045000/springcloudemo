//package com.caiyi.sport.filter;
//
//import java.util.Enumeration;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Component;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//public class AccessFilter extends ZuulFilter {
//
//	@Override
//	public Object run() {
//		RequestContext ctx = RequestContext.getCurrentContext();
//		HttpServletRequest request = ctx.getRequest();
//		
////		log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
////		Object accessToken = request.getParameter("accessToken");
////		if (accessToken == null) {
////			log.warn("access token is empty");
////			ctx.setSendZuulResponse(false);
////			ctx.setResponseStatusCode(401);
////			return null;
////		}
////		log.info("access token ok");
//		printHeader(request);
//		
//		
//		
//		
//		
//		return null;
//	}
//	
//	private void printHeader(HttpServletRequest request) {
//		
//		log.info("输出请求header");
//		Enumeration<String> headerNames = request.getHeaderNames();
//		StringBuilder sb = new StringBuilder();
//		while(headerNames.hasMoreElements()) {
//			
//			String hn = headerNames.nextElement();
//			String hv = request.getHeader(hn);
//			sb.append(hn).append("=").append(hv).append(";");
//		}
//		log.info(sb.toString());						
//	}
//
//	@Override
//	public boolean shouldFilter() {	
//		return true;
//	}
//
//	@Override
//	public int filterOrder() {
//		return 0;
//	}
//
//	@Override
//	public String filterType() {
//		return "route";
//	}
//
//}
