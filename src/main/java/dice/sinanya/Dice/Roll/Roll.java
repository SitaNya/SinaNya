package dice.sinanya.Dice.Roll;

import dice.sinanya.entity.EntityTypeMessages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static dice.sinanya.system.MessagesError.MESSAGES_ERROR;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author zhangxiaozhou
 */
public class Roll {

    private static Logger log = LogManager.getLogger(Roll.class.getName());

    private EntityTypeMessages entityTypeMessages;

    public Roll(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void r() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".r");

        String tagTimes = "d";
        int times = 1;
        int rolls = 100;
        int intResult = 0;

        String strTimesAndRoles = msg.split(" +")[0];

        StringBuilder resultMessage = new StringBuilder(getNickName(entityTypeMessages) + "掷出了: " + strTimesAndRoles + "=");

        if (strTimesAndRoles.contains(tagTimes) &&
                strTimesAndRoles.split(tagTimes).length == 2 &&
                isNumeric(strTimesAndRoles.split(tagTimes)[0]) &&
                isNumeric(strTimesAndRoles.split(tagTimes)[1])) {
            times = Integer.parseInt(strTimesAndRoles.split(tagTimes)[0]);
            rolls = Integer.parseInt(strTimesAndRoles.split(tagTimes)[1]);
        } else {
            sender(entityTypeMessages, MESSAGES_ERROR.get("strValueErr"));
        }
        for (int i = 0; i < times; i++) {
            int tmpResult = random(1, rolls);

            if (times > 10 && i < (times - 1)) {
                intResult += tmpResult;
            } else if (i == times - 1 && i != 0) {
                resultMessage.append(intResult);
            } else if (times > 1 && times <= 10) {
                intResult += tmpResult;
                resultMessage.append(tmpResult);

                if (i < (times - 1)) {
                    resultMessage.append("+");
                } else if (i == times - 1) {

                }
            } else if (i == 0) {
                resultMessage.append(tmpResult);
            }else{
                resultMessage.append("=").append(intResult);
            }
        }
        sender(entityTypeMessages, resultMessage.toString());
    }
}
