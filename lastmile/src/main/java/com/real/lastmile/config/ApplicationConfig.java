package com.real.lastmile.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "applicationprop")
public class ApplicationConfig {
	
	private double velocity;

	private String depotFile;
	
	private String storeFile;
	
	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public String getDepotFile() {
		return depotFile;
	}

	public void setDepotFile(String depotFile) {
		this.depotFile = depotFile;
	}

	public String getStoreFile() {
		return storeFile;
	}

	public void setStoreFile(String storeFile) {
		this.storeFile = storeFile;
	}
	
}
