package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static dice.sinanya.system.MessagesInit.initList;
import static dice.sinanya.system.MessagesTag.tagR;
import static dice.sinanya.system.MessagesTag.tagRi;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.ManyRolls.manyRollsProcess;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.RoleChoose.checkRoleChooseExistByFromQQ;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.Sender.sender;
import static java.lang.Math.ceil;

public class RiAndInit {

    private EntityTypeMessages entityTypeMessages;

    public RiAndInit(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void ri() {
        String tag = tagRi;
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
        if (initList.containsKey(entityTypeMessages.getFromGroup())) {
            HashMap<String, String> riList = initList.get(entityTypeMessages.getFromGroup());
            riList.put(nick, ": D20" + msgBefore + "=" + result);
            initList.put(entityTypeMessages.getFromGroup(), riList);
        } else {
            HashMap<String, String> riList = new HashMap<>();
            riList.put(nick, ": D20" + msgBefore + "=" + result);
            initList.put(entityTypeMessages.getFromGroup(), riList);
        }
    }

    public void init() {
        StringBuilder stringBuffer = new StringBuilder();
        if (!initList.containsKey(entityTypeMessages.getFromGroup())) {
            sender(entityTypeMessages, "先攻列表为空");
            return;
        }
        stringBuffer.append("先攻列表为:\n");
        int i = 1;
        for (Map.Entry<String, HashMap<String, String>> mapEntry : initList.entrySet()) {
            for (Map.Entry<String, String> mapEntry2 : mapEntry.getValue().entrySet()) {
                stringBuffer.append(i)
                        .append(".\t")
                        .append(mapEntry2.getKey())
                        .append(mapEntry2.getValue())
                        .append("\n");
                i++;
            }
        }
        sender(entityTypeMessages, stringBuffer.substring(0, stringBuffer.length() - 1));
    }

    public void clr() {
        initList.remove(entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, "已清空本群的先攻列表");
    }
}
