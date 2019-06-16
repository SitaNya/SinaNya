package dice.sinanya.system;

import dice.sinanya.entity.EntityLogTag;

import java.util.HashMap;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 日志的静态对象
 * <p>
 * 所有数据优先从静态对象取，取不到再去找数据库，这样可以提高效率
 */
public interface MessagesLog {

    /**
     * @param logNameSwitch 记录某个群的某个日志是否打开
     * @param logSwitchForGroup 记录某个群是否有日志打开
     * @param logNameForGroup 记录某个群存在哪些日志
     */
    HashMap<EntityLogTag, Boolean> logNameSwitch = new HashMap<EntityLogTag, Boolean>();

    HashMap<String, Boolean> logSwitchForGroup = new HashMap<>();

    HashMap<String, String> logNameForGroup = new HashMap<>();
}
