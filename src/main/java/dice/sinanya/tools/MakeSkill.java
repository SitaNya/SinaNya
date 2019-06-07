package dice.sinanya.tools;

import dice.sinanya.entity.EntityStrManyRolls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.tools.GetSkillValue.getSkillValue;
import static java.lang.Math.ceil;

public class MakeSkill {

    private static Pattern pContainD = Pattern.compile("(\\d+[dD]\\d+)");
    private static String tagNone = "";

    public static EntityStrManyRolls makeSkill(String skill, String qqId) {
        long qq = Long.parseLong(qqId);
        skill = skill.trim();
        int i = 0;
        StringBuilder strSkillName = new StringBuilder();
        while (!Character.isDigit(skill.charAt(i))
                && !Character.isSpaceChar(skill.charAt(i))
                && skill.charAt(i) != '+'
                && skill.charAt(i) != '-'
                && skill.charAt(i) != '*'
                && skill.charAt(i) != '/'
                && skill.charAt(i) != 'X'
                && skill.charAt(i) != 'x'
                && skill.charAt(i) != 'd'
                && skill.charAt(i) != 'D') {
            strSkillName.append(skill.charAt(i));
        }
        if (!strSkillName.toString().equals(tagNone)) {
            skill = skill.replaceFirst(strSkillName.toString(), String.valueOf(getSkillValue(qq, strSkillName.toString())));
        }

        Matcher findFunc = pContainD.matcher(skill);
        while (findFunc.find()) {
            MakeManyRollsStr makeManyRollsStr = new MakeManyRollsStr(findFunc.group(1));
            skill = skill.replaceFirst("(\\d+[dD]\\d+)", makeManyRollsStr.getStr());
        }

        return new EntityStrManyRolls(skill, (int) ceil(Calculator.conversion(skill)));
    }

    /**
     * @param skill 2d6+6、力量+20
     * @param qq    技能发起者
     * @return (4 + 5)+6,15\50+20,70
     */
    public static EntityStrManyRolls makeSkill(String skill, long qq) {
        skill = skill.trim();
        int i = 0;
        StringBuilder strSkillName = new StringBuilder();
        while (i < skill.length() && !Character.isDigit(skill.charAt(i))
                && !Character.isSpaceChar(skill.charAt(i))
                && skill.charAt(i) != '+'
                && skill.charAt(i) != '-'
                && skill.charAt(i) != '*'
                && skill.charAt(i) != '/'
                && skill.charAt(i) != 'X'
                && skill.charAt(i) != 'x'
                && skill.charAt(i) != 'd'
                && skill.charAt(i) != 'D') {
            strSkillName.append(skill.charAt(i));
            i++;
        }
        if (!strSkillName.toString().equals(tagNone)) {
            skill = skill.replaceFirst(strSkillName.toString(), String.valueOf(getSkillValue(qq, strSkillName.toString())));
        }

        Matcher findFunc = pContainD.matcher(skill);
        while (findFunc.find()) {
            MakeManyRollsStr makeManyRollsStr = new MakeManyRollsStr(findFunc.group(1));
            skill = skill.replaceFirst("(\\d+[dD]\\d+)", makeManyRollsStr.getStr());
        }

        return new EntityStrManyRolls(skill, (int) ceil(Calculator.conversion(skill)));
    }

    /**
     * @param skill 2d6+6
     * @return (4 + 5)+6,15\50+20,70
     */
    public static EntityStrManyRolls makeSkill(String skill) {
        skill = skill.trim();

        Matcher findFunc = pContainD.matcher(skill);
        while (findFunc.find()) {
            MakeManyRollsStr makeManyRollsStr = new MakeManyRollsStr(findFunc.group(1));
            skill = skill.replaceFirst("(\\d+[dD]\\d+)", makeManyRollsStr.getStr());
        }

        return new EntityStrManyRolls(skill, (int) ceil(Calculator.conversion(skill)));
    }
}
