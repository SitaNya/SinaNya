package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.BanListInputNotIdException;
import dice.sinanya.exceptions.NotBanListInputException;
import dice.sinanya.exceptions.NotMasterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

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
    private static final Logger Log = LogManager.getLogger(BanList.class.getName());

    private EntityTypeMessages entityTypeMessages;

    public BanList(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     *
     */
    public void inputQqBanList() {
        String tag = TAG_BAN_USER;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            insertQqBanList(msg, "手工录入");
            sender(entityTypeMessages,"已将用户:\t"+msg+"加入云黑名单");
        } catch (BanListInputNotIdException|NotMasterException e) {
            Log.error(e.getMessage(), e);
        }
    }


    /**
     *
     */
    public void inputGroupBanList() {
        String tag = TAG_BAN_GROUP;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            insertGroupBanList(msg, "手工录入");
            sender(entityTypeMessages,"已将群:\t"+msg+"加入云黑名单");
        } catch (BanListInputNotIdException|NotMasterException e) {
            Log.error(e.getMessage(), e);
        }
    }


    /**
     *
     */
    public void rmQqBanList() {
        String tag = TAG_RM_BAN_USER;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            removeQqBanList(msg, entityTypeMessages);
            sender(entityTypeMessages,"已将用户:\t"+msg+"移出云黑名单");
        } catch (BanListInputNotIdException | NotBanListInputException|NotMasterException e) {
            Log.error(e.getMessage(), e);
        }
    }


    /**
     *
     */
    public void rmGroupBanList() {
        String tag = TAG_RM_BAN_GROUP;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            removeGroupBanList(msg, entityTypeMessages);
            sender(entityTypeMessages,"已将群:\t"+msg+"移出云黑名单");
        } catch (BanListInputNotIdException | NotBanListInputException|NotMasterException e) {
            Log.error(e.getMessage(), e);
        }
    }


    /**
     *
     */
    public void getQqBanList() {
        try {
            checkMaster();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("当前云黑内记录的黑名单用户列表如下，黑名单的刷新周期为15分钟，其他人新添加的黑名单可能暂时未同步");
            for (Map.Entry<String, String> mapEntry : qqBanList.entrySet()) {
                stringBuilder.append("\n").append(mapEntry.getKey());
            }
            sender(entityTypeMessages, stringBuilder.toString());
        } catch (NotMasterException e) {
            Log.error(e.getMessage(),e);
        }
    }

    /**
     *
     */
    public void getGroupBanList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("当前云黑内记录的黑名单群列表如下，黑名单的刷新周期为15分钟，其他人新添加的黑名单可能暂时未同步");
        for (Map.Entry<String, String> mapEntry : groupBanList.entrySet()) {
            stringBuilder.append("\n").append(mapEntry.getKey());
        }
        sender(entityTypeMessages, stringBuilder.toString());
    }

    private void isQqOrGroup(String input) throws BanListInputNotIdException {
        if (!isNumeric(input) || input.length() > 15 || input.length() < 4) {
            Log.error(input);
            throw new BanListInputNotIdException(entityTypeMessages);
        }
    }

    private void checkMaster() throws NotMasterException {
        if (!entityTypeMessages.getFromQq().equals(MESSAGES_SYSTEM.get("master"))) {
            throw new NotMasterException(entityTypeMessages);
        }
    }
}
