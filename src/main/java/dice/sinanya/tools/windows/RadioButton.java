package dice.sinanya.tools.windows;

import javax.swing.*;

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
public class RadioButton {

    public RadioButton() {
    }

    public JCheckBox createRadioButton(JPanel jPanel, String value, int x, int y) {
        JCheckBox jCheckBox = new JCheckBox(value);
        jCheckBox.setBounds(x, y, getLength(value) * 3, 20);
        jPanel.add(jCheckBox);
        return jCheckBox;
    }
}
