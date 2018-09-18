package com.real.gps.service;

import com.real.gps.vo.GpsResponse;

public interface GpsService {
	GpsResponse getGpsResponse(String orgAdrs, String destAdrs);
}
