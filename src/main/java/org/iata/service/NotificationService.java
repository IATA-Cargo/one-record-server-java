package org.iata.service;

import org.iata.api.model.Notification;

public interface NotificationService {
  void handleNotification(Notification notification);
}
