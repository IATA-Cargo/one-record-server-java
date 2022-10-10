package org.iata.service.impl;

import cz.cvut.kbss.jsonld.JsonLd;
import org.iata.api.model.Notification;
import org.iata.cargo.model.LogisticsObject;
import org.iata.model.enums.EventType;
import org.iata.repository.NotificationsRepository;
import org.iata.service.NotificationService;
import org.iata.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private ObjectMapper jacksonObjectMapper;

    @Inject
    public NotificationServiceImpl(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    @Override
    public void handleNotification(Notification notification, String companyIdentifier) {
        LOG.info("Saving notification...");
        notification.setId(companyIdentifier + "/notifications/notification-" + Utils.generateUuid());
        notificationsRepository.save(notification);

        LOG.info("Retrieve content of the logistics object from publisher..." + notification.getLogisticsObject().getId());
        String response = Utils.retrieveContentFromURL(notification.getLogisticsObject().getId());
        LOG.info("Content of the logistics object: " + response);
    }

    @Override
    public void sendNotification(String callbackUrl, EventType eventType, String logisticsObjectType, String logisticsObjectId) {
        //prepare Notification object
        Notification notification = new Notification();
        LogisticsObject lo = new LogisticsObject();
        lo.setId(logisticsObjectId);
        notification.setLogisticsObject(lo);
        notification.setEventType(eventType.getEventType());
        notification.setTopic(logisticsObjectType);

        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL(callbackUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", JsonLd.MEDIA_TYPE);
            con.setRequestProperty("Accept", JsonLd.MEDIA_TYPE);
            con.setDoOutput(true);
            con.setUseCaches(true);
            String jsonInputString = jacksonObjectMapper.writeValueAsString(notification);
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
            LOG.info("Sent {} notification to {}", eventType.getEventType(), callbackUrl);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Error occurred while sending notification to {}: {}", callbackUrl, e.getMessage());
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }


}
