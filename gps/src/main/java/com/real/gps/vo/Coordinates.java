package com.real.gps.vo;

public class Coordinates {
	public Coordinates() {
		super();
	}

	public Coordinates(double lat, double lng) {
		super();
		latitude = lat;
		longitude = lng;
	}

	double latitude;
	double longitude;

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coordinates [latitude=").append(latitude).append(", longitude=").append(longitude).append("]");
		return builder.toString();
	}

	
}
