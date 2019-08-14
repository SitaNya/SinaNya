package dice.sinanya.windows;


import dice.sinanya.db.properties.ban.InsertProperties;
import dice.sinanya.tools.windows.Frame;
import dice.sinanya.windows.imal.MakeBan;

import javax.swing.*;
import java.awt.*;

import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityBanProperties;


/**
 * @author SitaNya
 * @date 2019-08-13
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class BanProperties extends MakeBan {
    static JCheckBox jCheckBox;

    public BanProperties() {
    }

    public static int getLength(String text) {
        return text.length() * 12;
    }

    public void init() {
        JFrame jFrame = new Frame("安全性配置", 1200, 600).init();
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

        JPanel monitorPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "监控功能", 0, 70, 400, 100);
        monitorPanel.setLayout(new GridLayout(0, 2));
        monitor(monitorPanel);

        JPanel masterPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "管理员信息", 0, 170, 400, 150);
        masterPanel.setLayout(new GridLayout(0, 2));
        master(masterPanel);

        JPanel cloudBanPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "云黑名单", 0, 320, 400, 250);
        cloudBanPanel.setLayout(new GridLayout(0, 1));
        cloudBan(cloudBanPanel);

        JPanel clearInfoPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "清理", 400, 0, 400, 200);
        clearInfoPanel.setLayout(new GridLayout(0, 2));
        clearInfo(clearInfoPanel);

        JPanel cloudBanInfoPanel = new dice.sinanya.tools.windows.Panel().init(jFrame, "用语", 800, 0, 400, 600);
        cloudBanInfoPanel.setLayout(new GridLayout(0, 2));
        cloudBanInfo(cloudBanInfoPanel);

    }

    private void save(JFrame jFrame) {
        createButton(jFrame, "保存", 400, 300);
    }

    public void createButton(JFrame jFrame, String text, int x, int y) {
        JButton jButton = new JButton(text);
        jButton.setBounds(x, y, 200, 100);
        jButton.addActionListener(e -> {
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
            new InsertProperties().insertProperties(entityBanProperties);
            JOptionPane.showMessageDialog(null, "保存成功");
        });
        jFrame.add(jButton);
    }
}
