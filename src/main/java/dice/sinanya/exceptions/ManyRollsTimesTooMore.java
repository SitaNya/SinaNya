package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.Sender.sender;

public class ManyRollsTimesTooMore extends Exception {

    public ManyRollsTimesTooMore(EntityTypeMessages entityTypeMessages) {
        super("骰点次数过多");
        sender(entityTypeMessages, strValueErr);
    }
}
