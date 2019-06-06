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

        ArrayList<String> propInvestigationOfCrimes = new ArrayList<String>() {{
            add("libraryUse");
            add("investigationOfCrimes");
            add("listen");
            add("unlock");
            add("aWonderfulHand");
        }};

        ArrayList<String> propTalk = new ArrayList<String>() {{
            add("persuade");
            add("intimidate");
            add("enchantment");
            add("talkingSkill");
            add("psychology");
        }};

        ArrayList<String> propFight = new ArrayList<String>() {{
            add("aFistFight");
            add("dodge");
            add("pistol");
            add("firstAid");
            add("medicalScience");
        }};

        ArrayList<String> allSelect = new ArrayList<String>();

        allSelect.addAll(propMain);
        allSelect.addAll(propInvestigationOfCrimes);
        allSelect.addAll(propTalk);
        allSelect.addAll(propFight);

        StringBuilder stringBuilder = new StringBuilder();


        if (checkRoleInfoFromChooseExistByFromQQ(entityTypeMessages)) {
            stringBuilder.append("您的角色: ");
            stringBuilder.append(getRoleChooseByFromQQ(entityTypeMessages))
                    .append("包含有以下数据\n");

            stringBuilder.append("主属性为:\n");
            ArrayList<String> propMainResult = showProp(propMain);
            Collections.sort(propMainResult);
            stringBuilder = formatResult(stringBuilder, propMainResult);

            stringBuilder.append("\n常规侦查技能:\n");
            ArrayList<String> propInvestigationOfCrimesResult = showProp(propInvestigationOfCrimes);
            Collections.sort(propInvestigationOfCrimesResult);
            stringBuilder = formatResult(stringBuilder, propInvestigationOfCrimesResult);

            stringBuilder.append("\n常规社交技能:\n");
            ArrayList<String> propTalkResult = showProp(propTalk);
            Collections.sort(propTalkResult);
            stringBuilder = formatResult(stringBuilder, propTalkResult);

            stringBuilder.append("\n常规战斗技能:\n");
            ArrayList<String> propFightResult = showProp(propFight);
            Collections.sort(propFightResult);
            stringBuilder = formatResult(stringBuilder, propFightResult);

            stringBuilder.append("\n剩余技能值在50以上的技能:\n");
            ArrayList<String> propUpResult = showProp(allSelect, true);
            Collections.sort(propUpResult);
            stringBuilder = formatResult(stringBuilder, propUpResult);

            stringBuilder.append("\n剩余输入了技能值但不足50的技能:\n");
            ArrayList<String> propDownResult = showProp(allSelect, false);
            Collections.sort(propDownResult);
            stringBuilder = formatResult(stringBuilder, propDownResult);
        } else {
            stringBuilder.append("您似乎未设定角色");
        }

        sender(entityTypeMessages, stringBuilder.toString());
    }

    private ArrayList<String> showProp(ArrayList<String> propMain) {

        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> mapEntry : Objects.requireNonNull(getRoleInfoFromChooseByFromQQ(entityTypeMessages)).entrySet()) {
            if (propMain.contains(mapEntry.getKey()) && mapEntry.getValue() != 0) {
                result.add(getSkillName(mapEntry.getKey()) + ":" + mapEntry.getValue());
            }

        }
        return result;
    }

    private ArrayList<String> showProp(ArrayList<String> propMain, boolean up) {
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
