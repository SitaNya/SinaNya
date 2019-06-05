package dice.sinanya.dice.manager;

import dice.sinanya.db.DbUtil;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.entity.EntityTypeMessages;
import dice.sinanya.exceptions.PlayerSetException;
import dice.sinanya.system.RolesInfo;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static dice.sinanya.system.GetRole.getRole;
import static dice.sinanya.system.RoleInfoCache.ROLE_CHOOISE;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.GetSkillName.getSkillName;
import static dice.sinanya.tools.GetTime.getNowString;
import static dice.sinanya.tools.GetTime.getTime;
import static dice.sinanya.tools.MakeMessages.deleteTag;
import static dice.sinanya.tools.SearchRole.searchRole;
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
        if (msg.contains(sepRoleAndPro) && msg.split(sepRoleAndPro).length == lenRoleAndPro) {
            role = msg.split(sepRoleAndPro)[0];
            properties = msg.split(sepRoleAndPro)[1];
            qqId = Long.parseLong(entityTypeMessages.getFromQQ());
            insertRoleInfo(ROLE_INFO_CACHE.get(new EntityRoleTag(entityTypeMessages)), properties);
        } else if (!msg.equals(tagRoleNameNone)) {
            new Roles(entityTypeMessages).get();
            if (searchRole(msg, entityTypeMessages)) {
                ROLE_CHOOISE.put(Long.parseLong(entityTypeMessages.getFromQQ()), msg);
                insertRoleChoose(Long.parseLong(entityTypeMessages.getFromQQ()), msg);
            } else {
                throw new PlayerSetException(entityTypeMessages);
            }
        }
        return true;
    }

    public void list() {
        new Roles(entityTypeMessages).get();
        new Roles(entityTypeMessages).searchRoleChoose(qqId);
        long qqId = Long.parseLong(entityTypeMessages.getFromQQ());
        StringBuilder stringBuilder = new StringBuilder();
        String strRoleNone = "";
        stringBuilder.append("您当前使用角色: \n");
        if (ROLE_CHOOISE.get(qqId) != null && !ROLE_CHOOISE.get(qqId).equals(strRoleNone)) {
            stringBuilder.append(ROLE_CHOOISE.get(qqId)).append("\n");
        } else {
            stringBuilder.append("无").append("\n");
        }
        stringBuilder.append("当前备选角色:\n");
        StringBuilder standbyRole = new StringBuilder();
//        HashMap<EntityRoleTag, HashMap<String, Integer>> tmp = ROLE_INFO_CACHE;
        for (Map.Entry<EntityRoleTag, HashMap<String, Integer>> mapEntry : ROLE_INFO_CACHE.entrySet()) {
            if (ROLE_CHOOISE.get(qqId) != null && !ROLE_CHOOISE.get(qqId).equals(mapEntry.getKey().getRole())) {
                standbyRole.append(mapEntry.getKey().getRole()).append("\n");
            } else if (ROLE_CHOOISE.get(qqId) == null) {
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
        String msg = deleteTag(entityTypeMessages.getMsgGet().getMsg(), ".st move");
        long qqId = Long.parseLong(entityTypeMessages.getFromQQ());
        if (searchRole(msg, entityTypeMessages)) {
            if (ROLE_CHOOISE.get(qqId).equals(msg)) {
                sender(entityTypeMessages, "您不能移除当前角色");
            } else {
                ROLE_INFO_CACHE.remove(new EntityRoleTag(qqId, msg));
                sender(entityTypeMessages, "已为您移除角色: " + msg);
            }
        }
    }

    public void show() {
        long qqId = Long.parseLong(entityTypeMessages.getFromQQ());
        StringBuilder stringBuilder = new StringBuilder();
        int propertiesWeight = 13;
        if (getRole(qqId, entityTypeMessages) && searchRole(ROLE_CHOOISE.get(qqId), entityTypeMessages)) {
            stringBuilder.append("您的角色: ");
            stringBuilder.append(ROLE_CHOOISE.get(qqId));
            stringBuilder.append("的属性为:\n");
            int rowNum = 0;

            for (Map.Entry<String, Integer> mapEntry : ROLE_INFO_CACHE.get(new EntityRoleTag(entityTypeMessages)).entrySet()) {
                stringBuilder.append(getSkillName(mapEntry.getKey()));
                stringBuilder.append(":");
                stringBuilder.append(mapEntry.getValue());
                if (rowNum < 3) {
                    int textLen = (getSkillName(mapEntry.getKey()) + mapEntry.getValue()).length();
                    for (int i = 0; i < propertiesWeight - textLen; i++) {
                        stringBuilder.append(" ");
                    }
                    rowNum++;
                } else {
                    stringBuilder.append("\n");
                    rowNum = 0;
                }
            }
        } else {
            stringBuilder.append("您似乎未设定角色");
        }
        sender(entityTypeMessages, stringBuilder.toString());
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    public boolean get() {
        long qqId = Long.parseLong(entityTypeMessages.getFromQQ());
        HashMap<String, Integer> propertiesForRole = new RolesInfo().getPropertiesForRole();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from role where qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, qqId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        String role = set.getString("userName");
                        propertiesForRole.put("str", set.getInt("str"));
                        propertiesForRole.put("dex", set.getInt("dex"));
                        propertiesForRole.put("pow", set.getInt("pow"));
                        propertiesForRole.put("con", set.getInt("con"));
                        propertiesForRole.put("app", set.getInt("app"));
                        propertiesForRole.put("edu", set.getInt("edu"));
                        propertiesForRole.put("siz", set.getInt("siz"));
                        propertiesForRole.put("intValue", set.getInt("intValue"));
                        propertiesForRole.put("san", set.getInt("san"));
                        propertiesForRole.put("luck", set.getInt("luck"));
                        propertiesForRole.put("mp", set.getInt("mp"));
                        propertiesForRole.put("hp", set.getInt("hp"));
                        propertiesForRole.put("accounting", set.getInt("accounting"));
                        propertiesForRole.put("anthropology", set.getInt("anthropology"));
                        propertiesForRole.put("evaluation", set.getInt("evaluation"));
                        propertiesForRole.put("archaeology", set.getInt("archaeology"));
                        propertiesForRole.put("enchantment", set.getInt("enchantment"));
                        propertiesForRole.put("toClimb", set.getInt("toClimb"));
                        propertiesForRole.put("computerUsage", set.getInt("computerUsage"));
                        propertiesForRole.put("creditRating", set.getInt("creditRating"));
                        propertiesForRole.put("cthulhuMythos", set.getInt("cthulhuMythos"));
                        propertiesForRole.put("disguise", set.getInt("disguise"));
                        propertiesForRole.put("dodge", set.getInt("dodge"));
                        propertiesForRole.put("drive", set.getInt("drive"));
                        propertiesForRole.put("electricalMaintenance", set.getInt("electricalMaintenance"));
                        propertiesForRole.put("electronics", set.getInt("electronics"));
                        propertiesForRole.put("talkingSkill", set.getInt("talkingSkill"));
                        propertiesForRole.put("aFistFight", set.getInt("aFistFight"));
                        propertiesForRole.put("wrangle", set.getInt("wrangle"));
                        propertiesForRole.put("pistol", set.getInt("pistol"));
                        propertiesForRole.put("firstAid", set.getInt("firstAid"));
                        propertiesForRole.put("history", set.getInt("history"));
                        propertiesForRole.put("intimidate", set.getInt("intimidate"));
                        propertiesForRole.put("jump", set.getInt("jump"));
                        propertiesForRole.put("motherTongue", set.getInt("motherTongue"));
                        propertiesForRole.put("law", set.getInt("law"));
                        propertiesForRole.put("libraryUse", set.getInt("libraryUse"));
                        propertiesForRole.put("listen", set.getInt("listen"));
                        propertiesForRole.put("unlock", set.getInt("unlock"));
                        propertiesForRole.put("mechanicalMaintenance", set.getInt("mechanicalMaintenance"));
                        propertiesForRole.put("medicalScience", set.getInt("medicalScience"));
                        propertiesForRole.put("naturalHistory", set.getInt("naturalHistory"));
                        propertiesForRole.put("naturalScience", set.getInt("naturalScience"));
                        propertiesForRole.put("pilotage", set.getInt("pilotage"));
                        propertiesForRole.put("occultScience", set.getInt("occultScience"));
                        propertiesForRole.put("operatingHeavyMachinery", set.getInt("operatingHeavyMachinery"));
                        propertiesForRole.put("persuade", set.getInt("persuade"));
                        propertiesForRole.put("psychoanalysis", set.getInt("psychoanalysis"));
                        propertiesForRole.put("psychology", set.getInt("psychology"));
                        propertiesForRole.put("horsemanship", set.getInt("horsemanship"));
                        propertiesForRole.put("aWonderfulHand", set.getInt("aWonderfulHand"));
                        propertiesForRole.put("investigationOfCrimes", set.getInt("investigationOfCrimes"));
                        propertiesForRole.put("stealth", set.getInt("stealth"));
                        propertiesForRole.put("existence", set.getInt("existence"));
                        propertiesForRole.put("swimming", set.getInt("swimming"));
                        propertiesForRole.put("throwValue", set.getInt("throwValue"));
                        propertiesForRole.put("trackValue", set.getInt("trackValue"));
                        propertiesForRole.put("domesticatedAnimal", set.getInt("domesticatedAnimal"));
                        propertiesForRole.put("diving", set.getInt("diving"));
                        propertiesForRole.put("blast", set.getInt("blast"));
                        propertiesForRole.put("lipReading", set.getInt("lipReading"));
                        propertiesForRole.put("hypnosis", set.getInt("hypnosis"));
                        propertiesForRole.put("artillery", set.getInt("artillery"));

                        ROLE_INFO_CACHE.put(new EntityRoleTag(qqId, role), propertiesForRole);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private void insertRoleChoose(long qqId, String role) {
        int num = 0;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from CHOOISE_ROLE where qq=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, qqId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        num++;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (num == 0) {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "INSERT INTO CHOOSE_ROLE(" +
                        "qq," +
                        "role" +
                        ") VALUES(?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setLong(1, qqId);
                    ps.setString(2, role);

                    System.out.println(ps.toString());
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "update CHOOSE_ROLE set " +
                        "role=? where qq=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, role);
                    ps.setLong(2, qqId);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void searchRoleChoose(long qqId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from CHOOSE_ROLE where qq=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, qqId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        ROLE_CHOOISE.put(set.getLong("qq"), set.getString("role"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    private void insertRoleInfo(HashMap<String, Integer> propertiesDefault, String properties) {
        HashMap<String, Integer> propertiesForRole;
        if (propertiesDefault == null) {
            propertiesForRole = new RolesInfo(properties).getPropertiesForRole();
        } else {
            propertiesForRole = new RolesInfo(properties, propertiesDefault).getPropertiesForRole();
        }
        Timestamp timestamp = getTime(getNowString());

        int num = 0;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from role where userName=? and qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, role);
                ps.setLong(2, qqId);
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        num++;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (num == 0) {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "update role set createTime=?,  str=?, dex=?, pow=?, con=?, app=?, edu=?, siz=?, intValue=?, san=?, luck=?, mp=?, hp=?, accounting=?, anthropology=?, evaluation=?, archaeology=?, enchantment=?, toClimb=?, computerUsage=?, creditRating=?, cthulhuMythos=?, disguise=?, dodge=?, drive=?, electricalMaintenance=?, electronics=?, talkingSkill=?, aFistFight=?, wrangle=?, pistol=?, firstAid=?, history=?, intimidate=?, jump=?, motherTongue=?, law=?, libraryUse=?, listen=?, `unlock`=?, mechanicalMaintenance=?, medicalScience=?, naturalHistory=?, naturalScience=?, pilotage=?, occultScience=?, operatingHeavyMachinery=?, persuade=?, psychoanalysis=?, psychology=?, horsemanship=?, aWonderfulHand=?, investigationOfCrimes=?, stealth=?, existence=?, swimming=?, throwValue=?, trackValue=?, domesticatedAnimal=?, diving=?, blast=?, lipReading=?, hypnosis=?, artillery=? where userName=? and qqId=? ";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setTimestamp(1, timestamp);
                    ps.setInt(2, propertiesForRole.get("str"));
                    ps.setInt(3, propertiesForRole.get("dex"));
                    ps.setInt(4, propertiesForRole.get("pow"));
                    ps.setInt(5, propertiesForRole.get("con"));
                    ps.setInt(6, propertiesForRole.get("app"));
                    ps.setInt(7, propertiesForRole.get("edu"));
                    ps.setInt(8, propertiesForRole.get("siz"));
                    ps.setInt(9, propertiesForRole.get("intValue"));
                    ps.setInt(10, propertiesForRole.get("san"));
                    ps.setInt(11, propertiesForRole.get("luck"));
                    ps.setInt(12, propertiesForRole.get("mp"));
                    ps.setInt(13, propertiesForRole.get("hp"));
                    ps.setInt(14, propertiesForRole.get("accounting"));
                    ps.setInt(15, propertiesForRole.get("anthropology"));
                    ps.setInt(16, propertiesForRole.get("evaluation"));
                    ps.setInt(17, propertiesForRole.get("archaeology"));
                    ps.setInt(18, propertiesForRole.get("enchantment"));
                    ps.setInt(19, propertiesForRole.get("toClimb"));
                    ps.setInt(20, propertiesForRole.get("computerUsage"));
                    ps.setInt(21, propertiesForRole.get("creditRating"));
                    ps.setInt(22, propertiesForRole.get("cthulhuMythos"));
                    ps.setInt(23, propertiesForRole.get("disguise"));
                    ps.setInt(24, propertiesForRole.get("dodge"));
                    ps.setInt(25, propertiesForRole.get("drive"));
                    ps.setInt(26, propertiesForRole.get("electricalMaintenance"));
                    ps.setInt(27, propertiesForRole.get("electronics"));
                    ps.setInt(28, propertiesForRole.get("talkingSkill"));
                    ps.setInt(29, propertiesForRole.get("aFistFight"));
                    ps.setInt(30, propertiesForRole.get("wrangle"));
                    ps.setInt(31, propertiesForRole.get("pistol"));
                    ps.setInt(32, propertiesForRole.get("firstAid"));
                    ps.setInt(33, propertiesForRole.get("history"));
                    ps.setInt(34, propertiesForRole.get("intimidate"));
                    ps.setInt(35, propertiesForRole.get("jump"));
                    ps.setInt(36, propertiesForRole.get("motherTongue"));
                    ps.setInt(37, propertiesForRole.get("law"));
                    ps.setInt(38, propertiesForRole.get("libraryUse"));
                    ps.setInt(39, propertiesForRole.get("listen"));
                    ps.setInt(40, propertiesForRole.get("unlock"));
                    ps.setInt(41, propertiesForRole.get("mechanicalMaintenance"));
                    ps.setInt(42, propertiesForRole.get("medicalScience"));
                    ps.setInt(43, propertiesForRole.get("naturalHistory"));
                    ps.setInt(44, propertiesForRole.get("naturalScience"));
                    ps.setInt(45, propertiesForRole.get("pilotage"));
                    ps.setInt(46, propertiesForRole.get("occultScience"));
                    ps.setInt(47, propertiesForRole.get("operatingHeavyMachinery"));
                    ps.setInt(48, propertiesForRole.get("persuade"));
                    ps.setInt(49, propertiesForRole.get("psychoanalysis"));
                    ps.setInt(50, propertiesForRole.get("psychology"));
                    ps.setInt(51, propertiesForRole.get("horsemanship"));
                    ps.setInt(52, propertiesForRole.get("aWonderfulHand"));
                    ps.setInt(53, propertiesForRole.get("investigationOfCrimes"));
                    ps.setInt(54, propertiesForRole.get("stealth"));
                    ps.setInt(55, propertiesForRole.get("existence"));
                    ps.setInt(56, propertiesForRole.get("swimming"));
                    ps.setInt(57, propertiesForRole.get("throwValue"));
                    ps.setInt(58, propertiesForRole.get("trackValue"));
                    ps.setInt(59, propertiesForRole.get("domesticatedAnimal"));
                    ps.setInt(60, propertiesForRole.get("diving"));
                    ps.setInt(61, propertiesForRole.get("blast"));
                    ps.setInt(62, propertiesForRole.get("lipReading"));
                    ps.setInt(63, propertiesForRole.get("hypnosis"));
                    ps.setInt(64, propertiesForRole.get("artillery"));
                    ps.setLong(65, qqId);
                    ps.setString(66, role);
                    System.out.println(ps.toString());
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "INSERT INTO role(" +
                        "createTime," +
                        "qqId," +
                        "userName," +
                        "str," +
                        "dex," +
                        "pow," +
                        "con," +
                        "app," +
                        "edu," +
                        "siz," +
                        "intValue," +
                        "san," +
                        "luck," +
                        "mp," +
                        "hp," +
                        "accounting," +
                        "anthropology," +
                        "evaluation," +
                        "archaeology," +
                        "enchantment," +
                        "toClimb," +
                        "computerUsage," +
                        "creditRating," +
                        "cthulhuMythos," +
                        "disguise," +
                        "dodge," +
                        "drive," +
                        "electricalMaintenance," +
                        "electronics," +
                        "talkingSkill," +
                        "aFistFight," +
                        "wrangle," +
                        "pistol," +
                        "firstAid," +
                        "history," +
                        "intimidate," +
                        "jump," +
                        "motherTongue," +
                        "law," +
                        "libraryUse," +
                        "listen," +
                        "`unlock`," +
                        "mechanicalMaintenance," +
                        "medicalScience," +
                        "naturalHistory," +
                        "naturalScience," +
                        "pilotage," +
                        "occultScience," +
                        "operatingHeavyMachinery," +
                        "persuade," +
                        "psychoanalysis," +
                        "psychology," +
                        "horsemanship," +
                        "aWonderfulHand," +
                        "investigationOfCrimes," +
                        "stealth," +
                        "existence," +
                        "swimming," +
                        "throwValue," +
                        "trackValue," +
                        "domesticatedAnimal," +
                        "diving," +
                        "blast," +
                        "lipReading," +
                        "hypnosis," +
                        "artillery" +
                        ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setTimestamp(1, timestamp);
                    ps.setInt(4, propertiesForRole.get("str"));
                    ps.setInt(5, propertiesForRole.get("dex"));
                    ps.setInt(6, propertiesForRole.get("pow"));
                    ps.setInt(7, propertiesForRole.get("con"));
                    ps.setInt(8, propertiesForRole.get("app"));
                    ps.setInt(9, propertiesForRole.get("edu"));
                    ps.setInt(10, propertiesForRole.get("siz"));
                    ps.setInt(11, propertiesForRole.get("intValue"));
                    ps.setInt(12, propertiesForRole.get("san"));
                    ps.setInt(13, propertiesForRole.get("luck"));
                    ps.setInt(14, propertiesForRole.get("mp"));
                    ps.setInt(15, propertiesForRole.get("hp"));
                    ps.setInt(16, propertiesForRole.get("accounting"));
                    ps.setInt(17, propertiesForRole.get("anthropology"));
                    ps.setInt(18, propertiesForRole.get("evaluation"));
                    ps.setInt(19, propertiesForRole.get("archaeology"));
                    ps.setInt(20, propertiesForRole.get("enchantment"));
                    ps.setInt(21, propertiesForRole.get("toClimb"));
                    ps.setInt(22, propertiesForRole.get("computerUsage"));
                    ps.setInt(23, propertiesForRole.get("creditRating"));
                    ps.setInt(24, propertiesForRole.get("cthulhuMythos"));
                    ps.setInt(25, propertiesForRole.get("disguise"));
                    ps.setInt(26, propertiesForRole.get("dodge"));
                    ps.setInt(27, propertiesForRole.get("drive"));
                    ps.setInt(28, propertiesForRole.get("electricalMaintenance"));
                    ps.setInt(29, propertiesForRole.get("electronics"));
                    ps.setInt(30, propertiesForRole.get("talkingSkill"));
                    ps.setInt(31, propertiesForRole.get("aFistFight"));
                    ps.setInt(32, propertiesForRole.get("wrangle"));
                    ps.setInt(33, propertiesForRole.get("pistol"));
                    ps.setInt(34, propertiesForRole.get("firstAid"));
                    ps.setInt(35, propertiesForRole.get("history"));
                    ps.setInt(36, propertiesForRole.get("intimidate"));
                    ps.setInt(37, propertiesForRole.get("jump"));
                    ps.setInt(38, propertiesForRole.get("motherTongue"));
                    ps.setInt(39, propertiesForRole.get("law"));
                    ps.setInt(40, propertiesForRole.get("libraryUse"));
                    ps.setInt(41, propertiesForRole.get("listen"));
                    ps.setInt(42, propertiesForRole.get("unlock"));
                    ps.setInt(43, propertiesForRole.get("mechanicalMaintenance"));
                    ps.setInt(44, propertiesForRole.get("medicalScience"));
                    ps.setInt(45, propertiesForRole.get("naturalHistory"));
                    ps.setInt(46, propertiesForRole.get("naturalScience"));
                    ps.setInt(47, propertiesForRole.get("pilotage"));
                    ps.setInt(48, propertiesForRole.get("occultScience"));
                    ps.setInt(49, propertiesForRole.get("operatingHeavyMachinery"));
                    ps.setInt(50, propertiesForRole.get("persuade"));
                    ps.setInt(51, propertiesForRole.get("psychoanalysis"));
                    ps.setInt(52, propertiesForRole.get("psychology"));
                    ps.setInt(53, propertiesForRole.get("horsemanship"));
                    ps.setInt(54, propertiesForRole.get("aWonderfulHand"));
                    ps.setInt(55, propertiesForRole.get("investigationOfCrimes"));
                    ps.setInt(56, propertiesForRole.get("stealth"));
                    ps.setInt(57, propertiesForRole.get("existence"));
                    ps.setInt(58, propertiesForRole.get("swimming"));
                    ps.setInt(59, propertiesForRole.get("throwValue"));
                    ps.setInt(60, propertiesForRole.get("trackValue"));
                    ps.setInt(61, propertiesForRole.get("domesticatedAnimal"));
                    ps.setInt(62, propertiesForRole.get("diving"));
                    ps.setInt(63, propertiesForRole.get("blast"));
                    ps.setInt(64, propertiesForRole.get("lipReading"));
                    ps.setInt(65, propertiesForRole.get("hypnosis"));
                    ps.setInt(66, propertiesForRole.get("artillery"));
                    System.out.println(ps.toString());
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
