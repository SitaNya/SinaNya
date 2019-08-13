package dice.sinanya.tools.windows;

import javax.swing.*;


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

    public JCheckBox createRadioButton(JPanel jPanel, String value) {
        JCheckBox jCheckBox = new JCheckBox(value);
//        jCheckBox.setBounds(x, y, getLength(value) * 3, 20);
        jPanel.add(jCheckBox);
        return jCheckBox;
    }
}
