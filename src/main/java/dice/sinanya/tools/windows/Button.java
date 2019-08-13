package dice.sinanya.tools.windows;

import dice.sinanya.db.properties.system.InsertProperties;
import dice.sinanya.windows.imal.MessagesWindows;

import javax.swing.*;

import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
import static dice.sinanya.windows.Tools.getLength;

/**
 * @author SitaNya
 * @date 2019-08-13
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class Button extends MessagesWindows {
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
            new InsertProperties().insertProperties(entitySystemProperties);
            JOptionPane.showMessageDialog(null, "保存成功");
        });
        jFrame.add(jButton);
    }
}
