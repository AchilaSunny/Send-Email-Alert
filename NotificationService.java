package com.achila.sendemailalert.service;

import com.achila.sendemailalert.domain.User;
import com.achila.sendemailalert.model.AlertList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    public JavaMailSender javaMailSender;
    private  static final Logger log = LoggerFactory.getLogger(AlertListenerService.class);

    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    private AlertList alertList;

    public  void sendNotification(AlertList alertList)throws MailException {

        User user = new User();
        user.setFirstName("Achila");
        user.setLastName("Sunny");
        user.setEmailAddress("achilats@gmail.com");
        try{

            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setTo(user.getEmailAddress());
            mail.setFrom("achilasunny@gmail.com");
            mail.setSubject("New alerts are available");
            mail.setText(String.valueOf(alertList.getAlertMessageList()));
            System.out.println("mail body is: " + mail.getText() );
            System.out.println("-------------------------Mail sent----------------------");
            log.info("Alert message body is: {}", mail.getText());
            javaMailSender.send(mail);

            }catch(MailException e) {
            log.info("Error sending email:" +e.getMessage());
        }
    }
}
