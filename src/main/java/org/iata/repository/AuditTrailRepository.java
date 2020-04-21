package org.iata.repository;

import org.iata.api.model.AuditTrail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuditTrailRepository extends MongoRepository<AuditTrail, String> {
  List<AuditTrail> findByLogisticsObjectRef(String loId);
}