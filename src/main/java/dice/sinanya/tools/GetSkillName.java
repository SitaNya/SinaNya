package dice.sinanya.tools;

import java.util.Map;

import static dice.sinanya.tools.MakeSkillName.skillTable;

public class GetSkillName {

    public static String getSkillName(String skillId) {
        for (Map.Entry<String, String> mapEntry : skillTable.entrySet()) {
            if (mapEntry.getValue().equals(skillId)) {
                return mapEntry.getKey();
            }
        }
        return "未找到" + skillId;
    }
}
