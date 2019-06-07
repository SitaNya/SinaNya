package dice.sinanya.tools;

import dice.sinanya.entity.EntityLevelResult;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesLevel.LEVEL_MAP;
import static dice.sinanya.tools.RandomInt.random;

public class CheckResultLevel {
    private int random;
    private int skill;
    private boolean ruleBook;
    private int level;

    public CheckResultLevel(int random, int skill, boolean ruleBook) {
        this.random = random;
        this.skill = skill;
        this.ruleBook = ruleBook;
    }

    private String checkResultLevel() {
        if (ruleBook) {
            if (random == 100) {
                level = 0;
                return "strFumble";
            } else if (random <= skill && random <= 1) {
                level = 5;
                return "strCriticalSuccess";
            } else if (random <= skill && random <= skill / 5) {
                level = 4;
                return "strExtremeSuccess";
            } else if (random <= skill && random <= skill / 2) {
                level = 3;
                return "strHardSuccess";
            } else if (random <= skill) {
                level = 2;
                return "strSuccess";
            } else {
                level = 1;
                return "strFailure";
            }
        } else {
            if (random >= 95 && random >= skill) {
                level = 0;
                return "strFumble";
            } else if (random <= skill && random <= 5) {
                level = 5;
                return "strCriticalSuccess";
            } else if (random <= skill && random <= skill / 5) {
                level = 4;
                return "strExtremeSuccess";
            } else if (random <= skill && random <= skill / 2) {
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

    public String getResultLevel() {
        String strlevel = checkResultLevel();
        ArrayList<String> resultInfo = LEVEL_MAP.get(strlevel);
        return resultInfo.get(random(0, resultInfo.size() - 1));
    }

    public EntityLevelResult getLevelResult() {
        return new EntityLevelResult(level, random);
    }
}
