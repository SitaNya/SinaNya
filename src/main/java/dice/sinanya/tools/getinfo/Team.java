package dice.sinanya.tools.getinfo;

import dice.sinanya.db.team.InsertTeam;
import dice.sinanya.db.team.SelectTeam;
import dice.sinanya.entity.EntityQqAndGroup;
import dice.sinanya.entity.EntityTeamInfo;
import dice.sinanya.entity.EntityTypeMessages;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static dice.sinanya.system.MessagesTeamEn.TEAM_EN;
import static dice.sinanya.tools.getinfo.GetMessagesProperties.entitySystemProperties;
import static dice.sinanya.tools.getinfo.RoleChoose.checkRoleChooseExistByQQ;
import static dice.sinanya.tools.getinfo.RoleChoose.getRoleChooseByQQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 小队信息数据库交互类
 */
public class Team {
    private static InsertTeam insertTeam = new InsertTeam();
    private static SelectTeam selectTeam = new SelectTeam();

    private Team() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 小队成员添加或小队创建
     *
     * @param entityTeamInfo 小队对象，包含群号，成员QQ号列表
     */
    public static void addIntoTeam(EntityTeamInfo entityTeamInfo) {
        insertTeam.changeTeamInfo(entityTeamInfo, true);
    }

    /**
     * 从小队中移除成员，若全部移除会视为清空小队
     *
     * @param entityTeamInfo 小队对象，包含群号，成员QQ号列表
     */
    public static void removeFromTeam(EntityTeamInfo entityTeamInfo) {
        insertTeam.changeTeamInfo(entityTeamInfo, false);
    }

    /**
     * 清空某个群的小队
     *
     * @param group 群号
     */
    public static void clearTeam(String group) {
        insertTeam.deleteGroup(group);
    }

    /**
     * 获取小队成员QQ号列表，这些数据会和人物卡角色逻辑配合计算出角色状态属性
     *
     * @param group 群号
     * @return 小队成员QQ号列表
     */
    public static List<String> queryTeam(String group) {
        return selectTeam.selectTeamInfo(group);
    }

    /**
     * 从数据库读取成员幕间成长标记数据刷写到静态变量，这个方法只在启动时调用一次
     */
    public static void flushTeamEn() {
        selectTeam.flushTeamEnFromDatabase();
    }

    /**
     * 保存成员幕间成长标记数据，这个方法会在dice.sinanya.listener.InputHistoryToDataBase中定时调用
     */
    public static void saveTeamEn() {
        for (Map.Entry<EntityQqAndGroup, ArrayList<String>> mapEntry : TEAM_EN.entrySet()) {
            insertTeam.saveTeamEnToDatabase(mapEntry);
        }
    }


    /**
     * 从成员幕间成长标记静态对象中取出某个成员的成长技能
     * 如果能够得到他的角色名则使用角色名
     * 不能得到角色名则@他
     *
     * @param entityTypeMessages 消息封装类
     * @param qqId               QQ号
     * @return 包装好的返回语句
     */
    public static String getTeamEn(EntityTypeMessages entityTypeMessages, String qqId) {
        EntityQqAndGroup entityQqAndGroup = new EntityQqAndGroup(entityTypeMessages.getFromGroup(), qqId);
        if (TEAM_EN.containsKey(entityQqAndGroup)) {
            String role;
            if (checkRoleChooseExistByQQ(qqId)) {
                role = getRoleChooseByQQ(qqId) + ": ";
            } else {
                role = "[CQ:at,qq=" + qqId + "]: ";
            }
            return role + StringUtils.join(TEAM_EN.get(entityQqAndGroup), ",");
        } else {
            return String.format(entitySystemProperties.getTeamMemberEnIsEmpty(), "[CQ:at,qq=" + qqId + "]");
        }
    }

    /**
     * 当小队清空时，删除里面所有成员的成员幕间成长标记，视为一个团结团
     *
     * @param groupId 群号
     */
    public static void clrTeamEn(String groupId) {
        insertTeam.clrTeamEnToDatabase(groupId);
    }

    /**
     * 当某人从小队中被移除时，删除里面所有成员的成员幕间成长标记，视为中途非正常脱离技能不再成长
     *
     * @param qqId    QQ号
     * @param groupId 群号
     */
    public static void rmTeamEn(String qqId, String groupId) {
        insertTeam.deleteTeamEnToDatabase(qqId, groupId);
    }
}
