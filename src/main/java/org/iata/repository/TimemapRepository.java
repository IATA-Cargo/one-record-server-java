package org.iata.repository;

import org.iata.api.model.Timemap;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimemapRepository extends MongoRepository<Timemap, String> {

}