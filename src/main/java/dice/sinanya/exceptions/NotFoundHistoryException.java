package dice.sinanya.exceptions;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.Sender.sender;

public class NotFoundHistoryException extends Exception {

    public NotFoundHistoryException(EntityTypeMessages entityTypeMessages) {
        super("您从未骰点过");
        sender(entityTypeMessages, strValueErr);
    }

}