package dice.sinanya.db.properties.system;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntitySystemProperties;

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

    public void insertProperties(EntitySystemProperties entitySystemProperties) {
        try (Connection conn = DbUtil.getConnection()) {
            //language=MySQL
            String sql = "replace into systemProperties(botId," +
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
                    "FUMBLE)  VALUES (?," +
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
                ps.setString(2, entitySystemProperties.getBotStart());
                ps.setString(3, entitySystemProperties.getBotAlreadyStart());
                ps.setString(4, entitySystemProperties.getBotStop());
                ps.setString(5, entitySystemProperties.getBotAlreadyStop());
                ps.setString(6, entitySystemProperties.getBotExit());
                ps.setString(7, entitySystemProperties.getBotInfo());
                ps.setString(8, entitySystemProperties.getBookCard());
                ps.setString(9, entitySystemProperties.getBookRp());
                ps.setString(10, entitySystemProperties.getBookKp());
                ps.setString(11, entitySystemProperties.getBookMake());
                ps.setString(12, entitySystemProperties.getManyRollsFormat());
                ps.setString(13, entitySystemProperties.getDiceTimesTooBig());
                ps.setString(14, entitySystemProperties.getSetPropFormat());
                ps.setString(15, entitySystemProperties.getSetHelp());
                ps.setString(16, entitySystemProperties.getNotFoundSkill());
                ps.setString(17, entitySystemProperties.getSetPropSuccess());
                ps.setString(18, entitySystemProperties.getDndInitIsEmtpy());
                ps.setString(19, entitySystemProperties.getClrDndInit());
                ps.setString(20, entitySystemProperties.getNeedKpGroup());
                ps.setString(21, entitySystemProperties.getCantInPrivate());
                ps.setString(22, entitySystemProperties.getOnlyManager());
                ps.setString(23, entitySystemProperties.getAlreadyOpen());
                ps.setString(24, entitySystemProperties.getAlreadyClose());
                ps.setString(25, entitySystemProperties.getNotFoundLog());
                ps.setString(26, entitySystemProperties.getReadLock());
                ps.setString(27, entitySystemProperties.getDeleteOpenLog());
                ps.setString(28, entitySystemProperties.getSanCheck());
                ps.setString(29, entitySystemProperties.getAntagonizeOver());
                ps.setString(30, entitySystemProperties.getAntagonizeFirstSuccess());
                ps.setString(31, entitySystemProperties.getAntagonizeSecondSuccess());
                ps.setString(32, entitySystemProperties.getAntagonizeAllFailed());
                ps.setString(33, entitySystemProperties.getAntagonizeDraw());
                ps.setString(34, entitySystemProperties.getSymptom());
                ps.setString(35, entitySystemProperties.getEnSuccess());
                ps.setString(36, entitySystemProperties.getEnFailed());
                ps.setString(37, entitySystemProperties.getHiddenDice());
                ps.setString(38, entitySystemProperties.getTeamIsEmpty());
                ps.setString(39, entitySystemProperties.getTeamMemberEnIsEmpty());
                ps.setString(40, entitySystemProperties.getAppendLog());
                ps.setString(41, entitySystemProperties.getCreateLog());
                ps.setString(42, entitySystemProperties.getCantEmptyLogName());
                ps.setString(43, entitySystemProperties.getSanCheckFumble());
                ps.setString(44, entitySystemProperties.getSanCheckCriticalSuccess());
                ps.setString(45, entitySystemProperties.getSanCheckSuccess());
                ps.setString(46, entitySystemProperties.getSanCheckFailure());
                ps.setString(47, entitySystemProperties.getCRITICAL_SUCCESS());
                ps.setString(48, entitySystemProperties.getEXTREME_SUCCESS());
                ps.setString(49, entitySystemProperties.getHARD_SUCCESS());
                ps.setString(50, entitySystemProperties.getSUCCESS());
                ps.setString(51, entitySystemProperties.getFAILURE());
                ps.setString(52, entitySystemProperties.getFUMBLE());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }
}