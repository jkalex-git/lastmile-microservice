package com.real.lastmile.vo;

public class RouteVO {
		
	public RouteVO(String depot, String store, double distance) {
		super();
		this.depot = depot;
		this.store = store;
		this.distance = distance;
	}
	private String depot;
	private String store;
	private double distance; 
	
	public String getDepot() {
		return depot;
	}
	public String getStore() {
		return store;
	}
	public double getDistance() {
		return distance;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RouteVO [depot=");
		builder.append(depot);
		builder.append(", store=");
		builder.append(store);
		builder.append(", distance=");
		builder.append(distance);
		builder.append("]");
		return builder.toString();
	}	
	
	
}
