package dice.sinanya.tools.getinfo;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.db.roles.SelectRoles;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.RoleInfoCache.ROLE_CHOOSE;

public class RoleChoose {
    private static SelectRoles selectRoles = new SelectRoles();
    private static InsertRoles insertRoles = new InsertRoles();

    public static void flushRoleChoose(){
        selectRoles.flushRoleChooseFromDatabase();
    }

    public static boolean checkRoleChooseExistByQQ(long qqId) {
        return ROLE_CHOOSE.containsKey(qqId);
    }

    public static boolean checkRoleChooseExistByQQ(String qq) {
        long qqId = Long.parseLong(qq);
        return ROLE_CHOOSE.containsKey(qqId);
    }

    public static boolean checkRoleChooseExistByFromQQ(EntityTypeMessages entityTypeMessages) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        return ROLE_CHOOSE.containsKey(qqId);
    }

    public static String getRoleChooseByQQ(String qqId) {
        long qq = Long.parseLong(qqId);
        if (checkRoleChooseExistByQQ(qq)) {
            return ROLE_CHOOSE.get(qq);
        } else {
            return "未找到当前角色";
        }
    }

    public static String getRoleChooseByQQ(long qq) {
        if (checkRoleChooseExistByQQ(qq)) {
            return ROLE_CHOOSE.get(qq);
        } else {
            return "未找到当前角色";
        }
    }

    public static String getRoleChooseByFromQQ(EntityTypeMessages entityTypeMessages) {
        if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
            return ROLE_CHOOSE.get(Long.parseLong(entityTypeMessages.getFromQq()));
        } else {
            return "未找到当前角色";
        }
    }

    public static void setRoleChooseByQQ(String qq, String role) {
        long qqId = Long.parseLong(qq);
        ROLE_CHOOSE.put(qqId, role);
        insertRoles.insertRoleChoose(qqId, role);
    }

    public static void setRoleChooseByQQ(long qqId, String role) {
        ROLE_CHOOSE.put(qqId, role);
        insertRoles.insertRoleChoose(qqId, role);
    }

    public static void setRoleChooseByFromQQ(EntityTypeMessages entityTypeMessages, String role) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        ROLE_CHOOSE.put(qqId, role);
        insertRoles.insertRoleChoose(qqId, role);
    }
}
