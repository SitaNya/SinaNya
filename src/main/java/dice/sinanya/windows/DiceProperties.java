package dice.sinanya.windows;

import dice.sinanya.db.properties.system.InsertProperties;
import dice.sinanya.tools.windows.Frame;
import dice.sinanya.windows.imal.MakeText;

import javax.swing.*;
import java.awt.*;

import static dice.sinanya.system.MessagesLevel.*;
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
public class DiceProperties extends MakeText {
    static JCheckBox jCheckBox;

    public DiceProperties() {
    }

    public static int getLength(String text) {
        return text.length() * 12;
    }

    public void init() {
        JFrame jFrame = new Frame("个性化配置", 1200, 980).init();
//        JPanel botSwitch = new Panel().init(jFrame, "开关", 510, 10, 400, 200);
//        botSwitch.setLayout(new GridLayout(0, 1));

        initText(jFrame);
        save(jFrame);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
    }

    private void initText(JFrame jFrame) {
        JPanel infoPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "骰娘信息", 0, 0, 400, 70);
        infoPanel.setLayout(new GridLayout(0, 2));
        info(infoPanel);

        JPanel botInfoPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "机器人基本信息", 0, 70, 400, 210);
        botInfoPanel.setLayout(new GridLayout(0, 2));
        botInfo(botInfoPanel);

        JPanel cardInfoPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "车卡信息", 0, 280, 400, 140);
        cardInfoPanel.setLayout(new GridLayout(0, 2));
        cardInfo(cardInfoPanel);

        JPanel propInfoPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "人物卡信息", 0, 420, 400, 150);
        propInfoPanel.setLayout(new GridLayout(0, 2));
        propInfo(propInfoPanel);

        JPanel teamPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "小队信息", 0, 570, 400, 100);
        teamPanel.setLayout(new GridLayout(0, 2));
        team(teamPanel);

        JPanel dndInfoPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "dnd信息", 0, 670, 400, 100);
        dndInfoPanel.setLayout(new GridLayout(0, 2));
        dndInfo(dndInfoPanel);


        JPanel logInfoPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "日志信息", 400, 0, 400, 280);
        logInfoPanel.setLayout(new GridLayout(0, 2));
        logInfo(logInfoPanel);

        JPanel sanCheckPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "理智检定信息", 400, 280, 400, 210);
        sanCheckPanel.setLayout(new GridLayout(0, 2));
        sanCheck(sanCheckPanel);

        JPanel otherPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "其余常用信息", 400, 490, 400, 125);
        otherPanel.setLayout(new GridLayout(0, 2));
        other(otherPanel);

        JPanel antagonizeInfoPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "对抗信息", 400, 615, 400, 175);
        antagonizeInfoPanel.setLayout(new GridLayout(0, 2));
        antagonizeInfo(antagonizeInfoPanel);

        JPanel errorInfoPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "其余报错信息", 400, 790, 400, 175);
        errorInfoPanel.setLayout(new GridLayout(0, 2));
        errorInfo(errorInfoPanel);

        JPanel diceReturnPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "骰点返回信息", 800, 0, 400, 965);
        diceReturnPanel.setLayout(new GridLayout(0, 2));
        diceReturn(diceReturnPanel);

    }

    private void save(JFrame jFrame) {
        createButton(jFrame, "保存", 0, 800);
    }

    public void createButton(JFrame jFrame, String text, int x, int y) {
        JButton jButton = new JButton(text);
        jButton.setBounds(x, y, getLength(text) * 4, 100);
        jButton.addActionListener(e -> {
            entitySystemProperties.setBotStart(botStart.getText());
            entitySystemProperties.setBotAlreadyStart(botAlreadyStart.getText());
            entitySystemProperties.setBotStop(botStop.getText());
            entitySystemProperties.setBotAlreadyStop(botAlreadyStop.getText());
            entitySystemProperties.setBotExit(botExit.getText());
            entitySystemProperties.setBotInfo(botInfo.getText());
            entitySystemProperties.setBookCard(bookCard.getText());
            entitySystemProperties.setBookRp(bookRp.getText());
            entitySystemProperties.setBookKp(bookKp.getText());
            entitySystemProperties.setBookMake(bookMake.getText());
            entitySystemProperties.setSetPropFormat(setPropFormat.getText());
            entitySystemProperties.setSetHelp(setHelp.getText());
            entitySystemProperties.setNotFoundSkill(notFoundSkill.getText());
            entitySystemProperties.setSetPropSuccess(setPropSuccess.getText());
            entitySystemProperties.setManyRollsFormat(manyRollsFormat.getText());
            entitySystemProperties.setDiceTimesTooBig(diceTimesTooBig.getText());
            entitySystemProperties.setNeedKpGroup(needKpGroup.getText());
            entitySystemProperties.setCantInPrivate(cantInPrivate.getText());
            entitySystemProperties.setOnlyManager(onlyManager.getText());
            entitySystemProperties.setAlreadyOpen(alreadyOpen.getText());
            entitySystemProperties.setAlreadyClose(alreadyClose.getText());
            entitySystemProperties.setNotFoundLog(notFoundLog.getText());
            entitySystemProperties.setReadLock(readLock.getText());
            entitySystemProperties.setDeleteOpenLog(deleteOpenLog.getText());
            entitySystemProperties.setAppendLog(appendLog.getText());
            entitySystemProperties.setCreateLog(createLog.getText());
            entitySystemProperties.setCantEmptyLogName(cantEmptyLogName.getText());
            entitySystemProperties.setDndInitIsEmtpy(dndInitIsEmtpy.getText());
            entitySystemProperties.setClrDndInit(clrDndInit.getText());
            entitySystemProperties.setAntagonizeOver(antagonizeOver.getText());
            entitySystemProperties.setAntagonizeFirstSuccess(antagonizeFirstSuccess.getText());
            entitySystemProperties.setAntagonizeSecondSuccess(antagonizeSecondSuccess.getText());
            entitySystemProperties.setAntagonizeAllFailed(antagonizeAllFailed.getText());
            entitySystemProperties.setAntagonizeDraw(antagonizeDraw.getText());
            entitySystemProperties.setSanCheck(sanCheck.getText());
            entitySystemProperties.setSymptom(symptom.getText());
            entitySystemProperties.setSanCheckFumble(sanCheckFumble.getText());
            entitySystemProperties.setSanCheckCriticalSuccess(sanCheckCriticalSuccess.getText());
            entitySystemProperties.setSanCheckSuccess(sanCheckSuccess.getText());
            entitySystemProperties.setSanCheckFailure(sanCheckFailure.getText());
            entitySystemProperties.setEnSuccess(enSuccess.getText());
            entitySystemProperties.setEnFailed(enFailed.getText());
            entitySystemProperties.setHiddenDice(hiddenDice.getText());
            entitySystemProperties.setTeamIsEmpty(teamIsEmpty.getText());
            entitySystemProperties.setTeamMemberEnIsEmpty(teamMemberEnIsEmpty.getText());
            entitySystemProperties.setCRITICAL_SUCCESS(criticalSuccess.getText());
            entitySystemProperties.setEXTREME_SUCCESS(extremeSuccess.getText());
            entitySystemProperties.setHARD_SUCCESS(headSuccess.getText());
            entitySystemProperties.setSUCCESS(success.getText());
            entitySystemProperties.setFAILURE(failure.getText());
            entitySystemProperties.setFUMBLE(fumble.getText());
            STR_CRITICAL_SUCCESS.setText(criticalSuccess.getText());
            STR_EXTREME_SUCCESS.setText(extremeSuccess.getText());
            STR_HARD_SUCCESS.setText(headSuccess.getText());
            STR_SUCCESS.setText(success.getText());
            STR_FAILURE.setText(failure.getText());
            STR_FUMBLE.setText(fumble.getText());
            new InsertProperties().insertProperties(entitySystemProperties);
            JOptionPane.showMessageDialog(null, "保存成功");
        });
        jFrame.add(jButton);
    }
}
