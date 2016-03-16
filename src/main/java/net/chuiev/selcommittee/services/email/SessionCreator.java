package net.chuiev.selcommittee.services.email;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SessionCreator {
    private Properties sessionProperties;

    public SessionCreator() {
        // upload mail server parametrs in mail session properties
        sessionProperties = new Properties();
        sessionProperties.setProperty("mail.transport.protocol", "smtp");
        sessionProperties.setProperty("mail.smtp.host", "smtp.gmail.com");
        sessionProperties.put("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.port", "465");
        sessionProperties.put("mail.smtp.socketFactory.port", "465");
        sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //sessionProperties.put("mail.smtp.socketFactory.fallback", "false");
        //sessionProperties.setProperty("mail.smtp.quitwait", "false");
        sessionProperties.put("mail.smtp.starttls.enable", "true");
    }

    public Session createSession() {
        return Session.getDefaultInstance(sessionProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("alexchuev090893@gmail.com", "090893alexchuev");
                    }
                });
    }
}