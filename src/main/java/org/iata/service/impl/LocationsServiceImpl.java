package org.iata.service.impl;

import org.iata.cargo.model.Location;
import org.iata.cargo.model.LogisticsObject;
import org.iata.repository.LocationRepository;
import org.iata.service.LocationsService;
import org.iata.util.Utils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;


@Service
public class LocationsServiceImpl implements LocationsService {

    private final LocationRepository locationRepository;

    @Inject
    public LocationsServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    @Override
    public void addLocation(Location location, String companyIdentifier) {
        if (location.getId() == null || !Utils.isValidURL(location.getId())) {
            String locationId = "";
            if (location.getCode() != null) {
                locationId += location.getCode() + " ";
            }
            if (location.getName() != null) {
                locationId += location.getName() + " ";
            }
            if (0 == locationId.length()) {
                locationId = Utils.generateUuid();
            }

            String locationUri = companyIdentifier + "/locations/" + locationId;

            location.setId(Utils.toKebabCase(locationUri));
        }
        locationRepository.save(location);
    }

    @Override
    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(String id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        locationRepository.deleteById(id);
    }
}
