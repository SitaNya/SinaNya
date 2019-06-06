package dice.sinanya.dice.manager;

import dice.sinanya.dice.roll.SanCheck;
import dice.sinanya.entity.EntityManyRolls;
import dice.sinanya.entity.EntityTeamInfo;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.DBAndSize.dbGetter;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.ManyRolls.manyRollsForInt;
import static dice.sinanya.tools.RoleChoose.getRoleChooseByQQ;
import static dice.sinanya.tools.RoleInfo.getRoleInfoFromChooseByQQ;
import static dice.sinanya.tools.RoleInfo.setRoleInfoFromChooseByQQ;
import static dice.sinanya.tools.Sender.sender;
import static dice.sinanya.tools.Team.*;
import static java.lang.Math.*;

public class Team {

    private EntityTypeMessages entityTypeMessages;

    public Team(EntityTypeMessages entityTypeMessages) {
        this.entityTypeMessages = entityTypeMessages;
    }

    public void set() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".team set");
        String regex = "\\[CQ:at,qq=([0-9]+)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }
        EntityTeamInfo entityTeamInfo = new EntityTeamInfo(entityTypeMessages.getFromGroup(), qqList);
        addIntoTeam(entityTeamInfo);
        for (String qq : qqList) {
            sender(entityTypeMessages, "已将玩家: [CQ:at,qq=" + qq + "]加入小队。可以使用.team查看队伍信息,.team hp/san对成员状态进行强制调整\n其余使用方式请查看.help命令");
        }
    }

    public void remove() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".team set");
        String regex = "\\[CQ:at,qq=([0-9]+)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }
        EntityTeamInfo entityTeamInfo = new EntityTeamInfo(entityTypeMessages.getFromGroup(), qqList);
        removeFromTeam(entityTeamInfo);
        for (String qq : qqList) {
            sender(entityTypeMessages, "已将玩家: [CQ:at,qq=" + qq + "]移出小队,其在这期间损失的血量和san值不会还原。");
        }
    }

    public void clr() {
        clearTeam(entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, "已清空本群小队");
    }

    public void call() {
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
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".team hp");
        String regex = "\\[CQ:at,qq=([0-9]+)]";
        final Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }

        msg = msg.replaceAll(regex, "").trim();
        for (String qq : qqList) {
            int changeValue = 0;
            boolean add = false;
            if (msg.contains("+")) {
                msg = msg.replaceAll("\\+", "");
                add = true;
            }
            if (isNumeric(msg)) {
                changeValue = Integer.parseInt(msg);
            } else if (msg.contains("D") || msg.contains("d")) {
                EntityManyRolls entityManyRolls;
                try {
                    entityManyRolls = new EntityManyRolls(msg).check(entityTypeMessages);
                    changeValue = manyRollsForInt(entityManyRolls.getTimes(), entityManyRolls.getRolls());
                } catch (PlayerSetException e) {
                    return;
                }
            }

            String role;
            int hp;
            HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
            if (prop != null) {
                role = getRoleChooseByQQ(qq);
                hp = prop.get("hp");
                if (add) {
                    int newHp = hp + changeValue;
                    prop.put("hp", newHp);
                    setRoleInfoFromChooseByQQ(qq, prop);
                    sender(entityTypeMessages, "已为" + role + "恢复" + changeValue + "点血量，剩余" + newHp + "点");
                } else {
                    int newHp = max(0, hp - changeValue);
                    prop.put("hp", newHp);
                    setRoleInfoFromChooseByQQ(qq, prop);
                    if (newHp == 0) {
                        sender(entityTypeMessages, role + "损失" + changeValue + "点血量，已死亡");
                    } else if (changeValue >= floor(hp / 2)) {
                        sender(entityTypeMessages, "已为" + role + "降低" + changeValue + "点血量，剩余" + newHp + "点,已进入重伤状态");
                    } else {
                        sender(entityTypeMessages, "已为" + role + "降低" + changeValue + "点血量，剩余" + newHp + "点");
                    }
                }
            } else {
                sender(entityTypeMessages, "[CQ:at,qq=" + qq + "] 未选择人物卡");
            }
        }
    }

    public void san() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".team san");
        String regex = "\\[CQ:at,qq=([0-9]+)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msg);

        ArrayList<String> qqList = new ArrayList<>();
        while (matcher.find()) {
            qqList.add(matcher.group(1));
        }

        msg = msg.replaceAll(regex, "").trim();
        String strSanCheckFunction = "";
        if ((msg.contains("d") || msg.contains("D")) || msg.contains("/")) {
            strSanCheckFunction = msg;
        }
        for (String qq : qqList) {
            int changeValue = 0;
            boolean add = false;
            if (msg.contains("+")) {
                msg = msg.replaceAll("\\+", "");
                add = true;
            }
            if (isNumeric(msg)) {
                changeValue = Integer.parseInt(msg);
            } else if ((msg.contains("D") || msg.contains("d")) && !msg.contains("/")) {
                EntityManyRolls entityManyRolls;
                try {
                    entityManyRolls = new EntityManyRolls(msg).check(entityTypeMessages);
                    changeValue = manyRollsForInt(entityManyRolls.getTimes(), entityManyRolls.getRolls());
                } catch (PlayerSetException e) {
                    return;
                }
            }

            String role;
            int san;
            HashMap<String, Integer> prop = getRoleInfoFromChooseByQQ(qq);
            if (prop != null) {
                role = getRoleChooseByQQ(qq);
                san = prop.get("san");
                if (add) {
                    int newSan = san + changeValue;
                    prop.put("san", newSan);
                    setRoleInfoFromChooseByQQ(qq, prop);
                    sender(entityTypeMessages, "已为" + role + "恢复" + changeValue + "点理智值，剩余" + newSan + "点");
                } else {
                    int newSan = max(0, san - changeValue);
                    prop.put("san", newSan);
                    setRoleInfoFromChooseByQQ(qq, prop);
                    if (changeValue == 0) {
                        new SanCheck(entityTypeMessages).sc(Long.parseLong(qq), strSanCheckFunction);
                    } else {
                        sender(entityTypeMessages, "已为" + role + "降低" + changeValue + "点理智值，剩余" + newSan + "点");
                    }
                    if (newSan == 0) {
                        sender(entityTypeMessages, role + "已永久疯狂");
                    } else if (changeValue >= 5) {
                        sender(entityTypeMessages, role + "已进入临时性疯狂");
                    } else if (changeValue >= floor(san / 5)) {
                        sender(entityTypeMessages, role + "已进入不定性疯狂");
                    }
                }
            } else {
                sender(entityTypeMessages, "[CQ:at,qq=" + qq + "] 未选择人物卡");
            }
        }
    }

    public void show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("您的小队情况目前为: ");
        for (String qq : queryTeam(entityTypeMessages.getFromGroup())
        ) {
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
                        .append("\t")
                        .append("血量=")
                        .append(hp)
                        .append("/")
                        .append((int) ceil((con + siz) / 10))
                        .append("\t")
                        .append("san值=")
                        .append(san)
                        .append("/")
                        .append(99 - cthulhuMythos)
                        .append("(初始值:")
                        .append(pow)
                        .append(")\t")
                        .append("DB:")
                        .append(dbGetter(siz + str));
            } else {
                stringBuilder.append("\n[CQ:at,qq=").append(qq).append("] 未选择人物卡");
            }
        }
        sender(entityTypeMessages, stringBuilder.toString());
    }


}
