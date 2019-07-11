package dice.sinanya.db.team;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityQqAndGroup;
import dice.sinanya.entity.EntityTeamInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 录入、删除、更新队伍信息，这里其实只包含QQ账号列表而不包含成员状态。QQ账号会拿到角色信息相关逻辑中再获得角色信息。
 */
public class InsertTeam {
    private static final Logger Log = LogManager.getLogger(InsertTeam.class);

    /**
     * 将teamEn静态变量遍历后更新到数据库中
     *
     * @param mapEntry 遍历后的teamEn静态变量元素
     */
    public void saveTeamEnToDatabase(Map.Entry<EntityQqAndGroup, ArrayList<String>> mapEntry) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "update teamEn set enSkill=? where groupId=? and qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, StringUtils.join(mapEntry.getValue(), ","));
                ps.setString(2, mapEntry.getKey().getGroupId());
                ps.setString(3, mapEntry.getKey().getQqId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }


    /**
     * 删除某人的En标记
     *
     * @param qqId    qq号
     * @param groupId 群号
     */
    public void deleteTeamEnToDatabase(String qqId, String groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from teamEn where groupId=? and qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qqId);
                ps.setString(2, groupId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * 清空某个队伍的En标记
     *
     * @param groupId 群号
     */
    public void clrTeamEnToDatabase(String groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from teamEn where groupId=? ";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * 删除某个群的所有队伍信息
     *
     * @param groupId 群号
     */
    public void deleteGroup(String groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from team where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * 删除某个群队伍中的某一个人,如果传入的qqList为null，则删除整个小队
     *
     * @param groupId 群号
     */
    private void deleteGroup(String groupId, ArrayList<String> qqList) {
        try (Connection conn = DbUtil.getConnection()) {
            if (qqList == null) {
                String sql = "delete from team where groupId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, groupId);
                    ps.executeUpdate();
                }
            } else {
                String sql = "update team set qqList=? where groupId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, StringUtils.join(qqList, ","));
                    ps.setString(2, groupId);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * 修改某个群中的队伍信息
     *
     * @param entityTeamInfo 队伍信息类，包含了成员QQ号列表和群号
     * @param add            true为添加队员，false为删除队员
     */
    public void changeTeamInfo(EntityTeamInfo entityTeamInfo, boolean add) {
        int num = 0;
        ArrayList<String> qqChangeList = (ArrayList<String>) entityTeamInfo.getQqList();
        ArrayList<String> qqList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from team where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityTeamInfo.getGroup());
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        num++;
                        qqList = new ArrayList<>(Arrays.asList(set.getString("qqList").split(",")));
                        if (add) {
                            qqList.addAll(qqChangeList);
                        } else {
                            qqList.removeAll(qqChangeList);
                            deleteGroup(entityTeamInfo.getGroup(), qqList);
                        }
                    }
                }
            }

            if (num == 0) {
                sql = "INSERT INTO team(" +
                        "groupId," +
                        "qqList" +
                        ") VALUES(?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, entityTeamInfo.getGroup());
                    ps.setString(2, StringUtils.join(entityTeamInfo.getQqList(), ","));
                    ps.executeUpdate();
                }
            } else {
                sql = "update team set " +
                        "qqList=? where groupId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    Set<String> middleHashSet = new HashSet<>(qqList);
                    ArrayList<String> afterHashSetList = new ArrayList<>(middleHashSet);

                    ps.setString(1, StringUtils.join(afterHashSetList, ","));
                    ps.setString(2, entityTeamInfo.getGroup());
                    ps.executeUpdate();
                }

            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
