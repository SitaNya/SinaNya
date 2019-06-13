package dice.sinanya.db.kp;

import dice.sinanya.db.tools.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dice.sinanya.system.MessagesKP.KpGroup;

/**
 * 查询kp主群
 *
 * @author zhangxiaozhou
 */
public class SelectKp {
    public SelectKp() {
    }

    public void flushKp() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from kp";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        KpGroup.put(set.getString("qqId"), set.getString("groupId"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
