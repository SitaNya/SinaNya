package dice.sinanya.db.ban;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotBanListInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dice.sinanya.system.MessagesLoginInfo.ENTITY_LOGINQQ_INFO;

/**
 * @author SitaNya
 * 日期: 2019-08-03
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class InputBanList {
    private static final Logger Log = LogManager.getLogger(InputBanList.class.getName());

    /**
     * 将QQ黑名单列表入库
     */
    public void insertQqBanList(String qq, String reason) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO qqBanList(botId,qqId,reason) VALUES(?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()));
                ps.setString(2, qq);
                ps.setString(3, reason);

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * 将QQ群黑名单列表入库
     */
    public void insertGroupBanList(String groupId, String reason) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO groupBanList(botId,groupId,reason) VALUES(?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()));
                ps.setString(2, groupId);
                ps.setString(3, reason);

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * 移除QQ群黑名单
     */
    public void removeGroupBanList(String groupId, EntityTypeMessages entityTypeMessages) throws NotBanListInputException {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from groupBanList where groupId=? and botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                ps.setString(2, String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new NotBanListInputException(entityTypeMessages);
        }
    }

    /**
     * 移除QQ群黑名单
     */
    public void removeQqBanList(String qq, EntityTypeMessages entityTypeMessages) throws NotBanListInputException {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from qqBanList where qqId=? and botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qq);
                ps.setString(2, String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new NotBanListInputException(entityTypeMessages);
        }
    }
}
