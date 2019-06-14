package dice.sinanya.system;

import dice.sinanya.entity.EntityTypeMessages;

import java.util.HashMap;

import static dice.sinanya.tools.GetMessagesToValue.getMessagesToValue;
import static dice.sinanya.tools.RoleInfo.getRoleInfoFromChooseByFromQQ;

/**
 * @author SitaNya
 */
public class RolesInfo {
    EntityTypeMessages entityTypeMessages;
    private String properties;
    private HashMap<String, Integer> propertiesForRole;

    public RolesInfo(EntityTypeMessages entityTypeMessages, String properties) {
        this.properties = properties;
        propertiesForRole = makeProperties();
        this.entityTypeMessages = entityTypeMessages;
    }

    public RolesInfo(String properties) {
        this.properties = properties;
        propertiesForRole = makeProperties();
    }

    public RolesInfo(String properties, HashMap<String, Integer> propertiesDefault) {
        this.properties = properties;
        propertiesForRole = makeProperties(propertiesDefault);
    }

    public RolesInfo() {
        propertiesForRole = init();
    }

    public void setPropertiesForRole(String skillName, int skillValue) {
        this.propertiesForRole.put(skillName, skillValue);
    }

    public HashMap<String, Integer> getPropertiesForRole() {
        return propertiesForRole;
    }

    private HashMap<String, Integer> makeProperties() {
        HashMap<String, Integer> propertiesDefault = getRoleInfoFromChooseByFromQQ(entityTypeMessages);

        String strInputNone = "";
        if (properties != null && !properties.equals(strInputNone)) {
            return getMessagesToValue(propertiesDefault, properties);
        }
        return propertiesDefault;
    }

    private HashMap<String, Integer> makeProperties(HashMap<String, Integer> propertiesDefault) {

        String strInputNone = "";
        if (properties != null && !properties.equals(strInputNone)) {
            return getMessagesToValue(propertiesDefault, properties);
        }
        return propertiesDefault;
    }

    public HashMap<String, Integer> init() {
        return new HashMap<String, Integer>(500) {{
            put("str", 0);
            put("dex", 0);
            put("pow", 0);
            put("con", 0);
            put("app", 0);
            put("edu", 0);
            put("siz", 0);
            put("intValue", 0);
            put("san", 0);
            put("luck", 0);
            put("mp", 0);
            put("hp", 0);
            put("accounting", 5);
            put("anthropology", 1);
            put("evaluation", 5);
            put("archaeology", 1);
            put("enchantment", 15);
            put("toClimb", 20);
            put("computerUsage", 5);
            put("creditRating", 0);
            put("cthulhuMythos", 0);
            put("disguise", 5);
            put("drive", 20);
            put("electricalMaintenance", 10);
            put("electronics", 1);
            put("talkingSkill", 5);
            put("aFistFight", 25);
            put("wrangle", 25);
            put("pistol", 20);
            put("firstAid", 30);
            put("history", 5);
            put("intimidate", 15);
            put("jump", 20);
            put("law", 5);
            put("libraryUse", 20);
            put("listen", 20);
            put("unlock", 1);
            put("mechanicalMaintenance", 10);
            put("medicalScience", 1);
            put("naturalHistory", 10);
            put("naturalScience", 10);
            put("pilotage", 10);
            put("occultScience", 5);
            put("operatingHeavyMachinery", 1);
            put("persuade", 10);
            put("psychoanalysis", 1);
            put("psychology", 10);
            put("horsemanship", 5);
            put("aWonderfulHand", 10);
            put("investigationOfCrimes", 25);
            put("stealth", 20);
            put("existence", 10);
            put("swimming", 20);
            put("throwValue", 20);
            put("trackValue", 10);
            put("domesticatedAnimal", 5);
            put("diving", 1);
            put("blast", 1);
            put("lipReading", 1);
            put("hypnosis", 1);
            put("artillery", 1);
            put("motherTongue", 0);
            put("dodge", 0);
            put("shipping", 1);
            put("rifle", 25);
            put("aircraftOperation", 1);
            put("machineGun", 10);
            put("sing", 5);
            put("paint", 5);
            put("tillage", 5);
            put("photography", 5);
            put("perform", 5);
            put("forge", 5);
            put("literature", 5);
            put("calligraphy", 5);
            put("music", 5);
            put("cooking", 5);
            put("tailor", 5);
            put("haircut", 5);
            put("architecture", 5);
            put("dance", 5);
            put("makeWine", 5);
            put("fishing", 5);
            put("potteryMaking", 5);
            put("sculpture", 5);
            put("acrobatics", 5);
            put("fengshui", 5);
            put("technicalDrawing", 5);
            put("typing", 5);
            put("shorthand", 5);
            put("whip", 5);
            put("electricSaw", 10);
            put("axe", 15);
            put("sword", 20);
            put("flail", 25);
            put("spear", 25);
            put("submachineGun", 15);
            put("archery", 15);
            put("flameEjector", 10);
            put("heavyWeapons", 10);
            put("ride", 5);
            put("geology", 1);
            put("chemistry", 1);
            put("biology", 1);
            put("mathematics", 1);
            put("astronomy", 1);
            put("physics", 1);
            put("pharmacy", 1);
            put("botany", 1);
            put("zoology", 1);
            put("cryptography", 1);
            put("engineering", 1);
            put("meteorology", 1);
            put("judicialScience", 1);
        }};
    }
}