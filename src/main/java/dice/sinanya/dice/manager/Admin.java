package dice.sinanya.dice.manager;

import com.sobte.cqp.jcq.entity.Group;
import com.sobte.cqp.jcq.entity.Member;
import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.entity.EntityTypeMessages;
import org.apache.commons.lang.StringUtils;

import java.util.List;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
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
public class Admin implements MakeNickToSender {
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

    public void search() {
        String tag = TAG_ADMIN_SEARCH;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        long qqId = 0;
        if (!isNumeric(msg)) {
            sender(entityTypeMessages, "请输入QQ号码");
            return;
        } else {
            qqId = Long.parseLong(msg);
        }
        StringBuilder hasGroup = new StringBuilder();
        hasGroup.append("你和指定人同处于以下群中:");
        List<Group> groups = CQ.getGroupList();
        for (Group group : groups) {
            List<Member> members = CQ.getGroupMemberList(group.getId());
            for (Member member : members) {
                if (qqId == member.getQqId()) {
                    hasGroup.append("\n").append(makeGroupNickToSender(group.getName())).append("(").append(group.getId()).append(")");
                }
            }
        }
        sender(entityTypeMessages, hasGroup.toString());
    }
}
