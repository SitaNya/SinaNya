package dice.sinanya.dice.roll;

import dice.sinanya.dice.MakeNickToSender;
import dice.sinanya.entity.EntityStrManyRolls;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.ManyRollsTimesTooMoreException;
import dice.sinanya.tools.checkdata.CheckResultLevel;
import dice.sinanya.tools.getinfo.GetSkillValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.MessagesRollMaxValue.ROLL_MAX_VALUE;
import static dice.sinanya.system.MessagesTag.TAGR;
import static dice.sinanya.system.MessagesTag.TAG_RH;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;

import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
import static dice.sinanya.tools.getinfo.GetNickName.getGroupName;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.makedata.GetRollResultAndStr.getResFunctionAndResultInt;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.RandomInt.random;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 默认骰掷，最基础也是最繁复的一个方法，里面包含很多种逻辑
 */
public class Roll implements MakeNickToSender {

    private static Pattern plus = Pattern.compile("[+*/\\-]");

    private static Pattern function = Pattern.compile("([+*/\\-dDkK#\\d]+)");

    private static Pattern AgainTimes = Pattern.compile("(\\d+)#");

    private EntityTypeMessages entityTypeMessages;

    public Roll(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * .r与.rd的骰点逻辑
     * 支持#表示骰点次数（分多次回复）
     * d默认为1D100
     * 1D默认为1D100
     * d60默认为1D60
     * 支持各种各样的计算表达式
     * 不支持技能名替换
     * 支持k+数字的方式取出几个较大值
     * 支持f+数字的方式取出几个较小值
     */
    public void r() throws ManyRollsTimesTooMoreException {
        String tag = TAGR;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2)).replaceAll(" +", "");

        String nick = makeNickToSender(getNickName(entityTypeMessages));


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

    /**
     * 隐蔽骰点
     * 同样支持上面的所有表达式
     * 支持使用技能或表达式进行判定
     */
    public void rh() throws ManyRollsTimesTooMoreException {
        String tag = TAG_RH;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));

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
//        通过运算符将表达式分段，getSkillValue.getResStr()得到的是，将技能名替换为技能值后其余不变的字符串，比如力量+20的结果是50+20


//        根据#次数循环
        for (int i = 0; i < intTimes; i++) {
            EntityStrManyRolls entityStrManyRolls = getResFunctionAndResultInt(entityTypeMessages, getSkillValue.getResStr(), everyFunction);
//            计算得出表达式最终的
//            strFunction符号表达式:3d6k2+4d6*3+d4/2-6d
//            strResult结果表达式: (1+2)+(1+3+4+6)*3+(2)/2-(32+35+12+54)
//            Result实际结果:-87
            sender(entityTypeMessages, entitySystemProperties.getHiddenDice());
//            在群中发出暗骰提示

            int maxRolls = ROLL_MAX_VALUE.getOrDefault(entityTypeMessages.getFromGroup(), 100);
//            确认默认骰点最大值
            int intHidden = random(1, maxRolls);
//            得出骰点结果

            if (entityStrManyRolls.getStrFunction().equals(entityStrManyRolls.getStrResult())) {
                entityStrManyRolls.setStrFunction(msg);
            }
//            如果数学表达式和符号表达式相等，那么只能是因为一开始输入的就是纯数字表达式，因此把可能的技能名表达式重新复制给符号表达式

            String groupName = makeGroupNickToSender(getGroupName(entityTypeMessages));
            if (skill != 0) {
//                如果技能值取到了，那就不管表达式直接进行判定
                String resLevel = new CheckResultLevel(intHidden, skill, true).getLevelResultStr();
                CQ.sendPrivateMsg(Long.parseLong(entityTypeMessages.getFromQq()), "您在群" + groupName + "(" + entityTypeMessages.getFromGroup() + ")的暗骰结果为:\n1D" + skill + "=" + intHidden + "/" + skill + "\n" + resLevel);
                return;
            } else {
//                如果技能值没取到，则使用表达式进行判定
                if (isNumeric(entityStrManyRolls.getStrFunction())) {
                    CQ.sendPrivateMsg(Long.parseLong(entityTypeMessages.getFromQq()), "您在群" + groupName + "(" + entityTypeMessages.getFromGroup() + ")的暗骰结果为:\n1D" + maxRolls + "=" + entityStrManyRolls.getResult());
                    return;
                }

                if (isNumeric(entityStrManyRolls.getStrResult())) {
                    CQ.sendPrivateMsg(Long.parseLong(entityTypeMessages.getFromQq()), "您在群" + groupName + "(" + entityTypeMessages.getFromGroup() + ")的暗骰结果为:\n" + entityStrManyRolls.getStrFunction() + "=" + entityStrManyRolls.getResult());
                } else {
                    CQ.sendPrivateMsg(Long.parseLong(entityTypeMessages.getFromQq()), "您在群" + groupName + "(" + entityTypeMessages.getFromGroup() + ")的暗骰结果为:\n" + entityStrManyRolls.getStrFunction() + "=" + entityStrManyRolls.getStrResult() + "=" + entityStrManyRolls.getResult());
                }
            }
//            骰点结果和之前的表达式比较后得出成功等级等，私聊回复给命令发起人
        }
    }
}
