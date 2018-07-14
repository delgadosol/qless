package com.qless.challenge.controllers;

import com.qless.challenge.error.LocationNotFoundException;
import com.qless.challenge.models.Location;
import com.qless.challenge.services.LocationServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class LocationControllerTest {

    @Mock
    private LocationServices locationServices;

    @InjectMocks
    private LocationController locationController;

    @Test(expected = LocationNotFoundException.class)
    public void givenEmptyLocationFromService_shouldThrowException() {
        Mockito.when(locationServices.findLocations(null, null, null, null, null, 10)).thenReturn(Collections.emptyList());
        locationController.getLocations(null, null, null, null, null, 10);
    }

    @Test
    public void givenAListOfLocationFromService_shouldReturnSameList() {
        Location location1 = mock(Location.class);
        Location location2 = mock(Location.class);
        List<Location> locationsResult = Arrays.asList(location1, location2);
        Mockito.when(locationServices.findLocations(null, null, null, null, null, 10)).thenReturn(locationsResult);
        List<Location> locations = locationController.getLocations(null, null, null, null, null, 10);
        assertEquals(locationsResult, locations);
    }
}