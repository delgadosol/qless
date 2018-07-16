package com.qless.challenge.services;

import com.qless.challenge.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

@Service
public class LocationServices {

    @Autowired
    DataSource dataSource;

    public List<Location> findLocations(String searchText, Double longitude, Double latitude, Double searchRadius
            , List<String> gid, Integer maximumResults) {
        Stream<Location> result = dataSource.getLocations().stream();
        if (nonNull(searchText)) {
            result = result
                    .filter(location -> location.getName().toLowerCase().contains(searchText.toLowerCase())
                            || location.getMerchant().getName().toLowerCase().contains(searchText.toLowerCase()));
        }
        if (nonNull(longitude) || nonNull(latitude)) {
            //TODO: calculate distance
        }
        if (nonNull(gid)) {
            result = result
                    .filter(location -> gid.contains(location.getSource().getGlobalId()));
        }

        return result
                .limit(maximumResults)
                .collect(Collectors.toList());
    }

    public Optional<Location> findLocation(String gid) {
        return dataSource.getLocations()
                .stream()
                .filter(location -> location.getSource().getGlobalId().equalsIgnoreCase(gid))
                .findFirst();
    }
}
