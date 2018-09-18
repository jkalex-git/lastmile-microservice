package com.real.lastmile.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "api")
public class GpsApiConfig {
	private String gpsUrl;
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public String getGpsUrl() {
		return gpsUrl;
	}

	public void setGpsUrl(String gpsUrl) {
		this.gpsUrl = gpsUrl;
	}
}
