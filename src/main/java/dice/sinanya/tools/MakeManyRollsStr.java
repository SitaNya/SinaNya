package dice.sinanya.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.ManyRolls.manyRollsForInt;
import static dice.sinanya.tools.ManyRolls.manyRollsProcess;

public class MakeManyRollsStr {
    private int strTimes = 1;
    private int strRolls = 100;
    private int resInt;
    private String resStr;

    private static Pattern KAndNums = Pattern.compile("[kK]([\\d]+)");

    public MakeManyRollsStr(String input) {
        makeManyRollsStr(input);
    }

    private void makeManyRollsStr(String input) {
        String tagNone = "";
        String strFirst = "";
        String strSecond = "";
        int Ktimes = 1;

        if (input.contains("d") || input.contains("D")) {
            strFirst = input.split("[dD]")[0];
            strSecond = input.split("[dD]")[1];
        } else if (isNumeric(input)) {
            strSecond = input;
        }

        if (isNumeric(strFirst) && !strFirst.equals(tagNone)) {
            strTimes = Integer.parseInt(strFirst);
        }

        if (isNumeric(strSecond) && !strSecond.equals(tagNone)) {
            strRolls = Integer.parseInt(strSecond);
        } else {
            strRolls = strTimes;
        }

        Matcher mKTimes = KAndNums.matcher(input);
        while (mKTimes.find()) {
            Ktimes = Integer.parseInt(mKTimes.group(1));
        }

        if (strRolls == 0) {
            resInt = 0;
            resStr = "0";
        } else {
            resInt = manyRollsForInt(strTimes, strRolls, Ktimes);
            resStr = manyRollsProcess(strTimes, strRolls, Ktimes);
        }
    }

    public String getStr() {
        return resStr;
    }

    public int getRes() {
        return resInt;
    }

}
