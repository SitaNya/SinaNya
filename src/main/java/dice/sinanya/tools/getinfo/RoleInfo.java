package dice.sinanya.tools.getinfo;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.db.roles.SelectRoles;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.HashMap;
import java.util.Map;

import static dice.sinanya.system.RoleInfoCache.ROLE_CHOOSE;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.getinfo.RoleChoose.checkRoleChooseExistByQQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 角色信息数据库交互类
 */
public class RoleInfo {
    private static SelectRoles selectRoles = new SelectRoles();
    private static InsertRoles insertRoles = new InsertRoles();

    private RoleInfo() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 从数据库读取角色具体信息数据刷写到静态变量，这个方法只在启动时调用一次
     */
    public static void flushRoleInfoCache() {
        selectRoles.flushRoleInfoCacheFromDatabase();
    }

    /**
     * 检查某个QQ号的某个角色是否已经录入角色信息
     *
     * @param qqId QQ号
     * @param role 角色名
     * @return 是否存在
     */
    public static boolean checkRoleInfoExistByQQ(long qqId, String role) {
        return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
    }

    /**
     * 检查某个QQ号的某个角色是否已经录入角色信息
     *
     * @param qq   QQ号
     * @param role 角色名
     * @return 是否存在
     */
    public static boolean checkRoleInfoExistByQQ(String qq, String role) {
        long qqId = Long.parseLong(qq);
        return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
    }


