package com.qless.challenge.controllers;

import com.qless.challenge.models.Location;
import com.qless.challenge.services.LocationServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/merchant/location")
public class LocationController {

    @Autowired
    LocationServices locationServices;

    @GetMapping
    @ApiOperation(value = "Returns merchant locations that match search criteria",
            notes = "This method identifies valid locations that match ALL provided search criteria. Simpler queries work best for experimentation and early development, whereas multi-parameter queries permit strict optimization of client data usage.")
    public List<Location> getLocations(
            @ApiParam(value = "Identify and sort merchants or locations for which the search text offers approximate matches.")
            @RequestParam(required = false) String searchText
            , @ApiParam(value = "Limits results to a region with its center specified by the given GPS longitude")
            @RequestParam(required = false) Double longitude
            , @ApiParam(value = "Limits results to a region with its center specified by the given GPS latitude.")
            @RequestParam(required = false) Double latitude
            ,@ApiParam(value = "Radius of the search area in meters.")
            @RequestParam(required = false, defaultValue = "50000") Double searchRadius
            ,@ApiParam(value = "Limits/queries locations by the provided global identifier.")
            @RequestParam(required = false) List<String> gid
            ,@ApiParam(value = "Limits/queries locations by the provided global identifier.")
            @RequestParam(required = false, defaultValue = "10") Integer maximumResults) {
        return locationServices.findLocations(searchText, longitude, latitude, searchRadius, gid, maximumResults);
    }
}
