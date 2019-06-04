package dice.sinanya.tools;

/**
 * @author zhangxiaozhou
 */
public class MakeMessages {
    public static String deleteTag(String message, String tag) {
        return message.trim().replace(tag, "").trim();
    }
}
