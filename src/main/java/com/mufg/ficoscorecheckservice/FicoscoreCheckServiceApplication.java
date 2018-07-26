package com.mufg.ficoscorecheckservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FicoscoreCheckServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FicoscoreCheckServiceApplication.class, args);
	}
}
