package dice.sinanya.system;

import dice.sinanya.dice.manager.Roles;
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

    public static boolean getRole(EntityTypeMessages entityTypeMessages) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQQ());
        if (ROLE_CHOOISE.containsKey(qqId)) {
            return true;
        } else {
            new Roles(entityTypeMessages).searchRoleChoose(qqId);
            return ROLE_CHOOISE.containsKey(qqId);
        }
    }

    public static String getRoleInfo(EntityTypeMessages entityTypeMessages) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQQ());
        if (getRole(entityTypeMessages)) {
            return ROLE_CHOOISE.get(qqId);
        }else {
            return "未找到角色";
        }
    }

    public static String getRoleInfo(EntityTypeMessages entityTypeMessages,long qqId) {
        if (getRole(entityTypeMessages)) {
            return ROLE_CHOOISE.get(qqId);
        }else {
            return "未找到角色";
        }
    }
}
