package by.epam.bikesharing.logic.email;

import by.epam.bikesharing.resource.MessageManager;
import by.epam.bikesharing.resource.StringManager;
import com.sun.mail.smtp.SMTPTransport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private static final Logger LOGGER = LogManager.getLogger("DaoLog");
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String USER_NAME = "deegreazz@gmail.com";
    private static final String PASSWORD = "3110clas";
    private String recipientAddress;
    private String subject;
    private String body;

    public EmailSender(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public void sendVerificationCode(String code) {
        subject = MessageManager.getProperty("email.verification.subject");
        body = formVerificationHtml(code);
        try {
            sendMail();
        } catch (MessagingException e) {
            LOGGER.error("Registration notification was not sent", e);
        }
        LOGGER.info(String.format("Notification was sent to %s", recipientAddress));
    }

    private String formVerificationHtml(String code) {
        return String.format("<h2>%s<h2><h1>%s<h1>", MessageManager.getProperty("email.verification.body"), code);
    }

    private void sendMail() throws MessagingException {
        LOGGER.debug("Start sending mail");
        Session session = Session.getInstance(getProperties());
        Message message = getMessage(session);
        SMTPTransport smtp = (SMTPTransport) session.getTransport("smtp");
        smtp.connect(SMTP_SERVER, USER_NAME, PASSWORD);
        smtp.sendMessage(message, message.getAllRecipients());
        LOGGER.debug(String.format("Response: %s", smtp.getLastServerResponse()));
        smtp.close();
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        return properties;
    }

    private Message getMessage(Session session) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USER_NAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));
        message.setSubject(subject);
        message.setContent(body, "text/html" );
        return message;
    }
}