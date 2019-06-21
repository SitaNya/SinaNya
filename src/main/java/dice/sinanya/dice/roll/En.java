package dice.sinanya.dice.roll;

import dice.sinanya.entity.EntityQqAndGroup;

import java.util.ArrayList;
import java.util.HashMap;

import static dice.sinanya.system.MessagesLog.LOG_SWITCH_FOR_GROUP;
import static dice.sinanya.system.MessagesTeamEn.TEAM_EN;

/**
 * @author SitaNya
 * 日期: 2019-06-16
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 接口说明: 技能成功标记接口
 */
public interface En {

    /**
     * 如果成功登记>2，且log日志开启，则认为在跑团中投出了成功技能，标记可以进行幕间成长
     *
     * @param level     成功等级
     * @param skillName 技能名
     * @param qqId      qq号
     * @param groupId   群号
     */
    default void checkEn(int level, String skillName, String qqId, String groupId) {
        EntityQqAndGroup entityQqAndGroup = new EntityQqAndGroup(groupId, qqId);
        if (level > 1 && LOG_SWITCH_FOR_GROUP.containsKey(groupId) && LOG_SWITCH_FOR_GROUP.get(groupId)) {
            if (TEAM_EN.containsKey(entityQqAndGroup)) {
                ArrayList<String> enSkillList = TEAM_EN.get(entityQqAndGroup);
                enSkillList.remove(skillName);
                enSkillList.add(skillName);
                TEAM_EN.put(entityQqAndGroup, enSkillList);
            } else {
                ArrayList<String> enSkillList = new ArrayList<>();
                enSkillList.add(skillName);
                TEAM_EN.put(entityQqAndGroup, enSkillList);
            }
        }
    }
}
