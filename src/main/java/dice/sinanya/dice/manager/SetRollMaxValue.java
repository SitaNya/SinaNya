package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesRollMaxValue.ROLL_MAX_VALUE;
import static dice.sinanya.system.MessagesTag.TAG_SET_ROLL_MAX_VALUE;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.DefaultMaxRolls.setMaxRolls;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 管理最大默认骰，目前未做入库
 */
public class SetRollMaxValue {
    private EntityTypeMessages entityTypeMessages;

    public SetRollMaxValue(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 设置本群中.r的最大默认骰
     */
    public void set() {
        String tag = TAG_SET_ROLL_MAX_VALUE;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));
        if (isNumeric(msg)) {
            ROLL_MAX_VALUE.put(entityTypeMessages.getFromGroup(), Integer.parseInt(msg));
            setMaxRolls(entityTypeMessages.getFromGroup(), Integer.parseInt(msg));
            sender(entityTypeMessages, "当前群的默认骰改为" + msg);
        } else {
            sender(entityTypeMessages, "输入数值有误");
        }
    }
}
