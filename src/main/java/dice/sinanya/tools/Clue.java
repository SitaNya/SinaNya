package dice.sinanya.tools;

import dice.sinanya.db.clue.InsertClue;
import dice.sinanya.db.clue.SelectClue;
import dice.sinanya.entity.EntityClue;

public class Clue {

    static SelectClue selectClue = new SelectClue();
    static InsertClue insertClue = new InsertClue();

    public static void setClue(EntityClue entityClue, String info) {
        insertClue.insertClue(entityClue, info);
    }

    public static String getClue(EntityClue entityClue) {
        String result = selectClue.selectClue(entityClue);
        if (result.trim().equals("")) {
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
