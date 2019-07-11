package dice.sinanya.system;

import dice.sinanya.entity.EntityHistory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 骰点历史的静态对象
 * 所有数据优先从静态对象取，取不到再去找数据库，这样可以提高效率
 */
public class MessagesHistory {
    public static final Map<String, EntityHistory> HISTORY_LIST = new HashMap<>();

    private MessagesHistory() {
        throw new IllegalStateException("Utility class");
    }
}
