package dice.sinanya.tools.getinfo;

import com.sobte.cqp.jcq.entity.Group;
import dice.sinanya.entity.EntityTypeMessages;

import java.util.List;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import static dice.sinanya.tools.getinfo.RoleChoose.*;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 获取昵称
 */
public class GetNickName {

    private GetNickName() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 如果已经设定了人物卡则默认给人物卡名字，没设定的话给QQ昵称
     *
     * @param entityTypeMessages 消息包装类
     * @return 昵称
     */
    public static String getNickName(EntityTypeMessages entityTypeMessages) {
        if (checkRoleChooseExistByFromQQ(entityTypeMessages)) {
            return getRoleChooseByFromQQ(entityTypeMessages);
        }
        switch (entityTypeMessages.getMessagesTypes()) {
            case GROUP_MSG:
            case DISCUSS_MSG:
                return CQ.getGroupMemberInfo(Long.parseLong(entityTypeMessages.getFromGroup()), Long.parseLong(entityTypeMessages.getFromQq())).getNick();
            default:
                return CQ.getStrangerInfo(Long.parseLong(entityTypeMessages.getFromQq())).getNick();
        }
    }

    /**
     * 如果已经设定了人物卡则默认给人物卡名字，没设定的话给QQ昵称
     *
     * @return 昵称
     */
    public static String getNickName(long qqId) {
        if (checkRoleChooseExistByQQ(qqId)) {
            return getRoleChooseByQQ(qqId);
        }
        return CQ.getStrangerInfo(qqId).getNick();
    }

    /**
     * 如果已经设定了人物卡则默认给人物卡名字，没设定的话给QQ昵称
     *
     * @return 昵称
     */
    public static String getUserName(long qqId) {
        return CQ.getStrangerInfo(qqId).getNick();
    }

    /**
     * 返回群或讨论组名
     *
     * @param entityTypeMessages 消息包装类
     * @return 昵称
     */
    public static String getGroupName(EntityTypeMessages entityTypeMessages) {
        return getGroupName(entityTypeMessages.getFromGroup());
    }

    /**
     * 返回群或讨论组名
     *
     * @return 昵称
     */
    public static String getGroupName(long groupId) {
        List<Group> groupList = CQ.getGroupList();
        for (Group group : groupList) {
            if (group.getId() == groupId) {
                return group.getName();
            }
        }
        return "未找到";
    }


    /**
     * 返回群或讨论组名
     *
     * @return 昵称
     */
    public static String getGroupName(String groupId) {
        List<Group> groupList = CQ.getGroupList();
        for (Group group : groupList) {
            if (group.getId() == Long.parseLong(groupId)) {
                return group.getName();
            }
        }
        return "未找到";
    }
}
