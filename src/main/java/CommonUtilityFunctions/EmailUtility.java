package CommonUtilityFunctions;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.io.File;

public class EmailUtility {

    public static void sendEmailWithAttachment(String toEmail, String subject, String body, String reportPath) {

        final String fromEmail = ConfigReader.initProperties().getProperty("senderEmail");
        final String password = ConfigReader.initProperties().getProperty("senderAppPassword");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            // Body part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Attachment part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(reportPath));

            // Combine
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email sent successfully with report.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
