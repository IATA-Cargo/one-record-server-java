package org.iata.repository;

import org.iata.api.model.DelegationRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DelegationRequestRepository extends MongoRepository<DelegationRequest, String> {
}