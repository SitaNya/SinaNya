package dice.sinanya.tools.makedata;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 消息格式化类
 */
public class MakeMessages {
    /**
     * 删除消息标签并删除两端空格
     *
     * @param message 传入消息，如.ra 50
     * @param tag     消息标签，如.ra
     * @return 删除标签并删除两端空格的结果，如50
     */
    public static String deleteTag(String message, String tag) {
        return message.toLowerCase().trim().replaceFirst(tag, "").trim();
    }
}
