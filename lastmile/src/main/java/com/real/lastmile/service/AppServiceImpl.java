package com.real.lastmile.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class AppServiceImpl implements AppService {

	private static final String workingDir = System.getProperty("user.dir");
	
	Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);
	
	@Autowired
	private ServiceHelper serviceHelper; 
	
	@Value("${applicationprop.depotFile}")
	private String depotFileLoc;
	
	@Value("${applicationprop.storeFile}")
	private String storeFileLoc;
	
	@Override
	public List<String> getAllDepots() {
		logger.debug("Getting all Depots from location {}", depotFileLoc);
		List<String> depots = serviceHelper.fileReader(workingDir + depotFileLoc);
		if(CollectionUtils.isEmpty(depots)) {
			logger.error("Depot list is empty");
		}
		
		return depots;
	}

	@Override
	public List<String> getAllStores() {
		logger.debug("Getting all Stores from location {}", storeFileLoc);
		List<String> stores = serviceHelper.fileReader(workingDir + storeFileLoc);
		if(CollectionUtils.isEmpty(stores)) {
			logger.error("Store list is empty");
		}
		return stores;
	}

}
