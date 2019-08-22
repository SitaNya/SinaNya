/*
 * Created by JFormDesigner on Wed Aug 21 10:22:21 CST 2019
 */

package dice.sinanya.windows;

import dice.sinanya.system.MessagesSystem;
import dice.sinanya.update.UpdateForDice;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author xiaozhou zhang
 */
public class UpdateFrame extends JFrame {
    UpdateForDice updateForDice;

    public UpdateFrame() {
        initComponents();
        this.updateForDice = new UpdateForDice(downJarProgress,downJsonProgress);
        updateButton.setEnabled(false);
        updateVersion.setText(MessagesSystem.VERSIONS);
        if (updateForDice.checkNeedUpdate()) {
            needUpdate.setText("需要");
            needUpdate.setForeground(Color.RED);
            updateButton.setEnabled(true);
        }
        if (!updateForDice.isJarExists()) {
            jarExists.setText("不存在");
            jarExists.setForeground(Color.RED);
            updateButton.setEnabled(false);
        }

        if (!updateForDice.isJsonExists()) {
            jsonExists.setText("不存在");
            jsonExists.setForeground(Color.RED);
            updateButton.setEnabled(false);
        }

        if (!updateForDice.isJarDirExists()) {
            jarDirExists.setText("不存在");
            jarDirExists.setForeground(Color.RED);
            updateButton.setEnabled(false);
        }

        if (!updateForDice.serverFileExist()) {
            serviceFileExists.setText("不存在");
            serviceFileExists.setForeground(Color.RED);
            updateButton.setEnabled(false);
        }
        this.setVisible(true);
    }

    private void thisWindowClosing(WindowEvent e) {
        this.setVisible(false);
    }

