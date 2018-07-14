package com.qless.challenge.services;

import com.qless.challenge.models.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonDataSource implements DataSource {

    @Override
    public List<Location> getLocations() {
        return null;
    }
}