    /**
     * 检查发起命令者的某个角色是否已经录入角色信息
     *
     * @param entityTypeMessages 消息封装类
     * @param role               角色名
     * @return 是否存在
     */
    public static boolean checkRoleInfoExistByFromQQ(EntityTypeMessages entityTypeMessages, String role) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
    }

    /**
     * 检查发起命令者的当前已选角色是否已经录入角色信息
     *
     * @param entityTypeMessages 消息封装类
     * @return 是否存在
     */
    static boolean checkRoleInfoFromChooseExistByFromQQ(EntityTypeMessages entityTypeMessages) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        String role;
        if (checkRoleChooseExistByQQ(qqId)) {
            role = ROLE_CHOOSE.get(qqId);
        } else {
            return false;
        }
        return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
    }

    /**
     * 检查某个QQ号的当前已选角色是否已经录入角色信息
     *
     * @param qq QQ号
     * @return 是否存在
     */
    public static boolean checkRoleInfoFromChooseExistByQQ(String qq) {
        long qqId = Long.parseLong(qq);
        String role;
        if (checkRoleChooseExistByQQ(qqId)) {
            role = ROLE_CHOOSE.get(qqId);
        } else {
            return false;
        }
        return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
    }

    /**
     * 检查某个QQ号的当前已选角色是否已经录入角色信息
     *
     * @param qqId QQ号
     * @return 是否存在
     */
    public static boolean checkRoleInfoFromChooseExistByQQ(long qqId) {
        String role;
        if (checkRoleChooseExistByQQ(qqId)) {
            role = ROLE_CHOOSE.get(qqId);
        } else {
            return false;
        }
        return ROLE_INFO_CACHE.containsKey(new EntityRoleTag(qqId, role));
    }

    /**
     * 获取某个QQ号的某个角色的全部属性信息
     *
     * @param qqId QQ号
     * @param role 角色名
     * @return 属性值列表
     */
    public static Map<String, Integer> getRoleInfoByQQ(long qqId, String role) {
        if (checkRoleInfoExistByQQ(qqId, role)) {
            return ROLE_INFO_CACHE.get(new EntityRoleTag(qqId, role));
        } else {
            return null;
        }
    }

    /**
     * 获取某个QQ号的某个角色的全部属性信息
     *
     * @param qq   QQ号
     * @param role 角色名
     * @return 属性值列表
     */
    public static Map<String, Integer> getRoleInfoByQQ(String qq, String role) {
        long qqId = Long.parseLong(qq);
        if (checkRoleInfoExistByQQ(qqId, role)) {
            return ROLE_INFO_CACHE.get(new EntityRoleTag(qqId, role));
        } else {
            return null;
        }
    }

    /**
     * 获取某个QQ号的当前已选角色的全部属性信息
     *
     * @param qqId QQ号
     * @return 属性值列表
     */
    public static Map<String, Integer> getRoleInfoFromChooseByQQ(long qqId) {
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

    /**
     * 获取发起命令者的当前已选角色的全部属性信息
     *
     * @param qq QQ号
     * @return 属性值列表
     */
    public static Map<String, Integer> getRoleInfoFromChooseByQQ(String qq) {
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

    /**
     * 获取发起命令者的某个角色的全部属性列表
     *
     * @param entityTypeMessages 消息封装类
     * @param role               角色名
     * @return 属性值列表
     */
    public static Map<String, Integer> getRoleInfoByFromQQ(EntityTypeMessages entityTypeMessages, String role) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        if (checkRoleInfoExistByQQ(qqId, role)) {
            return ROLE_INFO_CACHE.get(new EntityRoleTag(qqId, role));
        } else {
            return null;
        }
    }

    /**
     * 获取发起命令者的当前已选角色角色的全部属性列表
     *
     * @param entityTypeMessages 消息封装类
     * @return 属性值列表
     */
    public static Map<String, Integer> getRoleInfoFromChooseByFromQQ(EntityTypeMessages entityTypeMessages) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
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

    /**
     * 设置某个QQ的某个角色的全部属性，包含更新功能
     *
     * @param qqId QQ号
     * @param role 角色名
     * @param prop 全部属性列表（字符串格式）
     */
    public static void setRoleInfoByQQ(String qqId, String role, String prop) {
        long qq = Long.parseLong(qqId);
        insertRoles.insertRoleInfo(prop, role, qq);
    }

    /**
     * 设置某个QQ的某个角色的全部属性，包含更新功能
     *
     * @param qq   QQ号
     * @param role 角色名
     * @param prop 全部属性列表（字符串格式）
     */
    public static void setRoleInfoByQQ(long qq, String role, String prop) {
        insertRoles.insertRoleInfo(prop, role, qq);
    }

    /**
     * 设置发起命令者的某个角色的全部属性，包含更新功能
     *
     * @param role 角色名
     * @param prop 全部属性列表（字符串格式）
     */
    public static void setRoleInfoByFromQQ(EntityTypeMessages entityTypeMessages, String role, String prop) {
        long qq = Long.parseLong(entityTypeMessages.getFromQq());
        insertRoles.insertRoleInfo(prop, role, qq);
    }

    /**
     * 设置某个QQ的当前已选角色的全部属性，包含更新功能
     *
     * @param qqId QQ号
     * @param prop 全部属性列表（字符串格式）
     */
    public static void setRoleInfoFromChooseByQQ(String qqId, String prop) {
        long qq = Long.parseLong(qqId);
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo(prop, role, qq);
    }

    /**
     * 设置某个QQ的当前已选角色的全部属性，包含更新功能
     *
     * @param qq   QQ号
     * @param prop 全部属性列表
     */
    public static void setRoleInfoFromChooseByQQ(long qq, String prop) {
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo(prop, role, qq);
    }

    /**
     * 设置发起命令者的当前已选角色的全部属性，包含更新功能
     *
     * @param entityTypeMessages 消息封装类
     * @param prop               全部属性列表（字符串格式）
     */
    public static void setRoleInfoFromChooseByFromQQ(EntityTypeMessages entityTypeMessages, String prop) {
        long qq = Long.parseLong(entityTypeMessages.getFromQq());
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo(prop, role, qq);
    }

    /**
     * 设置某个QQ的当前已选角色的全部属性，包含更新功能
     *
     * @param qqId QQ号
     * @param prop 全部属性列表（HashMap格式）
     */
    public static void setRoleInfoFromChooseByQQ(String qqId, Map<String, Integer> prop) {
        long qq = Long.parseLong(qqId);
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo((HashMap<String, Integer>) prop, role, qq);
    }

    /**
     * 设置某个QQ的当前已选角色的全部属性，包含更新功能
     *
     * @param qq   QQ号
     * @param prop 全部属性列表（HashMap格式）
     */
    public static void setRoleInfoFromChooseByQQ(long qq, Map<String, Integer> prop) {
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo((HashMap<String, Integer>) prop, role, qq);
    }

    /**
     * 设置发起命令者的当前已选角色的全部属性，包含更新功能
     *
     * @param entityTypeMessages 消息封装类
     * @param prop               全部属性列表（HashMap格式）
     */
    public static void setRoleInfoFromChooseByFromQQ(EntityTypeMessages entityTypeMessages, Map<String, Integer> prop) {
        long qq = Long.parseLong(entityTypeMessages.getFromQq());
        String role;
        if (checkRoleChooseExistByQQ(qq)) {
            role = ROLE_CHOOSE.get(qq);
        } else {
            return;
        }
        insertRoles.insertRoleInfo((HashMap<String, Integer>) prop, role, qq);
    }

    /**
     * 删除某个QQ号的某个角色
     *
     * @param qqId QQ号
     * @param role 角色名
     */
    public static void removeRoleByQQ(String qqId, String role) {
        long qq = Long.parseLong(qqId);
        insertRoles.deleteInfo(qq, role);
        ROLE_INFO_CACHE.remove(new EntityRoleTag(qq, role));
    }

    /**
     * 删除某个QQ号的某个角色
     *
     * @param qq   QQ号
     * @param role 角色名
     */
    public static void removeRoleByQQ(long qq, String role) {
        insertRoles.deleteInfo(qq, role);
        ROLE_INFO_CACHE.remove(new EntityRoleTag(qq, role));
    }
}
