package dice.sinanya.dice.getbook;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.getinfo.GetMessagesSystem.messagesSystem;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取资料集，都是存储在百度网盘里的链接，回复语句而已
 */
public class Book {

    private EntityTypeMessages entityTypeMessages;

    public Book(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 车卡指南
     */
    public void make() {
        sender(entityTypeMessages, messagesSystem.get("bookMake"));
    }

    /**
     * 人物卡Excel
     */
    public void card() {
        sender(entityTypeMessages, messagesSystem.get("bookCard"));
    }

    /**
     * 规则书
     */
    public void kp() {
        sender(entityTypeMessages, messagesSystem.get("bookKp"));
    }

    /**
     * 人物扮演自问
     */
    public void rp() {
        sender(entityTypeMessages, messagesSystem.get("bookRP"));
    }

}
