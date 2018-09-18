package com.real.lastmile.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.real.lastmile.configs.ServiceHelperConfig;
import com.real.lastmile.service.ServiceHelper;
import com.real.lastmile.vo.GpsRequest;
import com.real.lastmile.vo.GpsResponse;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceHelperConfig.class)
public class ServiceHelperTest {
	
	@Autowired
	private ServiceHelper serviceHelper;
	
	@Autowired
	private RestTemplate gpsApi;
	
	@Before
	public void intilializeMocks() {
		
		GpsResponse gpsResponse = new GpsResponse();
		gpsResponse.setDistance(10.0);
		ResponseEntity<GpsResponse> responseEntity = new ResponseEntity<GpsResponse>(gpsResponse, HttpStatus.OK);
		Mockito.when(gpsApi.exchange(Mockito.anyString(),
				Mockito.<HttpMethod> any(),
                Mockito.<HttpEntity<GpsRequest>> any(),
                Mockito.<Class<GpsResponse>> any())).thenReturn(responseEntity);
	}

	@Test
	public void testGetDistance() {
		GpsResponse gpsResponse = serviceHelper.getGpsResponse("Kronprinzenstraße 88, 40217 Düsseldorf", "Kaiserstraße 2, 40479 Düsseldorf", "url");
		assertNotNull(gpsResponse);
		assertEquals(10.0d, gpsResponse.getDistance(), 0.0001d);
	}
}
