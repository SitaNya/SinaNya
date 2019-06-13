package dice.sinanya.db.history;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 录入骰点历史
 *
 * @author zhangxiaozhou
 */
public class InsertHistory {
    public void insertHistory(EntityHistory entityHistory) {
        int num = 0;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from history where qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityHistory.getQqId());
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
                String sql = "INSERT INTO history(qqId,Fumble,CriticalSuccess,ExtremeSuccess,HardSuccess,Success,Failure,times,mean) VALUES(?,?,?,?,?,?,?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, entityHistory.getQqId());
                    ps.setInt(2, entityHistory.getFumble());
                    ps.setInt(3, entityHistory.getCriticalSuccess());
                    ps.setInt(4, entityHistory.getExtremeSuccess());
                    ps.setInt(5, entityHistory.getHardSuccess());
                    ps.setInt(6, entityHistory.getSuccess());
                    ps.setInt(7, entityHistory.getFailure());
                    ps.setInt(8, entityHistory.getTimes());
                    ps.setInt(9, entityHistory.getMean());

                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "update history set Fumble=?,CriticalSuccess=?,ExtremeSuccess=?,HardSuccess=?,Success=?,Failure=?,times=?,mean=? where qqId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setInt(1, entityHistory.getFumble());
                    ps.setInt(2, entityHistory.getCriticalSuccess());
                    ps.setInt(3, entityHistory.getExtremeSuccess());
                    ps.setInt(4, entityHistory.getHardSuccess());
                    ps.setInt(5, entityHistory.getSuccess());
                    ps.setInt(6, entityHistory.getFailure());
                    ps.setInt(7, entityHistory.getTimes());
                    ps.setInt(8, entityHistory.getMean());
                    ps.setString(9, entityHistory.getQqId());

                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
