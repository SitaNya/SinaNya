package dice.sinanya.tools.makedata;

import dice.sinanya.entity.EntityStrManyRolls;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesSystem.ROLL_MAX_VALUE;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.makedata.ManyRolls.manyRollsProcess;
import static java.lang.Math.ceil;

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
    private int resInt = 0;
    private String strFunction = "";

    public GetRollResultAndStr(EntityTypeMessages entityTypeMessages, String msg) {
        this.msg = msg;
        maxRolls = ROLL_MAX_VALUE.getOrDefault(entityTypeMessages.getFromGroup(), 100);
        makeResult();
    }

    private void makeResult() {

        if (isNumeric(msg)) {
            resInt = Integer.parseInt(msg);
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
        if (isNumeric(resStr)) {
            resInt = Integer.parseInt(resStr);
        } else {
            resInt = (int) (ceil(Calculator.conversion(resStr)));
        }


        if (maxNums == times) {
            strFunction = times + "D" + maxRolls;
        } else {
            strFunction = times + "D" + maxRolls + "K" + maxNums;
        }
    }

    public String getResStr() {
        return resStr;
    }

    public int getResInt() {
        return resInt;
    }

    public String getFunction() {
        return strFunction;
    }

    public static EntityStrManyRolls getResFunctionAndResultInt(EntityTypeMessages entityTypeMessages, String inputMsg, String[] everyFunction) {
        String strFunction = inputMsg;
        String strResult = inputMsg;
        for (String function : everyFunction) {
            if (!isNumeric(function)) {
                GetRollResultAndStr resRollResultAndStr = new GetRollResultAndStr(entityTypeMessages, function);

                strResult = strResult.replaceFirst(function, resRollResultAndStr.getResStr());
                strFunction = strFunction.replaceFirst(function, resRollResultAndStr.getFunction());
            }
        }
//            将原3d6替换为(5+5+1)，塞回原字符串里。
//            如原本是3d6+3d6，替换后是（5+5+1）+（4+3+6）
//            其中strResult存储了数学表达式如（5+5+1）+（4+3+6）
//            而strFunction存储了最初的字符表达式，如3d6+3d6


        int result;
        if (isNumeric(strResult)) {
            result = Integer.parseInt(strResult);
        } else {
            result = (int) ceil(Calculator.conversion(strResult));
        }
//            由于Calculator.conversion处理纯数字时会错误的返回0，因此这里做一下判断

        return new EntityStrManyRolls(strFunction, strResult, result);
    }
}
