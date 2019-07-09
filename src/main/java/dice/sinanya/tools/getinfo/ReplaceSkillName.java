package dice.sinanya.tools.getinfo;

import java.util.Map;

import static dice.sinanya.tools.getinfo.MakeSkillName.skillTable;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 将技能名规整化为系统可以识别的技能名（英文）
 */
public class ReplaceSkillName {

    /**
     * @param skillId 传入的疑似技能名的信息
     * @return 规整化后的技能名，都是无空格的英文名
     */
    public static String replaceSkillName(String skillId) {
        for (Map.Entry<String, String> mapEntry : skillTable.entrySet()) {
            if (mapEntry.getValue().equals(skillId)) {
                return mapEntry.getKey();
            }
        }
        return skillId + "(无法识别，不会缓存)";
    }
}
