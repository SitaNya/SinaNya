package dice.sinanya.db.log.tag;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityLogTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static dice.sinanya.system.MessagesLog.LOG_NAME_SWITCH;
import static dice.sinanya.system.MessagesLog.LOG_SWITCH_FOR_GROUP;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询log开启状况，包含当前群内是否有log开启，是哪个log，目前某个log是否处于开启状态
 */
public class SelectLogTag {
    private static final Logger Log = LogManager.getLogger(SelectLogTag.class);


    public SelectLogTag() {
    }

    /**
     * 刷新日志标记位对应的开关值到静态变量中
     */
    public void flushLogTagFromDatabase() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from tagLog";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        LOG_NAME_SWITCH.put(new EntityLogTag(set.getString("groupId"), set.getString("logName")), set.getBoolean("logSwitch"));
                        if (set.getBoolean("logSwitch")) {
                            LOG_SWITCH_FOR_GROUP.put(set.getString("groupId"), set.getBoolean("logSwitch"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * 获取某个群中所有的日志信息列表
     *
     * @param groupId 群号
     * @return 日志名称列表
     */
    public ArrayList<String> getTagList(String groupId) {
        ArrayList<String> tagList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select logName from tagLog where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        tagList.add(set.getString("logName"));
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return tagList;
    }

    /**
     * 获取当前已开启的log日志的名称
     *
     * @param groupId 群号
     * @return 当前已开启的log日志的名称
     */
    public String getOthorLogTrue(String groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select logSwitch,logName from tagLog where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        if (set.getBoolean("logSwitch")) {
                            return set.getString("logName");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return "未找到";
    }
}
