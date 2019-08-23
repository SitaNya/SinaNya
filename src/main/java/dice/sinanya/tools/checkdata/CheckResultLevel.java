package dice.sinanya.tools.checkdata;

import dice.sinanya.entity.EntityAntagonize;
import dice.sinanya.entity.EntityLevelResult;
import dice.sinanya.system.MessagesLevel;

import java.util.ArrayList;
import java.util.HashMap;

import static dice.sinanya.tools.makedata.RandomInt.random;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 根据当前骰点值、技能值、是否使用规则书来判定成功等级
 */
public class CheckResultLevel {
    /**
     * @param random 随机骰点结果
     * @param skill 技能值
     * @param ruleBook 是否使用规则书
     * @param level 成功等级
     * @param ruleFumble 定义大失败的值
     * @param ruleCriticalSuccess 定义大成功的值
     */
    private int random;
    private int skill;
    private boolean ruleBook;
    private int level;
    private int ruleFumble = 100;
    private int ruleCriticalSuccess = 1;

    public CheckResultLevel(int random, int skill, boolean ruleBook) {
        this.random = random;
        this.skill = skill;
        this.ruleBook = ruleBook;
    }

    /**
     * @return 根据是否使用规则书，来返回字符串的成功等级
     */
    private String checkResultLevel() {
        if (ruleBook) {
            if (random == ruleFumble) {
                level = 0;
                return "STR_FUMBLE";
            } else if (random <= skill && random <= ruleCriticalSuccess) {
                level = 5;
                return "STR_CRITICAL_SUCCESS";
            } else {
                return checkNotNeedRules();
            }
        } else {
            ruleFumble = 96;
            ruleCriticalSuccess = 5;
            if (random >= ruleFumble) {
                level = 0;
                return "STR_FUMBLE";
            } else if (random <= skill && random <= ruleCriticalSuccess) {
                level = 5;
                return "STR_CRITICAL_SUCCESS";
            } else {
                return checkNotNeedRules();
            }
        }
    }

    /**
     * @return 根据字符串的成功等级，获取随机的回复语
     */
    public String getLevelResultStr() {
        String strlevel = checkResultLevel();
        ArrayList<String> resultInfo = MessagesLevel.valueOf(strlevel).getText();
        return resultInfo.get(random(0, resultInfo.size() - 1));
    }

    /**
     * @return 根据字符串的成功等级，获取随机的回复语
     */
    public String getLevelResultStrForSimple() {
        String strlevel = checkResultLevel();
        HashMap<String, String> resultInfo = new HashMap<>();
        resultInfo.put("STR_CRITICAL_SUCCESS", "大成功");
        resultInfo.put("STR_EXTREME_SUCCESS", "极难成功");
        resultInfo.put("STR_HARD_SUCCESS", "困难成功");
        resultInfo.put("STR_SUCCESS", "成功");
        resultInfo.put("STR_FAILURE", "失败");
        resultInfo.put("STR_FUMBLE", "大失败");
        return resultInfo.get(strlevel);
    }

    /**
     * @return 判断成功等级后，返回包含成功等级和随机骰结果的成功等级对象
     */
    public EntityLevelResult getLevelAndRandom() {
        checkResultLevel();
        return new EntityLevelResult(level, random);
    }

    /**
     * @return 判断成功等级后，返回包含成功等级、随机骰结果和技能值的对抗对象
     */
    public EntityAntagonize getAntagonize() {
        checkResultLevel();
        return new EntityAntagonize(random, level, skill);
    }

    /**
     * @return 判断逻辑中不需要规则书的部分
     */
    private String checkNotNeedRules() {
        int levelExtremeLine = 5;
        int levelHeadLine = 2;
        if (random <= skill && random <= skill / levelExtremeLine) {
            level = 4;
            return "STR_EXTREME_SUCCESS";
        } else if (random <= skill && random <= skill / levelHeadLine) {
            level = 3;
            return "STR_HARD_SUCCESS";
        } else if (random <= skill) {
            level = 2;
            return "STR_SUCCESS";
        } else {
            level = 1;
            return "STR_FAILURE";
        }
    }

    /**
     * @return 返回成功等级的标志数字
     */
    public int getLevel() {
        return level;
    }
}
