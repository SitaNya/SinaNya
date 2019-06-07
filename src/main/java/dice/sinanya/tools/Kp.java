package dice.sinanya.tools;

import dice.sinanya.db.kp.InsertKp;
import dice.sinanya.db.kp.SelectKp;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotSetKpGroupException;

import static dice.sinanya.system.MessagesKP.KpGroup;

public class Kp {
    static SelectKp selectKp = new SelectKp();
    static InsertKp insertKp = new InsertKp();

    public static void setKpGroup(EntityTypeMessages entityTypeMessages, String group) {
        insertKp.insertKp(entityTypeMessages.getFromQQ(), group);
    }

    public static String getKpGroup(EntityTypeMessages entityTypeMessages) throws NotSetKpGroupException {
        if (KpGroup.containsKey(entityTypeMessages.getFromQQ())) {
            return KpGroup.get(entityTypeMessages.getFromQQ());
        } else {
            selectKp.flushKp();
            if (KpGroup.containsKey(entityTypeMessages.getFromQQ())) {
                return KpGroup.get(entityTypeMessages.getFromQQ());
            } else {
                throw new NotSetKpGroupException(entityTypeMessages);
            }
        }
    }
}
