package dice.sinanya.Dice.Roll;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.CheckResultLevel;

import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;

public class RollAndCheck {

    private EntityTypeMessages entityTypeMessages;

    public RollAndCheck(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void ra() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".ra");

        int random = random(1, 100);
        if (isNumeric(msg)) {
            int skill = Integer.parseInt(msg);
            String stringBuilder = getNickName(entityTypeMessages) +
                    "进行鉴定: D100=" + random + "/" + skill +
                    new CheckResultLevel(random, skill, false).getResultLevel();
            sender(entityTypeMessages, stringBuilder);
        }
    }

}
