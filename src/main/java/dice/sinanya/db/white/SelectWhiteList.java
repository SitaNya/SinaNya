package dice.sinanya.db.white;

import dice.sinanya.db.tools.DbUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesBanList.*;

/**
 * @author SitaNya
 * 日期: 2019-08-07
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class SelectWhiteList {


    /**
     * 读取数据库中的骰点历史信息到缓存
     */
    public void flushQqWhiteListFromDataBase() {
        qqWhiteList.clear();
        try (Connection conn = DbUtil.getConnection()) {
            //language=MySQL
            String sql = "select * from qqWhiteList where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1,CQ.getLoginQQ());
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        qqWhiteList.add(set.getLong("qqId"));
                    }
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }

    /**
     * 读取数据库中的骰点历史信息到缓存
     */
    public void flushGroupWhiteListFromDataBase() {
        groupWhiteList.clear();
        try (Connection conn = DbUtil.getConnection()) {
            //language=MySQL
            String sql = "select * from groupWhiteList where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1,CQ.getLoginQQ());
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        groupWhiteList.add(set.getLong("groupId"));
                    }
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }
}
