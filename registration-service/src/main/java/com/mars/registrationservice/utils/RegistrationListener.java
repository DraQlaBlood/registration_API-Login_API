package com.mars.registrationservice.utils;


import com.mars.registrationservice.dao.UserDao;
import com.mars.registrationservice.model.User;
import com.mars.registrationservice.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageSource messages;

    @Autowired
    public JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        user.setVerificationToken(new VerificationToken(token,new VerificationToken().calculateExpiryDate(60*24)));
        user.setEnabled(false);
        userDao.save(user);

        try {
            String recipientAddress = user.getEmail();
            String subject = "Registration Confirmation";
            String confirmationUrl
                    = event.getAppUrl() + "/registration_Confirm?token="+token;
            String message = "Your account have been successfully created .\n" +
                    " Please click on the link to activate your account " +
                    "\n";
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText(message +" rn "+"http://localhost:8080"+confirmationUrl);
            mailSender.send(email);
        }catch (MailException e){
            e.printStackTrace();
        }

    }

    /*private void resendtoken(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        user.setVerificationToken(new VerificationToken(token,new VerificationToken().calculateExpiryDate(60*24)));
        user.setEnabled(false);
        userDao.save(user);

        try {
            String recipientAddress = user.getEmail();
            String subject = "Registration Confirmation";
            String confirmationUrl
                    = event.getAppUrl() + "/registration_Confirm?token="+token;
            String message = "You have received this email because your validation token has expired .\n" +
                    " Please click on the link to activate your account " +
                    "\n";
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText(message +" rn "+"http://localhost:8080"+confirmationUrl);
            mailSender.send(email);
        }catch (MailException e){
            e.printStackTrace();
        }

    }*/
}
