package dice.sinanya.tools;

import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.ManyRolls.manyRollsForInt;
import static dice.sinanya.tools.ManyRolls.manyRollsProcess;

public class MakeManyRollsStr {
    private int strTimes = 1;
    private int strRolls = 100;
    private int resInt;
    private String resStr;

    public MakeManyRollsStr(String input) {
        makeManyRollsStr(input);
    }

    private void makeManyRollsStr(String input) {
        String tagNone = "";
        String strFirst = "";
        String strSecond = "";

        if (input.contains("d") || input.contains("D")) {
            strFirst = input.split("[dD]")[0];
            strSecond = input.split("[dD]")[1];
        }else if (isNumeric(input)){
            strSecond=input;
        }

        if (isNumeric(strFirst) && !strFirst.equals(tagNone)) {
            strTimes = Integer.parseInt(strFirst);
        }

        if (isNumeric(strSecond) && !strSecond.equals(tagNone)) {
            strRolls = Integer.parseInt(strSecond);
        }else{
            strRolls=strTimes;
        }
        resInt=manyRollsForInt(strTimes,strRolls);
        resStr=manyRollsProcess(strTimes,strRolls);
    }

    public String getStr(){
        return resStr;
    }

    public int getRes(){
        return resInt;
    }

}
