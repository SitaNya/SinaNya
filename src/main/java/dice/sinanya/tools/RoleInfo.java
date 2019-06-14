package dice.sinanya.tools;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.db.roles.SelectRoles;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.HashMap;

import static dice.sinanya.system.RoleInfoCache.ROLE_CHOOSE;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.RoleChoose.checkRoleChooseExistByQQ;

public class RoleInfo {
    static SelectRoles selectRoles = new SelectRoles();
    static InsertRoles insertRoles = new InsertRoles();

    public static boolean checkRoleInfoExistByQQ(long qqId, String role) {
        if (ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role))) {
            return true;
        } else {
            selectRoles.flushRoleInfoCacheByQQ(qqId);
            return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
        }
    }

    public static boolean checkRoleInfoExistByQQ(String qq, String role) {
        long qqId = Long.parseLong(qq);
        if (ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role))) {
            return true;
        } else {
            selectRoles.flushRoleInfoCacheByQQ(qqId);
            return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
        }
    }

    public static boolean checkRoleInfoExistForChooseByQQ(long qqId) {
        String role;
        if (checkRoleChooseExistByQQ(qqId)) {
            role = ROLE_CHOOSE.get(qqId);
        } else {
            return false;
        }
        if (ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role))) {
            return true;
        } else {
            selectRoles.flushRoleInfoCacheByQQ(qqId);
            return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
        }
    }


    public static boolean checkRoleInfoExistByFromQQ(EntityTypeMessages entityTypeMessages, String role) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        if (ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role))) {
            return true;
        } else {
            selectRoles.flushRoleInfoCacheByQQ(qqId);
            return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
        }
    }

    public static boolean checkRoleInfoFromChooseExistByFromQQ(EntityTypeMessages entityTypeMessages) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        String role;
        if (checkRoleChooseExistByQQ(qqId)) {
            role = ROLE_CHOOSE.get(qqId);
        } else {
            return false;
        }
        if (ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role))) {
            return true;
        } else {
            selectRoles.flushRoleInfoCacheByQQ(qqId);
            return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
        }
    }

    public static boolean checkRoleInfoFromChooseExistByQQ(String qq) {
        long qqId = Long.parseLong(qq);
        String role;
        if (checkRoleChooseExistByQQ(qqId)) {
            role = ROLE_CHOOSE.get(qqId);
        } else {
            return false;
        }
        if (ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role))) {
            return true;
        } else {
            selectRoles.flushRoleInfoCacheByQQ(qqId);
            return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
        }
    }

    public static boolean checkRoleInfoFromChooseExistByQQ(long qqId) {
        String role;
        if (checkRoleChooseExistByQQ(qqId)) {
            role = ROLE_CHOOSE.get(qqId);
        } else {
            return false;
        }
        if (ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role))) {
            return true;
        } else {
            selectRoles.flushRoleInfoCacheByQQ(qqId);
            return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
        }
    }

    public static HashMap<String, Integer> getRoleInfoByQQ(long qqId, String role) {
        if (checkRoleInfoExistByQQ(qqId, role)) {
            return ROLE_INFO_CACHE.get(new EntityRoleTag(qqId, role));
        } else {
            return null;
        }
    }

    public static HashMap<String, Integer> getRoleInfoByQQ(String qq, String role) {
        long qqId = Long.parseLong(qq);
        if (checkRoleInfoExistByQQ(qqId, role)) {
            return ROLE_INFO_CACHE.get(new EntityRoleTag(qqId, role));
        } else {
            return null;
        }
    }

    public static HashMap<String, Integer> getRoleInfoFromChooseByQQ(long qqId) {
        String role;
        if (checkRoleChooseExistByQQ(qqId)) {
            role = ROLE_CHOOSE.get(qqId);
        } else {
            return null;
        }
        if (checkRoleInfoExistByQQ(qqId, role)) {
            return ROLE_INFO_CACHE.get(new EntityRoleTag(qqId, role));
        } else {
            return null;
        }
    }

    public static HashMap<String, Integer> getRoleInfoFromChooseByQQ(String qq) {
        long qqId = Long.parseLong(qq);
        String role;
        if (checkRoleChooseExistByQQ(qqId)) {
            role = ROLE_CHOOSE.get(qqId);
        } else {
            return null;
        }
        if (checkRoleInfoExistByQQ(qqId, role)) {
            return ROLE_INFO_CACHE.get(new EntityRoleTag(qqId, role));
        } else {
            return null;
        }
    }

    public static HashMap<String, Integer> getRoleInfoByFromQQ(EntityTypeMessages entityTypeMessages, String role) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        if (checkRoleInfoExistByQQ(qqId, role)) {
            return ROLE_INFO_CACHE.get(new EntityRoleTag(qqId, role));
        } else {
            return null;
        }
    }

    public static HashMap<String, Integer> getRoleInfoFromChooseByFromQQ(EntityTypeMessages entityTypeMessages) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        String role;
        if (checkRoleChooseExistByQQ(qqId)) {
            String tmp = ROLE_CHOOSE.get(qqId);
            role = ROLE_CHOOSE.get(qqId);
        } else {
            return null;
        }
        if (checkRoleInfoExistByQQ(qqId, role)) {
            return ROLE_INFO_CACHE.get(new EntityRoleTag(qqId, role));
        } else {
            return null;
        }
    }

    public static void setRoleInfoByQQ(String qqId, String role, String prop) {
        long qq = Long.parseLong(qqId);
        insertRoles.insertRoleInfo(prop, role, qq);
        selectRoles.flushRoleInfoCacheByQQ(qq);
    }

    public static void setRoleInfoByQQ(long qq, String role, String prop) {
        insertRoles.insertRoleInfo(prop, role, qq);
        selectRoles.flushRoleInfoCacheByQQ(qq);
    }

    public static void setRoleInfoByFromQQ(EntityTypeMessages entityTypeMessages, String role, String prop) {
        long qq = Long.parseLong(entityTypeMessages.getFromQq());
        insertRoles.insertRoleInfo(prop, role, qq);
        selectRoles.flushRoleInfoCacheByQQ(qq);
    }

    public static void setRoleInfoFromChooseByQQ(String qqId, String prop) {
        long qq = Long.parseLong(qqId);
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo(prop, role, qq);
        selectRoles.flushRoleInfoCacheByQQ(qq);
    }

    public static void setRoleInfoFromChooseByQQ(long qq, String prop) {
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo(prop, role, qq);
        selectRoles.flushRoleInfoCacheByQQ(qq);
    }

    public static void setRoleInfoFromChooseByFromQQ(EntityTypeMessages entityTypeMessages, String prop) {
        long qq = Long.parseLong(entityTypeMessages.getFromQq());
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo(prop, role, qq);
        selectRoles.flushRoleInfoCacheByQQ(qq);
    }

    public static void setRoleInfoFromChooseByQQ(String qqId, HashMap<String, Integer> prop) {
        long qq = Long.parseLong(qqId);
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo(prop, role, qq);
        selectRoles.flushRoleInfoCacheByQQ(qq);
    }

    public static void setRoleInfoFromChooseByQQ(long qq, HashMap<String, Integer> prop) {
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo(prop, role, qq);
        selectRoles.flushRoleInfoCacheByQQ(qq);
    }

    public static void setRoleInfoFromChooseByFromQQ(EntityTypeMessages entityTypeMessages, HashMap<String, Integer> prop) {
        long qq = Long.parseLong(entityTypeMessages.getFromQq());
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo(prop, role, qq);
        selectRoles.flushRoleInfoCacheByQQ(qq);
    }

    public static void removeRoleByQQ(String qqId, String role) {
        long qq = Long.parseLong(qqId);
        insertRoles.deleteInfo(qq, role);
        ROLE_INFO_CACHE.remove(new EntityRoleTag(qq, role));
    }

    public static void removeRoleByQQ(long qq, String role) {
        insertRoles.deleteInfo(qq, role);
        ROLE_INFO_CACHE.remove(new EntityRoleTag(qq, role));
    }
}
