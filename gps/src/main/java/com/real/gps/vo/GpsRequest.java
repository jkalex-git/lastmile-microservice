package com.real.gps.vo;

import java.io.Serializable;

public class GpsRequest implements Serializable{

	private static final long serialVersionUID = 4467623740099933778L;

	public GpsRequest() {
		super();
	}
	
	public GpsRequest(String originAddress, String destAddress) {
		super();
		this.originAddress = originAddress;
		this.destAddress = destAddress;
	}
	
	private String originAddress;
	private String destAddress;
	
	public String getOriginAddress() {
		return originAddress;
	}
	public String getDestAddress() {
		return destAddress;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GpsRequest [originAddress=").append(originAddress).append(", destAddress=").append(destAddress)
				.append("]");
		return builder.toString();
	}
	
	
}
