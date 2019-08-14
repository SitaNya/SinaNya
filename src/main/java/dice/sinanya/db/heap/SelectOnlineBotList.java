package dice.sinanya.db.heap;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityOtherBotInfo;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.ArrayList;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * 日期: 2019-08-14
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class SelectOnlineBotList {

    public ArrayList<EntityOtherBotInfo> selectOnlineBotList() {
        ArrayList<EntityOtherBotInfo> otherBotInfos=new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from heap";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        EntityOtherBotInfo otherBotInfo = new EntityOtherBotInfo();
                        otherBotInfo.setBotId(set.getString("botId"));
                        otherBotInfo.setBotName(set.getString("botName"));
                        otherBotInfos.add(otherBotInfo);
                    }
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
        return otherBotInfos;
    }

}
