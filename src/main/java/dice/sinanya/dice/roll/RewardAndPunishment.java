package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityBPRoll;
import dice.sinanya.entity.EntityRollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.Calculator;
import dice.sinanya.tools.CheckResultLevel;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesTag.tagRB;
import static dice.sinanya.system.MessagesTag.tagRP;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetSkillValue.getSkillValue;
import static dice.sinanya.tools.MakeRollCheckResult.makeResult;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;

/**
 * 奖励投、惩罚投
 * 这两者不再支持计算公式
 *
 * @author zhangxiaozhou
 */
public class RewardAndPunishment {

    private EntityTypeMessages entityTypeMessages;

    private static Pattern SkillNameAndSkill = Pattern.compile("([^\\d]+)(\\d)");
    private static Pattern p2 = Pattern.compile("(\\d+d\\d+)");
    private static Pattern p3 = Pattern.compile("[+*-/]");
    private static Pattern p4 = Pattern.compile("(\\d+)");

    public RewardAndPunishment(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void rb() {
        String tag = tagRB;
        String msg = entityTypeMessages.getMsgGet().getMsg().trim().replaceFirst(tag.substring(0, tag.length() - 2), "");
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);

        StringBuilder stringBuilder = new StringBuilder();

        int times = getTimesAndSkill(msg).getTimes();
        int skill = getTimesAndSkill(msg).getSkill();
        ArrayList<Integer> listDice = makeBAndPRoll(times);

        int min = 10;
        for (int result : listDice) {
            stringBuilder.append(result).append(",");
            if (result < min) {
                min = result;
            }
        }

        int random = entityRollAndCheck.getRandom();

        if ((int) floor(random / 10) < min) {
            min = (int) floor(random / 10);
        }

        int resultRandom = min * 10 + random % 10;

        String strRes;
        if (skill != 0) {
            strRes = entityRollAndCheck.getNick() +
                    "进行奖励骰鉴定: D100=" + random + "[奖励骰:" + stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1) + "] = " + resultRandom + "/" + skill +
                    new CheckResultLevel(resultRandom, skill, false).getLevelResultStr();
        } else {
            strRes = entityRollAndCheck.getNick() +
                    "进行奖励骰鉴定: D100=" + random + "[奖励骰:" + stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1) + "] = " + resultRandom;
        }
        sender(entityTypeMessages, strRes);
    }

    public void rp() {
        String tag = tagRP;
        String msg = entityTypeMessages.getMsgGet().getMsg().trim().replaceFirst(tag.substring(0, tag.length() - 2), "");
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);

        StringBuilder stringBuilder = new StringBuilder();

        int times = getTimesAndSkill(msg).getTimes();
        int skill = getTimesAndSkill(msg).getSkill();
        ArrayList<Integer> listDice = makeBAndPRoll(times);

        int max = 0;
        for (int result : listDice) {
            stringBuilder.append(result).append(",");
            if (result > max) {
                max = result;
            }
        }

        int random = entityRollAndCheck.getRandom();

        if ((int) floor(random / 10) > max) {
            max = (int) floor(random / 10);
        }

        int resultRandom = max * 10 + random % 10;

        String strRes;
        if (skill != 0) {
            strRes = entityRollAndCheck.getNick() +
                    "进行惩罚骰鉴定: D100=" + random + "[惩罚骰:" + stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1) + "] = " + resultRandom + "/" + skill +
                    new CheckResultLevel(resultRandom, skill, false).getLevelResultStr();
        } else {
            strRes = entityRollAndCheck.getNick() +
                    "进行惩罚骰鉴定: D100=" + random + "[惩罚骰:" + stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1) + "] = " + resultRandom;
        }
        sender(entityTypeMessages, strRes);
    }

    private ArrayList<Integer> makeBAndPRoll(int times) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            result.add(random(0, 9));
        }
        return result;
    }

    private EntityBPRoll getTimesAndSkill(String msg) {
        int times;
        int skill;
        if (msg.contains(" ") && isNumeric(msg.split(" ")[1])) {
            if (msg.split(" ")[0].equals("")) {
                times = 1;
            } else {
                times = Integer.parseInt(msg.split(" ")[0]);
            }
            skill = Integer.parseInt(msg.split(" ")[1]);
        } else if (msg.contains(" ")) {
            if (isNumeric(msg.split(" ")[0])) {
                times = Integer.parseInt(msg.split(" ")[0]);
            } else {
                times = 1;
            }
            Matcher findFuncAgain = p3.matcher(msg.split(" ")[1]);
            if (findFuncAgain.find()) {
                int tmpSkill = 0;
                if (msg.split(" ")[1].charAt(1) != '+' && !Character.isDigit(msg.split(" ")[1].charAt(1))) {
                    String strFunc = msg.split(" ")[1];
                    int i = 0;
                    StringBuilder skillName = new StringBuilder();
                    while (!Character.isDigit(strFunc.charAt(i)) && strFunc.charAt(i) != '+' && strFunc.charAt(i) != '-' && strFunc.charAt(i) != '*' && strFunc.charAt(i) != '/' && strFunc.charAt(i) != 'x' && strFunc.charAt(i) != 'X') {
                        skillName.append(strFunc.charAt(i));
                        i++;
                    }
                    strFunc = strFunc.replaceFirst(skillName.toString(), String.valueOf(getSkillValue(entityTypeMessages, skillName.toString())));
                    skill = (int) ceil(Calculator.conversion(strFunc));
                } else {
                    skill = (int) ceil(Calculator.conversion(msg.split(" ")[1]));
                }
            } else {
                skill = getSkillValue(entityTypeMessages, msg.split(" ")[1]);
            }

        } else {
            if (isNumeric(msg)) {
                times = Integer.parseInt(msg);
            } else {
                times = 1;
            }
            skill = 0;
        }
        return new EntityBPRoll(times, skill);
    }
}
