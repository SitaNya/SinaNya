package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityQqAndGroup;

import java.util.ArrayList;

import static dice.sinanya.system.MessagesTeamEn.teamEn;

/**
 * @author SitaNya
 * 日期: 2019-06-16
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明:
 */
public interface En {

    default void checkEn(int level, String skillName, String qqId, String groupId) {
        EntityQqAndGroup entityQqAndGroup = new EntityQqAndGroup(groupId, qqId);
        if (level > 1) {
            if (teamEn.containsKey(entityQqAndGroup)) {
                ArrayList<String> enSkillList = teamEn.get(entityQqAndGroup);
                enSkillList.remove(skillName);
                enSkillList.add(skillName);
                teamEn.put(entityQqAndGroup, enSkillList);
            } else {
                ArrayList<String> enSkillList = new ArrayList<>();
                enSkillList.add(skillName);
                teamEn.put(entityQqAndGroup, enSkillList);
            }
        }
    }
}
