package dice.sinanya.tools.getinfo;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.db.roles.SelectRoles;
import dice.sinanya.entity.EntityTypeMessages;

import static dice.sinanya.system.RoleInfoCache.ROLE_CHOOSE;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 当前选择角色数据库交互类
 */
public class RoleChoose {
    private static SelectRoles selectRoles = new SelectRoles();
    private static InsertRoles insertRoles = new InsertRoles();

    /**
     * 从数据库读取当前选择角色数据刷写到静态变量，这个方法只在启动时调用一次
     */
    public static void flushRoleChoose() {
        selectRoles.flushRoleChooseFromDatabase();
    }

    /**
     * 当前指定QQ号是否已选择角色
     *
     * @param qqId QQ号
     * @return 是否已选择角色
     */
    public static boolean checkRoleChooseExistByQQ(long qqId) {
        return ROLE_CHOOSE.containsKey(qqId);
    }

    /**
     * 当前指定QQ号是否已选择角色
     *
     * @param qq QQ号
     * @return 是否已选择角色
     */
    public static boolean checkRoleChooseExistByQQ(String qq) {
        long qqId = Long.parseLong(qq);
        return ROLE_CHOOSE.containsKey(qqId);
    }

    /**
     * 当前发起命令者是否已选择角色
     *
     * @param entityTypeMessages 消息封装类
     * @return 是否已选择角色
     */
    public static boolean checkRoleChooseExistByFromQQ(EntityTypeMessages entityTypeMessages) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        return ROLE_CHOOSE.containsKey(qqId);
    }

    /**
     * 获取指定QQ号的当前角色名
     *
     * @param qqId QQ号
     * @return 角色名
     */
    public static String getRoleChooseByQQ(String qqId) {
        long qq = Long.parseLong(qqId);
        if (checkRoleChooseExistByQQ(qq)) {
            return  ROLE_CHOOSE.get(qq) ;
        } else {
            return "未找到当前角色";
        }
    }

    /**
     * 获取指定QQ号的当前角色名
     *
     * @param qq QQ号
     * @return 角色名
     */
    public static String getRoleChooseByQQ(long qq) {
        if (checkRoleChooseExistByQQ(qq)) {
            return  ROLE_CHOOSE.get(qq) ;
        } else {
            return "未找到当前角色";
        }
    }

    /**
     * 获取发起命令者的当前角色名
     *
     * @param entityTypeMessages 消息封装类
     * @return 角色名
     */
    public static String getRoleChooseByFromQQ(EntityTypeMessages entityTypeMessages) {
        if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
            return ROLE_CHOOSE.get(Long.parseLong(entityTypeMessages.getFromQq()));
        } else {
            return "未找到当前角色";
        }
    }

    /**
     * 设置某个QQ号的当前角色为指定角色
     *
     * @param qq   QQ号
     * @param role 角色名
     */
    public static void setRoleChooseByQQ(String qq, String role) {
        long qqId = Long.parseLong(qq);
        ROLE_CHOOSE.put(qqId, role);
        insertRoles.insertRoleChoose(qqId, role);
    }

    /**
     * 设置某个QQ号的当前角色为指定角色
     *
     * @param qqId QQ号
     * @param role 角色名
     */
    public static void setRoleChooseByQQ(long qqId, String role) {
        ROLE_CHOOSE.put(qqId, role);
        insertRoles.insertRoleChoose(qqId, role);
    }

    /**
     * 设置发起命令者的当前角色为指定角色
     *
     * @param entityTypeMessages 消息封装类
     * @param role               角色名
     */
    public static void setRoleChooseByFromQQ(EntityTypeMessages entityTypeMessages, String role) {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        ROLE_CHOOSE.put(qqId, role);
        insertRoles.insertRoleChoose(qqId, role);
    }
}
