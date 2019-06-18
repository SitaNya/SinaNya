package dice.sinanya.tools.makedata;

import dice.sinanya.entity.EntityNickAndRandomAndSkill;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesSystem.NONE;
import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.getinfo.GetSkillValue.getSkillValue;
import static dice.sinanya.tools.getinfo.RoleChoose.checkRoleChooseExistByFromQQ;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByFromQQ;
import static dice.sinanya.tools.makedata.RandomInt.random;
import static java.lang.Math.ceil;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取昵称、随机值、技能值
 */
public class GetNickAndRandomAndSkill {
    private static Pattern p = Pattern.compile("[+*/\\-]");

    /**
     * 从传入的整体信息中获取昵称、随机值、技能值
     *
     * @param entityTypeMessages 消息封装类
     * @param msg                传入的消息字符串，可能包含骰点表达式，技能名，骰点原因（昵称）等
     * @return 包装后的EntityNickAndRandomAndSkill对象
     */
    public static EntityNickAndRandomAndSkill getNickAndRandomAndSkill(EntityTypeMessages entityTypeMessages, String msg) {
        int random = random(1, 100);

        StringBuilder skillName = new StringBuilder();
        int i = 0;
//        过滤所有非空格、非数字、非符号的内容，就只剩下技能名（以后可能改成正则进行匹配
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

//        查询当前命令执行人已选角色的角色名，否则使用昵称
        if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
            nick = getRoleChooseByFromQQ(entityTypeMessages);
        } else {
            nick = getNickName(entityTypeMessages);
        }

//        根据找到的技能名查询技能值
        if (!skillName.toString().equals(NONE)) {
            skill = getSkillValue(entityTypeMessages, skillName.toString());
        }

//        如果没找到技能名，则看看整段消息是不是数字技能值
        if (skill == 0) {
            if (isNumeric(msg.substring(i).trim())) {
                skill = Integer.parseInt(msg.substring(i).trim());
            }
        }

//        如果正段消息包含运算符，则将传入消息中的技能明天喜欢为技能值返回
        Matcher m = p.matcher(msg);
        if (m.find()) {
            msg = msg.replaceAll(skillName.toString(), String.valueOf(skill));
            skill = (int) ceil(Calculator.conversion(msg));
        }


        return new EntityNickAndRandomAndSkill(nick, random, skill);
    }

}