    private void button1MouseClicked(MouseEvent e) {
        if (updateButton.isEnabled()) {
            try {
                updateForDice.update();
            } catch (IOException ex) {
                CQ.logError("更新", "失败: " + ex.getMessage() + "\n" + StringUtils.join(ex.getStackTrace(), "\n"));
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel4 = new JPanel();
        panel2 = new JPanel();
        label7 = new JLabel();
        updateVersion = new JLabel();
        label9 = new JLabel();
        needUpdate = new JLabel();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        jarExists = new JLabel();
        jsonExists = new JLabel();
        jarDirExists = new JLabel();
        label13 = new JLabel();
        serviceFileExists = new JLabel();
        panel3 = new JPanel();
        downJarProgress = new JProgressBar();
        downJsonProgress = new JProgressBar();
        label11 = new JLabel();
        label12 = new JLabel();
        updateButton = new JButton();
        label15 = new JLabel();

        //======== this ========
        setTitle("\u5347\u7ea7\u7a97\u53e3");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel4 ========
        {
            panel4.setLayout(null);

            //======== panel2 ========
            {
                panel2.setBorder(new EtchedBorder());
                panel2.setLayout(null);

                //---- label7 ----
                label7.setText("\u5f53\u524d\u7248\u672c");
                panel2.add(label7);
                label7.setBounds(10, 10, label7.getPreferredSize().width, 25);

                //---- updateVersion ----
                updateVersion.setText("\u7248\u672c\u53f7");
                panel2.add(updateVersion);
                updateVersion.setBounds(95, 10, 95, 25);

                //---- label9 ----
                label9.setText("\u662f\u5426\u9700\u8981\u66f4\u65b0");
                panel2.add(label9);
                label9.setBounds(10, 40, label9.getPreferredSize().width, 25);

                //---- needUpdate ----
                needUpdate.setText("\u4e0d\u9700\u8981");
                needUpdate.setForeground(Color.green);
                panel2.add(needUpdate);
                needUpdate.setBounds(95, 40, 60, 25);

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
            panel4.add(panel2);
            panel2.setBounds(10, 30, 200, 75);

            //======== panel1 ========
            {
                panel1.setBorder(new EtchedBorder());
                panel1.setLayout(null);

                //---- label1 ----
                label1.setText("\u5f53\u524dJar\u5305");
                panel1.add(label1);
                label1.setBounds(10, 10, label1.getPreferredSize().width, 25);

                //---- label2 ----
                label2.setText("\u5f53\u524dJSON\u6587\u4ef6");
                panel1.add(label2);
                label2.setBounds(10, 40, label2.getPreferredSize().width, 25);

                //---- label3 ----
                label3.setText("\u5f53\u524dJar\u5305\u76ee\u5f55");
                panel1.add(label3);
                label3.setBounds(10, 70, label3.getPreferredSize().width, 25);

                //---- jarExists ----
                jarExists.setText("\u6b63\u5e38");
                jarExists.setForeground(Color.green);
                panel1.add(jarExists);
                jarExists.setBounds(130, 10, 60, 25);

                //---- jsonExists ----
                jsonExists.setText("\u6b63\u5e38");
                jsonExists.setForeground(Color.green);
                panel1.add(jsonExists);
                jsonExists.setBounds(130, 40, 60, 25);

                //---- jarDirExists ----
                jarDirExists.setText("\u6b63\u5e38");
                jarDirExists.setForeground(Color.green);
                panel1.add(jarDirExists);
                jarDirExists.setBounds(130, 70, 60, 25);

                //---- label13 ----
                label13.setText("\u670d\u52a1\u5668\u7aef\u6587\u4ef6");
                panel1.add(label13);
                label13.setBounds(10, 100, 84, 25);

                //---- serviceFileExists ----
                serviceFileExists.setText("\u6b63\u5e38");
                serviceFileExists.setForeground(Color.green);
                panel1.add(serviceFileExists);
                serviceFileExists.setBounds(130, 100, 60, 25);

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
            panel4.add(panel1);
            panel1.setBounds(215, 10, 200, 135);

            //======== panel3 ========
            {
                panel3.setBorder(new EtchedBorder());
                panel3.setForeground(new Color(51, 255, 51));
                panel3.setLayout(null);

                //---- downJarProgress ----
                downJarProgress.setBackground(new Color(204, 204, 204));
                downJarProgress.setStringPainted(true);
                panel3.add(downJarProgress);
                downJarProgress.setBounds(10, 40, 515, 20);

                //---- downJsonProgress ----
                downJsonProgress.setBackground(new Color(204, 204, 204));
                downJsonProgress.setStringPainted(true);
                panel3.add(downJsonProgress);
                downJsonProgress.setBounds(10, 105, 515, 19);

                //---- label11 ----
                label11.setText("\u4e0b\u8f7dJar\u5305");
                panel3.add(label11);
                label11.setBounds(new Rectangle(new Point(10, 10), label11.getPreferredSize()));

                //---- label12 ----
                label12.setText("\u4e0b\u8f7dJSON\u6587\u4ef6");
                panel3.add(label12);
                label12.setBounds(new Rectangle(new Point(10, 75), label12.getPreferredSize()));

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
            panel4.add(panel3);
            panel3.setBounds(420, 10, 535, 135);

            //---- updateButton ----
            updateButton.setText("\u5347\u7ea7");
            updateButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            panel4.add(updateButton);
            updateButton.setBounds(new Rectangle(new Point(70, 115), updateButton.getPreferredSize()));

            //---- label15 ----
            label15.setText("\u5347\u7ea7\u8fc7\u7a0b\u4e2d\u670d\u52a1\u4e0d\u4f1a\u505c\u6b62");
            panel4.add(label15);
            label15.setBounds(new Rectangle(new Point(35, 5), label15.getPreferredSize()));

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
        contentPane.add(panel4, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel4;
    private JPanel panel2;
    private JLabel label7;
    private JLabel updateVersion;
    private JLabel label9;
    private JLabel needUpdate;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel jarExists;
    private JLabel jsonExists;
    private JLabel jarDirExists;
    private JLabel label13;
    private JLabel serviceFileExists;
    private JPanel panel3;
    private JProgressBar downJarProgress;
    private JProgressBar downJsonProgress;
    private JLabel label11;
    private JLabel label12;
    private JButton updateButton;
    private JLabel label15;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
