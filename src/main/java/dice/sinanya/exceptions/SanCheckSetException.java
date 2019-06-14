package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.Sender.sender;

/**
 * SanCheck表达式输入错误报错
 *
 * @author SitaNya
 */
public class SanCheckSetException extends Exception {

    public SanCheckSetException(EntityTypeMessages entityTypeMessages) {
        super("很抱歉，参数输入错误。应该类似1/1d3|1d6/1d3|0/1这样的表达式");
        sender(entityTypeMessages, strValueErr);
    }

}
