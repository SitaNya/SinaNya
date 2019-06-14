package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesSystem.ROLL_MAX_VALUE;
import static dice.sinanya.system.MessagesTag.tagSetRollMaxValue;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.Sender.sender;

/**
 * 管理最大默认骰
 *
 * @author SitaNya
 */
public class SetRollMaxValue {
    private EntityTypeMessages entityTypeMessages;

    public SetRollMaxValue(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void set() {
        String tag = tagSetRollMaxValue;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        if (isNumeric(msg)) {
            ROLL_MAX_VALUE.put(entityTypeMessages.getFromGroup(), Integer.parseInt(msg));
            sender(entityTypeMessages, "当前群的默认骰改为" + msg);
        } else {
            sender(entityTypeMessages, "输入数值有误");
        }
    }
}
