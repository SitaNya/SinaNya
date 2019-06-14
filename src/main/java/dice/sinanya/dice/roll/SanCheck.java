package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.exceptions.SanCheckSetException;
import dice.sinanya.tools.CheckSanCheck;

import static dice.sinanya.system.MessagesSystem.SPACE;
import static dice.sinanya.system.MessagesTag.TAG_SC;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.Sender.sender;

/**
 * .sc逻辑类
 *
 * @author SitaNya
 */
public class SanCheck {

    private EntityTypeMessages entityTypeMessages;

    public SanCheck(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void sc() throws PlayerSetException, SanCheckSetException {
        String tag = TAG_SC;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String result = new CheckSanCheck(entityTypeMessages).checkSanCheck(msg);
        sender(entityTypeMessages, result);
    }
}
