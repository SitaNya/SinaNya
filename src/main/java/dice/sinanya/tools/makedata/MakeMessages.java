package dice.sinanya.tools.makedata;

/**
 * @author SitaNya
 */
public class MakeMessages {
    public static String deleteTag(String message, String tag) {
        return message.trim().replaceFirst(tag, "").trim();
    }
}