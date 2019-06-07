package dice.sinanya.system;

import dice.sinanya.entity.EntityHistory;

import java.util.HashMap;

public interface MessagesHistory {
    HashMap<String, EntityHistory> historyList = new HashMap<>();
}
