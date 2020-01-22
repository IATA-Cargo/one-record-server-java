package org.iata.repository;

import org.iata.api.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationsRepository extends MongoRepository<Notification, String> {
}