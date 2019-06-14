package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.Sender.sender;

/**
 * 多重骰点次数过多报错
 *
 * @author SitaNya
 */
public class ManyRollsTimesTooMoreException extends Exception {

    public ManyRollsTimesTooMoreException(EntityTypeMessages entityTypeMessages) {
        super("骰点次数过多");
        sender(entityTypeMessages, strValueErr);
    }
}
