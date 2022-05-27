package JavaMailer;

import java.util.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

public class JavaMail {

    public static void main1(String[] args){
        MailReader.main(args);
    }

    public static void main(String[] args) {
        String recipient = "joshua@joshua.com";
        String author = "notjoshua@notjoshua.com";
        String mailHost = "mail.joshua.org";
 
        // Create properties, get Session
        Properties properties = new Properties();

        properties.put("mail.smtp.host", mailHost);

        properties.put("mail.debug", "true");
        Session session = Session.getInstance(properties);

        try {
            // New message
            Message message = new MimeMessage(session);
 
            // Message characteristics
            message.setFrom(new InternetAddress(author));
            InternetAddress[] address = {new InternetAddress(recipient)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject("Hello");
            message.setSentDate(new Date());

            // Content of Message
            message.setText("Yo.");

            System.out.println(message.getHeader("Message-ID")[0]);
            Transport.send(message);
            System.out.println(message.getHeader("Message-ID")[0]);
            
        }
        
        catch (MessagingException msgexception) {
            msgexception.printStackTrace();
        }
    }
}