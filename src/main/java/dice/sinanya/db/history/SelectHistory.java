package dice.sinanya.db.history;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityHistory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dice.sinanya.system.MessagesHistory.historyList;


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
    }

    /**
     * 将某个QQ号的历史信息刷新，通常只有在静态变量中找不到某人的记录时才会使用
     * @param qqId 需要刷新的QQ号
     */
    public void flushHistory(String qqId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from history where qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qqId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        historyList.put(qqId, new EntityHistory(qqId, set.getInt("Fumble"), set.getInt("CriticalSuccess"), set.getInt("ExtremeSuccess"), set.getInt("HardSuccess"), set.getInt("Success"), set.getInt("Failure"), set.getInt("times"), set.getInt("mean")));
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
