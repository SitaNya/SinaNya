package dice.sinanya.windows.imal;

import dice.sinanya.tools.windows.Lable;
import dice.sinanya.tools.windows.Text;

import javax.swing.*;

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
        lable.createLable(jPanel, "12345");

        lable.createLable(jPanel, "你当前登录的骰娘昵称为:\t");
        lable.createLable(jPanel, "aaa");
    }

    public void botInfo(JPanel jPanel) {
        lable.createLable(jPanel, "开启");
        botStart = text.createText(jPanel);

        lable.createLable(jPanel, "已开启");
        botAlreadyStart = text.createText(jPanel);

        lable.createLable(jPanel, "关闭");
        botStop = text.createText(jPanel);

        lable.createLable(jPanel, "已关闭");
        botAlreadyStop = text.createText(jPanel);

        lable.createLable(jPanel, "退群");
        botExit = text.createText(jPanel);

        lable.createLable(jPanel, "信息");
        botInfo = text.createText(jPanel);
    }

    public void cardInfo(JPanel jPanel) {
        lable.createLable(jPanel, "人物卡");
        bookCard = text.createText(jPanel);

        lable.createLable(jPanel, "RP问答");
        bookRp = text.createText(jPanel);

        lable.createLable(jPanel, "规则书");
        bookKp = text.createText(jPanel);

        lable.createLable(jPanel, "车卡指南");
        bookMake = text.createText(jPanel);
    }

    public void propInfo(JPanel jPanel) {
        lable.createLable(jPanel, "录入格式有误");
        setPropFormat = text.createText(jPanel);

        lable.createLable(jPanel, "录入格式帮助");
        setHelp = text.createText(jPanel);

        lable.createLable(jPanel, "未找到技能值");
        notFoundSkill = text.createText(jPanel);

        lable.createLable(jPanel, "属性设置成功");
        setPropSuccess = text.createText(jPanel);
    }

    public void errorInfo(JPanel jPanel) {
        lable.createLable(jPanel, "多重骰点命令有误(ral)");
        manyRollsFormat = text.createText(jPanel);

        lable.createLable(jPanel, "骰点次数过多(100000d2)");
        diceTimesTooBig = text.createText(jPanel);

        lable.createLable(jPanel, "未设置kp群");
        needKpGroup = text.createText(jPanel);

        lable.createLable(jPanel, "非私聊命令");
        cantInPrivate = text.createText(jPanel);

        lable.createLable(jPanel, "仅管理员或群主可用");
        onlyManager = text.createText(jPanel);
    }

    public void logInfo(JPanel jPanel) {
        lable.createLable(jPanel, "日志已开启");
        alreadyOpen = text.createText(jPanel);

        lable.createLable(jPanel, "日志已关闭");
        alreadyClose = text.createText(jPanel);

        lable.createLable(jPanel, "日志未找到");
        notFoundLog = text.createText(jPanel);

        lable.createLable(jPanel, "获取日志队列堵塞");
        readLock = text.createText(jPanel);

        lable.createLable(jPanel, "无法删除已开启的日志");
        deleteOpenLog = text.createText(jPanel);

        lable.createLable(jPanel, "开启日志");
        appendLog = text.createText(jPanel);

        lable.createLable(jPanel, "追加日志");
        createLog = text.createText(jPanel);

        lable.createLable(jPanel, "不可使用空日志名");
        cantEmptyLogName = text.createText(jPanel);
    }

    public void dndInfo(JPanel jPanel) {
        lable.createLable(jPanel, "先攻列表已经为空");
        dndInitIsEmtpy = text.createText(jPanel);

        lable.createLable(jPanel, "请空先攻列表");
        clrDndInit = text.createText(jPanel);
    }

    public void antagonizeInfo(JPanel jPanel) {
        lable.createLable(jPanel, "对抗结束");
        antagonizeOver = text.createText(jPanel);

        lable.createLable(jPanel, "先手胜利");
        antagonizeFirstSuccess = text.createText(jPanel);

        lable.createLable(jPanel, "后手胜利");
        antagonizeSecondSuccess = text.createText(jPanel);

        lable.createLable(jPanel, "全部失败");
        antagonizeAllFailed = text.createText(jPanel);

        lable.createLable(jPanel, "平手");
        antagonizeDraw = text.createText(jPanel);
    }

    public void sanCheck(JPanel jPanel) {
        lable.createLable(jPanel, "sc格式有误");
        sanCheck = text.createText(jPanel);

        lable.createLable(jPanel, "疯狂");
        symptom = text.createText(jPanel);

        lable.createLable(jPanel, "sc大失败");
        sanCheckFumble = text.createText(jPanel);

        lable.createLable(jPanel, "sc大成功");
        sanCheckCriticalSuccess = text.createText(jPanel);

        lable.createLable(jPanel, "sc成功");
        sanCheckSuccess = text.createText(jPanel);

        lable.createLable(jPanel, "sc失败");
        sanCheckFailure = text.createText(jPanel);
    }

    public void other(JPanel jPanel) {
        lable.createLable(jPanel, "en成功");
        enSuccess = text.createText(jPanel);

        lable.createLable(jPanel, "en失败");
        enFailed = text.createText(jPanel);

        lable.createLable(jPanel, "暗骰");
        hiddenDice = text.createText(jPanel);
    }

    public void team(JPanel jPanel) {
        lable.createLable(jPanel, "当前小队为空");
        teamIsEmpty = text.createText(jPanel);

        lable.createLable(jPanel, "当前小队EN列表为空");
        teamMemberEnIsEmpty = text.createText(jPanel);
    }

    public void diceReturn(JPanel jPanel) {
        lable.createLable(jPanel, "大成功");
        criticalSuccess = text.createTextArea(jPanel);

        lable.createLable(jPanel, "极难成功");
        extremeSuccess = text.createTextArea(jPanel);

        lable.createLable(jPanel, "困难成功");
        headSuccess = text.createTextArea(jPanel);

        lable.createLable(jPanel, "成功");
        success = text.createTextArea(jPanel);

        lable.createLable(jPanel, "失败");
        failure = text.createTextArea(jPanel);

        lable.createLable(jPanel, "大失败");
        fumble = text.createTextArea(jPanel);
    }
}
