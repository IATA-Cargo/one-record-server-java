package org.iata.service.impl;

import cz.cvut.kbss.jsonld.JsonLd;
import org.iata.api.model.Notification;
import org.iata.cargo.model.LogisticsObject;
import org.iata.model.enums.EventType;
import org.iata.repository.NotificationsRepository;
import org.iata.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final NotificationsRepository notificationsRepository;

    @Autowired
    private Jackson2ObjectMapperBuilder mapperBuilder;

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

    @Override
    public void sendNotification(String callbackUrl, EventType eventType, String logisticsObjectType, String logisticsObjectId) {
        //prepare Notification object
        Notification notification = new Notification();
        notification.setLogisticsObject(new LogisticsObject().setId(logisticsObjectId));
        notification.setEventType(eventType.getEventType());
        notification.setTopic(logisticsObjectType);

        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL(callbackUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept", JsonLd.MEDIA_TYPE);
            con.setDoOutput(true);
            con.setUseCaches(true);
            String jsonInputString = mapperBuilder.build().writeValueAsString(notification);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Error occurred while sending notification to {}: {}", callbackUrl, e.getMessage());
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        LOG.info("Sent {} notification to {}", eventType.getEventType(), callbackUrl);
    }

    private String retrieveLogisticsObjectContentFromPublisher(Notification notification) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(notification.getLogisticsObject().getId());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", JsonLd.MEDIA_TYPE);
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
