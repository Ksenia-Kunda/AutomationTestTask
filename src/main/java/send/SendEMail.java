package send;

import helper.DateManager;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Ksenia on 09.12.2016.
 */
public class SendEMail {

    private DateManager dateManager;
    private static final String ENCODING = "UTF-8";
    private static final String MESSAGE_TEXT = "Test";
    private static final String STMP_HOST = "smtp.yandex.ru";
    private static final String IMAP_HOST = "imap.yandex.ru";
    private static final String SMTP_PORT = "25";
    private static final String FOLDER_NAME = "Отправленные";
    private static final String IMAP_PROTOCOL = "imap";
    private Message message;
    private Session session;
    private static String subject;

    public SendEMail() {
        dateManager = new DateManager();
        subject = dateManager.toString();
    }

    public void sendMessage(String loginAccFrom, String passwordAccFrom, String loginAccTo) {
        try {
            Authenticator authenticator = new MyAuthenticator(loginAccFrom, passwordAccFrom);
            Properties props = System.getProperties();
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.host", STMP_HOST);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.imap.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.mime.charset", ENCODING);
            session = Session.getInstance(props, authenticator);
            message = new MimeMessage(session);

            message.setFrom(new InternetAddress(loginAccFrom));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(loginAccTo));
            message.setSubject(subject);
            message.setText(MESSAGE_TEXT);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        saveSentMessage(session, message, loginAccFrom, passwordAccFrom);
    }

    public void saveSentMessage(Session session, Message message, String loginAccFrom, String passwordAccFrom) {
        try {
            Store store = session.getStore(IMAP_PROTOCOL);
            store.connect(IMAP_HOST, loginAccFrom, passwordAccFrom);
            Folder folder = store.getFolder(FOLDER_NAME);
            if (!folder.exists()) {
                folder.open(Folder.READ_WRITE);
            }
            message = new MimeMessage((MimeMessage) message);
            message.setFlag(Flags.Flag.SEEN, true);
            folder.appendMessages(new Message[]{message});
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String getSubject() {
        return subject;
    }
}

