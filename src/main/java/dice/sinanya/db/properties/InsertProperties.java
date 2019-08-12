package dice.sinanya.db.properties;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityProperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class InsertProperties {

    public void insertProperties(EntityProperties entityProperties) {
        try (Connection conn = DbUtil.getConnection()) {
                String sql = "replace into properties(botId," +
                        "botStart," +
                        "botAlreadyStart," +
                        "botStop," +
                        "botAlreadyStop," +
                        "botExit," +
                        "botInfo," +
                        "bookCard," +
                        "bookRp," +
                        "bookKp," +
                        "bookMake," +
                        "manyRollsFormat," +
                        "diceTimesTooBig," +
                        "setPropFormat," +
                        "setHelp," +
                        "NotFoundSkill," +
                        "setPropSuccess," +
                        "dndInitIsEmtpy," +
                        "clrDndInit," +
                        "needKpGroup," +
                        "cantInPrivate," +
                        "onlyManager," +
                        "alreadyOpen," +
                        "alreadyClose," +
                        "notFoundLog," +
                        "readLock," +
                        "deleteOpenLog," +
                        "sanCheck," +
                        "antagonizeOver," +
                        "antagonizeFirstSuccess," +
                        "antagonizeSecondSuccess," +
                        "antagonizeAllFailed," +
                        "antagonizeDraw," +
                        "symptom," +
                        "enSuccess," +
                        "enFailed," +
                        "hiddenDice," +
                        "teamIsEmpty," +
                        "teamMemberEnIsEmpty," +
                        "appendLog," +
                        "createLog," +
                        "CantEmptyLogName," +
                        "sanCheckFumble," +
                        "sanCheckCriticalSuccess," +
                        "sanCheckSuccess," +
                        "sanCheckFailure," +
                        "CRITICAL_SUCCESS," +
                        "EXTREME_SUCCESS," +
                        "HARD_SUCCESS," +
                        "SUCCESS," +
                        "FAILURE," +
                        "FUMBLE," +
                        "notMaster," +
                        "master," +
                        "PrometheusPort," +
                        "heap," +
                        "dbPassword," +
                        "cloudBan," +
                        "banListInputNotId)  VALUES (?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?," +
                        "?);";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                    ps.setString(2,entityProperties.getBotStart());
                    ps.setString(3,entityProperties.getBotAlreadyStart());
                    ps.setString(4,entityProperties.getBotStop());
                    ps.setString(5,entityProperties.getBotAlreadyStop());
                    ps.setString(6,entityProperties.getBotExit());
                    ps.setString(7,entityProperties.getBotInfo());
                    ps.setString(8,entityProperties.getBookCard());
                    ps.setString(9,entityProperties.getBookRp());
                    ps.setString(10,entityProperties.getBookKp());
                    ps.setString(11,entityProperties.getBookMake());
                    ps.setString(12,entityProperties.getManyRollsFormat());
                    ps.setString(13,entityProperties.getDiceTimesTooBig());
                    ps.setString(14,entityProperties.getSetPropFormat());
                    ps.setString(15,entityProperties.getSetHelp());
                    ps.setString(16,entityProperties.getNotFoundSkill());
                    ps.setString(17,entityProperties.getSetPropSuccess());
                    ps.setString(18,entityProperties.getDndInitIsEmtpy());
                    ps.setString(19,entityProperties.getClrDndInit());
                    ps.setString(20,entityProperties.getNeedKpGroup());
                    ps.setString(21,entityProperties.getCantInPrivate());
                    ps.setString(22,entityProperties.getOnlyManager());
                    ps.setString(23,entityProperties.getAlreadyOpen());
                    ps.setString(24,entityProperties.getAlreadyClose());
                    ps.setString(25,entityProperties.getNotFoundLog());
                    ps.setString(26,entityProperties.getReadLock());
                    ps.setString(27,entityProperties.getDeleteOpenLog());
                    ps.setString(28,entityProperties.getSanCheck());
                    ps.setString(29,entityProperties.getAntagonizeOver());
                    ps.setString(30,entityProperties.getAntagonizeFirstSuccess());
                    ps.setString(31,entityProperties.getAntagonizeSecondSuccess());
                    ps.setString(32,entityProperties.getAntagonizeAllFailed());
                    ps.setString(33,entityProperties.getAntagonizeDraw());
                    ps.setString(34,entityProperties.getSymptom());
                    ps.setString(35,entityProperties.getEnSuccess());
                    ps.setString(36,entityProperties.getEnFailed());
                    ps.setString(37,entityProperties.getHiddenDice());
                    ps.setString(38,entityProperties.getTeamIsEmpty());
                    ps.setString(39,entityProperties.getTeamMemberEnIsEmpty());
                    ps.setString(40,entityProperties.getAppendLog());
                    ps.setString(41,entityProperties.getCreateLog());
                    ps.setString(42,entityProperties.getCantEmptyLogName());
                    ps.setString(43,entityProperties.getSanCheckFumble());
                    ps.setString(44,entityProperties.getSanCheckCriticalSuccess());
                    ps.setString(45,entityProperties.getSanCheckSuccess());
                    ps.setString(46,entityProperties.getSanCheckFailure());
                    ps.setString(47,entityProperties.getCRITICAL_SUCCESS());
                    ps.setString(48,entityProperties.getEXTREME_SUCCESS());
                    ps.setString(49,entityProperties.getHARD_SUCCESS());
                    ps.setString(50,entityProperties.getSUCCESS());
                    ps.setString(51,entityProperties.getFAILURE());
                    ps.setString(52,entityProperties.getFUMBLE());
                    ps.setString(53,entityProperties.getNotMaster());
                    ps.setString(54,entityProperties.getMaster());
                    ps.setString(55,entityProperties.getPrometheusPort());
                    ps.setString(56,entityProperties.getHeap());
                    ps.setString(57,entityProperties.getDbPassword());
                    ps.setString(58,entityProperties.getCloudBan());
                    ps.setString(59,entityProperties.getBanListInputNotId());
                    ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }
}