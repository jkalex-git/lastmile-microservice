package com.real.lastmile.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "scheduler")
public class SchedulerConfig {
    private String fixedDelay;
    private String intialDelay;

	public String getIntialDelay() {
		return intialDelay;
	}

	public void setIntialDelay(String intialDelay) {
		this.intialDelay = intialDelay;
	}

	public String getFixedDelay() {
		return fixedDelay;
	}

	public void setFixedDelay(String fixedDelay) {
		this.fixedDelay = fixedDelay;
	}
}