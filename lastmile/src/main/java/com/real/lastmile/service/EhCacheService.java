package com.real.lastmile.service;

import com.real.lastmile.vo.GpsResponse;
import com.real.lastmile.vo.RouteVO;

public interface EhCacheService {

	GpsResponse getDistance(String orginAdrs, String destAdrs);
	RouteVO getDepotStoreDistance(String depot, String store);
	void clearStoreDepotCache();
}
