package com.real.gps.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.real.gps.vo.Coordinates;

@Component
public class GpsServiceHelper {
	
	private static final String workingDir = System.getProperty("user.dir");
	
	Logger logger = LoggerFactory.getLogger(GpsServiceHelper.class);
	
	@Value("${applicationprop.coordinateFile}")
	private String coordinateFileLoc;
	
	/**
	 * Calculate distance between two points in latitude and longitude taking into
	 * account height difference. If you are not interested in height difference
	 * pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters el2
	 * End altitude in meters
	 * 
	 * @returns Distance in Meters
	 */
	public double getDistance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {
		logger.debug("Finding distance between lat1:{} - long1:{} and lat2:{} - long2:{}", new Object[]{lat1,lon1,lat2,lon2});
		
		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = el1 - el2;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance)/1000.0d;
	}
	/**
	 * Method which returns latitude and longitude of the address given as parameter.
	 * @param orgAdrs
	 * @return Coordinates
	 */
	public Coordinates getCoordinates(String orgAdrs) {

		Coordinates coordinates = null;
		String fileName = workingDir + coordinateFileLoc;
		logger.debug("Reading from file {}", fileName);
		
		List<String> lines = fileReader(fileName);
		if(!CollectionUtils.isEmpty(lines)) {
			for(String line : lines) {
				String coordinate[] = line.split("~~");
				if(StringUtils.equals(orgAdrs, coordinate[0])) {
					double lat = Double.parseDouble(coordinate[1].split(":")[0]);
					double lng = Double.parseDouble(coordinate[1].split(":")[1]);
					coordinates = new Coordinates(lat, lng);
					break;
				}
			}
		} else {
			logger.error("Empty file {}", fileName);
		}
		if(null == coordinates) {
			logger.error("No coordinate found for address {} in file {}", orgAdrs, fileName);
		}
		return coordinates;
	}
	
	public List<String> fileReader(String fileName) {
		logger.debug("Reading file {}", fileName);
		
		List<String> lines = null;
		try {
			 File file = new File(fileName);
			 lines = FileUtils.readLines(file, "UTF-8"); 
		} catch (IOException e) {
			logger.error("Failed to read from file {}", fileName);
			logger.error("Exception occured {}", e.getMessage());
		}
		
		return lines;
	}
}
