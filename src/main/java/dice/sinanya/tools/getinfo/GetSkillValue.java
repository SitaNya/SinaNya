package dice.sinanya.tools.getinfo;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.tools.checkdata.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.getinfo.MakeSkillName.makeSkillName;
import static dice.sinanya.tools.getinfo.RoleInfo.*;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取技能值
 */
public class GetSkillValue {

    /**
     * @param skillNameRegex 过滤了所有中文
     */
    private Pattern skillNameRegex = Pattern.compile("([\\u4e00-\\u9fa5]+)");

    private String skillName = "";

    private Long qq;

    private String msg;

    private int skill = 0;

    /**
     * 从消息封装类中取出骰点人QQ号
     *
     * @param entityTypeMessages 消息封装类
     * @param msg                所有输入的信息，可能包含骰点表达式
     */
    public GetSkillValue(EntityTypeMessages entityTypeMessages, String msg) {
        this.qq = Long.parseLong(entityTypeMessages.getFromQq());
        this.msg = msg;
        replaceSkill(qq);
    }

    public GetSkillValue(Long qq) {
        this.qq = qq;
        replaceSkill(qq);
    }

    /**
     * 尝试用一个字符串匹配是否是技能，是的话取出技能值，不是的话返回0
     *
     * @param entityTypeMessages 消息封装类
     * @param tmpSkillName       可能的技能值字符串
     * @return 技能值
     */
    public static int getSkillValue(EntityTypeMessages entityTypeMessages, String tmpSkillName) {
        String skillName = makeSkillName(tmpSkillName);
        if (checkRoleInfoFromChooseExistByFromQQ(entityTypeMessages)) {
            return Objects.requireNonNull(getRoleInfoFromChooseByFromQQ(entityTypeMessages)).getOrDefault(skillName, 0);
        } else {
            return 0;
        }
    }

    /**
     * 尝试用一个字符串匹配是否是技能，是的话取出技能值，不是的话返回0
     * 这个方法用于取指定某个人的技能值
     *
     * @param qq           某人的QQ
     * @param tmpSkillName 可能的技能值字符串
     * @return 技能值
     */
    public static int getSkillValue(String qq, String tmpSkillName) {
        String skillName = makeSkillName(tmpSkillName);
        if (checkRoleInfoFromChooseExistByQQ(qq)) {
            return Objects.requireNonNull(getRoleInfoFromChooseByQQ(qq)).get(skillName);
        } else {
            return 0;
        }
    }

    /**
     * 尝试用一个字符串匹配是否是技能，是的话取出技能值，不是的话返回0
     * 这个方法用于取指定某个人的技能值
     *
     * @param qq           某人的QQ
     * @param tmpSkillName 可能的技能值字符串
     * @return 技能值
     */
    public static int getSkillValue(long qq, String tmpSkillName) {
        String skillName = makeSkillName(tmpSkillName);
        if (checkRoleInfoFromChooseExistByQQ(qq)) {
            return Objects.requireNonNull(getRoleInfoFromChooseByQQ(qq)).get(skillName);
        } else {
            return 0;
        }
    }

    public String getResStr() {
        return msg;
    }

    /**
     * 将技能名替换为技能值
     * <p>
     * 替换原有的msg字符串
     * 将技能名取出
     * 将技能值取出
     *
     * @param qq QQ号
     */
    private void replaceSkill(Long qq) {
        Matcher mSkillName = skillNameRegex.matcher(msg);
        while (mSkillName.find()) {
            this.msg = msg.replaceFirst(skillNameRegex.toString(), String.valueOf(getSkillValue(qq, mSkillName.group(1))));
            skill = getSkillValue(qq, mSkillName.group(1));
            skillName = mSkillName.group(1);
        }
    }

    /**
     * 如果输入值是数字，那么这个数字就是技能值
     *
     * @return 技能值
     */
    public int getSkill() {
        if (skill == 0 && isNumeric(msg)) {
            return Integer.parseInt(msg);
        }
        return skill;
    }

    public String getSkillName() {
        return skillName;
    }
}
