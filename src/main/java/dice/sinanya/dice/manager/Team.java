package dice.sinanya.dice.manager;

import dice.sinanya.db.roles.SelectRoles;
import dice.sinanya.entity.*;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.exceptions.SanCheckSetException;
import dice.sinanya.tools.CheckSanCheck;
import dice.sinanya.tools.MakeManyRollsStr;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.MessagesTag.*;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.DBAndSize.dbGetter;
import static dice.sinanya.tools.GetSkillName.getSkillName;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.MakeSkill.makeSkill;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByQQ;
import static dice.sinanya.tools.RoleInfo.*;
import static dice.sinanya.tools.Sender.sender;
import static dice.sinanya.tools.Team.*;
import static java.lang.Math.*;

public class Team {

    private EntityTypeMessages entityTypeMessages;

    public Team(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void set() {
        new SelectRoles().flushRoleChooseByFromQQ(entityTypeMessages);
        new SelectRoles().flushRoleInfoCacheByFromQQ(entityTypeMessages);

        String tag = tagTeamSet;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String regex = "\\[CQ:at,qq=([0-9]+)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        int i = 0;
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }
        for (String qq : qqList) {
            sender(entityTypeMessages, "已将玩家: [CQ:at,qq=" + qq + "]加入小队。可以使用.team查看队伍信息,.team hp/san对成员状态进行强制调整\n其余使用方式请查看.help命令");
        }
        EntityTeamInfo entityTeamInfo = new EntityTeamInfo(entityTypeMessages.getFromGroup(), qqList);
        addIntoTeam(entityTeamInfo);
    }

    public void remove() {
        new SelectRoles().flushRoleChooseByFromQQ(entityTypeMessages);
        new SelectRoles().flushRoleInfoCacheByFromQQ(entityTypeMessages);

        String tag = tagTeamMove;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String regex = "\\[CQ:at,qq=([0-9]+)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }
        ArrayList<String> tmpQQList = qqList;
        EntityTeamInfo entityTeamInfo = new EntityTeamInfo(entityTypeMessages.getFromGroup(), qqList);
        removeFromTeam(entityTeamInfo);
        for (String qq : tmpQQList) {
            sender(entityTypeMessages, "已将玩家: [CQ:at,qq=" + qq + "]移出小队,其在这期间损失的血量和san值不会还原。");
        }
    }

    public void clr() {
        clearTeam(entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, "已清空本群小队");
    }

    public void call() {
        new SelectRoles().flushRoleChooseByFromQQ(entityTypeMessages);
        new SelectRoles().flushRoleInfoCacheByFromQQ(entityTypeMessages);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("kp正在呼叫以下成员:");
        for (String qq : queryTeam(entityTypeMessages.getFromGroup())
        ) {
            stringBuilder.append("\n[CQ:at,qq=")
                    .append(qq)
                    .append("]");
        }
        sender(entityTypeMessages, stringBuilder.toString());
    }

    public void hp() {
        new SelectRoles().flushRoleChooseByFromQQ(entityTypeMessages);
        new SelectRoles().flushRoleInfoCacheByFromQQ(entityTypeMessages);

        String tag = tagTeamHp;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String regex = "\\[CQ:at,qq=([0-9]+)]";
        final Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }

