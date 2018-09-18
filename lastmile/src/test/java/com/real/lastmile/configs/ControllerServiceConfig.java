package com.real.lastmile.configs;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.real.lastmile.service.AppService;
import com.real.lastmile.service.AppServiceImpl;
import com.real.lastmile.service.ControllerService;
import com.real.lastmile.service.ControllerServiceImpl;
import com.real.lastmile.service.EhCacheService;
import com.real.lastmile.service.ServiceHelper;

public class ControllerServiceConfig {
	public ControllerServiceConfig() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Bean
	public AppService appService() {
		return new AppServiceImpl();
	}
	
	@Bean
	public EhCacheService ehCacheService() {
		return ehCacheService;
	}
	
	@Bean
	public ServiceHelper serviceHelper() {
		return serviceHelper;
	}
	
	@Bean
	public ControllerService controllerService( ) {
		return new ControllerServiceImpl();
	}
	
	@Mock
	public EhCacheService ehCacheService;
	
	@Mock
	public ServiceHelper serviceHelper;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	  PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	  YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
	  yaml.setResources(new ClassPathResource("test-application.yml"));
	  propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
	  return propertySourcesPlaceholderConfigurer;
	}
}
