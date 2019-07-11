package dice.sinanya.tools.getinfo;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.HashMap;
import java.util.Map;

import static dice.sinanya.system.MessagesSystem.NONE;
import static dice.sinanya.tools.getinfo.GetMessagesToValue.getMessagesToValue;
import static dice.sinanya.tools.getinfo.RoleInfo.getRoleInfoFromChooseByFromQQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 角色信息的具体包装生成处理类
 * <p>
 * 这里完成了从属性字符串到HashMap的过程
 * 定义了所有技能的初始值
 */
public class MakeRolesInfo {
    private EntityTypeMessages entityTypeMessages;
    private String properties;
    private HashMap<String, Integer> propertiesForRole;

    public MakeRolesInfo(EntityTypeMessages entityTypeMessages, String properties) {
        this.properties = properties;
        propertiesForRole = makeProperties();
        this.entityTypeMessages = entityTypeMessages;
    }

    public MakeRolesInfo(String properties) {
        this.properties = properties;
        propertiesForRole = makeProperties();
    }

    public MakeRolesInfo(String properties, Map<String, Integer> propertiesDefault) {
        this.properties = properties;
        propertiesForRole = makeProperties((HashMap<String, Integer>) propertiesDefault);
    }

    public MakeRolesInfo() {
        propertiesForRole = (HashMap<String, Integer>) init();
    }

    public void setPropertiesForRole(String skillName, int skillValue) {
        this.propertiesForRole.put(skillName, skillValue);
    }

    public Map<String, Integer> getPropertiesForRole() {
        return propertiesForRole;
    }

    /**
     * 先尝试获取命令发起者的当前所选角色的所有属性列表，不存在的话这里会得到propertiesDefault=null
     * 将propertiesDefault对象和传入的属性字符串放到getMessagesToValue方法中进行包装，同技能会用字符串中的值覆盖原值
     *
     * @return 包装完成的HashMap对象
     */
    private HashMap<String, Integer> makeProperties() {
        HashMap<String, Integer> propertiesDefault = (HashMap<String, Integer>) getRoleInfoFromChooseByFromQQ(entityTypeMessages);
        if (properties != null && !properties.equals(NONE)) {
            return getMessagesToValue(propertiesDefault, properties);
        }
        return propertiesDefault;
    }

    /**
     * 传入指定属性列表，和字符串一起包装后返回。全程不干涉具体是哪个QQ的哪个角色
     *
     * @param propertiesDefault 传入的属性HashMap列表对象
     * @return 包装后的对象
     */
    private HashMap<String, Integer> makeProperties(HashMap<String, Integer> propertiesDefault) {
        if (properties != null && !properties.equals(NONE)) {
            return getMessagesToValue(propertiesDefault, properties);
        }
        return propertiesDefault;
    }

    /**
     * 初始化属性值列表，里面包含了所有技能的初始值（当然不包括比如莫里斯舞之类太过偏门的技能）
     *
     * @return 初始化后的属性值列表
     */
    public Map<String, Integer> init() {
        HashMap<String, Integer> prop = new HashMap<>(200);

        prop.put("str", 0);
        prop.put("dex", 0);
        prop.put("pow", 0);
        prop.put("con", 0);
        prop.put("app", 0);
        prop.put("edu", 0);
        prop.put("siz", 0);
        prop.put("intValue", 0);
        prop.put("san", 0);
        prop.put("luck", 0);
        prop.put("mp", 0);
        prop.put("hp", 0);
        prop.put("accounting", 5);
        prop.put("anthropology", 1);
        prop.put("evaluation", 5);
        prop.put("archaeology", 1);
        prop.put("enchantment", 15);
        prop.put("toClimb", 20);
        prop.put("computerUsage", 5);
        prop.put("creditRating", 0);
        prop.put("cthulhuMythos", 0);
        prop.put("disguise", 5);
        prop.put("drive", 20);
        prop.put("electricalMaintenance", 10);
        prop.put("electronics", 1);
        prop.put("talkingSkill", 5);
        prop.put("aFistFight", 25);
        prop.put("wrangle", 25);
        prop.put("pistol", 20);
        prop.put("firstAid", 30);
        prop.put("history", 5);
        prop.put("intimidate", 15);
        prop.put("jump", 20);
        prop.put("law", 5);
        prop.put("libraryUse", 20);
        prop.put("listen", 20);
        prop.put("unlock", 1);
        prop.put("mechanicalMaintenance", 10);
        prop.put("medicalScience", 1);
        prop.put("naturalHistory", 10);
        prop.put("naturalScience", 10);
        prop.put("pilotage", 10);
        prop.put("occultScience", 5);
        prop.put("operatingHeavyMachinery", 1);
        prop.put("persuade", 10);
        prop.put("psychoanalysis", 1);
        prop.put("psychology", 10);
        prop.put("horsemanship", 5);
        prop.put("aWonderfulHand", 10);
        prop.put("investigationOfCrimes", 25);
        prop.put("stealth", 20);
        prop.put("existence", 10);
        prop.put("swimming", 20);
        prop.put("throwValue", 20);
        prop.put("trackValue", 10);
        prop.put("domesticatedAnimal", 5);
        prop.put("diving", 1);
        prop.put("blast", 1);
        prop.put("lipReading", 1);
        prop.put("hypnosis", 1);
        prop.put("artillery", 1);
        prop.put("motherTongue", 0);
        prop.put("dodge", 0);
        prop.put("shipping", 1);
        prop.put("rifle", 25);
        prop.put("aircraftOperation", 1);
        prop.put("machineGun", 10);
        prop.put("sing", 5);
        prop.put("paint", 5);
        prop.put("tillage", 5);
        prop.put("photography", 5);
        prop.put("perform", 5);
        prop.put("forge", 5);
        prop.put("literature", 5);
        prop.put("calligraphy", 5);
        prop.put("music", 5);
        prop.put("cooking", 5);
        prop.put("tailor", 5);
        prop.put("haircut", 5);
        prop.put("architecture", 5);
        prop.put("dance", 5);
        prop.put("makeWine", 5);
        prop.put("fishing", 5);
        prop.put("potteryMaking", 5);
        prop.put("sculpture", 5);
        prop.put("acrobatics", 5);
        prop.put("fengshui", 5);
        prop.put("technicalDrawing", 5);
        prop.put("typing", 5);
        prop.put("shorthand", 5);
        prop.put("whip", 5);
        prop.put("electricSaw", 10);
        prop.put("axe", 15);
        prop.put("sword", 20);
        prop.put("flail", 25);
        prop.put("spear", 25);
        prop.put("submachineGun", 15);
        prop.put("archery", 15);
        prop.put("flameEjector", 10);
        prop.put("heavyWeapons", 10);
        prop.put("ride", 5);
        prop.put("geology", 1);
        prop.put("chemistry", 1);
        prop.put("biology", 1);
        prop.put("mathematics", 1);
        prop.put("astronomy", 1);
        prop.put("physics", 1);
        prop.put("pharmacy", 1);
        prop.put("botany", 1);
        prop.put("zoology", 1);
        prop.put("cryptography", 1);
        prop.put("engineering", 1);
        prop.put("meteorology", 1);
        prop.put("judicialScience", 1);

        return prop;
    }
}