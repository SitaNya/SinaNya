package dice.sinanya.db.history;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityHistory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dice.sinanya.system.MessagesHistory.HISTORY_LIST;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;


/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询骰点历史类，这里不会返回对象而是直接刷写到静态变量中，只有在静态变量中获取不到时才会使用
 */
public class SelectHistory {
    private static final Logger Log = LogManager.getLogger(SelectHistory.class);

    public SelectHistory() {
        //        初始化时无需逻辑
    }

    /**
     * 读取数据库中的骰点历史信息到缓存
     */
    public void flushHistoryFromDatabase() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from history where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        EntityHistory history = new EntityHistory(set.getString("qqId"));
                        history.setFumble(set.getInt("Fumble"));
                        history.setCriticalSuccess(set.getInt("CriticalSuccess"));
                        history.setExtremeSuccess(set.getInt("ExtremeSuccess"));
                        history.setHardSuccess(set.getInt("HardSuccess"));
                        history.setSuccess(set.getInt("Success"));
                        history.setFailure(set.getInt("Failure"));
                        history.setTimes(set.getInt("times"));
                        history.setMean(set.getInt("mean"));
                        HISTORY_LIST.put(set.getString("qqId"), history);
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
