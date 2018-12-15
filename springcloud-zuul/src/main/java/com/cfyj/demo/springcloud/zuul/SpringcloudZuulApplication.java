package com.cfyj.demo.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 备注：
 * 	1.过滤器filterOrder 定义： 数值越小优先级越高，自定义过滤器同一类型初始化定义顺序为 15，如优先级需靠前，则在8-15之间嵌入
 * 	2.如果未通过授权过滤器，则所有的自定义post过滤器都不执行，使用获取执行标识，如下：
 * 		RequestContext ctx = RequestContext.getCurrentContext(); 
 * 		return (boolean) ctx.get("isSuccess");
 *  3.动态路由生效时间在15s内
 *  --------------------------------------------------------------------------------------
 *  各过滤器作用：
 *  AuthorizeFilter ： 授权过滤器，类型pre，请求远程服务授权资源请求
 *  MonitorPreFilter: 监控请求过滤器，打印请求信息，并且授权的结果会在这里进行决策，当授权不通过时，不路由到下游服务，直接返回给前端
 *  MonitorPostFilter： 监控请求响应过滤器，打印本次响应的时间和一部分请求信息。
 *  ErrorFilter： 拦截全局异常，返回对用户友好的提示。
 * ----------------------------------------------------------------------------------------
 * 动态路由：
 * 	动态路由可以关注X个类
 * 	ZuulServerAutoConfiguration：zuul的自动配置类，加载了监听器，路由定位器，配置器，请求处理器
 * CompositeRouteLocator--路由组装器（提供获取路由列表、获取匹配的路由、刷新路由的方法，在后面的路由转发时也是这里匹配的。）
 * SimpleRouteLocator ：简单路由定位器，从配置文件中加载路由配置的路由定位器，加载路由信息由locateRoutes()来实现
 * DiscoveryClientRouteLocator：从eureka中加载路由信息，zuul默认是使用eureka做服务发现，来获取服务和路由信息，会为每个服务都创建一个路由配置，
 * 
 * 自定义的动态路由是参考SimpleRouteLocator+ DiscoveryClientRouteLocator 实现的，实现思路：
 * 	1.实现RefreshableRouteLocator接口，因为要实现refrensh方法和SimpleRouteLocator类（实现他是因为要服用其中的基本方法）
 *  2.重写locateRoutes()方法，这个方法回返回路由信息，比如从db中读取路由配置然后返回
 *  3.装载自定义bean，如在@Configuration的配置类下装载这个bean，使用@Configuration是提高自定义bean的装载优先级
 *  
 * ZuulServerAutoConfiguration 会将所有实现RefreshableRouteLocator的接口的类装载进 CompositeRouteLocator类中（所以叫他路由组装器，获取路由列表，刷新路由，获取匹配的路由都是他做的） 
 * 
 * 
 * @author ls
 * @2018年11月12日
 */
@SpringCloudApplication
@EnableDiscoveryClient
@EnableZuulProxy
@ComponentScan("com.caiyi.sport")
@EnableFeignClients
public class SpringcloudZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudZuulApplication.class, args);
	}
	
	
	/**
	 * 解决跨域
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true); // 允许cookies跨域
		config.addAllowedOrigin("*");// #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
		config.addAllowedHeader("*");// #允许访问的头信息,*表示全部
		config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
		config.addAllowedMethod("OPTIONS");// 允许提交请求的方法，*表示全部允许
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");// 允许Get的请求方法
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
