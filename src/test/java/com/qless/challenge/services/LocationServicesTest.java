package com.qless.challenge.services;

import com.qless.challenge.models.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.qless.challenge.models.Location.LocationBuilder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LocationServicesTest {

    private List<Location> locations;

    @Mock
    DataSource dataSource;

    @InjectMocks
    LocationServices locationServices;

    @Before
    public void setUp() throws Exception {
        locations = new ArrayList<>();
        Mockito.when(dataSource.getLocations()).thenReturn(locations);
    }

    @Test
    public void whenNoFilterSpecified_shouldReturnAllLocations() {
        Location location1 = createLocation(null);
        Location location2 = createLocation(null);

        locations.addAll(Arrays.asList(location1, location2));
        List<Location> locations = locationServices.findLocations(null);
        assertEquals(2, locations.size());
    }

    @Test
    public void whenSearchTextFilterSpecified_givenLocationsThatContainsText_shouldReturnLocationThatContainsSearchTextIgnoringCaseSensitive() {
        Location location1 = createLocation("Mission");
        Location location2 = createLocation("Olathe");
        Location location3 = createLocation("Austin Animal mIstery Center");
        Location location4 = createLocation("Milary");
        Location location5 = createLocation("Austin AnimalmIss Center");


        locations.addAll(Arrays.asList(location1, location2, location3, location4, location5));
        List<Location> locations = locationServices.findLocations("Mis");
        assertEquals(3, locations.size());
        assertTrue(locations.contains(location1));
        assertTrue(locations.contains(location3));
        assertTrue(locations.contains(location5));
    }

    @Test
    public void whenSearchTextFilterSpecified_givenNoLocationWithSpecifiedText_shouldReturnEmptyList() {
        Location location1 = createLocation("Austin Animal Center");
        Location location2 = createLocation("Olathe");


        locations.addAll(Arrays.asList(location1, location2));
        List<Location> locations = locationServices.findLocations("miss");
        assertTrue(locations.isEmpty());
    }


    private Location createLocation(String searchText) {
        return new LocationBuilder().withName(searchText).createLocation();
    }
}