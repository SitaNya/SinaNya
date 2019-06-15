package dice.sinanya.dice.system;

import dice.sinanya.dice.manager.imal.AtQq;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;

import static dice.sinanya.system.GetMessagesSystem.*;
import static dice.sinanya.system.MessagesSystem.STR_BOT_VERSIONS;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.getinfo.SwitchBot.*;
import static dice.sinanya.tools.log.Sender.sender;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;

/**
 * 机器人开启、关闭、退群
 *
 * @author SitaNya
 */
public class Bot implements AtQq {

    private EntityTypeMessages entityTypeMessages;

    public Bot(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void on() {
        String tag = TAG_BOT_ON;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        for (String qq : qqList) {
            if (qq.equals(entityTypeMessages.getMsgSender().GETTER.getLoginQQInfo().getQQ())) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, "无法在私聊中使用");
                    return;
                }
                if (getBot(groupId)) {
                    sender(entityTypeMessages, messagesSystem.get("botAlreadyStart"));
                } else {
                    botOn(groupId);
                    sender(entityTypeMessages, messagesSystem.get("botStart"));
                }
            }
        }
    }

    public void off() {
        String tag = TAG_BOT_OFF;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        for (String qq : qqList) {
            if (qq.equals(entityTypeMessages.getMsgSender().GETTER.getLoginQQInfo().getQQ())) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, "无法在私聊中使用");
                    return;
                }
                if (!getBot(groupId)) {
                    sender(entityTypeMessages, messagesSystem.get("botAlreadyStop"));
                } else {
                    botOff(groupId);
                    sender(entityTypeMessages, messagesSystem.get("botStop"));
                }
            }
        }
    }

    public void exit() {
        String tag = TAG_BOT_EXIT;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        for (String qq : qqList) {
            if (qq.equals(entityTypeMessages.getMsgSender().GETTER.getLoginQQInfo().getQQ())) {
                sender(entityTypeMessages, messagesSystem.get("botExit"));
                entityTypeMessages.getMsgSender().SETTER.setGroupLeave(entityTypeMessages.getFromGroup());
            }
        }
    }

    public void info() {
        sender(entityTypeMessages, STR_BOT_VERSIONS.toString());
    }
}
