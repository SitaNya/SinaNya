package dice.sinanya.tools;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.MakeSkillName.makeSkillName;
import static dice.sinanya.tools.RoleInfo.*;

public class GetSkillValue {

    private Pattern skillNameRegex = Pattern.compile("([\\u4e00-\\u9fa5]+)");

    private String skillName = "";

    private Long qq;

    private String msg;

    private int skill = 0;

    public GetSkillValue(EntityTypeMessages entityTypeMessages, String msg) {
        this.qq = Long.parseLong(entityTypeMessages.getFromQQ());
        this.msg = msg;
        replaceSkill(qq);
    }

    public GetSkillValue(Long qq, String msg) {
        this.qq = qq;
        replaceSkill(qq);
    }

    public String getResStr() {
        return msg;
    }

    private void replaceSkill(Long qq) {
        Matcher mSkillName = skillNameRegex.matcher(msg);
        while (mSkillName.find()) {
            this.msg = msg.replaceFirst(skillNameRegex.toString(), String.valueOf(getSkillValue(qq, mSkillName.group(1))));
            skill = getSkillValue(qq, mSkillName.group(1));
            skillName = mSkillName.group(1);
        }
    }

    public static int getSkillValue(EntityTypeMessages entityTypeMessages, String tmpSkillName) {
        String skillName = makeSkillName(tmpSkillName);
        if (checkRoleInfoFromChooseExistByFromQQ(entityTypeMessages)) {
            return Objects.requireNonNull(getRoleInfoFromChooseByFromQQ(entityTypeMessages)).getOrDefault(skillName, 0);
        } else {
            return 0;
        }
    }

    public static int getSkillValue(String qq, String tmpSkillName) {
        String skillName = makeSkillName(tmpSkillName);
        if (checkRoleInfoFromChooseExistByQQ(qq)) {
            return Objects.requireNonNull(getRoleInfoFromChooseByQQ(qq)).get(skillName);
        } else {
            return 0;
        }
    }

    public static int getSkillValue(long qq, String tmpSkillName) {
        String skillName = makeSkillName(tmpSkillName);
        if (checkRoleInfoFromChooseExistByQQ(qq)) {
            return Objects.requireNonNull(getRoleInfoFromChooseByQQ(qq)).get(skillName);
        } else {
            return 0;
        }
    }

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
