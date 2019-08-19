/*
 * Created by JFormDesigner on Mon Aug 19 17:26:35 CST 2019
 */

package dice.sinanya.windows;

import javax.swing.*;
import javax.swing.border.*;
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
        windows = new Frame();
        tabContain = new JTabbedPane();
        setting = new JPanel();
        panel3 = new JPanel();
        infoPanel = new JPanel();
        qqText = new JLabel();
        nickText = new JLabel();
        qqValue = new JLabel();
        nickValue = new JLabel();
        save = new JButton();
        scrollPane1 = new JScrollPane();
        panel2 = new JPanel();
        panel6 = new JPanel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        textField7 = new JTextField();
        textField8 = new JTextField();
        textField9 = new JTextField();
        textField10 = new JTextField();
        panel13 = new JPanel();
        label45 = new JLabel();
        label46 = new JLabel();
        label47 = new JLabel();
        textField41 = new JTextField();
        textField42 = new JTextField();
        textField43 = new JTextField();
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
        panel12 = new JPanel();
        label39 = new JLabel();
        label40 = new JLabel();
        label41 = new JLabel();
        label42 = new JLabel();
        label43 = new JLabel();
        label44 = new JLabel();
        textField35 = new JTextField();
        textField36 = new JTextField();
        textField37 = new JTextField();
        textField38 = new JTextField();
        textField39 = new JTextField();
        textField40 = new JTextField();
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
        panel10 = new JPanel();
        label32 = new JLabel();
        label33 = new JLabel();
        textField28 = new JTextField();
        textField29 = new JTextField();
        panel14 = new JPanel();
        label48 = new JLabel();
        label49 = new JLabel();
        textField44 = new JTextField();
        textField45 = new JTextField();
        panel7 = new JPanel();
        label15 = new JLabel();
        label16 = new JLabel();
        label17 = new JLabel();
        label18 = new JLabel();
        textField11 = new JTextField();
        textField12 = new JTextField();
        textField13 = new JTextField();
        textField14 = new JTextField();
        panel11 = new JPanel();
        label34 = new JLabel();
        label35 = new JLabel();
        label36 = new JLabel();
        label37 = new JLabel();
        textField30 = new JTextField();
        textField31 = new JTextField();
        textField32 = new JTextField();
        textField33 = new JTextField();
        label38 = new JLabel();
        textField34 = new JTextField();
        panel1 = new JPanel();
        panel4 = new JPanel();
        infoPanel2 = new JPanel();
        qqText2 = new JLabel();
        nickText2 = new JLabel();
        qqValue2 = new JLabel();
        nickValue2 = new JLabel();
        save2 = new JButton();
        panel15 = new JPanel();
        label1 = new JLabel();
        scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();
        label2 = new JLabel();
        scrollPane3 = new JScrollPane();
        textArea2 = new JTextArea();
        label3 = new JLabel();
        scrollPane4 = new JScrollPane();
        textArea3 = new JTextArea();
        label4 = new JLabel();
        label50 = new JLabel();
        label51 = new JLabel();
        scrollPane5 = new JScrollPane();
        textArea4 = new JTextArea();
        scrollPane6 = new JScrollPane();
        textArea5 = new JTextArea();
        scrollPane7 = new JScrollPane();
        textArea6 = new JTextArea();
        clean = new JPanel();
        monitorPanel2 = new JPanel();
        promethusPortText2 = new JLabel();
        promethusPortValue2 = new JTextField();
        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        setMaster = new JPanel();
        masterText = new JLabel();
        notMasterInfoText = new JLabel();
        masterValue = new JTextField();
        scrollPane8 = new JScrollPane();
        textArea7 = new JTextArea();
        panel16 = new JPanel();
        infoPanel3 = new JPanel();
        qqText3 = new JLabel();
        nickText3 = new JLabel();
        qqValue3 = new JLabel();
        nickValue3 = new JLabel();
        save3 = new JButton();
        panel17 = new JPanel();
        label52 = new JLabel();
        label53 = new JLabel();
        label54 = new JLabel();
        label55 = new JLabel();
        textField46 = new JTextField();
        textField47 = new JTextField();
        textField48 = new JTextField();
        textField49 = new JTextField();
        panel18 = new JPanel();
        label56 = new JLabel();
        label57 = new JLabel();
        label58 = new JLabel();
        label59 = new JLabel();
        textField50 = new JTextField();
        textField51 = new JTextField();
        textField52 = new JTextField();
        textField53 = new JTextField();
        panel19 = new JPanel();
        panel20 = new JPanel();
        infoPanel4 = new JPanel();
        qqText4 = new JLabel();
        nickText4 = new JLabel();
        qqValue4 = new JLabel();
        nickValue4 = new JLabel();
        save4 = new JButton();
        panel21 = new JPanel();
        checkBox3 = new JCheckBox();
        checkBox4 = new JCheckBox();

        //======== windows ========
        {
            windows.setTitle("SinaNya\u8dd1\u56e2\u9ab0\u70b9\u6838\u5fc3 By SitaNya");
            windows.setLayout(null);

            //======== tabContain ========
            {

                //======== setting ========
                {
                    setting.setLayout(null);

                    //======== panel3 ========
                    {
                        panel3.setBorder(new EtchedBorder());
                        panel3.setLayout(null);

                        //======== infoPanel ========
                        {
                            infoPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                            infoPanel.setLayout(null);

                            //---- qqText ----
                            qqText.setText("\u5f53\u524d\u9ab0\u5a18QQ\u53f7:");
                            infoPanel.add(qqText);
                            qqText.setBounds(5, 5, 90, 20);

                            //---- nickText ----
                            nickText.setText("\u5f53\u524d\u9ab0\u5a18\u6635\u79f0:");
                            infoPanel.add(nickText);
                            nickText.setBounds(5, 40, 90, 20);

                            //---- qqValue ----
                            qqValue.setText("text");
                            infoPanel.add(qqValue);
                            qqValue.setBounds(20, 25, 50, qqValue.getPreferredSize().height);

                            //---- nickValue ----
                            nickValue.setText("text");
                            infoPanel.add(nickValue);
                            nickValue.setBounds(15, 65, 40, 16);

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
                        panel3.add(infoPanel);
                        infoPanel.setBounds(5, 5, 145, 105);

                        //---- save ----
                        save.setText("\u4fdd\u5b58");
                        save.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                save(e);
                            }
                        });
                        panel3.add(save);
                        save.setBounds(new Rectangle(new Point(35, 435), save.getPreferredSize()));

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
                                panel13.add(textField41);
                                textField41.setBounds(105, 10, 340, 25);
                                panel13.add(textField42);
                                textField42.setBounds(105, 40, 340, 25);
                                panel13.add(textField43);
                                textField43.setBounds(105, 70, 340, 25);

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
                                panel12.add(textField35);
                                textField35.setBounds(105, 10, 340, 25);
                                panel12.add(textField36);
                                textField36.setBounds(105, 40, 340, 25);
                                panel12.add(textField37);
                                textField37.setBounds(105, 70, 340, 25);
                                panel12.add(textField38);
                                textField38.setBounds(105, 100, 340, 25);
                                panel12.add(textField39);
                                textField39.setBounds(105, 130, 340, 25);
                                panel12.add(textField40);
                                textField40.setBounds(105, 160, 340, 25);

                                {
                                    // compute preferred size
                                    Dimension preferredSize = new Dimension();
                                    for(int i = 0; i < panel12.getComponentCount(); i++) {
                                        Rectangle bounds = panel12.getComponent(i).getBounds();
                                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                    }
                                    Insets insets = panel12.getInsets();
                                    preferredSize.width += insets.right;
                                    preferredSize.height += insets.bottom;
                                    panel12.setMinimumSize(preferredSize);
                                    panel12.setPreferredSize(preferredSize);
                                }
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
                                panel10.add(textField28);
                                textField28.setBounds(145, 10, 300, 25);
                                panel10.add(textField29);
                                textField29.setBounds(145, 40, 300, 25);

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
                                panel14.add(textField44);
                                textField44.setBounds(105, 10, 340, 25);
                                panel14.add(textField45);
                                textField45.setBounds(105, 40, 340, 25);

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
                                panel11.add(textField30);
                                textField30.setBounds(145, 10, 300, 25);
                                panel11.add(textField31);
                                textField31.setBounds(145, 40, 300, 25);
                                panel11.add(textField32);
                                textField32.setBounds(145, 70, 300, 25);
                                panel11.add(textField33);
                                textField33.setBounds(145, 100, 300, 25);

                                //---- label38 ----
                                label38.setText("\u5e73\u624b");
                                panel11.add(label38);
                                label38.setBounds(0, 130, 117, 25);
                                panel11.add(textField34);
                                textField34.setBounds(150, 130, 300, 25);

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

                        //======== infoPanel2 ========
                        {
                            infoPanel2.setBorder(new BevelBorder(BevelBorder.LOWERED));
                            infoPanel2.setLayout(null);

                            //---- qqText2 ----
                            qqText2.setText("\u5f53\u524d\u9ab0\u5a18QQ\u53f7:");
                            infoPanel2.add(qqText2);
                            qqText2.setBounds(5, 5, 90, 20);

                            //---- nickText2 ----
                            nickText2.setText("\u5f53\u524d\u9ab0\u5a18\u6635\u79f0:");
                            infoPanel2.add(nickText2);
                            nickText2.setBounds(5, 40, 90, 20);

                            //---- qqValue2 ----
                            qqValue2.setText("text");
                            infoPanel2.add(qqValue2);
                            qqValue2.setBounds(20, 25, 50, qqValue2.getPreferredSize().height);

                            //---- nickValue2 ----
                            nickValue2.setText("text");
                            infoPanel2.add(nickValue2);
                            nickValue2.setBounds(15, 65, 40, 16);

                            {
                                // compute preferred size
                                Dimension preferredSize = new Dimension();
                                for(int i = 0; i < infoPanel2.getComponentCount(); i++) {
                                    Rectangle bounds = infoPanel2.getComponent(i).getBounds();
                                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                                }
                                Insets insets = infoPanel2.getInsets();
                                preferredSize.width += insets.right;
                                preferredSize.height += insets.bottom;
                                infoPanel2.setMinimumSize(preferredSize);
                                infoPanel2.setPreferredSize(preferredSize);
                            }
                        }
                        panel4.add(infoPanel2);
                        infoPanel2.setBounds(5, 5, 145, 105);

                        //---- save2 ----
                        save2.setText("\u4fdd\u5b58");
                        save2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                save(e);
                            }
                        });
                        panel4.add(save2);
                        save2.setBounds(new Rectangle(new Point(35, 435), save2.getPreferredSize()));

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
                            scrollPane2.setViewportView(textArea1);
                        }
                        panel15.add(scrollPane2);
                        scrollPane2.setBounds(10, 30, 440, 120);

                        //---- label2 ----
                        label2.setText("\u6781\u96be\u6210\u529f\u56de\u590d\u8bed");
                        panel15.add(label2);
                        label2.setBounds(new Rectangle(new Point(10, 165), label2.getPreferredSize()));

                        //======== scrollPane3 ========
                        {
                            scrollPane3.setViewportView(textArea2);
                        }
                        panel15.add(scrollPane3);
                        scrollPane3.setBounds(10, 185, 440, 120);

                        //---- label3 ----
                        label3.setText("\u56f0\u96be\u6210\u529f\u56de\u590d\u8bed");
                        panel15.add(label3);
                        label3.setBounds(new Rectangle(new Point(10, 320), label3.getPreferredSize()));

                        //======== scrollPane4 ========
                        {
                            scrollPane4.setViewportView(textArea3);
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
                            scrollPane5.setViewportView(textArea4);
                        }
                        panel15.add(scrollPane5);
                        scrollPane5.setBounds(490, 30, 440, 120);

                        //======== scrollPane6 ========
                        {
                            scrollPane6.setViewportView(textArea5);
                        }
                        panel15.add(scrollPane6);
                        scrollPane6.setBounds(490, 185, 440, 120);

                        //======== scrollPane7 ========
                        {
                            scrollPane7.setViewportView(textArea6);
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
                        monitorPanel2.add(promethusPortValue2);
                        promethusPortValue2.setBounds(140, 40, 40, 25);

                        //---- checkBox1 ----
                        checkBox1.setText("\u542f\u7528\u666e\u7f57\u7c73\u4fee\u65af\u76d1\u63a7\u4e0a\u62a5");
                        monitorPanel2.add(checkBox1);
                        checkBox1.setBounds(new Rectangle(new Point(20, 10), checkBox1.getPreferredSize()));

                        //---- checkBox2 ----
                        checkBox2.setText("\u542f\u7528\u5fc3\u8df3\u62a5\u544a");
                        monitorPanel2.add(checkBox2);
                        checkBox2.setBounds(new Rectangle(new Point(40, 65), checkBox2.getPreferredSize()));

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
                        setMaster.add(masterValue);
                        masterValue.setBounds(80, 10, 144, masterValue.getPreferredSize().height);

                        //======== scrollPane8 ========
                        {
                            scrollPane8.setViewportView(textArea7);
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
                            qqText3.setBounds(5, 5, 90, 20);

                            //---- nickText3 ----
                            nickText3.setText("\u5f53\u524d\u9ab0\u5a18\u6635\u79f0:");
                            infoPanel3.add(nickText3);
                            nickText3.setBounds(5, 40, 90, 20);

                            //---- qqValue3 ----
                            qqValue3.setText("text");
                            infoPanel3.add(qqValue3);
                            qqValue3.setBounds(20, 25, 50, qqValue3.getPreferredSize().height);

                            //---- nickValue3 ----
                            nickValue3.setText("text");
                            infoPanel3.add(nickValue3);
                            nickValue3.setBounds(15, 65, 40, 16);

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
                        infoPanel3.setBounds(5, 5, 145, 105);

                        //---- save3 ----
                        save3.setText("\u4fdd\u5b58");
                        save3.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                save(e);
                            }
                        });
                        panel16.add(save3);
                        save3.setBounds(new Rectangle(new Point(35, 435), save3.getPreferredSize()));

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
                        panel17.add(textField46);
                        textField46.setBounds(205, 10, 320, 25);
                        panel17.add(textField47);
                        textField47.setBounds(205, 40, 320, 25);
                        panel17.add(textField48);
                        textField48.setBounds(205, 70, 320, 25);
                        panel17.add(textField49);
                        textField49.setBounds(205, 100, 320, 25);

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
                    panel17.setBounds(425, 155, 545, 130);

                    //======== panel18 ========
                    {
                        panel18.setBorder(new EtchedBorder());
                        panel18.setToolTipText("\u673a\u5668\u4eba\u5e38\u7528\u4fe1\u606f");
                        panel18.setLayout(null);

                        //---- label56 ----
                        label56.setText("\u8b66\u544a\u5237\u5c4f\u9891\u5ea6(N\u6761/\u6bcf\u5206\u949f)");
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
                        panel18.add(textField50);
                        textField50.setBounds(205, 10, 320, 25);
                        panel18.add(textField51);
                        textField51.setBounds(205, 40, 320, 25);
                        panel18.add(textField52);
                        textField52.setBounds(205, 70, 320, 25);
                        panel18.add(textField53);
                        textField53.setBounds(205, 100, 320, 25);

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
                    panel18.setBounds(425, 15, 545, 130);

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

                        //======== infoPanel4 ========
                        {
                            infoPanel4.setBorder(new BevelBorder(BevelBorder.LOWERED));
                            infoPanel4.setLayout(null);

                            //---- qqText4 ----
                            qqText4.setText("\u5f53\u524d\u9ab0\u5a18QQ\u53f7:");
                            infoPanel4.add(qqText4);
                            qqText4.setBounds(5, 5, 90, 20);

                            //---- nickText4 ----
                            nickText4.setText("\u5f53\u524d\u9ab0\u5a18\u6635\u79f0:");
                            infoPanel4.add(nickText4);
                            nickText4.setBounds(5, 40, 90, 20);

                            //---- qqValue4 ----
                            qqValue4.setText("text");
                            infoPanel4.add(qqValue4);
                            qqValue4.setBounds(20, 25, 50, qqValue4.getPreferredSize().height);

                            //---- nickValue4 ----
                            nickValue4.setText("text");
                            infoPanel4.add(nickValue4);
                            nickValue4.setBounds(15, 65, 40, 16);

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
                        infoPanel4.setBounds(5, 5, 145, 105);

                        //---- save4 ----
                        save4.setText("\u4fdd\u5b58");
                        save4.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                save(e);
                            }
                        });
                        panel20.add(save4);
                        save4.setBounds(new Rectangle(new Point(35, 435), save4.getPreferredSize()));

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
            }
            windows.add(tabContain);
            tabContain.setBounds(25, 30, 1155, 530);

            //======== panel21 ========
            {
                panel21.setBorder(new EtchedBorder());
                panel21.setLayout(null);

                //---- checkBox3 ----
                checkBox3.setText("\u5f00\u542f\u4e91\u9ed1\u540d\u5355");
                panel21.add(checkBox3);
                checkBox3.setBounds(new Rectangle(new Point(20, 20), checkBox3.getPreferredSize()));

                //---- checkBox4 ----
                checkBox4.setText("\u5f53\u9ed1\u540d\u5355\u7528\u6237\u5904\u4e8e\u767d\u540d\u5355\u7fa4\u4e2d\u65f6\uff0c\u5ffd\u7565\u6b64\u7528\u6237");
                panel21.add(checkBox4);
                checkBox4.setBounds(new Rectangle(new Point(40, 50), checkBox4.getPreferredSize()));

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
            windows.add(panel21);
            panel21.setBounds(130, 600, 405, 325);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < windows.getComponentCount(); i++) {
                    Rectangle bounds = windows.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = windows.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                windows.setMinimumSize(preferredSize);
                windows.setPreferredSize(preferredSize);
            }
            windows.pack();
            windows.setLocationRelativeTo(windows.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private Frame windows;
    private JTabbedPane tabContain;
    private JPanel setting;
    private JPanel panel3;
    private JPanel infoPanel;
    private JLabel qqText;
    private JLabel nickText;
    private JLabel qqValue;
    private JLabel nickValue;
    private JButton save;
    private JScrollPane scrollPane1;
    private JPanel panel2;
    private JPanel panel6;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JPanel panel13;
    private JLabel label45;
    private JLabel label46;
    private JLabel label47;
    private JTextField textField41;
    private JTextField textField42;
    private JTextField textField43;
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
    private JPanel panel12;
    private JLabel label39;
    private JLabel label40;
    private JLabel label41;
    private JLabel label42;
    private JLabel label43;
    private JLabel label44;
    private JTextField textField35;
    private JTextField textField36;
    private JTextField textField37;
    private JTextField textField38;
    private JTextField textField39;
    private JTextField textField40;
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
    private JPanel panel10;
    private JLabel label32;
    private JLabel label33;
    private JTextField textField28;
    private JTextField textField29;
    private JPanel panel14;
    private JLabel label48;
    private JLabel label49;
    private JTextField textField44;
    private JTextField textField45;
    private JPanel panel7;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JPanel panel11;
    private JLabel label34;
    private JLabel label35;
    private JLabel label36;
    private JLabel label37;
    private JTextField textField30;
    private JTextField textField31;
    private JTextField textField32;
    private JTextField textField33;
    private JLabel label38;
    private JTextField textField34;
    private JPanel panel1;
    private JPanel panel4;
    private JPanel infoPanel2;
    private JLabel qqText2;
    private JLabel nickText2;
    private JLabel qqValue2;
    private JLabel nickValue2;
    private JButton save2;
    private JPanel panel15;
    private JLabel label1;
    private JScrollPane scrollPane2;
    private JTextArea textArea1;
    private JLabel label2;
    private JScrollPane scrollPane3;
    private JTextArea textArea2;
    private JLabel label3;
    private JScrollPane scrollPane4;
    private JTextArea textArea3;
    private JLabel label4;
    private JLabel label50;
    private JLabel label51;
    private JScrollPane scrollPane5;
    private JTextArea textArea4;
    private JScrollPane scrollPane6;
    private JTextArea textArea5;
    private JScrollPane scrollPane7;
    private JTextArea textArea6;
    private JPanel clean;
    private JPanel monitorPanel2;
    private JLabel promethusPortText2;
    private JTextField promethusPortValue2;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JPanel setMaster;
    private JLabel masterText;
    private JLabel notMasterInfoText;
    private JTextField masterValue;
    private JScrollPane scrollPane8;
    private JTextArea textArea7;
    private JPanel panel16;
    private JPanel infoPanel3;
    private JLabel qqText3;
    private JLabel nickText3;
    private JLabel qqValue3;
    private JLabel nickValue3;
    private JButton save3;
    private JPanel panel17;
    private JLabel label52;
    private JLabel label53;
    private JLabel label54;
    private JLabel label55;
    private JTextField textField46;
    private JTextField textField47;
    private JTextField textField48;
    private JTextField textField49;
    private JPanel panel18;
    private JLabel label56;
    private JLabel label57;
    private JLabel label58;
    private JLabel label59;
    private JTextField textField50;
    private JTextField textField51;
    private JTextField textField52;
    private JTextField textField53;
    private JPanel panel19;
    private JPanel panel20;
    private JPanel infoPanel4;
    private JLabel qqText4;
    private JLabel nickText4;
    private JLabel qqValue4;
    private JLabel nickValue4;
    private JButton save4;
    private JPanel panel21;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
