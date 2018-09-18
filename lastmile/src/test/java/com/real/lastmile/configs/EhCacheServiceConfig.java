package com.real.lastmile.configs;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.real.lastmile.helper.TestHelper;
import com.real.lastmile.service.AppService;
import com.real.lastmile.service.AppServiceImpl;
import com.real.lastmile.service.EhCacheService;
import com.real.lastmile.service.EhCacheServiceImpl;
import com.real.lastmile.service.ServiceHelper;

public class EhCacheServiceConfig {

	public EhCacheServiceConfig() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Bean
	public AppService appService() {
		return new AppServiceImpl();
	}
	
	@Bean
	public EhCacheService ehCacheService() {
		return new EhCacheServiceImpl();
	}
	
	@Bean
	public ServiceHelper serviceHelper() {
		return serviceHelper;
	}
	
	@Mock
	public ServiceHelper serviceHelper;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	 return new TestHelper().getProperties();
	}
	
	
	
}
