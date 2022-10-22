package org.iata.service;

import org.iata.cargo.model.Location;
import org.iata.cargo.model.LogisticsObject;

import java.util.List;

public interface LocationsService {

    void addLocation(Location location, String companyIdentifier);

    Location findById(String id);

    List<Location> getLocations();

    void deleteById(String id);


}
