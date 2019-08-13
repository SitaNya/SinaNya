package dice.sinanya.tools.getinfo;

import dice.sinanya.db.ban.InputBanList;
import dice.sinanya.db.ban.SelectBanList;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotBanListInputException;

import static dice.sinanya.system.MessagesBanList.groupBanList;
import static dice.sinanya.system.MessagesBanList.qqBanList;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityBanProperties;

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
        insertBanList.removeQqBanList(qq, entityTypeMessages);
        qqBanList.remove(qq);
    }

    public static void removeGroupBanList(String groupId, EntityTypeMessages entityTypeMessages) throws NotBanListInputException {
        insertBanList.removeGroupBanList(groupId, entityTypeMessages);
        groupBanList.remove(groupId);
    }
}
