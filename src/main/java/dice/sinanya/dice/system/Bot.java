package dice.sinanya.dice.system;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesSystem.*;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.Sender.sender;
import static dice.sinanya.tools.SwitchBot.*;

public class Bot {

    private EntityTypeMessages entityTypeMessages;
    Pattern pattern = Pattern.compile("\\[CQ:at,qq=([0-9]+)]");

    public Bot(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void on() {
        String tag = TAG_BOT_ON;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }

        for (String qq : qqList) {
            if (qq.equals(entityTypeMessages.getMsgSender().GETTER.getLoginQQInfo().getQQ())) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, "无法在私聊中使用");
                    return;
                }
                if (getBot(groupId)) {
                    sender(entityTypeMessages, STR_ALREADY_ENABLED_ERR);
                } else {
                    botOn(groupId);
                    sender(entityTypeMessages, STR_SUCCESSFULLY_ENABLED_NOTICE);
                }
            }
        }
    }

    public void off() {
        String tag = TAG_BOT_OFF;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }

        for (String qq : qqList) {
            if (qq.equals(entityTypeMessages.getMsgSender().GETTER.getLoginQQInfo().getQQ())) {
                long groupId = Long.parseLong(entityTypeMessages.getFromGroup());
                if (groupId == 0) {
                    sender(entityTypeMessages, "无法在私聊中使用");
                    return;
                }
                if (!getBot(groupId)) {
                    sender(entityTypeMessages, STR_ALREADY_DISABLED_ERR);
                } else {
                    botOff(groupId);
                    sender(entityTypeMessages, STR_SUCCESSFULLY_DISABLED_NOTICE);
                }
            }
        }
    }

    public void exit() {
        String tag = TAG_BOT_EXIT;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }

        for (String qq : qqList) {
            if (qq.equals(entityTypeMessages.getMsgSender().GETTER.getLoginQQInfo().getQQ())) {
                sender(entityTypeMessages, STR_EXIT_INFO);
                entityTypeMessages.getMsgSender().SETTER.setGroupLeave(entityTypeMessages.getFromGroup());
            }
        }
    }

    public void info() {
        sender(entityTypeMessages, STR_BOT_VERSIONS.toString());
    }
}
