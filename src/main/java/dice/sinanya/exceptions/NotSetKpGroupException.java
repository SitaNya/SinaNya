package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.Sender.sender;

/**
 * 未设定kp管理群报错
 *
 * @author SitaNya
 */
public class NotSetKpGroupException extends Exception {

    public NotSetKpGroupException(EntityTypeMessages entityTypeMessages) {
        super("您未设置KP管理群");
        sender(entityTypeMessages, strValueErr);
    }

}