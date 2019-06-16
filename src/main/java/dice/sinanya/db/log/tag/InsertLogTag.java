package dice.sinanya.db.log.tag;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityLogTag;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
 * 类说明: 录入log开启标签，不包含log日志内容
 */
public class InsertLogTag {

    private static final Logger Log = LogManager.getLogger(InsertLogTag.class);

    public InsertLogTag() {
    }

    /**
     * 插入一条日志开关标记
     *
     * @param entityLogTag 日志标志对象，包含群、日志名
     * @param logSwitch    开关，true为开，false为关
     */
    public void insertLogTag(EntityLogTag entityLogTag, boolean logSwitch) {
        int num = 0;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from tagLog where groupId=? and logName=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityLogTag.getGroupId());
                ps.setString(2, entityLogTag.getLogName());
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
                String sql = "INSERT INTO tagLog(" +
                        "groupId, logName,logSwitch) VALUES(?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, entityLogTag.getGroupId());
                    ps.setString(2, entityLogTag.getLogName());
                    ps.setBoolean(3, logSwitch);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
        } else {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "update tagLog set logSwitch=? where groupId=? and logName=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setBoolean(1, logSwitch);
                    ps.setString(2, entityLogTag.getGroupId());
                    ps.setString(3, entityLogTag.getLogName());
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
        }

    }

    /**
     * 删除数据库中所有关于某个群中某个日志的标记位，并删除其所有的日志信息
     *
     * @param entityLogTag 日志标志对象，包含群、日志名
     */
    public void deleteLog(EntityLogTag entityLogTag) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from tagLog where groupId=? and logName=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityLogTag.getGroupId());
                ps.setString(2, entityLogTag.getLogName());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }

        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from textLog where groupId=? and logName=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityLogTag.getGroupId());
                ps.setString(2, entityLogTag.getLogName());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
