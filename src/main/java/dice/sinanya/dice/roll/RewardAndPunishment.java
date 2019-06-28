package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityNickAndRandomAndSkill;
import dice.sinanya.entity.EntityRewardAndPunishment;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.checkdata.CheckResultLevel;
import dice.sinanya.tools.getinfo.GetSkillValue;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesSystem.SPACE;
import static dice.sinanya.system.MessagesTag.TAG_RB;
import static dice.sinanya.system.MessagesTag.TAG_RP;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.makedata.GetNickAndRandomAndSkill.getNickAndRandomAndSkill;
import static dice.sinanya.tools.makedata.RandomInt.random;
import static dice.sinanya.tools.makedata.Sender.sender;
import static java.lang.Integer.min;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 奖励骰、惩罚投。这两者不再支持计算公式
 */
public class RewardAndPunishment implements En {

    private EntityTypeMessages entityTypeMessages;

    //    过滤一个字符串的首字母是不是加减乘除
    private int multiple = 10;

    public RewardAndPunishment(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }
//    奖励惩罚都是取10位数

    /**
     * 奖励骰
     */
    public void rb() {
        String tag = TAG_RB;
        String msg = entityTypeMessages.getMsgGet().getMsg().trim().replaceFirst(tag.substring(0, tag.length() - 2), "");
        EntityNickAndRandomAndSkill entityNickAndRandomAndSkill = getNickAndRandomAndSkill(entityTypeMessages, msg);

        StringBuilder stringBuilder = new StringBuilder();

        int random = entityNickAndRandomAndSkill.getRandom();

        ArrayList<Integer> listDice = makeBAndPRoll(getTimesAndSkill(msg).getTimes());
//        得到奖励投列表

        int min = 10;
        for (int result : listDice) {
            stringBuilder.append(result).append(",");
            if (result < min) {
                if (random % 10 != 0 || result != 0) {
                    min = result;
                }
            }
        }
//        取最小值


        if (random / multiple < min) {
            min = random / multiple;
        }

        int resultRandom = min * multiple + random % multiple;
//        进行替换，高位替换为整个列表中最小值


        String strRes;
        String substring = stringBuilder.substring(0, stringBuilder.length() - 1);
        if (getTimesAndSkill(msg).getSkill() != 0) {
            CheckResultLevel checkResultLevel = new CheckResultLevel(resultRandom, getTimesAndSkill(msg).getSkill(), false);
            strRes = entityNickAndRandomAndSkill.getNick() +
                    "进行奖励骰鉴定: D100=" + random + "[奖励骰:" + substring + "] = " + resultRandom + "/" + getTimesAndSkill(msg).getSkill() +
                    checkResultLevel.getLevelResultStr();
            checkEn(checkResultLevel.getLevel(), msg, entityTypeMessages.getFromQq(), entityTypeMessages.getFromGroup());
        } else {
            strRes = entityNickAndRandomAndSkill.getNick() +
                    "进行奖励骰鉴定: D100=" + random + "[奖励骰:" + substring + "] = " + resultRandom;
        }

        sender(entityTypeMessages, strRes);
//        将列表打印，并根据最后确定的值进行成功登记判定
    }

    /**
     * 惩罚骰
     */
    public void rp() {
        String tag = TAG_RP;
        String msg = entityTypeMessages.getMsgGet().getMsg().trim().replaceFirst(tag.substring(0, tag.length() - 2), "");
        EntityNickAndRandomAndSkill entityNickAndRandomAndSkill = getNickAndRandomAndSkill(entityTypeMessages, msg);

        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<Integer> listDice = makeBAndPRoll(getTimesAndSkill(msg).getTimes());
//        得到惩罚投列表

        int max = 0;
        for (int result : listDice) {
            if (result == 0) {
                result = 10;
            }
            stringBuilder.append(result).append(",");
            if (result > max) {
                max = result;
            }
        }
//        取最大值

        int random = entityNickAndRandomAndSkill.getRandom();

        if (random / multiple > max) {
            max = random / multiple;
        }

        int resultRandom = min(100,max * multiple + random % multiple);
//        进行替换，高位替换为整个列表中最大值

        String strRes;
        String substring = stringBuilder.substring(0, stringBuilder.length() - 1);
        if (getTimesAndSkill(msg).getSkill() != 0) {
            CheckResultLevel checkResultLevel = new CheckResultLevel(resultRandom, getTimesAndSkill(msg).getSkill(), false);
            strRes = entityNickAndRandomAndSkill.getNick() +
                    "进行惩罚骰鉴定: D100=" + random + "[惩罚骰:" + substring + "] = " + resultRandom + "/" + getTimesAndSkill(msg).getSkill() +
                    checkResultLevel.getLevelResultStr();
            checkEn(checkResultLevel.getLevel(), msg, entityTypeMessages.getFromQq(), entityTypeMessages.getFromGroup());
        } else {
            strRes = entityNickAndRandomAndSkill.getNick() +
                    "进行惩罚骰鉴定: D100=" + random + "[惩罚骰:" + substring + "] = " + resultRandom;
        }
        sender(entityTypeMessages, strRes);
        //        将列表打印，并根据最后确定的值进行成功登记判定
    }

    /**
     * 根据奖励骰或惩罚骰的个数，返回一定数量的0-9随机数列表
     *
     * @param times 个数
     * @return 随机数列表
     */
    private ArrayList<Integer> makeBAndPRoll(int times) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            result.add(random(0, 9));
        }
        return result;
    }

    /**
     * 取出输入信息中的技能值（如果是技能名，则替换为技能值），与惩罚/奖励骰个数，包装后返回
     *
     * @param msg 参数信息字符串，是去除了.rp|.rb后剩下的所有信息
     * @return 惩罚、奖励骰方法，包含技能值和个数
     */
    private EntityRewardAndPunishment getTimesAndSkill(String msg) {
        int times;
        int skill;

        /*
        因为rb格式为“.rb个数 技能”，因此空格前的如果是数字，或者只包含数字则认为是个数。否则个数默认为1
         */
        if (msg.contains(SPACE) && isNumeric(msg.split(SPACE)[0])) {
            times = Integer.parseInt(msg.split(SPACE)[0]);
        } else if (isNumeric(msg)) {
            times = Integer.parseInt(msg);
        } else {
            times = 1;
        }

        /*
        如果包含空格，则第二个参数一定存在且为技能，传递给GetSkillValue方法
        如果是数字则直接认为是技能，如果文字则查找对方当前人物卡确定技能值
         */
        if (msg.contains(SPACE)) {
            GetSkillValue getSkillValue = new GetSkillValue(entityTypeMessages, msg.split(SPACE)[1]);
            skill = getSkillValue.getSkill();
        } else {
            skill = 0;
        }

        return new EntityRewardAndPunishment(times, skill);
    }
}
