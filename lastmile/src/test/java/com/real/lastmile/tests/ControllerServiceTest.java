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

import com.real.lastmile.configs.ControllerServiceConfig;
import com.real.lastmile.helper.TestHelper;
import com.real.lastmile.service.ControllerService;
import com.real.lastmile.service.EhCacheService;
import com.real.lastmile.service.ServiceHelper;
import com.real.lastmile.vo.GpsResponse;
import com.real.lastmile.vo.LastMileResponse;
import com.real.lastmile.vo.RouteVO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ControllerServiceConfig.class)
public class ControllerServiceTest {
	@Autowired
	private EhCacheService ehCacheService;
	
	@Autowired
	private ControllerService controllerService;
	
	@Autowired
	private ServiceHelper serviceHelper;
	
	@Value("${applicationprop.depotFile}")
	private String depotFile;
	
	@Value("${applicationprop.storeFile}")
	private String storeFile;
	
	@Value("${applicationprop.velocity}")
	private double velocity;
	
	@Before
	public void intilializeMocks() {
		
		GpsResponse gpsResponse = new GpsResponse();
		gpsResponse.setDistance(10.0);
		RouteVO routeVO = new RouteVO("Am Albertussee 1, 40549 Düsseldorf", "Breslauer Str. 2, 41460 Neuss", 10.0);
		Mockito.when(ehCacheService.getDistance(Mockito.anyString(), Mockito.anyString())).thenReturn(gpsResponse);
		Mockito.when(ehCacheService.getDepotStoreDistance(Mockito.anyString(), Mockito.anyString())).thenReturn(routeVO);
		Mockito.when(serviceHelper.fileReader(Mockito.contains("Store"))).thenReturn(new TestHelper().fileReader(storeFile));
		Mockito.when(serviceHelper.fileReader(Mockito.contains("Depot"))).thenReturn(new TestHelper().fileReader(depotFile));
	}
	
	@Test
	public void getDepotStore() {
		LastMileResponse response = controllerService.getDepotStore("Schiessstraße 31, 40549 Düsseldorf");
		assertNotNull(response);
		assertEquals(20.0, response.getDistance(), 0.0001d);
		assertEquals(20.0/velocity, response.getTime(), 0.0001d);
	}
}
