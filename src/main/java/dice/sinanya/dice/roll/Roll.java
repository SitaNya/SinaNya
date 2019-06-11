package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityManyRolls;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.tools.Calculator;
import dice.sinanya.tools.CheckResultLevel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesError.strDiceTimesTooBig;
import static dice.sinanya.system.MessagesError.strHiddenDice;
import static dice.sinanya.system.MessagesSystem.ROLL_MAX_VALUE;
import static dice.sinanya.system.MessagesTag.tagR;
import static dice.sinanya.system.MessagesTag.tagRH;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.ManyRolls.manyRollsProcess;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.RoleChoose.checkRoleChooseExistByFromQQ;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.Sender.sender;
import static java.lang.Math.ceil;

/**
 * @author zhangxiaozhou
 */
public class Roll {

    private static Pattern plus = Pattern.compile("[+*/-]");
    private static Pattern times = Pattern.compile("(\\d+#)");
    private static Pattern p1 = Pattern.compile("(^[dD]\\d+$)");

    private static Pattern AgainTimes = Pattern.compile("(\\d+)#");

    private EntityTypeMessages entityTypeMessages;

    public Roll(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void r() {
        String tag = tagR;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        int intTimes = 1;

        String strMsg = msg;

        Matcher mAgainTimes = AgainTimes.matcher(msg);

        while (mAgainTimes.find()) {
            intTimes = Integer.parseInt(mAgainTimes.group(1));
            msg.replaceFirst(AgainTimes.toString(), "");
        }

        int rollMaxValue = 100;
        if (ROLL_MAX_VALUE.containsKey(entityTypeMessages.getFromGroup())) {
            rollMaxValue = ROLL_MAX_VALUE.get(entityTypeMessages.getFromGroup());
        }


        String strTimes = "";
        Matcher mTimes = times.matcher(msg);
        while (mTimes.find()) {
            strTimes = mTimes.group(1);
        }

        if (msg.equals("") || !msg.contains("d")) {
            msg = "1d" + rollMaxValue;
            strMsg = "1d100";
        }

        String[] everyFunction = msg.split(plus.toString());

        if (everyFunction.length > 1 && !strTimes.equals("")) {
            sender(entityTypeMessages, "表达式输入错误");
            return;
        }


        for (int i = 0; i < intTimes; i++) {
            String strResult = msg;
            for (String function : everyFunction) {
                String tmpFunction = function;
                if (tmpFunction.equals("d")) {
                    tmpFunction = "1d100";
                } else if (tmpFunction.split("[dD]").length < 2) {
                    tmpFunction = tmpFunction + rollMaxValue;
                } else if (tmpFunction.split("[dD]")[0].equals("")) {
                    tmpFunction = 1 + tmpFunction;
                }
                EntityManyRolls entityManyRolls;

                if (!isNumeric(tmpFunction)) {
                    try {
                        entityManyRolls = new EntityManyRolls(tmpFunction.replaceFirst(strTimes, ""), entityTypeMessages).check(entityTypeMessages);
                    } catch (PlayerSetException e) {
                        return;
                    }

                    String strManyRolls = manyRollsProcess(entityManyRolls.getTimes(), entityManyRolls.getRolls(), entityManyRolls.getKtimes());
                    strResult = strResult.replaceFirst(function, strManyRolls);
                }
            }


            int result;
            if (isNumeric(strResult)) {
                result = Integer.parseInt(strResult);
            } else {
                result = (int) ceil(Calculator.conversion(strResult));
            }

            String nick = "";

            if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
                nick = getRoleChooseByFromQQ(entityTypeMessages);
            } else {
                nick = getNickName(entityTypeMessages);
            }

            String resultMessage = nick + "掷出了: ";
            Matcher matcher = p1.matcher(msg);
            if (isNumeric(msg) || (everyFunction.length == 1 && matcher.find())) {
                sender(entityTypeMessages, resultMessage + strMsg + "=" + result);
            } else if (msg.equals("d")) {
                sender(entityTypeMessages, resultMessage + strMsg + rollMaxValue + "=" + result);
            } else {
                if (strResult.equals(String.valueOf(result))) {
                    sender(entityTypeMessages, resultMessage + strMsg + "=" + result);
                }
                sender(entityTypeMessages, resultMessage + strMsg + "=" + strResult + "=" + result);
            }
        }
    }

    public void rh() {
        String tag = tagRH;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        String strTimesAndRoles = msg.split(" +")[0];
        int skill = 0;
        if (msg.split(" +").length >= 2 && isNumeric(msg.split(" +")[1])) {
            skill = Integer.parseInt(msg.split(" +")[1]);
        }

        String resultMessage = "您在群" + entityTypeMessages.getFromGroup() + "中掷出了: ";

        EntityManyRolls entityManyRolls;
        try {
            entityManyRolls = new EntityManyRolls(strTimesAndRoles, entityTypeMessages).check(resultMessage, entityTypeMessages);
        } catch (PlayerSetException e) {
            return;
        }
        sender(entityTypeMessages, strHiddenDice);
        String resHidden = makeHiddenRoll(entityManyRolls.getTimes(), entityManyRolls.getRolls());
        int intHidden = (int) ceil(Calculator.conversion(resHidden));
        if (skill != 0) {
            String resLevel = new CheckResultLevel(intHidden, skill, true).getLevelResultStr();
            entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), "您在群" + entityTypeMessages.getFromGroup() + "的暗骰结果为:\n" + entityManyRolls.getResultMessages() + resHidden + "=" + intHidden + "/" + skill + "\n" + resLevel);
        } else {
            entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), "您在群" + entityTypeMessages.getFromGroup() + "的暗骰结果为:\n" + entityManyRolls.getResultMessages() + resHidden);
        }
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
            stringBuilder.append("(");
            for (int i = 0; i < times; i++) {
                int tmpRandom = random(1, rolls);
                stringBuilder.append(tmpRandom);
                if (i != (times - 1)) {
                    stringBuilder.append("+");
                }
            }
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}
