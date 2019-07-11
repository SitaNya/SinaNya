package dice.sinanya.tools.getinfo;

import dice.sinanya.db.clue.InsertClue;
import dice.sinanya.db.clue.SelectClue;
import dice.sinanya.entity.EntityClue;

import static dice.sinanya.system.MessagesSystem.NONE;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取线索方法
 * <p>
 * 包装了和数据库交互的方法
 */
public class Clue {

    private static SelectClue selectClue = new SelectClue();
    private static InsertClue insertClue = new InsertClue();
    private Clue() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 插入一条新线索
     *
     * @param entityClue 线索对象，包含qqId、时间戳、群号
     * @param info       线索内容
     */
    public static void setClue(EntityClue entityClue, String info) {
        insertClue.insertClue(entityClue, info);
    }

    /**
     * 得到本群内所有线索集
     *
     * @param entityClue 线索对象，包含qqId、时间戳、群号
     * @return 所有线索格式化好后返回
     */
    public static String getClue(EntityClue entityClue) {
        String result = selectClue.selectClue(entityClue);
        if (result.trim().equals(NONE)) {
            return "此群当前没有记录线索";
        } else {
            return "您群内当前记录的线索集包含以下内容:\n" +
                    result;
        }

    }

    /**
     * 删除一条线索
     *
     * @param entityClue 线索对象，包含qqId、时间戳、群号
     */
    public static void delClue(EntityClue entityClue) {
        insertClue.deleteClue(entityClue);
    }

    /**
     * 清空一个群内的所有线索
     *
     * @param groupId 群号
     */
    public static void clrClue(String groupId) {
        insertClue.clrClue(groupId);
    }
}
