package dice.sinanya.db.white;

import dice.sinanya.db.tools.DbUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class InputWhiteList {


    /**
     * 将QQ白名单列表入库
     */
    public void insertQqWhiteList(long qq) {
        try (Connection conn = DbUtil.getConnection()) {
            //language=MySQL
            String sql = "INSERT INTO qqWhiteList(botId,qqId) VALUES(?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, CQ.getLoginQQ());
                ps.setLong(2, qq);

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }

    /**
     * 将QQ群白名单列表入库
     */
    public void insertGroupWhiteList(long groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            //language=MySQL
            String sql = "INSERT INTO groupWhiteList(botId,groupId) VALUES(?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, CQ.getLoginQQ());
                ps.setLong(2, groupId);

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }

    /**
     * 移除QQ群白名单
     */
    public void removeGroupWhiteList(long groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            //language=MySQL
            String sql = "delete from groupWhiteList where groupId=? and botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, groupId);
                ps.setLong(2, CQ.getLoginQQ());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }

    /**
     * 移除QQ群白名单
     */
    public void removeQqWhiteList(long qq){
        try (Connection conn = DbUtil.getConnection()) {
            //language=MySQL
            String sql = "delete from qqWhiteList where qqId=? and botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, qq);
                ps.setLong(2, CQ.getLoginQQ());

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }
}
