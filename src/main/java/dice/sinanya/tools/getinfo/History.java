package dice.sinanya.tools.getinfo;

import dice.sinanya.db.history.InsertHistory;
import dice.sinanya.db.history.SelectHistory;
import dice.sinanya.entity.EntityHistory;

import java.util.Map;

import static dice.sinanya.system.MessagesHistory.HISTORY_LIST;


public class History {

    private static SelectHistory selectHistory = new SelectHistory();
    private static InsertHistory insertHistory = new InsertHistory();

    public static void flushHistory() {
        selectHistory.flushHistoryFromDatabase();
    }

    public static void setHistory() {
        for (Map.Entry<String, EntityHistory> mapEntry : HISTORY_LIST.entrySet()) {
            insertHistory.insertHistory(mapEntry.getValue());
        }
    }

    public static EntityHistory changeHistory(String qqId) {
        if (HISTORY_LIST.containsKey(qqId)) {
            return HISTORY_LIST.get(qqId);
        } else {
            HISTORY_LIST.put(qqId, new EntityHistory(qqId));
            return HISTORY_LIST.get(qqId);
        }
    }
}
