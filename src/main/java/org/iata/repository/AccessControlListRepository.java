package org.iata.repository;

import org.iata.model.AccessControlList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccessControlListRepository extends MongoRepository<AccessControlList, String> {
  List<AccessControlList> findByLogisticsObjectRef(String loId);
}