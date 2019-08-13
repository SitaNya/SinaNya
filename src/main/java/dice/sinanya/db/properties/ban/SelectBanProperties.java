package dice.sinanya.db.properties.ban;

import dice.sinanya.db.tools.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityBanProperties;


/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询KP主群类，刷写到静态变量中，只在静态变量中找不到时才需要使用
 */
public class SelectBanProperties {

    public SelectBanProperties() {
        //        初始化时无需逻辑
    }

    /**
     * 刷新kp主群设定到静态变量中，只有静态变量中找不到某人的kp主群记录时才会使用
     */
    public void flushProperties() {
        try (Connection conn = DbUtil.getConnection()) {
            int i = 0;
            //language=MySQL
            String sql = "select * from banProperties where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        entityBanProperties.setCloudBan(set.getBoolean("cloudBan"));
                        entityBanProperties.setPrometheus(set.getBoolean("Prometheus"));
                        entityBanProperties.setHeap(set.getBoolean("heap"));
                        entityBanProperties.setMaster(set.getString("master"));
                        entityBanProperties.setBanListInputNotId(set.getString("banListInputNotId"));
                        entityBanProperties.setNotMaster(set.getString("notMaster"));
                        entityBanProperties.setDoSomthingForBanUserInGroup(set.getBoolean("doSomthingForBanUserInGroup"));
                        entityBanProperties.setIgnoreBanUser(set.getBoolean("ignoreBanUser"));
                        entityBanProperties.setLeaveByBanUser(set.getBoolean("leaveByBanUser"));
                        entityBanProperties.setLeaveGroupByBan(set.getBoolean("leaveGroupByBan"));
                        entityBanProperties.setBanGroupBecauseBan(set.getBoolean("banGroupBecauseBan"));
                        entityBanProperties.setBanGroupBecauseReduce(set.getBoolean("banGroupBecauseReduce"));
                        entityBanProperties.setBanUserBecauseReduce(set.getBoolean("banUserBecauseReduce"));
                        entityBanProperties.setAddGroup(set.getString("addGroup"));
                        entityBanProperties.setAddFriend(set.getString("addFriend"));
                        entityBanProperties.setRefuseGroupByBan(set.getString("refuseGroupByBan"));
                        entityBanProperties.setRefuseFriendByBan(set.getString("refuseFriendByBan"));
                        entityBanProperties.setWhiteGroup(set.getString("whiteGroup"));
                        entityBanProperties.setWhiteUser(set.getString("whiteUser"));
                        entityBanProperties.setNotBanListInput(set.getString("notBanListInput"));
                        i++;
                        CQ.logInfo("测试","从数据库中获取数据");
                    }
                }
                if (i == 0) {
                    CQ.logInfo("测试","初始化数据");
                    new InsertProperties().insertProperties(entityBanProperties);
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }
}
