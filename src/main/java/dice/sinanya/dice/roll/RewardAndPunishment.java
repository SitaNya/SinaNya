package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityBPRoll;
import dice.sinanya.entity.EntityRollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.CheckResultLevel;

import java.util.ArrayList;

import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetSkillValue.getSkillValue;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.MakeRollCheckResult.makeResult;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;
import static java.lang.Math.floor;

public class RewardAndPunishment {

    private EntityTypeMessages entityTypeMessages;

    public RewardAndPunishment(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void rb() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".rb");
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
                    new CheckResultLevel(resultRandom, entityRollAndCheck.getSkill(), false).getResultLevel();
        } else {
            strRes = entityRollAndCheck.getNick() +
                    "进行奖励骰鉴定: D100=" + random + "[奖励骰:" + stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1) + "] = " + resultRandom;
        }
        sender(entityTypeMessages, strRes);
    }

    public void rp() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".rp");
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
                    new CheckResultLevel(resultRandom, entityRollAndCheck.getSkill(), false).getResultLevel();
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
            times = Integer.parseInt(msg.split(" ")[0]);
            skill = Integer.parseInt(msg.split(" ")[1]);
        } else if (msg.contains(" ")) {
            if (isNumeric(msg)) {
                times = Integer.parseInt(msg);
            } else {
                times = 1;
            }
            skill = getSkillValue(entityTypeMessages, msg.split(" ")[1]);
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
