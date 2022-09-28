package org.iata.repository;

import org.iata.cargo.model.LogisticsObject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogisticsObjectsRepository extends MongoRepository<LogisticsObject, String> {
    List<LogisticsObject> findByCompanyIdentifier(String companyIdentifier);

    List<LogisticsObject> findByCompanyIdentifierAndTypes(String companyIdentifier, String logisticsObjectType);

    List<LogisticsObject> findByIdStartsWith(String id);
}