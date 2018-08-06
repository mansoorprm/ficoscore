package com.mufg.ficoscorecheckservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class FicoscoreCheckServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FicoscoreCheckServiceApplication.class, args);
	}

	public Docket accountOpenApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("MUFGPOC").select()
				.apis(RequestHandlerSelectors.basePackage("com.mufg.ficoscorecheckservice.controller"))
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo("Fico Score Validation Service", "exploring swagger UI features"));
	}

	private ApiInfo apiInfo(String title, String description) {
		ApiInfo apiInfo = new ApiInfo("MUFG POC " + title + " API", " for " + description, "V1.0", "Terms of service",
				null, null, "null");
		return apiInfo;
	}
}
