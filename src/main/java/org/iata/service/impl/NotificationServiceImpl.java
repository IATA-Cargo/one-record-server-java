package org.iata.service.impl;

import org.iata.api.model.Notification;
import org.iata.repository.NotificationsRepository;
import org.iata.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class NotificationServiceImpl implements NotificationService {

  private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

  private final NotificationsRepository notificationsRepository;

  @Inject
  public NotificationServiceImpl(NotificationsRepository notificationsRepository) {
    this.notificationsRepository = notificationsRepository;
  }

  @Override
  public void handleNotification(Notification notification) {
    LOG.info("Saving notification...");
    notification.setId(notification.getLogisticsObject().getId());
    notificationsRepository.save(notification);

    LOG.info("Retrieve content of the logistics object from publisher...");
    String response = retrieveLogisticsObjectContentFromPublisher(notification);
    LOG.info("Content of the logistics object: " + response);
  }

  private String retrieveLogisticsObjectContentFromPublisher(Notification notification) {
    URL url;
    HttpURLConnection connection = null;
    try {
      url = new URL(notification.getLogisticsObject().getId());
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Content-Type", "application/ld+json");
      connection.setUseCaches(true);

      // Get Response
      InputStream is = connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\r');
      }
      rd.close();
      return response.toString();
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("Error occurred while retrieving logistics object from publisher " + e.getMessage());
      return null;
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
  }

}
