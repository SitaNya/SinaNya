package dice.sinanya.tools;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.Objects;

import static dice.sinanya.tools.MakeSkillName.makeSkillName;
import static dice.sinanya.tools.RoleInfo.*;

public class GetSkillValue {
    public static int getSkillValue(EntityTypeMessages entityTypeMessages, String tmpSkillName) {
        String skillName = makeSkillName(tmpSkillName);
        if (checkRoleInfoFromChooseExistByFromQQ(entityTypeMessages)) {
            return Objects.requireNonNull(getRoleInfoFromChooseByFromQQ(entityTypeMessages)).get(skillName);
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
}
