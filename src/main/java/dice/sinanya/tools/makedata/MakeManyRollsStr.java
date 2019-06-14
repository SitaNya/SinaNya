package dice.sinanya.tools.makedata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.makedata.ManyRolls.manyRollsForInt;
import static dice.sinanya.tools.makedata.ManyRolls.manyRollsProcess;

public class MakeManyRollsStr {
    private int strTimes = 1;
    private int resInt;
    private String resStr;

    private static Pattern KeyAndNums = Pattern.compile("[kK]([\\d]+)");

    public MakeManyRollsStr(String input) {
        makeManyRollsStr(input);
    }

    private void makeManyRollsStr(String input) {
        String tagNone = "";
        String strFirst = "";
        String strSecond = "";
        int maxNums = 1;

        if (input.contains("d") || input.contains("D")) {
            strFirst = input.split("[dD]")[0];
            strSecond = input.split("[dD]")[1];
        } else if (isNumeric(input)) {
            strSecond = input;
        }

        if (isNumeric(strFirst) && !strFirst.equals(tagNone)) {
            strTimes = Integer.parseInt(strFirst);
        }

        int strRolls;
        if (isNumeric(strSecond) && !strSecond.equals(tagNone)) {
            strRolls = Integer.parseInt(strSecond);
        } else {
            strRolls = strTimes;
        }

        Matcher mKeyAndNums = KeyAndNums.matcher(input);
        while (mKeyAndNums.find()) {
            maxNums = Integer.parseInt(mKeyAndNums.group(1));
        }

        if (strRolls == 0) {
            resInt = 0;
            resStr = "0";
        } else {
            resInt = manyRollsForInt(strTimes, strRolls, maxNums);
            resStr = manyRollsProcess(strTimes, strRolls, maxNums);
        }
    }

    public String getStr() {
        return resStr;
    }

    public int getRes() {
        return resInt;
    }

}
