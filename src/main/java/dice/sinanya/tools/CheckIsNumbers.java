package dice.sinanya.tools;

/**
 * @author zhangxiaozhou
 */
public class CheckIsNumbers {

    /**
     * 是否为数字类型
     *
     * @param str 字符串
     * @return 布尔值
     */
    public static boolean isNumeric(String str) {
        if (str != null && !str.equals("")) {
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
