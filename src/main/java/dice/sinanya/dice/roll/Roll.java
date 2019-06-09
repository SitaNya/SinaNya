package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityManyRolls;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.tools.Calculator;
import dice.sinanya.tools.CheckResultLevel;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesError.strDiceTimesTooBig;
import static dice.sinanya.system.MessagesError.strHiddenDice;
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

    private static Pattern p1 = Pattern.compile("(\\d+[dD][^\\d+\\-*/]*)");
    private static Pattern p2 = Pattern.compile("(\\d+[dD]\\d+)");
    private static Pattern p3 = Pattern.compile("[+*/-]");
    private EntityTypeMessages entityTypeMessages;

    public Roll(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void r() {
        String tag = tagR;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        if (msg.equals("") || msg.equals("d")) {
            msg = "100";
        }

        String nick = "";

        if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
            nick = getRoleChooseByFromQQ(entityTypeMessages);
        } else {
            nick = getNickName(entityTypeMessages);
        }

        String resultMessage = nick + "掷出了: ";

        ArrayList<String> strTimesAndRolesList = new ArrayList<>();
        Matcher matcher = p2.matcher(msg);
        boolean setMaxValue = true;
        while (matcher.find()) {
            strTimesAndRolesList.add(matcher.group(1));
            setMaxValue = false;
        }
        ArrayList<String> resultRoll = new ArrayList<>();

        Matcher matcher1 = p1.matcher(msg);
        while (matcher1.find()) {
            strTimesAndRolesList.add(matcher1.group(1) + "100");
            msg=msg.replaceFirst("(\\d+[dD][^\\d+\\-*/]*)",matcher1.group(1)+"100");
        }

        for (String strTimesAndRoles : strTimesAndRolesList) {
            EntityManyRolls entityManyRolls;
            try {
                entityManyRolls = new EntityManyRolls(strTimesAndRoles).check(entityTypeMessages);
            } catch (PlayerSetException e) {
                return;
            }
            resultRoll.add(manyRollsProcess(entityManyRolls.getTimes(), entityManyRolls.getRolls()));
        }
        int i = 0;
        Matcher findFunc = p3.matcher(msg);
        if (!findFunc.find()) {
            if (setMaxValue) {
                resultMessage += msg + "=";
            } else {
                resultMessage += "D" + msg + "=";
            }
        }

        Matcher findTimesAndRoles = p2.matcher(msg);
        while (findTimesAndRoles.find()) {
            msg = msg.replaceFirst(findTimesAndRoles.group(1), resultRoll.get(i));
            i++;
        }

        Matcher findFuncAgain = p3.matcher(msg);
        if (findFuncAgain.find()) {
            resultMessage += msg + "=" + (int) ceil(Calculator.conversion(msg));
        } else {
            resultMessage += random(1, Integer.parseInt(msg.replaceAll("d", "")));
        }
        sender(entityTypeMessages, resultMessage);
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
            entityManyRolls = new EntityManyRolls(strTimesAndRoles).check(resultMessage, entityTypeMessages);
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
