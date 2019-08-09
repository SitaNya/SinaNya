package dice.sinanya.db.heap;

import dice.sinanya.db.tools.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dice.sinanya.system.MessagesLoginInfo.ENTITY_LOGINQQ_INFO;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.MESSAGES_SYSTEM;
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
    private static final Logger Log = LogManager.getLogger(InsertHeap.class.getName());

    /**
     * 将QQ黑名单列表入库
     */
    public void updateHeap() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "replace into heap(botId,time,enable,botMaster)  VALUES (?,?,?,?);";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(ENTITY_LOGINQQ_INFO.getLoginQQ()));
                ps.setTimestamp(2, getTime(getNowString()));
                ps.setBoolean(3, Boolean.parseBoolean(MESSAGES_SYSTEM.get("heap")));
                ps.setString(4,MESSAGES_SYSTEM.get("master"));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
