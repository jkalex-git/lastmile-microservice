package com.real.lastmile.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.real.lastmile.configs.EhCacheServiceConfig;
import com.real.lastmile.service.EhCacheService;
import com.real.lastmile.service.ServiceHelper;
import com.real.lastmile.vo.GpsResponse;
import com.real.lastmile.vo.RouteVO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EhCacheServiceConfig.class)
public class EhCacheServiceTest {

	@Autowired
	private EhCacheService cacheService;
	
	@Autowired
	private ServiceHelper serviceHelper;
	
	@Value("${applicationprop.depotFile}")
	private String depotFile;
	
	@Before
	public void intilializeMocks() {
		
		GpsResponse gpsResponse = new GpsResponse();
		gpsResponse.setDistance(10.0);
		Mockito.when(serviceHelper.getGpsResponse(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(gpsResponse);
	}
	
	@Test
	public void getClosestDepot() {
		RouteVO routeVO = cacheService.getDepotStoreDistance("Metrostrasse 12, 40235 Düsseldorf", "Schiessstraße 31, 40549 Düsseldorf");
		assertNotNull(routeVO);
		assertEquals(10.0, routeVO.getDistance(), 0.0001d);
		
	}
	
}
