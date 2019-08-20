package dice.sinanya.tools.getinfo;

import dice.sinanya.db.ban.InputBanList;
import dice.sinanya.db.ban.SelectBanList;
import dice.sinanya.db.heap.SelectOnlineBotList;
import dice.sinanya.entity.EntityOtherBotInfo;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotBanListInputException;

import java.util.ArrayList;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesBanList.groupBanList;
import static dice.sinanya.system.MessagesBanList.qqBanList;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityBanProperties;
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

    private static SelectBanList selectBanList = new SelectBanList();
    private static InputBanList insertBanList = new InputBanList();

    private BanList() {
        throw new IllegalStateException("Utility class");
    }

    public static void flushBanList() {
        if (entityBanProperties.isCloudBan()) {
            selectBanList.flushGroupBanListFromDataBase();
            selectBanList.flushQqBanListFromDataBase();
        }
    }

    public static void insertQqBanList(String qq, String reason) {
        insertBanList.insertQqBanList(qq, reason);
        qqBanList.put(qq, reason);
    }

    public static void insertGroupBanList(String groupId, String reason) {
        insertBanList.insertGroupBanList(groupId, reason);
        groupBanList.put(groupId, reason);
    }

    public static boolean checkQqInBanList(String qq) {
        return qqBanList.containsKey(qq);
    }

    public static boolean checkGroupInBanList(String groupId) {
        return groupBanList.containsKey(groupId);
    }

    public static void removeQqBanList(String qq, EntityTypeMessages entityTypeMessages) throws NotBanListInputException {
        String id = insertBanList.selectOthorInputBanQq(qq, entityTypeMessages);

        if (Long.parseLong(id) == CQ.getLoginQQ()) {
            insertBanList.removeQqBanList(qq, entityTypeMessages);
            qqBanList.remove(qq);
            sender(entityTypeMessages, "已将用户:\t" + qq + "移出云黑名单");
            return;
        }

        ArrayList<EntityOtherBotInfo> otherBotInfos = new SelectOnlineBotList().selectOnlineBotList();
        for (EntityOtherBotInfo entityOtherBotInfo : otherBotInfos) {
            if (entityOtherBotInfo.getBotId().equals(id)) {
                id = entityOtherBotInfo.getBotName() + "(" + entityOtherBotInfo.getBotId() + ")";
                sender(entityTypeMessages, "您无法删除此用户黑名单，录入人为: " + id);
                return;
            }
        }
    }

    public static void removeGroupBanList(String groupId, EntityTypeMessages entityTypeMessages) throws NotBanListInputException {
        String id = insertBanList.selectOthorInputBanGroup(groupId, entityTypeMessages);
        if (Long.parseLong(id) == CQ.getLoginQQ()) {
            insertBanList.removeGroupBanList(groupId, entityTypeMessages);
            groupBanList.remove(groupId);
            sender(entityTypeMessages, "已将群:\t" + groupId + "移出云黑名单");
            return;
        }
        ArrayList<EntityOtherBotInfo> otherBotInfos = new SelectOnlineBotList().selectOnlineBotList();
        for (EntityOtherBotInfo entityOtherBotInfo : otherBotInfos) {
            if (entityOtherBotInfo.getBotId().equals(id)) {
                id = entityOtherBotInfo.getBotName() + "(" + entityOtherBotInfo.getBotId() + ")";
                sender(entityTypeMessages, "您无法删除此群黑名单，录入人为: " + id);
                return;
            }
        }
    }
}
