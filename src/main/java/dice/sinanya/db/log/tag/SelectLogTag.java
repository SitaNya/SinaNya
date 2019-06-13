package dice.sinanya.db.log.tag;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityLogTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static dice.sinanya.system.MessagesLog.logNameSwitch;

/**
 * 查询log开启标签
 *
 * @author zhangxiaozhou
 */
public class SelectLogTag {

    public SelectLogTag() {
    }

    public void flushLogTag() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from tagLog";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        logNameSwitch.put(new EntityLogTag(set.getString("groupId"), set.getString("logName")), set.getBoolean("logSwitch"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
        return tagList;
    }

    public boolean checkOthorLogTrue(String groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select logSwitch from tagLog where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        if (set.getBoolean("logSwitch")) {
                            return false;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String getOthorLogTrue(String groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select logSwitch,logName from tagLog where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1,groupId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        if (set.getBoolean("logSwitch")) {
                            return set.getString("logName");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "未找到";
    }
}
