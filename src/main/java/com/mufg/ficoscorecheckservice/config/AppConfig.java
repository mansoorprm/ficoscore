package com.mufg.ficoscorecheckservice.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author rajasekarj
 *
 */
@Configuration
@ComponentScan("com.mufg.fraudcheckservice")
public class AppConfig {
	@Bean
	public RestTemplate restTemplate(MUFGCustomClientHttpRequestInterceptor customClientHttpRequestInterceptor) {
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
		RestTemplate restTemplate = new RestTemplate(factory);
		List<ClientHttpRequestInterceptor> interceptors = Collections.singletonList(customClientHttpRequestInterceptor);
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}
}

