package net.chuiev.selcommittee.services.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Алексей on 3/7/2016.
 */

public class Send2 {
    private String username;
    private String password;
    private Properties props;

    public Send2(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
    }

    public void send(String subject, String text, String toEmail) {
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //from who
            message.setFrom(new InternetAddress("alexchuev090893@gmail.com"));
            //to who
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //theme
            message.setSubject(subject);
            //text
            message.setText(text);
            //send email
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Send2 sender = new Send2("alexchuev090893@gmail.com", "090893alexchuev");
        sender.send("This is Subject", "TLS: This is text!", "<alexeychuev@mail.ru>");
    }
}
