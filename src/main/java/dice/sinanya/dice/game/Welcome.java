package dice.sinanya.dice.game;

import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.entity.EntityWelcome;

import static dice.sinanya.system.MessagesTag.TAG_WELCOME;
import static dice.sinanya.system.MessagesWelcome.welcomes;
import static dice.sinanya.tools.getinfo.Welcome.insertWelcome;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

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
        long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
        if (msg.equals("close")) {
            String text = "";
            if (welcomes.containsKey(groupId)) {
                text = welcomes.get(groupId).getText();
            }
            EntityWelcome entityWelcome = new EntityWelcome(false, text);
            insertWelcome(Long.parseLong(entityTypeMessages.getFromGroup()), entityWelcome);
            welcomes.put(groupId, entityWelcome);
            sender(entityTypeMessages, "已关闭，将保留原欢迎词设置");
        } else if (msg.equals("open")) {
            String text = "";
            if (welcomes.containsKey(groupId)) {
                text = welcomes.get(groupId).getText();
            }
            EntityWelcome entityWelcome = new EntityWelcome(true, text);
            insertWelcome(Long.parseLong(entityTypeMessages.getFromGroup()), entityWelcome);
            welcomes.put(groupId, entityWelcome);
            sender(entityTypeMessages, "已开启，原有的欢迎词已保留并恢复");
        } else if (welcomes.get(groupId).isEnable()) {
            EntityWelcome entityWelcome = new EntityWelcome(true, msg);
            insertWelcome(groupId, entityWelcome);
            sender(entityTypeMessages, "已录入,置为开启");
        }
    }
}
