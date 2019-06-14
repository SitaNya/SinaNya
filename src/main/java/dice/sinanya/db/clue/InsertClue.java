package dice.sinanya.db.clue;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityClue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 录入线索集
 *
 * @author SitaNya
 */
public class InsertClue {

    private static final Logger Log = LogManager.getLogger(InsertClue.class);

    public InsertClue() {
    }

    /**
     * 将某条线索插入数据库，entityClue包含群号、时间和插入者QQ号，属于主键。
     *
     * @param entityClue 线索对象
     * @param info       线索信息
     */
    public void insertClue(EntityClue entityClue, String info) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO clue(" +
                    "groupId, createTime, qqId,info) VALUES(?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityClue.getGroupId());
                ps.setDate(2, entityClue.getDate());
                ps.setString(3, entityClue.getQqId());
                ps.setString(4, info);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(),e);
        }
    }

    /**
     * 从数据库中删除某一条线索
     *
     * @param entityClue 线索对象
     */
    public void deleteClue(EntityClue entityClue) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from clue where " +
                    "groupId=? and createTime=? and qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityClue.getGroupId());
                ps.setDate(2, entityClue.getDate());
                ps.setString(3, entityClue.getQqId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(),e);
        }
    }

    /**
     * 清理群内全部线索
     *
     * @param groupId 群号
     */
    public void clrClue(String groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from clue where " +
                    "groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(),e);
        }
    }
}
