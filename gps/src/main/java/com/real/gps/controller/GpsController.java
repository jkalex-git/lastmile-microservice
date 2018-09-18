package com.real.gps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.real.gps.service.GpsService;
import com.real.gps.vo.GpsRequest;
import com.real.gps.vo.GpsResponse;
/**
 * Main controller class for GPS api service
 * @author Joe
 *
 */
@RestController
public class GpsController {

	Logger logger = LoggerFactory.getLogger(GpsController.class);
	
	@Autowired
	GpsService gpsService;
	
	@Autowired
	private Environment env;
   /**
    * Method which accepts origin and destination address and respond with distance between them
    * @param gpsRequest
    * @return
    */
	@PostMapping("/distance")
    public GpsResponse getDistance(@RequestBody GpsRequest gpsRequest) {
		logger.info("Find Distance request received");
		logger.debug("Request Received {} on port {}", gpsRequest, env.getProperty("local.server.port"));
		
		GpsResponse gpsResponse = gpsService.getGpsResponse(gpsRequest.getOriginAddress(), gpsRequest.getDestAddress());
		if(null == gpsResponse) {
			logger.error("GPS response is null");
		}
        return gpsResponse;
    }
}