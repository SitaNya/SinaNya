/*
 * Created by JFormDesigner on Mon Aug 19 17:26:35 CST 2019
 */

package dice.sinanya.windows;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static dice.sinanya.tools.getinfo.GetMessagesProperties.entityBanProperties;

/**
 * @author unknown
 */
public class test extends JFrame {
    public test() {
        initComponents();
    }

    private void save(MouseEvent e) {

        JOptionPane.showMessageDialog(null, "保存成功");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        frame1 = new Frame();
        tabbedPane1 = new JTabbedPane();
        monitor = new JPanel();
        qqText = new JLabel();
        nickText = new JLabel();
        qqValue = new JLabel();
        nickValue = new JLabel();
        infoPanel = new JPanel();
        save = new JButton();
        panel5 = new JPanel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        textField6 = new JTextField();
        panel6 = new JPanel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        textField7 = new JTextField();
        textField8 = new JTextField();
        textField9 = new JTextField();
        textField10 = new JTextField();
        panel7 = new JPanel();
        label15 = new JLabel();
        label16 = new JLabel();
        label17 = new JLabel();
        label18 = new JLabel();
        textField11 = new JTextField();
        textField12 = new JTextField();
        textField13 = new JTextField();
        textField14 = new JTextField();
        panel8 = new JPanel();
        label19 = new JLabel();
        label20 = new JLabel();
        label21 = new JLabel();
        label22 = new JLabel();
        label23 = new JLabel();
        textField15 = new JTextField();
        textField16 = new JTextField();
        textField17 = new JTextField();
        textField18 = new JTextField();
        textField19 = new JTextField();
        panel9 = new JPanel();
        label24 = new JLabel();
        label25 = new JLabel();
        label26 = new JLabel();
        label27 = new JLabel();
        label28 = new JLabel();
        label29 = new JLabel();
        textField20 = new JTextField();
        textField21 = new JTextField();
        textField22 = new JTextField();
        textField23 = new JTextField();
        textField24 = new JTextField();
        textField25 = new JTextField();
        label30 = new JLabel();
        label31 = new JLabel();
        textField26 = new JTextField();
        textField27 = new JTextField();
        scrollPane1 = new JScrollPane();
        scrollBar1 = new JScrollBar();
        clean = new JPanel();
        monitorPanel2 = new JPanel();
        promethusActive2 = new JRadioButtonMenuItem();
        promethusPortText2 = new JLabel();
        promethusPortValue2 = new JTextField();
        heapActive2 = new JRadioButtonMenuItem();
        setMaster = new JPanel();
        masterText = new JLabel();
        notMasterInfoText = new JLabel();
        masterValue = new JTextField();
        notMasterInfoValue = new JFormattedTextField();

        //======== frame1 ========
        {
            frame1.setLayout(null);

            //======== tabbedPane1 ========
            {

                //======== monitor ========
                {
                    monitor.setPreferredSize(new Dimension(625, 495));
                    monitor.setLayout(null);

                    //---- qqText ----
                    qqText.setText("\u5f53\u524d\u9ab0\u5a18QQ\u53f7:");
                    monitor.add(qqText);
                    qqText.setBounds(20, 15, 90, 20);

                    //---- nickText ----
                    nickText.setText("\u5f53\u524d\u9ab0\u5a18\u6635\u79f0:");
                    monitor.add(nickText);
                    nickText.setBounds(20, 40, 90, 20);

                    //---- qqValue ----
                    qqValue.setText("text");
                    monitor.add(qqValue);
                    qqValue.setBounds(115, 15, 50, qqValue.getPreferredSize().height);

                    //---- nickValue ----
                    nickValue.setText("text");
                    monitor.add(nickValue);
                    nickValue.setBounds(115, 45, 40, 16);

                    //======== infoPanel ========
                    {
                        infoPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                        infoPanel.setLayout(null);

                        {
                            // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < infoPanel.getComponentCount(); i++) {
                                Rectangle bounds = infoPanel.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = infoPanel.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            infoPanel.setMinimumSize(preferredSize);
                            infoPanel.setPreferredSize(preferredSize);
                        }
                    }
                    monitor.add(infoPanel);
                    infoPanel.setBounds(15, 10, 135, 55);

                    //---- save ----
                    save.setText("\u4fdd\u5b58");
                    save.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            save(e);
                        }
                    });
                    monitor.add(save);
                    save.setBounds(new Rectangle(new Point(15, 455), save.getPreferredSize()));

                    //======== panel5 ========
                    {
                        panel5.setBorder(new EtchedBorder());
                        panel5.setToolTipText("\u673a\u5668\u4eba\u5e38\u7528\u4fe1\u606f");
                        panel5.setLayout(null);

                        //---- label5 ----
                        label5.setText("\u5f00\u542f\u4fe1\u606f");
                        panel5.add(label5);
                        label5.setBounds(10, 10, label5.getPreferredSize().width, 25);

                        //---- label6 ----
                        label6.setText("\u5df2\u5f00\u542f\u63d0\u793a");
                        panel5.add(label6);
                        label6.setBounds(10, 40, label6.getPreferredSize().width, 20);

                        //---- label7 ----
                        label7.setText("\u5173\u95ed\u4fe1\u606f");
                        panel5.add(label7);
                        label7.setBounds(10, 70, label7.getPreferredSize().width, 20);

                        //---- label8 ----
                        label8.setText("\u5df2\u5173\u95ed\u63d0\u793a");
                        panel5.add(label8);
                        label8.setBounds(10, 100, label8.getPreferredSize().width, 20);

                        //---- label9 ----
                        label9.setText("\u9000\u7fa4\u63d0\u793a");
                        panel5.add(label9);
                        label9.setBounds(10, 130, label9.getPreferredSize().width, 20);

                        //---- label10 ----
                        label10.setText("\u673a\u5668\u4eba\u4fe1\u606f\u63d0\u793a");
                        panel5.add(label10);
                        label10.setBounds(10, 160, label10.getPreferredSize().width, 20);
                        panel5.add(textField1);
                        textField1.setBounds(105, 10, 340, 25);
                        panel5.add(textField2);
                        textField2.setBounds(105, 40, 340, 25);
                        panel5.add(textField3);
                        textField3.setBounds(105, 70, 340, 25);
                        panel5.add(textField4);
                        textField4.setBounds(105, 100, 340, 25);
                        panel5.add(textField5);
                        textField5.setBounds(105, 130, 340, 25);
                        panel5.add(textField6);
                        textField6.setBounds(105, 160, 340, 25);

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
                    monitor.add(panel5);
                    panel5.setBounds(165, 10, 460, 195);

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
                        panel6.add(textField7);
                        textField7.setBounds(145, 10, 300, 25);
                        panel6.add(textField8);
                        textField8.setBounds(145, 40, 300, 25);
                        panel6.add(textField9);
                        textField9.setBounds(145, 70, 300, 25);
                        panel6.add(textField10);
                        textField10.setBounds(145, 100, 300, 25);

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
                    monitor.add(panel6);
                    panel6.setBounds(165, 215, 460, 135);

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
                        panel7.add(textField11);
                        textField11.setBounds(145, 10, 300, 25);
                        panel7.add(textField12);
                        textField12.setBounds(145, 40, 300, 25);
                        panel7.add(textField13);
                        textField13.setBounds(145, 70, 300, 25);
                        panel7.add(textField14);
                        textField14.setBounds(145, 100, 300, 25);

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
                    monitor.add(panel7);
                    panel7.setBounds(165, 360, 460, 135);

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
                        panel8.add(textField15);
                        textField15.setBounds(175, 10, 270, 25);
                        panel8.add(textField16);
                        textField16.setBounds(175, 40, 270, 25);
                        panel8.add(textField17);
                        textField17.setBounds(175, 70, 270, 25);
                        panel8.add(textField18);
                        textField18.setBounds(175, 100, 270, 25);
                        panel8.add(textField19);
                        textField19.setBounds(175, 130, 270, 25);

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
                    monitor.add(panel8);
                    panel8.setBounds(635, 10, 460, 165);

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
                        panel9.add(textField20);
                        textField20.setBounds(150, 10, 290, 25);
                        panel9.add(textField21);
                        textField21.setBounds(150, 40, 290, 25);
                        panel9.add(textField22);
                        textField22.setBounds(150, 70, 290, 25);
                        panel9.add(textField23);
                        textField23.setBounds(150, 100, 290, 25);
                        panel9.add(textField24);
                        textField24.setBounds(150, 130, 290, 25);
                        panel9.add(textField25);
                        textField25.setBounds(150, 160, 290, 25);

                        //---- label30 ----
                        label30.setText("\u65e0\u6cd5\u5220\u9664\u5df2\u5f00\u542f\u7684\u65e5\u5fd7");
                        panel9.add(label30);
                        label30.setBounds(10, 190, label30.getPreferredSize().width, 20);

                        //---- label31 ----
                        label31.setText("\u83b7\u53d6\u65e5\u5fd7\u961f\u5217\u5835\u585e");
                        panel9.add(label31);
                        label31.setBounds(10, 220, label31.getPreferredSize().width, 20);
                        panel9.add(textField26);
                        textField26.setBounds(150, 190, 290, 25);
                        panel9.add(textField27);
                        textField27.setBounds(150, 220, 290, 25);

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
                    monitor.add(panel9);
                    panel9.setBounds(635, 185, 460, 250);

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(scrollBar1);
                    }
                    monitor.add(scrollPane1);
                    scrollPane1.setBounds(160, 0, 945, 500);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < monitor.getComponentCount(); i++) {
                            Rectangle bounds = monitor.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = monitor.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        monitor.setMinimumSize(preferredSize);
                        monitor.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("\u57fa\u7840\u4fe1\u606f", monitor);

                //======== clean ========
                {
                    clean.setLayout(null);

                    //======== monitorPanel2 ========
                    {
                        monitorPanel2.setToolTipText("\u76d1\u63a7\u4fe1\u606f");
                        monitorPanel2.setBorder(new EtchedBorder());
                        monitorPanel2.setLayout(null);

                        //---- promethusActive2 ----
                        promethusActive2.setText("\u542f\u7528\u666e\u7f57\u7c73\u4fee\u65af\u76d1\u63a7\u4e0a\u62a5");
                        monitorPanel2.add(promethusActive2);
                        promethusActive2.setBounds(10, 10, 170, 20);

                        //---- promethusPortText2 ----
                        promethusPortText2.setText("\u666e\u7f57\u7c73\u4fee\u65af\u81ea\u5b9a\u4e49\u7aef\u53e3");
                        monitorPanel2.add(promethusPortText2);
                        promethusPortText2.setBounds(10, 40, promethusPortText2.getPreferredSize().width, 20);
                        monitorPanel2.add(promethusPortValue2);
                        promethusPortValue2.setBounds(140, 40, 40, 20);

                        //---- heapActive2 ----
                        heapActive2.setText("\u542f\u7528\u5fc3\u8df3\u62a5\u544a");
                        monitorPanel2.add(heapActive2);
                        heapActive2.setBounds(10, 70, 170, 20);

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
                    monitorPanel2.setBounds(0, 190, 190, 100);

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
                        setMaster.add(masterValue);
                        masterValue.setBounds(80, 10, 144, masterValue.getPreferredSize().height);
                        setMaster.add(notMasterInfoValue);
                        notMasterInfoValue.setBounds(15, 70, 200, 75);

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
                    setMaster.setBounds(0, 10, 235, 165);

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
                tabbedPane1.addTab("\u76d1\u63a7", clean);
            }
            frame1.add(tabbedPane1);
            tabbedPane1.setBounds(25, 30, 1105, 530);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < frame1.getComponentCount(); i++) {
                    Rectangle bounds = frame1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = frame1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                frame1.setMinimumSize(preferredSize);
                frame1.setPreferredSize(preferredSize);
            }
            frame1.pack();
            frame1.setLocationRelativeTo(frame1.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private Frame frame1;
    private JTabbedPane tabbedPane1;
    private JPanel monitor;
    private JLabel qqText;
    private JLabel nickText;
    private JLabel qqValue;
    private JLabel nickValue;
    private JPanel infoPanel;
    private JButton save;
    private JPanel panel5;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JPanel panel6;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JPanel panel7;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JPanel panel8;
    private JLabel label19;
    private JLabel label20;
    private JLabel label21;
    private JLabel label22;
    private JLabel label23;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JTextField textField18;
    private JTextField textField19;
    private JPanel panel9;
    private JLabel label24;
    private JLabel label25;
    private JLabel label26;
    private JLabel label27;
    private JLabel label28;
    private JLabel label29;
    private JTextField textField20;
    private JTextField textField21;
    private JTextField textField22;
    private JTextField textField23;
    private JTextField textField24;
    private JTextField textField25;
    private JLabel label30;
    private JLabel label31;
    private JTextField textField26;
    private JTextField textField27;
    private JScrollPane scrollPane1;
    private JScrollBar scrollBar1;
    private JPanel clean;
    private JPanel monitorPanel2;
    private JRadioButtonMenuItem promethusActive2;
    private JLabel promethusPortText2;
    private JTextField promethusPortValue2;
    private JRadioButtonMenuItem heapActive2;
    private JPanel setMaster;
    private JLabel masterText;
    private JLabel notMasterInfoText;
    private JTextField masterValue;
    private JFormattedTextField notMasterInfoValue;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
