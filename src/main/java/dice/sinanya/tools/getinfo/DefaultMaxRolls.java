package dice.sinanya.tools.getinfo;

import dice.sinanya.db.setdefaultrolls.InsertDefaultMaxRolls;
import dice.sinanya.db.setdefaultrolls.SelectDefaultMaxRolls;

/**
 * @author SitaNya
 * 日期: 2019-06-17
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 默认骰数据库交互类
 * <p>
 * 包装了数据到数据库的过程
 */
public class DefaultMaxRolls {

    private DefaultMaxRolls() {
        throw new IllegalStateException("Utility class");
    }

    private static InsertDefaultMaxRolls insertDefaultMaxRolls = new InsertDefaultMaxRolls();
    private static SelectDefaultMaxRolls selectDefaultMaxRolls = new SelectDefaultMaxRolls();

    /**
     * 刷新默认骰，只在启动时这样做
     */
    public static void flushMaxRolls() {
        selectDefaultMaxRolls.flushMaxRollsFromDatabase();
    }

    /**
     * 更新库中某个群的默认骰最大值设定，若库中不存在则新增
     *
     * @param groupId  群号
     * @param maxRolls 新的默认骰最大值
     */
    public static void setMaxRolls(String groupId, int maxRolls) {
        insertDefaultMaxRolls.insert(groupId, maxRolls);
    }
}
