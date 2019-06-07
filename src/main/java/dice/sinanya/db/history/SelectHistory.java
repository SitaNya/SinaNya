package dice.sinanya.db.history;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dice.sinanya.system.MessagesHistory.historyList;

public class SelectHistory {
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
            System.out.println(e.getMessage());
        }
    }
}
