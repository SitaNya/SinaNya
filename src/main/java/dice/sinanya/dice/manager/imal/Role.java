package dice.sinanya.dice.manager.imal;

import dice.sinanya.db.roles.SelectRoles;
import dice.sinanya.entity.EntityTypeMessages;

/**
 * 声明使用角色
 *
 * @author zhangxiaozhou
 */
public interface Role {

    /**
     * 刷新当前角色和角色信息
     *
     * @param entityTypeMessages 传入信息类型用以确定当前QQ号
     */
    default void useRole(EntityTypeMessages entityTypeMessages) {
        new SelectRoles().flushRoleChooseByFromQQ(entityTypeMessages);
        new SelectRoles().flushRoleInfoCacheByFromQQ(entityTypeMessages);
    }
}
