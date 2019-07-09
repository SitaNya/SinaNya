package dice.sinanya.tools.makedata;

import dice.sinanya.entity.EntityStrManyRolls;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesRollMaxValue.ROLL_MAX_VALUE;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.makedata.ManyRolls.manyRollsProcess;
import static java.lang.Math.ceil;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 根据一个类似于3D6K3的字符串计算，返回计算表达式和结果
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

    /**
     * 这里取最大默认骰，没取到则默认为100
     * 会自动调用makeResult对消息进行处理
     *
     * @param entityTypeMessages 消息封装类
     * @param msg                传入的所有信息
     */
    public GetRollResultAndStr(EntityTypeMessages entityTypeMessages, String msg) {
        this.msg = msg;
        maxRolls = ROLL_MAX_VALUE.getOrDefault(entityTypeMessages.getFromGroup(), 100);
        makeResult();
    }

    /**
     * 使用分隔符分割后的分段表达式和整体信息进行替换，最终得出字符表达式和数字表达式并返回
     *
     * @param entityTypeMessages 消息封装类
     * @param inputMsg           输入的整体值
     * @param everyFunction      通过运算符分割后的分段值
     * @return 最终得出的字符、数字表达式
     */
    public static EntityStrManyRolls getResFunctionAndResultInt(EntityTypeMessages entityTypeMessages, String inputMsg, String[] everyFunction) {
        String strFunction = inputMsg;
        String strResult = inputMsg;
        if (isNumeric(inputMsg)){
            return new EntityStrManyRolls(inputMsg, inputMsg, Integer.parseInt(inputMsg));
        }
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

    /**
     * 自动处理信息，从里面使用正则function进行查找(\d*)[dD](\d*)[kK]?(\d*)
     * <p>
     * 可以分别取出XdXkX中的3个X
     */
    private void makeResult() {

        if (isNumeric(msg)) {
            resInt = Integer.parseInt(msg);
            resStr = msg;
            strFunction = msg;
            return;
        }

        Matcher mFunction = function.matcher(msg);

        while (mFunction.find()) {
//            取第一个X，是骰点次数，这个times已经默认设定为1，如果取到则覆盖
            if (!mFunction.group(1).isEmpty()) {
                times = Integer.parseInt(mFunction.group(1));
            }
//            取第二个X，是骰点最大值，这个maxRolls已根据设定的默认最大值或100设定，如果取到则覆盖
            if (!mFunction.group(2).isEmpty()) {
                maxRolls = Integer.parseInt(mFunction.group(2));
            }
//            取第三个X，是获取最大几个结果（也就是K命令，如果你不知道这是什么的话，可以输入.r4d6k2试试）
//            如果取到则使用取到的值
//            如果没取到则默认为与times值相等
            if (!mFunction.group(3).isEmpty()) {
                maxNums = Integer.parseInt(mFunction.group(3));
            } else {
                maxNums = times;
            }
        }

//        通过manyRollsProcess计算得出结果字符串
        resStr = manyRollsProcess(times, maxRolls, maxNums);
        if (isNumeric(resStr)) {
            resInt = Integer.parseInt(resStr);
        } else {
            resInt = (int) (ceil(Calculator.conversion(resStr)));
        }

//        如果K和最大值相等，则K值无效，因此不打印K参数
        if (maxNums == times) {
            strFunction = times + "D" + maxRolls;
        } else {
            strFunction = times + "D" + maxRolls + "K" + maxNums;
        }
    }

    public int getResInt() {
        return resInt;
    }

    String getResStr() {
        return resStr;
    }

    private String getFunction() {
        return strFunction;
    }
}
