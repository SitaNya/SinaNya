package dice.sinanya.db.log.info;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityLogTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static dice.sinanya.system.MessagesLog.logNameSwitch;

/**
 * 录入log信息
 *
 * @author zhangxiaozhou
 */
public class InsertLogInfo {

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
            e.printStackTrace();
            System.out.println(entityLogTag);
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
            System.out.println(stringBuilder.toString());
        }
    }
}
