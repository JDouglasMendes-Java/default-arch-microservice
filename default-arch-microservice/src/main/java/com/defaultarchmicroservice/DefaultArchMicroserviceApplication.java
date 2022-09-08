package com.defaultarchmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DefaultArchMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DefaultArchMicroserviceApplication.class, args);
	}

}
