package dice.sinanya.db.properties;

import dice.sinanya.db.tools.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.entityProperties;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.initMessagesSystem;


/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询KP主群类，刷写到静态变量中，只在静态变量中找不到时才需要使用
 */
public class SelectProperties {


    public SelectProperties() {
        //        初始化时无需逻辑
    }

    /**
     * 刷新kp主群设定到静态变量中，只有静态变量中找不到某人的kp主群记录时才会使用
     */
    public void flushProperties() {
        try (Connection conn = DbUtil.getConnection()) {
          int i=0;
            String sql = "select * from properties where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, String.valueOf(CQ.getLoginQQ()));
                try (ResultSet set = ps.executeQuery()) {

                    while (set.next()) {
                        entityProperties.setBotStart(set.getString("botStart"));
                        entityProperties.setBotAlreadyStart(set.getString("botAlreadyStart"));
                        entityProperties.setBotStop(set.getString("botStop"));
                        entityProperties.setBotAlreadyStop(set.getString("botAlreadyStop"));
                        entityProperties.setBotExit(set.getString("botExit"));
                        entityProperties.setBotInfo(set.getString("botInfo"));
                        entityProperties.setBookCard(set.getString("bookCard"));
                        entityProperties.setBookRp(set.getString("bookRp"));
                        entityProperties.setBookKp(set.getString("bookKp"));
                        entityProperties.setBookMake(set.getString("bookMake"));
                        entityProperties.setManyRollsFormat(set.getString("manyRollsFormat"));
                        entityProperties.setDiceTimesTooBig(set.getString("diceTimesTooBig"));
                        entityProperties.setSetPropFormat(set.getString("setPropFormat"));
                        entityProperties.setSetHelp(set.getString("setHelp"));
                        entityProperties.setNotFoundSkill(set.getString("NotFoundSkill"));
                        entityProperties.setSetPropSuccess(set.getString("setPropSuccess"));
                        entityProperties.setDndInitIsEmtpy(set.getString("dndInitIsEmtpy"));
                        entityProperties.setClrDndInit(set.getString("clrDndInit"));
                        entityProperties.setNeedKpGroup(set.getString("needKpGroup"));
                        entityProperties.setCantInPrivate(set.getString("cantInPrivate"));
                        entityProperties.setOnlyManager(set.getString("onlyManager"));
                        entityProperties.setAlreadyOpen(set.getString("alreadyOpen"));
                        entityProperties.setAlreadyClose(set.getString("alreadyClose"));
                        entityProperties.setNotFoundLog(set.getString("notFoundLog"));
                        entityProperties.setReadLock(set.getString("readLock"));
                        entityProperties.setDeleteOpenLog(set.getString("deleteOpenLog"));
                        entityProperties.setSanCheck(set.getString("sanCheck"));
                        entityProperties.setAntagonizeOver(set.getString("antagonizeOver"));
                        entityProperties.setAntagonizeFirstSuccess(set.getString("antagonizeFirstSuccess"));
                        entityProperties.setAntagonizeSecondSuccess(set.getString("antagonizeSecondSuccess"));
                        entityProperties.setAntagonizeAllFailed(set.getString("antagonizeAllFailed"));
                        entityProperties.setAntagonizeDraw(set.getString("antagonizeDraw"));
                        entityProperties.setSymptom(set.getString("symptom"));
                        entityProperties.setEnSuccess(set.getString("enSuccess"));
                        entityProperties.setEnFailed(set.getString("enFailed"));
                        entityProperties.setHiddenDice(set.getString("hiddenDice"));
                        entityProperties.setTeamIsEmpty(set.getString("teamIsEmpty"));
                        entityProperties.setTeamMemberEnIsEmpty(set.getString("teamMemberEnIsEmpty"));
                        entityProperties.setAppendLog(set.getString("appendLog"));
                        entityProperties.setCreateLog(set.getString("createLog"));
                        entityProperties.setCantEmptyLogName(set.getString("CantEmptyLogName"));
                        entityProperties.setSanCheckFumble(set.getString("sanCheckFumble"));
                        entityProperties.setSanCheckCriticalSuccess(set.getString("sanCheckCriticalSuccess"));
                        entityProperties.setSanCheckSuccess(set.getString("sanCheckSuccess"));
                        entityProperties.setSanCheckFailure(set.getString("sanCheckFailure"));
                        entityProperties.setCRITICAL_SUCCESS(set.getString("CRITICAL_SUCCESS"));
                        entityProperties.setEXTREME_SUCCESS(set.getString("EXTREME_SUCCESS"));
                        entityProperties.setHARD_SUCCESS(set.getString("HARD_SUCCESS"));
                        entityProperties.setSUCCESS(set.getString("SUCCESS"));
                        entityProperties.setFAILURE(set.getString("FAILURE"));
                        entityProperties.setFUMBLE(set.getString("FUMBLE"));
                        entityProperties.setNotMaster(set.getString("notMaster"));
                        entityProperties.setMaster(set.getString("master"));
                        entityProperties.setPrometheusPort(set.getString("PrometheusPort"));
                        entityProperties.setHeap(set.getString("heap"));
                        entityProperties.setDbPassword(set.getString("dbPassword"));
                        entityProperties.setCloudBan(set.getString("cloudBan"));
                        entityProperties.setBanListInputNotId(set.getString("banListInputNotId"));
                        i++;
                    }
                }
                if (i==0){
                    initMessagesSystem();
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), Arrays.toString(e.getStackTrace()));
        }
    }
}
