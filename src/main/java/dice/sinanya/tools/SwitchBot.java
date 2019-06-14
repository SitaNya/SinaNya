package dice.sinanya.tools;

import dice.sinanya.db.system.InsertBot;
import dice.sinanya.db.system.SelectBot;

import static dice.sinanya.system.SystemInfo.SWITCH_BOT;

public class SwitchBot {
    private static InsertBot insertBot = new InsertBot();
    private static SelectBot selectBot = new SelectBot();

    public static void botOn(Long groupId) {
        SWITCH_BOT.put(groupId, true);
        insertBot.insertBot(groupId, true);
    }

    public static void botOff(Long groupId) {
        SWITCH_BOT.put(groupId, false);
        insertBot.insertBot(groupId, false);
    }

    public static boolean getBot(Long groupId) {
        if (SWITCH_BOT.containsKey(groupId)) {
            return SWITCH_BOT.get(groupId);
        } else {
            selectBot.selectBot();
            if (SWITCH_BOT.containsKey(groupId)) {
                return SWITCH_BOT.get(groupId);
            } else {
                insertBot.insertBot(groupId, true);
                return true;
            }
        }
    }
}
