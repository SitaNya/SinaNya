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
public class Text {
    public JTextArea createTextArea(JPanel jPanel){
        JTextArea jTextArea=new JTextArea();
        jPanel.add(jTextArea);
        return jTextArea;
    }

    public JTextField createText(JPanel jPanel){
        JTextField jTextField=new JTextField();
        jPanel.add(jTextField);
        return jTextField;
    }
}
