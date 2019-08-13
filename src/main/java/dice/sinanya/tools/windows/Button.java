package dice.sinanya.tools.windows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class Button {
    public void createButton(JFrame jFrame, String text, int x, int y) {
        JButton jButton = new JButton(text);
        jButton.setBounds(x, y, getLength(text) * 4, 100);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "");
            }
        });
        jFrame.add(jButton);
    }
}
