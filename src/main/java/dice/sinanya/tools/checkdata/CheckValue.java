package dice.sinanya.tools.checkdata;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 检查一个值是否为null
 * <p>
 * 如果是null的话置为字符串null，这样在插入数据库时会减少很多问题，也方便显示
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
