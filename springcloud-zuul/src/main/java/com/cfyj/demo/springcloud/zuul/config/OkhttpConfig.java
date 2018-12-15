//package com.caiyi.sport.config;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import okhttp3.ConnectionPool;
//
//@Configuration
//@ConditionalOnProperty("ribbon.okhttp.enable")
//@ConditionalOnClass(name = "okhttp3.OkHttpClient")
//public class OkhttpConfig {
//
//	@Autowired
//	OkHttpLoggingInterceptor okHttpLoggingInterceptor;
//
//	@Bean
//	public okhttp3.OkHttpClient okHttpClient() {
//		return new okhttp3.OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS)
//				.writeTimeout(120, TimeUnit.SECONDS).connectionPool(new ConnectionPool())
//				// .addInterceptor();
//				.build();
//	}
//
//}
