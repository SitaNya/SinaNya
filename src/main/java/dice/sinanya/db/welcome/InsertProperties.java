package dice.sinanya.db.welcome;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityWelcome;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 录入KP主群类
 */
public class InsertProperties {
    public void insertProperties(long groupId, EntityWelcome entityWelcome) {
        try (Connection conn = DbUtil.getConnection()) {
            //language=MySQL
            String sql = "replace into welcome(botId,groupId,enable,text)  VALUES (?,?,?,?);";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                ps.setLong(2, groupId);
                ps.setBoolean(3, entityWelcome.isEnable());
                ps.setString(4, entityWelcome.getText());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }
}