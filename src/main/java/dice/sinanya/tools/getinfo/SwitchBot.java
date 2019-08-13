package dice.sinanya.tools.getinfo;

import dice.sinanya.db.system.InsertBot;

import static dice.sinanya.system.SystemInfo.SWITCH_BOT;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 机器人开关数据库交互类
 */
public class SwitchBot {
    private static InsertBot insertBot = new InsertBot();

    private SwitchBot() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 设定某个群机器人开启
     *
     * @param groupId 群号
     */
    public static void botOn(Long groupId) {
        SWITCH_BOT.put(groupId, true);
        insertBot.insertBot(groupId, true);
    }

    /**
     * 设定某个群机器人关闭
     *
     * @param groupId 群号
     */
    public static void botOff(Long groupId) {
        SWITCH_BOT.put(groupId, false);
        insertBot.insertBot(groupId, false);
    }

    /**
     * 获取某个群机器人开启状态，不存在视为开启
     *
     * @param groupId 群号
     */
    public static boolean getBot(Long groupId) {
        if (SWITCH_BOT.containsKey(groupId)) {
            return SWITCH_BOT.get(groupId);
        } else {
            insertBot.insertBot(groupId, true);
            SWITCH_BOT.put(groupId, true);
            return true;
        }
    }

}
