package dice.sinanya.db.kp;

import dice.sinanya.db.tools.DbUtil;
import org.apache.logging.log4j.LogManager;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import java.util.Arrays;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 录入KP主群类
 */
public class InsertKp {



    /**
     * 将kp主群设定插入或更新到数据库中
     *
     * @param qqId    QQ号
     * @param groupId 群号
     */
    public void insertKp(String qqId, String groupId) {
        int num = 0;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from kp where qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, qqId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        num++;
                    }
                }
            }


            if (num == 0) {
                sql = "INSERT INTO kp(qqId,groupId) VALUES(?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, qqId);
                    ps.setString(2, groupId);

                    ps.executeUpdate();
                }
            } else {
                sql = "update kp set groupId=? where qqId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setString(1, groupId);
                    ps.setString(2, qqId);

                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }
}