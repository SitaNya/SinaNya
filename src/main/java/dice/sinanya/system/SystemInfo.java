package dice.sinanya.system;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 系统环境变量，如机器人开关的静态对象等
 */
public class SystemInfo {

    public static final Map<Long, Boolean> SWITCH_BOT = new HashMap<>();

    private SystemInfo() {
        throw new IllegalStateException("Utility class");
    }
}
