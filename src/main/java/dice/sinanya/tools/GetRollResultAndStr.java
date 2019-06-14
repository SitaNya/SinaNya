package dice.sinanya.tools;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesSystem.ROLL_MAX_VALUE;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.ManyRolls.manyRollsProcess;

/**
 * 根据一个类似于3D6K3的字符串计算，返回计算表达式和结果
 *
 * @author SitaNya
 */
public class GetRollResultAndStr {
    private String msg;

    private Pattern function = Pattern.compile("(\\d*)[dD](\\d*)[kK]?(\\d*)");

    private int times = 1;
    private int maxRolls;
    private int maxNums = 1;

    private String resStr = "";
    private String strFunction = "";

    public GetRollResultAndStr(EntityTypeMessages entityTypeMessages, String msg) {
        this.msg = msg;
        maxRolls = ROLL_MAX_VALUE.getOrDefault(entityTypeMessages.getFromGroup(), 100)
        ;
        makeResult();
    }

    private void makeResult() {

        if (isNumeric(msg)) {
            resStr = msg;
            strFunction = msg;
            return;
        }

        Matcher mFunction = function.matcher(msg);

        while (mFunction.find()) {
            if (!mFunction.group(1).isEmpty()) {
                times = Integer.parseInt(mFunction.group(1));
            }
            if (!mFunction.group(2).isEmpty()) {
                maxRolls = Integer.parseInt(mFunction.group(2));
            }
            if (!mFunction.group(3).isEmpty()) {
                maxNums = Integer.parseInt(mFunction.group(3));
            } else {
                maxNums = times;
            }
        }

        resStr = manyRollsProcess(times, maxRolls, maxNums);

        if (maxNums == times) {
            strFunction = times + "D" + maxRolls;
        } else {
            strFunction = times + "D" + maxRolls + "K" + maxNums;
        }
    }

    public String getResStr() {
        return resStr;
    }

    public String getFunction() {
        return strFunction;
    }
}
