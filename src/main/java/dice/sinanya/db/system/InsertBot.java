package dice.sinanya.db.system;

import dice.sinanya.db.tools.DbUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 录入某个群的开启与关闭情况
 *
 * @author SitaNya
 */
public class InsertBot {
    private static final Logger Log = LogManager.getLogger(InsertBot.class);
    public void insertBot(long groupId, boolean switchBot) {
        int num = 0;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from switchBot where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, groupId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        num++;
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(),e);
        }

        if (num == 0) {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "INSERT INTO switchBot(" +
                        "groupId," +
                        "switchBot" +
                        ") VALUES(?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setLong(1, groupId);
                    ps.setBoolean(2, switchBot);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(),e);
            }
        } else {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "update switchBot set " +
                        "switchBot=? where groupId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setBoolean(1, switchBot);
                    ps.setLong(2, groupId);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(),e);
            }
        }
    }
}
