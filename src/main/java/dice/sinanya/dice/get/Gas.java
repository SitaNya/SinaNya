package dice.sinanya.dice.get;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesGas.gasList;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;

/**
 * 获取煤气灯特质
 *
 * @author SitaNya
 */
public class Gas {
    private EntityTypeMessages entityTypeMessages;

    public Gas(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void get() {
        int random7 = random(0, gasList.size() - 1);
        int random20 = random(0, 19);

        sender(entityTypeMessages, gasList.get(random7).get(random20));
    }
}
