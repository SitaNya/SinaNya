package dice.sinanya.db.log.info;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityLogTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static dice.sinanya.system.MessagesLog.LOG_NAME_SWITCH;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 录入log信息类，这里是实际的log内容而不是log是否开启的标签
 */
public class InsertLogInfo {
    private static final Logger Log = LogManager.getLogger(InsertLogInfo.class);

    public InsertLogInfo() {
    }

    /**
     * 将某个群的某个日志的某一条信息插入到数据库中
     *
     * @param entityLogTag 日志标志对象，包含群、日志名
     * @param info         信息，通常是一句话
     */
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
            for (Map.Entry<EntityLogTag, Boolean> mapEntry : LOG_NAME_SWITCH.entrySet()) {
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
