package dice.sinanya.dice.manager;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.dice.manager.imal.PropList;
import dice.sinanya.dice.manager.imal.Role;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;

import java.util.HashMap;
import java.util.Map;

import static dice.sinanya.system.MessagesTag.tagStMove;
import static dice.sinanya.system.MessagesTag.tagStSet;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RoleChoose.*;
import static dice.sinanya.tools.RoleInfo.*;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author zhangxiaozhou
 */
public class Roles extends PropList implements Role {
    private EntityTypeMessages entityTypeMessages;

    public Roles(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    public boolean set() throws PlayerSetException {
        useRole(entityTypeMessages);

        String tag = tagStSet;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String sepRoleAndPro = "-";
        String tagRoleNameNone = "";
        int lenRoleAndPro = 2;
        String properties;
        InsertRoles insertRoles = new InsertRoles();
        long qqId = Long.parseLong(entityTypeMessages.getFromQQ());

        String role;
        if (msg.contains(sepRoleAndPro) && msg.split(sepRoleAndPro).length == lenRoleAndPro) {
            role = msg.split(sepRoleAndPro)[0];
            properties = msg.split(sepRoleAndPro)[1];
            insertRoles.insertRoleInfo(properties, role, qqId);
        } else if (!msg.equals(tagRoleNameNone)) {
            role = msg;
            if (checkRoleInfoExistByQQ(qqId, role)) {
                setRoleChooseByQQ(qqId, role);
            } else {
                throw new PlayerSetException(entityTypeMessages);
            }
        }
        return true;
    }

    public void list() {
        useRole(entityTypeMessages);

        long qqId = Long.parseLong(entityTypeMessages.getFromQQ());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("您当前使用角色: \n");
        if (checkRoleChooseExistByQQ(qqId)) {
            stringBuilder.append(getRoleChooseByQQ(qqId)).append("\n");
        } else {
            stringBuilder.append("无").append("\n");
        }
        stringBuilder.append("当前备选角色:\n");
        StringBuilder standbyRole = new StringBuilder();

        for (Map.Entry<EntityRoleTag, HashMap<String, Integer>> mapEntry : ROLE_INFO_CACHE.entrySet()) {
            if (checkRoleChooseExistByQQ(qqId) && !getRoleChooseByQQ(qqId).equals(mapEntry.getKey().getRole()) && mapEntry.getKey().getQq() == qqId) {
                standbyRole.append(mapEntry.getKey().getRole()).append("\n");
            }
        }
        if (standbyRole.length() == 0) {
            standbyRole.append("无").append("\n");
        }
        stringBuilder.append(standbyRole);
        String result = stringBuilder.toString();
        sender(entityTypeMessages, result.substring(0, result.length() - 1));
    }

    public void move() {
        useRole(entityTypeMessages);

        String tag = tagStMove;
        String role = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        long qqId = Long.parseLong(entityTypeMessages.getFromQQ());
        if (checkRoleInfoExistByFromQQ(entityTypeMessages, role)) {
            if (getRoleChooseByQQ(qqId).equals(role)) {
                sender(entityTypeMessages, "您不能删除当前选定角色");
            } else {
                removeRoleByQQ(qqId, role);
                sender(entityTypeMessages, "已为您删除角色: " + role);
            }
        }
    }

    public void show() {
        useRole(entityTypeMessages);

        String qq = entityTypeMessages.getFromQQ();
        StringBuilder stringBuilder = showProp(entityTypeMessages, qq);
        sender(entityTypeMessages, stringBuilder.toString());
    }
}
