package dice.sinanya.tools.checkdata;

/**
 * 检查值，如果是null的话置为字符串null，这样在插入数据库时会减少很多问题，也方便显示
 *
 * @author SitaNya
 */
public class CheckValue {
    public static Object checkValue(Object object) {
        if (object == null) {
            return "null";
        } else {
            return object.toString();
        }
    }
}
