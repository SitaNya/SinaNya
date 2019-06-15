package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.GetMessagesSystem.messagesSystem;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * 多重骰点次数过多报错
 *
 * @author SitaNya
 */
public class ManyRollsTimesTooMoreException extends Exception {

    public ManyRollsTimesTooMoreException(EntityTypeMessages entityTypeMessages) {
        super(messagesSystem.get("diceTimesTooBig"));
        sender(entityTypeMessages, messagesSystem.get("diceTimesTooBig"));
    }
}
