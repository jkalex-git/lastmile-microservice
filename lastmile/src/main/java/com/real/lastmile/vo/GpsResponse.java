package com.real.lastmile.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = false)
public class GpsResponse implements Serializable{

	private static final long serialVersionUID = -6032473342599407203L;
	
	private double distance;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GpsResponse [distance=").append(distance).append("]");
		return builder.toString();
	}
}
