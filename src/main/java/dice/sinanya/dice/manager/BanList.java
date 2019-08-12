package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.BanListInputNotIdException;
import dice.sinanya.exceptions.NotBanListInputException;
import dice.sinanya.exceptions.NotMasterException;
import org.apache.logging.log4j.LogManager;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import java.util.Arrays;

import java.util.Arrays;
import java.util.Map;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesBanList.groupBanList;
import static dice.sinanya.system.MessagesBanList.qqBanList;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.BanList.*;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.MESSAGES_SYSTEM;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-08-07
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class BanList {


    private EntityTypeMessages entityTypeMessages;

    public BanList(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     *
     */
    public void inputQqBanList() {
        if (!Boolean.parseBoolean(MESSAGES_SYSTEM.get("cloudBan"))) {
            sender(entityTypeMessages,"配置文件中未启用云黑");
            return;
        }
        String tag = TAG_BAN_USER;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            insertQqBanList(msg, "手工录入");
            sender(entityTypeMessages,"已将用户:\t"+msg+"加入云黑名单");
        } catch (BanListInputNotIdException|NotMasterException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }


    /**
     *
     */
    public void inputGroupBanList() {
        if (!Boolean.parseBoolean(MESSAGES_SYSTEM.get("cloudBan"))) {
            sender(entityTypeMessages,"配置文件中未启用云黑");
            return;
        }
        String tag = TAG_BAN_GROUP;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            insertGroupBanList(msg, "手工录入");
            sender(entityTypeMessages,"已将群:\t"+msg+"加入云黑名单");
        } catch (BanListInputNotIdException|NotMasterException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }


    /**
     *
     */
    public void rmQqBanList() {
        if (!Boolean.parseBoolean(MESSAGES_SYSTEM.get("cloudBan"))) {
            sender(entityTypeMessages,"配置文件中未启用云黑");
            return;
        }
        String tag = TAG_RM_BAN_USER;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            removeQqBanList(msg, entityTypeMessages);
            sender(entityTypeMessages,"已将用户:\t"+msg+"移出云黑名单");
        } catch (BanListInputNotIdException | NotBanListInputException|NotMasterException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }


    /**
     *
     */
    public void rmGroupBanList() {
        if (!Boolean.parseBoolean(MESSAGES_SYSTEM.get("cloudBan"))) {
            sender(entityTypeMessages,"配置文件中未启用云黑");
            return;
        }
        String tag = TAG_RM_BAN_GROUP;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            removeGroupBanList(msg, entityTypeMessages);
            sender(entityTypeMessages,"已将群:\t"+msg+"移出云黑名单");
        } catch (BanListInputNotIdException | NotBanListInputException|NotMasterException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }


    /**
     *
     */
    public void getQqBanList() {
        if (!Boolean.parseBoolean(MESSAGES_SYSTEM.get("cloudBan"))) {
            sender(entityTypeMessages,"配置文件中未启用云黑");
            return;
        }
        try {
            checkMaster();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("当前云黑内记录的黑名单用户列表如下，黑名单的刷新周期为15分钟，其他人新添加的黑名单可能暂时未同步");
            for (Map.Entry<String, String> mapEntry : qqBanList.entrySet()) {
                stringBuilder.append("\n").append(mapEntry.getKey());
            }
            sender(entityTypeMessages, stringBuilder.toString());
        } catch (NotMasterException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     *
     */
    public void getGroupBanList() {
        if (!Boolean.parseBoolean(MESSAGES_SYSTEM.get("cloudBan"))) {
            sender(entityTypeMessages,"配置文件中未启用云黑");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("当前云黑内记录的黑名单群列表如下，黑名单的刷新周期为15分钟，其他人新添加的黑名单可能暂时未同步");
        for (Map.Entry<String, String> mapEntry : groupBanList.entrySet()) {
            stringBuilder.append("\n").append(mapEntry.getKey());
        }
        sender(entityTypeMessages, stringBuilder.toString());
    }

    private void isQqOrGroup(String input) throws BanListInputNotIdException {
        if (!isNumeric(input) || input.length() > 15 || input.length() < 4) {
            CQ.logError("黑名单添加错误", "输入的不是群号或QQ号");
            throw new BanListInputNotIdException(entityTypeMessages);
        }
    }

    private void checkMaster() throws NotMasterException {
        if (!MESSAGES_SYSTEM.get("master").equals(String.valueOf(entityTypeMessages.getFromQq()))) {
            throw new NotMasterException(entityTypeMessages);
        }
    }
}
