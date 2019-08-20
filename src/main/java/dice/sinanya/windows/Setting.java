/*
 * Created by JFormDesigner on Mon Aug 19 17:26:35 CST 2019
 */

package dice.sinanya.windows;

import dice.sinanya.db.properties.ban.InsertProperties;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesLevel.*;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityBanProperties;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;

/**
 * @author unknown
 */
public class Setting extends JFrame {
    public Setting() {
        initComponents();
        initData();
    }

    private void saveSystem(MouseEvent e) {
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
        new dice.sinanya.db.properties.system.InsertProperties().insertProperties(entitySystemProperties);
        dialog1.setVisible(true);
    }

    private void saveBan(MouseEvent e) {
        entityBanProperties.setCloudBan(cloudBan.isSelected());
        entityBanProperties.setPrometheus(Prometheus.isSelected());
        entityBanProperties.setHeap(heap.isSelected());
        entityBanProperties.setMaster(master.getText());
        entityBanProperties.setBanListInputNotId(banListInputNotId.getText());
        entityBanProperties.setNotMaster(notMaster.getText());
        entityBanProperties.setIgnoreBanUser(ignoreBanUser.isSelected());
        entityBanProperties.setLeaveByBanUser(leaveByBanUser.isSelected());
        entityBanProperties.setLeaveGroupByBan(leaveGroupByBan.isSelected());
        entityBanProperties.setBanGroupBecauseBan(banGroupBecauseBan.isSelected());
        entityBanProperties.setBanGroupBecauseReduce(banGroupBecauseReduce.isSelected());
        entityBanProperties.setBanUserBecauseReduce(banUserBecauseReduce.isSelected());
        entityBanProperties.setAddGroup(addGroup.getText());
        entityBanProperties.setAddFriend(addFriend.getText());
        entityBanProperties.setRefuseGroupByBan(refuseGroupByBan.getText());
        entityBanProperties.setRefuseFriendByBan(refuseFriendByBan.getText());
        entityBanProperties.setClearGroup(Integer.parseInt(clearGroup.getText()));
        entityBanProperties.setClearGroupByOff(Integer.parseInt(clearGroupByOff.getText()));
        entityBanProperties.setAlterFrequentness(Integer.parseInt(alterFrequentness.getText()));
        entityBanProperties.setBanFrequentness(Integer.parseInt(banFrequentness.getText()));
        entityBanProperties.setPrometheusPort(Integer.parseInt(prometheusPort.getText()));
        entityBanProperties.setClearGroupByOffInfo(clearGroupByOffInfo.getText());
        entityBanProperties.setClearGroupInfo(clearGroupInfo.getText());
        entityBanProperties.setFrequentnessAlterInfo(frequentnessAlterInfo.getText());
        entityBanProperties.setFrequentnessBanInfo(frequentnessBanInfo.getText());
        new InsertProperties().insertProperties(entityBanProperties);
        dialog1.setVisible(true);
    }

    private void windowsWindowClosing(WindowEvent e) {
        windows.setVisible(false);
    }

