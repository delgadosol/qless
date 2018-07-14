package com.qless.challenge.services;

import com.qless.challenge.models.Location;
import com.qless.challenge.models.Merchant;
import com.qless.challenge.models.NetworkSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        Location location1 = createLocation(null, createMerchant("merchant Name"), null);
        Location location2 = createLocation(null, createMerchant("merchant Name"), null);

        locations.addAll(Arrays.asList(location1, location2));
        List<Location> locations = locationServices.findLocations(null, null, null, null, null);
        assertEquals(2, locations.size());
    }

    @Test
    public void whenSearchTextFilterSpecified_givenLocationsThatContainsText_shouldReturnLocationThatContainsSearchTextIgnoringCaseSensitive() {
        Location location1 = createLocation("Mission", createMerchant("merchant Name"), null);
        Location location2 = createLocation("Olathe", createMerchant("merchant Name"), null);
        Location location3 = createLocation("Austin Animal mIstery Center", createMerchant("merchant Name"), null);
        Location location4 = createLocation("Milary", createMerchant("merchant Name"), null);
        Location location5 = createLocation("Austin AnimalmIss Center", createMerchant("merchant Name"), null);


        locations.addAll(Arrays.asList(location1, location2, location3, location4, location5));
        List<Location> locations = locationServices.findLocations("Mis", null, null, null, null);
        assertEquals(3, locations.size());
        assertTrue(locations.contains(location1));
        assertTrue(locations.contains(location3));
        assertTrue(locations.contains(location5));
    }

    @Test
    public void whenSearchTextFilterSpecified_givenNoLocationWithSpecifiedText_shouldReturnEmptyList() {
        Location location1 = createLocation("Austin Animal Center", createMerchant("merchant Name"), null);
        Location location2 = createLocation("Olathe", createMerchant("merchant Name"), null);


        locations.addAll(Arrays.asList(location1, location2));
        List<Location> locations = locationServices.findLocations("miss", null, null, null, null);
        assertTrue(locations.isEmpty());
    }

    @Test
    public void whenSearchTextFilterSpecified_givenLocationsThatContainsTextInMerchantName_shouldReturnLocationThatContainsSearchTextIgnoringCaseSensitive() {
        Location location1 = createLocation("Mission", createMerchant("Johnson County"), null);
        Location location2 = createLocation("Olathe", createMerchant("merchant Name"), null);


        locations.addAll(Arrays.asList(location1, location2));
        List<Location> locations = locationServices.findLocations("Coun", null, null, null, null);
        assertEquals(1, locations.size());
        assertTrue(locations.contains(location1));
    }

    @Test
    public void whenSearchTextFilterSpecified_givenLocationsThatContainsTextInMerchantNameOrLocationName_shouldReturnLocationThatContainsSearchTextIgnoringCaseSensitive() {
        Location location1 = createLocation("Mission", createMerchant("Johnson County"), null);
        Location location2 = createLocation("Johnson", createMerchant("merchant Name"), null);


        locations.addAll(Arrays.asList(location1, location2));
        List<Location> locations = locationServices.findLocations("son", null, null, null, null);
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location1));
        assertTrue(locations.contains(location2));
    }

    @Test
    public void whenGidSpecified_givenLocationsThatContainsGid_shouldReturnLocationsWithGid() {
        NetworkSource networkSource1 = NetworkSource.newBuilder().withGlobalId("123").build();
        NetworkSource networkSource2 = NetworkSource.newBuilder().withGlobalId("234").build();
        Location location1 = createLocation(null, null, networkSource1);
        Location location2 = createLocation(null, null, networkSource2);

        locations.addAll(Arrays.asList(location1, location2));
        List<Location> locations = locationServices.findLocations(null, null, null, null, Collections.singletonList("123"));
        assertEquals(1, locations.size());
        assertTrue(locations.contains(location1));
    }

    private Location createLocation(String name, Merchant merchant, NetworkSource networkSource) {
        return Location.newBuilder()
                .withName(name)
                .withMerchant(merchant)
                .withSource(networkSource)
                .build();
    }

    private Merchant createMerchant(String name) {
        return Merchant.newBuilder()
                .withName(name)
                .build();
    }
}