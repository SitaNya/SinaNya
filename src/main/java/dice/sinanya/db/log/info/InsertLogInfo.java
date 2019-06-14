package dice.sinanya.db.log.info;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityLogTag;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static dice.sinanya.system.MessagesLog.logNameSwitch;

/**
 * 录入log信息
 *
 * @author SitaNya
 */
public class InsertLogInfo {
    private static final Logger Log = LogManager.getLogger(InsertLogInfo.class);

    public InsertLogInfo() {
    }

    public void insertLogTag(EntityLogTag entityLogTag, String info) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO textLog(" +
                    "groupId, logName, logInfo) VALUES(?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityLogTag.getGroupId());
                ps.setString(2, entityLogTag.getLogName());
                ps.setString(3, info);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<EntityLogTag, Boolean> mapEntry : logNameSwitch.entrySet()) {
                stringBuilder.append("groupId")
                        .append(":")
                        .append(mapEntry.getKey().getGroupId())
                        .append("\t")
                        .append("logName")
                        .append(mapEntry.getKey().getLogName())
                        .append("\n");
            }
            Log.debug(stringBuilder.toString());
        }
    }
}
