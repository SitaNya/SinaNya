package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.GetMessagesSystem.messagesSystem;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * SanCheck表达式输入错误报错
 *
 * @author SitaNya
 */
public class SanCheckSetException extends Exception {

    public SanCheckSetException(EntityTypeMessages entityTypeMessages) {
        super(messagesSystem.get("sanCheck"));
        sender(entityTypeMessages, messagesSystem.get("sanCheck"));
    }

}
