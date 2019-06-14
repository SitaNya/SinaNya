package dice.sinanya.tools;

import dice.sinanya.db.clue.InsertClue;
import dice.sinanya.db.clue.SelectClue;
import dice.sinanya.entity.EntityClue;

import static dice.sinanya.system.MessagesSystem.NONE;

/**
 * 获取线索方法，这个方法更深一层就是数据库交互了
 *
 * @author SitaNya
 */
public class Clue {

    private static SelectClue selectClue = new SelectClue();
    private static InsertClue insertClue = new InsertClue();

    public static void setClue(EntityClue entityClue, String info) {
        insertClue.insertClue(entityClue, info);
    }

    public static String getClue(EntityClue entityClue) {
        String result = selectClue.selectClue(entityClue);
        if (result.trim().equals(NONE)) {
            return "此群当前没有记录线索";
        } else {
            return "您群内当前记录的线索集包含以下内容:\n" +
                    result;
        }

    }

    public static void delClue(EntityClue entityClue) {
        insertClue.deleteClue(entityClue);
    }

    public static void delClue(String groupId) {
        insertClue.clrClue(groupId);
    }
}
