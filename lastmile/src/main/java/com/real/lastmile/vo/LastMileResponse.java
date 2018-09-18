package com.real.lastmile.vo;

import java.io.Serializable;

public class LastMileResponse extends RouteVO implements Serializable {

	private static final long serialVersionUID = 3935753671557973592L;
	
	public LastMileResponse(String depot, String store, double distance, double velocity) {
		super(depot, store, distance);
		this.time = distance/velocity;
	}
	
	private double time;
	
	public double getTime() {
		return time;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LastMileResponse [time=");
		builder.append(time);
		builder.append(", getDepot()=");
		builder.append(getDepot());
		builder.append(", getStore()=");
		builder.append(getStore());
		builder.append(", getDistance()=");
		builder.append(getDistance());
		builder.append("]");
		return builder.toString();
	}
	
	
}
