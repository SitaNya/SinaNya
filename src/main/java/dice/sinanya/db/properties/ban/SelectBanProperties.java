package dice.sinanya.db.properties.ban;

import dice.sinanya.db.tools.DbUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                        entityBanProperties.setClearGroupByOff(set.getInt("clearGroupByOff"));
                        entityBanProperties.setClearGroup(set.getInt("clearGroup"));
                        entityBanProperties.setAlterFrequentness(set.getInt("alterFrequentness"));
                        entityBanProperties.setBanFrequentness(set.getInt("banFrequentness"));
                        entityBanProperties.setClearGroupByOffInfo(set.getString("clearGroupByOffInfo"));
                        entityBanProperties.setClearGroupInfo(set.getString("clearGroupInfo"));
                        entityBanProperties.setFrequentnessAlterInfo(set.getString("frequentnessAlterInfo"));
                        entityBanProperties.setFrequentnessBanInfo(set.getString("frequentnessBanInfo"));
                        entityBanProperties.setPrometheusPort(set.getInt("prometheusPort"));
                        entityBanProperties.setBanGroupAndUserByFre(set.getBoolean("banGroupAndUserByFre"));
                        entityBanProperties.setBanUserByFre(set.getBoolean("banUserByFre"));
                        entityBanProperties.setAutoInputGroup(set.getBoolean("autoInputGroup"));
                        entityBanProperties.setAutoAddFriends(set.getBoolean("autoAddFriends"));
                        i++;
                    }
                }
                if (i == 0) {
                    new InsertProperties().insertProperties(entityBanProperties);
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }
}
