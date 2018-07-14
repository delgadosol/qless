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

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LocationServicesTest {

    List<Location> locations;

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
    public void whenNoFilterSpecifiedShouldReturnAllLocations() {
        locations.addAll(Arrays.asList(new Location(), new Location()));
        List<Location> locations = locationServices.findLocations();
        assertEquals(locations.size(), 2);
    }
}