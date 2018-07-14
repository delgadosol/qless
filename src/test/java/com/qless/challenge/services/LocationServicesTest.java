package com.qless.challenge.services;

import com.qless.challenge.models.Location;
import com.qless.challenge.models.Merchant;
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
    private DataSource dataSource;

    @InjectMocks
    private LocationServices locationServices;

    @Before
    public void setUp() throws Exception {
        locations = new ArrayList<>();
        Mockito.when(dataSource.getLocations()).thenReturn(locations);
    }

    @Test
    public void whenNoFilterSpecified_shouldReturnAllLocations() {
        Location location1 = createLocation(null, createMerchant("merchant Name"));
        Location location2 = createLocation(null, createMerchant("merchant Name"));

        locations.addAll(Arrays.asList(location1, location2));
        List<Location> locations = locationServices.findLocations(null);
        assertEquals(2, locations.size());
    }

    @Test
    public void whenSearchTextFilterSpecified_givenLocationsThatContainsText_shouldReturnLocationThatContainsSearchTextIgnoringCaseSensitive() {
        Location location1 = createLocation("Mission", createMerchant("merchant Name"));
        Location location2 = createLocation("Olathe", createMerchant("merchant Name"));
        Location location3 = createLocation("Austin Animal mIstery Center", createMerchant("merchant Name"));
        Location location4 = createLocation("Milary", createMerchant("merchant Name"));
        Location location5 = createLocation("Austin AnimalmIss Center", createMerchant("merchant Name"));


        locations.addAll(Arrays.asList(location1, location2, location3, location4, location5));
        List<Location> locations = locationServices.findLocations("Mis");
        assertEquals(3, locations.size());
        assertTrue(locations.contains(location1));
        assertTrue(locations.contains(location3));
        assertTrue(locations.contains(location5));
    }

    @Test
    public void whenSearchTextFilterSpecified_givenNoLocationWithSpecifiedText_shouldReturnEmptyList() {
        Location location1 = createLocation("Austin Animal Center", createMerchant("merchant Name"));
        Location location2 = createLocation("Olathe", createMerchant("merchant Name"));


        locations.addAll(Arrays.asList(location1, location2));
        List<Location> locations = locationServices.findLocations("miss");
        assertTrue(locations.isEmpty());
    }

    @Test
    public void whenSearchTextFilterSpecified_givenLocationsThatContainsTextInMerchantName_shouldReturnLocationThatContainsSearchTextIgnoringCaseSensitive() {
        Location location1 = createLocation("Mission", createMerchant("Johnson County"));
        Location location2 = createLocation("Olathe", createMerchant("merchant Name"));


        locations.addAll(Arrays.asList(location1, location2));
        List<Location> locations = locationServices.findLocations("Coun");
        assertEquals(1, locations.size());
        assertTrue(locations.contains(location1));
    }

    @Test
    public void whenSearchTextFilterSpecified_givenLocationsThatContainsTextInMerchantNameOrLocationName_shouldReturnLocationThatContainsSearchTextIgnoringCaseSensitive() {
        Location location1 = createLocation("Mission", createMerchant("Johnson County"));
        Location location2 = createLocation("Johnson", createMerchant("merchant Name"));


        locations.addAll(Arrays.asList(location1, location2));
        List<Location> locations = locationServices.findLocations("son");
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location1));
        assertTrue(locations.contains(location2));
    }

    private Location createLocation(String name, Merchant merchant) {
        return new LocationBuilder()
                .withName(name)
                .withMerchant(merchant)
                .createLocation();
    }

    private Merchant createMerchant(String name){
        return Merchant.newBuilder()
                .withName(name)
                .build();
    }
}