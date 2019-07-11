package dice.sinanya.system;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 先攻列表的静态信息，目前此值不入库
 */
public class MessagesInit {

    public static final Map<String, HashMap<String, String>> INIT_LIST = new HashMap<>();

    private MessagesInit() {
        throw new IllegalStateException("Utility class");
    }
}
