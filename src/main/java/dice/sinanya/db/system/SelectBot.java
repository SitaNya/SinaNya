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
 * 查询某个群的开启与关闭情况
 *
 * @author SitaNya
 */
public class SelectBot {
    private static final Logger Log = LogManager.getLogger(SelectBot.class);

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
