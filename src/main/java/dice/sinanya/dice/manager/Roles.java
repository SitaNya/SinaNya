package dice.sinanya.dice.manager;

import dice.sinanya.db.roles.InsertRoles;
import dice.sinanya.db.roles.SelectRoles;
import dice.sinanya.dice.manager.imal.PropList;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;

import java.util.*;

import static dice.sinanya.system.MessagesTag.tagStMove;
import static dice.sinanya.system.MessagesTag.tagStSet;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.GetSkillName.getSkillName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.RoleChoose.*;
import static dice.sinanya.tools.RoleInfo.*;
import static dice.sinanya.tools.Sender.sender;

/**
 * @author zhangxiaozhou
 */
public class Roles extends PropList {
    private EntityTypeMessages entityTypeMessages;

    public Roles(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    public boolean set() throws PlayerSetException {
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

        new SelectRoles().flushRoleChooseByFromQQ(entityTypeMessages);
        new SelectRoles().flushRoleInfoCacheByFromQQ(entityTypeMessages);
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
        String qq = entityTypeMessages.getFromQQ();
        StringBuilder stringBuilder = showProp(qq);
        sender(entityTypeMessages, stringBuilder.toString());
    }

    private ArrayList<String> formatProp(ArrayList<String> propMain) {

        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> mapEntry : Objects.requireNonNull(getRoleInfoFromChooseByFromQQ(entityTypeMessages)).entrySet()) {
            if (propMain.contains(mapEntry.getKey()) && mapEntry.getValue() != 0) {
                result.add(getSkillName(mapEntry.getKey()) + ":" + mapEntry.getValue());
            }

        }
        return result;
    }

    private ArrayList<String> formatProp(ArrayList<String> propMain, boolean up) {
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> mapEntry : Objects.requireNonNull(getRoleInfoFromChooseByFromQQ(entityTypeMessages)).entrySet()) {
            if (!propMain.contains(mapEntry.getKey()) && mapEntry.getValue() != 0) {
                if (up && mapEntry.getValue() >= 50) {
                    result.add(getSkillName(mapEntry.getKey()) + ":" + mapEntry.getValue());
                } else if (!up && mapEntry.getValue() < 50) {
                    result.add(getSkillName(mapEntry.getKey()) + ":" + mapEntry.getValue());
                }

            }

        }
        return result;
    }

    private void formatResult(StringBuilder stringBuilder, ArrayList<String> propList) {
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
    }

    StringBuilder showProp(String qq) {
        new SelectRoles().flushRoleChooseByFromQQ(entityTypeMessages);
        new SelectRoles().flushRoleInfoCacheByFromQQ(entityTypeMessages);

        ArrayList<String> allSelect = new ArrayList<>();

        allSelect.addAll(propMain);
        allSelect.addAll(propInvestigationOfCrimes);
        allSelect.addAll(propTalk);
        allSelect.addAll(propFight);

        StringBuilder stringBuilder = new StringBuilder();


        if (checkRoleInfoFromChooseExistByQQ(qq)) {
            stringBuilder
                    .append("\n")
                    .append("======================================================")
                    .append("\n")
                    .append(getRoleChooseByQQ(qq))
                    .append("的角色: ")
                    .append("包含有以下数据\n");

            stringBuilder.append("主属性为:\n");
            ArrayList<String> propMainResult = formatProp(propMain);
            Collections.sort(propMainResult);
            formatResult(stringBuilder, propMainResult);

            stringBuilder.append("\n常规侦查技能:\n");
            ArrayList<String> propInvestigationOfCrimesResult = formatProp(propInvestigationOfCrimes);
            Collections.sort(propInvestigationOfCrimesResult);
            formatResult(stringBuilder, propInvestigationOfCrimesResult);

            stringBuilder.append("\n常规社交技能:\n");
            ArrayList<String> propTalkResult = formatProp(propTalk);
            Collections.sort(propTalkResult);
            formatResult(stringBuilder, propTalkResult);

            stringBuilder.append("\n常规战斗技能:\n");
            ArrayList<String> propFightResult = formatProp(propFight);
            Collections.sort(propFightResult);
            formatResult(stringBuilder, propFightResult);

            stringBuilder.append("\n剩余技能值在50以上的技能:\n");
            ArrayList<String> propUpResult = formatProp(allSelect, true);
            Collections.sort(propUpResult);
            formatResult(stringBuilder, propUpResult);

            stringBuilder.append("\n剩余输入了技能值但不足50的技能:\n");
            ArrayList<String> propDownResult = formatProp(allSelect, false);
            Collections.sort(propDownResult);
            formatResult(stringBuilder, propDownResult);
        } else {
            stringBuilder.append(getRoleChooseByQQ(qq)).append("似乎未设定角色");
        }
        return stringBuilder;
    }
}
