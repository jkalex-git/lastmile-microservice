package com.real.lastmile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.real.lastmile.vo.GpsResponse;
import com.real.lastmile.vo.RouteVO;

@Service
@CacheConfig(cacheNames = "distanceCache")
public class EhCacheServiceImpl implements EhCacheService{
	Logger logger = LoggerFactory.getLogger(EhCacheServiceImpl.class);
	
	@Value("${api.gpsUrl}")
	private String gpsApiUrl;
	
	@Autowired
	private ServiceHelper serviceHelper;
	/**
	 * Method which gets distance from gps api.
	 * Response is cached 
	 */
	@Cacheable
	public GpsResponse getDistance(String orginAdrs, String destAdrs) {
		logger.info("Cache(distanceCache) miss for origin address {} and destination address {}", orginAdrs, destAdrs);
		
		GpsResponse gpsResponse = null;
		gpsResponse = serviceHelper.getGpsResponse(gpsApiUrl, orginAdrs, destAdrs);
		return gpsResponse;
	}
	/**
	 * Method which gets distance between stores and depots
	 * Result is cached
	 * Scheduler configured to run this method.
	 */
	@Cacheable("depot-store")
	public RouteVO getDepotStoreDistance(String depot, String store) {
		logger.info("Cache(depot-store) miss for depot {} & store {}", depot, store);
		
		RouteVO routeVO = null;
		logger.debug("Finding distance from store {} to depot {}", store, depot);
		
		GpsResponse	gpsResponse = getDistance(store, depot);
		if(null != gpsResponse) {
			routeVO = new RouteVO(depot, store, gpsResponse.getDistance());
		} else {
			logger.error("Get Distance returned null");
		}
		
		logger.debug("Depot to Store distance is {}", routeVO);
		return routeVO;
	}
	
	@CacheEvict(value = "depot-store", allEntries = true)
    public void clearStoreDepotCache(){
		logger.info("Evicting cache: depot-store");
	}
}
