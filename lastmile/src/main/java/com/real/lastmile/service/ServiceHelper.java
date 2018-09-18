package com.real.lastmile.service;

import java.util.List;

import com.real.lastmile.vo.GpsResponse;

public interface ServiceHelper {
	List<String> fileReader(String fileName);
	GpsResponse getGpsResponse(String url, String orginAdrs, String destAdrs);
	
}
