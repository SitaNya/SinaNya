package dice.sinanya.dice.getbook;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.GetMessagesSystem.messagesSystem;
import static dice.sinanya.tools.log.Sender.sender;

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
        sender(entityTypeMessages, messagesSystem.get("bookCard"));
    }

    public void rp() {
        sender(entityTypeMessages, messagesSystem.get("bookRP"));
    }

    public void kp() {
        sender(entityTypeMessages, messagesSystem.get("bookKp"));
    }

    public void make() {
        sender(entityTypeMessages, messagesSystem.get("bookMake"));
    }

}
