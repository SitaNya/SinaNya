package dice.sinanya.tools;

import dice.sinanya.entity.EntityRollAndCheck;
import dice.sinanya.entity.EntityStrManyRolls;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesSystem.NONE;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.GetNickName.getNickName;
import static dice.sinanya.tools.GetSkillValue.getSkillValue;
import static dice.sinanya.tools.RandomInt.random;
import static dice.sinanya.tools.RoleChoose.checkRoleChooseExistByFromQQ;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByFromQQ;
import static java.lang.Math.ceil;

public class MakeRollCheckResult {
    private static Pattern p = Pattern.compile("[+*/\\-]");

    public static EntityRollAndCheck makeResult(EntityTypeMessages entityTypeMessages, String msg) {
        int random = random(1, 100);

        StringBuilder skillName = new StringBuilder();
        int i = 0;
        while (i < msg.length() && !Character.isSpaceChar(msg.charAt(i)) &&
                !Character.isDigit(msg.charAt(i)) &&
                msg.charAt(i) != ':' &&
                msg.charAt(i) != '=' &&
                msg.charAt(i) != '+' &&
                msg.charAt(i) != '-' &&
                msg.charAt(i) != '*' &&
                msg.charAt(i) != 'x' &&
                msg.charAt(i) != '/') {
            skillName.append(msg.charAt(i));
            i++;
        }
        String nick;
        int skill = 0;

        if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
            nick = getRoleChooseByFromQQ(entityTypeMessages);
        } else {
            nick = getNickName(entityTypeMessages);
        }

        if (!skillName.toString().equals(NONE)) {
            skill = getSkillValue(entityTypeMessages, skillName.toString());
        }
        if (skill == 0) {
            if (isNumeric(msg.substring(i).trim())) {
                skill = Integer.parseInt(msg.substring(i).trim());
            }
        }


        Matcher m = p.matcher(msg);
        if (m.find()) {
            msg = msg.replaceAll(skillName.toString(), String.valueOf(skill));
            skill = (int) ceil(Calculator.conversion(msg));
        }


        return new EntityRollAndCheck(nick, random, skill);
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
