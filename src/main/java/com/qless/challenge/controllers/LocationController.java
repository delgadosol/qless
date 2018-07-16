package com.qless.challenge.controllers;

import com.qless.challenge.error.ApiError;
import com.qless.challenge.error.LocationNotFoundException;
import com.qless.challenge.models.Location;
import com.qless.challenge.services.LocationServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            , @ApiParam(value = "Radius of the search area in meters.")
            @RequestParam(required = false, defaultValue = "50000") Double searchRadius
            , @ApiParam(value = "Limits/queries locations by the provided global identifier.")
            @RequestParam(required = false) List<String> gid
            , @ApiParam(value = "The maximum number of locations to return for a single query.")
            @RequestParam(required = false, defaultValue = "10") Integer maximumResults) {
        List<Location> locations = locationServices.findLocations(searchText, longitude, latitude, searchRadius, gid, maximumResults);
        if (locations.isEmpty()) {
            throw new LocationNotFoundException();
        }
        return locations;
    }

    @GetMapping("/{location_gid}")
    @ApiOperation(value = "Returns a uniquely-identified merchant location",
            notes = "This method returns a single merchant location that matches a unique global identifier. The global identifier is returned in a NetworkSource entity with a ‘location’ type, usually in response to a location search query.")
    public Location getLocation(
            @PathVariable(value = "location_gid") String location_gid
    ) {
        return locationServices.findLocation(location_gid)
                .orElseThrow(LocationNotFoundException::new);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiError> handleNumberFormatException(NumberFormatException ex) {
        return new ResponseEntity<>(new ApiError("Invalid argument"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ApiError> noResultException() {
        return new ResponseEntity<>(new ApiError("No records found."), HttpStatus.NOT_FOUND);
    }

}
