package dice.sinanya.dice.manager;

import dice.sinanya.db.DbUtil;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTeamInfo;
import dice.sinanya.entity.EntityTypeMessages;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dice.sinanya.system.GetRole.getRoleInfo;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.CheckIsNumbers.isNumeric;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.Sender.sender;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;

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
        insertTeamInfo(entityTeamInfo, true);
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
        insertTeamInfo(entityTeamInfo, false);
        for (String qq : qqList) {
            sender(entityTypeMessages, "已将玩家: [CQ:at,qq=" + qq + "]移出小队,其在这期间损失的血量和san值不会还原。");
        }
    }

    public void clr() {
        deleteGroup(entityTypeMessages.getFromGroup());
        sender(entityTypeMessages, "已清空本群小队");
    }

    public void hp() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".team hp");
        String regex = "\\[CQ:at,qq=([0-9]+)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msg);

        String qq = "0";
        while (matcher.find()) {
            qq = matcher.group(1);
        }
        if (isNumeric(msg)) {
            new Roles(entityTypeMessages).searchRoleChoose(Long.parseLong(qq));
            HashMap<String, Integer> prop = ROLE_INFO_CACHE.get(new EntityRoleTag(Long.parseLong(qq), getRoleInfo(entityTypeMessages, Long.parseLong(qq))));
            if (prop != null) {
                String role = getRoleInfo(entityTypeMessages, Long.parseLong(qq));
                int hp = prop.get("hp");
                hp = hp - Integer.parseInt(msg);
                String newHp = "hp" + hp;
                new Roles(entityTypeMessages).insertRoleInfo(ROLE_INFO_CACHE.get(new EntityRoleTag(entityTypeMessages)), newHp);
                if (Integer.parseInt(msg) > floor(hp / 2)) {
                    sender(entityTypeMessages, "已为" + role + "降低" + hp + "点血量，剩余" + newHp + "点,已进入重伤状态");
                } else {
                    sender(entityTypeMessages, "已为" + role + "降低" + hp + "点血量，剩余" + newHp + "点");
                }
            } else {
                sender(entityTypeMessages, "[CQ:at,qq=" + qq + "] 未选择人物卡");
            }
        } else if (msg.contains("+") && isNumeric(msg.replace("+", "").trim())) {
            new Roles(entityTypeMessages).searchRoleChoose(Long.parseLong(qq));
            HashMap<String, Integer> prop = ROLE_INFO_CACHE.get(new EntityRoleTag(Long.parseLong(qq), getRoleInfo(entityTypeMessages, Long.parseLong(qq))));
            if (prop != null) {
                String role = getRoleInfo(entityTypeMessages, Long.parseLong(qq));
                int hp = prop.get("hp");
                hp = hp + Integer.parseInt(msg);
                String newHp = "hp" + hp;
                new Roles(entityTypeMessages).insertRoleInfo(ROLE_INFO_CACHE.get(new EntityRoleTag(entityTypeMessages)), newHp);
                sender(entityTypeMessages, "已为" + role + "恢复" + hp + "点血量，剩余" + newHp + "点");
            } else {
                sender(entityTypeMessages, "[CQ:at,qq=" + qq + "] 未选择人物卡");
            }
        } else {
            sender(entityTypeMessages, "输入错误，接受一个数字参数用于降低血量如.team hp @xxx 10，也可.team hp @xxx +10恢复血量");
        }
    }

    public void san() {
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".team hp");
        String regex = "\\[CQ:at,qq=([0-9]+)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msg);

        String qq = "0";
        while (matcher.find()) {
            qq = matcher.group(1);
        }
        if (isNumeric(msg)) {
            new Roles(entityTypeMessages).searchRoleChoose(Long.parseLong(qq));
            HashMap<String, Integer> prop = ROLE_INFO_CACHE.get(new EntityRoleTag(Long.parseLong(qq), getRoleInfo(entityTypeMessages, Long.parseLong(qq))));
            if (prop != null) {
                String role = getRoleInfo(entityTypeMessages, Long.parseLong(qq));
                int san = prop.get("san");
                san = san - Integer.parseInt(msg);
                String newSan = "san" + san;
                new Roles(entityTypeMessages).insertRoleInfo(ROLE_INFO_CACHE.get(new EntityRoleTag(entityTypeMessages)), newSan);
                sender(entityTypeMessages, "已为" + role + "降低" + san + "点san值，剩余" + newSan + "点");
            } else {
                sender(entityTypeMessages, "[CQ:at,qq=" + qq + "] 未选择人物卡");
            }
        } else if (msg.contains("+") && isNumeric(msg.replace("+", "").trim())) {
            new Roles(entityTypeMessages).searchRoleChoose(Long.parseLong(qq));
            HashMap<String, Integer> prop = ROLE_INFO_CACHE.get(new EntityRoleTag(Long.parseLong(qq), getRoleInfo(entityTypeMessages, Long.parseLong(qq))));
            if (prop != null) {
                String role = getRoleInfo(entityTypeMessages, Long.parseLong(qq));
                int san = prop.get("san");
                san = san + Integer.parseInt(msg);
                String newSan = "san" + san;
                new Roles(entityTypeMessages).insertRoleInfo(ROLE_INFO_CACHE.get(new EntityRoleTag(entityTypeMessages)), newSan);
                sender(entityTypeMessages, "已为" + role + "恢复" + san + "点san值，剩余" + newSan + "点");
            } else {
                sender(entityTypeMessages, "[CQ:at,qq=" + qq + "] 未选择人物卡");
            }
        } else {
            sender(entityTypeMessages, "输入错误，接受一个数字参数用于降低san值如.team san @xxx 10，也可.team san @xxx +10恢复san");
        }
    }

    private ArrayList<String> get() {
        return selectTeamInfo(entityTypeMessages.getFromGroup());
    }

    public void show() {
        new Roles(entityTypeMessages).get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("您的小队情况目前为: ");
        for (String qq : get()
        ) {
            new Roles(entityTypeMessages).searchRoleChoose(Long.parseLong(qq));
            HashMap<String, Integer> prop = ROLE_INFO_CACHE.get(new EntityRoleTag(Long.parseLong(qq), getRoleInfo(entityTypeMessages, Long.parseLong(qq))));
            if (prop != null) {
                String role = getRoleInfo(entityTypeMessages, Long.parseLong(qq));
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

    private ArrayList<String> selectTeamInfo(String groupId) {
        String strQqList = "";
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from team where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, groupId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        strQqList = set.getString("qqList");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<String>(Arrays.asList(strQqList.split(",")));
    }

    private void insertTeamInfo(EntityTeamInfo entityTeamInfo, boolean add) {
        int num = 0;
        ArrayList<String> qqList = entityTeamInfo.getQqList();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from team where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, entityTeamInfo.getGroup());
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        num++;
                        if (add) {
                            qqList.addAll(Arrays.asList(set.getString("qqList").split(",")));
                        } else {
                            for (String tmp : entityTeamInfo.getQqList()) {
                                qqList.remove(tmp);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (num == 0) {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "INSERT INTO team(" +
                        "groupId," +
                        "qqList" +
                        ") VALUES(?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, entityTeamInfo.getGroup());
                    ps.setString(2, StringUtils.join(entityTeamInfo.getQqList(), ","));
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "update team set " +
                        "qqList=? where groupId=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    Set<String> middleHashSet = new HashSet<String>(qqList);
                    ArrayList<String> afterHashSetList = new ArrayList<String>(middleHashSet);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String tmp : afterHashSetList) {
                        stringBuilder.append(tmp);
                        stringBuilder.append(",");
                    }
                    ps.setString(1, stringBuilder.toString().substring(0, stringBuilder.length() - 1));
                    ps.setString(2, entityTeamInfo.getGroup());
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void deleteGroup(String group) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from team where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, group);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private String dbGetter(int a) {
        if (a <= 64) {
            return "-2";
        } else if (a <= 84) {
            return "-1";
        } else if (a <= 124) {
            return "0";
        } else if (a <= 164) {
            return "1d4";
        } else {
            return (a - 164) / 80 + "d6";
        }
    }
}
