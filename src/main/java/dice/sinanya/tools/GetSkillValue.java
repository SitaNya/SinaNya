package dice.sinanya.tools;

import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.RoleInfoCache.ROLE_CHOOISE;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.MakeSkillName.makeSkillName;
import static dice.sinanya.tools.SearchRole.searchRole;

public class GetSkillValue {
    public static int getSkillValue(long idQq, EntityTypeMessages entityTypeMessages, String tmpSkillName) {
        String skillName = makeSkillName(tmpSkillName);
        if (searchRole(ROLE_CHOOISE.get(idQq), entityTypeMessages)) {
            return ROLE_INFO_CACHE.get(new EntityRoleTag(entityTypeMessages)).get(skillName);
        } else {
            return 0;
        }
    }

    public static int getSkillValue(EntityTypeMessages entityTypeMessages, String tmpSkillName) {
        long idQq = Long.parseLong(entityTypeMessages.getFromQQ());
        String skillName = makeSkillName(tmpSkillName);
        if (searchRole(ROLE_CHOOISE.get(idQq), entityTypeMessages)) {
            EntityRoleTag entityRoleTag=new EntityRoleTag(entityTypeMessages);
            return ROLE_INFO_CACHE.get(entityRoleTag).get(skillName);
        } else {
            return 0;
        }
    }
}
