package org.iata.repository;

import org.iata.api.model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubscriptionsRepository extends MongoRepository<Subscription, String> {
  List<Subscription> findByMyCompanyIdentifier(String companyId);
}