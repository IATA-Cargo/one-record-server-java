package org.iata.service;

import org.iata.model.Notification;

public interface NotificationService {
  void handleNotification(Notification notification);
}
