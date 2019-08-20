package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityTypeMessages;
import org.apache.commons.lang.StringUtils;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityBanProperties;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
import static dice.sinanya.tools.getinfo.SwitchBot.botOff;
import static dice.sinanya.tools.getinfo.SwitchBot.botOn;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-08-14
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class Admin {
    private EntityTypeMessages entityTypeMessages;

    public Admin(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void on() {
        String tag = TAG_ADMIN_ON;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        if (entityBanProperties.getMaster().equals(entityTypeMessages.getFromQq())) {
            botOn(Long.parseLong(msg));
            CQ.sendGroupMsg(Long.parseLong(msg), entitySystemProperties.getBotStart());
        } else {
            sender(entityTypeMessages, entityBanProperties.getNotMaster());
        }
    }

    public void off() {
        String tag = TAG_ADMIN_OFF;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        if (entityBanProperties.getMaster().equals(entityTypeMessages.getFromQq())) {
            botOff(Long.parseLong(msg));
            CQ.sendGroupMsg(Long.parseLong(msg), entitySystemProperties.getBotAlreadyStop());
        } else {
            sender(entityTypeMessages, entityBanProperties.getNotMaster());
        }
    }

    public void exit() {
        String tag = TAG_ADMIN_EXIT;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        if (entityBanProperties.getMaster().equals(entityTypeMessages.getFromQq())) {
            CQ.sendGroupMsg(Long.parseLong(msg), entitySystemProperties.getBotExit());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
                Thread.currentThread().interrupt();
            }
            CQ.setGroupLeave(Long.parseLong(msg), false);
        } else {
            sender(entityTypeMessages, entityBanProperties.getNotMaster());
        }
    }
}
