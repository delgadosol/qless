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
            @RequestParam(required = false) String searchText) {
        return locationServices.findLocations(searchText);
    }
}
