package dice.sinanya.dice.system;

import dice.sinanya.entity.EntityTypeMessages;

/**
 * 测试类，并不被任何逻辑调用
 *
 * @author SitaNya
 */
public class Test {

    private EntityTypeMessages entityTypeMessages;

    public Test(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void get() {
        entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg("450609203", "test");
    }
}
