/*
 * Created by JFormDesigner on Wed Aug 21 10:22:21 CST 2019
 */

package dice.sinanya.windows;

import dice.sinanya.system.MessagesSystem;
import dice.sinanya.update.UpdateForDice;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author xiaozhou zhang
 */
public class UpdateFrame extends JFrame {
    UpdateForDice updateForDice;

    public UpdateFrame() {
        this.updateForDice = new UpdateForDice();
        initComponents();
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


    }

    private void thisWindowClosing(WindowEvent e) {
        this.setVisible(false);
    }

    private void button1MouseClicked(MouseEvent e) {
        updateForDice.update();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        jarExists = new JLabel();
        jsonExists = new JLabel();
        jarDirExists = new JLabel();
        panel2 = new JPanel();
        label7 = new JLabel();
        updateVersion = new JLabel();
        label9 = new JLabel();
        needUpdate = new JLabel();
        updateButton = new JButton();

        //======== this ========
        setTitle("\u5347\u7ea7\u7a97\u53e3");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

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
        contentPane.add(panel1);
        panel1.setBounds(20, 100, 200, 105);

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
        contentPane.add(panel2);
        panel2.setBounds(20, 20, 200, 75);

        //---- updateButton ----
        updateButton.setText("\u5347\u7ea7");
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(updateButton);
        updateButton.setBounds(new Rectangle(new Point(455, 345), updateButton.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel jarExists;
    private JLabel jsonExists;
    private JLabel jarDirExists;
    private JPanel panel2;
    private JLabel label7;
    private JLabel updateVersion;
    private JLabel label9;
    private JLabel needUpdate;
    private JButton updateButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}