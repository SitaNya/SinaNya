package dice.sinanya.dice.get;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesRule.rulesText;
import static dice.sinanya.system.MessagesTag.TAG_RULES;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-08-07
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class Rules {
    private EntityTypeMessages entityTypeMessages;

    public Rules(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 获取煤气灯特质并发送
     */
    public void get() {
        String tag = TAG_RULES;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        sender(entityTypeMessages, rulesText.getOrDefault(msg, "不存在此规则解释:\t" +msg));
    }
}
