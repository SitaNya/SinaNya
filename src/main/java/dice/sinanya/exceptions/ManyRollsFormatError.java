package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.Sender.sender;

public class ManyRollsFormatError extends Exception{
    public ManyRollsFormatError(EntityTypeMessages entityTypeMessages) {
        super("请按照\".ral 值 次数\"的格式输入");
        sender(entityTypeMessages, strValueErr);
    }
}
