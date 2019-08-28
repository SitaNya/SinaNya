package dice.sinanya.dice.manager;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotMasterException;
import dice.sinanya.exceptions.WhiteListInputNotIdException;
import org.apache.commons.lang.StringUtils;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesBanList.groupWhiteList;
import static dice.sinanya.system.MessagesBanList.qqWhiteList;
import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityBanProperties;
import static dice.sinanya.tools.getinfo.WhiteList.*;
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
public class WhiteList {


    private EntityTypeMessages entityTypeMessages;

    public WhiteList(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     *
     */
    public void inputQqWhiteList() {
        if (!entityBanProperties.isWhiteUser()) {
            sender(entityTypeMessages, "未启用用户白名单");
            return;
        }
        String tag = TAG_BAN_USER;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            long qqId=Long.parseLong(msg);
            if (qqWhiteList.contains(qqId)) {
                sender(entityTypeMessages, "用户:\t" + qqId + "已在白名单中");
            } else {
                insertUserWhiteList(qqId);
                sender(entityTypeMessages, "已将用户:\t" + qqId + "加入白名单");
            }
        } catch (WhiteListInputNotIdException | NotMasterException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }


    /**
     *
     */
    public void inputGroupWhiteList() {
        if (!entityBanProperties.isWhiteGroup()) {
            sender(entityTypeMessages, "未启群组白名单");
            return;
        }
        String tag = TAG_BAN_GROUP;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            long groupId=Long.parseLong(msg);
            if (groupWhiteList.contains(groupId)) {
                sender(entityTypeMessages, "群:\t" + groupId + "已在白名单中");
            } else {
                insertGroupWhiteList(groupId);
                sender(entityTypeMessages, "已将群:\t" + groupId + "加白名单");
            }
        } catch (WhiteListInputNotIdException | NotMasterException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }


    /**
     *
     */
    public void rmQqWhiteList() {
        if (!entityBanProperties.isWhiteUser()) {
            sender(entityTypeMessages, "未启用用户白名单");
            return;
        }
        String tag = TAG_RM_BAN_USER;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            long qqId=Long.parseLong(msg);
            if (qqWhiteList.contains(qqId)) {
                removeQqWhiteList(qqId);
            } else {
                sender(entityTypeMessages, "用户:\t" + qqId + "不在白名单中");
            }
        } catch (WhiteListInputNotIdException | NotMasterException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }


    /**
     *
     */
    public void rmGroupWhiteList() {
        if (!entityBanProperties.isWhiteGroup()) {
            sender(entityTypeMessages, "未启群组白名单");
            return;
        }
        String tag = TAG_RM_BAN_GROUP;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 4));
        try {
            checkMaster();
            isQqOrGroup(msg);
            long groupId=Long.parseLong(msg);
            if (groupWhiteList.contains(groupId)) {
                removeGroupWhiteList(groupId);
            } else {
                sender(entityTypeMessages, "群:\t" + groupId + "不在白名单中");
            }
        } catch (WhiteListInputNotIdException| NotMasterException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }


    /**
     *
     */
    public void getQqWhiteList() {
        if (!entityBanProperties.isWhiteUser()) {
            sender(entityTypeMessages, "未启用用户白名单");
            return;
        }
        try {
            checkMaster();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("当前记录的白名单用户列表如下，白名单不做同步");
            for (long qqId : qqWhiteList) {
                stringBuilder.append("\n").append(qqId);
            }
            sender(entityTypeMessages, stringBuilder.toString());
        } catch (NotMasterException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }

    /**
     *
     */
    public void getGroupWhiteList() {
        if (!entityBanProperties.isWhiteGroup()) {
            sender(entityTypeMessages, "未启群组白名单");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("当前记录的白名单群列表如下，白名单不做同步");
        for (long groupId:groupWhiteList) {
            stringBuilder.append("\n").append(groupId);
        }
        sender(entityTypeMessages, stringBuilder.toString());
    }

    private void isQqOrGroup(String input) throws WhiteListInputNotIdException {
        if (!isNumeric(input) || input.length() > 15 || input.length() < 4) {
            CQ.logError("白明单添加错误", "输入的不是群号或QQ号");
            throw new WhiteListInputNotIdException(entityTypeMessages);
        }
    }

    private void checkMaster() throws NotMasterException {
        if (!entityBanProperties.getMaster().equals(String.valueOf(entityTypeMessages.getFromQq()))) {
            throw new NotMasterException(entityTypeMessages);
        }
    }
}
