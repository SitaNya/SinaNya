package dice.sinanya.tools.getinfo;

import dice.sinanya.db.history.InsertHistory;
import dice.sinanya.db.history.SelectHistory;
import dice.sinanya.entity.EntityHistory;

import java.util.Map;

import static dice.sinanya.system.MessagesHistory.historyList;


public class History {

    private static SelectHistory selectHistory = new SelectHistory();
    private static InsertHistory insertHistory = new InsertHistory();

    public static void setHistory() {
        for (Map.Entry<String, EntityHistory> mapEntry : historyList.entrySet()) {
            insertHistory.insertHistory(mapEntry.getValue());
        }
    }

    public static EntityHistory changeHistory(String qqId) {
        if (historyList.containsKey(qqId)) {
            return historyList.get(qqId);
        } else {
            selectHistory.flushHistory(qqId);
            if (historyList.containsKey(qqId)) {
                return historyList.get(qqId);
            } else {
                historyList.put(qqId, new EntityHistory(qqId));
                return historyList.get(qqId);
            }
        }
    }
}
