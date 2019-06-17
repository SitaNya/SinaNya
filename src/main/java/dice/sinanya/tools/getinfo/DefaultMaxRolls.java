package dice.sinanya.tools.getinfo;

import dice.sinanya.db.setdefaultrolls.InsertDefaultMaxRolls;
import dice.sinanya.db.setdefaultrolls.SelectDefaultMaxRolls;

/**
 * @author SitaNya
 * 日期: 2019-06-17
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public class DefaultMaxRolls {

    private static InsertDefaultMaxRolls insertDefaultMaxRolls = new InsertDefaultMaxRolls();
    private static SelectDefaultMaxRolls selectDefaultMaxRolls = new SelectDefaultMaxRolls();

    public static void flushMaxRolls() {
        selectDefaultMaxRolls.flushMaxRollsFromDatabase();
    }

    public static void setMaxRolls(String groupId, int maxRolls) {
        insertDefaultMaxRolls.insert(groupId, maxRolls);
    }
}
