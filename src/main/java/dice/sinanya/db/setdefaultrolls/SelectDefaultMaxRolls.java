package dice.sinanya.db.setdefaultrolls;

import dice.sinanya.db.tools.DbUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesRollMaxValue.ROLL_MAX_VALUE;

/**
 * @author SitaNya
 * 日期: 2019-06-17
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 默认骰点上限查询类
 * <p>
 * 直接刷新到静态变量
 */
public class SelectDefaultMaxRolls {


    /**
     * 从数据库刷写默认骰最大值到静态变量
     */
    public void flushMaxRollsFromDatabase() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from maxRolls";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        ROLL_MAX_VALUE.put(set.getString("groupId"), set.getInt("maxRolls"));
                    }
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }
}
