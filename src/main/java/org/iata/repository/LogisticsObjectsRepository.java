package org.iata.repository;

import org.iata.model.LogisticsObject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogisticsObjectsRepository extends MongoRepository<LogisticsObject, String> {
  List<LogisticsObject> findByCompanyId(String companyId);
}