package dice.sinanya.tools;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesLevel.LEVEL_MAP;
import static dice.sinanya.tools.RandomInt.random;

public class CheckResultLevel {
    private int random;
    private int skill;
    private boolean ruleBook;

    public CheckResultLevel(int random, int skill, boolean ruleBook) {
        this.random = random;
        this.skill = skill;
        this.ruleBook = ruleBook;
    }

    private String checkResultLevel() {
        if (ruleBook) {
            if (random == 100) {
                return "strFumble";
            } else if (random <= skill && random <= 1) {
                return "strCriticalSuccess";
            } else if (random <= skill && random <= skill / 5) {
                return "strExtremeSuccess";
            } else if (random <= skill && random <= skill / 2) {
                return "strHardSuccess";
            } else if (random <= skill) {
                return "strSuccess";
            } else {
                return "strFailure";
            }
        } else {
            if (random >= 95 && random >= skill) {
                return "strFumble";
            } else if (random <= skill && random <= 5) {
                return "strCriticalSuccess";
            } else if (random <= skill && random <= skill / 5) {
                return "strExtremeSuccess";
            } else if (random <= skill && random <= skill / 2) {
                return "strHardSuccess";
            } else if (random <= skill) {
                return "strSuccess";
            } else {
                return "strFailure";
            }
        }
    }

    public String getResultLevel() {
        String strlevel = checkResultLevel();
        ArrayList<String> resultInfo = LEVEL_MAP.get(strlevel);
        return resultInfo.get(random(0, resultInfo.size() - 1));
    }
}
