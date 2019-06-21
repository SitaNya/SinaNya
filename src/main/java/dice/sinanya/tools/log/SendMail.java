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

import static dice.sinanya.tools.getinfo.GetMessagesSystem.messagesSystem;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 邮件发送类
 */
public class SendMail {
    private static final Logger log = LogManager.getLogger(SendMail.class);

    /**
     * 重新编码为GBK2312（中文），可以解决大部分乱码问题
     *
     * @param text 输入的字符串
     * @return 重新编码后的字符串
     */
    private static String toChinese(String text) {
        try {
            text = MimeUtility.encodeText(new String(text.getBytes(StandardCharsets.UTF_8), "GB2312"), "GB2312", "B");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return text;
    }

    /**
     * 设定邮件必要的信息
     *
     * @param to      给谁
     * @param groupId 日志所在群号
     * @param logName 日志名
     */
    public static void sendMail(String to, String groupId, String logName) {
        MailBean mb = new MailBean();
        mb.setHost("smtp.qq.com");
        // 设置SMTP主机(163)，若用126，则设为：smtp.126.com
        mb.setUsername(messagesSystem.get("mailUserName"));
        // 设置发件人邮箱的用户名
        mb.setPassword(messagesSystem.get("mailPassword"));
        // 设置发件人邮箱的密码，需将*号改成正确的密码
        mb.setFrom(messagesSystem.get("mailUserName"));
        // 设置发件人的邮箱
        mb.setTo(to + "@qq.com");
        // 设置收件人的邮箱
        mb.setSubject(logName);
        // 设置邮件的主题
        mb.setContent("在群: " + groupId + " 中的log日志: " + logName + "\n其中docx为染色文件用word打开，无后缀为文本文件用txt打开，收到邮件烦请回复\n遇到问题请加群162279609进行反馈");
        // 设置邮件的正文

//        这里取相对路径"bin/../saveLogs/${groupId}/${logName}"
        mb.attachFile("../saveLogs/" + groupId + "/" + logName);
        mb.attachFile("../saveLogs/" + groupId + "/" + logName + ".docx");

        SendMail sm = new SendMail();
        log.info("正在发送邮件...");
        // 发送邮件
        if (sm.sendMail(mb)) {
            log.info("发送成功!");
        } else {
            log.info("发送失败!");
        }
    }

    /**
     * 设定邮件必要的信息
     *
     */
    public static void sendMail() {
        MailBean mb = new MailBean();
        mb.setHost("smtp.qq.com");
        // 设置SMTP主机(163)，若用126，则设为：smtp.126.com
        mb.setUsername(messagesSystem.get("mailUserName"));
        // 设置发件人邮箱的用户名
        mb.setPassword(messagesSystem.get("mailPassword"));
        // 设置发件人邮箱的密码，需将*号改成正确的密码
        mb.setFrom(messagesSystem.get("mailUserName"));
        // 设置发件人的邮箱
        mb.setTo(messagesSystem.get("masterMail"));
        // 设置收件人的邮箱
        mb.setSubject("骰娘宕机");
        // 设置邮件的主题
        mb.setContent("您的骰娘: "+messagesSystem.get("loginQQ")+" 失去响应，请查看");
        // 设置邮件的正文

        SendMail sm = new SendMail();
        log.info("正在发送邮件...");
        // 发送邮件
        if (sm.sendMail(mb)) {
            log.info("发送成功!");
        } else {
            log.info("发送失败!");
        }
    }

    /**
     * 实际发送邮件
     *
     * @param mb 传入的邮件对象
     * @return 是否发送成功
     */
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
                        log.error(e.getMessage(), e);
                    }
                    mp.addBodyPart(mbpFile);
                }
                log.error("添加成功");
            }

            msg.setContent(mp);
            msg.setSentDate(new Date());
            Transport.send(msg);

        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

}