package dice.sinanya.system;

import java.util.HashMap;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: kp主群的静态对象
 * 所有数据优先从静态对象取，取不到再去找数据库，这样可以提高效率
 */
public interface MessagesKP {
    HashMap<String, String> KpGroup = new HashMap<>();
}
