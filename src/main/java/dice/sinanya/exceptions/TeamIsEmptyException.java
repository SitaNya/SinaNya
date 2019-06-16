package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.GetMessagesSystem.messagesSystem;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * 多重骰点格式错误
 *
 * @author SitaNya
 */
public class TeamIsEmptyException extends Exception {
    public TeamIsEmptyException(EntityTypeMessages entityTypeMessages) {
        super(messagesSystem.get("teamIsEmpty"));
        sender(entityTypeMessages, messagesSystem.get("teamIsEmpty"));
    }
}
