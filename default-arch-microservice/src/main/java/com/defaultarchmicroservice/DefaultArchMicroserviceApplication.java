package com.defaultarchmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableEurekaClient
public class DefaultArchMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefaultArchMicroserviceApplication.class, args);
	}

}
