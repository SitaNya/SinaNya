package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.tools.Calculator;
import dice.sinanya.tools.CheckResultLevel;
import dice.sinanya.tools.GetRollResultAndStr;
import dice.sinanya.tools.GetSkillValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesError.strHiddenDice;
import static dice.sinanya.system.MessagesSystem.ROLL_MAX_VALUE;
import static dice.sinanya.system.MessagesTag.tagR;
import static dice.sinanya.system.MessagesTag.tagRH;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.Sender.sender;
import static java.lang.Math.ceil;

/**
 * .r的逻辑
 *
 * @author zhangxiaozhou
 */
public class Roll {

    private static Pattern plus = Pattern.compile("[+*/-]");

    private static Pattern AgainTimes = Pattern.compile("(\\d+)#");

    private EntityTypeMessages entityTypeMessages;

    public Roll(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * .r包含.rd的内容
     *
     * @throws PlayerSetException 如果填写错误，会报错并终止后续逻辑，这个错误类型会包含一个发往来源qq的信息（包括群）
     */
    public void r() throws PlayerSetException {
        String tag = tagR;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        int intTimes = 1;
        Matcher mAgainTimes = AgainTimes.matcher(msg);
        while (mAgainTimes.find()) {
            intTimes = Integer.parseInt(mAgainTimes.group(1));
            msg = msg.replaceFirst(AgainTimes.toString(), "");
        }
//        获取是否存在#表示重复次数

        String[] everyFunction = msg.split(plus.toString());
        if (everyFunction.length > 1 && intTimes != 1) {
            throw new PlayerSetException(entityTypeMessages);
        }
//        如果存在#号次数，则不允许多个表达式相加

        for (int i = 0; i < intTimes; i++) {
//            根据#次数循环
            String strResult = msg;
            String strFunction = msg;
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

            String nick = getNickName(entityTypeMessages);


            String resultMessage = nick + "掷出了: ";
            if (isNumeric(strResult)) {
                sender(entityTypeMessages, resultMessage + strFunction + "=" + result);
            } else {
                sender(entityTypeMessages, resultMessage + strFunction + "=" + strResult + "=" + result);
            }
//            如果骰纯数字，则不显示表达式
        }
    }

    public void rh() throws PlayerSetException {
        String tag = tagRH;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        GetSkillValue getSkillValue = new GetSkillValue(entityTypeMessages, msg);
        int skill = getSkillValue.getSkill();
//        取技能值用于判定

        int intTimes = 1;
        Matcher mAgainTimes = AgainTimes.matcher(msg);
        while (mAgainTimes.find()) {
            intTimes = Integer.parseInt(mAgainTimes.group(1));
            msg = msg.replaceFirst(AgainTimes.toString(), "");
        }
//        获取是否存在#表示重复次数

        String[] everyFunction = getSkillValue.getResStr().split(plus.toString());
        if (everyFunction.length > 1 && intTimes != 1) {
            throw new PlayerSetException(entityTypeMessages);
        }
//        如果存在#号次数，则不允许多个表达式相加


//        根据#次数循环
        for (int i = 0; i < intTimes; i++) {
            String strResult = getSkillValue.getResStr();
            String strFunction = getSkillValue.getResStr();
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

            sender(entityTypeMessages, strHiddenDice);


            int result;
            if (isNumeric(strResult)) {
                result = Integer.parseInt(strResult);
            } else {
                result = (int) ceil(Calculator.conversion(strResult));
            }
//            由于Calculator.conversion处理纯数字时会错误的返回0，因此这里做一下判断


            int maxRolls = entityTypeMessages.getFromGroup() != null ? 100 : ROLL_MAX_VALUE.get(entityTypeMessages.getFromGroup());

            int intHidden = random(1, maxRolls);

            if (strFunction.equals(strResult)) {
                strFunction = msg;
            }
//            如果数学表达式和符号表达式相等，那么只能是因为一开始输入的就是纯数字表达式，因此把可能的技能名表达式重新复制给符号表达式

            if (skill != 0) {
                String resLevel = new CheckResultLevel(intHidden, skill, true).getLevelResultStr();
                entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), "您在群" + entityTypeMessages.getFromGroup() + "的暗骰结果为:\n" + strFunction + "=" + strResult + "=" + intHidden + "/" + skill + "\n" + resLevel);
            } else {
                if (isNumeric(strFunction)) {
                    entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), "您在群" + entityTypeMessages.getFromGroup() + "的暗骰结果为:\n1D" + maxRolls + "=" + result);
                } else {
                    entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), "您在群" + entityTypeMessages.getFromGroup() + "的暗骰结果为:\n" + strFunction + "=" + strResult + "=" + result);
                }
            }
        }
    }
}
