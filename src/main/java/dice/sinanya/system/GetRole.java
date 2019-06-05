package dice.sinanya.system;

import dice.sinanya.dice.roles.Roles;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.RoleInfoCache.ROLE_CHOOISE;

public class GetRole {
    public static boolean getRole(long qqId, EntityTypeMessages entityTypeMessages) {
        if (ROLE_CHOOISE.containsKey(qqId)) {
            return true;
        } else {
            new Roles(entityTypeMessages).searchRoleChoose(qqId);
            return ROLE_CHOOISE.containsKey(qqId);
        }
    }
}
