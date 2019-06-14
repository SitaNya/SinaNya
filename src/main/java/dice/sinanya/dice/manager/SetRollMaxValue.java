package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesSystem.ROLL_MAX_VALUE;
import static dice.sinanya.system.MessagesTag.TAG_SET_ROLL_MAX_VALUE;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.log.Sender.sender;

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
        String tag = TAG_SET_ROLL_MAX_VALUE;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        if (isNumeric(msg)) {
            ROLL_MAX_VALUE.put(entityTypeMessages.getFromGroup(), Integer.parseInt(msg));
            sender(entityTypeMessages, "当前群的默认骰改为" + msg);
        } else {
            sender(entityTypeMessages, "输入数值有误");
        }
    }
}
