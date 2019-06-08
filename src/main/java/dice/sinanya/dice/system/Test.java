package dice.sinanya.dice.system;

import dice.sinanya.entity.EntityTypeMessages;

public class Test {

    private EntityTypeMessages entityTypeMessages;

    public Test(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void get() {
        entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg("450609203", "test");
    }
}
