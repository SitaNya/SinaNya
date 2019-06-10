package dice.sinanya.system;

import java.util.HashMap;

public interface MessagesRGB {
    HashMap<Integer, String> RBG = new HashMap<Integer, String>() {{
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
}
