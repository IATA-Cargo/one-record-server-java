package org.iata.repository;

import org.iata.cargo.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LocationRepository extends MongoRepository<Location, String> {

    Optional<Location> findByCode(String code);

}