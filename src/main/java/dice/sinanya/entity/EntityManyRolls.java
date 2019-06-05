package dice.sinanya.entity;

import dice.sinanya.exceptions.PlayerSetException;

import static dice.sinanya.system.MessagesError.strValueErr;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author zhangxiaozhou
 */
public class EntityManyRolls {
    private int times = 1;
    private int rolls = 100;
    private String strTimesAndRoles;
    private String resultMessages;

    private boolean boolInputError = false;
    private boolean boolContainD = false;

    public EntityManyRolls(String strTimesAndRoles) {
        String strInputValueNone = "";
        String tagTimes = "d";
        String strTimes;
        this.strTimesAndRoles = strTimesAndRoles;
        if (strTimesAndRoles.contains(tagTimes)) {
            switch (strTimesAndRoles.split(tagTimes).length) {
                case 1:
                    strTimes = strTimesAndRoles.split(tagTimes)[0];
                    if (isNumeric(strTimes) && !strTimes.equals(strInputValueNone)) {
                        times = Integer.parseInt(strTimes);
                    } else if (!strTimes.equals(strInputValueNone)) {
                        boolInputError = true;
                    }
                    break;
                case 2:
                    strTimes = strTimesAndRoles.split(tagTimes)[0];
                    String strRolls = strTimesAndRoles.split(tagTimes)[1];

                    if (isNumeric(strTimes) && !strTimes.equals(strInputValueNone)) {
                        times = Integer.parseInt(strTimes);
                    } else if (!strTimes.equals(strInputValueNone)) {
                        boolInputError = true;
                    }
                    if (isNumeric(strRolls) && !strRolls.equals(strInputValueNone)) {
                        rolls = Integer.parseInt(strRolls);
                    } else if (!strRolls.equals(strInputValueNone)) {
                        boolInputError = true;
                    }
                    break;
                default:
                    boolContainD = true;
                    break;
            }
        } else {
            boolContainD = true;
        }
    }

    public EntityManyRolls check(String resultMessage, EntityTypeMessages entityTypeMessages) throws PlayerSetException {
        EntityManyRolls entityManyRolls = new EntityManyRolls(strTimesAndRoles);
        if (entityManyRolls.checkContainD()) {
            if (entityManyRolls.checkInputError()) {
                resultMessage += strTimesAndRoles + "=";
            } else {
                sender(entityTypeMessages, strValueErr);
                throw new PlayerSetException();
            }
        } else {
            resultMessage += "D100=";
        }
        this.resultMessages = resultMessage;
        return entityManyRolls;
    }

    public String getResultMessages() {
        return resultMessages;
    }

    private boolean checkContainD() {
        return !boolContainD;
    }

    private boolean checkInputError() {
        return !boolInputError;
    }

    public int getTimes() {
        return times;
    }

    public int getRolls() {
        return rolls;
    }
}
