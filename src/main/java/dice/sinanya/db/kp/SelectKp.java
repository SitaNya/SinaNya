package dice.sinanya.db.kp;

import dice.sinanya.db.tools.DbUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dice.sinanya.system.MessagesKP.KpGroup;

/**
 * 查询kp主群
 *
 * @author SitaNya
 */
public class SelectKp {
    private static final Logger Log = LogManager.getLogger(SelectKp.class);

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
            Log.error(e.getMessage(), e);
        }
    }
}
