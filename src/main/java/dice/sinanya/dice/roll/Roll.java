package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityManyRolls;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;

import static dice.sinanya.system.MessagesError.strDiceTimesTooBig;
import static dice.sinanya.system.MessagesError.strHiddenDice;
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

        String strTimesAndRoles = msg.split(" +")[0];

        String resultMessage = getNickName(entityTypeMessages) + "掷出了: ";

        EntityManyRolls entityManyRolls;
        try {
            entityManyRolls = new EntityManyRolls(strTimesAndRoles).check(resultMessage, entityTypeMessages);
        } catch (PlayerSetException e) {
            return;
        }
        sender(entityTypeMessages, entityManyRolls.getResultMessages() + makeRoll(entityManyRolls.getTimes(), entityManyRolls.getRolls()));
    }

    public void rh() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".rh");

        String strTimesAndRoles = msg.split(" +")[0];

        String resultMessage = "您在群" + entityTypeMessages.getFromGroup() + "中掷出了: ";

        EntityManyRolls entityManyRolls;
        try {
            entityManyRolls = new EntityManyRolls(strTimesAndRoles).check(resultMessage, entityTypeMessages);
        } catch (PlayerSetException e) {
            return;
        }
        sender(entityTypeMessages, strHiddenDice);
        entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), entityManyRolls.getResultMessages() + makeHiddenRoll(entityManyRolls.getTimes(), entityManyRolls.getRolls()));
    }


    private String makeRoll(int times, int rolls) {
        int maxTimesRolls = 10;
        StringBuilder stringBuilder = new StringBuilder(200);
        int intResult = 0;
        if (times == 1) {
            int tmpResult = random(1, rolls);
            return String.valueOf(tmpResult);
        } else if (times > maxTimesRolls) {
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
        int maxTimesHidden = 20;
        StringBuilder stringBuilder = new StringBuilder(200);
        if (times == 1) {
            int tmpResult = random(1, rolls);
            return String.valueOf(tmpResult);
        } else if (times > maxTimesHidden) {
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
