package com.qless.challenge.services;

import com.qless.challenge.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LocationServices {

    @Autowired
    DataSource dataSource;

    public List<Location> findLocations(String searchText) {
        List<Location> result = dataSource.getLocations();
        if (Objects.nonNull(searchText)) {
            result = result.stream()
                    .filter(location -> location.getName().toLowerCase().contains(searchText.toLowerCase())
                            || location.getMerchant().getName().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return result;
    }
}
