package com.cfyj.demo.springcloud.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cfyj.demo.springcloud.zuul.mapper.ZuulRouteVOMapper;

@Configuration
public class CustomZuulConfig {

	@Autowired
	ZuulProperties zuulProperties;
	@Autowired
	ServerProperties server;
	
	@Autowired
	ZuulRouteVOMapper zuulRouteVOMapper;

	@Bean
	public CustomRouteLocator routeLocator() {
		CustomRouteLocator routeLocator = new CustomRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
		routeLocator.setZuulRouteVOMapper(zuulRouteVOMapper);
		return routeLocator;
	}
	
	

}
