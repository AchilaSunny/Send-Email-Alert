package com.achila.sendemailalert.service;

import com.achila.sendemailalert.SendEmailAlertApplication;
import com.achila.sendemailalert.model.AlertList;
import com.achila.sendemailalert.model.AlertMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import org.springframework.stereotype.Service;


@Service
public class AlertListenerService {

    private  static final Logger log = LoggerFactory.getLogger(AlertListenerService.class);

    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = SendEmailAlertApplication.DEFAULT_PARSING_QUEUE)
    public void consumeAlertMessage(AlertList message) {
        log.info("Received Alert message is: {}", message.getAlertMessageList());
        notificationService.sendNotification(message);

    }

}
