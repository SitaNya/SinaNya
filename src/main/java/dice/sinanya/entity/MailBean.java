package dice.sinanya.entity;

import java.util.Vector;

/**
 * 邮箱发件对象
 *
 * @author SitaNya
 */
public class MailBean {

    /**
     * @param to    收件人
     * @param from  发件人
     * @param host  SMTP主机
     * @param username  发件人的用户名
     * @param password  发件人的密码
     * @param subject  邮件主题
     * @param content  邮件正文
     * @param file  多个附件
     * @param filename  附件的文件名
     */
    private String to;
    private String from;
    private String host;
    private String username;
    private String password;
    private String subject;
    private String content;
    private Vector<String> file;
    private String filename;

    public MailBean() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public Vector<String> getFile() {
        return file;
    }

    public void attachFile(String fileName) {
        if (file == null) {
            file = new Vector<>();
        }
        file.addElement(fileName);
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
