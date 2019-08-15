package dice.sinanya.tools.getinfo;

import dice.sinanya.db.ban.InputBanList;
import dice.sinanya.db.ban.SelectBanList;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotBanListInputException;

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
        boolean remove = insertBanList.removeQqBanList(qq, entityTypeMessages);
        if (remove) {
            qqBanList.remove(qq);
            sender(entityTypeMessages, "已将用户:\t" + entityTypeMessages.getMsg() + "移出云黑名单");
        } else {
            sender(entityTypeMessages, "您无法删除此用户黑名单，录入人为: " + insertBanList.selectOthorInputBanQq(entityTypeMessages.getMsg(), entityTypeMessages));
        }
    }

    public static void removeGroupBanList(String groupId, EntityTypeMessages entityTypeMessages) throws NotBanListInputException {
        boolean remove = insertBanList.removeGroupBanList(groupId, entityTypeMessages);
        if (remove) {
            groupBanList.remove(groupId);
            sender(entityTypeMessages, "已将群:\t" + entityTypeMessages.getMsg() + "移出云黑名单");
        } else {
            sender(entityTypeMessages, "您无法删除此群黑名单，录入人为: " + insertBanList.selectOthorInputBanGroup(entityTypeMessages.getMsg(), entityTypeMessages));
        }
    }
}
