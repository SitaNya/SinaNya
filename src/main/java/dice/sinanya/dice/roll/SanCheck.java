package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntitySanCheck;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.exceptions.SanCheckSetException;
import dice.sinanya.tools.CheckSanCheck;

import static dice.sinanya.system.MessagesTag.TAG_SC;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.Sender.sender;

public class SanCheck {

    private EntityTypeMessages entityTypeMessages;

    public SanCheck(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void sc() throws PlayerSetException, SanCheckSetException {
        String tag = TAG_SC;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String strCheckValue;
        String strSkill = "";
        if (msg.contains(" ")) {
            strCheckValue = msg.split(" ")[0].trim();

            strSkill = msg.split(" ")[1].trim();
        } else {
            strCheckValue = msg;
        }
        EntitySanCheck entitySanCheck = new CheckSanCheck(entityTypeMessages).checkSanCheck(strSkill, strCheckValue);
        sender(entityTypeMessages, entitySanCheck.getStrSanCheck());
    }

    public void sc(long qq, String inputMsg) throws PlayerSetException, SanCheckSetException {
        String tag = TAG_SC;
        String msg = deleteTag(inputMsg, tag.substring(0, tag.length() - 2));
        String strCheckValue;
        String strSkill = "";
        if (msg.contains(" ")) {
            strCheckValue = msg.split(" ")[0].trim();

            strSkill = msg.split(" ")[1].trim();
        } else {
            strCheckValue = msg;
        }
        EntitySanCheck entitySanCheck = new CheckSanCheck(entityTypeMessages, qq).checkSanCheck(strSkill, strCheckValue);
        sender(entityTypeMessages, entitySanCheck.getStrSanCheck());
    }
}
