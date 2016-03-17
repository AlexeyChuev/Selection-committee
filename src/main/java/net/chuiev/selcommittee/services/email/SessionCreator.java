package net.chuiev.selcommittee.services.email;

import org.apache.log4j.Logger;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 * Class produce sessionProperties for connection to smtp.gmail.com
 * and send emails from account in gmail.
 *
 * @author Oleksii Chuiev
 *
 */
public class SessionCreator {
    private static final Logger LOG = Logger.getLogger(SessionCreator.class);

    private Properties sessionProperties;

    /**
     * Creating params in constructor
     */
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
        //sessionProperties.setProperty("mail.smtp.quitwait", "true");
        sessionProperties.put("mail.smtp.starttls.enable", "true");
        LOG.debug("Params for sending email were added");
    }

    /**
     * Returns Session for new connection to smtp
     *
     * @return Session object this validated username and password
     */
    public Session createSession() {
        return Session.getDefaultInstance(sessionProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        LOG.debug("Creating session for sending email");
                        return new PasswordAuthentication("addmission.office.test@gmail.com", "addmission");
                    }
                });
    }
}