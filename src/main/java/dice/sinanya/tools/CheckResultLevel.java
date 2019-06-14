package dice.sinanya.tools;

import dice.sinanya.entity.EntityAntagonize;
import dice.sinanya.entity.EntityLevelResult;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesLevel.LEVEL_MAP;
import static dice.sinanya.tools.RandomInt.random;

/**
 * 根据当前骰点值、技能值、是否使用规则书来判定成功等级
 *
 * @author SitaNya
 */
public class CheckResultLevel {
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

    private String checkResultLevel() {
        if (ruleBook) {
            if (random == ruleFumble) {
                level = 0;
                return "strFumble";
            } else if (random <= skill && random <= ruleCriticalSuccess) {
                level = 5;
                return "strCriticalSuccess";
            } else {
                return checkNotNeedRules();
            }
        } else {
            ruleFumble = 95;
            ruleCriticalSuccess = 5;
            if (random >= ruleFumble && random >= skill) {
                level = 0;
                return "strFumble";
            } else if (random <= skill && random <= ruleCriticalSuccess) {
                level = 5;
                return "strCriticalSuccess";
            } else {
                return checkNotNeedRules();
            }
        }
    }

    public String getLevelResultStr() {
        String strlevel = checkResultLevel();
        ArrayList<String> resultInfo = LEVEL_MAP.get(strlevel);
        return resultInfo.get(random(0, resultInfo.size() - 1));
    }

    public EntityLevelResult getLevelAndRandom() {
        checkResultLevel();
        return new EntityLevelResult(level, random);
    }

    public EntityAntagonize getAntagonize() {
        checkResultLevel();
        return new EntityAntagonize(random, level, skill);
    }

    private String checkNotNeedRules() {
        int levelExtremeLine = 5;
        int levelHeadLine = 2;
        if (random <= skill && random <= skill / levelExtremeLine) {
            level = 4;
            return "strExtremeSuccess";
        } else if (random <= skill && random <= skill / levelHeadLine) {
            level = 3;
            return "strHardSuccess";
        } else if (random <= skill) {
            level = 2;
            return "strSuccess";
        } else {
            level = 1;
            return "strFailure";
        }
    }
}
