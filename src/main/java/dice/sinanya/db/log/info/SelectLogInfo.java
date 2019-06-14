package dice.sinanya.db.log.info;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityLogTag;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 查询log信息
 *
 * @author SitaNya
 */
public class SelectLogInfo {

    private static final Logger Log = LogManager.getLogger(SelectLogInfo.class);

    public SelectLogInfo() {
    }

    public String selectLogInfo(EntityLogTag entityLogTag) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from textLog where groupId=? and logName=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityLogTag.getGroupId());
                ps.setString(2, entityLogTag.getLogName());
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        stringBuilder.append(set.getString("logInfo"));
                        stringBuilder.append("\n");
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return stringBuilder.toString();
    }
}
