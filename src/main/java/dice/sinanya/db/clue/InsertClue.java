package dice.sinanya.db.clue;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityClue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 录入线索集
 *
 * @author SitaNya
 */
public class InsertClue {

    public InsertClue() {
    }

    public void insertClue(EntityClue entityClue, String info) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO clue(" +
                    "groupId, createTime, qqId,info) VALUES(?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityClue.getGroupId());
                ps.setDate(2, entityClue.getDate());
                ps.setString(3, entityClue.getQqId());
                ps.setString(4, info);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClue(EntityClue entityClue) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from clue where " +
                    "groupId=? and createTime=? and qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityClue.getGroupId());
                ps.setDate(2, entityClue.getDate());
                ps.setString(3, entityClue.getQqId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clrClue(String groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from clue where " +
                    "groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
