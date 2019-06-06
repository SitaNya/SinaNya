package dice.sinanya.dice.manager;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;

import java.util.*;

import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.GetSkillName.getSkillName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RoleChoose.*;
import static dice.sinanya.tools.RoleInfo.*;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author zhangxiaozhou
 */
public class Roles {
    private String role;
    private long qqId;
    private EntityTypeMessages entityTypeMessages;

    public Roles(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    public boolean set() throws PlayerSetException {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".st");
        String sepRoleAndPro = "-";
        String tagRoleNameNone = "";
        int lenRoleAndPro = 2;
        String properties;
        InsertRoles insertRoles = new InsertRoles();
        qqId = Long.parseLong(entityTypeMessages.getFromQQ());

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
            if (checkRoleChooseExistByQQ(qqId) && !getRoleChooseByQQ(qqId).equals(mapEntry.getKey().getRole())) {
                standbyRole.append(mapEntry.getKey().getRole()).append("\n");
            } else if (checkRoleChooseExistByQQ(qqId)) {
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
        String role = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".st move");
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
        ArrayList<String> propMain = new ArrayList<String>() {{
            add("hp");
            add("san");
            add("str");
            add("pow");
            add("con");
            add("app");
            add("edu");
            add("siz");
            add("intValue");
            add("luck");
            add("mp");
        }};
        StringBuilder stringBuilder = new StringBuilder();


        if (checkRoleInfoFromChooseExistByFromQQ(entityTypeMessages)) {
            stringBuilder.append("您的角色: ");
            stringBuilder.append(getRoleChooseByFromQQ(entityTypeMessages));
            stringBuilder.append("的主属性为:\n");
            ArrayList<String> propMainResult = showMainProp(propMain, true);
            Collections.sort(propMainResult);
            stringBuilder = formatResult(stringBuilder, propMainResult);
            stringBuilder.append("\n\n技能列表为:\n");
            ArrayList<String> propSkillResult = showMainProp(propMain, false);
            Collections.sort(propSkillResult);
            stringBuilder = formatResult(stringBuilder, propSkillResult);
        } else {
            stringBuilder.append("您似乎未设定角色");
        }

        sender(entityTypeMessages, stringBuilder.toString());
    }

    private ArrayList<String> showMainProp(ArrayList<String> propMain, boolean main) {

        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> mapEntry : Objects.requireNonNull(getRoleInfoFromChooseByFromQQ(entityTypeMessages)).entrySet()) {
            if (main) {
                if (propMain.contains(mapEntry.getKey())) {
                    result.add(getSkillName(mapEntry.getKey()) + ":" + mapEntry.getValue());
                }
            } else {
                if (!propMain.contains(mapEntry.getKey())) {
                    result.add(getSkillName(mapEntry.getKey()) + ":" + mapEntry.getValue());
                }
            }

        }
        return result;
    }

    private StringBuilder formatResult(StringBuilder stringBuilder, ArrayList<String> propList) {
        int rowNum = 0;
        int propertiesWeight = 13;
        for (String prop : propList) {
            if (propList.indexOf(prop) == 0) {
                stringBuilder.append(prop);
                int textLen = (prop).length();
                for (int i = 0; i < propertiesWeight - textLen; i++) {
                    stringBuilder.append(" ");
                }
                continue;
            }
            if (rowNum < 3) {
                stringBuilder.append(prop);
                int textLen = (prop).length();
                for (int i = 0; i < propertiesWeight - textLen; i++) {
                    stringBuilder.append(" ");
                }
                rowNum++;
            } else {
                stringBuilder.append("\n");
                stringBuilder.append(prop);
                int textLen = (prop).length();
                for (int i = 0; i < propertiesWeight - textLen; i++) {
                    stringBuilder.append(" ");
                }
                rowNum = 0;
            }
        }
        return stringBuilder;
    }
}
