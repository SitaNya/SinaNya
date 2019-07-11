package dice.sinanya.system;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 颜色的静态信息
 * <p>
 * 这里我挑了一些比较方便看的颜色，括号内颜色是灰色，骰子颜色是橘红色，kp颜色是鲜红色
 */
public class MessagesRgb {

    public static final Map<Integer, String> RGB = new HashMap<Integer, String>() {{
        put(0, "FF7F24");
        put(1, "8B4513");
        put(2, "483D8B");
        put(3, "2F4F4F");
        put(4, "FF3E96");
        put(5, "1C1C1C");
        put(6, "68228B");
        put(7, "8B8B83");
        put(8, "FF7F00");
        put(9, "9ACD32");
        put(10, "DCDCDC");
//        括号内颜色
        put(11, "FFDAB9");
//        骰子颜色
        put(12, "FF0000");
//        kp颜色
    }};

    private MessagesRgb() {
        throw new IllegalStateException("Utility class");
    }
}
