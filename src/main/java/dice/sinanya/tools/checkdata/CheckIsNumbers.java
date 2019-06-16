package dice.sinanya.tools.checkdata;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 检查是否是数字
 */
public class CheckIsNumbers {

    /**
     * 是否为数字类型
     *
     * @param str 字符串
     * @return 布尔值
     */
    public static boolean isNumeric(String str) {
        String strInputNone = "";
        if (str != null && !str.equals(strInputNone)) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

}
