package com.qless.challenge.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qless.challenge.models.Location;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class JsonDataSource implements DataSource {

    @Value("${json.data.file}")
    private String jsonDataFile;

    private List<Location> locations;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        Resource resource = new ClassPathResource(jsonDataFile);
        InputStream jsonFile = resource.getInputStream();
        locations = jsonMapper.readValue(jsonFile, new TypeReference<List<Location>>() {
        });
    }

    @Override
    public List<Location> getLocations() {
        return locations;
    }
}