        msg = msg.replaceAll(regex, "").trim();
        for (String qq : qqList) {
            boolean add = false;
            if (msg.contains("+")) {
                msg = msg.replaceAll("\\+", "");
                add = true;
            }

            EntityStrManyRolls entityStrManyRolls = makeSkill(msg);

            String role;
            int hp;
            HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
            if (prop != null) {

                role = getRoleChooseByQQ(qq);
                hp = prop.get("hp");
                int con = prop.get("con");
                int siz = prop.get("siz");
                if (add) {
                    int maxHp = (int) ceil((con + siz) / 10);
                    int newHp = min(hp + entityStrManyRolls.getResult(), maxHp);
                    prop.put("hp", newHp);
                    setRoleInfoFromChooseByQQ(qq, prop);
                    sender(entityTypeMessages, "已为" + role + "恢复" + entityStrManyRolls.getStrManyRolls() + "=" + entityStrManyRolls.getResult() + "点血量，剩余" + newHp + "点");
                } else {
                    int newHp = max(0, hp - entityStrManyRolls.getResult());
                    prop.put("hp", newHp);
                    setRoleInfoFromChooseByQQ(qq, prop);
                    ROLE_INFO_CACHE.put(new EntityRoleTag(Long.parseLong(qq), role), prop);
                    if (newHp == 0) {
                        sender(entityTypeMessages, role + "损失" + entityStrManyRolls.getStrManyRolls() + "=" + entityStrManyRolls.getResult() + "点血量，已死亡");
                    } else if (entityStrManyRolls.getResult() >= floor(hp / 2)) {
                        sender(entityTypeMessages, "已为" + role + "降低" + entityStrManyRolls.getStrManyRolls() + "=" + entityStrManyRolls.getResult() + "点血量，剩余" + newHp + "点,已进入重伤状态");
                    } else {
                        sender(entityTypeMessages, "已为" + role + "降低" + entityStrManyRolls.getStrManyRolls() + "=" + entityStrManyRolls.getResult() + "点血量，剩余" + newHp + "点");
                    }
                }
            } else {
                sender(entityTypeMessages, "[CQ:at,qq=" + qq + "] 未选择人物卡");
            }
        }
    }

    public void san() {
        new SelectRoles().flushRoleChooseByFromQQ(entityTypeMessages);
        new SelectRoles().flushRoleInfoCacheByFromQQ(entityTypeMessages);

        String tag = tagTeamSan;
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), tag.substring(0, tag.length() - 2));
        String regex = "\\[CQ:at,qq=([0-9]+)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }

        msg = msg.replaceAll(regex, "").trim();
        for (String qq : qqList) {
            boolean add = false;
            if (msg.contains("+")) {
                msg = msg.replaceAll("\\+", "");
                add = true;
            }

            CheckSanCheck checkSanCheck = new CheckSanCheck(entityTypeMessages, qq);

            if (add) {
                try {
                    checkSanCheck.addSanCheck("理智", msg);
                } catch (PlayerSetException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (msg.contains("/")) {
                        EntitySanCheck entitySanCheck = checkSanCheck.checkSanCheck("理智", msg);
                        sender(entityTypeMessages, entitySanCheck.getStrSanCheck());
                    } else {
                        int changeValue = 0;
                        if (isNumeric(msg)) {
                            changeValue = Integer.parseInt(msg);
                        } else {
                            changeValue = new MakeManyRollsStr(msg).getRes();
                        }
                        HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
                        if (prop != null) {
                            StringBuilder strResult = new StringBuilder();
                            int newSan = max(0, prop.get("san") - changeValue);
                            String role = getRoleChooseByQQ(qq);
                            strResult.append("已为").append(role).append("减少").append(changeValue).append("点理智值，剩余").append(newSan).append("点");
                            if (newSan == 0) {
                                strResult.append("\n已永久疯狂");
                            } else if (changeValue >= 5) {
                                strResult.append("\n已进入临时性疯狂");
                            } else if (changeValue >= floor(prop.get("san") / 5)) {
                                strResult.append("\n已因单次损失值进入不定性疯狂");
                            }
                            prop.put("san", newSan);
                            setRoleInfoFromChooseByQQ(qq, prop);
                            ROLE_INFO_CACHE.put(new EntityRoleTag(Long.parseLong(qq), role), prop);
                            sender(entityTypeMessages, strResult.toString());
                        } else {
                            sender(entityTypeMessages, "未找到[CQ:at,qq=" + qq + "]的人物卡");
                        }
                    }

                } catch (SanCheckSetException | PlayerSetException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public void show() {
        new SelectRoles().flushRoleChooseByFromQQ(entityTypeMessages);
        new SelectRoles().flushRoleInfoCacheByFromQQ(entityTypeMessages);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("您的小队情况目前为: ");
        ArrayList<String> qqList = queryTeam(entityTypeMessages.getFromGroup());
        if (qqList == null) {
            sender(entityTypeMessages, "小队成员为空");
            return;
        }
        for (String qq : qqList) {
            HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
            if (prop != null) {
                String role = getRoleChooseByQQ(qq);
                int str = prop.get("str");
                int pow = prop.get("pow");
                int con = prop.get("con");
                int siz = prop.get("siz");
                int san = prop.get("san");
                int hp = prop.get("hp");
                int cthulhuMythos = prop.get("cthulhuMythos");

                stringBuilder
                        .append("\n")
                        .append(role)
                        .append("  ")
                        .append("血量=")
                        .append(hp)
                        .append("/")
                        .append((int) ceil((con + siz) / 10))
                        .append("  ")
                        .append("san值=")
                        .append(san)
                        .append("/")
                        .append(99 - cthulhuMythos)
                        .append("  ")
                        .append("(初始值:")
                        .append(pow)
                        .append(")  ")
                        .append("DB:")
                        .append(dbGetter(siz + str));
            } else {
                stringBuilder.append("\n[CQ:at,qq=").append(qq).append("] 未选择人物卡");
            }
        }
        sender(entityTypeMessages, stringBuilder.toString());
    }

    public void desc() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("您小队内成员的属性值为:\n");

        ArrayList<String> qqList = queryTeam(entityTypeMessages.getFromGroup());
        if (qqList == null) {
            sender(entityTypeMessages, "小队成员为空");
            return;
        }
        for (String qq : qqList) {
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


            if (checkRoleInfoFromChooseExistByQQ(qq)) {
                stringBuilder
                        .append("\n")
                        .append("======================================================")
                        .append("\n")
                        .append(getRoleChooseByQQ(qq))
                        .append("的角色: ")
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
                stringBuilder.append(getRoleChooseByQQ(qq) + "似乎未设定角色");
            }
        }
        entityTypeMessages.getMsgSender().SENDER.sendPrivateMsg(entityTypeMessages.getFromQQ(), stringBuilder.toString());
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
}
