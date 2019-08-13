package dice.sinanya.db.team;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityQqAndGroup;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesTeamEn.TEAM_EN;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询队伍QQ号列表，这些QQ号会用到角色逻辑中以获取角色信息
 */
public class SelectTeam {


    /**
     * 从数据库中刷新幕间成长的缓存到teamEn静态变量
     */
    public void flushTeamEnFromDatabase() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from teamEn";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        TEAM_EN.put(new EntityQqAndGroup(set.getString("groupId"), set.getString("qq")), new ArrayList<>(Arrays.asList(set.getString("enSkill").split(","))));
                    }
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }

    /**
     * 查询某个群中队伍的成员QQ号列表
     *
     * @param groupId 群号
     * @return 成员列表
     */
    public List<String> selectTeamInfo(String groupId) {
        String strQqList = null;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from team where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        strQqList = set.getString("qqList");
                    }
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
        if (strQqList != null) {
            return new ArrayList<>(Arrays.asList(strQqList.split(",")));
        } else {
            return new ArrayList<>();
        }

    }
}
