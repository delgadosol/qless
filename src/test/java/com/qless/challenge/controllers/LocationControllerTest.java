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
import java.util.Optional;

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
        List<Location> locations = Arrays.asList(location1, location2);
        Mockito.when(locationServices.findLocations(null, null, null, null, null, 10)).thenReturn(locations);
        List<Location> locationsResult = locationController.getLocations(null, null, null, null, null, 10);
        assertEquals(locations, locationsResult);
    }

    @Test(expected = LocationNotFoundException.class)
    public void givenLocationIsEmpty_shouldThrowException_whenCallGetLocationWithGid() {
        Mockito.when(locationServices.findLocation("gid")).thenReturn(Optional.empty());
        locationController.getLocation("gid");
    }

    @Test
    public void givenAOptionalLocation_shouldReturnLocation_whenCallGetLocationWithGid() {
        Location location = mock(Location.class);
        Mockito.when(locationServices.findLocation("gid")).thenReturn(Optional.of(location));
        Location locationResult = locationController.getLocation("gid");
        assertEquals(location, locationResult);
    }
}