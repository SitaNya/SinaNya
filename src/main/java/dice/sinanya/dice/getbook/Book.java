package dice.sinanya.dice.getbook;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
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
        sender(entityTypeMessages, entitySystemProperties.getBookMake());
    }

    /**
     * 人物卡Excel
     */
    public void card() {
        sender(entityTypeMessages, entitySystemProperties.getBookCard());
    }

    /**
     * 规则书
     */
    public void kp() {
        sender(entityTypeMessages, entitySystemProperties.getBookKp());
    }

    /**
     * 人物扮演自问
     */
    public void rp() {
        sender(entityTypeMessages, entitySystemProperties.getBookRp());
    }

}
