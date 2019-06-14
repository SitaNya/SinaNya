package dice.sinanya.tools.getinfo;

import dice.sinanya.db.kp.InsertKp;
import dice.sinanya.db.kp.SelectKp;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.NotSetKpGroupException;

import static dice.sinanya.system.MessagesKP.KpGroup;

public class Kp {
    private static SelectKp selectKp = new SelectKp();
    private static InsertKp insertKp = new InsertKp();

    public static void setKpGroup(EntityTypeMessages entityTypeMessages, String group) {
        insertKp.insertKp(entityTypeMessages.getFromQq(), group);
    }

    public static String getKpGroup(EntityTypeMessages entityTypeMessages) throws NotSetKpGroupException {
        if (KpGroup.containsKey(entityTypeMessages.getFromQq())) {
            return KpGroup.get(entityTypeMessages.getFromQq());
        } else {
            selectKp.flushKp();
            if (KpGroup.containsKey(entityTypeMessages.getFromQq())) {
                return KpGroup.get(entityTypeMessages.getFromQq());
            } else {
                throw new NotSetKpGroupException(entityTypeMessages);
            }
        }
    }
}
