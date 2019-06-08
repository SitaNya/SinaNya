package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.HashMap;

import static dice.sinanya.system.MessagesInit.initList;
import static dice.sinanya.system.MessagesTag.tagRE;
import static dice.sinanya.system.MessagesTag.tagRi;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.RoleChoose.checkRoleChooseExistByFromQQ;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.Sender.sender;

public class RollForDnd {

    private EntityTypeMessages entityTypeMessages;

    public RollForDnd(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void re(){
        String tag = tagRE;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String msgBefore = msg;
        int result;
        boolean add = false;
        int random = random(1, 20);
        if (msg.contains("-")) {
            result = random - Integer.parseInt(msg.replace("-", "").trim());
            msg = msg.replace("-", "").trim();
        } else if (msg.contains("+") || isNumeric(msg)) {
            result = random + Integer.parseInt(msg.replace("+", "").trim());
            msg = msg.replace("+", "").trim();
            add = true;
        } else {
            result = random;
        }

        String nick;

        if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
            nick = getRoleChooseByFromQQ(entityTypeMessages);
        } else {
            nick = getNickName(entityTypeMessages);
        }

        if (msg.equals("")) {
            sender(entityTypeMessages, nick + "掷出了: D20=" + result);
        } else {
            if (add) {
                sender(entityTypeMessages, nick + "掷出了: D20=" + random + "+" + msg + "=" + result);
            } else {
                sender(entityTypeMessages, nick + "掷出了: D20=" + random + "-" + msg + "=" + result);
            }
        }
    }
}
