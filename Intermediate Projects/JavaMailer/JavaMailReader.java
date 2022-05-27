package JavaMailer;

import java.io.*;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import java.util.*;
import javax.mail.*;
import javax.mail.Flags.Flag;

public class JavaMailReader {
     Folder inbox;

     public JavaMailReader() {
         // Properties for Mail
         Properties props = System.getProperties();
         props.setProperty("mail.store.protocol", "imaps");

         try {
             //  Create Messaging Session
             Session msgSession = Session.getDefaultInstance(props, null);
             Store msgStore = msgSession.getMsgStore("imaps");
             msgStore.connect("mail.joshdevyn.org", "Joshua", "Sims");

             //  Mention folder
             inbox = msgStore.getFolder("Inbox");
             System.out.println("Messages Unread: " + inbox.getUnreadMessageCount());

             // Open inbox
             inbox.open(Folder.READ_ONLY);

             // Unread messages
             Message messages[] = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));

             // Fetch Profile
             FetchProfile fetchProfile = new FetchProfile();
             fetchProfile.add(FetchProfile.Item.ENVELOPE);
             fetchProfile.add(FetchProfile.Item.CONTENT_INFO);
             inbox.fetch(messages, fetchProfile);

             try {
                 printAllMsg(messages);
                 inbox.close(true);
                 msgStore.close();
             }
             catch (Exception ex) {
                 System.out.println("Read Mail Exception");
                 ex.printStackTrace();
             }
         }
         catch (NoSuchProviderException e) {
             e.printStackTrace();
             System.exit(1);
         }
         catch (MessagingException e) {
             e.printStackTrace();
             System.exit(2);
         }
 }

    public void printAllMsg(Message[] messages) throws Exception {
        for (int i = 0; i < messages.length; i++) {
            System.out.println("Message number " + (i + 1) + ":");
            printEnvelope(messages[i]);
        }
    }

    // Envelope printing
    public void printEnvelope(Message message) throws Exception {

        Address[] addresses;

        // Message Author
        if ((addresses = message.getFrom()) != null) {
            for (int j = 0; j < addresses.length; j++) {
                System.out.println("FROM: " + addresses[j].toString());
            }
        }

        // Message Recipient
        if ((addresses = message.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < addresses.length; j++) {
                System.out.println("TO: " + addresses[j].toString());
            }
        }

        String subject = message.getSubject();
        Date receivedDate = message.getReceivedDate();
        System.out.println("Subject : " + subject);
        System.out.println("Received Date : " + receivedDate.toString());
        System.out.println("Content : " + message.getContent());
        dumpPart(((MimeMessage)message).getRawInputStream());
        getContent(message);
    }

    public void getContent(Message msg) {

        try {
            String contentType = msg.getContentType();
            System.out.println("Content Type : " + contentType);
            Multipart multiPart = (Multipart) ((MimeMessage)msg).getContent();

            int count = multiPart.getCount();
            for (int i = 0; i < count; i++) {
                dumpPart(multiPart.getBodyPart(i).getInputStream());
            }
        }

        catch (Exception e) {
            System.out.println("Exception arise at get Content");
            e.printStackTrace();
        }
    }

    public void dumpPart(InputStream inputStream) throws Exception {

        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }

        int count;

        System.out.println("Message : ");

        while ((count = inputStream.read()) != -1) {
            System.out.write(count);
        }
    }

    public static void main(String args[]) {
        new JavaMailReader();
    }
}
