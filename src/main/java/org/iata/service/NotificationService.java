package org.iata.service;

import org.iata.api.model.Notification;
import org.iata.model.enums.EventType;

public interface NotificationService {
  void handleNotification(Notification notification);

    void sendNotification(String callbackUrl, EventType eventType, String logisticsObjectType, String logisticsObjectId);
}
