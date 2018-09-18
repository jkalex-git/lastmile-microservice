package com.real.gps;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.real.gps.service.GpsServiceHelper;
import com.real.gps.vo.Coordinates;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ServiceHelperTest 
{
	@Autowired
	private GpsServiceHelper serviceHelper;
	
	@Test
	public void testGetDistance( ) {
		double distance = serviceHelper.getDistance(51.21171649999999d, 51.23516d, 6.770084199999928d, 6.778400000000033d, 0.0, 0.0);
		assertEquals(2.67d, distance, 0.1d);
	}
	
	@Test
	public void testGetCoordinates() {
		Coordinates coordinates = serviceHelper.getCoordinates("Wildenbruchstraße 2, 40545 Düsseldorf");
		assertNotNull(coordinates);
		assertEquals(51.22759989999999, coordinates.getLatitude(), 0.0001);
		assertEquals(6.75993840000001, coordinates.getLongitude(), 0.0001);
	}
}
