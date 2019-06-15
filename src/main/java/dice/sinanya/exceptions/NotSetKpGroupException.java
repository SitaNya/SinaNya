package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.GetMessagesSystem.messagesSystem;
import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * 未设定kp管理群报错
 *
 * @author SitaNya
 */
public class NotSetKpGroupException extends Exception {

    public NotSetKpGroupException(EntityTypeMessages entityTypeMessages) {
        super(messagesSystem.get("needKpGroup"));
        sender(entityTypeMessages, messagesSystem.get("needKpGroup"));
    }

}