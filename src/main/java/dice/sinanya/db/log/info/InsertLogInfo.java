package dice.sinanya.db.log.info;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityLogTag;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.system.RolesInfo;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static dice.sinanya.system.MessagesLog.logNameSwitch;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.GetTime.getNowString;
import static dice.sinanya.tools.GetTime.getTime;
import static dice.sinanya.tools.RoleInfo.getRoleInfoByQQ;

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
            StringBuffer stringBuffer = new StringBuffer();
            for (Map.Entry<EntityLogTag, Boolean> mapEntry : logNameSwitch.entrySet()) {
                stringBuffer.append("groupId")
                        .append(":")
                        .append(mapEntry.getKey().getGroupId())
                        .append("\t")
                        .append("logName")
                        .append(mapEntry.getKey().getLogName())
                        .append("\n");
            }
            System.out.println(stringBuffer.toString());
        }
    }
}
