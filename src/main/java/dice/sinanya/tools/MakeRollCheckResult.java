package dice.sinanya.tools;

import dice.sinanya.entity.EntityRollAndCheck;
import dice.sinanya.entity.EntityTypeMessages;

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

}
