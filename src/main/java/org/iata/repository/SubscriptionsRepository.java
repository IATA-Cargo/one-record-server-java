package org.iata.repository;

import org.iata.api.model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionsRepository extends MongoRepository<Subscription, String> {


}