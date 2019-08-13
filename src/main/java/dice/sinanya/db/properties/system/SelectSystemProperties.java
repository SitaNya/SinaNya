package dice.sinanya.db.properties.system;

import dice.sinanya.db.tools.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.initMessagesSystemProperties;


/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询KP主群类，刷写到静态变量中，只在静态变量中找不到时才需要使用
 */
public class SelectSystemProperties {


    public SelectSystemProperties() {
        //        初始化时无需逻辑
    }

    /**
     * 刷新kp主群设定到静态变量中，只有静态变量中找不到某人的kp主群记录时才会使用
     */
    public void flushProperties() {
        try (Connection conn = DbUtil.getConnection()) {
            int i = 0;
            //language=MySQL
            String sql = "select * from systemProperties where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        entitySystemProperties.setBotStart(set.getString("botStart"));
                        entitySystemProperties.setBotAlreadyStart(set.getString("botAlreadyStart"));
                        entitySystemProperties.setBotStop(set.getString("botStop"));
                        entitySystemProperties.setBotAlreadyStop(set.getString("botAlreadyStop"));
                        entitySystemProperties.setBotExit(set.getString("botExit"));
                        entitySystemProperties.setBotInfo(set.getString("botInfo"));
                        entitySystemProperties.setBookCard(set.getString("bookCard"));
                        entitySystemProperties.setBookRp(set.getString("bookRp"));
                        entitySystemProperties.setBookKp(set.getString("bookKp"));
                        entitySystemProperties.setBookMake(set.getString("bookMake"));
                        entitySystemProperties.setManyRollsFormat(set.getString("manyRollsFormat"));
                        entitySystemProperties.setDiceTimesTooBig(set.getString("diceTimesTooBig"));
                        entitySystemProperties.setSetPropFormat(set.getString("setPropFormat"));
                        entitySystemProperties.setSetHelp(set.getString("setHelp"));
                        entitySystemProperties.setNotFoundSkill(set.getString("NotFoundSkill"));
                        entitySystemProperties.setSetPropSuccess(set.getString("setPropSuccess"));
                        entitySystemProperties.setDndInitIsEmtpy(set.getString("dndInitIsEmtpy"));
                        entitySystemProperties.setClrDndInit(set.getString("clrDndInit"));
                        entitySystemProperties.setNeedKpGroup(set.getString("needKpGroup"));
                        entitySystemProperties.setCantInPrivate(set.getString("cantInPrivate"));
                        entitySystemProperties.setOnlyManager(set.getString("onlyManager"));
                        entitySystemProperties.setAlreadyOpen(set.getString("alreadyOpen"));
                        entitySystemProperties.setAlreadyClose(set.getString("alreadyClose"));
                        entitySystemProperties.setNotFoundLog(set.getString("notFoundLog"));
                        entitySystemProperties.setReadLock(set.getString("readLock"));
                        entitySystemProperties.setDeleteOpenLog(set.getString("deleteOpenLog"));
                        entitySystemProperties.setSanCheck(set.getString("sanCheck"));
                        entitySystemProperties.setAntagonizeOver(set.getString("antagonizeOver"));
                        entitySystemProperties.setAntagonizeFirstSuccess(set.getString("antagonizeFirstSuccess"));
                        entitySystemProperties.setAntagonizeSecondSuccess(set.getString("antagonizeSecondSuccess"));
                        entitySystemProperties.setAntagonizeAllFailed(set.getString("antagonizeAllFailed"));
                        entitySystemProperties.setAntagonizeDraw(set.getString("antagonizeDraw"));
                        entitySystemProperties.setSymptom(set.getString("symptom"));
                        entitySystemProperties.setEnSuccess(set.getString("enSuccess"));
                        entitySystemProperties.setEnFailed(set.getString("enFailed"));
                        entitySystemProperties.setHiddenDice(set.getString("hiddenDice"));
                        entitySystemProperties.setTeamIsEmpty(set.getString("teamIsEmpty"));
                        entitySystemProperties.setTeamMemberEnIsEmpty(set.getString("teamMemberEnIsEmpty"));
                        entitySystemProperties.setAppendLog(set.getString("appendLog"));
                        entitySystemProperties.setCreateLog(set.getString("createLog"));
                        entitySystemProperties.setCantEmptyLogName(set.getString("CantEmptyLogName"));
                        entitySystemProperties.setSanCheckFumble(set.getString("sanCheckFumble"));
                        entitySystemProperties.setSanCheckCriticalSuccess(set.getString("sanCheckCriticalSuccess"));
                        entitySystemProperties.setSanCheckSuccess(set.getString("sanCheckSuccess"));
                        entitySystemProperties.setSanCheckFailure(set.getString("sanCheckFailure"));
                        entitySystemProperties.setCRITICAL_SUCCESS(set.getString("CRITICAL_SUCCESS"));
                        entitySystemProperties.setEXTREME_SUCCESS(set.getString("EXTREME_SUCCESS"));
                        entitySystemProperties.setHARD_SUCCESS(set.getString("HARD_SUCCESS"));
                        entitySystemProperties.setSUCCESS(set.getString("SUCCESS"));
                        entitySystemProperties.setFAILURE(set.getString("FAILURE"));
                        entitySystemProperties.setFUMBLE(set.getString("FUMBLE"));
                        i++;
                    }
                }
                if (i == 0) {
                    initMessagesSystemProperties();
                    new InsertProperties().insertProperties(entitySystemProperties);
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }
}
