package dice.sinanya.db.history;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityHistory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dice.sinanya.system.MessagesHistory.historyList;

/**
 * 查询骰点历史
 *
 * @author SitaNya
 */
public class SelectHistory {
    private static final Logger Log = LogManager.getLogger(SelectHistory.class);

    public SelectHistory() {
    }

    public void flushHistory(String qqId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from history where qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qqId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        historyList.put(qqId, new EntityHistory(qqId, set.getInt("Fumble"), set.getInt("CriticalSuccess"), set.getInt("ExtremeSuccess"), set.getInt("HardSuccess"), set.getInt("Success"), set.getInt("Failure"), set.getInt("times"), set.getInt("mean")));
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
