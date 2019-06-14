package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityRewardAndPunishment;
import dice.sinanya.entity.EntityRollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.CheckResultLevel;
import dice.sinanya.tools.GetSkillValue;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesSystem.SPACE;
import static dice.sinanya.system.MessagesTag.TAG_RB;
import static dice.sinanya.system.MessagesTag.TAG_RP;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.MakeRollCheckResult.makeResult;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;

/**
 * 奖励投、惩罚投
 * 这两者不再支持计算公式
 *
 * @author SitaNya
 */
public class RewardAndPunishment {

    private EntityTypeMessages entityTypeMessages;

//    过滤一个字符串的首字母是不是加减乘除

    public RewardAndPunishment(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    private int multiple = 10;
//    奖励惩罚都是取10位数

    public void rb() {
        String tag = TAG_RB;
        String msg = entityTypeMessages.getMsgGet().getMsg().trim().replaceFirst(tag.substring(0, tag.length() - 2), "");
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);

        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<Integer> listDice = makeBAndPRoll(getTimesAndSkill(msg).getTimes());
//        得到奖励投列表

        int min = 10;
        for (int result : listDice) {
            stringBuilder.append(result).append(",");
            if (result < min) {
                min = result;
            }
        }
//        取最小值

        int random = entityRollAndCheck.getRandom();

        if (random / multiple < min) {
            min = random / multiple;
        }

        int resultRandom = min * multiple + random % multiple;
//        进行替换，高位替换为整个列表中最小值

        String strRes;
        String substring = stringBuilder.substring(0, stringBuilder.length() - 1);
        if (getTimesAndSkill(msg).getSkill() != 0) {
            strRes = entityRollAndCheck.getNick() +
                    "进行奖励骰鉴定: D100=" + random + "[奖励骰:" + substring + "] = " + resultRandom + "/" + getTimesAndSkill(msg).getSkill() +
                    new CheckResultLevel(resultRandom, getTimesAndSkill(msg).getSkill(), false).getLevelResultStr();
        } else {
            strRes = entityRollAndCheck.getNick() +
                    "进行奖励骰鉴定: D100=" + random + "[奖励骰:" + substring + "] = " + resultRandom;
        }
        sender(entityTypeMessages, strRes);
//        将列表打印，并根据最后确定的值进行成功登记判定
    }

    public void rp() {
        String tag = TAG_RP;
        String msg = entityTypeMessages.getMsgGet().getMsg().trim().replaceFirst(tag.substring(0, tag.length() - 2), "");
        EntityRollAndCheck entityRollAndCheck = makeResult(entityTypeMessages, msg);

        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<Integer> listDice = makeBAndPRoll(getTimesAndSkill(msg).getTimes());
//        得到惩罚投列表

        int max = 0;
        for (int result : listDice) {
            stringBuilder.append(result).append(",");
            if (result > max) {
                max = result;
            }
        }
//        取最大值

        int random = entityRollAndCheck.getRandom();

        if (random / multiple > max) {
            max = random / multiple;
        }

        int resultRandom = max * multiple + random % multiple;
//        进行替换，高位替换为整个列表中最大值

        String strRes;
        String substring = stringBuilder.substring(0, stringBuilder.length() - 1);
        if (getTimesAndSkill(msg).getSkill() != 0) {
            strRes = entityRollAndCheck.getNick() +
                    "进行惩罚骰鉴定: D100=" + random + "[惩罚骰:" + substring + "] = " + resultRandom + "/" + getTimesAndSkill(msg).getSkill() +
                    new CheckResultLevel(resultRandom, getTimesAndSkill(msg).getSkill(), false).getLevelResultStr();
        } else {
            strRes = entityRollAndCheck.getNick() +
                    "进行惩罚骰鉴定: D100=" + random + "[惩罚骰:" + substring + "] = " + resultRandom;
        }
        sender(entityTypeMessages, strRes);
        //        将列表打印，并根据最后确定的值进行成功登记判定
    }

    private ArrayList<Integer> makeBAndPRoll(int times) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            result.add(random(0, 9));
        }
        return result;
    }

    private EntityRewardAndPunishment getTimesAndSkill(String msg) {
        int times;
        int skill;

        if (msg.contains(SPACE) && isNumeric(msg.split(SPACE)[0])) {
            times = Integer.parseInt(msg.split(" ")[0]);
        } else if (isNumeric(msg)) {
            times = Integer.parseInt(msg);
        } else {
            times = 1;
        }

        if (msg.contains(SPACE)) {
            GetSkillValue getSkillValue = new GetSkillValue(entityTypeMessages, msg.split(" ")[1]);
            skill = getSkillValue.getSkill();
        } else {
            skill = 0;
        }

        return new EntityRewardAndPunishment(times, skill);
    }
}
