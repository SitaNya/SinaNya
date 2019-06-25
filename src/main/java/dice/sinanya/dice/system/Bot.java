package dice.sinanya.dice.system;

import dice.sinanya.dice.manager.imal.AtQq;
import dice.sinanya.entity.EntityGroupCensus;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;

import static dice.sinanya.db.system.SelectBot.selectBot;
import static dice.sinanya.system.MessagesSystem.ENTITY_LOGINQQ_INFO;
import static dice.sinanya.system.MessagesSystem.STR_BOT_VERSIONS;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.MESSAGES_SYSTEM;
import static dice.sinanya.tools.getinfo.SwitchBot.*;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 机器人控制类，如开关退群等
 */
public class Bot implements AtQq {

    private EntityTypeMessages entityTypeMessages;

    public Bot(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 开启
     */
    public void on() {
        String tag = TAG_BOT_ON;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        if (qqList.size() == 0) {
            qqList.add(String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()));
        }
        for (String qq : qqList) {
            if (qq.equals(String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()))) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("can'tInPrivate"));
                    return;
                }
                if (getBot(groupId)) {
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("botAlreadyStart"));
                } else {
                    botOn(groupId);
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("botStart"));
                }
            }
        }
    }

    /**
     * 关闭
     */
    public void off() {
        String tag = TAG_BOT_OFF;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        if (qqList.size() == 0) {
            qqList.add(String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()));
        }

        for (String qq : qqList) {
            if (qq.equals(String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()))) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("can'tInPrivate"));
                    return;
                }
                if (!getBot(groupId)) {
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("botAlreadyStop"));
                } else {
                    botOff(groupId);
                    sender(entityTypeMessages, MESSAGES_SYSTEM.get("botStop"));
                }
            }
        }
    }

    /**
     * 退群
     */
    public void exit() {
        String tag = TAG_BOT_EXIT;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        for (String qq : qqList) {
            if (qq.equals(String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()))) {
                sender(entityTypeMessages, MESSAGES_SYSTEM.get("botExit"));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                entityTypeMessages.getMsgSender().SETTER.setGroupLeave(entityTypeMessages.getFromGroup());
            }
        }
    }

    /**
     * 机器人信息
     */
    public void info() {
        EntityGroupCensus entityGroupCensus = selectBot();
        sender(entityTypeMessages, STR_BOT_VERSIONS.toString() + "\n目前供职于: " + entityGroupCensus.getGroupNum() + " 个群，其中 " + entityGroupCensus.getOnNum() + " 个群处于开启状态");
    }
}
