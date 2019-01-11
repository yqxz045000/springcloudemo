package com.caiyi.sport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * @author B-0257
 *
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class StepEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StepEurekaServerApplication.class, args);
	}
}
