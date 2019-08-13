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
public class Frame {
    private String title;
    private int width;
    private int height;

    public Frame(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public JFrame init() {
        JFrame f = new JFrame(title);
        f.setSize(width, height);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
//        f.setLayout(new GridLayout(0,4));
        try {
            UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
}
