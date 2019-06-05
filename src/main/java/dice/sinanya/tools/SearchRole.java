package dice.sinanya.tools;

import dice.sinanya.dice.roles.Roles;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;
import sun.rmi.runtime.Log;

import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;

public class SearchRole {
    public static boolean searchRole(String role, EntityTypeMessages entityTypeMessages) {
        if (ROLE_INFO_CACHE.containsKey(new EntityRoleTag(Long.parseLong(entityTypeMessages.getFromQQ()), role))) {
            return true;
        } else {
            new Roles(entityTypeMessages).get();
            return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(Long.parseLong(entityTypeMessages.getFromQQ()), role));
        }
    }
}
