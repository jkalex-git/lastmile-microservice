package com.real.lastmile.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.real.lastmile.vo.GpsRequest;
import com.real.lastmile.vo.GpsResponse;

@Component
public class ServiceHelperImpl implements ServiceHelper{
	Logger logger = LoggerFactory.getLogger(ServiceHelperImpl.class);

	@Autowired
	private RestTemplate gpsApi;
	/**
	 * Generic file reader function
	 */
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

	/**
	 * Method which uses rest template to find the distance from GPS API service
	 */
	public GpsResponse getGpsResponse(String url, String orginAdrs, String destAdrs) {
		ResponseEntity<GpsResponse> responseEntity = null;
		GpsResponse gpsResponse = null;
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<GpsRequest> request = new HttpEntity<>(new GpsRequest(orginAdrs, destAdrs), requestHeaders);
		logger.debug("Rest API call to GPS API to url {}", url);
		try {
			responseEntity = gpsApi.exchange(url, HttpMethod.POST, request, GpsResponse.class);
		} catch (RestClientException e) {
			logger.error("GPS API call failed: {}", e.getMessage());
		}
		if(null != responseEntity && HttpStatus.OK == responseEntity.getStatusCode()) {
			gpsResponse = responseEntity.getBody();
		} else {
			logger.error("GPS API called failed with response: {}", responseEntity);
		}
		return gpsResponse;
	}
	
}
