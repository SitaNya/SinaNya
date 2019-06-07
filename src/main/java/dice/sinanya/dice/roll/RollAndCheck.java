package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityRollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.CheckResultLevel;

import static dice.sinanya.system.MessagesTag.tagRA;
import static dice.sinanya.system.MessagesTag.tagRC;
import static dice.sinanya.tools.History.changeHistory;
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
        String tag = tagRA;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), false);
        String stringBuilder = entityRollAndCheck.getNick() +
                "进行鉴定: D100=" + entityRollAndCheck.getRandom() + "/" + entityRollAndCheck.getSkill() +
                checkResultLevel.getResultLevel();
        changeHistory(entityTypeMessages.getFromQQ()).update(checkResultLevel.getLevelResult());
        sender(entityTypeMessages, stringBuilder);
    }

    public void rc() {
        String tag = tagRC;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);
        CheckResultLevel checkResultLevel = new CheckResultLevel(entityRollAndCheck.getRandom(), entityRollAndCheck.getSkill(), true);
        String stringBuilder = entityRollAndCheck.getNick() +
                "进行鉴定: D100=" + entityRollAndCheck.getRandom() + "/" + entityRollAndCheck.getSkill() +
                checkResultLevel.getResultLevel();
        changeHistory(entityTypeMessages.getFromQQ()).update(checkResultLevel.getLevelResult());
        sender(entityTypeMessages, stringBuilder);
    }

}
