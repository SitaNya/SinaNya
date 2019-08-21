package dice.sinanya.tools.getinfo;

import dice.sinanya.entity.EntityBanProperties;
import dice.sinanya.entity.EntitySystemProperties;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 设定默认回复语，并读取配置文件中的回复语
 */
public class GetMessagesProperties {

    /**
     * 各种回复的默认值，保证配置文件里写错或者删掉了，也不会报错
     */
    public static EntitySystemProperties entitySystemProperties;
    public static EntityBanProperties entityBanProperties;

    private GetMessagesProperties() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 读取配置文件，默认从bin目录的上一层找conf目录，然后找sinanya.properties文件，也就是说如果启动时不在bin目录，可能会找不到文件
     */
    public static void initMessagesSystemProperties() {
        entitySystemProperties = new EntitySystemProperties();
    }

    /**
     * 读取配置文件，默认从bin目录的上一层找conf目录，然后找sinanya.properties文件，也就是说如果启动时不在bin目录，可能会找不到文件
     */
    public static void initMessagesBanProperties() {
        entityBanProperties = new EntityBanProperties();
    }
}
