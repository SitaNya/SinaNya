package dice.sinanya.entity;

import dice.sinanya.exceptions.PlayerSetException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesSystem.ROLL_MAX_VALUE;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;

/**
 * @author zhangxiaozhou
 */
public class EntityManyRolls {
    private int times = 1;
    private int rolls = 100;
    private int Ktimes = 0;
    private String strTimesAndRoles;
    private String resultMessages;

    private static Pattern KAndNums = Pattern.compile("[kK]([\\d]+)");

    private boolean boolInputError = false;
    private boolean boolContainD = false;

    public EntityManyRolls(String strTimesAndRoles, EntityTypeMessages entityTypeMessages) {
        if (ROLL_MAX_VALUE.containsKey(entityTypeMessages.getFromGroup())) {
            rolls = ROLL_MAX_VALUE.get(entityTypeMessages.getFromGroup());
        }
        String strInputValueNone = "";
        String tagTimes = "d";
        String strTimes;
        this.resultMessages = "";
        this.strTimesAndRoles = strTimesAndRoles;
        if (strTimesAndRoles.contains(tagTimes)) {
            Matcher mKTimes = KAndNums.matcher(strTimesAndRoles);
            while (mKTimes.find()) {
                Ktimes = Integer.parseInt(mKTimes.group(1));
                strTimesAndRoles = strTimesAndRoles.replaceFirst(KAndNums.toString(), "");
            }
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
                    if (isNumeric(strRolls) && !strRolls.equals(strInputValueNone) && !strRolls.equals("100")) {
                        rolls = Integer.parseInt(strRolls);
                    } else if (!strRolls.equals(strInputValueNone) && !strRolls.equals("100")) {
                        boolInputError = true;
                    }
                    break;
                default:
                    boolContainD = true;
                    break;
            }

            if (Ktimes > times) {
                boolInputError = true;
            }
        } else {
            boolContainD = true;
        }
    }

    public EntityManyRolls check(String resultMessages, EntityTypeMessages entityTypeMessages) throws PlayerSetException {
        EntityManyRolls entityManyRolls = new EntityManyRolls(strTimesAndRoles, entityTypeMessages);
        if (entityManyRolls.checkContainD()) {
            if (entityManyRolls.checkInputError()) {
                resultMessages += strTimesAndRoles + "=";
            } else {
                throw new PlayerSetException(entityTypeMessages);
            }
        } else {
            resultMessages += "D100=";
        }
        entityManyRolls.setResultMessages(resultMessages);
        return entityManyRolls;
    }

    public EntityManyRolls check(EntityTypeMessages entityTypeMessages) throws PlayerSetException {
        EntityManyRolls entityManyRolls = new EntityManyRolls(strTimesAndRoles, entityTypeMessages);
        if (entityManyRolls.checkInputError()) {
            return entityManyRolls;
        } else {
            throw new PlayerSetException(entityTypeMessages);
        }
    }

    public String getResultMessages() {
        return this.resultMessages;
    }

    private void setResultMessages(String resultMessages) {
        this.resultMessages = resultMessages;
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

    public int getKtimes() {
        return Ktimes;
    }
}
