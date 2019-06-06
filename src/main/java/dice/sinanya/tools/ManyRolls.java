package dice.sinanya.tools;

import static dice.sinanya.tools.RandomInt.random;

public class ManyRolls {

    public static String manyRolls(int times, int rolls) {
        int maxTimesRolls = 10;
        StringBuilder stringBuilder = new StringBuilder(200);
        int intResult = 0;
        if (times == 1) {
            int tmpResult = random(1, rolls);
            return String.valueOf(tmpResult);
        } else if (times > maxTimesRolls) {
            for (int i = 0; i < times; i++) {
                intResult += random(1, rolls);
            }
            stringBuilder.append(intResult);
        } else if (times > 1) {
            for (int i = 0; i < times; i++) {
                int tmpRandom = random(1, rolls);
                stringBuilder.append(tmpRandom);
                if (i != (times - 1)) {
                    stringBuilder.append("+");
                }
                intResult += tmpRandom;
            }
            stringBuilder.append("=").append(intResult);
        }
        return stringBuilder.toString();
    }

    public static String manyRollsProcess(int times, int rolls) {
        int maxTimesRolls = 10;
        StringBuilder stringBuilder = new StringBuilder(200);
        stringBuilder.append("(");
        int intResult = 0;
        if (times == 1) {
            int tmpResult = random(1, rolls);
            return String.valueOf(tmpResult);
        } else if (times > maxTimesRolls) {
            for (int i = 0; i < times; i++) {
                intResult += random(1, rolls);
            }
            stringBuilder.append(intResult);
        } else if (times > 1) {
            for (int i = 0; i < times; i++) {
                int tmpRandom = random(1, rolls);
                stringBuilder.append(tmpRandom);
                if (i != (times - 1)) {
                    stringBuilder.append("+");
                }
                intResult += tmpRandom;
            }
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }

    public static int manyRollsForInt(int times, int rolls) {
        int maxTimesRolls = 10;
        int intResult = 0;
        if (times == 1) {
            return random(1, rolls);
        } else if (times > maxTimesRolls) {
            for (int i = 0; i < times; i++) {
                intResult += random(1, rolls);
            }
            return intResult;
        } else if (times > 1) {
            for (int i = 0; i < times; i++) {
                intResult += random(1, rolls);
            }
            return intResult;
        } else {
            return 0;
        }
    }
}
