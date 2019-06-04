package dice.sinanya.Dice.Roll;

import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.MessagesError.*;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author zhangxiaozhou
 */
public class Roll {

    private EntityTypeMessages entityTypeMessages;

    public Roll(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void r() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".r");

        String tagTimes = "d";
        int times = 1;
        int rolls = 100;

        String strTimesAndRoles = msg.split(" +")[0];

        String resultMessage = getNickName(entityTypeMessages) + "掷出了: ";

        if (strTimesAndRoles.contains(tagTimes) && strTimesAndRoles.split(tagTimes).length == 2) {
            String strTimes = strTimesAndRoles.split(tagTimes)[0];
            String strRolls = strTimesAndRoles.split(tagTimes)[1];

            if (isNumeric(strTimes) && !strTimes.equals("")) {
                times = Integer.parseInt(strTimes);
            } else if (!strTimes.equals("")) {
                sender(entityTypeMessages, strValueErr);
                return;
            }
            if (isNumeric(strRolls) && !strRolls.equals("")) {
                rolls = Integer.parseInt(strRolls);
            } else if (!strRolls.equals("")) {
                sender(entityTypeMessages, strValueErr);
                return;
            }
            resultMessage += strTimesAndRoles + "=";
            sender(entityTypeMessages, resultMessage + makeRoll(times, rolls));
        } else {
            resultMessage += "D100=";
            sender(entityTypeMessages, resultMessage + makeRoll(1, 100));
        }
    }

    public void rh() {
        sender(entityTypeMessages, strHiddenDice);

        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".rh");
        String tagTimes = "d";
        int times = 1;
        int rolls = 100;

        String strTimesAndRoles = msg.split(" +")[0];

        String resultMessage = "您在群" + entityTypeMessages.getFromGroup() + "中掷出了: ";

        if (strTimesAndRoles.contains(tagTimes) && strTimesAndRoles.split(tagTimes).length == 2) {
            String strTimes = strTimesAndRoles.split(tagTimes)[0];
            String strRolls = strTimesAndRoles.split(tagTimes)[1];

            if (isNumeric(strTimes) && !strTimes.equals("")) {
                times = Integer.parseInt(strTimes);
            } else if (!strTimes.equals("")) {
                sender(entityTypeMessages, strValueErr);
                return;
            }
            if (isNumeric(strRolls) && !strRolls.equals("")) {
                rolls = Integer.parseInt(strRolls);
            } else if (!strRolls.equals("")) {
                sender(entityTypeMessages, strValueErr);
                return;
            }
            resultMessage += strTimesAndRoles + "=";

            entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), resultMessage + makeHiddenRoll(times, rolls));
        } else {
            resultMessage += "D100=";
            entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), resultMessage + makeHiddenRoll(1, 100));
        }
    }


    private String makeRoll(int times, int rolls) {
        StringBuilder stringBuilder = new StringBuilder(200);
        int intResult = 0;
        if (times == 1) {
            int tmpResult = random(1, rolls);
            return String.valueOf(tmpResult);
        } else if (times > 10) {
            for (int i = 0; i < times; i++) {
                intResult += random(1, rolls);
            }
            stringBuilder.append(intResult);
        } else if (times > 1) {
            for (int i = 0; i < times; i++) {
                int tmpRandom = random(1, rolls);
                stringBuilder.append(tmpRandom);
                if (i != (times - 1)) {
                    stringBuilder.append("+");
                }
                intResult += tmpRandom;
            }
            stringBuilder.append("=").append(intResult);
        }
        return stringBuilder.toString();
    }

    private String makeHiddenRoll(int times, int rolls) {
        StringBuilder stringBuilder = new StringBuilder(200);
        if (times == 1) {
            int tmpResult = random(1, rolls);
            return String.valueOf(tmpResult);
        } else if (times > 20) {
            return strDiceTimesTooBig;
        } else if (times > 1) {
            stringBuilder.append("[");
            for (int i = 0; i < times; i++) {
                int tmpRandom = random(1, rolls);
                stringBuilder.append(tmpRandom);
                if (i != (times - 1)) {
                    stringBuilder.append(",");
                }
            }
            stringBuilder.append("]");
        }
        return stringBuilder.toString();
    }
}
