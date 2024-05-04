package service;



import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SmtpEmail {
    //setting proprepreties
    Properties properties = System.getProperties();
    //account
    private InternetAddress fromEmail;
    private String password;
    // session after authentication
    Session session;
    // the message to create;
    MimeMessage mimeMessage;

    // Constructor to set the email details

    public SmtpEmail() throws MessagingException {
        // storing the fromEmail as InternetAddress not String
        this.fromEmail = new InternetAddress("mariem.jrad@esprit.tn");

        this.configServerSmtp("mariem.jrad@esprit.tn", "$bounouma20$");

        createNewMessage_andAssignSender();

    }

    // config server and smtp
    private void configServerSmtp(String fromEmail, String password) throws MessagingException {
        // SMTP server configuration for Gmail
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        // Set SMTP server properties
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Create a new session with an authenticator object that provides the username and password
        session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

    }

    private void createNewMessage_andAssignSender() throws MessagingException {

        if (session != null) {
            // Create a new message
            this.mimeMessage = new MimeMessage(session);
            // Set the sender and recipient addresses, subject, and message body
            mimeMessage.setFrom(fromEmail);
        } else {
            System.err.println("session is null , null pointer exception occured @ createNewMessage_andAssignSender() .");
        }

    }

    public void set_emailsToSentTo(String[] emailsArray) throws AddressException, MessagingException {

        // get the addresses to send to
        InternetAddress[] toAddresses = new InternetAddress[emailsArray.length];
        for (int i = 0; i < emailsArray.length; i++) {
            toAddresses[i] = new InternetAddress(emailsArray[i]);
        }

        if (mimeMessage != null) {
            mimeMessage.setRecipients(Message.RecipientType.TO, toAddresses);
        } else {
            System.err.println("mimeMessage is null , null pointer exception occured @ set_emailsToSentTo()");
        }

    }

    // Method to send the email

    public void setEmailSubject_andBody( String subject, String textMessageVersion, String htmlMessageVersion ) throws MessagingException {

        if (mimeMessage != null) {

            mimeMessage.setSubject(subject);

            // Create the message body
            MimeBodyPart textPart = new MimeBodyPart();
            if(textMessageVersion == null) {
                textMessageVersion = "";
            }
            textPart.setText(textMessageVersion);

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlMessageVersion, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(htmlPart);

            mimeMessage.setContent(multipart);

        } else {
            System.err.println("mimeMessage is null , null pointer exception occured @ setEmailSubject_andBody()");
        }

    }
    public void sendEmail() throws MessagingException {

        if (mimeMessage != null && session != null ) // Send the message

            Transport.send(mimeMessage);
        else {
            System.err.println("mimeMessage is null , null pointer exception dude .");
        }
    }



    public void sendEmail(String[] emailsTo, String subject, String textMessageVersion, String htmlMessageVersion) throws MessagingException {
        this.set_emailsToSentTo(emailsTo);
        this.setEmailSubject_andBody(subject, textMessageVersion, htmlMessageVersion);
        this.sendEmail();
    }
}
