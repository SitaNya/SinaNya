package dice.sinanya.system;

import java.util.HashMap;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 日志染色标志的静态对象
 * <p>
 * 将对方的昵称hash后存到这里，可以确保同一个人说话用同一种颜色
 */
public interface MessagesLogColorTag {
    HashMap<String, HashMap<String, Integer>> LOG_COLOR_TAG = new HashMap<>();
}
