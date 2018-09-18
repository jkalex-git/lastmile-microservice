package com.real.gps.vo;

public class GpsResponse {

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
