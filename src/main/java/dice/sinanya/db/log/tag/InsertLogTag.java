package dice.sinanya.db.log.tag;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityLogTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 录入log开启标签
 *
 * @author zhangxiaozhou
 */
public class InsertLogTag {
    public InsertLogTag() {
    }

    public void insertLogTag(EntityLogTag entityLogTag, boolean logSwitch) {
        int num = 0;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from tagLog where groupId=? and logName=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityLogTag.getGroupId());
                ps.setString(2, entityLogTag.getLogName());
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
                String sql = "INSERT INTO tagLog(" +
                        "groupId, logName,logSwitch) VALUES(?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, entityLogTag.getGroupId());
                    ps.setString(2, entityLogTag.getLogName());
                    ps.setBoolean(3, logSwitch);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "update tagLog set logSwitch=? where groupId=? and logName=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setBoolean(1, logSwitch);
                    ps.setString(2, entityLogTag.getGroupId());
                    ps.setString(3, entityLogTag.getLogName());
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteLog(EntityLogTag entityLogTag) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from tagLog where groupId=? and logName=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityLogTag.getGroupId());
                ps.setString(2, entityLogTag.getLogName());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from textLog where groupId=? and logName=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityLogTag.getGroupId());
                ps.setString(2, entityLogTag.getLogName());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
