package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.tools.Kp.setKpGroup;
import static dice.sinanya.tools.Sender.sender;

public class Kp {
    private EntityTypeMessages entityTypeMessages;

    public Kp(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void set() {
        setKpGroup(entityTypeMessages, entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, "已设置此群为您的带团群");
    }
}
