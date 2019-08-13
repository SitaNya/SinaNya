package dice.sinanya.dice.system;

import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.dice.manager.imal.AtQq;
import dice.sinanya.entity.EntityGroupCensus;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.entity.imal.MessagesTypes;
import dice.sinanya.system.MessagesSystem;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.db.system.SelectBot.selectBot;
import static dice.sinanya.system.MessagesSystem.*;
import static dice.sinanya.system.MessagesTag.*;

import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
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
public class Bot implements AtQq, MakeNickToSender {

    private Logger log = LogManager.getLogger(Bot.class.getName());

    private EntityTypeMessages entityTypeMessages;

    public Bot(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 开启
     */
    public void on() {
        String tag = TAG_BOT_ON;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        if (qqList.isEmpty()) {
            qqList.add(String.valueOf(CQ.getLoginQQ()));
        }
        for (String qq : qqList) {
            if (qq.equals(String.valueOf(CQ.getLoginQQ()))) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, entitySystemProperties.getCantInPrivate());
                    return;
                }
                if (getBot(groupId)) {
                    sender(entityTypeMessages, entitySystemProperties.getBotAlreadyStart());
                } else {
                    botOn(groupId);
                    sender(entityTypeMessages, entitySystemProperties.getBotStart());
                }
            }
        }
    }

    /**
     * 关闭
     */
    public void off() {
        String tag = TAG_BOT_OFF;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        if (qqList.isEmpty()) {
            qqList.add(String.valueOf(CQ.getLoginQQ()));
        }

        for (String qq : qqList) {
            if (qq.equals(String.valueOf(CQ.getLoginQQ()))) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, entitySystemProperties.getCantInPrivate());
                    return;
                }
                if (!getBot(groupId)) {
                    sender(entityTypeMessages, entitySystemProperties.getBotAlreadyStop());
                } else {
                    botOff(groupId);
                    sender(entityTypeMessages, entitySystemProperties.getBotStop());
                }
            }
        }
    }

    /**
     * 退群
     */
    public void exit() {
        String tag = TAG_BOT_EXIT;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));

        ArrayList<String> qqList = getAtQqList(msg);

        if (qqList.isEmpty()) {
            qqList.add(String.valueOf(CQ.getLoginQQ()));
        }

        for (String qq : qqList) {
            if (qq.equals(String.valueOf(CQ.getLoginQQ()))) {
                sender(entityTypeMessages, entitySystemProperties.getBotExit());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
                    Thread.currentThread().interrupt();
                }
                if (entityTypeMessages.getMessagesTypes() == MessagesTypes.GROUP_MSG) {
                    CQ.setGroupLeave(Long.parseLong(entityTypeMessages.getFromGroup()), false);
                } else if (entityTypeMessages.getMessagesTypes() == MessagesTypes.DISCUSS_MSG) {
                    CQ.setDiscussLeave(Long.parseLong(entityTypeMessages.getFromGroup()));
                }
            }
        }
    }

    /**
     * 机器人信息
     */
    public void info() {
        String botInfo = entitySystemProperties.getBotInfo();
        if (!botInfo.equals(NONE)) {
            botInfo = "\n" + botInfo;
        }
        EntityGroupCensus entityGroupCensus = selectBot();
        sender(entityTypeMessages, STR_BOT_VERSIONS.toString() + "\n目前供职于:\t" + entityGroupCensus.getGroupNum() + " 个群，其中 " + entityGroupCensus.getOnNum() + " 个群处于开启状态" + botInfo);
    }

    /**
     * 机器人更新日志
     */
    public void update() {
        sender(entityTypeMessages, "当前版本:\t" + MessagesSystem.VERSIONS + "\n" + UPDATE.toString());
    }

}
