package dice.sinanya.db.clue;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityClue;
import org.apache.logging.log4j.LogManager;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import java.util.Arrays;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 线索入库类，包含更新、插入、删除、清空
 */
public class InsertClue {



    public InsertClue() {
        //        初始化时不需要参数
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
                ps.setTimestamp(2, entityClue.getDate());
                ps.setString(3, entityClue.getQqId());
                ps.setString(4, info);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
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
                ps.setTimestamp(2, entityClue.getDate());
                ps.setString(3, entityClue.getQqId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
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
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }
}
