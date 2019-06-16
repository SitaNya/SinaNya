package dice.sinanya.db.roles;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.system.RolesInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;

import static dice.sinanya.system.RoleInfoCache.ROLE_CHOOSE;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;
import static dice.sinanya.tools.getinfo.GetTime.getNowString;
import static dice.sinanya.tools.getinfo.GetTime.getTime;
import static dice.sinanya.tools.getinfo.RoleInfo.getRoleInfoByQQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 录入角色信息，包含当前激活角色和角色内容（分两张表存储），log内容的信息会在另一个类中初始化好，未设定的技能将置为初始值
 */
public class InsertRoles {
    private static final Logger Log = LogManager.getLogger(InsertRoles.class);

    /**
     * 将某个QQ当前选择角色置为某个角色名，这个信息是跨群的，这也就是为什么在一个群中切角色，其它群也自动切换
     *
     * @param qqId qq号
     * @param role 角色名
     */
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
            Log.error(e.getMessage(), e);
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
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
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
                Log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 将角色信息字符串格式化后插入数据库，给某一个QQ的某一个角色
     *
     * @param properties 角色信息字符串，如“力量50体质60智力50体型30意志70侦查40聆听70……”
     * @param role       角色名
     * @param qqId       qq号
     */
    public void insertRoleInfo(String properties, String role, long qqId) {
        HashMap<String, Integer> propertiesForRole = getRoleInfoByQQ(qqId, role);
        if (propertiesForRole == null) {
            propertiesForRole = new RolesInfo().init();
        }
        propertiesForRole = new RolesInfo(properties, propertiesForRole).getPropertiesForRole();
        insertRoleInfo(propertiesForRole, role, qqId);
    }

    /**
     * 将已经格式化好的HashMap给某一个QQ的某一个角色
     *
     * @param properties 角色信息字符串格式化后的HashMap类，属性名是key，属性值是value
     * @param role 角色名
     * @param qqId qq号
     */
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
            Log.error(e.getMessage(), e);
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

    /**
     * 将角色信息字符串格式化后插入数据库，给某一个QQ的某一个角色
     *
     * @param properties 角色信息字符串，如“力量50体质60智力50体型30意志70侦查40聆听70……”
     * @param role       角色名
     * @param qq       qq号，这里可以支持String类型传入
     */
    public void insertRoleInfo(String properties, String role, String qq) {
        insertRoleInfo(properties, role, Long.parseLong(qq));
    }


