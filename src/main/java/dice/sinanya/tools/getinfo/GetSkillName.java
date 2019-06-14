package dice.sinanya.tools.getinfo;

import java.util.Map;

import static dice.sinanya.tools.getinfo.MakeSkillName.skillTable;

/**
 * 将技能名规整化为系统可以识别的技能名（英文）
 *
 * @author SitaNya
 */
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
