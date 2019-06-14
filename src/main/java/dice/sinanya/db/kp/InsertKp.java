package dice.sinanya.db.kp;

import dice.sinanya.db.tools.DbUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 录入kp主群
 *
 * @author SitaNya
 */
public class InsertKp {

    private static final Logger Log = LogManager.getLogger(InsertKp.class);

    public void insertKp(String qqId, String groupId) {
        int num = 0;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from kp where qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qqId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        num++;
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        if (num == 0) {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "INSERT INTO kp(qqId,groupId) VALUES(?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, qqId);
                    ps.setString(2, groupId);

                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
        } else {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "update kp set groupId=? where qqId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setString(1, groupId);
                    ps.setString(2, qqId);

                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }
}
