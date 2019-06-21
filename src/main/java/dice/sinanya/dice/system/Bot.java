package dice.sinanya.dice.system;

import com.forte.qqrobot.BaseConfiguration;
import com.forte.qqrobot.component.forhttpapi.HttpConfiguration;
import dice.sinanya.dice.manager.imal.AtQq;
import dice.sinanya.entity.EntityGroupCensus;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;

import static dice.sinanya.db.system.SelectBot.selectBot;
import static dice.sinanya.system.MessagesSystem.STR_BOT_VERSIONS;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.messagesSystem;
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
            qqList.add(HttpConfiguration.getLocalQQCode());
        }
        for (String qq : qqList) {
            if (qq.equals(HttpConfiguration.getLocalQQCode())) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, messagesSystem.get("can'tInPrivate"));
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

    /**
     * 关闭
     */
    public void off() {
        String tag = TAG_BOT_OFF;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        for (String qq : qqList) {
            if (qq.equals(HttpConfiguration.getLocalQQCode())) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, messagesSystem.get("can'tInPrivate"));
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

    /**
     * 退群
     */
    public void exit() {
        String tag = TAG_BOT_EXIT;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        for (String qq : qqList) {
            if (qq.equals(HttpConfiguration.getLocalQQCode())) {
                sender(entityTypeMessages, messagesSystem.get("botExit"));
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
