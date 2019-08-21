package dice.sinanya.db.properties.game;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityGame;
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

    public void insertProperties(EntityGame entityGame) {
        try (Connection conn = DbUtil.getConnection()) {
            //language=MySQL
            String sql = "replace into gameProperties(botId,jrrpSwitch,jrrpInfo,welcomeSwitch,botList,deck)  VALUES (?,?,?,?,?,?);";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                ps.setBoolean(2, entityGame.isJrrpSwitch());
                ps.setString(3, entityGame.getJrrpInfo());
                ps.setBoolean(4, entityGame.isWelcomeSwitch());
                ps.setBoolean(5, entityGame.isBotList());
                ps.setBoolean(6, entityGame.isDeck());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }
}