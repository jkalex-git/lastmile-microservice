package com.real.lastmile.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.real.lastmile.vo.LastMileResponse;

@Service
public class ControllerServiceImpl implements ControllerService {

	@Autowired
	private AppService appService;
	
	@Autowired
	private EhCacheService ehCacheService;
	
	@Value("${applicationprop.velocity}")
	private double velocity;
	
	Logger logger = LoggerFactory.getLogger(ControllerServiceImpl.class);
	/**
	 * Method which finds the shortest route
	 * Brute force approach
	 * Distance from customer to every store is only calculated on each request.
	 * Store - Depot distance is pre-calculated and is readily available in cache.
	 */
	@Override
	public LastMileResponse getDepotStore(String custAdrs) {
		
		double distance = Double.MAX_VALUE;
		String finalStore = null;
		String finalDepot = null;
		LastMileResponse lastMileResponse = null;
		List<String> stores = appService.getAllStores();
		List<String> depots = appService.getAllDepots();
		if(null != stores && null != depots) {
			logger.debug("Finding the closest store-depot from the customer:{}", custAdrs);
			for(String store : stores) {
				//Get distance from Customer to store and to depot
				double custToStore = ehCacheService.getDistance(custAdrs, store).getDistance();
				logger.debug("Distance {} between customer address {} and Store {}", custToStore, custAdrs, store);
				for(String depot : depots) {
					//Depot to store distance is static and is retreived from cache.
					double depotToStore = ehCacheService.getDepotStoreDistance(depot, store).getDistance();
					logger.debug("Distance {} between depot {} and Store {}", depotToStore, depot, store);
					if((custToStore + depotToStore) < distance) {
						distance = custToStore + depotToStore;
						finalStore = store;
						finalDepot = depot;
						logger.debug("New short distance match");
					}
				}
			}
		} else {
			logger.error("Store list or Depot list is null. Stores {} & Depots {}", stores, depots);
		}
		if(null != finalDepot) {
			lastMileResponse = new LastMileResponse(finalDepot, finalStore, distance, velocity);
		} else {
			logger.error("Final Depot is null");
		}
		logger.debug("Final response {}", lastMileResponse);
		
		return lastMileResponse;
	}

}
