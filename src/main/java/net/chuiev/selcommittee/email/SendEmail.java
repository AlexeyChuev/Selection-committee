package net.chuiev.selcommittee.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Created by Alex on 3/7/2016.
 */
public class SendEmail {

    private String username;
    private String password;
    private Properties props;

    private static SendEmail tlsSender = new SendEmail("chuevalexey93@gmail.com", "pass");

    /*public static void main(String[] args){
        tlsSender.send("This is Subject", "TLS: This is text!", "chuevalexey93@gmail.com", "alexchuev090893@gmail.com");
    }*/

    public SendEmail(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void send(String subject, String text, String fromEmail, String toEmail){
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //sender
            message.setFrom(new InternetAddress(username));
            //receiver
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //Header of mail
            message.setSubject(subject);
            //Content
            message.setText(text);

            //end mail
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }





    public static void main(String[] args) {

        // SUBSTITUTE YOUR EMAIL ADDRESSES HERE!!!
        String to = "alexchuev090893@gmail.com";
        String from = "chuevalexey93@gmail.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        String host = "smtp.gmail.com";

        // Create properties, get Session
        Properties props = new Properties();

        // If using static Transport.send(),
        // need to specify which host to send it to
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        // To see what is going on behind the scene
        props.put("mail.debug", "true");
        //props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props);

        try {
            // Instantiatee a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(from, "pass"));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Test E-Mail through Java");
            msg.setSentDate(new Date());

            // Set message content
            msg.setText("This is a test of sending a " +
                    "plain text e-mail through Java.\n" +
                    "Here is line 2.");

            //Send the message
            Transport.send(msg);
        }
        catch (MessagingException mex) {
            // Prints all nested (chained) exceptions as well
            mex.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
