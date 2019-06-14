package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * 多重骰点格式错误
 *
 * @author SitaNya
 */
public class ManyRollsFormatException extends Exception {
    public ManyRollsFormatException(EntityTypeMessages entityTypeMessages) {
        super("请按照\".ral 值 次数\"的格式输入");
        sender(entityTypeMessages, strValueErr);
    }
}
