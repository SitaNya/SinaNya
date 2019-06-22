package dice.sinanya.db.system;

import com.forte.qqrobot.component.forhttpapi.HttpConfiguration;
import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityTypeMessages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dice.sinanya.system.MessagesSystem.entityLoginQQInfo;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 录入机器人在某个群内的开关情况
 */
public class InsertBot {
    private static final Logger Log = LogManager.getLogger(InsertBot.class);

    /**
     * 将机器人的开关值插入某个群，true为开启，false为关闭
     *
     * @param groupId   群号
     * @param switchBot 开关
     */
    public void insertBot(long groupId, boolean switchBot) {
        int num = 0;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from switchBot where groupId=? and botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, groupId);
                ps.setString(2,String.valueOf(entityLoginQQInfo.getLoginQQ()));
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
                String sql = "INSERT INTO switchBot(" +
                        "botId," +
                        "groupId," +
                        "switchBot" +
                        ") VALUES(?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, String.valueOf(entityLoginQQInfo.getLoginQQ()));
                    ps.setLong(2, groupId);
                    ps.setBoolean(3, switchBot);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
        } else {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "update switchBot set " +
                        "switchBot=? where groupId=? and botId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setBoolean(1, switchBot);
                    ps.setLong(2, groupId);
                    ps.setString(3,String.valueOf(entityLoginQQInfo.getLoginQQ()));
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }
}
