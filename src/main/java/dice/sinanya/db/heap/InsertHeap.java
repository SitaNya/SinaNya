package dice.sinanya.db.heap;

import dice.sinanya.db.tools.DbUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityBanProperties;
import static dice.sinanya.tools.getinfo.GetTime.getNowString;
import static dice.sinanya.tools.getinfo.GetTime.getTime;

/**
 * @author SitaNya
 * 日期: 2019-08-09
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class InsertHeap {


    /**
     * 将QQ黑名单列表入库
     */
    public void updateHeap() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "replace into heap(botId,botName,time,enable,botMaster)  VALUES (?,?,?,?,?);";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                ps.setString(2, CQ.getLoginNick());
                ps.setTimestamp(3, getTime(getNowString()));
                ps.setBoolean(4, entityBanProperties.isHeap());
                ps.setString(5, entityBanProperties.getMaster());

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }
}