    /**
     * 更新某个QQ号中某个角色的属性值
     *
     * @param propertiesForRole 角色信息字符串格式化后的HashMap类，属性名是key，属性值是value
     * @param qqId qq号
     * @param role 角色名
     */
    @SuppressWarnings("AlibabaMethodTooLong")
    private void updateInfo(HashMap<String, Integer> propertiesForRole, long qqId, String role) {
        Timestamp timestamp = getTime(getNowString());
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "update role set createTime=?,  str=?, dex=?, pow=?, con=?, app=?, edu=?, siz=?, intValue=?, san=?, luck=?, mp=?, hp=?, accounting=?, anthropology=?, evaluation=?, archaeology=?, enchantment=?, toClimb=?, computerUsage=?, creditRating=?, cthulhuMythos=?, disguise=?, dodge=?, drive=?, electricalMaintenance=?, electronics=?, talkingSkill=?, aFistFight=?, wrangle=?, pistol=?, firstAid=?, history=?, intimidate=?, jump=?, motherTongue=?, law=?, libraryUse=?, listen=?, `unlock`=?, mechanicalMaintenance=?, medicalScience=?, naturalHistory=?, naturalScience=?, pilotage=?, occultScience=?, operatingHeavyMachinery=?, persuade=?, psychoanalysis=?, psychology=?, horsemanship=?, aWonderfulHand=?, investigationOfCrimes=?, stealth=?, existence=?, swimming=?, throwValue=?, trackValue=?, domesticatedAnimal=?, diving=?, blast=?, lipReading=?, hypnosis=?, artillery=?,shipping=?, rifle=?, aircraftOperation=?, machineGun=?, sing=?, paint=?, tillage=?, photography=?, perform=?, forge=?, literature=?, calligraphy=?, music=?, cooking=?, tailor=?, haircut=?, architecture=?, dance=?, makeWine=?, fishing=?, potteryMaking=?, sculpture=?, acrobatics=?, fengshui=?, technicalDrawing=?, typing=?, shorthand=?, whip=?, electricSaw=?, axe=?, sword=?, flail=?, spear=?, submachineGun=?, archery=?, flameEjector=?, heavyWeapons=?, ride=?, geology=?, chemistry=?, biology=?, mathematics=?, astronomy=?, physics=?, pharmacy=?, botany=?, zoology=?, cryptography=?, engineering=?, meteorology=?, judicialScience=? where userName=? and qqId=? ";
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
                ps.setInt(65, propertiesForRole.get("shipping"));
                ps.setInt(66, propertiesForRole.get("rifle"));
                ps.setInt(67, propertiesForRole.get("aircraftOperation"));
                ps.setInt(68, propertiesForRole.get("machineGun"));
                ps.setInt(69, propertiesForRole.get("sing"));
                ps.setInt(70, propertiesForRole.get("paint"));
                ps.setInt(71, propertiesForRole.get("tillage"));
                ps.setInt(72, propertiesForRole.get("photography"));
                ps.setInt(73, propertiesForRole.get("perform"));
                ps.setInt(74, propertiesForRole.get("forge"));
                ps.setInt(75, propertiesForRole.get("literature"));
                ps.setInt(76, propertiesForRole.get("calligraphy"));
                ps.setInt(77, propertiesForRole.get("music"));
                ps.setInt(78, propertiesForRole.get("cooking"));
                ps.setInt(79, propertiesForRole.get("tailor"));
                ps.setInt(80, propertiesForRole.get("haircut"));
                ps.setInt(81, propertiesForRole.get("architecture"));
                ps.setInt(82, propertiesForRole.get("dance"));
                ps.setInt(83, propertiesForRole.get("makeWine"));
                ps.setInt(84, propertiesForRole.get("fishing"));
                ps.setInt(85, propertiesForRole.get("potteryMaking"));
                ps.setInt(86, propertiesForRole.get("sculpture"));
                ps.setInt(87, propertiesForRole.get("acrobatics"));
                ps.setInt(88, propertiesForRole.get("fengshui"));
                ps.setInt(89, propertiesForRole.get("technicalDrawing"));
                ps.setInt(90, propertiesForRole.get("typing"));
                ps.setInt(91, propertiesForRole.get("shorthand"));
                ps.setInt(92, propertiesForRole.get("whip"));
                ps.setInt(93, propertiesForRole.get("electricSaw"));
                ps.setInt(94, propertiesForRole.get("axe"));
                ps.setInt(95, propertiesForRole.get("sword"));
                ps.setInt(96, propertiesForRole.get("flail"));
                ps.setInt(97, propertiesForRole.get("spear"));
                ps.setInt(98, propertiesForRole.get("submachineGun"));
                ps.setInt(99, propertiesForRole.get("archery"));
                ps.setInt(100, propertiesForRole.get("flameEjector"));
                ps.setInt(101, propertiesForRole.get("heavyWeapons"));
                ps.setInt(102, propertiesForRole.get("ride"));
                ps.setInt(103, propertiesForRole.get("geology"));
                ps.setInt(104, propertiesForRole.get("chemistry"));
                ps.setInt(105, propertiesForRole.get("biology"));
                ps.setInt(106, propertiesForRole.get("mathematics"));
                ps.setInt(107, propertiesForRole.get("astronomy"));
                ps.setInt(108, propertiesForRole.get("physics"));
                ps.setInt(109, propertiesForRole.get("pharmacy"));
                ps.setInt(110, propertiesForRole.get("botany"));
                ps.setInt(111, propertiesForRole.get("zoology"));
                ps.setInt(112, propertiesForRole.get("cryptography"));
                ps.setInt(113, propertiesForRole.get("engineering"));
                ps.setInt(114, propertiesForRole.get("meteorology"));
                ps.setInt(115, propertiesForRole.get("judicialScience"));
                ps.setString(116, role);
                ps.setLong(117, qqId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }


    /**
     * 插入某个QQ号中某个角色的属性值
     *
     * @param propertiesForRole 角色信息字符串格式化后的HashMap类，属性名是key，属性值是value
     * @param qqId qq号
     * @param role 角色名
     */
    @SuppressWarnings("AlibabaMethodTooLong")
    private void insertInfo(HashMap<String, Integer> propertiesForRole, long qqId, String role) {
        Timestamp timestamp = getTime(getNowString());
        //noinspection AlibabaMethodTooLong
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO role(createTime,qqId,userName,str,dex,pow,con,app,edu,siz,intValue,san,luck,mp,hp,accounting,anthropology,evaluation,archaeology,enchantment,toClimb,computerUsage,creditRating,cthulhuMythos,disguise,dodge,drive,electricalMaintenance,electronics,talkingSkill,aFistFight,wrangle,pistol,firstAid,history,intimidate,jump,motherTongue,law,libraryUse,listen,`unlock`,mechanicalMaintenance,medicalScience,naturalHistory,naturalScience,pilotage,occultScience,operatingHeavyMachinery,persuade,psychoanalysis,psychology,horsemanship,aWonderfulHand,investigationOfCrimes,stealth,existence,swimming,throwValue,trackValue,domesticatedAnimal,diving,blast,lipReading,hypnosis,artillery,shipping,rifle,aircraftOperation,machineGun,sing,paint,tillage,photography,perform,forge,literature,calligraphy,music,cooking,tailor,haircut,architecture,dance,makeWine,fishing,potteryMaking,sculpture,acrobatics,fengshui,technicalDrawing,typing,shorthand,whip,electricSaw,axe,sword,flail,spear,submachineGun,archery,flameEjector,heavyWeapons,ride,geology,chemistry,biology,mathematics,astronomy,physics,pharmacy,botany,zoology,cryptography,engineering,meteorology,judicialScience) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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

                ps.setInt(67, propertiesForRole.get("shipping"));
                ps.setInt(68, propertiesForRole.get("rifle"));
                ps.setInt(69, propertiesForRole.get("aircraftOperation"));
                ps.setInt(70, propertiesForRole.get("machineGun"));
                ps.setInt(71, propertiesForRole.get("sing"));
                ps.setInt(72, propertiesForRole.get("paint"));
                ps.setInt(73, propertiesForRole.get("tillage"));
                ps.setInt(74, propertiesForRole.get("photography"));
                ps.setInt(75, propertiesForRole.get("perform"));
                ps.setInt(76, propertiesForRole.get("forge"));
                ps.setInt(77, propertiesForRole.get("literature"));
                ps.setInt(78, propertiesForRole.get("calligraphy"));
                ps.setInt(79, propertiesForRole.get("music"));
                ps.setInt(80, propertiesForRole.get("cooking"));
                ps.setInt(81, propertiesForRole.get("tailor"));
                ps.setInt(82, propertiesForRole.get("haircut"));
                ps.setInt(83, propertiesForRole.get("architecture"));
                ps.setInt(84, propertiesForRole.get("dance"));
                ps.setInt(85, propertiesForRole.get("makeWine"));
                ps.setInt(86, propertiesForRole.get("fishing"));
                ps.setInt(87, propertiesForRole.get("potteryMaking"));
                ps.setInt(88, propertiesForRole.get("sculpture"));
                ps.setInt(89, propertiesForRole.get("acrobatics"));
                ps.setInt(90, propertiesForRole.get("fengshui"));
                ps.setInt(91, propertiesForRole.get("technicalDrawing"));
                ps.setInt(92, propertiesForRole.get("typing"));
                ps.setInt(93, propertiesForRole.get("shorthand"));
                ps.setInt(94, propertiesForRole.get("whip"));
                ps.setInt(95, propertiesForRole.get("electricSaw"));
                ps.setInt(96, propertiesForRole.get("axe"));
                ps.setInt(97, propertiesForRole.get("sword"));
                ps.setInt(98, propertiesForRole.get("flail"));
                ps.setInt(99, propertiesForRole.get("spear"));
                ps.setInt(100, propertiesForRole.get("submachineGun"));
                ps.setInt(101, propertiesForRole.get("archery"));
                ps.setInt(102, propertiesForRole.get("flameEjector"));
                ps.setInt(103, propertiesForRole.get("heavyWeapons"));
                ps.setInt(104, propertiesForRole.get("ride"));
                ps.setInt(105, propertiesForRole.get("geology"));
                ps.setInt(106, propertiesForRole.get("chemistry"));
                ps.setInt(107, propertiesForRole.get("biology"));
                ps.setInt(108, propertiesForRole.get("mathematics"));
                ps.setInt(109, propertiesForRole.get("astronomy"));
                ps.setInt(110, propertiesForRole.get("physics"));
                ps.setInt(111, propertiesForRole.get("pharmacy"));
                ps.setInt(112, propertiesForRole.get("botany"));
                ps.setInt(113, propertiesForRole.get("zoology"));
                ps.setInt(114, propertiesForRole.get("cryptography"));
                ps.setInt(115, propertiesForRole.get("engineering"));
                ps.setInt(116, propertiesForRole.get("meteorology"));
                ps.setInt(117, propertiesForRole.get("judicialScience"));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * 删除某个QQ号的某个角色，由于当前角色不可删除，因此这里不用再改变一次CHOOSE_ROLE表的值
     *
     * @param qqId QQ号
     * @param role 角色
     */
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
            Log.error(e.getMessage(), e);
        }
    }
}
