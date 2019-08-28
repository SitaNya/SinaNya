package dice.sinanya.tools.getinfo;

import dice.sinanya.db.white.InputWhiteList;
import dice.sinanya.db.white.SelectWhiteList;

import static dice.sinanya.system.MessagesBanList.groupWhiteList;
import static dice.sinanya.system.MessagesBanList.qqWhiteList;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityBanProperties;

/**
 * @author SitaNya
 * 日期: 2019/8/28
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class WhiteList {
    static InputWhiteList inputWhiteList = new InputWhiteList();
    static SelectWhiteList selectWhiteList = new SelectWhiteList();

    public static void flushWhiteList() {
        if (entityBanProperties.isWhiteGroup()) {
            selectWhiteList.flushGroupWhiteListFromDataBase();
        }
        if (entityBanProperties.isWhiteUser()) {
            selectWhiteList.flushQqWhiteListFromDataBase();
        }
    }

    public static void insertGroupWhiteList(long groupId) {
        inputWhiteList.insertGroupWhiteList(groupId);
        groupWhiteList.add(groupId);
    }

    public static void insertUserWhiteList(long qqId) {
        inputWhiteList.insertQqWhiteList(qqId);
        qqWhiteList.add(qqId);
    }

    public static boolean checkQqInWhiteList(long qq) {
        return qqWhiteList.contains(qq);
    }

    public static boolean checkGroupInWhiteList(long group) {
        return groupWhiteList.contains(group);
    }

    public static void removeQqWhiteList(long qq) {
        inputWhiteList.removeQqWhiteList(qq);
        qqWhiteList.remove(qq);
    }

    public static void removeGroupWhiteList(long group) {
        inputWhiteList.removeGroupWhiteList(group);
        groupWhiteList.remove(group);
    }
}
