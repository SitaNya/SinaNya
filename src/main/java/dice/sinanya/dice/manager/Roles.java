package dice.sinanya.dice.manager;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.dice.manager.imal.Role;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;

import java.util.HashMap;
import java.util.Map;

import static dice.sinanya.system.MessagesSystem.NONE;
import static dice.sinanya.system.MessagesTag.TAG_ST_RM;
import static dice.sinanya.system.MessagesTag.TAG_ST_SET;
import static dice.sinanya.system.RoleInfoCache.ROLE_CHOOSE;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.getinfo.GetMessagesSystem.MESSAGES_SYSTEM;
import static dice.sinanya.tools.getinfo.GetNickName.getNickName;
import static dice.sinanya.tools.getinfo.RoleChoose.*;
import static dice.sinanya.tools.getinfo.RoleInfo.*;
import static dice.sinanya.tools.makedata.MakeMessages.deleteTag;
import static dice.sinanya.tools.makedata.Sender.sender;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 人物卡角色
 */
public class Roles implements Role {
    private EntityTypeMessages entityTypeMessages;

    public Roles(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    /**
     * 人物卡变更，“.st角色”视为切换角色
     * “.st角色-属性50”视为修改或添加属性，这取决于是否可以找到这张人物卡，找到的话只修改提及的值，未找到的话其余所有属性置为初始属性
     * 初始属性情况可以参见dice.sinanya.system.MakeRolesInfo
     *
     * @return set是否成功
     * @throws PlayerSetException 如果字符串无法解析，会报错并在原渠道回复，回复语可以在配置文件中修改
     */
    @SuppressWarnings("AlibabaMethodTooLong")
    public boolean set() throws PlayerSetException {
        String tag = TAG_ST_SET;
        String msg = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));
        String sepRoleAndPro = "-";
        String tagRoleNameNone = "";
        String defaultRole = "自定义";
        int lenRoleAndPro = 2;
        String properties;
        InsertRoles insertRoles = new InsertRoles();
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());

        String role;
        if (msg.contains(sepRoleAndPro) && msg.split(sepRoleAndPro).length == lenRoleAndPro) {
            role = msg.split(sepRoleAndPro)[0];
            if (role != null && !role.equals(NONE)) {
                properties = msg.split(sepRoleAndPro)[1];
                insertRoles.insertRoleInfo(properties, role, qqId);
                sender(entityTypeMessages, MESSAGES_SYSTEM.get("setPropSuccess"));
                return true;
            } else {
                return false;
            }
        } else if (!msg.equals(tagRoleNameNone)) {
            role = msg;
            if (checkRoleInfoExistByQQ(qqId, role)) {
                setRoleChooseByQQ(qqId, role);
            } else if (defaultRole.equals(role)) {
                ROLE_CHOOSE.remove(qqId);
                sender(entityTypeMessages, "已为" + makeNickToSender(getNickName(entityTypeMessages)) + "切换到自定义档位\n此状态下无法使用.team\\.en功能，但所有技能使用不会受到限制。骰点时会默认取用群昵称");
            } else {
                throw new PlayerSetException(entityTypeMessages);
            }
            sender(entityTypeMessages, "已为" + makeNickToSender(getNickName(entityTypeMessages)) + "切换到人物卡: " + role);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回当前查询QQ的角色列表
     */
    public void list() {
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("您当前使用角色: \n");
        if (checkRoleChooseExistByQQ(qqId)) {
            stringBuilder.append(getRoleChooseByQQ(qqId)).append("\n");
        } else {
            stringBuilder.append("自定义").append("\n");
        }
        stringBuilder.append("当前备选角色:\n");
        StringBuilder standbyRole = new StringBuilder();

        for (Map.Entry<EntityRoleTag, HashMap<String, Integer>> mapEntry : ROLE_INFO_CACHE.entrySet()) {
            if (checkRoleChooseExistByQQ(qqId) && !getRoleChooseByQQ(qqId).equals(mapEntry.getKey().getRole()) && mapEntry.getKey().getQq() == qqId) {
                standbyRole.append(mapEntry.getKey().getRole()).append("\n");
            } else if (stringBuilder.toString().contains("自定义") && mapEntry.getKey().getQq() == qqId) {
                standbyRole.append(mapEntry.getKey().getRole()).append("\n");
            }
        }
        if (!stringBuilder.toString().contains("自定义")) {
            standbyRole.append("自定义").append("\n");
        }else if (standbyRole.length() == 0){
            standbyRole.append("无").append("\n");
        }
        stringBuilder.append(standbyRole);
        String result = stringBuilder.toString();
        sender(entityTypeMessages, result.substring(0, result.length() - 1));
    }

    /**
     * 移除某个角色
     */
    public void move() {
        String tag = TAG_ST_RM;
        String role = deleteTag(entityTypeMessages.getMsg(), tag.substring(0, tag.length() - 2));
        long qqId = Long.parseLong(entityTypeMessages.getFromQq());
        if (checkRoleInfoExistByFromQQ(entityTypeMessages, role)) {
            if (getRoleChooseByQQ(qqId).equals(role)) {
                sender(entityTypeMessages, "您不能删除当前选定角色");
            } else {
                removeRoleByQQ(qqId, role);
                sender(entityTypeMessages, "已为您删除角色: " + role);
            }
        }
    }

    /**
     * 显示角色的技能信息
     */
    public void show() {
        String qq = entityTypeMessages.getFromQq();
        StringBuilder stringBuilder = showProp(entityTypeMessages, qq);
        sender(entityTypeMessages, stringBuilder.toString());
    }
}
