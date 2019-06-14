package dice.sinanya.db.history;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityHistory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 录入骰点历史
 *
 * @author SitaNya
 */
public class InsertHistory {

    private static final Logger Log = LogManager.getLogger(InsertHistory.class);

    /**
     * 将历史骰点信息插入数据库，不过这个语句是由定时器每分钟调用一次的
     * 如果觉得卡的话可以去调整dice.sinanya.listener.InputHistoryToDatabase里的间隔时间
     * 先查询是否存在条目，不存在则插入，存在则更新
     *
     * @param entityHistory 历史对象
     */
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
            Log.error(e.getMessage(), e);
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
                Log.error(e.getMessage(), e);
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
                Log.error(e.getMessage(), e);
            }
        }
    }
}
