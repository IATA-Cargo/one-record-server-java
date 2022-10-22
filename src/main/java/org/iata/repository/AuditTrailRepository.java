package org.iata.repository;

import org.iata.api.model.AuditTrail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AuditTrailRepository extends MongoRepository<AuditTrail, String> {
  @Query(value = "{ 'logisticsObjectRef.logisticsObjectId' : ?0}")
  List<AuditTrail> findByLogisticsObjectRef(String loId);
}