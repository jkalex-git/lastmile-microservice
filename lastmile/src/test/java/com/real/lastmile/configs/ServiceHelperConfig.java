package com.real.lastmile.configs;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.real.lastmile.service.ServiceHelper;
import com.real.lastmile.service.ServiceHelperImpl;

public class ServiceHelperConfig {
	
	public ServiceHelperConfig() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Mock
    private RestTemplate gpsApi;
	
	@Bean
	public ServiceHelper serviceHelper() {
		return new ServiceHelperImpl();
	}
	
	@Bean
	public RestTemplate gpsApi() {
		return gpsApi;
	}
}