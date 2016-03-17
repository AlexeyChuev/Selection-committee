package net.chuiev.selcommittee.services.email;

import org.apache.log4j.Logger;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Implementation of Runnable interface which send emails to enrollees.
 *
 * @author Oleksii Chuiev
 *
 */
public class MailThread implements Runnable {
    private static final Logger LOG = Logger.getLogger(MailThread.class);

    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;

    public MailThread(String sendToEmail,
                      String mailSubject, String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
    }

    /**
     * Create mailSession and put params for message
     */
    private void init() {
        // mail session object
        Session mailSession = (new SessionCreator()).createSession();
        mailSession.setDebug(true);
        // creating mail session object
        message = new MimeMessage(mailSession);
        try {
            // upload parametrs to mail message object
            message.setSubject(mailSubject);
            message.setContent(mailText, "text/html");
            message.setFrom(new InternetAddress("addmission.office.test@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
            LOG.trace("Message params were set");
        } catch (AddressException e) {
            LOG.error(e);
        } catch (MessagingException e) {
            LOG.error(e);
        }
    }

    /**
     * Which method invokes, then runnable starts.
     */
    public void run() {
        LOG.debug("Run email sending");
        init();
        try {
            // sending mail message
            Transport.send(message);
        } catch (MessagingException e) {
            LOG.error(e);
        }
    }
}