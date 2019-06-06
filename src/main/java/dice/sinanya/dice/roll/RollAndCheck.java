package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityRollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.CheckResultLevel;

import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.MakeRollCheckResult.makeResult;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author zhangxiaozhou
 */
public class RollAndCheck {

    private EntityTypeMessages entityTypeMessages;

    public RollAndCheck(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void ra() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".ra");
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);
        String stringBuilder = entityRollAndCheck.getNick() +
                "进行鉴定: D100=" + entityRollAndCheck.getRandom() + "/" + entityRollAndCheck.getSkill() +
                new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), false).getResultLevel();
        sender(entityTypeMessages, stringBuilder);
    }

    public void rc() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".rc");

        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);
        String stringBuilder = entityRollAndCheck.getNick() +
                "进行鉴定: D100=" + entityRollAndCheck.getRandom() + "/" + entityRollAndCheck.getSkill() +
                new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), true).getResultLevel();
        sender(entityTypeMessages, stringBuilder);
    }

}
