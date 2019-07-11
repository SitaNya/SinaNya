package dice.sinanya.tools.checkdata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 检查是否是数字
 */
public class CheckIsNumbers {

    private static Pattern pattern = Pattern.compile("[-]{0,1}[0-9]+");

    private CheckIsNumbers() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 是否为数字类型
     *
     * @param str 字符串
     * @return 布尔值
     */
    public static boolean isNumeric(String str){
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

}
