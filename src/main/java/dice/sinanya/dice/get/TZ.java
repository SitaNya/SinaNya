package dice.sinanya.dice.get;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesTZ.tzList;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;

/**
 * 获取自定义特质
 *
 * @author zhangxiaozhou
 */
public class TZ {
    private EntityTypeMessages entityTypeMessages;

    public TZ(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void get() {
        int random8 = random(0, tzList.size()-1);
        int random20 = random(0, 19);

        sender(entityTypeMessages, tzList.get(random8).get(random20));
    }
}
