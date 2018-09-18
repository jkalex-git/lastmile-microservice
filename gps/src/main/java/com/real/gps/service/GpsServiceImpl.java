package com.real.gps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.real.gps.vo.Coordinates;
import com.real.gps.vo.GpsResponse;

@Component
public class GpsServiceImpl implements GpsService {
	
	Logger logger = LoggerFactory.getLogger(GpsServiceImpl.class);
	
	@Autowired
	GpsServiceHelper helper;
	
	public GpsResponse getGpsResponse(String orgAdrs, String destAdrs) {
		GpsResponse gpsResponse = null;
		Coordinates orgCoordiates = helper.getCoordinates(orgAdrs);
		logger.debug("GPS co-ordinates for origin address {} is {}", orgAdrs, orgCoordiates);
		
		Coordinates destCoordinates = helper.getCoordinates(destAdrs);
		logger.debug("GPS co-ordinates for destination address {} is {}", destAdrs, destCoordinates);
		
		if(null != orgCoordiates && null != destCoordinates) {
			gpsResponse = new GpsResponse();
			gpsResponse.setDistance(helper.getDistance(orgCoordiates.getLatitude(), destCoordinates.getLatitude(), orgCoordiates.getLongitude(), destCoordinates.getLongitude(), 0.0d, 0.0d));
			logger.debug("Distane calculated:{}", gpsResponse.getDistance());
		}		
		return gpsResponse;
	}
}
