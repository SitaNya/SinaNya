package dice.sinanya.db.system;

import dice.sinanya.db.tools.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertBot {
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
            System.out.println(e.getMessage());
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
                System.out.println(e.getMessage());
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
                System.out.println(e.getMessage());
            }
        }
    }
}
