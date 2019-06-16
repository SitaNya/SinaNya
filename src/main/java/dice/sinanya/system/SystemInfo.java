package dice.sinanya.system;

import java.util.HashMap;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 系统环境变量，如机器人开关的静态对象等
 */
public interface SystemInfo {

    /**
     * 编译时的target环境路径
     */
    String BUILD_CLASS_TARGET_DIR = "/target/classes";
    /**
     * 编译时的BOOT-INF环境路径
     */
    String BUILD_CLASS_BOOT_INF_DIR = "!/BOOT-INF/classes!";

    HashMap<Long, Boolean> SWITCH_BOT = new HashMap<>();
}
