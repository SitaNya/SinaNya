package dice.sinanya.tools.getinfo;

import dice.sinanya.db.kp.InsertKp;
import dice.sinanya.db.kp.SelectKp;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotSetKpGroupException;

import static dice.sinanya.system.MessagesKP.KP_GROUP;

public class Kp {
    private static SelectKp selectKp = new SelectKp();
    private static InsertKp insertKp = new InsertKp();

    public static void flushKp() {
        selectKp.flushKpFromDatabase();
    }

    public static void setKpGroup(EntityTypeMessages entityTypeMessages, String group) {
        insertKp.insertKp(entityTypeMessages.getFromQq(), group);
    }

    public static String getKpGroup(EntityTypeMessages entityTypeMessages) throws NotSetKpGroupException {
        if (KP_GROUP.containsKey(entityTypeMessages.getFromQq())) {
            return KP_GROUP.get(entityTypeMessages.getFromQq());
        } else {
            throw new NotSetKpGroupException(entityTypeMessages);
        }
    }
}
