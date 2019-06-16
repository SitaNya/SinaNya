package dice.sinanya.db.system;

import dice.sinanya.db.tools.DbUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void selectBot() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select groupId,switchBot from switchBot";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
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
}
