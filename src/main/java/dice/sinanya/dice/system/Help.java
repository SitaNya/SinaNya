package dice.sinanya.dice.system;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesHelp.*;
import static dice.sinanya.tools.Sender.sender;

public class Help {
    private EntityTypeMessages entityTypeMessages;

    public Help(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void normal() {
        sender(entityTypeMessages, normalHelp.toString());
    }

    public void make() {
        sender(entityTypeMessages, makeHelp.toString());
    }

    public void group() {
        sender(entityTypeMessages, groupHelp.toString());
    }
}
