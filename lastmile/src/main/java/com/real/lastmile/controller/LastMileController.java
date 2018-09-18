package com.real.lastmile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.real.lastmile.service.ControllerService;
import com.real.lastmile.vo.LastMileResponse;
/**
 * Main controller class
 * @author Joe 
 *
 */
@RestController
public class LastMileController {

	Logger logger = LoggerFactory.getLogger(LastMileController.class);
	
	@Autowired
	private ControllerService controllerService;
	
	/**
	 * Method which accepts address and respond with nearest store,depot, distance in km and time in hour
	 * CrossOrigin enabled
	 * @param custAdrs
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/findDepotStore")
    public LastMileResponse getDepotStore(@RequestParam(value="custAdrs") String custAdrs) {
		
		logger.info("Find Depot Store request received");
		logger.debug("Customer Address Received {}", custAdrs);
		
        return controllerService.getDepotStore(custAdrs);
    }
}