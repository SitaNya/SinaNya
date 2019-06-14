package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * 玩家属性输入错误报错
 *
 * @author SitaNya
 */
public class PlayerSetException extends Exception {

    public PlayerSetException(EntityTypeMessages entityTypeMessages) {
        super("很抱歉，参数输入错误。应该类似.st角色名-力量50体质60");
        sender(entityTypeMessages, strValueErr);
    }

}
