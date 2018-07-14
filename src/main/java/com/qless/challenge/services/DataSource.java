package com.qless.challenge.services;

import com.qless.challenge.models.Location;

import java.util.List;

public interface DataSource {
    List<Location> getLocations();
}
