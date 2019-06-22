package dice.sinanya.db.system;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityGroupCensus;
import dice.sinanya.entity.EntityTypeMessages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dice.sinanya.system.MessagesSystem.entityLoginQQInfo;
import static dice.sinanya.system.SystemInfo.SWITCH_BOT;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询所有开关的情况，并刷新到变量中，一般只在变量中找不到时才会使用
 */
public class SelectBot {
    private static final Logger Log = LogManager.getLogger(SelectBot.class);


    /**
     * 将所有开关值刷写到静态变量中
     */
    public static void flushBot() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select groupId,switchBot from switchBot where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(entityLoginQQInfo.getLoginQQ()));
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        SWITCH_BOT.put(set.getLong("groupId"), set.getBoolean("switchBot"));
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * 通过查询开关列表，得到有多少开启的群，加入多少群
     */
    public static EntityGroupCensus selectBot() {
        int groupNum = 0;
        int onNum = 0;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select groupId,switchBot from switchBot where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1,String.valueOf(entityLoginQQInfo.getLoginQQ()));
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        groupNum++;
                        if (set.getBoolean("switchBot")) {
                            onNum++;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return new EntityGroupCensus(groupNum, onNum);
    }
}
