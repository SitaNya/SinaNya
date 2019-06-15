package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.GetMessagesSystem.messagesSystem;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * 玩家属性输入错误报错
 *
 * @author SitaNya
 */
public class PlayerSetException extends Exception {

    public PlayerSetException(EntityTypeMessages entityTypeMessages) {
        super(messagesSystem.get("setPropFormat"));
        sender(entityTypeMessages, messagesSystem.get("setPropFormat"));
    }

}
