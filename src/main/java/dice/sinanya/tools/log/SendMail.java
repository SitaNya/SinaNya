package dice.sinanya.tools.log;

import dice.sinanya.entity.MailBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import static dice.sinanya.system.MessagesSystem.*;

public class SendMail {
    private static final Logger Log = LogManager.getLogger(SendMail.class);

    private String toChinese(String text) {
        try {
            text = MimeUtility.encodeText(new String(text.getBytes(StandardCharsets.UTF_8), "GB2312"), "GB2312", "B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    private boolean sendMail(MailBean mb) {
        String host = mb.getHost();
        final String username = mb.getUsername();
        final String password = mb.getPassword();
        String from = mb.getFrom();
        String to = mb.getTo();
        String subject = mb.getSubject();
        String content = mb.getContent();
        String fileName;
        Vector<String> file = mb.getFile();

        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        // 设置SMTP的主机
        props.put("mail.smtp.auth", "true");
        // 需要经过验证

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(toChinese(subject));

            Multipart mp = new MimeMultipart();
            MimeBodyPart mbpContent = new MimeBodyPart();
            mbpContent.setText(content);
            mp.addBodyPart(mbpContent);

            /* 往邮件中添加附件 */
            if (file != null) {
                Enumeration<String> efile = file.elements();
                while (efile.hasMoreElements()) {
                    MimeBodyPart mbpFile = new MimeBodyPart();
                    fileName = efile.nextElement();
                    FileDataSource fds = new FileDataSource(fileName);
                    mbpFile.setDataHandler(new DataHandler(fds));
                    try {
                        mbpFile.setFileName(MimeUtility.encodeWord(fds.getName()));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    mp.addBodyPart(mbpFile);
                }
                Log.info("添加成功");
            }

            msg.setContent(mp);
            msg.setSentDate(new Date());
            Transport.send(msg);

        } catch (MessagingException me) {
            me.printStackTrace();
            return false;
        }
        return true;
    }

    public static void sendMail(String to, String groupId, String logName) {
        MailBean mb = new MailBean();
        mb.setHost("smtp.qq.com");
        // 设置SMTP主机(163)，若用126，则设为：smtp.126.com
        mb.setUsername("2730902267@qq.com");
        // 设置发件人邮箱的用户名
        mb.setPassword("kktjwuakdafbdcej");
        // 设置发件人邮箱的密码，需将*号改成正确的密码
        mb.setFrom("2730902267@qq.com");
        // 设置发件人的邮箱
        mb.setTo(to + "@qq.com");
        // 设置收件人的邮箱
        mb.setSubject(logName);
        // 设置邮件的主题
        mb.setContent("日志邮件测试");
        // 设置邮件的正文

        if (OSX_MODEL) {
            mb.attachFile("/Users/SitaNya/Desktop/" + groupId + "/" + logName);
            mb.attachFile("/Users/SitaNya/Desktop/" + groupId + "/" + logName + ".docx");
        } else if (WIN_MODEL) {
            mb.attachFile("C:/Files/" + groupId + "/" + logName);
            mb.attachFile("C:/Files/" + groupId + "/" + logName + ".docx");
        } else if (LINUX_MODEL) {
            mb.attachFile("/tmp/" + groupId + "/" + logName);
            mb.attachFile("/tmp/" + groupId + "/" + logName + ".docx");
        }

        SendMail sm = new SendMail();
        Log.info("正在发送邮件...");
        // 发送邮件
        if (sm.sendMail(mb)) {
            Log.info("发送成功!");
        } else {
            Log.info("发送失败!");
        }
    }

}