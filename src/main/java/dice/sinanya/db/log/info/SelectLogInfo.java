package dice.sinanya.db.log.info;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityLogTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询log信息类，只在log get时使用
 */
public class SelectLogInfo {

    private static final Logger Log = LogManager.getLogger(SelectLogInfo.class);

    public SelectLogInfo() {
    }

    /**
     * 根据日志标志对象内的信息查询所有相关日志内容并以换行分割后返回
     *
     * @param entityLogTag 日志标志对象，包含群、日志名
     * @return 符合日志标记对象的所有信息，以换行符分割传出
     */
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
