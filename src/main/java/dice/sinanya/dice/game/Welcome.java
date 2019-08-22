package dice.sinanya.dice.game;

import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.entity.EntityWelcome;

import static dice.sinanya.system.MessagesTag.TAG_WELCOME;
import static dice.sinanya.system.MessagesWelcome.welcomes;
import static dice.sinanya.tools.getinfo.Welcome.insertWelcome;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 今日人品类，其实不是很想做……
 */
public class Welcome implements MakeNickToSender {
    private EntityTypeMessages entityTypeMessages;

    public Welcome(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void set() {
        String tag = TAG_WELCOME;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));
        if (msg.equals("close")) {
            if (welcomes.containsKey(Long.parseLong(entityTypeMessages.getFromGroup()))) {
                EntityWelcome entityWelcome = welcomes.get(Long.parseLong(entityTypeMessages.getFromGroup()));
                entityWelcome.setEnable(false);
                insertWelcome(Long.parseLong(entityTypeMessages.getFromGroup()), entityWelcome);
            }
        }
    }
}
