package dice.sinanya.db.clue;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityClue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dice.sinanya.tools.RoleChoose.checkRoleChooseExistByQQ;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByQQ;

public class SelectClue {

    public SelectClue() {
    }

    public String selectClue(EntityClue entityClue) {
        StringBuilder stringBuffer = new StringBuilder();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from clue where groupId=? and createTime=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityClue.getGroupId());
                ps.setDate(2, entityClue.getDate());
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        stringBuffer.append("于 ")
                                .append(set.getDate("createTime"))
                                .append(" 由 ");
                        if (checkRoleChooseExistByQQ(entityClue.getQqId())) {
                            stringBuffer.append(getRoleChooseByQQ(set.getString("qqId")));
                        } else {
                            stringBuffer.append(set.getString("qqId"));
                        }
                        stringBuffer.append(" 记录的线索:\t")
                                .append(set.getString("info"))
                                .append("\n");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println();
        }
        if (stringBuffer.toString().equals("")) {
            return stringBuffer.toString();
        } else {
            return stringBuffer.substring(0, stringBuffer.length() - 1);
        }
    }
}
