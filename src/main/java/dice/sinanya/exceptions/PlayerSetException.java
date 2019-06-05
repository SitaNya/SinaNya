package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author zhangxiaozhou
 */
public class PlayerSetException extends Exception {

    public PlayerSetException(EntityTypeMessages entityTypeMessages) {
        super("很抱歉，参数输入错误");
        sender(entityTypeMessages, strValueErr);
    }

}
