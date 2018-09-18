package com.real.lastmile.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

public class TestHelper {
	public List<String> fileReader(String fileName) {

		List<String> lines = new ArrayList<>();
		//Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public PropertySourcesPlaceholderConfigurer getProperties() {
		  PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		  YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		  yaml.setResources(new ClassPathResource("test-application.yml"));
		  propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
		  return propertySourcesPlaceholderConfigurer;
	}
}
