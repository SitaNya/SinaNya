package dice.sinanya.db.roles;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.system.RolesInfo;

import java.sql.*;
import java.util.HashMap;

import static dice.sinanya.system.RoleInfoCache.ROLE_CHOOSE;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.GetTime.getNowString;
import static dice.sinanya.tools.GetTime.getTime;
import static dice.sinanya.tools.RoleInfo.getRoleInfoByQQ;

/**
 * 录入角色信息，包括当前角色和角色内容
 *
 * @author zhangxiaozhou
 */
public class InsertRoles {
    public void insertRoleChoose(long qqId, String role) {
        int num = 0;
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from CHOOSE_ROLE where qq=?";
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

    @SuppressWarnings("AlibabaMethodTooLong")
    public void insertRoleInfo(String properties, String role, long qqId) {
        HashMap<String, Integer> propertiesForRole = getRoleInfoByQQ(qqId, role);
        if (propertiesForRole == null) {
            propertiesForRole = new RolesInfo().init();
        }
        propertiesForRole = new RolesInfo(properties, propertiesForRole).getPropertiesForRole();
        insertRoleInfo(propertiesForRole, role, qqId);
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    public void insertRoleInfo(HashMap<String, Integer> properties, String role, long qqId) {
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

        ROLE_CHOOSE.put(qqId, role);
        insertRoleChoose(qqId, role);
        ROLE_INFO_CACHE.put(new EntityRoleTag(qqId, role), properties);

        if (num == 0) {
            insertInfo(properties, qqId, role);
        } else {
            updateInfo(properties, qqId, role);
        }

    }

    @SuppressWarnings("AlibabaMethodTooLong")
    public void insertRoleInfo(String properties, String role, String qq) {
        insertRoleInfo(properties, role, Long.parseLong(qq));
    }


    private void updateInfo(HashMap<String, Integer> propertiesForRole, long qqId, String role) {
        Timestamp timestamp = getTime(getNowString());
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
                ps.setString(65, role);
                ps.setLong(66, qqId);
                System.out.println(ps.toString());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    private void insertInfo(HashMap<String, Integer> propertiesForRole, long qqId, String role) {
        Timestamp timestamp = getTime(getNowString());
        //noinspection AlibabaMethodTooLong
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
                ps.setLong(2, qqId);
                ps.setString(3, role);
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

    public void deleteInfo(long qqId, String role) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from role where " +
                    "qqId=? and " +
                    "userName=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, qqId);
                ps.setString(2, role);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
