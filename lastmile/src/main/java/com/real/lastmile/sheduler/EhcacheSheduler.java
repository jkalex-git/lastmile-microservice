package com.real.lastmile.sheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.real.lastmile.service.AppService;
import com.real.lastmile.service.EhCacheService;

@Configuration
public class EhcacheSheduler {

	Logger logger = LoggerFactory.getLogger(EhcacheSheduler.class);
	
	@Autowired
	private EhCacheService ehCacheService;

	@Autowired
	private AppService appService;
	/**
	 * Scheduler which refresh the depot-store cache. 
	 * Distance between store and depot is cached as this is static data
	 */
	@Scheduled(fixedDelayString = "${scheduler.fixedDelay}", initialDelayString = "${scheduler.intialDelay}")
	public void schedulingCacheRefresh() {
		logger.info("Scheduled Refresh of cache depot-store starting");
		
		List<String> stores = appService.getAllStores();
		List<String> depots = appService.getAllDepots();
		if(null != stores && null != depots) {
			logger.debug("Clear depot-store cache");
			ehCacheService.clearStoreDepotCache();
			for(String store : stores) {
				for (String depot : depots) {
					ehCacheService.getDepotStoreDistance(depot, store);
				}
			}
		}
		logger.info("Scheduled Refresh of cache depot-store completed");
	}
}
