package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityStrManyRolls;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.tools.checkdata.CheckResultLevel;
import dice.sinanya.tools.getinfo.GetSkillValue;
import dice.sinanya.tools.makedata.Calculator;
import dice.sinanya.tools.makedata.GetRollResultAndStr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.GetMessagesSystem.messagesSystem;
import static dice.sinanya.system.MessagesSystem.ROLL_MAX_VALUE;
import static dice.sinanya.system.MessagesTag.TAGR;
import static dice.sinanya.system.MessagesTag.TAG_RH;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.getinfo.RandomInt.random;
import static dice.sinanya.tools.log.Sender.sender;
import static dice.sinanya.tools.makedata.GetRollResultAndStr.getResFunctionAndResultInt;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static java.lang.Math.ceil;

/**
 * .r的逻辑
 *
 * @author SitaNya
 */
public class Roll {

    private static Pattern plus = Pattern.compile("[+*/\\-]");

    private static Pattern function = Pattern.compile("([+*/\\-dDkK#\\d]+)");

    private static Pattern AgainTimes = Pattern.compile("(\\d+)#");

    private EntityTypeMessages entityTypeMessages;

    public Roll(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * .r包含.rd的内容
     */
    public void r() {
        String tag = TAGR;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));

        String nick = getNickName(entityTypeMessages);


        StringBuilder stringBuilderFunction = new StringBuilder();
        StringBuilder resultMessage = new StringBuilder(nick + "掷出了: ");

        int intTimes = 1;
        Matcher mAgainTimes = AgainTimes.matcher(msg);
        while (mAgainTimes.find()) {
            intTimes = Integer.parseInt(mAgainTimes.group(1));
            msg = msg.replaceFirst(AgainTimes.toString(), "");
        }
//        获取是否存在#表示重复次数

        Matcher mFunction = function.matcher(msg);

        if (mFunction.find()) {
            stringBuilderFunction.append(mFunction.group(1));
            msg = msg.replaceFirst(function.toString(), "");
        }
        if (!msg.isEmpty()) {
            resultMessage.append(msg).append(": ");
        }
        String[] everyFunction = stringBuilderFunction.toString().split(plus.toString());

        for (int i = 0; i < intTimes; i++) {
//            根据#次数循环
            EntityStrManyRolls entityStrManyRolls = getResFunctionAndResultInt(entityTypeMessages, stringBuilderFunction.toString(), everyFunction);


            if (isNumeric(entityStrManyRolls.getStrResult())) {
                sender(entityTypeMessages, resultMessage + entityStrManyRolls.getStrFunction() + "=" + entityStrManyRolls.getResult());
            } else {
                sender(entityTypeMessages, resultMessage + entityStrManyRolls.getStrFunction() + "=" + entityStrManyRolls.getStrResult() + "=" + entityStrManyRolls.getResult());
            }
//            如果骰纯数字，则不显示表达式
        }
    }

    public void rh() {
        String tag = TAG_RH;
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

            sender(entityTypeMessages, messagesSystem.get("hiddenDice"));

            int result;
            if (isNumeric(strResult)) {
                result = Integer.parseInt(strResult);
            } else {
                result = (int) ceil(Calculator.conversion(strResult));
            }
//            由于Calculator.conversion处理纯数字时会错误的返回0，因此这里做一下判断


            int maxRolls = ROLL_MAX_VALUE.getOrDefault(entityTypeMessages.getFromGroup(), 100);

            int intHidden = random(1, maxRolls);

            if (strFunction.equals(strResult)) {
                strFunction = msg;
            }
//            如果数学表达式和符号表达式相等，那么只能是因为一开始输入的就是纯数字表达式，因此把可能的技能名表达式重新复制给符号表达式

            if (skill != 0) {
                String resLevel = new CheckResultLevel(intHidden, skill, true).getLevelResultStr();
                entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQq(), "您在群" + entityTypeMessages.getFromGroup() + "的暗骰结果为:\n" + strFunction + "=" + strResult + "=" + intHidden + "/" + skill + "\n" + resLevel);
            } else {
                if (isNumeric(strFunction)) {
                    entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQq(), "您在群" + entityTypeMessages.getFromGroup() + "的暗骰结果为:\n1D" + maxRolls + "=" + result);
                } else {
                    entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQq(), "您在群" + entityTypeMessages.getFromGroup() + "的暗骰结果为:\n" + strFunction + "=" + strResult + "=" + result);
                }
            }
        }
    }
}
