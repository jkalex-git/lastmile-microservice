package com.real.gps.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "applicationprop")
public class ApplicationConfig {


	private String coordinateFile;

	public String getCoordinateFile() {
		return coordinateFile;
	}

	public void setCoordinateFile(String coordinateFile) {
		this.coordinateFile = coordinateFile;
	}

	
}