    private void button1MouseClicked(MouseEvent e) {
        dialog1.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        windows = new Frame();
        tabContain = new JTabbedPane();
        setting = new JPanel();
        panel3 = new JPanel();
        save = new JButton();
        infoPanel6 = new JPanel();
        qqText6 = new JLabel();
        nickText6 = new JLabel();
        qqValue6 = new JLabel();
        nickValue6 = new JLabel();
        scrollPane11 = new JScrollPane();
        textPane1 = new JTextPane();
        scrollPane1 = new JScrollPane();
        panel2 = new JPanel();
        panel6 = new JPanel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        setPropFormat = new JTextField();
        setHelp = new JTextField();
        notFoundSkill = new JTextField();
        setPropSuccess = new JTextField();
        panel13 = new JPanel();
        label45 = new JLabel();
        label46 = new JLabel();
        label47 = new JLabel();
        enSuccess = new JTextField();
        enFailed = new JTextField();
        hiddenDice = new JTextField();
        panel5 = new JPanel();
        botStartLable = new JLabel();
        botAlreadyStartLable = new JLabel();
        botStopLable = new JLabel();
        botAlreadyStopLable = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        botStart = new JTextField();
        botAlreadyStart = new JTextField();
        botStop = new JTextField();
        botAlreadyStop = new JTextField();
        botExit = new JTextField();
        botInfo = new JTextField();
        panel12 = new JPanel();
        label39 = new JLabel();
        label40 = new JLabel();
        label41 = new JLabel();
        label42 = new JLabel();
        label43 = new JLabel();
        label44 = new JLabel();
        sanCheck = new JTextField();
        symptom = new JTextField();
        sanCheckCriticalSuccess = new JTextField();
        sanCheckFumble = new JTextField();
        sanCheckSuccess = new JTextField();
        sanCheckFailure = new JTextField();
        panel9 = new JPanel();
        label24 = new JLabel();
        label25 = new JLabel();
        label26 = new JLabel();
        label27 = new JLabel();
        label28 = new JLabel();
        label29 = new JLabel();
        createLog = new JTextField();
        appendLog = new JTextField();
        alreadyOpen = new JTextField();
        alreadyClose = new JTextField();
        notFoundLog = new JTextField();
        cantEmptyLogName = new JTextField();
        label30 = new JLabel();
        label31 = new JLabel();
        deleteOpenLog = new JTextField();
        readLock = new JTextField();
        panel8 = new JPanel();
        label19 = new JLabel();
        label20 = new JLabel();
        label21 = new JLabel();
        label22 = new JLabel();
        label23 = new JLabel();
        manyRollsFormat = new JTextField();
        diceTimesTooBig = new JTextField();
        needKpGroup = new JTextField();
        cantInPrivate = new JTextField();
        onlyManager = new JTextField();
        panel10 = new JPanel();
        label32 = new JLabel();
        label33 = new JLabel();
        dndInitIsEmtpy = new JTextField();
        clrDndInit = new JTextField();
        panel14 = new JPanel();
        label48 = new JLabel();
        label49 = new JLabel();
        teamIsEmpty = new JTextField();
        teamMemberEnIsEmpty = new JTextField();
        panel7 = new JPanel();
        label15 = new JLabel();
        label16 = new JLabel();
        label17 = new JLabel();
        label18 = new JLabel();
        bookCard = new JTextField();
        bookRp = new JTextField();
        bookKp = new JTextField();
        bookMake = new JTextField();
        panel11 = new JPanel();
        label34 = new JLabel();
        label35 = new JLabel();
        label36 = new JLabel();
        label37 = new JLabel();
        antagonizeOver = new JTextField();
        antagonizeFirstSuccess = new JTextField();
        antagonizeSecondSuccess = new JTextField();
        antagonizeAllFailed = new JTextField();
        label38 = new JLabel();
        antagonizeDraw = new JTextField();
        panel1 = new JPanel();
        panel4 = new JPanel();
        save2 = new JButton();
        infoPanel5 = new JPanel();
        qqText5 = new JLabel();
        nickText5 = new JLabel();
        qqValue5 = new JLabel();
        nickValue5 = new JLabel();
        scrollPane14 = new JScrollPane();
        textPane2 = new JTextPane();
        panel15 = new JPanel();
        label1 = new JLabel();
        scrollPane2 = new JScrollPane();
        criticalSuccess = new JTextArea();
        label2 = new JLabel();
        scrollPane3 = new JScrollPane();
        extremeSuccess = new JTextArea();
        label3 = new JLabel();
        scrollPane4 = new JScrollPane();
        headSuccess = new JTextArea();
        label4 = new JLabel();
        label50 = new JLabel();
        label51 = new JLabel();
        scrollPane5 = new JScrollPane();
        success = new JTextArea();
        scrollPane6 = new JScrollPane();
        failure = new JTextArea();
        scrollPane7 = new JScrollPane();
        fumble = new JTextArea();
        clean = new JPanel();
        monitorPanel2 = new JPanel();
        promethusPortText2 = new JLabel();
        prometheusPort = new JTextField();
        Prometheus = new JCheckBox();
        heap = new JCheckBox();
        setMaster = new JPanel();
        masterText = new JLabel();
        notMasterInfoText = new JLabel();
        master = new JTextField();
        scrollPane8 = new JScrollPane();
        notMaster = new JTextArea();
        panel16 = new JPanel();
        infoPanel3 = new JPanel();
        qqText3 = new JLabel();
        nickText3 = new JLabel();
        qqValue3 = new JLabel();
        nickValue3 = new JLabel();
        save3 = new JButton();
        scrollPane15 = new JScrollPane();
        textPane3 = new JTextPane();
        panel17 = new JPanel();
        label52 = new JLabel();
        label53 = new JLabel();
        label54 = new JLabel();
        label55 = new JLabel();
        clearGroupByOff = new JTextField();
        clearGroupByOffInfo = new JTextField();
        clearGroup = new JTextField();
        clearGroupInfo = new JTextField();
        panel18 = new JPanel();
        label56 = new JLabel();
        label57 = new JLabel();
        label58 = new JLabel();
        label59 = new JLabel();
        alterFrequentness = new JTextField();
        frequentnessAlterInfo = new JTextField();
        banFrequentness = new JTextField();
        frequentnessBanInfo = new JTextField();
        banGroupAndUserByFre = new JRadioButton();
        banUserByFre = new JRadioButton();
        panel19 = new JPanel();
        panel20 = new JPanel();
        save4 = new JButton();
        infoPanel4 = new JPanel();
        qqText4 = new JLabel();
        nickText4 = new JLabel();
        qqValue4 = new JLabel();
        nickValue4 = new JLabel();
        scrollPane16 = new JScrollPane();
        textPane4 = new JTextPane();
        panel21 = new JPanel();
        cloudBan = new JCheckBox();
        ignoreBanUser = new JCheckBox();
        leaveByBanUser = new JCheckBox();
        leaveGroupByBan = new JCheckBox();
        banGroupBecauseBan = new JCheckBox();
        banGroupBecauseReduce = new JCheckBox();
        banUserBecauseReduce = new JCheckBox();
        panel23 = new JPanel();
        label60 = new JLabel();
        scrollPane9 = new JScrollPane();
        addGroup = new JTextArea();
        label61 = new JLabel();
        scrollPane10 = new JScrollPane();
        addFriend = new JTextArea();
        label62 = new JLabel();
        label63 = new JLabel();
        label64 = new JLabel();
        scrollPane12 = new JScrollPane();
        refuseGroupByBan = new JTextArea();
        scrollPane13 = new JScrollPane();
        refuseFriendByBan = new JTextArea();
        panel24 = new JPanel();
        label65 = new JLabel();
        banListInputNotId = new JTextField();
        panel26 = new JPanel();
        label6 = new JLabel();
        scrollPane17 = new JScrollPane();
        textPane5 = new JTextPane();
        panel27 = new JPanel();
        label7 = new JLabel();
        scrollPane18 = new JScrollPane();
        textPane6 = new JTextPane();
        label8 = new JLabel();
        scrollPane19 = new JScrollPane();
        textPane7 = new JTextPane();
        panel22 = new JPanel();
        dialog1 = new JDialog();
        panel25 = new JPanel();
        label5 = new JLabel();
        button1 = new JButton();

        //======== windows ========
        {
            windows.setTitle("SinaNya\u8dd1\u56e2\u9ab0\u70b9\u6838\u5fc3 By SitaNya");
            windows.setBackground(new Color(60, 63, 65));
            windows.setAlwaysOnTop(true);
            windows.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    windowsWindowClosing(e);
                }
            });
            windows.setLayout(new BorderLayout());

            //======== tabContain ========
            {

                //======== setting ========
                {
                    setting.setLayout(null);

                    //======== panel3 ========
                    {
                        panel3.setBorder(new EtchedBorder());
                        panel3.setLayout(null);

                        //---- save ----
                        save.setText("\u4fdd\u5b58");
                        save.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                saveSystem(e);
                            }
                        });
                        panel3.add(save);
                        save.setBounds(new Rectangle(new Point(35, 435), save.getPreferredSize()));

                        //======== infoPanel6 ========
                        {
                            infoPanel6.setBorder(new BevelBorder(BevelBorder.LOWERED));
                            infoPanel6.setLayout(null);

                            //---- qqText6 ----
                            qqText6.setText("\u5f53\u524d\u9ab0\u5a18QQ\u53f7:");
                            infoPanel6.add(qqText6);
                            qqText6.setBounds(10, 10, 90, 25);

                            //---- nickText6 ----
                            nickText6.setText("\u5f53\u524d\u9ab0\u5a18\u6635\u79f0:");
                            infoPanel6.add(nickText6);
                            nickText6.setBounds(10, 70, nickText6.getPreferredSize().width, 25);

                            //---- qqValue6 ----
                            qqValue6.setText("text");
                            infoPanel6.add(qqValue6);
                            qqValue6.setBounds(10, 40, 140, 25);

                            //---- nickValue6 ----
                            nickValue6.setText("text");
                            infoPanel6.add(nickValue6);
                            nickValue6.setBounds(10, 100, 140, 25);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < infoPanel6.getComponentCount(); i++) {
                                    Rectangle bounds = infoPanel6.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = infoPanel6.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                infoPanel6.setMinimumSize(preferredSize);
                                infoPanel6.setPreferredSize(preferredSize);
                            }
                        }
                        panel3.add(infoPanel6);
                        infoPanel6.setBounds(5, 5, 145, 135);

                        //======== scrollPane11 ========
                        {

                            //---- textPane1 ----
                            textPane1.setText("\u8fd9\u4e2a\u754c\u9762\u7528\u4e8e\u8bbe\u5b9a\uff1a\n\u5404\u79cd\u547d\u4ee4\u56de\u590d\u8bed\n\u5404\u79cd\u62a5\u9519\u56de\u590d\u8bed\n\u4f60\u9700\u8981\u6ce8\u610f\u7684\u662f\uff0c\u7b2c\u4e00\u6b21\u542f\u52a8\u4f1a\u6709\u9ed8\u8ba4\u4fe1\u606f\u751f\u6210\n\u91cc\u9762\u53ef\u80fd\u4f1a\u5939\u6742%s\u8fd9\u6837\u7684\u5b57\u7b26\uff0c\u8fd9\u6307\u4ee3\u5b9e\u9645\u7684\u540d\u79f0\n\u5982\u65e5\u5fd7\u521b\u5efa\u5e76\u5f00\u542f\u680f\u4f4d\u4e2d\u7684%s\u6307\u4ee3\u65e5\u5fd7\u540d\u79f0\n\u5c0f\u961f\u6210\u5458en\u4e2d\u7684%s\u6307\u4ee3\u6210\u5458\u540d\n\u6b64\u5916\uff0c\u4f60\u53ef\u4ee5\u5584\u7528\\n\u5b57\u7b26\u6765\u4ee3\u8868\u56de\u8f66\n\u5b8c\u6bd5\u540e\u8bb0\u5f97\u70b9\u4fdd\u5b58");
                            scrollPane11.setViewportView(textPane1);
                        }
                        panel3.add(scrollPane11);
                        scrollPane11.setBounds(5, 145, 145, 275);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel3.getComponentCount(); i++) {
                                Rectangle bounds = panel3.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel3.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel3.setMinimumSize(preferredSize);
                            panel3.setPreferredSize(preferredSize);
                        }
                    }
                    setting.add(panel3);
                    panel3.setBounds(10, 10, 155, 480);

                    //======== scrollPane1 ========
                    {

                        //======== panel2 ========
                        {
                            panel2.setBorder(new BevelBorder(BevelBorder.LOWERED));
                            panel2.setLayout(null);

                            //======== panel6 ========
                            {
                                panel6.setBorder(new EtchedBorder());
                                panel6.setLayout(null);

                                //---- label11 ----
                                label11.setText("\u4eba\u7269\u5361\u5f55\u5165\u683c\u5f0f\u6709\u8bef");
                                panel6.add(label11);
                                label11.setBounds(10, 10, label11.getPreferredSize().width, 25);

                                //---- label12 ----
                                label12.setText("\u4eba\u7269\u5361\u5f55\u5165\u683c\u5f0f\u5e2e\u52a9");
                                panel6.add(label12);
                                label12.setBounds(10, 40, 117, 25);

                                //---- label13 ----
                                label13.setText("\u672a\u627e\u5230\u4eba\u7269\u5361\u6280\u80fd\u503c");
                                panel6.add(label13);
                                label13.setBounds(10, 70, 117, 25);

                                //---- label14 ----
                                label14.setText("\u5c5e\u6027\u8bbe\u7f6e\u6210\u529f");
                                panel6.add(label14);
                                label14.setBounds(10, 100, 117, 25);
                                panel6.add(setPropFormat);
                                setPropFormat.setBounds(145, 10, 300, 25);
                                panel6.add(setHelp);
                                setHelp.setBounds(145, 40, 300, 25);
                                panel6.add(notFoundSkill);
                                notFoundSkill.setBounds(145, 70, 300, 25);
                                panel6.add(setPropSuccess);
                                setPropSuccess.setBounds(145, 100, 300, 25);

                                {
                                    // compute preferred size
                                    Dimension preferredSize = new Dimension();
                                    for(int i = 0; i < panel6.getComponentCount(); i++) {
                                        Rectangle bounds = panel6.getComponent(i).getBounds();
                                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                    }
                                    Insets insets = panel6.getInsets();
                                    preferredSize.width += insets.right;
                                    preferredSize.height += insets.bottom;
                                    panel6.setMinimumSize(preferredSize);
                                    panel6.setPreferredSize(preferredSize);
                                }
                            }
                            panel2.add(panel6);
                            panel6.setBounds(10, 330, 460, 135);

                            //======== panel13 ========
                            {
                                panel13.setBorder(new EtchedBorder());
                                panel13.setToolTipText("\u673a\u5668\u4eba\u5e38\u7528\u4fe1\u606f");
                                panel13.setLayout(null);

                                //---- label45 ----
                                label45.setText("en\u6210\u529f\u9644\u52a0\u8bed");
                                panel13.add(label45);
                                label45.setBounds(10, 10, label45.getPreferredSize().width, 25);

                                //---- label46 ----
                                label46.setText("en\u5931\u8d25\u9644\u52a0\u8bed");
                                panel13.add(label46);
                                label46.setBounds(10, 40, label46.getPreferredSize().width, 20);

                                //---- label47 ----
                                label47.setText("\u6697\u9ab0\u8bed");
                                panel13.add(label47);
                                label47.setBounds(10, 70, label47.getPreferredSize().width, 20);
                                panel13.add(enSuccess);
                                enSuccess.setBounds(105, 10, 340, 25);
                                panel13.add(enFailed);
                                enFailed.setBounds(105, 40, 340, 25);
                                panel13.add(hiddenDice);
                                hiddenDice.setBounds(105, 70, 340, 25);

                                {
                                    // compute preferred size
                                    Dimension preferredSize = new Dimension();
                                    for(int i = 0; i < panel13.getComponentCount(); i++) {
                                        Rectangle bounds = panel13.getComponent(i).getBounds();
                                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                    }
                                    Insets insets = panel13.getInsets();
                                    preferredSize.width += insets.right;
                                    preferredSize.height += insets.bottom;
                                    panel13.setMinimumSize(preferredSize);
                                    panel13.setPreferredSize(preferredSize);
                                }
                            }
                            panel2.add(panel13);
                            panel13.setBounds(10, 215, 460, 105);

                            //======== panel5 ========
                            {
                                panel5.setBorder(new EtchedBorder());
                                panel5.setToolTipText("\u673a\u5668\u4eba\u5e38\u7528\u4fe1\u606f");
                                panel5.setLayout(null);

                                //---- botStartLable ----
                                botStartLable.setText("\u5f00\u542f\u4fe1\u606f");
                                panel5.add(botStartLable);
                                botStartLable.setBounds(10, 10, botStartLable.getPreferredSize().width, 25);

                                //---- botAlreadyStartLable ----
                                botAlreadyStartLable.setText("\u5df2\u5f00\u542f\u63d0\u793a");
                                panel5.add(botAlreadyStartLable);
                                botAlreadyStartLable.setBounds(10, 40, botAlreadyStartLable.getPreferredSize().width, 20);

                                //---- botStopLable ----
                                botStopLable.setText("\u5173\u95ed\u4fe1\u606f");
                                panel5.add(botStopLable);
                                botStopLable.setBounds(10, 70, botStopLable.getPreferredSize().width, 20);

                                //---- botAlreadyStopLable ----
                                botAlreadyStopLable.setText("\u5df2\u5173\u95ed\u63d0\u793a");
                                panel5.add(botAlreadyStopLable);
                                botAlreadyStopLable.setBounds(10, 100, botAlreadyStopLable.getPreferredSize().width, 20);

                                //---- label9 ----
                                label9.setText("\u9000\u7fa4\u63d0\u793a");
                                panel5.add(label9);
                                label9.setBounds(10, 130, label9.getPreferredSize().width, 20);

                                //---- label10 ----
                                label10.setText("\u673a\u5668\u4eba\u4fe1\u606f\u63d0\u793a");
                                panel5.add(label10);
                                label10.setBounds(10, 160, label10.getPreferredSize().width, 20);
                                panel5.add(botStart);
                                botStart.setBounds(105, 10, 340, 25);
                                panel5.add(botAlreadyStart);
                                botAlreadyStart.setBounds(105, 40, 340, 25);
                                panel5.add(botStop);
                                botStop.setBounds(105, 70, 340, 25);
                                panel5.add(botAlreadyStop);
                                botAlreadyStop.setBounds(105, 100, 340, 25);
                                panel5.add(botExit);
                                botExit.setBounds(105, 130, 340, 25);
                                panel5.add(botInfo);
                                botInfo.setBounds(105, 160, 340, 25);

                                {
                                    // compute preferred size
                                    Dimension preferredSize = new Dimension();
                                    for(int i = 0; i < panel5.getComponentCount(); i++) {
                                        Rectangle bounds = panel5.getComponent(i).getBounds();
                                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                    }
                                    Insets insets = panel5.getInsets();
                                    preferredSize.width += insets.right;
                                    preferredSize.height += insets.bottom;
                                    panel5.setMinimumSize(preferredSize);
                                    panel5.setPreferredSize(preferredSize);
                                }
                            }
                            panel2.add(panel5);
                            panel5.setBounds(10, 10, 460, 195);

                            //======== panel12 ========
                            {
                                panel12.setBorder(new EtchedBorder());
                                panel12.setToolTipText("\u673a\u5668\u4eba\u5e38\u7528\u4fe1\u606f");
                                panel12.setLayout(null);

                                //---- label39 ----
                                label39.setText("SC\u683c\u5f0f\u6709\u8bef");
                                panel12.add(label39);
                                label39.setBounds(10, 10, label39.getPreferredSize().width, 25);

                                //---- label40 ----
                                label40.setText("\u75af\u72c2\u9644\u52a0\u8bed");
                                panel12.add(label40);
                                label40.setBounds(10, 40, label40.getPreferredSize().width, 20);

                                //---- label41 ----
                                label41.setText("SC\u5927\u6210\u529f\u9644\u52a0\u8bed");
                                panel12.add(label41);
                                label41.setBounds(10, 70, label41.getPreferredSize().width, 20);

                                //---- label42 ----
                                label42.setText("SC\u5927\u5931\u8d25\u9644\u52a0\u8bed");
                                panel12.add(label42);
                                label42.setBounds(10, 100, label42.getPreferredSize().width, 20);

                                //---- label43 ----
                                label43.setText("SC\u6210\u529f\u9644\u52a0\u8bed");
                                panel12.add(label43);
                                label43.setBounds(10, 130, label43.getPreferredSize().width, 20);

                                //---- label44 ----
                                label44.setText("SC\u5931\u8d25\u9644\u52a0\u8bed");
                                panel12.add(label44);
                                label44.setBounds(10, 160, label44.getPreferredSize().width, 20);
                                panel12.add(sanCheck);
                                sanCheck.setBounds(105, 10, 340, 25);
                                panel12.add(symptom);
                                symptom.setBounds(105, 40, 340, 25);
                                panel12.add(sanCheckCriticalSuccess);
                                sanCheckCriticalSuccess.setBounds(105, 70, 340, 25);
                                panel12.add(sanCheckFumble);
                                sanCheckFumble.setBounds(105, 100, 340, 25);
                                panel12.add(sanCheckSuccess);
                                sanCheckSuccess.setBounds(105, 130, 340, 25);
                                panel12.add(sanCheckFailure);
                                sanCheckFailure.setBounds(105, 160, 340, 25);
                            }
                            panel2.add(panel12);
                            panel12.setBounds(10, 470, 460, 195);

                            //======== panel9 ========
                            {
                                panel9.setBorder(new EtchedBorder());
                                panel9.setToolTipText("\u673a\u5668\u4eba\u5e38\u7528\u4fe1\u606f");
                                panel9.setLayout(null);

                                //---- label24 ----
                                label24.setText("\u65e5\u5fd7\u521b\u5efa\u5e76\u5f00\u542f");
                                panel9.add(label24);
                                label24.setBounds(10, 10, label24.getPreferredSize().width, 25);

                                //---- label25 ----
                                label25.setText("\u65e5\u5fd7\u8ffd\u52a0\u5e76\u5f00\u542f");
                                panel9.add(label25);
                                label25.setBounds(10, 40, label25.getPreferredSize().width, 20);

                                //---- label26 ----
                                label26.setText("\u65e5\u5fd7\u5df2\u5f00\u542f");
                                panel9.add(label26);
                                label26.setBounds(10, 70, label26.getPreferredSize().width, 20);

                                //---- label27 ----
                                label27.setText("\u65e5\u5fd7\u5df2\u5173\u95ed");
                                panel9.add(label27);
                                label27.setBounds(10, 100, label27.getPreferredSize().width, 20);

                                //---- label28 ----
                                label28.setText("\u65e5\u5fd7\u672a\u627e\u5230");
                                panel9.add(label28);
                                label28.setBounds(10, 130, label28.getPreferredSize().width, 20);

                                //---- label29 ----
                                label29.setText("\u4e0d\u53ef\u4f7f\u7528\u7a7a\u65e5\u5fd7\u540d");
                                panel9.add(label29);
                                label29.setBounds(10, 160, label29.getPreferredSize().width, 20);
                                panel9.add(createLog);
                                createLog.setBounds(150, 10, 290, 25);
                                panel9.add(appendLog);
                                appendLog.setBounds(150, 40, 290, 25);
                                panel9.add(alreadyOpen);
                                alreadyOpen.setBounds(150, 70, 290, 25);
                                panel9.add(alreadyClose);
                                alreadyClose.setBounds(150, 100, 290, 25);
                                panel9.add(notFoundLog);
                                notFoundLog.setBounds(150, 130, 290, 25);
                                panel9.add(cantEmptyLogName);
                                cantEmptyLogName.setBounds(150, 160, 290, 25);

                                //---- label30 ----
                                label30.setText("\u65e0\u6cd5\u5220\u9664\u5df2\u5f00\u542f\u7684\u65e5\u5fd7");
                                panel9.add(label30);
                                label30.setBounds(10, 190, label30.getPreferredSize().width, 20);

                                //---- label31 ----
                                label31.setText("\u83b7\u53d6\u65e5\u5fd7\u961f\u5217\u5835\u585e");
                                panel9.add(label31);
                                label31.setBounds(10, 220, label31.getPreferredSize().width, 20);
                                panel9.add(deleteOpenLog);
                                deleteOpenLog.setBounds(150, 190, 290, 25);
                                panel9.add(readLock);
                                readLock.setBounds(150, 220, 290, 25);

                                {
                                    // compute preferred size
                                    Dimension preferredSize = new Dimension();
                                    for(int i = 0; i < panel9.getComponentCount(); i++) {
                                        Rectangle bounds = panel9.getComponent(i).getBounds();
                                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                    }
                                    Insets insets = panel9.getInsets();
                                    preferredSize.width += insets.right;
                                    preferredSize.height += insets.bottom;
                                    panel9.setMinimumSize(preferredSize);
                                    panel9.setPreferredSize(preferredSize);
                                }
                            }
                            panel2.add(panel9);
                            panel9.setBounds(480, 10, 460, 250);

                            //======== panel8 ========
                            {
                                panel8.setBorder(new EtchedBorder());
                                panel8.setToolTipText("\u673a\u5668\u4eba\u5e38\u7528\u4fe1\u606f");
                                panel8.setLayout(null);

                                //---- label19 ----
                                label19.setText("\u591a\u91cd\u9ab0\u70b9(ral)\u547d\u4ee4\u683c\u5f0f\u6709\u8bef");
                                panel8.add(label19);
                                label19.setBounds(10, 10, label19.getPreferredSize().width, 25);

                                //---- label20 ----
                                label20.setText("\u9ab0\u70b9\u6b21\u6570\u8fc7\u591a");
                                panel8.add(label20);
                                label20.setBounds(10, 40, label20.getPreferredSize().width, 20);

                                //---- label21 ----
                                label21.setText("\u672a\u8bbe\u7f6ekp\u7fa4");
                                panel8.add(label21);
                                label21.setBounds(10, 70, label21.getPreferredSize().width, 20);

                                //---- label22 ----
                                label22.setText("\u975e\u79c1\u804a\u547d\u4ee4");
                                panel8.add(label22);
                                label22.setBounds(10, 100, label22.getPreferredSize().width, 20);

                                //---- label23 ----
                                label23.setText("\u4ec5\u7fa4\u4e3b\u6216\u7ba1\u7406\u5458\u53ef\u7528");
                                panel8.add(label23);
                                label23.setBounds(10, 130, label23.getPreferredSize().width, 20);
                                panel8.add(manyRollsFormat);
                                manyRollsFormat.setBounds(175, 10, 270, 25);
                                panel8.add(diceTimesTooBig);
                                diceTimesTooBig.setBounds(175, 40, 270, 25);
                                panel8.add(needKpGroup);
                                needKpGroup.setBounds(175, 70, 270, 25);
                                panel8.add(cantInPrivate);
                                cantInPrivate.setBounds(175, 100, 270, 25);
                                panel8.add(onlyManager);
                                onlyManager.setBounds(175, 130, 270, 25);

                                {
                                    // compute preferred size
                                    Dimension preferredSize = new Dimension();
                                    for(int i = 0; i < panel8.getComponentCount(); i++) {
                                        Rectangle bounds = panel8.getComponent(i).getBounds();
                                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                    }
                                    Insets insets = panel8.getInsets();
                                    preferredSize.width += insets.right;
                                    preferredSize.height += insets.bottom;
                                    panel8.setMinimumSize(preferredSize);
                                    panel8.setPreferredSize(preferredSize);
                                }
                            }
                            panel2.add(panel8);
                            panel8.setBounds(480, 270, 460, 165);

                            //======== panel10 ========
                            {
                                panel10.setBorder(new EtchedBorder());
                                panel10.setLayout(null);

                                //---- label32 ----
                                label32.setText("\u5148\u653b\u5217\u8868\u4e3a\u7a7a");
                                panel10.add(label32);
                                label32.setBounds(10, 10, 117, 25);

                                //---- label33 ----
                                label33.setText("\u6e05\u7a7a\u5148\u653b\u5217\u8868");
                                panel10.add(label33);
                                label33.setBounds(10, 40, 117, 25);
                                panel10.add(dndInitIsEmtpy);
                                dndInitIsEmtpy.setBounds(145, 10, 300, 25);
                                panel10.add(clrDndInit);
                                clrDndInit.setBounds(145, 40, 300, 25);

                                {
                                    // compute preferred size
                                    Dimension preferredSize = new Dimension();
                                    for(int i = 0; i < panel10.getComponentCount(); i++) {
                                        Rectangle bounds = panel10.getComponent(i).getBounds();
                                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                    }
                                    Insets insets = panel10.getInsets();
                                    preferredSize.width += insets.right;
                                    preferredSize.height += insets.bottom;
                                    panel10.setMinimumSize(preferredSize);
                                    panel10.setPreferredSize(preferredSize);
                                }
                            }
                            panel2.add(panel10);
                            panel10.setBounds(480, 440, 460, 75);

                            //======== panel14 ========
                            {
                                panel14.setBorder(new EtchedBorder());
                                panel14.setToolTipText("\u673a\u5668\u4eba\u5e38\u7528\u4fe1\u606f");
                                panel14.setLayout(null);

                                //---- label48 ----
                                label48.setText("\u5f53\u524d\u5c0f\u961f\u4e3a\u7a7a");
                                panel14.add(label48);
                                label48.setBounds(10, 10, label48.getPreferredSize().width, 25);

                                //---- label49 ----
                                label49.setText("\u5f53\u524d\u5c0f\u961fEN\u5217\u8868\u4e3a\u7a7a");
                                panel14.add(label49);
                                label49.setBounds(10, 40, label49.getPreferredSize().width, 20);
                                panel14.add(teamIsEmpty);
                                teamIsEmpty.setBounds(145, 10, 300, 25);
                                panel14.add(teamMemberEnIsEmpty);
                                teamMemberEnIsEmpty.setBounds(145, 40, 300, 25);

                                {
                                    // compute preferred size
                                    Dimension preferredSize = new Dimension();
                                    for(int i = 0; i < panel14.getComponentCount(); i++) {
                                        Rectangle bounds = panel14.getComponent(i).getBounds();
                                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                    }
                                    Insets insets = panel14.getInsets();
                                    preferredSize.width += insets.right;
                                    preferredSize.height += insets.bottom;
                                    panel14.setMinimumSize(preferredSize);
                                    panel14.setPreferredSize(preferredSize);
                                }
                            }
                            panel2.add(panel14);
                            panel14.setBounds(480, 525, 460, 75);

                            //======== panel7 ========
                            {
                                panel7.setBorder(new EtchedBorder());
                                panel7.setLayout(null);

                                //---- label15 ----
                                label15.setText("\u65b0\u7248\u4eba\u7269\u5361\u94fe\u63a5");
                                panel7.add(label15);
                                label15.setBounds(10, 10, 117, 25);

                                //---- label16 ----
                                label16.setText("RP\u95ee\u7b54\u94fe\u63a5");
                                panel7.add(label16);
                                label16.setBounds(10, 40, 117, 25);

                                //---- label17 ----
                                label17.setText("\u89c4\u5219\u4e66\u94fe\u63a5");
                                panel7.add(label17);
                                label17.setBounds(10, 70, 117, 25);

                                //---- label18 ----
                                label18.setText("\u8f66\u5361\u6307\u5357\u94fe\u63a5");
                                panel7.add(label18);
                                label18.setBounds(10, 100, 117, 25);
                                panel7.add(bookCard);
                                bookCard.setBounds(145, 10, 300, 25);
                                panel7.add(bookRp);
                                bookRp.setBounds(145, 40, 300, 25);
                                panel7.add(bookKp);
                                bookKp.setBounds(145, 70, 300, 25);
                                panel7.add(bookMake);
                                bookMake.setBounds(145, 100, 300, 25);

                                {
                                    // compute preferred size
                                    Dimension preferredSize = new Dimension();
                                    for(int i = 0; i < panel7.getComponentCount(); i++) {
                                        Rectangle bounds = panel7.getComponent(i).getBounds();
                                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                    }
                                    Insets insets = panel7.getInsets();
                                    preferredSize.width += insets.right;
                                    preferredSize.height += insets.bottom;
                                    panel7.setMinimumSize(preferredSize);
                                    panel7.setPreferredSize(preferredSize);
                                }
                            }
                            panel2.add(panel7);
                            panel7.setBounds(10, 670, 460, 135);

                            //======== panel11 ========
                            {
                                panel11.setBorder(new EtchedBorder());
                                panel11.setLayout(null);

                                //---- label34 ----
                                label34.setText("\u5bf9\u6297\u7ed3\u675f");
                                panel11.add(label34);
                                label34.setBounds(10, 10, 117, 25);

                                //---- label35 ----
                                label35.setText("\u5148\u624b\u80dc\u5229");
                                panel11.add(label35);
                                label35.setBounds(10, 40, 117, 25);

                                //---- label36 ----
                                label36.setText("\u540e\u624b\u80dc\u5229");
                                panel11.add(label36);
                                label36.setBounds(10, 70, 117, 25);

                                //---- label37 ----
                                label37.setText("\u5168\u90e8\u5931\u8d25");
                                panel11.add(label37);
                                label37.setBounds(10, 100, 117, 25);
                                panel11.add(antagonizeOver);
                                antagonizeOver.setBounds(145, 10, 300, 25);
                                panel11.add(antagonizeFirstSuccess);
                                antagonizeFirstSuccess.setBounds(145, 40, 300, 25);
                                panel11.add(antagonizeSecondSuccess);
                                antagonizeSecondSuccess.setBounds(145, 70, 300, 25);
                                panel11.add(antagonizeAllFailed);
                                antagonizeAllFailed.setBounds(145, 100, 300, 25);

                                //---- label38 ----
                                label38.setText("\u5e73\u624b");
                                panel11.add(label38);
                                label38.setBounds(10, 130, 117, 25);
                                panel11.add(antagonizeDraw);
                                antagonizeDraw.setBounds(145, 130, 300, 25);

                                {
                                    // compute preferred size
                                    Dimension preferredSize = new Dimension();
                                    for(int i = 0; i < panel11.getComponentCount(); i++) {
                                        Rectangle bounds = panel11.getComponent(i).getBounds();
                                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                    }
                                    Insets insets = panel11.getInsets();
                                    preferredSize.width += insets.right;
                                    preferredSize.height += insets.bottom;
                                    panel11.setMinimumSize(preferredSize);
                                    panel11.setPreferredSize(preferredSize);
                                }
                            }
                            panel2.add(panel11);
                            panel11.setBounds(480, 610, 460, 160);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < panel2.getComponentCount(); i++) {
                                    Rectangle bounds = panel2.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = panel2.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                panel2.setMinimumSize(preferredSize);
                                panel2.setPreferredSize(preferredSize);
                            }
                        }
                        scrollPane1.setViewportView(panel2);
                    }
                    setting.add(scrollPane1);
                    scrollPane1.setBounds(170, 10, 970, 480);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < setting.getComponentCount(); i++) {
                            Rectangle bounds = setting.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = setting.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        setting.setMinimumSize(preferredSize);
                        setting.setPreferredSize(preferredSize);
                    }
                }
                tabContain.addTab("\u62a5\u9519\u56de\u590d\u8bed\u7b49\u8bbe\u7f6e", setting);

                //======== panel1 ========
                {
                    panel1.setLayout(null);

                    //======== panel4 ========
                    {
                        panel4.setBorder(new EtchedBorder());
                        panel4.setLayout(null);

                        //---- save2 ----
                        save2.setText("\u4fdd\u5b58");
                        save2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                saveSystem(e);
                            }
                        });
                        panel4.add(save2);
                        save2.setBounds(new Rectangle(new Point(35, 435), save2.getPreferredSize()));

                        //======== infoPanel5 ========
                        {
                            infoPanel5.setBorder(new BevelBorder(BevelBorder.LOWERED));
                            infoPanel5.setLayout(null);

                            //---- qqText5 ----
                            qqText5.setText("\u5f53\u524d\u9ab0\u5a18QQ\u53f7:");
                            infoPanel5.add(qqText5);
                            qqText5.setBounds(10, 10, 90, 25);

                            //---- nickText5 ----
                            nickText5.setText("\u5f53\u524d\u9ab0\u5a18\u6635\u79f0:");
                            infoPanel5.add(nickText5);
                            nickText5.setBounds(10, 70, nickText5.getPreferredSize().width, 25);

                            //---- qqValue5 ----
                            qqValue5.setText("text");
                            infoPanel5.add(qqValue5);
                            qqValue5.setBounds(10, 40, 140, 25);

                            //---- nickValue5 ----
                            nickValue5.setText("text");
                            infoPanel5.add(nickValue5);
                            nickValue5.setBounds(10, 100, 140, 25);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < infoPanel5.getComponentCount(); i++) {
                                    Rectangle bounds = infoPanel5.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = infoPanel5.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                infoPanel5.setMinimumSize(preferredSize);
                                infoPanel5.setPreferredSize(preferredSize);
                            }
                        }
                        panel4.add(infoPanel5);
                        infoPanel5.setBounds(5, 5, 145, 135);

                        //======== scrollPane14 ========
                        {

                            //---- textPane2 ----
                            textPane2.setText("\u8fd9\u4e2a\u754c\u9762\u7528\u4e8e\u8bbe\u5b9a\uff1a\n\u5927\u6210\u529f\u4e43\u81f3\u5927\u5931\u8d25\u7684\u56de\u590d\u8bed\n\u8fd9\u91cc\u4e0d\u4f1a\u51fa\u73b0%s\n\\n\u4e00\u6837\u4ee3\u8868\u6362\u884c\n\n\u4f60\u4e00\u5b9a\u8981\u8bb0\u4f4f\u7684\u662f\uff1a\n|\n\u8fd9\u4e2a\u7ad6\u7ebf\u7b26\u53f7\uff0c\u5b83\u5728\u4f60\u7684\u56de\u8f66\u4e0a\u65b9\n\u540c\u4e00\u79cd\u56de\u590d\u8bed\uff0c\u7528|\u5206\u5272\u591a\u53e5\uff0c\u5728\u9ab0\u5a18\u56de\u590d\u65f6\u4f1a\u968f\u5373\u9009\u53d6\u4e00\u53e5\uff0c\u8fd9\u91cc\u6bd4\u5982\n\u6210\u529f|\u6210\u529f\u5566|\u662f\u6210\u529f\u5462|\u4f60\u6210\u529f\u4e86\n\u4f1a4\u79cd\u91cc\u968f\u673a\u9009\u62e91\u79cd\n\u6b64\u5916\uff0c\u6211\u4e3a\u4e86\u5c3d\u53ef\u80fd\u7ed9\u4e88\u4f60\u81ea\u7531\uff0c\u6ca1\u6709\u5199\u6b7b\u56de\u590d\u201c\u6210\u529f\u201d\u8fd9\u4e2a\u5b57\u773c\uff0c\u56e0\u6b64\u4f60\u9700\u8981\u5728\u56de\u590d\u8bed\u4e2d\u586b\u5199\u201c\u6210\u529f\u201d\u4e8c\u5b57");
                            scrollPane14.setViewportView(textPane2);
                        }
                        panel4.add(scrollPane14);
                        scrollPane14.setBounds(5, 145, 145, 275);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel4.getComponentCount(); i++) {
                                Rectangle bounds = panel4.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel4.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel4.setMinimumSize(preferredSize);
                            panel4.setPreferredSize(preferredSize);
                        }
                    }
                    panel1.add(panel4);
                    panel4.setBounds(10, 10, 155, 480);

                    //======== panel15 ========
                    {
                        panel15.setBorder(new EtchedBorder());
                        panel15.setLayout(null);

                        //---- label1 ----
                        label1.setText("\u5927\u6210\u529f\u56de\u590d\u8bed");
                        panel15.add(label1);
                        label1.setBounds(10, 10, 150, label1.getPreferredSize().height);

                        //======== scrollPane2 ========
                        {
                            scrollPane2.setViewportView(criticalSuccess);
                        }
                        panel15.add(scrollPane2);
                        scrollPane2.setBounds(10, 30, 440, 120);

                        //---- label2 ----
                        label2.setText("\u6781\u96be\u6210\u529f\u56de\u590d\u8bed");
                        panel15.add(label2);
                        label2.setBounds(new Rectangle(new Point(10, 165), label2.getPreferredSize()));

                        //======== scrollPane3 ========
                        {
                            scrollPane3.setViewportView(extremeSuccess);
                        }
                        panel15.add(scrollPane3);
                        scrollPane3.setBounds(10, 185, 440, 120);

                        //---- label3 ----
                        label3.setText("\u56f0\u96be\u6210\u529f\u56de\u590d\u8bed");
                        panel15.add(label3);
                        label3.setBounds(new Rectangle(new Point(10, 320), label3.getPreferredSize()));

                        //======== scrollPane4 ========
                        {
                            scrollPane4.setViewportView(headSuccess);
                        }
                        panel15.add(scrollPane4);
                        scrollPane4.setBounds(10, 340, 440, 120);

                        //---- label4 ----
                        label4.setText("\u6210\u529f\u56de\u590d\u8bed");
                        panel15.add(label4);
                        label4.setBounds(new Rectangle(new Point(490, 10), label4.getPreferredSize()));

                        //---- label50 ----
                        label50.setText("\u5931\u8d25\u56de\u590d\u8bed");
                        panel15.add(label50);
                        label50.setBounds(new Rectangle(new Point(490, 165), label50.getPreferredSize()));

                        //---- label51 ----
                        label51.setText("\u5927\u5931\u8d25\u56de\u590d\u8bed");
                        panel15.add(label51);
                        label51.setBounds(new Rectangle(new Point(490, 320), label51.getPreferredSize()));

                        //======== scrollPane5 ========
                        {
                            scrollPane5.setViewportView(success);
                        }
                        panel15.add(scrollPane5);
                        scrollPane5.setBounds(490, 30, 440, 120);

                        //======== scrollPane6 ========
                        {
                            scrollPane6.setViewportView(failure);
                        }
                        panel15.add(scrollPane6);
                        scrollPane6.setBounds(490, 185, 440, 120);

                        //======== scrollPane7 ========
                        {
                            scrollPane7.setViewportView(fumble);
                        }
                        panel15.add(scrollPane7);
                        scrollPane7.setBounds(490, 340, 440, 120);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel15.getComponentCount(); i++) {
                                Rectangle bounds = panel15.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel15.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel15.setMinimumSize(preferredSize);
                            panel15.setPreferredSize(preferredSize);
                        }
                    }
                    panel1.add(panel15);
                    panel15.setBounds(175, 10, 970, 480);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel1.getComponentCount(); i++) {
                            Rectangle bounds = panel1.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel1.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel1.setMinimumSize(preferredSize);
                        panel1.setPreferredSize(preferredSize);
                    }
                }
                tabContain.addTab("\u81ea\u5b9a\u4e49\u6210\u529f\u968f\u5373\u56de\u590d\u8bcd", panel1);

                //======== clean ========
                {
                    clean.setLayout(null);

                    //======== monitorPanel2 ========
                    {
                        monitorPanel2.setToolTipText("\u76d1\u63a7\u4fe1\u606f");
                        monitorPanel2.setBorder(new EtchedBorder());
                        monitorPanel2.setLayout(null);

                        //---- promethusPortText2 ----
                        promethusPortText2.setText("\u666e\u7f57\u7c73\u4fee\u65af\u81ea\u5b9a\u4e49\u7aef\u53e3");
                        monitorPanel2.add(promethusPortText2);
                        promethusPortText2.setBounds(10, 40, promethusPortText2.getPreferredSize().width, 25);
                        monitorPanel2.add(prometheusPort);
                        prometheusPort.setBounds(140, 40, 55, 25);

                        //---- Prometheus ----
                        Prometheus.setText("\u542f\u7528\u666e\u7f57\u7c73\u4fee\u65af\u76d1\u63a7\u4e0a\u62a5");
                        monitorPanel2.add(Prometheus);
                        Prometheus.setBounds(new Rectangle(new Point(10, 10), Prometheus.getPreferredSize()));

                        //---- heap ----
                        heap.setText("\u542f\u7528\u5fc3\u8df3\u62a5\u544a");
                        monitorPanel2.add(heap);
                        heap.setBounds(new Rectangle(new Point(10, 70), heap.getPreferredSize()));

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < monitorPanel2.getComponentCount(); i++) {
                                Rectangle bounds = monitorPanel2.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = monitorPanel2.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            monitorPanel2.setMinimumSize(preferredSize);
                            monitorPanel2.setPreferredSize(preferredSize);
                        }
                    }
                    clean.add(monitorPanel2);
                    monitorPanel2.setBounds(180, 185, 235, 100);

                    //======== setMaster ========
                    {
                        setMaster.setBorder(new EtchedBorder());
                        setMaster.setLayout(null);

                        //---- masterText ----
                        masterText.setText("Master");
                        setMaster.add(masterText);
                        masterText.setBounds(10, 10, 60, 25);

                        //---- notMasterInfoText ----
                        notMasterInfoText.setText("\u975eMaster\u547d\u4ee4\u56de\u590d");
                        setMaster.add(notMasterInfoText);
                        notMasterInfoText.setBounds(10, 40, 220, 25);
                        setMaster.add(master);
                        master.setBounds(80, 10, 144, master.getPreferredSize().height);

                        //======== scrollPane8 ========
                        {
                            scrollPane8.setViewportView(notMaster);
                        }
                        setMaster.add(scrollPane8);
                        scrollPane8.setBounds(15, 75, 205, 75);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < setMaster.getComponentCount(); i++) {
                                Rectangle bounds = setMaster.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = setMaster.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            setMaster.setMinimumSize(preferredSize);
                            setMaster.setPreferredSize(preferredSize);
                        }
                    }
                    clean.add(setMaster);
                    setMaster.setBounds(180, 15, 235, 165);

                    //======== panel16 ========
                    {
                        panel16.setBorder(new EtchedBorder());
                        panel16.setLayout(null);

                        //======== infoPanel3 ========
                        {
                            infoPanel3.setBorder(new BevelBorder(BevelBorder.LOWERED));
                            infoPanel3.setLayout(null);

                            //---- qqText3 ----
                            qqText3.setText("\u5f53\u524d\u9ab0\u5a18QQ\u53f7:");
                            infoPanel3.add(qqText3);
                            qqText3.setBounds(10, 10, 90, 25);

                            //---- nickText3 ----
                            nickText3.setText("\u5f53\u524d\u9ab0\u5a18\u6635\u79f0:");
                            infoPanel3.add(nickText3);
                            nickText3.setBounds(10, 70, nickText3.getPreferredSize().width, 25);

                            //---- qqValue3 ----
                            qqValue3.setText("text");
                            infoPanel3.add(qqValue3);
                            qqValue3.setBounds(10, 40, 140, 25);

                            //---- nickValue3 ----
                            nickValue3.setText("text");
                            infoPanel3.add(nickValue3);
                            nickValue3.setBounds(10, 100, 140, 25);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < infoPanel3.getComponentCount(); i++) {
                                    Rectangle bounds = infoPanel3.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = infoPanel3.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                infoPanel3.setMinimumSize(preferredSize);
                                infoPanel3.setPreferredSize(preferredSize);
                            }
                        }
                        panel16.add(infoPanel3);
                        infoPanel3.setBounds(5, 5, 145, 135);

                        //---- save3 ----
                        save3.setText("\u4fdd\u5b58");
                        save3.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                saveBan(e);
                            }
                        });
                        panel16.add(save3);
                        save3.setBounds(new Rectangle(new Point(35, 435), save3.getPreferredSize()));

                        //======== scrollPane15 ========
                        {

                            //---- textPane3 ----
                            textPane3.setText("\u5728\u8fd9\u4e2a\u754c\u9762\u4e2d\u4f60\u9700\u8981\u8bbe\u7f6e:\nMasterQQ\u53f7\u548c\u5176\u62a5\u9519\u4fe1\u606f\n\u53ea\u6709\u8bbe\u7f6e\u4e86Master\u7684QQ\u53f7\uff0c\u5fc3\u8df3\u62a5\u544a\u624d\u4f1a\u6b63\u786e\u7684\u62a5\u8b66\uff0c\u540e\u7eed\u7684\u4e91\u9ed1\u4e5f\u624d\u53ef\u4ee5\u6b63\u5e38\u4f7f\u7528\n\u8fd9\u91cc\u9700\u8981\u4e3a\u4f60\u89e3\u91ca\u7684\u662f\uff1a\n\u5fc3\u8df3\u62a5\u544a\uff0c\u662f\u6307\u5f53\u4f60\u7684\u9ab0\u5a18\u56e0\u4e3a\u4efb\u4f55\u539f\u56e0\u6302\u8d77\u65f6\uff0c\u53ef\u4ee5\u572820\u5206\u949f\u5185\u4e8e\u7fa4808619122\u4e2d\u5f97\u5230\u62a5\u544a\uff08\u53ea\u9700\u8981\u4f60\u52a0\u5165\uff0c\u4f60\u7684\u9ab0\u5a18\u4e0d\u9700\u8981\uff09\n\u800c\u666e\u7f57\u7c73\u4fee\u65af\u76d1\u63a7\u4e0a\u62a5\uff0c\u4f60\u53ef\u4ee5\u5ffd\u7565\uff0c\u90a3\u662f\u4e00\u79cd\u4f01\u4e1a\u7ea7\u7684\u670d\u52a1\u5668\u8d1f\u8f7d\u76d1\u63a7\u80fd\u529b\uff0c\u9700\u8981\u4e00\u4e9b\u989d\u5916\u7684\u914d\u7f6e\u3002\u5982\u679c\u4f60\u7684\u670d\u52a1\u5668\u4e0a\u6302\u8f7d\u4e86\u975e\u5e38\u591a\u7684\u9ab0\u5a18\u4e0d\u582a\u91cd\u8d1f\uff0c\u6216\u8005\u5e72\u8106\u5b83\u5c31\u6709\u8fc7\u5b95\u673a\u8fde\u4e0d\u4e0a\u7684\u7ecf\u5386\u3002\u4f60\u53ef\u4ee5\u5728\u52a0\u5165\u62a5\u8b66\u7fa4\u540e\u8be2\u95ee\u6211\u5982\u4f55\u914d\u7f6e");
                            scrollPane15.setViewportView(textPane3);
                        }
                        panel16.add(scrollPane15);
                        scrollPane15.setBounds(5, 145, 145, 275);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel16.getComponentCount(); i++) {
                                Rectangle bounds = panel16.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel16.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel16.setMinimumSize(preferredSize);
                            panel16.setPreferredSize(preferredSize);
                        }
                    }
                    clean.add(panel16);
                    panel16.setBounds(10, 10, 155, 480);

                    //======== panel17 ========
                    {
                        panel17.setBorder(new EtchedBorder());
                        panel17.setToolTipText("\u673a\u5668\u4eba\u5e38\u7528\u4fe1\u606f");
                        panel17.setLayout(null);

                        //---- label52 ----
                        label52.setText("\u5173\u95ed\u7fa4\u6e05\u7406\u671f\u9650(\u5355\u4f4d:\u5929)");
                        panel17.add(label52);
                        label52.setBounds(10, 10, label52.getPreferredSize().width, 25);

                        //---- label53 ----
                        label53.setText("\u5173\u95ed\u7fa4\u6e05\u7406\u7528\u8bed");
                        panel17.add(label53);
                        label53.setBounds(10, 40, label53.getPreferredSize().width, 20);

                        //---- label54 ----
                        label54.setText("\u5e9f\u5f03\u7fa4\u6e05\u7406\u671f\u9650(\u5355\u4f4d:\u5929)");
                        panel17.add(label54);
                        label54.setBounds(10, 70, label54.getPreferredSize().width, 20);

                        //---- label55 ----
                        label55.setText("\u5e9f\u5f03\u7fa4\u6e05\u7406\u7528\u8bed");
                        panel17.add(label55);
                        label55.setBounds(10, 100, label55.getPreferredSize().width, 20);
                        panel17.add(clearGroupByOff);
                        clearGroupByOff.setBounds(205, 10, 320, 25);
                        panel17.add(clearGroupByOffInfo);
                        clearGroupByOffInfo.setBounds(205, 40, 320, 25);
                        panel17.add(clearGroup);
                        clearGroup.setBounds(205, 70, 320, 25);
                        panel17.add(clearGroupInfo);
                        clearGroupInfo.setBounds(205, 100, 320, 25);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel17.getComponentCount(); i++) {
                                Rectangle bounds = panel17.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel17.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel17.setMinimumSize(preferredSize);
                            panel17.setPreferredSize(preferredSize);
                        }
                    }
                    clean.add(panel17);
                    panel17.setBounds(425, 220, 545, 130);

                    //======== panel18 ========
                    {
                        panel18.setBorder(new EtchedBorder());
                        panel18.setToolTipText("\u673a\u5668\u4eba\u5e38\u7528\u4fe1\u606f");
                        panel18.setLayout(null);

                        //---- label56 ----
                        label56.setText("\u8b66\u544a\u5237\u5c4f\u9891\u5ea6(N\u6761/\u6bcf10\u79d2)");
                        panel18.add(label56);
                        label56.setBounds(10, 10, label56.getPreferredSize().width, 25);

                        //---- label57 ----
                        label57.setText("\u8b66\u544a\u5237\u5c4f\u7528\u8bed");
                        panel18.add(label57);
                        label57.setBounds(10, 40, label57.getPreferredSize().width, 20);

                        //---- label58 ----
                        label58.setText("\u62c9\u9ed1\u9000\u7fa4\u5237\u5c4f\u9891\u5ea6(N\u6761/\u6bcf\u5206\u949f)");
                        panel18.add(label58);
                        label58.setBounds(10, 70, label58.getPreferredSize().width, 20);

                        //---- label59 ----
                        label59.setText("\u62c9\u9ed1\u9000\u7fa4\u5237\u5c4f\u7528\u8bed");
                        panel18.add(label59);
                        label59.setBounds(10, 100, label59.getPreferredSize().width, 20);
                        panel18.add(alterFrequentness);
                        alterFrequentness.setBounds(205, 10, 320, 25);
                        panel18.add(frequentnessAlterInfo);
                        frequentnessAlterInfo.setBounds(205, 40, 320, 25);
                        panel18.add(banFrequentness);
                        banFrequentness.setBounds(205, 70, 320, 25);
                        panel18.add(frequentnessBanInfo);
                        frequentnessBanInfo.setBounds(205, 100, 320, 25);

                        //---- banGroupAndUserByFre ----
                        banGroupAndUserByFre.setText("\u5f53\u5230\u8fbe\u62c9\u9ed1\u9891\u5ea6\u65f6\uff0c\u9000\u7fa4\u3001\u62c9\u9ed1\u7fa4\u3001\u62c9\u9ed1\u64cd\u4f5c\u4eba");
                        panel18.add(banGroupAndUserByFre);
                        banGroupAndUserByFre.setBounds(10, 130, banGroupAndUserByFre.getPreferredSize().width, 25);

                        //---- banUserByFre ----
                        banUserByFre.setText("\u5f53\u5230\u8fbe\u62c9\u9ed1\u9891\u5ea6\u65f6\uff0c\u53ea\u62c9\u9ed1\u64cd\u4f5c\u4eba");
                        panel18.add(banUserByFre);
                        banUserByFre.setBounds(10, 160, banUserByFre.getPreferredSize().width, 25);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel18.getComponentCount(); i++) {
                                Rectangle bounds = panel18.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel18.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel18.setMinimumSize(preferredSize);
                            panel18.setPreferredSize(preferredSize);
                        }
                    }
                    clean.add(panel18);
                    panel18.setBounds(425, 15, 545, 200);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < clean.getComponentCount(); i++) {
                            Rectangle bounds = clean.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = clean.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        clean.setMinimumSize(preferredSize);
                        clean.setPreferredSize(preferredSize);
                    }
                }
                tabContain.addTab("\u76d1\u63a7", clean);

                //======== panel19 ========
                {
                    panel19.setLayout(null);

                    //======== panel20 ========
                    {
                        panel20.setBorder(new EtchedBorder());
                        panel20.setLayout(null);

                        //---- save4 ----
                        save4.setText("\u4fdd\u5b58");
                        save4.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                saveBan(e);
                            }
                        });
                        panel20.add(save4);
                        save4.setBounds(new Rectangle(new Point(35, 435), save4.getPreferredSize()));

                        //======== infoPanel4 ========
                        {
                            infoPanel4.setBorder(new BevelBorder(BevelBorder.LOWERED));
                            infoPanel4.setLayout(null);

                            //---- qqText4 ----
                            qqText4.setText("\u5f53\u524d\u9ab0\u5a18QQ\u53f7:");
                            infoPanel4.add(qqText4);
                            qqText4.setBounds(10, 10, 90, 25);

                            //---- nickText4 ----
                            nickText4.setText("\u5f53\u524d\u9ab0\u5a18\u6635\u79f0:");
                            infoPanel4.add(nickText4);
                            nickText4.setBounds(10, 70, nickText4.getPreferredSize().width, 25);

                            //---- qqValue4 ----
                            qqValue4.setText("text");
                            infoPanel4.add(qqValue4);
                            qqValue4.setBounds(10, 40, 140, 25);

                            //---- nickValue4 ----
                            nickValue4.setText("text");
                            infoPanel4.add(nickValue4);
                            nickValue4.setBounds(10, 100, 140, 25);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < infoPanel4.getComponentCount(); i++) {
                                    Rectangle bounds = infoPanel4.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = infoPanel4.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                infoPanel4.setMinimumSize(preferredSize);
                                infoPanel4.setPreferredSize(preferredSize);
                            }
                        }
                        panel20.add(infoPanel4);
                        infoPanel4.setBounds(5, 5, 145, 135);

                        //======== scrollPane16 ========
                        {

                            //---- textPane4 ----
                            textPane4.setText("\u8fd9\u91cc\u662f\u9ed1\u540d\u5355\u8bbe\u7f6e\u533a:\n\u6211\u4eec\u91c7\u7528\u4e91\u9ed1\u7b56\u7565\uff0c\u56e0\u6b64\u8fd9\u91cc\u4e0d\u4f1a\u7528\u6765\u663e\u793a\u9ed1\u540d\u5355\u5217\u8868\u2014\u2014\u522b\u4eba\u6dfb\u52a0\u7684\u9ed1\u540d\u5355\u4f60\u662f\u4e0d\u80fd\u4fee\u6539\u7684\uff0c\u540c\u6837\uff0c\u4f60\u6dfb\u52a0\u7684\u9ed1\u540d\u5355\u4e5f\u4e0d\u4f1a\u6709\u522b\u4eba\u642d\u4e2a\u9ab0\u5b50\u540e\u5077\u5077\u5220\u6389\u3002\n\u5f00\u542f\u4e91\u9ed1\u540d\u5355\u4e4b\u540e\uff0c\u6bcf15\u5206\u949f\u4f1a\u8bf7\u6c42\u4e00\u6b21\u8fdc\u7aef\u670d\u52a1\u5668\uff0c\u62c9\u53d6\u6700\u65b0\u7684\u9ed1\u540d\u5355\u5217\u8868\n\u800c\u624b\u5de5\u6dfb\u52a0\u3001\u5220\u9664\u9ed1\u540d\u5355\u4e00\u6837\u662f\u5141\u8bb8\u7684\uff0c\u4f60\u53ef\u4ee5\u5728\u300a\u5173\u4e8e\u300b\u9009\u9879\u5361\u7684\u6587\u6863\u4e2d\u627e\u5230\u76f8\u5e94\u547d\u4ee4\n\u5982\u679c\u4f60\u60f3\u8981\u7528\u79c1\u4eba\u7684\u9ed1\u540d\u5355\u63d2\u4ef6\uff0c\u90a3\u4e48\u53ea\u9700\u8981\u53d6\u6d88\u6389\u5de6\u4e0a\u65b9\u7684\u201c\u5f00\u542f\u4e91\u9ed1\u540d\u5355\u201d\u5373\u53ef");
                            scrollPane16.setViewportView(textPane4);
                        }
                        panel20.add(scrollPane16);
                        scrollPane16.setBounds(5, 145, 145, 275);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel20.getComponentCount(); i++) {
                                Rectangle bounds = panel20.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel20.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel20.setMinimumSize(preferredSize);
                            panel20.setPreferredSize(preferredSize);
                        }
                    }
                    panel19.add(panel20);
                    panel20.setBounds(10, 10, 155, 480);

                    //======== panel21 ========
                    {
                        panel21.setBorder(new EtchedBorder());
                        panel21.setLayout(null);

                        //---- cloudBan ----
                        cloudBan.setText("\u5f00\u542f\u4e91\u9ed1\u540d\u5355");
                        panel21.add(cloudBan);
                        cloudBan.setBounds(new Rectangle(new Point(10, 10), cloudBan.getPreferredSize()));

                        //---- ignoreBanUser ----
                        ignoreBanUser.setText("\u5f53\u9ed1\u540d\u5355\u7528\u6237\u5904\u4e8e\u767d\u540d\u5355\u7fa4\u4e2d\u65f6\uff0c\u5ffd\u7565\u6b64\u7528\u6237");
                        panel21.add(ignoreBanUser);
                        ignoreBanUser.setBounds(new Rectangle(new Point(10, 35), ignoreBanUser.getPreferredSize()));

                        //---- leaveByBanUser ----
                        leaveByBanUser.setText("\u5f53\u9ed1\u540d\u5355\u7528\u6237\u5904\u4e8e\u767d\u540d\u5355\u7fa4\u4e2d\u65f6\uff0c\u56e0\u6b64\u800c\u9000\u7fa4");
                        panel21.add(leaveByBanUser);
                        leaveByBanUser.setBounds(new Rectangle(new Point(10, 60), leaveByBanUser.getPreferredSize()));

                        //---- leaveGroupByBan ----
                        leaveGroupByBan.setText("\u81ea\u52a8\u9000\u51fa\u9ed1\u540d\u5355\u7fa4");
                        panel21.add(leaveGroupByBan);
                        leaveGroupByBan.setBounds(new Rectangle(new Point(10, 85), leaveGroupByBan.getPreferredSize()));

                        //---- banGroupBecauseBan ----
                        banGroupBecauseBan.setText("\u9000\u51fa\u5e76\u62c9\u9ed1\u88ab\u7981\u8a00\u7684\u7fa4");
                        panel21.add(banGroupBecauseBan);
                        banGroupBecauseBan.setBounds(new Rectangle(new Point(10, 110), banGroupBecauseBan.getPreferredSize()));

                        //---- banGroupBecauseReduce ----
                        banGroupBecauseReduce.setText("\u62c9\u9ed1\u88ab\u8e22\u51fa\u7684\u7fa4");
                        panel21.add(banGroupBecauseReduce);
                        banGroupBecauseReduce.setBounds(new Rectangle(new Point(10, 135), banGroupBecauseReduce.getPreferredSize()));

                        //---- banUserBecauseReduce ----
                        banUserBecauseReduce.setText("\u62c9\u9ed1\u8e22\u51fa\u81ea\u5df1\u7684\u7ba1\u7406\u5458");
                        panel21.add(banUserBecauseReduce);
                        banUserBecauseReduce.setBounds(new Rectangle(new Point(10, 160), banUserBecauseReduce.getPreferredSize()));

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel21.getComponentCount(); i++) {
                                Rectangle bounds = panel21.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel21.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel21.setMinimumSize(preferredSize);
                            panel21.setPreferredSize(preferredSize);
                        }
                    }
                    panel19.add(panel21);
                    panel21.setBounds(175, 15, 325, 190);

                    //======== panel23 ========
                    {
                        panel23.setBorder(new EtchedBorder());
                        panel23.setLayout(null);

                        //---- label60 ----
                        label60.setText("\u5165\u7fa4\u8bcd");
                        panel23.add(label60);
                        label60.setBounds(10, 10, 150, label60.getPreferredSize().height);

                        //======== scrollPane9 ========
                        {
                            scrollPane9.setViewportView(addGroup);
                        }
                        panel23.add(scrollPane9);
                        scrollPane9.setBounds(10, 30, 440, 100);

                        //---- label61 ----
                        label61.setText("\u52a0\u597d\u53cb\u6b22\u8fce\u8bcd");
                        panel23.add(label61);
                        label61.setBounds(new Rectangle(new Point(10, 140), label61.getPreferredSize()));

                        //======== scrollPane10 ========
                        {
                            scrollPane10.setViewportView(addFriend);
                        }
                        panel23.add(scrollPane10);
                        scrollPane10.setBounds(10, 165, 440, 100);
                        panel23.add(label62);
                        label62.setBounds(new Rectangle(new Point(10, 320), label62.getPreferredSize()));

                        //---- label63 ----
                        label63.setText("\u62d2\u7edd\u9ed1\u540d\u5355\u7fa4\u7528\u8bed");
                        panel23.add(label63);
                        label63.setBounds(new Rectangle(new Point(490, 10), label63.getPreferredSize()));

                        //---- label64 ----
                        label64.setText("\u62d2\u7edd\u9ed1\u540d\u5355\u597d\u53cb\u7528\u8bed");
                        panel23.add(label64);
                        label64.setBounds(new Rectangle(new Point(490, 140), label64.getPreferredSize()));

                        //======== scrollPane12 ========
                        {
                            scrollPane12.setViewportView(refuseGroupByBan);
                        }
                        panel23.add(scrollPane12);
                        scrollPane12.setBounds(490, 30, 440, 100);

                        //======== scrollPane13 ========
                        {
                            scrollPane13.setViewportView(refuseFriendByBan);
                        }
                        panel23.add(scrollPane13);
                        scrollPane13.setBounds(490, 165, 440, 100);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel23.getComponentCount(); i++) {
                                Rectangle bounds = panel23.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel23.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel23.setMinimumSize(preferredSize);
                            panel23.setPreferredSize(preferredSize);
                        }
                    }
                    panel19.add(panel23);
                    panel23.setBounds(175, 215, 970, 275);

                    //======== panel24 ========
                    {
                        panel24.setBorder(new EtchedBorder());
                        panel24.setLayout(null);

                        //---- label65 ----
                        label65.setText("\u9ed1\u540d\u5355\u5f55\u5165\u62a5\u9519:\u8f93\u5165\u7684\u4e0d\u662fQQ\u53f7\u6216\u7fa4\u53f7");
                        panel24.add(label65);
                        label65.setBounds(15, 15, label65.getPreferredSize().width, 25);
                        panel24.add(banListInputNotId);
                        banListInputNotId.setBounds(265, 15, 355, 25);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel24.getComponentCount(); i++) {
                                Rectangle bounds = panel24.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel24.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel24.setMinimumSize(preferredSize);
                            panel24.setPreferredSize(preferredSize);
                        }
                    }
                    panel19.add(panel24);
                    panel24.setBounds(505, 15, 635, 190);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel19.getComponentCount(); i++) {
                            Rectangle bounds = panel19.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel19.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel19.setMinimumSize(preferredSize);
                        panel19.setPreferredSize(preferredSize);
                    }
                }
                tabContain.addTab("\u4e91\u9ed1\u540d\u5355", panel19);

                //======== panel26 ========
                {
                    panel26.setLayout(null);

                    //---- label6 ----
                    label6.setText("\u8fd9\u91cc\u663e\u793a\u4e86\u63d2\u4ef6\u7684\u66f4\u65b0\u65e5\u5fd7");
                    panel26.add(label6);
                    label6.setBounds(new Rectangle(new Point(20, 15), label6.getPreferredSize()));

                    //======== scrollPane17 ========
                    {
                        scrollPane17.setViewportView(textPane5);
                    }
                    panel26.add(scrollPane17);
                    scrollPane17.setBounds(20, 50, 1115, 430);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel26.getComponentCount(); i++) {
                            Rectangle bounds = panel26.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel26.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel26.setMinimumSize(preferredSize);
                        panel26.setPreferredSize(preferredSize);
                    }
                }
                tabContain.addTab("\u66f4\u65b0\u65e5\u5fd7", panel26);

                //======== panel27 ========
                {
                    panel27.setLayout(null);

                    //---- label7 ----
                    label7.setText("\u9879\u76ee\u6587\u6863");
                    panel27.add(label7);
                    label7.setBounds(new Rectangle(new Point(535, 35), label7.getPreferredSize()));

                    //======== scrollPane18 ========
                    {

                        //---- textPane6 ----
                        textPane6.setText("https://sitcnya.gitbook.io/sinanya/");
                        scrollPane18.setViewportView(textPane6);
                    }
                    panel27.add(scrollPane18);
                    scrollPane18.setBounds(455, 60, 215, scrollPane18.getPreferredSize().height);

                    //---- label8 ----
                    label8.setText("\u9e23\u8c22");
                    panel27.add(label8);
                    label8.setBounds(new Rectangle(new Point(545, 100), label8.getPreferredSize()));

                    //======== scrollPane19 ========
                    {

                        //---- textPane7 ----
                        textPane7.setText("\u611f\u8c22\u4ee5\u4e0b\u4eba\u5458\u7684\u5927\u529b\u652f\u6301:\n\n\u7fb2\u3001Bright \u6211\u7684\u7b2c\u4e00\u6279\u7528\u6237\uff0c\u611f\u8c22\u4f60\u4eec\n\nshiki\u3001\u60e0\u60e0\uff0c\u4f60\u4eec\u7ed9\u6211\u63d0\u4f9b\u4e86\u5f88\u591a\u597d\u7684\u5efa\u8bae\u548c\u601d\u8def\n\n\u79c3\u4e86\u79c3\u4e86\u3001\u590f\u6d45\u79cb \u4f60\u4eec\u5236\u4f5c\u7684rule\u548cdnd\u6cd5\u672f\u5217\u8868\u6587\u672c\u5e2e\u52a9\u7684\u4e0d\u53ea\u662f\u6211\uff0c\u8fd8\u6709\u5f88\u591a\u4eba\n\n\u6708\u4e0a\u4e2d\u5929 \u6211\u6700\u68d2\u7684\u6d4b\u8bd5\u8005\uff0c\u5728\u6211\u7684\u9ab0\u5b50\u6068\u4e0d\u5f97\u4e00\u5929\u5d29\u6e835\u6b21\u7684\u65f6\u5019\uff0c\u575a\u6301\u4e3a\u6211\u6d4b\u8bd5\u5404\u79cd\u529f\u80fd\u3002\u6211\u4e5f\u627f\u8bfa\uff0c\u4f60\u7684\u9700\u6c42\u6211\u6c38\u8fdc\u6392\u5728\u6700\u524d\u9762\u5b8c\u6210\n\n\u8d1d\u5c14SAMA\u55b5 \u611f\u8c22\u4f60\u4e3a\u8fd9\u4e2a\u6838\u5fc3\u5236\u4f5c\u7684\u4eba\u7269\u5361\uff0c\u8f9b\u82e6\u4e86\n\n\u8fd8\u6709\u5f88\u591a\u6ca1\u60f3\u8d77\u6765\u7684\u4eba\uff0c\u8bf7\u968f\u65f6\u79c1\u804a\u63d0\u9192\u6211\u52a0\u5230\u8fd9\u91cc\u6765\u3002");
                        scrollPane19.setViewportView(textPane7);
                    }
                    panel27.add(scrollPane19);
                    scrollPane19.setBounds(295, 135, 550, 320);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel27.getComponentCount(); i++) {
                            Rectangle bounds = panel27.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel27.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel27.setMinimumSize(preferredSize);
                        panel27.setPreferredSize(preferredSize);
                    }
                }
                tabContain.addTab("\u5173\u4e8e", panel27);
            }
            windows.add(tabContain, BorderLayout.CENTER);

            //======== panel22 ========
            {
                panel22.setLayout(null);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel22.getComponentCount(); i++) {
                        Rectangle bounds = panel22.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel22.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel22.setMinimumSize(preferredSize);
                    panel22.setPreferredSize(preferredSize);
                }
            }
            windows.add(panel22, BorderLayout.NORTH);
            windows.pack();
            windows.setLocationRelativeTo(windows.getOwner());
        }

        //======== dialog1 ========
        {
            dialog1.setModal(true);
            Container dialog1ContentPane = dialog1.getContentPane();
            dialog1ContentPane.setLayout(null);

            //======== panel25 ========
            {
                panel25.setLayout(null);

                //---- label5 ----
                label5.setText("\u4fdd\u5b58\u6210\u529f");
                panel25.add(label5);
                label5.setBounds(140, 35, 60, 20);

                //---- button1 ----
                button1.setText("\u786e\u5b9a");
                button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                    }
                });
                panel25.add(button1);
                button1.setBounds(new Rectangle(new Point(130, 80), button1.getPreferredSize()));

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel25.getComponentCount(); i++) {
                        Rectangle bounds = panel25.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel25.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel25.setMinimumSize(preferredSize);
                    panel25.setPreferredSize(preferredSize);
                }
            }
            dialog1ContentPane.add(panel25);
            panel25.setBounds(0, 0, 335, 125);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < dialog1ContentPane.getComponentCount(); i++) {
                    Rectangle bounds = dialog1ContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = dialog1ContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                dialog1ContentPane.setMinimumSize(preferredSize);
                dialog1ContentPane.setPreferredSize(preferredSize);
            }
            dialog1.setSize(335, 150);
            dialog1.setLocationRelativeTo(null);
        }

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(banGroupAndUserByFre);
        buttonGroup1.add(banUserByFre);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void initData() {
        String qqId = String.valueOf(CQ.getLoginQQ());
        String nick = String.valueOf(CQ.getLoginNick());
        qqValue3.setText(qqId);
        qqValue4.setText(qqId);
        qqValue5.setText(qqId);
        qqValue6.setText(qqId);
        nickValue3.setText(nick);
        nickValue4.setText(nick);
        nickValue5.setText(nick);
        nickValue6.setText(nick);

        Prometheus.setSelected(entityBanProperties.isPrometheus());
        heap.setSelected(entityBanProperties.isHeap());
        master.setText(entityBanProperties.getMaster());
        notMaster.setText(entityBanProperties.getNotMaster());
        banListInputNotId.setText(entityBanProperties.getBanListInputNotId());
        prometheusPort.setText(String.valueOf(entityBanProperties.getPrometheusPort()));
        cloudBan.setSelected(entityBanProperties.isCloudBan());
        ignoreBanUser.setSelected(entityBanProperties.isIgnoreBanUser());
        leaveByBanUser.setSelected(entityBanProperties.isLeaveByBanUser());
        leaveGroupByBan.setSelected(entityBanProperties.isLeaveGroupByBan());
        banGroupBecauseBan.setSelected(entityBanProperties.isBanGroupBecauseBan());
        banGroupBecauseReduce.setSelected(entityBanProperties.isBanGroupBecauseReduce());
        banUserBecauseReduce.setSelected(entityBanProperties.isBanUserBecauseReduce());
        addGroup.setText(entityBanProperties.getAddGroup());
        addFriend.setText(entityBanProperties.getAddFriend());
        refuseGroupByBan.setText(entityBanProperties.getRefuseGroupByBan());
        refuseFriendByBan.setText(entityBanProperties.getRefuseFriendByBan());
        clearGroupByOff.setText(String.valueOf(entityBanProperties.getClearGroupByOff()));
        clearGroupByOffInfo.setText(String.valueOf(entityBanProperties.getClearGroupByOffInfo()));
        clearGroup.setText(String.valueOf(entityBanProperties.getClearGroup()));
        clearGroupInfo.setText(String.valueOf(entityBanProperties.getClearGroupInfo()));
        alterFrequentness.setText(String.valueOf(entityBanProperties.getAlterFrequentness()));
        banFrequentness.setText(String.valueOf(entityBanProperties.getBanFrequentness()));
        frequentnessAlterInfo.setText(String.valueOf(entityBanProperties.getFrequentnessAlterInfo()));
        frequentnessBanInfo.setText(String.valueOf(entityBanProperties.getFrequentnessBanInfo()));

        botStart.setText(entitySystemProperties.getBotStart());
        botAlreadyStart.setText(entitySystemProperties.getBotAlreadyStart());
        botStop.setText(entitySystemProperties.getBotStop());
        botAlreadyStop.setText(entitySystemProperties.getBotAlreadyStop());
        botExit.setText(entitySystemProperties.getBotExit());
        botInfo.setText(entitySystemProperties.getBotInfo());
        bookCard.setText(entitySystemProperties.getBookCard());
        bookRp.setText(entitySystemProperties.getBookRp());
        bookKp.setText(entitySystemProperties.getBookKp());
        bookMake.setText(entitySystemProperties.getBookMake());
        setPropFormat.setText(entitySystemProperties.getSetPropFormat());
        setHelp.setText(entitySystemProperties.getSetHelp());
        notFoundSkill.setText(entitySystemProperties.getNotFoundSkill());
        setPropSuccess.setText(entitySystemProperties.getSetPropSuccess());
        manyRollsFormat.setText(entitySystemProperties.getManyRollsFormat());
        diceTimesTooBig.setText(entitySystemProperties.getDiceTimesTooBig());
        needKpGroup.setText(entitySystemProperties.getNeedKpGroup());
        cantInPrivate.setText(entitySystemProperties.getCantInPrivate());
        onlyManager.setText(entitySystemProperties.getOnlyManager());
        alreadyOpen.setText(entitySystemProperties.getAlreadyOpen());
        alreadyClose.setText(entitySystemProperties.getAlreadyClose());
        notFoundLog.setText(entitySystemProperties.getNotFoundLog());
        readLock.setText(entitySystemProperties.getReadLock());
        deleteOpenLog.setText(entitySystemProperties.getDeleteOpenLog());
        appendLog.setText(entitySystemProperties.getAppendLog());
        createLog.setText(entitySystemProperties.getCreateLog());
        cantEmptyLogName.setText(entitySystemProperties.getCantEmptyLogName());
        dndInitIsEmtpy.setText(entitySystemProperties.getDndInitIsEmtpy());
        clrDndInit.setText(entitySystemProperties.getClrDndInit());
        antagonizeOver.setText(entitySystemProperties.getAntagonizeOver());
        antagonizeFirstSuccess.setText(entitySystemProperties.getAntagonizeFirstSuccess());
        antagonizeSecondSuccess.setText(entitySystemProperties.getAntagonizeSecondSuccess());
        antagonizeAllFailed.setText(entitySystemProperties.getAntagonizeAllFailed());
        antagonizeDraw.setText(entitySystemProperties.getAntagonizeDraw());
        sanCheck.setText(entitySystemProperties.getSanCheck());
        symptom.setText(entitySystemProperties.getSymptom());
        sanCheckFumble.setText(entitySystemProperties.getSanCheckFumble());
        sanCheckCriticalSuccess.setText(entitySystemProperties.getSanCheckCriticalSuccess());
        sanCheckSuccess.setText(entitySystemProperties.getSanCheckSuccess());
        sanCheckFailure.setText(entitySystemProperties.getSanCheckFailure());
        enSuccess.setText(entitySystemProperties.getEnSuccess());
        enFailed.setText(entitySystemProperties.getEnFailed());
        hiddenDice.setText(entitySystemProperties.getHiddenDice());
        teamIsEmpty.setText(entitySystemProperties.getTeamIsEmpty());
        teamMemberEnIsEmpty.setText(entitySystemProperties.getTeamMemberEnIsEmpty());
        criticalSuccess.setText(entitySystemProperties.getCRITICAL_SUCCESS());
        extremeSuccess.setText(entitySystemProperties.getEXTREME_SUCCESS());
        headSuccess.setText(entitySystemProperties.getHARD_SUCCESS());
        success.setText(entitySystemProperties.getSUCCESS());
        failure.setText(entitySystemProperties.getFAILURE());
        fumble.setText(entitySystemProperties.getFUMBLE());
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private Frame windows;
    private JTabbedPane tabContain;
    private JPanel setting;
    private JPanel panel3;
    private JButton save;
    private JPanel infoPanel6;
    private JLabel qqText6;
    private JLabel nickText6;
    private JLabel qqValue6;
    private JLabel nickValue6;
    private JScrollPane scrollPane11;
    private JTextPane textPane1;
    private JScrollPane scrollPane1;
    private JPanel panel2;
    private JPanel panel6;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JTextField setPropFormat;
    private JTextField setHelp;
    private JTextField notFoundSkill;
    private JTextField setPropSuccess;
    private JPanel panel13;
    private JLabel label45;
    private JLabel label46;
    private JLabel label47;
    private JTextField enSuccess;
    private JTextField enFailed;
    private JTextField hiddenDice;
    private JPanel panel5;
    private JLabel botStartLable;
    private JLabel botAlreadyStartLable;
    private JLabel botStopLable;
    private JLabel botAlreadyStopLable;
    private JLabel label9;
    private JLabel label10;
    private JTextField botStart;
    private JTextField botAlreadyStart;
    private JTextField botStop;
    private JTextField botAlreadyStop;
    private JTextField botExit;
    private JTextField botInfo;
    private JPanel panel12;
    private JLabel label39;
    private JLabel label40;
    private JLabel label41;
    private JLabel label42;
    private JLabel label43;
    private JLabel label44;
    private JTextField sanCheck;
    private JTextField symptom;
    private JTextField sanCheckCriticalSuccess;
    private JTextField sanCheckFumble;
    private JTextField sanCheckSuccess;
    private JTextField sanCheckFailure;
    private JPanel panel9;
    private JLabel label24;
    private JLabel label25;
    private JLabel label26;
    private JLabel label27;
    private JLabel label28;
    private JLabel label29;
    private JTextField createLog;
    private JTextField appendLog;
    private JTextField alreadyOpen;
    private JTextField alreadyClose;
    private JTextField notFoundLog;
    private JTextField cantEmptyLogName;
    private JLabel label30;
    private JLabel label31;
    private JTextField deleteOpenLog;
    private JTextField readLock;
    private JPanel panel8;
    private JLabel label19;
    private JLabel label20;
    private JLabel label21;
    private JLabel label22;
    private JLabel label23;
    private JTextField manyRollsFormat;
    private JTextField diceTimesTooBig;
    private JTextField needKpGroup;
    private JTextField cantInPrivate;
    private JTextField onlyManager;
    private JPanel panel10;
    private JLabel label32;
    private JLabel label33;
    private JTextField dndInitIsEmtpy;
    private JTextField clrDndInit;
    private JPanel panel14;
    private JLabel label48;
    private JLabel label49;
    private JTextField teamIsEmpty;
    private JTextField teamMemberEnIsEmpty;
    private JPanel panel7;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JTextField bookCard;
    private JTextField bookRp;
    private JTextField bookKp;
    private JTextField bookMake;
    private JPanel panel11;
    private JLabel label34;
    private JLabel label35;
    private JLabel label36;
    private JLabel label37;
    private JTextField antagonizeOver;
    private JTextField antagonizeFirstSuccess;
    private JTextField antagonizeSecondSuccess;
    private JTextField antagonizeAllFailed;
    private JLabel label38;
    private JTextField antagonizeDraw;
    private JPanel panel1;
    private JPanel panel4;
    private JButton save2;
    private JPanel infoPanel5;
    private JLabel qqText5;
    private JLabel nickText5;
    private JLabel qqValue5;
    private JLabel nickValue5;
    private JScrollPane scrollPane14;
    private JTextPane textPane2;
    private JPanel panel15;
    private JLabel label1;
    private JScrollPane scrollPane2;
    private JTextArea criticalSuccess;
    private JLabel label2;
    private JScrollPane scrollPane3;
    private JTextArea extremeSuccess;
    private JLabel label3;
    private JScrollPane scrollPane4;
    private JTextArea headSuccess;
    private JLabel label4;
    private JLabel label50;
    private JLabel label51;
    private JScrollPane scrollPane5;
    private JTextArea success;
    private JScrollPane scrollPane6;
    private JTextArea failure;
    private JScrollPane scrollPane7;
    private JTextArea fumble;
    private JPanel clean;
    private JPanel monitorPanel2;
    private JLabel promethusPortText2;
    private JTextField prometheusPort;
    private JCheckBox Prometheus;
    private JCheckBox heap;
    private JPanel setMaster;
    private JLabel masterText;
    private JLabel notMasterInfoText;
    private JTextField master;
    private JScrollPane scrollPane8;
    private JTextArea notMaster;
    private JPanel panel16;
    private JPanel infoPanel3;
    private JLabel qqText3;
    private JLabel nickText3;
    private JLabel qqValue3;
    private JLabel nickValue3;
    private JButton save3;
    private JScrollPane scrollPane15;
    private JTextPane textPane3;
    private JPanel panel17;
    private JLabel label52;
    private JLabel label53;
    private JLabel label54;
    private JLabel label55;
    private JTextField clearGroupByOff;
    private JTextField clearGroupByOffInfo;
    private JTextField clearGroup;
    private JTextField clearGroupInfo;
    private JPanel panel18;
    private JLabel label56;
    private JLabel label57;
    private JLabel label58;
    private JLabel label59;
    private JTextField alterFrequentness;
    private JTextField frequentnessAlterInfo;
    private JTextField banFrequentness;
    private JTextField frequentnessBanInfo;
    private JRadioButton banGroupAndUserByFre;
    private JRadioButton banUserByFre;
    private JPanel panel19;
    private JPanel panel20;
    private JButton save4;
    private JPanel infoPanel4;
    private JLabel qqText4;
    private JLabel nickText4;
    private JLabel qqValue4;
    private JLabel nickValue4;
    private JScrollPane scrollPane16;
    private JTextPane textPane4;
    private JPanel panel21;
    private JCheckBox cloudBan;
    private JCheckBox ignoreBanUser;
    private JCheckBox leaveByBanUser;
    private JCheckBox leaveGroupByBan;
    private JCheckBox banGroupBecauseBan;
    private JCheckBox banGroupBecauseReduce;
    private JCheckBox banUserBecauseReduce;
    private JPanel panel23;
    private JLabel label60;
    private JScrollPane scrollPane9;
    private JTextArea addGroup;
    private JLabel label61;
    private JScrollPane scrollPane10;
    private JTextArea addFriend;
    private JLabel label62;
    private JLabel label63;
    private JLabel label64;
    private JScrollPane scrollPane12;
    private JTextArea refuseGroupByBan;
    private JScrollPane scrollPane13;
    private JTextArea refuseFriendByBan;
    private JPanel panel24;
    private JLabel label65;
    private JTextField banListInputNotId;
    private JPanel panel26;
    private JLabel label6;
    private JScrollPane scrollPane17;
    private JTextPane textPane5;
    private JPanel panel27;
    private JLabel label7;
    private JScrollPane scrollPane18;
    private JTextPane textPane6;
    private JLabel label8;
    private JScrollPane scrollPane19;
    private JTextPane textPane7;
    private JPanel panel22;
    private JDialog dialog1;
    private JPanel panel25;
    private JLabel label5;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
