package dice.sinanya.dice.system;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesSystem.*;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.Sender.sender;
import static dice.sinanya.tools.SwitchBot.*;

public class Bot {

    private EntityTypeMessages entityTypeMessages;

    public Bot(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void on() {
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

    public void off() {
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

    public void exit() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".bot");
        String tagExit = "exit";
        if (msg.equals(tagExit)) {
            sender(entityTypeMessages, STR_EXIT_INFO);
            entityTypeMessages.getMsgSender().SETTER.setGroupLeave(entityTypeMessages.getFromGroup());
        }
    }

    public void info() {
        sender(entityTypeMessages, STR_BOT_INFO.toString());
    }
}
