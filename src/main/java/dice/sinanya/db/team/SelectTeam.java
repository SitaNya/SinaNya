package dice.sinanya.db.team;

import dice.sinanya.db.tools.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 查询队伍信息
 *
 * @author zhangxiaozhou
 */
public class SelectTeam {
    public ArrayList<String> selectTeamInfo(String groupId) {
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
            System.out.println(e.getMessage());
        }
        if (strQqList != null) {
            return new ArrayList<>(Arrays.asList(strQqList.split(",")));
        } else {
            return null;
        }

    }
}
