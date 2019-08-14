package dice.sinanya.windows.imal;

import dice.sinanya.tools.windows.Lable;
import dice.sinanya.tools.windows.Text;

import javax.swing.*;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;

/**
 * @author SitaNya
 * @date 2019-08-13
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class MakeText extends MessagesWindows {
    Text text = new Text();
    Lable lable = new Lable();

    public void info(JPanel jPanel) {
        lable.createLable(jPanel, "你当前登录的骰娘QQ为:\t");
        lable.createLable(jPanel, String.valueOf(CQ.getLoginQQ()));

        lable.createLable(jPanel, "你当前登录的骰娘昵称为:\t");
        lable.createLable(jPanel, CQ.getLoginNick());
    }

    public void botInfo(JPanel jPanel) {
        lable.createLable(jPanel, "开启");
        botStart = text.createText(jPanel);
        botStart.setText(entitySystemProperties.getBotStart());

        lable.createLable(jPanel, "已开启");
        botAlreadyStart = text.createText(jPanel);
        botAlreadyStart.setText(entitySystemProperties.getBotAlreadyStart());

        lable.createLable(jPanel, "关闭");
        botStop = text.createText(jPanel);
        botStop.setText(entitySystemProperties.getBotStop());

        lable.createLable(jPanel, "已关闭");
        botAlreadyStop = text.createText(jPanel);
        botAlreadyStop.setText(entitySystemProperties.getBotAlreadyStop());

        lable.createLable(jPanel, "退群");
        botExit = text.createText(jPanel);
        botExit.setText(entitySystemProperties.getBotExit());

        lable.createLable(jPanel, "信息");
        botInfo = text.createText(jPanel);
        botInfo.setText(entitySystemProperties.getBotInfo());
    }

    public void cardInfo(JPanel jPanel) {
        lable.createLable(jPanel, "人物卡");
        bookCard = text.createText(jPanel);
        bookCard.setText(entitySystemProperties.getBookCard());

        lable.createLable(jPanel, "RP问答");
        bookRp = text.createText(jPanel);
        bookRp.setText(entitySystemProperties.getBookRp());

        lable.createLable(jPanel, "规则书");
        bookKp = text.createText(jPanel);
        bookKp.setText(entitySystemProperties.getBookKp());

        lable.createLable(jPanel, "车卡指南");
        bookMake = text.createText(jPanel);
        bookMake.setText(entitySystemProperties.getBookMake());
    }

    public void propInfo(JPanel jPanel) {
        lable.createLable(jPanel, "录入格式有误");
        setPropFormat = text.createText(jPanel);
        setPropFormat.setText(entitySystemProperties.getSetPropFormat());

        lable.createLable(jPanel, "录入格式帮助");
        setHelp = text.createText(jPanel);
        setHelp.setText(entitySystemProperties.getSetHelp());

        lable.createLable(jPanel, "未找到技能值");
        notFoundSkill = text.createText(jPanel);
        notFoundSkill.setText(entitySystemProperties.getNotFoundSkill());

        lable.createLable(jPanel, "属性设置成功");
        setPropSuccess = text.createText(jPanel);
        setPropSuccess.setText(entitySystemProperties.getSetPropSuccess());
    }

    public void errorInfo(JPanel jPanel) {
        lable.createLable(jPanel, "多重骰点命令有误(ral)");
        manyRollsFormat = text.createText(jPanel);
        manyRollsFormat.setText(entitySystemProperties.getManyRollsFormat());

        lable.createLable(jPanel, "骰点次数过多(100000d2)");
        diceTimesTooBig = text.createText(jPanel);
        diceTimesTooBig.setText(entitySystemProperties.getDiceTimesTooBig());

        lable.createLable(jPanel, "未设置kp群");
        needKpGroup = text.createText(jPanel);
        needKpGroup.setText(entitySystemProperties.getNeedKpGroup());

        lable.createLable(jPanel, "非私聊命令");
        cantInPrivate = text.createText(jPanel);
        cantInPrivate.setText(entitySystemProperties.getCantInPrivate());

        lable.createLable(jPanel, "仅管理员或群主可用");
        onlyManager = text.createText(jPanel);
        onlyManager.setText(entitySystemProperties.getOnlyManager());
    }

    public void logInfo(JPanel jPanel) {
        lable.createLable(jPanel, "日志已开启");
        alreadyOpen = text.createText(jPanel);
        alreadyOpen.setText(entitySystemProperties.getAlreadyOpen());

        lable.createLable(jPanel, "日志已关闭");
        alreadyClose = text.createText(jPanel);
        alreadyClose.setText(entitySystemProperties.getAlreadyClose());

        lable.createLable(jPanel, "日志未找到");
        notFoundLog = text.createText(jPanel);
        notFoundLog.setText(entitySystemProperties.getNotFoundLog());

        lable.createLable(jPanel, "获取日志队列堵塞");
        readLock = text.createText(jPanel);
        readLock.setText(entitySystemProperties.getReadLock());

        lable.createLable(jPanel, "无法删除已开启的日志");
        deleteOpenLog = text.createText(jPanel);
        deleteOpenLog.setText(entitySystemProperties.getDeleteOpenLog());

        lable.createLable(jPanel, "追加日志");
        appendLog = text.createText(jPanel);
        appendLog.setText(entitySystemProperties.getAppendLog());

        lable.createLable(jPanel, "创建日志");
        createLog = text.createText(jPanel);
        createLog.setText(entitySystemProperties.getCreateLog());

        lable.createLable(jPanel, "不可使用空日志名");
        cantEmptyLogName = text.createText(jPanel);
        cantEmptyLogName.setText(entitySystemProperties.getCantEmptyLogName());
    }

    public void dndInfo(JPanel jPanel) {
        lable.createLable(jPanel, "先攻列表已经为空");
        dndInitIsEmtpy = text.createText(jPanel);
        dndInitIsEmtpy.setText(entitySystemProperties.getDndInitIsEmtpy());

        lable.createLable(jPanel, "请空先攻列表");
        clrDndInit = text.createText(jPanel);
        clrDndInit.setText(entitySystemProperties.getClrDndInit());
    }

    public void antagonizeInfo(JPanel jPanel) {
        lable.createLable(jPanel, "对抗结束");
        antagonizeOver = text.createText(jPanel);
        antagonizeOver.setText(entitySystemProperties.getAntagonizeOver());

        lable.createLable(jPanel, "先手胜利");
        antagonizeFirstSuccess = text.createText(jPanel);
        antagonizeFirstSuccess.setText(entitySystemProperties.getAntagonizeFirstSuccess());

        lable.createLable(jPanel, "后手胜利");
        antagonizeSecondSuccess = text.createText(jPanel);
        antagonizeSecondSuccess.setText(entitySystemProperties.getAntagonizeSecondSuccess());

        lable.createLable(jPanel, "全部失败");
        antagonizeAllFailed = text.createText(jPanel);
        antagonizeAllFailed.setText(entitySystemProperties.getAntagonizeAllFailed());

        lable.createLable(jPanel, "平手");
        antagonizeDraw = text.createText(jPanel);
        antagonizeDraw.setText(entitySystemProperties.getAntagonizeDraw());
    }

    public void sanCheck(JPanel jPanel) {
        lable.createLable(jPanel, "sc格式有误");
        sanCheck = text.createText(jPanel);
        sanCheck.setText(entitySystemProperties.getSanCheck());

        lable.createLable(jPanel, "疯狂");
        symptom = text.createText(jPanel);
        symptom.setText(entitySystemProperties.getSymptom());

        lable.createLable(jPanel, "sc大失败");
        sanCheckFumble = text.createText(jPanel);
        sanCheckFumble.setText(entitySystemProperties.getSanCheckFumble());

        lable.createLable(jPanel, "sc大成功");
        sanCheckCriticalSuccess = text.createText(jPanel);
        sanCheckCriticalSuccess.setText(entitySystemProperties.getSanCheckCriticalSuccess());

        lable.createLable(jPanel, "sc成功");
        sanCheckSuccess = text.createText(jPanel);
        sanCheckSuccess.setText(entitySystemProperties.getSanCheckSuccess());

        lable.createLable(jPanel, "sc失败");
        sanCheckFailure = text.createText(jPanel);
        sanCheckFailure.setText(entitySystemProperties.getSanCheckFailure());
    }

    public void other(JPanel jPanel) {
        lable.createLable(jPanel, "en成功");
        enSuccess = text.createText(jPanel);
        enSuccess.setText(entitySystemProperties.getEnSuccess());

        lable.createLable(jPanel, "en失败");
        enFailed = text.createText(jPanel);
        enFailed.setText(entitySystemProperties.getEnFailed());

        lable.createLable(jPanel, "暗骰");
        hiddenDice = text.createText(jPanel);
        hiddenDice.setText(entitySystemProperties.getHiddenDice());
    }

    public void team(JPanel jPanel) {
        lable.createLable(jPanel, "当前小队为空");
        teamIsEmpty = text.createText(jPanel);
        teamIsEmpty.setText(entitySystemProperties.getTeamIsEmpty());

        lable.createLable(jPanel, "当前小队EN列表为空");
        teamMemberEnIsEmpty = text.createText(jPanel);
        teamMemberEnIsEmpty.setText(entitySystemProperties.getTeamMemberEnIsEmpty());
    }

    public void diceReturn(JPanel jPanel) {
        lable.createLable(jPanel, "大成功");
        criticalSuccess = text.createTextArea(jPanel);
        criticalSuccess.setText(entitySystemProperties.getCRITICAL_SUCCESS());

        lable.createLable(jPanel, "极难成功");
        extremeSuccess = text.createTextArea(jPanel);
        extremeSuccess.setText(entitySystemProperties.getEXTREME_SUCCESS());

        lable.createLable(jPanel, "困难成功");
        headSuccess = text.createTextArea(jPanel);
        headSuccess.setText(entitySystemProperties.getHARD_SUCCESS());

        lable.createLable(jPanel, "成功");
        success = text.createTextArea(jPanel);
        success.setText(entitySystemProperties.getSUCCESS());

        lable.createLable(jPanel, "失败");
        failure = text.createTextArea(jPanel);
        failure.setText(entitySystemProperties.getFAILURE());

        lable.createLable(jPanel, "大失败");
        fumble = text.createTextArea(jPanel);
        fumble.setText(entitySystemProperties.getFUMBLE());
    }
}
