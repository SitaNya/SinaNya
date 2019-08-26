package dice.sinanya.entity;

/**
 * @author SitaNya
 * 日期: 2019-08-26
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class EntityDeckList {
    String command;
    String name;
    String author;
    int version;
    String desc;

    public EntityDeckList() {
    }

    public EntityDeckList(String command, String name, String author, int version, String desc) {
        this.command = command;
        this.name = name;
        this.author = author;
        this.version = version;
        this.desc = desc;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
