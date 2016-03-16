package net.chuiev.selcommittee.services.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailThread extends Thread {
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
            message.setFrom(new InternetAddress("alexchuev090893@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
        } catch (AddressException e) {
            //System.err.print("Некорректный адрес:" + sendToEmail + " " + e);
        } catch (MessagingException e) {
            //System.err.print("Ошибка формирования сообщения" + e);
        }
    }

    public void run() {
        init();
        try {
            // sending mail message
            Transport.send(message);
        } catch (MessagingException e) {
            //System.err.print("Ошибка при отправлении сообщения" + e);
        }
    }

    public static void main(String[] args) {
        MailThread mailThread = new MailThread("<alexeychuev@mail.ru>", "bla", "text", new Properties());
        mailThread.start();
    }
}