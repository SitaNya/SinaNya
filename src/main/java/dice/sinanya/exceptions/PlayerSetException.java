package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author zhangxiaozhou
 */
public class PlayerSetException extends Exception {

    public PlayerSetException(EntityTypeMessages entityTypeMessages) {
        super("很抱歉，参数输入错误。应该类似.st角色名-力量50体质60");
        sender(entityTypeMessages, strValueErr);
    }

}
