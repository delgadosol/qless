package com.qless.challenge.services;

import com.qless.challenge.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServices {

    @Autowired
    DataSource dataSource;

    public List<Location> findLocations(){
        return dataSource.getLocations();
    }
}
