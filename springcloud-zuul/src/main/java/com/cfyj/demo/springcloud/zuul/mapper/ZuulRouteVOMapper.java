package com.cfyj.demo.springcloud.zuul.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cfyj.demo.springcloud.zuul.config.CustomRouteLocator.ZuulRouteVO;

@Mapper
public interface ZuulRouteVOMapper {

	@Select("select id as id,path as path,service_id as serviceId,url as url,retryable as retryable,enabled as enabled,strip_prefix as stripPrefix   from tb_gatewayConfig where enabled = 1 order by ISORT desc ")
	List<ZuulRouteVO> queryZuulConfig();
	
	

}
