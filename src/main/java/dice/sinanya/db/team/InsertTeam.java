package dice.sinanya.db.team;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityTeamInfo;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 录入队伍信息
 *
 * @author zhangxiaozhou
 */
public class InsertTeam {

    public void deleteGroup(String group) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from team where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, group);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteGroup(String group, ArrayList<String> qqList) {
        try (Connection conn = DbUtil.getConnection()) {
            if (qqList == null) {
                String sql = "delete from team where groupId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, group);
                    ps.executeUpdate();
                }
            } else {
                String sql = "update team set qqList=? where groupId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, StringUtils.join(qqList, ","));
                    ps.setString(2, group);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertTeamInfo(EntityTeamInfo entityTeamInfo, boolean add) {
        int num = 0;
        ArrayList<String> qqChangeList = entityTeamInfo.getQqList();
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (num == 0) {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "INSERT INTO team(" +
                        "groupId," +
                        "qqList" +
                        ") VALUES(?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, entityTeamInfo.getGroup());
                    ps.setString(2, StringUtils.join(entityTeamInfo.getQqList(), ","));
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "update team set " +
                        "qqList=? where groupId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    Set<String> middleHashSet = new HashSet<>(qqList);
                    ArrayList<String> afterHashSetList = new ArrayList<>(middleHashSet);

                    ps.setString(1, StringUtils.join(afterHashSetList, ","));
                    ps.setString(2, entityTeamInfo.getGroup());
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
