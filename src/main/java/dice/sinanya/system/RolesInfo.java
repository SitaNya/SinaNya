package dice.sinanya.system;

import java.util.HashMap;

import static dice.sinanya.tools.MakeSkillName.makeSkillName;

/**
 * @author zhangxiaozhou
 */
public class RolesInfo {
    private String properties;
    private HashMap<String, Integer> propertiesForRole;

    public RolesInfo(String properties) {
        this.properties = properties;
        propertiesForRole = makeProperties();
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
        HashMap<String, Integer> propertiesDefault = init();

        String strInputNone = "";
        if (properties != null && !properties.equals(strInputNone)) {
            StringBuilder strSkillValue = new StringBuilder();
            StringBuilder strSkillName = new StringBuilder();
            for (int i = 0; i < properties.length(); ) {
                while (i < properties.length() && !Character.isSpaceChar(properties.charAt(i)) &&
                        !Character.isDigit(properties.charAt(i)) &&
                        properties.charAt(i) != ':' &&
                        properties.charAt(i) != '=') {
                    strSkillName.append(properties.charAt(i));
                    i++;
                }

                while (i < properties.length() && Character.isDigit(properties.charAt(i))) {
                    strSkillValue.append(properties.charAt(i));
                    i++;
                }

                if (i < properties.length()) {
                    try {
                        propertiesDefault.put(makeSkillName(strSkillName.toString()), Integer.parseInt(strSkillValue.toString()));
                        strSkillName = new StringBuilder();
                        strSkillValue = new StringBuilder();
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                        System.out.println(strSkillName.toString());
                    }
                }
            }
        }
        return propertiesDefault;
    }

    private HashMap<String, Integer> init() {
        return new HashMap<String, Integer>(500) {{
            put("str",0);
            put("dex",0);
            put("pow",0);
            put("con",0);
            put("app",0);
            put("edu",0);
            put("siz",0);
            put("intValue",0);
            put("san",0);
            put("luck",0);
            put("mp",0);
            put("hp",0);
            put("accounting",0);
            put("anthropology",0);
            put("evaluation",0);
            put("archaeology",0);
            put("enchantment",0);
            put("toClimb",0);
            put("computerUsage",0);
            put("creditRating",0);
            put("cthulhuMythos",0);
            put("disguise",0);
            put("dodge",0);
            put("drive",0);
            put("electricalMaintenance",0);
            put("electronics",0);
            put("talkingSkill",0);
            put("aFistFight",0);
            put("wrangle",0);
            put("pistol",0);
            put("firstAid",0);
            put("history",0);
            put("intimidate",0);
            put("jump",0);
            put("motherTongue",0);
            put("law",0);
            put("libraryUse",0);
            put("listen",0);
            put("unlock",0);
            put("mechanicalMaintenance",0);
            put("medicalScience",0);
            put("naturalHistory",0);
            put("naturalScience",0);
            put("pilotage",0);
            put("occultScience",0);
            put("operatingHeavyMachinery",0);
            put("persuade",0);
            put("psychoanalysis",0);
            put("psychology",0);
            put("horsemanship",0);
            put("aWonderfulHand",0);
            put("investigationOfCrimes",0);
            put("stealth",0);
            put("existence",0);
            put("swimming",0);
            put("throwValue",0);
            put("trackValue",0);
            put("domesticatedAnimal",0);
            put("diving",0);
            put("blast",0);
            put("lipReading",0);
            put("hypnosis",0);
            put("artillery",0);
        }};
    }
}