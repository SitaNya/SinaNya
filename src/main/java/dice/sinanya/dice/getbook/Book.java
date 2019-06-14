package dice.sinanya.dice.getbook;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesBook.*;
import static dice.sinanya.tools.Sender.sender;

/**
 * 获取资料集
 *
 * @author SitaNya
 */
public class Book {

    private EntityTypeMessages entityTypeMessages;

    public Book(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void card() {
        sender(entityTypeMessages, strGetBookHumanMailContent);
    }

    public void rp() {
        sender(entityTypeMessages, strGetBookRPMailContent);
    }

    public void kp() {
        sender(entityTypeMessages, strGetBookKPMailContent);
    }

    public void make() {
        sender(entityTypeMessages, strGetBookMakeCardMailContent);
    }

}
