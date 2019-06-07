package dice.sinanya.db.log.info;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityLogTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectLogInfo {

    public SelectLogInfo() {
    }

    public String selectLogInfo(EntityLogTag entityLogTag) {
        StringBuffer stringBuffer = new StringBuffer();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from textLog where groupId=? and logName=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityLogTag.getGroupId());
                ps.setString(2, entityLogTag.getLogName());
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        stringBuffer.append(set.getString("logInfo"));
                        stringBuffer.append("\n");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println();
        }
        return stringBuffer.toString();
    }
}
