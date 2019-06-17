package dice.sinanya.dice.system;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesHelp.*;
import static dice.sinanya.system.MessagesSystem.STR_BOT_INFO;
import static dice.sinanya.tools.log.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 帮助信息
 */
public class Help {
    private EntityTypeMessages entityTypeMessages;

    public Help(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 骰点帮助
     */
    public void normal() {
        sender(entityTypeMessages, NORMAL_HELP.toString());
    }

    /**
     * 车卡帮助
     */
    public void make() {
        sender(entityTypeMessages, MAKE_HELP.toString());
    }

    /**
     * 带团帮助
     */
    public void group() {
        sender(entityTypeMessages, GROUP_HELP.toString());
    }

    /**
     * 获取资料帮助
     */
    public void book() {
        sender(entityTypeMessages, BOOK_HELP.toString());
    }

    /**
     * dnd帮助
     */
    public void dnd() {
        sender(entityTypeMessages, DND_HELP.toString());
    }

    /**
     * 机器人信息
     */
    public void info() {
        sender(entityTypeMessages, STR_BOT_INFO.toString());
    }
}
