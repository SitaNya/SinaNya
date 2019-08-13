package dice.sinanya.db.roles;

import dice.sinanya.db.tools.DbUtil;
import dice.sinanya.entity.EntityRoleTag;
import dice.sinanya.tools.getinfo.MakeRolesInfo;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.system.RoleInfoCache.ROLE_CHOOSE;
import static dice.sinanya.system.RoleInfoCache.ROLE_INFO_CACHE;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询角色信息，其中当前激活角色信息和角色技能信息的结果不会反回，而是自动刷写到静态变量中
 */
public class SelectRoles {


    public SelectRoles() {
        //        初始化时无需逻辑
    }

    /**
     * 刷新当前角色信息到缓存
     */
    public void flushRoleChooseFromDatabase() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from CHOOSE_ROLE";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        ROLE_CHOOSE.put(set.getLong("qq"), set.getString("role"));
                    }
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }

    /**
     * 将数据库中关于角色信息的内容读进缓存
     */
    @SuppressWarnings("AlibabaMethodTooLong")
    public void flushRoleInfoCacheFromDatabase() {

        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from role";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        HashMap<String, Integer> propertiesForRole = (HashMap<String, Integer>) new MakeRolesInfo().getPropertiesForRole();
                        String role = set.getString("userName");
                        long qqId = Long.parseLong(set.getString("qqId"));
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

                        propertiesForRole.put("shipping", set.getInt("shipping"));
                        propertiesForRole.put("rifle", set.getInt("rifle"));
                        propertiesForRole.put("aircraftOperation", set.getInt("aircraftOperation"));
                        propertiesForRole.put("machineGun", set.getInt("machineGun"));
                        propertiesForRole.put("sing", set.getInt("sing"));
                        propertiesForRole.put("paint", set.getInt("paint"));
                        propertiesForRole.put("tillage", set.getInt("tillage"));
                        propertiesForRole.put("photography", set.getInt("photography"));
                        propertiesForRole.put("perform", set.getInt("perform"));
                        propertiesForRole.put("forge", set.getInt("forge"));
                        propertiesForRole.put("literature", set.getInt("literature"));
                        propertiesForRole.put("calligraphy", set.getInt("calligraphy"));
                        propertiesForRole.put("music", set.getInt("music"));
                        propertiesForRole.put("cooking", set.getInt("cooking"));
                        propertiesForRole.put("tailor", set.getInt("tailor"));
                        propertiesForRole.put("haircut", set.getInt("haircut"));
                        propertiesForRole.put("architecture", set.getInt("architecture"));
                        propertiesForRole.put("dance", set.getInt("dance"));
                        propertiesForRole.put("makeWine", set.getInt("makeWine"));
                        propertiesForRole.put("fishing", set.getInt("fishing"));
                        propertiesForRole.put("potteryMaking", set.getInt("potteryMaking"));
                        propertiesForRole.put("sculpture", set.getInt("sculpture"));
                        propertiesForRole.put("acrobatics", set.getInt("acrobatics"));
                        propertiesForRole.put("fengshui", set.getInt("fengshui"));
                        propertiesForRole.put("technicalDrawing", set.getInt("technicalDrawing"));
                        propertiesForRole.put("typing", set.getInt("typing"));
                        propertiesForRole.put("shorthand", set.getInt("shorthand"));
                        propertiesForRole.put("whip", set.getInt("whip"));
                        propertiesForRole.put("electricSaw", set.getInt("electricSaw"));
                        propertiesForRole.put("axe", set.getInt("axe"));
                        propertiesForRole.put("sword", set.getInt("sword"));
                        propertiesForRole.put("flail", set.getInt("flail"));
                        propertiesForRole.put("spear", set.getInt("spear"));
                        propertiesForRole.put("submachineGun", set.getInt("submachineGun"));
                        propertiesForRole.put("archery", set.getInt("archery"));
                        propertiesForRole.put("flameEjector", set.getInt("flameEjector"));
                        propertiesForRole.put("heavyWeapons", set.getInt("heavyWeapons"));
                        propertiesForRole.put("ride", set.getInt("ride"));
                        propertiesForRole.put("geology", set.getInt("geology"));
                        propertiesForRole.put("chemistry", set.getInt("chemistry"));
                        propertiesForRole.put("biology", set.getInt("biology"));
                        propertiesForRole.put("mathematics", set.getInt("mathematics"));
                        propertiesForRole.put("astronomy", set.getInt("astronomy"));
                        propertiesForRole.put("physics", set.getInt("physics"));
                        propertiesForRole.put("pharmacy", set.getInt("pharmacy"));
                        propertiesForRole.put("botany", set.getInt("botany"));
                        propertiesForRole.put("zoology", set.getInt("zoology"));
                        propertiesForRole.put("cryptography", set.getInt("cryptography"));
                        propertiesForRole.put("engineering", set.getInt("engineering"));
                        propertiesForRole.put("meteorology", set.getInt("meteorology"));
                        propertiesForRole.put("judicialScience", set.getInt("judicialScience"));

                        ROLE_INFO_CACHE.put(new EntityRoleTag(qqId, role), propertiesForRole);
                    }
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), StringUtils.join(e.getStackTrace(), "\n"));
        }
    }
}
