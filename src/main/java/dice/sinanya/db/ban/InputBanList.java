package dice.sinanya.db.ban;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotBanListInputException;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * 日期: 2019-08-03
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class InputBanList {


    /**
     * 将QQ黑名单列表入库
     */
    public void insertQqBanList(String qq, String reason) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO qqBanList(botId,qqId,reason) VALUES(?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                ps.setString(2, qq);
                ps.setString(3, reason);

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }

    /**
     * 将QQ群黑名单列表入库
     */
    public void insertGroupBanList(String groupId, String reason) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO groupBanList(botId,groupId,reason) VALUES(?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                ps.setString(2, groupId);
                ps.setString(3, reason);

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }

    /**
     * 移除QQ群黑名单
     */
    public boolean removeGroupBanList(String groupId, EntityTypeMessages entityTypeMessages) throws NotBanListInputException {
        boolean remove = false;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from groupBanList where groupId=? and botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                ps.setString(2, String.valueOf(CQ.getLoginQQ()));
                ps.executeUpdate();
                remove = true;
            }
        } catch (SQLException e) {
            throw new NotBanListInputException(entityTypeMessages);
        }
        return remove;
    }

    /**
     * 移除QQ群黑名单
     */
    public boolean removeQqBanList(String qq, EntityTypeMessages entityTypeMessages) throws NotBanListInputException {
        boolean remove = false;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from qqBanList where qqId=? and botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qq);
                ps.setLong(2, CQ.getLoginQQ());

                ps.executeUpdate();
                remove = true;
            }
        } catch (SQLException e) {
            throw new NotBanListInputException(entityTypeMessages);
        }
        return remove;
    }

    /**
     * 查询其它录入人
     */
    public String selectOthorInputBanQq(String qq, EntityTypeMessages entityTypeMessages) throws NotBanListInputException {
        String inputBot = "";
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select botId from qqBanList where qqId=? ";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qq);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        inputBot = set.getString("botId");
                    }
                }
            }
        } catch (SQLException e) {
            throw new NotBanListInputException(entityTypeMessages);
        }
        return inputBot;
    }

    /**
     * 查询其它录入人
     */
    public String selectOthorInputBanGroup(String groupId, EntityTypeMessages entityTypeMessages) throws NotBanListInputException {
        String inputBot = "";
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select botId from groupBanList where groupId=? ";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        inputBot = set.getString("botId");
                    }
                }
            }
        } catch (SQLException e) {
            throw new NotBanListInputException(entityTypeMessages);
        }
        return inputBot;
    }
}
